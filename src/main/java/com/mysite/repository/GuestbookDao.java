package com.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> selectAll(){
		return sqlSession.selectList("guestbook.selectAll");
	}
	
	public int insert(GuestbookVo guestbookVo){ 
		return sqlSession.insert("guestbook.insert", guestbookVo);
	}
	
	//변경된 메소드
	public int insertNo(GuestbookVo guestbookVo){
		sqlSession.insert("guestbook.insertNo", guestbookVo);		
		return guestbookVo.getNo();
	}
	
	public int delete(GuestbookVo guestbookVo){
		return sqlSession.delete("guestbook.delete", guestbookVo);
	}

	public GuestbookVo selectByNo(int no){
		return sqlSession.selectOne("guestbook.selectByNo", no);	
	}
	
	public List<GuestbookVo> selectListByLastNo(int lastNo){
		return sqlSession.selectList("guestbook.selectListBylastNo", lastNo);
	}
}
