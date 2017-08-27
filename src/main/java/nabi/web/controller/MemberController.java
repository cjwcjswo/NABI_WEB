package nabi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nabi.web.dto.MemberDTO;
import nabi.web.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService memberService;
	
	/**
	 * ȸ�������ϱ�
	 * @param dto ȸ������
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public int insertMember(MemberDTO dto){
		return memberService.insertMember(dto);
	}
	
	
	/**
	 * �α���
	 * @param dto �Է��� ȸ�� ����
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public MemberDTO login(MemberDTO dto){
		return memberService.selectMember(dto);
	}
	
	/**
	 * �̸��� ����
	 * @param dto ������ ȸ�� ����
	 * @return
	 */
	@RequestMapping("/auth")
	public String auth(MemberDTO dto){
		int result = memberService.authMember(dto);
		if(result > 0){
			return "/member/authSuccess";
		}else{
			return "/member/error";
		}
	}
	
}
