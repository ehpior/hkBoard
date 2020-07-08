package com.hk.test.dto;

public class AccountSaltDto {
	
	private int accnt_id;
	private String salt_value;
	
	public AccountSaltDto(int accnt_id, String salt_value) {
		this.accnt_id = accnt_id;
		this.salt_value = salt_value;
	}
	public int getAccnt_id() {
		return accnt_id;
	}
	public void setAccnt_id(int accnt_id) {
		this.accnt_id = accnt_id;
	}
	public String getSalt_value() {
		return salt_value;
	}
	public void setSalt_value(String salt_value) {
		this.salt_value = salt_value;
	}
	
}
