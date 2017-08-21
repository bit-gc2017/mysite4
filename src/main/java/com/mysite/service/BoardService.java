package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.BoardDao;
import com.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getList(){
		return boardDao.selectAll();
	}
	
	public BoardVo getBoard(int no) {
		BoardVo boardVo = boardDao.selectBoard(no);
		if(boardVo != null) {
			boardDao.updateHit(no);
		}
		return boardVo;
	}
	
	public BoardVo getBoardNoHit(int no) {
		BoardVo boardVo = boardDao.selectBoard(no);
		return boardVo;
	}
	
	public int write(BoardVo boardVo) {
		return boardDao.insert(boardVo);
	}
	
	public int remove(BoardVo boardVo) {
		return boardDao.delete(boardVo);
	}

	public int modify(BoardVo boardVo) {
		return boardDao.update(boardVo);
	}
	
}
