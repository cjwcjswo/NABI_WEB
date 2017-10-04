package nabi.web.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nabi.web.dao.MemberDAO;
import nabi.web.dto.MemberDTO;
import nabi.web.util.Constants;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO memberDAO;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public int insertMember(MemberDTO dto) {
		int result = 0;
		MemberDTO member = memberDAO.checkId(dto); // 아이디 중복체크

		// 아이디 중복일 경우
		if (member != null) {
			result = -1;
			return result;
		}
		String encPassword = passwordEncoder.encode(dto.getPassword()); // 패스워드
																		// 암호화
		dto.setPassword(encPassword);
		dto.setToken("0");
		int authNum = (int) ((Math.random() * 99998) + 1);
		sendEmail(dto.getEmail(), authNum);
		dto.setAuth(authNum);
		result = memberDAO.insertMember(dto);
		return result;
	}

	@Override
	public MemberDTO selectMember(MemberDTO dto) {
		MemberDTO member = memberDAO.checkId(dto); // 회원 검색
		if(member == null) return null; // 검색 결과가 없다면
		//비밀번호가 일치하지 않다면
		if(!passwordEncoder.matches(dto.getPassword(), member.getPassword())){
			System.out.println("비밀번호 불일치: " + dto.getPassword() + ", " + member.getPassword());
			return null;
		}
		return member;
	}

	@Override
	public void sendEmail(String email, int authNum) {
		// 인증메일을 보내기위한 세팅
		String host = "smtp.gmail.com";
		String subject = "NABI Authentcation Email!.";
		String fromName = "NABI";
		String from = "doothing123@gmail.com";
		String to1 = email;
		String content = "가입을 축하드립니다! 아래 링크를 누르면 인증이 자동적으로 완료됩니다!"
				+ "<br> <a href='"+Constants.NABI_IP+"/member/auth?email="+email+"&auth="+authNum+"'>인증하기</a>";
		try {
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth", "true");
			Session mailSession = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("doothing123", "dvorakdoothing");
				}
			});
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName, "UTF-8", "B")));
			InternetAddress[] address1 = { new InternetAddress(to1) };
			msg.setRecipients(Message.RecipientType.TO, address1);
			msg.setSubject(subject);
			msg.setSentDate(new java.util.Date());
			msg.setContent(content, "text/html;charset=UTF-8");
			Transport.send(msg); // 이메일 보내기
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int authMember(MemberDTO dto) {
		return memberDAO.authMember(dto);
	}

}
