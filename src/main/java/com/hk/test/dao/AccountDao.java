package com.hk.test.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.hk.test.dto.AccountDto;
import com.hk.test.dto.LoginDto;
import com.hk.test.dto.LoginHistoryDto;

public interface AccountDao {
	
	public ArrayList<AccountDto> listAccount();
	public int writeAccount(AccountDto accountDto);
	public int updateAccount(int accnt_id, AccountDto accountDto);
	public int deleteAccount(int accnt_id);
	public int deleteAccountSalt(int accnt_id);
	public int writeAccountSalt(@Param("accnt_id") int accnt_id, @Param("salt") String salt);
	public Integer selectIdAccount(String id);
	public String selectAccountSalt(int accnt_id);
	
	
}
