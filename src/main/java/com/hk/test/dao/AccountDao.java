package com.hk.test.dao;

import java.util.ArrayList;

import com.hk.test.dto.AccountDto;
import com.hk.test.dto.LoginDto;
import com.hk.test.dto.LoginHistoryDto;

public interface AccountDao {
	
	public ArrayList<AccountDto> listAccount();
	public int writeAccount(AccountDto accountDto);
	public int updateAccount(int accnt_id, AccountDto accountDto);
	public int deleteAccount(int accnt_id);
	
	
	
}
