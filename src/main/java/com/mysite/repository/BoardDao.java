package com.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> selectAll(){
		return sqlSession.selectList("board.selectAll");
	}

	public BoardVo selectBoard(int no) {
		return sqlSession.selectOne("board.selctBoard", no);
	}
	
	public int updateHit(int no) {
		return sqlSession.update("board.updateHit", no);
	}
	
	public int insert(BoardVo boardVo) {
		return sqlSession.insert("board.insert", boardVo);
	}
	
	public int delete(BoardVo boardVo) {
		return sqlSession.delete("board.delete", boardVo);
	}
	
	public int update(BoardVo boardVo) {
		System.out.println(boardVo.toString());
		return sqlSession.update("board.update", boardVo);
	}
	
	
}
