package com.hk.test.dto;

import java.sql.Timestamp;

public class AccountDto {
	private int accnt_id;
	private String nickname;
	private String name;
	private String user_type;
	private String phone;
	private String id;
	private String s_passwd;
	private Timestamp last_login;
	private String block;
	
	public AccountDto() {
		
	}
	
	public AccountDto(int accnt_id, String nickname, String name, String user_type, String phone, String id,
			String s_passwd, Timestamp last_login, String block) {
		super();
		this.accnt_id = accnt_id;
		this.nickname = nickname;
		this.name = name;
		this.user_type = user_type;
		this.phone = phone;
		this.id = id;
		this.s_passwd = s_passwd;
		this.last_login = last_login;
		this.block = block;
	}
	
	public AccountDto(int accnt_id, String nickname, String name, String user_type, String phone, String id, String s_passwd, Timestamp last_login) {
		this.accnt_id = accnt_id;
		this.nickname = nickname;
		this.name = name;
		this.user_type = user_type;
		this.phone = phone;
		this.id = id;
		this.s_passwd = s_passwd;
		this.last_login = last_login;
	}
	
	@Override
	public String toString() {
		return "AccountDto [accnt_id=" + accnt_id + ", nickname=" + nickname + ", name=" + name + ", user_type="
				+ user_type + ", phone=" + phone + ", id=" + id + ", s_passwd=" + s_passwd.substring(0, 10) + ", last_login="
				+ last_login + ", block=" + block + "]";
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public int getAccnt_id() {
		return accnt_id;
	}
	public void setAccnt_id(int accnt_id) {
		this.accnt_id = accnt_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getS_passwd() {
		return s_passwd;
	}
	public void setS_passwd(String s_passwd) {
		this.s_passwd = s_passwd;
	}
	public Timestamp getLast_login() {
		return last_login;
	}
	public void setLast_login(Timestamp last_login) {
		this.last_login = last_login;
	}
	
}
