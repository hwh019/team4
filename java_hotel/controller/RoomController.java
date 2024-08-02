package java_hotel.controller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import java_hotel.model.vo.RoomVO;
import java_hotel.service.RoomService;
import java_hotel.service.RoomServiceImp;


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






}
