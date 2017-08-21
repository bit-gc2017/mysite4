package com.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.service.GuestbookService;
import com.mysite.vo.GuestbookVo;


@Controller
@RequestMapping(value="/gb")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/list-ajax")
	public String list(){
		return "guestbook/list-ajax";
	}
	
	@RequestMapping(value="/list")
	public String list(Model model){
		List<GuestbookVo> list = guestbookService.getList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/write")
	public String write(@ModelAttribute GuestbookVo guestbookVo){
		guestbookService.write(guestbookVo);
		return "redirect:/gb/list";
	}
	
	@RequestMapping(value="/deleteform")
	public String deleteform(){
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@ModelAttribute GuestbookVo guestbookVo){
		guestbookService.remove(guestbookVo);
		return "redirect:/gb/list";
	}
}
