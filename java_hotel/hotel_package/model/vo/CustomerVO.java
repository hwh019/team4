package java_hotel.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerVO {
	
	private int mb_no; //key
	private String mb_id;
	private String mb_name;
	private String mb_password;
	private String mb_email;
	private boolean mb_is_admin;

	// id, password만 같으면 true;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerVO other = (CustomerVO) obj;
		return Objects.equals(mb_id, other.mb_id) && Objects.equals(mb_password, other.mb_password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mb_id, mb_password);
	}

	@Override
	public String toString() {
		return "회원번호 :" + mb_no + " 아이디 :" + mb_id + " 이름 :" + mb_name + " 이메일 :" + mb_email;
	}

	public CustomerVO(String mb_id, String mb_name, String mb_password, String mb_email) {
		this.mb_id = mb_id;
		this.mb_name = mb_name;
		this.mb_password = mb_password;
		this.mb_email = mb_email;
	}
	
	


	
}
