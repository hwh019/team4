package model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomVO {

	private int ro_id; // key
	private int ro_num;
	private int ro_price;
	private int ro_max_person;
	private String ro_name;

	@Override
	public String toString() {
		return  "[" + ro_num + "호 : "+ ro_name +"] 1박: " + ro_price + " 정원: " + ro_max_person + "명";
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
	
	public RoomVO(int ro_num, int ro_max_person, String ro_name) {
		this.ro_num = ro_num;
		this.ro_max_person = ro_max_person;
		this.ro_name = ro_name;
	}
	
	public RoomVO(int roomNum) {
		this.ro_num = roomNum;
	}

}
