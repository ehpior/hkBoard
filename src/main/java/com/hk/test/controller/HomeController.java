package com.hk.test.controller;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.hk.test.dto.AccountDto;
import com.hk.test.dto.BoardDto;
import com.hk.test.dto.LoginDto;
import com.hk.test.dto.LoginHistoryDto;
import com.hk.test.naver.NaverLoginBO;
import com.hk.test.service.AccountServiceImpl;
import com.hk.test.service.BoardServiceImpl;
import com.hk.test.service.LoginServiceImpl;
import com.hk.test.util.CommUtil;
import com.hk.test.util.RSAUtil;
import com.hk.test.util.SHA256Util;


@Controller
public class HomeController{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@Autowired
	LoginServiceImpl loginService;
	
	@Autowired
	BoardServiceImpl boardService;
	
	@Autowired
	AccountServiceImpl accountService;
	
	@Autowired
	NaverLoginBO naverLoginBO;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String rootPage(Locale locale, Model model) {
		
		System.out.println("/");
		
		
//		logger.debug( "#ex1 - debug log" );
//		logger.info( "#ex1 - info log" );
//		logger.warn( "#ex1 - warn log" );
//		logger.error( "#ex1 - error log" );
		
		
		return "redirect:home.hk";
	}

	@RequestMapping(value = "/test.hk", method = RequestMethod.GET)
	public String testPage(HttpServletRequest request, Model model) {
		
		return "test";
	}
	
	@RequestMapping(value = "/home.hk")
	public String home(HttpServletRequest request, Model model) {
			
		logger.info(CommUtil.getClientIP(request)+":/home.hk");
		
		return "home";
	}
	
	@RequestMapping(value = "/account.hk", method = RequestMethod.GET)
	public String account(HttpServletRequest request, Model model) {
		
		System.out.println("/account.hk");
		
		model.addAttribute("list", accountService.listAccount());
		
		return "account";
	}
	
	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public String deleteAccount(int accnt_id, HttpServletRequest request,  Model model) {
		
		System.out.println("/deleteAccount");
		
		accountService.deleteAccount(accnt_id);

		return "redirect:"+(String)request.getHeader("REFERER");
	}
	
}
