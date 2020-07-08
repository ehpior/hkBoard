package com.hk.test.dto;

import java.sql.Timestamp;
import java.time.Instant;

public class LoginHistoryDto {

	private int accnt_id;
	private Timestamp login_date;
	private String is_mobile;
	private String ip;
	private String browser;
	private String os;
	
	public LoginHistoryDto() {

	}

	public LoginHistoryDto(int accnt_id, Timestamp login_date, String is_mobile, String ip, String browser, String os) {
		this.accnt_id = accnt_id;
		this.login_date = login_date;
		this.is_mobile = is_mobile;
		this.ip = ip;
		this.browser = browser;
		this.os = os;
	}
	
	@Override
	public String toString() {
		return "LoginHistoryDto [accnt_id=" + accnt_id + ", login_date=" + login_date + ", is_mobile=" + is_mobile
				+ ", ip=" + ip + ", browser=" + browser + ", os=" + os + "]";
	}

	public int getAccnt_id() {
		return accnt_id;
	}
	public void setAccnt_id(int accnt_id) {
		this.accnt_id = accnt_id;
	}
	public Timestamp getLogin_date() {
		return login_date;
	}
	public void setLogin_date(Timestamp login_date) {
		this.login_date = login_date;
	}
	public String isIs_mobile() {
		return is_mobile;
	}
	public void setIs_mobile(String is_mobile) {
		this.is_mobile = is_mobile;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}

}
