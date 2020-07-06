package com.hk.test.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.test.dao.BoardDao;
import com.hk.test.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private SqlSession sqlSession;
	
	public ArrayList<BoardDto> listBoard(int boardPage, int maxSelectLimit){
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		ArrayList<BoardDto> boardList = dao.listBoard(boardPage, maxSelectLimit);
		
		return boardList;
	}
	
	public int countBoard() {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		int cnt = dao.countBoard();
		
		return cnt;
	}
	
	public BoardDto selectBoard(int board_id) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		return dao.selectBoard(board_id);
	}
	
	public int deleteBoard(int board_id) {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		return dao.deleteBoard(board_id);
	}
	
	public int insertBoard(BoardDto boardDto) {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		return dao.insertBoard(boardDto);
	}
	
	public int updateBoard(BoardDto boardDto) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		return dao.updateBoard(boardDto);
	}

}
