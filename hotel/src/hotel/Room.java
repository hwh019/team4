package hotel;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
public class Room implements Serializable {
	private static final long serialVersionUID = -4287018336496117093L;
	
	/*
	 * roomNum = 방 호수, roomType = 방의 정원, roomPrice = 방 가격, 
	 * roomName = 방이름(스위트/더블/싱글 등), isReservation = 예약가능여부 / false 예약 불가능
	 * */
	private int roomNum, roomType, roomPrice;
	private String roomName;
	private boolean isReservation;
	
	//같은 호수는 존재 할 수 없으므로 확인하는 코드 추가
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return roomNum == other.roomNum;
	}
	@Override
	public int hashCode() {
		return Objects.hash(roomNum);
	}
	
	//방의 정보를 변경할 때 사용
	public void updateRoom(int roomType, int roomPrice, boolean isReservation) {
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.isReservation = isReservation;
	}
	
	//현재 있는 방 간단하게 출력하기 위하여 선언
	@Override
	public String toString() {
		return roomNum + "호 방 이름:" + roomName + "\n1박 가격: " + roomPrice + " 정원: " + roomType;
	}	

}
