package com.hk.test.controller;

import java.io.File;
import java.io.FilenameFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.test.dto.BoardDto;
import com.hk.test.naver.NaverLoginBO;
import com.hk.test.service.AccountServiceImpl;
import com.hk.test.service.BoardServiceImpl;
import com.hk.test.service.LoginServiceImpl;
import com.hk.test.util.CommUtil;


@Controller
public class BoardController{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@Autowired
	LoginServiceImpl loginService;
	
	@Autowired
	BoardServiceImpl boardService;
	
	@Autowired
	AccountServiceImpl accountService;
	
	@Autowired
	NaverLoginBO naverLoginBO;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/board.hk", method = RequestMethod.GET)
	public String board(HttpServletRequest request, HttpSession session, Model model) {
		
		logger.info(CommUtil.getClientIP(request)+":/board.hk");
		
		int pageNumber = CommUtil.parseInt(request.getParameter("boardPage")==null?"1":request.getParameter("boardPage"));
		
		String maxSelectLimit_pam = request.getParameter("maxSelectLimit");
		
		if (maxSelectLimit_pam == null) {
			if(session.getAttribute("maxSelectLimit")==null) {
				session.setAttribute("maxSelectLimit", 10);
			}
		}
		else {
			session.setAttribute("maxSelectLimit", CommUtil.parseInt(maxSelectLimit_pam));			
		}
		int maxSelectLimit = (Integer)session.getAttribute("maxSelectLimit");
		
		model.addAttribute("boardCount", Math.ceil((double)boardService.countBoard()/maxSelectLimit));
		model.addAttribute("boardList", boardService.listBoard(pageNumber, maxSelectLimit));
		
		return "board/board";
	}
	
	@RequestMapping(value = "/boardView.hk", method = RequestMethod.GET)
	public String boardView(HttpServletRequest request, HttpSession session, Model model) {
		
		logger.info(CommUtil.getClientIP(request)+":/boardView.hk");
		
		int board_id = CommUtil.parseInt(request.getParameter("board_id"));
		
		BoardDto selectBoard = boardService.selectBoard(board_id);
		
		model.addAttribute("selectBoard", selectBoard);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/boardWrite.hk", method = RequestMethod.GET)
	public String boardWrite(HttpServletRequest request, HttpSession session, Model model) {
		
		logger.info(CommUtil.getClientIP(request)+":/boardWrite.hk");
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/boardWriteResult", method = RequestMethod.POST)
	public String boardWriteResult(BoardDto boardDto, HttpServletRequest request, HttpSession session, Model model) {
		
		logger.info(CommUtil.getClientIP(request)+":/boardWriteResult");
		
		boardDto.setContent(StringEscapeUtils.unescapeXml(boardDto.getContent()));
		
		File path_tmp = new File(System.getProperty("catalina.base") + "/uploads_tmp/");
		
		File[] fileNameList = path_tmp.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith((String)session.getId());
			}
		});
		for(File file : fileNameList) {
			boolean isMoved = file.renameTo(new File(System.getProperty("catalina.base")+"/uploads/"+
					boardDto.getWriter()+"_"+file.getName().substring(file.getName().indexOf("_")+1)));
		}
		
		String tmp = boardDto.getContent().replaceAll("uploads_tmp/"+(String)session.getId(), "uploads/"+boardDto.getWriter());
		
		boardDto.setContent(tmp);
		boardDto.setContent(boardDto.getContent().replace("\'", "&#39;"));		
		//boardDto.setContent(boardDto.getContent().replace("\"", "&quot;"));
		
		int result = boardService.insertBoard(boardDto);
		
		return "redirect:/board.hk";
	}
	
	@RequestMapping(value = "/boardModify.hk", method = RequestMethod.GET)
	public String boardModify(HttpServletRequest request, HttpSession session, Model model) {
		
		logger.info(CommUtil.getClientIP(request)+":/boardModify.hk");
		
		int board_id = Integer.valueOf(request.getParameter("board_id"));
		
		BoardDto boardDto = boardService.selectBoard(board_id);

		model.addAttribute("board_id", request.getParameter("board_id"));
		model.addAttribute("dto", boardDto);
		
		return "board/boardModify";
	}
	
	@RequestMapping(value = "/boardModifyResult", method = RequestMethod.POST)
	public String boardModifyResult(BoardDto boardDto, HttpServletRequest request, HttpSession session, Model model) {
		
		logger.info(CommUtil.getClientIP(request)+":/boardModifyResult");
		
		boardDto.setContent(StringEscapeUtils.unescapeXml(boardDto.getContent()));
		
		File path_tmp = new File(System.getProperty("catalina.base") + "/uploads_tmp/");
		
		File[] fileNameList = path_tmp.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith((String)session.getId());
			}
		});
		for(File file : fileNameList) {
			boolean isMoved = file.renameTo(new File(System.getProperty("catalina.base")+"/uploads/"+
					boardDto.getWriter()+"_"+file.getName().substring(file.getName().indexOf("_")+1)));
		}
		
		String tmp = boardDto.getContent().replaceAll("uploads_tmp/"+(String)session.getId(), "uploads/"+boardDto.getWriter());
		
		boardDto.setContent(tmp);
		
		boardDto.setContent(boardDto.getContent().replace("\'", "&#39;"));		
		//boardDto.setContent(boardDto.getContent().replace("\"", "&quot;"));
		
		boardService.updateBoard(boardDto);
		
		return "redirect:/board.hk";
	}
	
	@RequestMapping(value = "/boardDelete", method = RequestMethod.GET)
	public String boardDelete(HttpServletRequest request, HttpSession session, Model model) {
		
		logger.info(CommUtil.getClientIP(request)+":/boardDelete.hk");
		
		int board_id = CommUtil.parseInt(request.getParameter("board_id"));
		
		boardService.deleteBoard(board_id);
		
		return "redirect:/board.hk";
	}

}
