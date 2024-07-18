package hotel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member implements Serializable {
	private static final long serialVersionUID = -8777478153713267876L;
	
	//id = 회원 아이디, name = 회원 이름, passWord = 회원 비밀번호, email = 회원 이메일, isAdmin = 관리자 여부 확인 / false면 일반회원
	private String id, name, passWord, email;
	private boolean isAdmin;
	
	//회원정보 수정
	public void updateMember(Member tmp) {
		this.name = tmp.name; //이름은 개명했을 수 도 있음 빼도 됨
		this.passWord = tmp.passWord;
		this.email = tmp.email;
		this.isAdmin = tmp.isAdmin;
	}

}
