package com.mysite.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.service.GuestbookService;
import com.mysite.vo.GuestbookVo;
import com.mysite.vo.JSONResult;

@Controller
@RequestMapping(value = "/api/gb")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<GuestbookVo> list(@RequestParam("lastNo") int lastNo) {
		System.out.println(lastNo);
		List<GuestbookVo> list = guestbookService.getList(lastNo);
		System.out.println(list.toString());
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JSONResult add(@ModelAttribute GuestbookVo guestbookVo) {
		JSONResult resultVo = new JSONResult();
		
		guestbookVo = guestbookService.writeVo(guestbookVo);
		if(guestbookVo != null) {
			resultVo.success(guestbookVo);
		}else {
			resultVo.fail("통신성공 등록실패");
		}
		return resultVo;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONResult delete(@RequestBody GuestbookVo guestbookVo) {
		JSONResult resultVo = new JSONResult();
		
		int count = guestbookService.remove(guestbookVo);
		System.out.println(count);
		if(count != 0) {
			resultVo.success(guestbookVo.getNo()); //이번호로 찾아지운다.
		}else {
			resultVo.fail("통신성공 삭제실패");
		}
		return resultVo;
	}

	
}
