package hotel.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomVO {
	private int ro_id; //기본키
	private int ro_num; //방호수
	private int ro_price; //1박 가격
	private int ro_max_person; //최대 예약 인원수
	private String ro_name; //방이름
	private char ro_use; //방 예약 가능 여부 Y / N 으로 판별
	
	public RoomVO(int ro_num, int ro_max_person, String ro_name, char ro_use) {
		this.ro_num = ro_num;
		this.ro_max_person = ro_max_person;
		this.ro_name = ro_name;
		this.ro_use = ro_use;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomVO other = (RoomVO) obj;
		return ro_num == other.ro_num;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ro_num);
	}

	@Override
	public String toString() {
		String isUse = ro_use == 'Y' ? "가능" : "불가능";
		return  "[" + isUse + "] " + "[" + ro_num + "호 : "+ ro_name +"] 1박: " + ro_price + " 정원: " + ro_max_person + "명";
	}

	public RoomVO(int roomNum, String roomName) {
		this.ro_num = roomNum;
		this.ro_name = roomName;
	}
}
