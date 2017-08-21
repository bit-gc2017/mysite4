package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.GuestbookDao;
import com.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> getList(){
		return guestbookDao.selectAll();
	}

	
	public int write(GuestbookVo guestbookVo){
		return guestbookDao.insert(guestbookVo);
	}

	public int remove(GuestbookVo guestbookVo){
		return guestbookDao.delete(guestbookVo);
	}
	
	
	public GuestbookVo writeVo(GuestbookVo guestbookVo){
		int no = guestbookDao.insertNo(guestbookVo);
		return guestbookDao.selectByNo(no);
	}
	
	public List<GuestbookVo> getList(int lastNo){
		return guestbookDao.selectListByLastNo(lastNo);
	}
	
}
