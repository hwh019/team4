package controller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.vo.RoomVO;
import service.RoomService;
import service.RoomServiceImp;

public class RoomController {

	private RoomService roomService = new RoomServiceImp();
	private Scanner scanner;

	public RoomController(Scanner scan) {
		this.scanner = scan;
	}

	public List<RoomVO> selectroom(Date startdate, Date enddate, int rv_stay_person) {
		return roomService.selectRoom(startdate, enddate, rv_stay_person);
	}

	public void showroom(int ro_num) {
		RoomVO room = roomService.showroom(ro_num);
		System.out.println(room);
	}

	public int getroom_price(int rv_room_num) {
		RoomVO room = roomService.showroom(rv_room_num);
		return room.getRo_price();
	}

	public void AllRooms() {
		List<RoomVO> roomlist = roomService.showroom_all();
		if (roomlist.size() == 0 || roomlist.get(0) == null) {
			System.out.println("현재 준비된 방이 존재하지 않습니다.");
		} else {
			for (RoomVO room : roomlist) {
				System.out.println(room);
				System.out.println();
			}
		}
	}

	public void insertRoom() {
		RoomVO room = inputRoom();
		RoomVO chkRoom = roomService.showroom(room.getRo_num());
		if(chkRoom != null) {
			System.out.println("이미 등록된 방입니다.");
			return;
		}
		room = inputRoomExpand(room);
		if(roomService.insertRoom(room)) {
			System.out.println( room.getRo_num() + "호 " + room.getRo_name() + " 등록되었습니다.");
		} else {
			System.out.println("이미 등록된 방입니다.");
		}
	} //end insertRoom

	private RoomVO inputRoomExpand(RoomVO room) {

		System.out.println("방 이름: ");
		scanner.nextLine();
		String roomName = scanner.nextLine();
		
		System.out.print("1박 가격: ");
		int price = scanner.nextInt();
		
		System.out.print("정원: ");
		int max = scanner.nextInt();
		
		room.setRo_name(roomName);
		room.setRo_price(price);
		room.setRo_max_person(max);
		
		return room;
	} //end inputRoomExpand
	
	private RoomVO inputRoom() {
		System.out.print("호수: ");
		int roomNum = scanner.nextInt();
		
		return new RoomVO(roomNum);
	} //end inputRoom

	public void updateRoom() {
		RoomVO dbRoom = roomService.showroom(inputRoom().getRo_num());
		
		if(dbRoom == null) {
			System.out.println("존재하지 않는 방입니다.");
			return;
		}
		
		RoomVO newRoom = inputRoomExpand(dbRoom);
		
		newRoom.setRo_id(dbRoom.getRo_id());
		
		if(roomService.updateRoom(dbRoom, newRoom)) {
			System.out.println("방 정보를 수정했습니다.");
			return;
		}

		System.out.println("방 정보를 수정하지 못했습니다.");
	}

	public void deleteRoom() {
		RoomVO dbRoom = roomService.showroom(inputRoom().getRo_num());
		if(dbRoom == null) {
			System.out.println("존재하지 않는 방입니다.");
			return;
		}
		System.out.println("[선택한 방 정보]");
		System.out.println(dbRoom);
		System.out.println("=========================");
		System.out.print("정말로 방을 삭제하시겠습니까? (Y/N): ");
		char del = scanner.next().charAt(0);
		del = Character.toUpperCase(del); //무족권 대 문 자 
		if(del != 'N' && del != 'Y') {
			del = 'N';
		}
		
		if(del == 'N') {
			System.out.println("방을 삭제하지 않았습니다.");
			return;
		} else {
			if(roomService.deleteRoom(dbRoom)) {
				System.out.println(dbRoom.getRo_num() + "호 방이 삭제 되었습니다.");
				return;
			} else {
				System.out.println("방을 삭제하지 못했습니다.");
				return;
			}
		}
		
	}

}
