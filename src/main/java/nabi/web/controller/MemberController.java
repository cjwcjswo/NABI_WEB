package nabi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import nabi.web.dto.MemberDTO;
import nabi.web.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/insert")
	public int insertMember(MemberDTO dto){
		System.out.println("OK, " + dto);
		return memberService.insertMember(dto);
	}
}
