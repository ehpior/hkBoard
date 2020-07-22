package com.hk.test.dto;

import java.sql.Date;

public class BoardDto {
	
	private int board_id;
	private String title="";
	private String writer="";
	private String content="";
	private Date create_date;
	private Date modify_date;
	private String notice="";
	
	public BoardDto() {
		
	}	
	
	public BoardDto(int board_id, String title, String writer, String content, Date create_date, Date modify_date,
			String notice) {
		this.board_id = board_id;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.notice = notice;
	}

	@Override
	public String toString() {
		return "BoardDto [board_id=" + board_id + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", create_date=" + create_date + ", modify_date=" + modify_date + ", notice=" + notice + "]";
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

}
