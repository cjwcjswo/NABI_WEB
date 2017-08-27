package nabi.web.service;

import nabi.web.dto.MemberDTO;

public interface MemberService {
	/**
	 * ȸ�� ����
	 * @param dto ��� dto
	 * @return �����ͺ��̽� ��� ��
	 */
	int insertMember(MemberDTO dto);
	
	/**
	 * �α���
	 * @param dto ��� dto
	 * @return �����ͺ��̽� ��� ����
	 */
	MemberDTO selectMember(MemberDTO dto);
	
	/**
	 * �̸��� ����
	 * @param dto ������ ȸ�� dto
	 * @return
	 */
	int authMember(MemberDTO dto);
	
	 /**
	  * �ش��ϴ� �������� ���� ���� ������
	  * @param email: �ش��ϴ� ���� �̸���
	  * @param authNum: ���� ��ȣ
	  */
	 void sendEmail(String email, int authNum);

}
