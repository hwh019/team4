package java_hotel.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationVO {

	private long rv_id;
	private Date rv_date;
	private int rv_room_num;
	private Date rv_start_date;
	private Date rv_end_date;
	private String rv_status;
	private int rv_total_price;
	private int rv_stay_person;
	private String mb_id;

	public ReservationVO(Date startdate, Date enddate, int rv_room_num, String mb_id) {
		this.rv_date = new Date();
		this.rv_start_date = startdate;
		this.rv_end_date = enddate;
		this.rv_room_num = rv_room_num;
		this.mb_id = mb_id;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일");
		String date = sdf2.format(rv_date);
		String start_date = sdf2.format(rv_start_date);
		String end_date = sdf2.format(rv_end_date);
		return "[예약번호 :" + rv_id + "| 예약한 날짜: " + date + "| 방 번호: " + rv_room_num + "| 시작일:" + start_date
				+ "| 종료일 :" + end_date + "| 가격: " + rv_total_price + "| 상태 : " + rv_status + "| 인원수: "
				+ rv_stay_person + "명]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationVO other = (ReservationVO) obj;
		return Objects.equals(mb_id, other.mb_id) && Objects.equals(rv_date, other.rv_date)
				&& Objects.equals(rv_end_date, other.rv_end_date) && rv_id == other.rv_id
				&& rv_room_num == other.rv_room_num && Objects.equals(rv_start_date, other.rv_start_date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mb_id, rv_date, rv_end_date, rv_id, rv_room_num, rv_start_date);
	}

}
