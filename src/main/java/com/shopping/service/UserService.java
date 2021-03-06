package com.shopping.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shopping.dao.IUserDao;
import com.shopping.model.User;

@Service
public class UserService {
	
	private static final String LOGIN_SUCCESS = "{\"status\":\"login_success\"}";
	private static final String LOGIN_FAIL = "{\"status\":\"login_fail\"}";
	private static final String REGISTER_SUCCESS = "{\"status\":\"register_success\"}";
	private static final String USERNAME_ALREADY_EXIST = "{\"status\":\"username_already_exist\"}";
	private static final String USERNAME_NOT_EXIST = "{\"status\":\"username_not_exist\"}";
	
	@Resource
	private IUserDao userDao;
	
	public String login(User user) {
		if (userDao.login(user) != null) {
			return LOGIN_SUCCESS;
		}
		return LOGIN_FAIL;
	}
	
	public String register(User user) {
		if (userDao.getUser(user.getUserName()) != null) {
			return USERNAME_ALREADY_EXIST;
		}
		userDao.register(user);
		return REGISTER_SUCCESS;
	}
	
	public String checkUserName(String userName) {
		if (userDao.getUser(userName) == null) {
			return USERNAME_NOT_EXIST;
		}
		return USERNAME_ALREADY_EXIST;
	}
	
}
