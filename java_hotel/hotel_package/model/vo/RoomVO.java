package java_hotel.model.vo;

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
		return ro_name + "|  방 번호 :" + ro_num + "| 가격 :" + ro_price + "| 최대 인원 :" + ro_max_person;
	}

}
