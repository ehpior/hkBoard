package com.hk.test.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.hk.test.dto.AccountDto;
import com.hk.test.dto.LoginDto;
import com.hk.test.dto.LoginHistoryDto;

public interface AccountDao {
	
	public int selectAccount(String id);
	public ArrayList<AccountDto> selectAccountList();
	public int insertAccount(AccountDto accountDto);
	public int insertAccountSalt(@Param("accnt_id") int accnt_id, @Param("salt") String salt);
	public int updateAccount(int accnt_id, AccountDto accountDto);
	public int deleteAccount(int accnt_id);
	public int deleteAccountSalt(int accnt_id);
	public String selectAccountSalt(int accnt_id);
	
}
