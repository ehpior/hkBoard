package com.hk.test.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.hk.test.dto.AccountDto;
import com.hk.test.dto.BoardDto;

public interface BoardDao {
	
	public BoardDto selectBoard(@Param("board_id") int board_id);
	public ArrayList<BoardDto> selectBoardList(@Param("boardPage") int boardPage, @Param("maxSelectLimit") int maxSelectLimit);
	
	public int insertBoard(BoardDto boardDto);
	public int updateBoard(BoardDto boardDto);
	
	public Integer countBoard();
	
	public int deleteBoard(@Param("board_id") int board_id);
	
}
