package nabi.web.dto;
/**
 * 회원 정보 DTO
 */
public class MemberDTO {
	private String email;
	private String password;
	private String name;
	private int auth;
	private String token;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "MemberDTO [email=" + email + ", password=" + password + ", name=" + name + ", auth=" + auth + ", token="
				+ token + "]";
	}
	
}
