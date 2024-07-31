package hotel.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {	
	private int mb_no; //auto
	private String mb_id;
	private String mb_name;
	private String mb_password;
	private String mb_email;
	private char mb_is_admin; //Y / N 으로 판별
	
	public MemberVO(String mb_name, String mb_password, String mb_email, char mb_is_admin) {
		this.mb_name = mb_name;
		this.mb_password = mb_password;
		this.mb_email = mb_email;
		this.mb_is_admin = mb_is_admin;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		return Objects.equals(mb_email, other.mb_email) && Objects.equals(mb_id, other.mb_id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mb_email, mb_id);
	}

	@Override
	public String toString() {
		String is_admin = ( mb_is_admin == 'Y' ) ? "관리자 회원" : "일반 회원";
		return "["+ is_admin +"] 회원 아이디:" + mb_id + "회원명:" + mb_name + "회원 이메일:" + mb_email;
	}

	public MemberVO(String mb_id, String mb_passWord) {
		this.mb_id = mb_id;
		this.mb_password = mb_passWord;
	}
}
