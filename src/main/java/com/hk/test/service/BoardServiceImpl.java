package com.hk.test.service;

import java.util.ArrayList;
import java.util.Optional;

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
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		ArrayList<BoardDto> boardList = boardDao.selectBoardList(boardPage, maxSelectLimit);
		
		return boardList;
	}
	
	public int countBoard() {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		//int cnt = Optional.ofNullable(boardDao.countBoard()).orElse(0).intValue();
		int cnt = boardDao.countBoard();
		
		return cnt;
	}
	
	public BoardDto selectBoard(int board_id) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		BoardDto boardDto = boardDao.selectBoard(board_id);
		
		return boardDto;
	}
	
	public int deleteBoard(int board_id) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		return boardDao.deleteBoard(board_id);
	}
	
	public int insertBoard(BoardDto boardDto) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		return boardDao.insertBoard(boardDto);
	}
	
	public int updateBoard(BoardDto boardDto) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		return boardDao.updateBoard(boardDto);
	}

}
