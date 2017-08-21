package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.ReplyBoardDao;
import com.mysite.vo.ReplyBoardVo;

@Service
public class ReplyBoardService {

	@Autowired
	private ReplyBoardDao replyDao;

	public List<ReplyBoardVo> getList(){
		return replyDao.selectList();
	}

	public int write(ReplyBoardVo replyBoardVo) {
		int groupNo = replyBoardVo.getGroupNo();
		
		if(groupNo != 0) { //댓글일때  orderNo, depth 증가시키기
			System.out.println("댓글");
			int orderNo = replyBoardVo.getOrderNo();
			int depth = replyBoardVo.getDepth();
			//자신보다 orderNo가 큰 글 +1시키기
			replyDao.increaseOrderNo(groupNo, orderNo);
			
			//orderNo, depth 부모값에서 증가시키기
			replyBoardVo.setOrderNo(orderNo+1);
			replyBoardVo.setDepth(depth+1);
		}
			
		return replyDao.insert(replyBoardVo);
	}

	public ReplyBoardVo read(int no) {
		ReplyBoardVo replyBoardVo = replyDao.selectReplyBoard(no);
		
		if(replyBoardVo != null) {
			replyDao.updateHit(no);
		}
		
		return replyBoardVo;
	}
	
	
	public int modify(ReplyBoardVo replyBoardVo) {
		return replyDao.update(replyBoardVo);
	}
	
	
	public int delete(ReplyBoardVo replyBoardVo) {
		return replyDao.delete(replyBoardVo);
	}
}
