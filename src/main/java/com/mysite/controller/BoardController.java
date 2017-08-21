package com.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.BoardService;
import com.mysite.vo.BoardVo;
import com.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<BoardVo> list = boardService.getList();
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@RequestMapping(value="/read/{no}", method=RequestMethod.GET)
	public String read(@PathVariable("no") int no, Model model) {
		BoardVo boardVo = boardService.getBoard(no);
		model.addAttribute("boardVo", boardVo);
		return "board/read";
	}
	
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String writeform() {
		return "board/writeform";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		boardService.write(boardVo);		
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@ModelAttribute BoardVo boardVo, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		boardService.remove(boardVo);	
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(@RequestParam int no, Model model) {
		BoardVo boardVo = boardService.getBoardNoHit(no);
		model.addAttribute("boardVo", boardVo);
		return "board/modifyform";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyform(@ModelAttribute BoardVo boardVo, HttpSession session, Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		boardVo.setUserNo(userNo);
		boardService.modify(boardVo);
		System.out.println("aaa: " + boardVo.toString());
		return "redirect:/board/list";
	}
	
}
