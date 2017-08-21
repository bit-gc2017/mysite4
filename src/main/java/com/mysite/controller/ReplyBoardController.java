package com.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.ReplyBoardService;
import com.mysite.vo.ReplyBoardVo;
import com.mysite.vo.UserVo;

@Controller
@RequestMapping(value="/replyboard")
public class ReplyBoardController {
	
	@Autowired
	private ReplyBoardService replyService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET )
	public String list(Model model) {
		List<ReplyBoardVo> list = replyService.getList();
		model.addAttribute("list", list);
		return "replyboard/list";
	}
	
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String writeform() {
		return "replyboard/writeform";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute ReplyBoardVo replyboardVo, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		replyboardVo.setUserNo(authUser.getNo());
		
		replyService.write(replyboardVo);
		return "redirect:/replyboard/list";
	}

	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String write(@RequestParam("no") int no, Model model) {
		
		ReplyBoardVo replyboardVo = replyService.read(no);
		model.addAttribute("replyboardVo", replyboardVo);
		return "replyboard/read";
	}
	
	@RequestMapping(value="/replyform", method=RequestMethod.GET)
	public String replyform(@RequestParam("no") int no, Model model) {
		
		ReplyBoardVo replyboardVo = replyService.read(no);
		model.addAttribute("replyboardVo", replyboardVo);
		return "replyboard/replyform";
	}
	
	
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(@RequestParam("no") int no, Model model) {
		
		ReplyBoardVo replyboardVo = replyService.read(no);
		model.addAttribute("replyboardVo", replyboardVo);
		return "replyboard/modifyform";
	}

	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute ReplyBoardVo replyboardVo,  HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		replyboardVo.setUserNo(userNo);
		
		replyService.modify(replyboardVo);
		return "redirect:/replyboard/list";
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@ModelAttribute ReplyBoardVo replyboardVo,  HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		replyboardVo.setUserNo(userNo);
		
		replyService.delete(replyboardVo);
		return "redirect:/replyboard/list";
	}
}
