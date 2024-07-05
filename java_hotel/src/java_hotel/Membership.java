package java_hotel;

import java.io.Serializable;

import lombok.Data;

@Data
public class Membership implements Serializable{

	private static final long serialVersionUID = -1783446289356868108L;
	
	private String id;
	private String pw;
	private String name;
	private String p_number;
	
	private final int member_num = 1;   //고유 회원 번호
	/**
	 * 회원가입
	 * @param id 아이디
	 * @param pw 비밀번호
	 * @param name 이름
	 * @param p_number 전화번호
	 */
	public Membership(String id, String pw, String name, String p_number) { 
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.p_number = p_number;
	}
	/**
	 * 비회원 로그인
	 * @param name 이름 
	 * @param p_number 전화번호
	 * @param pw 비밀번호
	 */
	public Membership(String name, String p_number, String pw) {
		this.id = null;
		this.name = name;
		this.p_number = p_number;
		this.pw = pw;
	}

	public void check_num() {
		//번호가 형식에 알맞은지
	}
	//메소드는 아직 미정
}