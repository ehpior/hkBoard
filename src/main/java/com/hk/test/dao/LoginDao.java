package com.hk.test.dao;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

import com.hk.test.dto.AccountDto;
import com.hk.test.dto.LoginDto;
import com.hk.test.dto.LoginHistoryDto;

public interface LoginDao {
	
	public AccountDto loginCheck(LoginDto loginDto);
	public int updateLastLoginAccount(@Param("last_login") Timestamp last_login, @Param("accnt_id") int accnt_id);
	public int insertLoginHistory(LoginHistoryDto loginHistoryDto);

}
