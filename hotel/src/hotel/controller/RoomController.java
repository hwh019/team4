package hotel.controller;

import java.util.Scanner;

import hotel.model.vo.RoomVO;
import hotel.service.RoomServiceImp;

public class RoomController {
	
	private RoomServiceImp roomSerivce = new RoomServiceImp();
	
	private Scanner scan;
	
	public RoomController(Scanner scan) {
		this.scan = scan;
	} //end RoomController
	
	public void insertRoom() {
		RoomVO room = inputRoomExpand();
		if(roomSerivce.insertRoom(room)) {
			System.out.println( room.getRo_num() + "호 " + room.getRo_name() + " 등록되었습니다.");
		} else {
			System.out.println("이미 등록된 방입니다.");
		}
	} //end insertRoom

	private RoomVO inputRoomExpand() {
		RoomVO room = inputRoom();
		/*
		 * private int ro_price; //1박 가격
			private int ro_max_person; //최대 예약 인원수
			private int ro_use; //방 예약 가능 여부 Y / N 으로 판별
		 * */
		System.out.print("1박 가격: ");
		int price = scan.nextInt();
		System.out.print("정원: ");
		int max = scan.nextInt();
		System.out.print("예약 가능 여부 (Y / N) : ");
		char use = scan.next().charAt(0);
		use = Character.toUpperCase(use); //무족권 대 문 자 
		
		if(use != 'N' && use != 'Y') {
			use = 'N';
		}
		
		room.setRo_price(price);
		room.setRo_max_person(max);
		room.setRo_use(use);
		return room;
	}

	private RoomVO inputRoom() {
		System.out.print("호수: ");
		int roomNum = scan.nextInt();
		
		System.out.println("방 이름: ");
		scan.nextLine();
		String roomName = scan.nextLine();
		
		return new RoomVO(roomNum, roomName);
	}
}
