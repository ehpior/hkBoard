package com.hk.test.controller;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.hk.test.dto.AccountDto;
import com.hk.test.dto.BoardDto;
import com.hk.test.dto.LoginDto;
import com.hk.test.dto.LoginHistoryDto;
import com.hk.test.naver.NaverLoginBO;
import com.hk.test.service.AccountServiceImpl;
import com.hk.test.service.BoardServiceImpl;
import com.hk.test.service.LoginServiceImpl;
import com.hk.test.util.CommUtil;
import com.hk.test.util.RSAUtil;
import com.hk.test.util.SHA256Util;


@Controller
public class HomeController{
	
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
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String rootPage(Locale locale, Model model) {
		
		System.out.println("/");
		
		
		logger.debug( "#ex1 - debug log" );
		logger.info( "#ex1 - info log" );
		logger.warn( "#ex1 - warn log" );
		logger.error( "#ex1 - error log" );
		
		
		return "redirect:home.hk";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testPage(HttpServletRequest request, Model model) {
		
		return "test";
	}
	
	@RequestMapping(value = "/home.hk")
	public String home(HttpServletRequest request, Model model) {
			
		System.out.println("/home.hk");
		
		logger.debug( "#ex1 - debug log" );
		logger.info( "#ex1 - info log" );
		logger.warn( "#ex1 - warn log" );
		logger.error( "#ex1 - error log" );
		
		return "home";
	}
	
	@RequestMapping(value = "/login.hk")
	public String login(HttpServletRequest request, HttpSession session, Model model) {
		System.out.println("/login.hk");
		
		return "login";
	}
	
	//public String loginResult(HttpServletRequest request, HttpSession session, LoginDto loginDto) {
	@RequestMapping(value = "/loginResult")
	public @ResponseBody JSONObject loginResult(HttpServletRequest request, HttpSession session) {
		
		System.out.println("/loginResult");
		
		JSONObject listObj = new JSONObject();
		
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pwd");
		String _uid;
		String _pwd;
		try {
			PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");
			
			if (privateKey == null) 
			{ 
				listObj.put("state","false");
				return listObj;
			}
	
			_uid = RSAUtil.decryptRsa(privateKey, id);
			_pwd = RSAUtil.decryptRsa(privateKey, pw);
			
			//System.out.println(_uid);
			System.out.println(_pwd);
	        
			listObj.put("state","true");
			
		}catch(Exception e) {
			System.out.println("exception");
			listObj.put("state","false");
			return listObj;
		}
		
		LoginDto loginDto = new LoginDto(_uid, _pwd);
		
		AccountDto dto = loginService.loginResult(loginDto);
		
		if(dto != null) {
			if(session.getAttribute("login")!=null) {
				session.removeAttribute("login");
			}
			session.setAttribute("login", dto);
		}else {
			listObj.put("state","false");
			return listObj;
		}
		String ip = CommUtil.getClientIP(request);
		String ua = request.getHeader("user-agent");
		
		CommUtil.mobileCheck(ua);
		System.out.println(CommUtil.getClientBrowser(ua));
		System.out.println(CommUtil.getClientOS(ua));
		System.out.println(ip);
		
		Date testt = new Date(System.currentTimeMillis());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
		String strDate = dateFormat.format(testt);
		System.out.println(strDate);
		
		LoginHistoryDto loginHistoryDto = new LoginHistoryDto(dto.getAccnt_id(),
				new Timestamp(System.currentTimeMillis()), CommUtil.mobileCheck(ua), 
				ip, CommUtil.getClientBrowser(ua), CommUtil.getClientOS(ua));
		
		loginService.loginHistory(loginHistoryDto);
		
		return listObj;
	}
	
	@RequestMapping(value = "/loginResultNaver")
	// public String loginResultNaver(HttpServletRequest request, HttpSession
	// session, LoginDto loginDto) {
	public String loginResultNaver(Model model, @RequestParam String code, @RequestParam String state,
			HttpSession session) throws IOException, ParseException {
		
		System.out.println("/loginResultNaver");
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 1. 
		String apiResult = naverLoginBO.getUserProfile(oauthToken); // 
		/**
		 * apiResult json  {"resultcode":"00", "message":"success",
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		// 2.
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		// 3.
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		// 
		String nickname = (String) response_obj.get("nickname");
		System.out.println(nickname);
		// 4.
		session.setAttribute("login", nickname); // ���� ����
		//Model.addAttribute("result", apiResult);
		return "redirect:/home.hk";
	}
	
	@RequestMapping(value = "/getRSA")
	public @ResponseBody JSONObject getRSA(HttpSession session) {
		System.out.println("/getRSA");
		
		JSONObject listObj = new JSONObject();
		
		try {
		
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
	 
			session.setAttribute("_RSA_WEB_Key_", privateKey);   //세션에 RSA 개인키를 세션에 저장한다.
			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
	 
			listObj.put("RSAModulus", publicKeyModulus);  //로그인 폼에 Input Hidden에 값을 셋팅하기위해서
			listObj.put("RSAExponent", publicKeyExponent);   //로그인 폼에 Input Hidden에 값을 셋팅하기위해서
        
		}catch(Exception e) {
			
		}
		return listObj;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loginNaverUrl")
	public String loginNaverUrl(HttpSession session) {
		System.out.println("/loginNaverUrl");
		
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		return naverAuthUrl;
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session, LoginDto loginDto) {
		System.out.println("/logout");
		session.invalidate();
		return "redirect:/home.hk";
	}
	
	@RequestMapping(value = "/signUp.hk", method = RequestMethod.GET)
	public String signUp(Model model) {
		
		System.out.println("/signUp.hk");
		
		return "signUp";
	}
	
	@RequestMapping(value = "/signUpResult", method = RequestMethod.POST)
	public String signUpResult(AccountDto accountDto, Model model) {
		
		System.out.println("/signUpResult");
		
		if(accountDto.getUser_type()==null) {
			accountDto.setUser_type("K");
		}
		
		String salt = SHA256Util.generateSalt();
        
        accountDto.setS_passwd(SHA256Util.getEncrypt(accountDto.getS_passwd(), salt));
		
		accountService.signUpResult(accountDto, salt);

		return "redirect:/home.hk";
	}
	
	@RequestMapping(value = "/account.hk", method = RequestMethod.GET)
	public String account(Model model) {
		
		System.out.println("/account.hk");
		
		model.addAttribute("list", accountService.listAccount());
		
		return "account";
	}
	
	@RequestMapping(value = "/deleteAccount.hk", method = RequestMethod.POST)
	public String deleteAccount(int accnt_id, Model model) {
		
		System.out.println("/deleteAccount");
		
		accountService.deleteAccount(accnt_id);

		return "redirect:account.hk";
	}
	
	@RequestMapping(value = "/board.hk", method = RequestMethod.GET)
	public String board(HttpServletRequest request, HttpSession session, Model model) {
		
		System.out.println("/board.hk");

		
		int pageNumber = Integer.valueOf(request.getParameter("boardPage")==null?"1":request.getParameter("boardPage"));
		
		//int maxSelectLimit = Integer.valueOf(request.getParameter("maxSelectLimit"));
		int maxSelectLimit = 10;
				
		
		model.addAttribute("boardCount", (boardService.countBoard()/maxSelectLimit) + 1);
		model.addAttribute("boardList", boardService.listBoard(pageNumber, maxSelectLimit));
		
		return "board";
	}
	
	@RequestMapping(value = "/boardView.hk", method = RequestMethod.GET)
	public String boardView(HttpServletRequest request, HttpSession session, Model model) {
		
		System.out.println("/boardView.hk");
		
		model.addAttribute("selectBoard", boardService.selectBoard(Integer.parseInt(request.getParameter("board_id"))));
		
		return "boardView";
	}
	
	@RequestMapping(value = "/boardWrite.hk", method = RequestMethod.GET)
	public String boardWrite(HttpServletRequest request, HttpSession session, Model model) {
		
		System.out.println("/boardWrite.hk");
		
		return "boardWrite";
	}
	
	@RequestMapping(value = "/boardWriteResult")
	public String boardWriteResult(BoardDto boardDto, HttpServletRequest request, HttpSession session, Model model) {
		
		System.out.println("/boardWriteResult");
		
		if(boardDto.getTitle()==null) {
			boardDto.setTitle("test");
		}
		if(boardDto.getWriter()==0) {
			boardDto.setWriter(1);
		}
		if(boardDto.getContent()==null) {
			boardDto.setContent("test");
		}
		if(boardDto.getNotice()==null || boardDto.getNotice()=="") {
			boardDto.setNotice("T");
		}
		System.out.println("id : "+boardDto.getBoard_id()+", title: "+boardDto.getContent()+", writer: "+boardDto.getWriter());
		int result = boardService.insertBoard(boardDto);
		
		return "redirect:/board.hk";
	}
	
	@RequestMapping(value = "/boardModify.hk")
	public String boardModify(HttpServletRequest request, HttpSession session, Model model) {
		
		System.out.println("/boardModify.hk");
		
		BoardDto boardDto = boardService.selectBoard(Integer.valueOf(request.getParameter("board_id")));
		
		//System.out.println("id : "+boardDto.getBoard_id()+", title: "+boardDto.getContent()+", writer: "+boardDto.getWriter());
		
		model.addAttribute("board_id", request.getParameter("board_id"));
		model.addAttribute("dto", boardDto);
		
		return "boardModify";
	}
	
	@RequestMapping(value = "/boardModifyResult")
	public String boardModifyResult(BoardDto boardDto, HttpServletRequest request, HttpSession session, Model model) {
		
		System.out.println("/boardModifyResult");
		
		if(boardDto.getNotice()==null || boardDto.getNotice()=="") {
			boardDto.setNotice("T");
		}
		
		boardService.updateBoard(boardDto);
		
		return "redirect:/board.hk";
	}
	
	@RequestMapping(value = "/boardDelete", method = RequestMethod.GET)
	public String boardDelete(HttpServletRequest request, HttpSession session, Model model) {
		
		System.out.println("/boardDelete.hk");
		
		boardService.deleteBoard(Integer.parseInt(request.getParameter("board_id")));
		
		return "redirect:/board.hk";
	}
	
}
