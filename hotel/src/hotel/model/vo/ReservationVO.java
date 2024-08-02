package hotel.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationVO {
	private long rv_id; //예약고유번호
	private String rv_date; //예약일
	private int rv_room_num; //예약한 방 호수
	private String rv_start_date; //이용시작일
	private String rv_end_date; //이용종료일
	private String rv_status; //예약상태
	private int rv_total_price; //총 결제액
	private int rv_stay_person; //머무르는 인원 수
	private String mb_id; //예약한 회원
}
