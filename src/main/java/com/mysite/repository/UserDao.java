package com.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo userVo) {
		
		return sqlSession.insert("user.insert", userVo);
	}
	
	public UserVo getUser(String email, String password) {
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("email", email);
		userMap.put("password", password);
		
		UserVo userVo = sqlSession.selectOne("user.selectUserByEmailPw", userMap);
		return userVo;
	}
	
	public UserVo getUser(int no) {
		UserVo userVo = sqlSession.selectOne("user.selectUserByNo", no);
		return userVo;
	}
	
	public UserVo getUser( String email ) {
		UserVo userVo = sqlSession.selectOne( "user.selectUserByEmail", email );	
		return userVo;
	}
	
	
	public int updateUser(UserVo userVo) {
		return sqlSession.update("user.update", userVo);
	}
	
}
