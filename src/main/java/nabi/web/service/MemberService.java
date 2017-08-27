package nabi.web.service;

import nabi.web.dto.MemberDTO;

public interface MemberService {
	/**
	 * 회원 가입
	 * @param dto 멤버 dto
	 * @return 데이터베이스 결과 값
	 */
	int insertMember(MemberDTO dto);
	
	/**
	 * 로그인
	 * @param dto 멤버 dto
	 * @return 데이터베이스 멤버 정보
	 */
	MemberDTO selectMember(MemberDTO dto);
	
	/**
	 * 이메일 인증
	 * @param dto 인증할 회원 dto
	 * @return
	 */
	int authMember(MemberDTO dto);
	
	 /**
	  * 해당하는 유저에게 인증 메일 보내기
	  * @param email: 해당하는 유저 이메일
	  * @param authNum: 인증 번호
	  */
	 void sendEmail(String email, int authNum);

}
