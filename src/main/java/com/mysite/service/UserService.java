package com.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.UserDao;
import com.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		return userDao.insert(userVo);
	}
	
	public UserVo getUser(String email, String password) {
		return userDao.getUser(email, password);
	}
	
	public UserVo getUser(int no) {
		return userDao.getUser(no);
	}
	
	public int updateUser(UserVo userVo) {
		return userDao.updateUser(userVo);
	}
	
	public boolean exists( String email ) {
		boolean exists;
		UserVo vo =userDao.getUser( email );
		
		if(vo != null) {
			exists = true;
		}else {
			exists = false;
		}
		return exists;
	}
}
