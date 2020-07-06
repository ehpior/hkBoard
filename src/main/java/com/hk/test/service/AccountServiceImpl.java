package com.hk.test.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.test.dao.AccountDao;
import com.hk.test.dto.AccountDto;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private SqlSession sqlSession;
	
	public ArrayList<AccountDto> listAccount(){
		
		AccountDao dao = sqlSession.getMapper(AccountDao.class);
		
		return dao.listAccount();
	}
	
	public int signUpResult(AccountDto accountDto) {
		
		AccountDao dao = sqlSession.getMapper(AccountDao.class);
		
		return dao.writeAccount(accountDto);
	}
	public int deleteAccount(int accnt_id) {
		
		AccountDao dao = sqlSession.getMapper(AccountDao.class);
		
		return dao.deleteAccount(accnt_id);
	}
}
