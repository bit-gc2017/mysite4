package com.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.ReplyBoardVo;

@Repository
public class ReplyBoardDao {
	
	@Autowired
	private SqlSession sqlssion;
	
	public List<ReplyBoardVo> selectList() {
		return sqlssion.selectList("replyboard.selectList");
	}

	public int insert(ReplyBoardVo replyBoardVo) {
		return sqlssion.insert("replyboard.insert", replyBoardVo);
	}

	public ReplyBoardVo selectReplyBoard(int no) {
		return sqlssion.selectOne("replyboard.selectReplyBoard", no);
	}
	
	public int updateHit(int no) {
		return sqlssion.update("replyboard.updateHit", no);
	}
	

	public ReplyBoardVo increaseOrderNo(int groupNo, int orderNo) {
		Map<String, Integer> imap = new HashMap<String, Integer>();
		imap.put("groupNo", groupNo);
		imap.put("orderNo", orderNo);
		return sqlssion.selectOne("replyboard.increaseOrderNo", imap);
	}
	
	public int update(ReplyBoardVo replyBoardVo) {
		System.out.println(replyBoardVo.toString());
		return sqlssion.update("replyboard.update", replyBoardVo);
	}
	
	public int delete(ReplyBoardVo replyBoardVo) {
		System.out.println(replyBoardVo.toString());
		return sqlssion.update("replyboard.delete", replyBoardVo);
	}
}
