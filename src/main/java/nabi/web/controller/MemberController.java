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
	 * 회원가입하기
	 * @param dto 회원정보
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public int insertMember(MemberDTO dto){
		return memberService.insertMember(dto);
	}
	
	
	/**
	 * 로그인
	 * @param dto 입력한 회원 정보
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public MemberDTO login(MemberDTO dto){
		return memberService.selectMember(dto);
	}
	
	/**
	 * 이메일 인증
	 * @param dto 인증할 회원 정보
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
