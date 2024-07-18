package hotel;

import java.io.Serializable;

import lombok.Data;

@Data
public class Reservation implements Serializable {
	private static final long serialVersionUID = -2859132536696208192L;
	
	/*
	 * roomNum = 예약한 방 호수, totalPrice = 총 결제금액, memberCnt = 숙박인원, 
	 * useDate = 예약일, status = 예약상태
	 * */
	private int roomNum, totalPrice ,memberCnt;
	private String useDate, status;
	
	//예약상태 변경
	public void updateReservaion(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "예약하신 " + roomNum + "호의 총 결제금액은 " + totalPrice + 
				"원 이며\n숙박인원은 " + memberCnt + "명입니다.\n예약일: " + useDate + " 예약상태: " + status;
	}

}
