package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.vo.CustomerVO;
import model.vo.ReservationVO;
import model.vo.RoomVO;
import service.ReservationService;
import service.ReservationServiceImp;


public class ReservationController {

	private ReservationService reservationService = new ReservationServiceImp();
	private Scanner scanner;
	private RoomController roomController = new RoomController(scanner);

	public ReservationController(Scanner scan) {
		this.scanner = scan;
	}

	public boolean makeReservation(CustomerVO loginmember) {
		// 날짜, 인원 선택

		System.out.println("날짜를 정해주세요  (예시 : 2024-01-01)");
		System.out.print("시작 날짜 : ");
		String start_date = scanner.nextLine();
		System.out.print("종료 날짜 : ");
		String end_date = scanner.nextLine();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 입력된 시작 날짜와 종료 날짜를 Date 객체로 변환
			Date startdate = format.parse(start_date);
			Date enddate = format.parse(end_date);
			// 시작 날짜가 종료 날짜보다 같거나 이후이면 잘못된 입력
			if (startdate.compareTo(enddate) >= 0) {
				System.out.println("날짜를 잘못입력하셨습니다.");
				return false;
			}
			System.out.print("인원을 입력해주세요 : ");
			int rv_stay_person = scanner.nextInt();
			scanner.nextLine();
			// 몇일간 예약했는지 계산
			long days = calculate(startdate, enddate);
			Date date = new Date();
			// 날짜, 인원수에 맞는 방 선택
			List<RoomVO> roomlist1 = roomController.selectroom(startdate, enddate, rv_stay_person);
			 // 방 목록이 비어 있으면 방이 없다는 메시지 출력
			if (roomlist1 == null || roomlist1.isEmpty()) {
	            System.out.println("예약 가능한 방이 없습니다.");
	            return false;
	        }
	        int roomlist_size = roomlist1.size();
	        int roomcount = 0;
	        // 예약 가능한 방이 있는지 여부 추적
	        boolean roomAvailable = false;
	        for (RoomVO roomper_person : roomlist1) {
	            // 인원수 맞는 방
	            int i = roomper_person.getRo_num();
	            // 방 하나씩 가능한 날짜가 있는지 체크
	            ReservationVO ress = reservationService.selectroom(startdate, enddate, i);
	            if (ress == null) {
	            	// 예약 가능한 방이 있으면 방 정보 출력
	                roomController.showroom(i);
	                roomAvailable = true;
	            } else {
	                roomcount++;
	            }
	            if (roomcount == roomlist_size && !roomAvailable) {
	                System.out.println("예약 가능한 방이 없습니다.");
	                return false;
	            }
	        }
	        if (!roomAvailable) {
	            System.out.println("예약 가능한 방이 없습니다.");
	            return false;
	        }
			System.out.print("예약하실 방을 선택해주세요 :");
			int rv_room_num = scanner.nextInt();
			scanner.nextLine();
			// 결제
			int room_price = roomController.getroom_price(rv_room_num);
			System.out.println("총 예약 " + days + "일. 금액 : " + room_price * days + "입니다.");
			System.out.print("결제해주세요(Y)  :");
			long total_price = room_price * days;
			char payed = scanner.next().charAt(0);
			if (payed == 'y' || payed == 'Y') {
				if (reservationService.insert_reservation(startdate, enddate, rv_room_num,
						loginmember.getMb_id(), date, rv_stay_person, total_price)) {
					System.out.println("예약이 완료되었습니다.");
					return true;
				}
			}
			System.out.println("결제 취소");
			System.out.println("이전 화면으로 돌아갑니다.");
			return false;
		} catch (ParseException e) {
			System.out.println("날짜를 잘못입력하였습니다.");
			return false;
		}

	} //방 예약 기본기능

	private long calculate(Date startdate, Date enddate) {
		long diffdays = (enddate.getTime() - startdate.getTime()) / 1000;
		diffdays = diffdays / (24 * 60 * 60);
		return diffdays;
	} //일수에 따른 방 금액 계산

	public boolean confirmReservation(CustomerVO loginmember) {
		// true는 예약 있음, false는 예약내역 없음
		List<ReservationVO> res = reservationService.search_reservation_id(loginmember.getMb_id());
		int count = 0;
		if (res != null) {
			for (ReservationVO r : res) {
				System.out.println(r);
				count++;
			}
			if (count == 0) {
				System.out.println("예약 내역이 없습니다.");
				return false;
			}
			System.out.println("넘어가려면 엔터를 눌러주세요.");
			scanner.nextLine();
			return true;
		}
		return false;
	} //예약 확인 기능

	public boolean modityReservation(CustomerVO loginmember) {
		// 수정실패 false; 성공 true;
		if (!confirmReservation(loginmember)) {
			return false;
		}
		// 수정
		System.out.print("수정할 예약번호를 입력해주세요: ");
		int rv_id = scanner.nextInt();
		scanner.nextLine();
		ReservationVO update_res = reservationService.search_reservation(rv_id);
		// 날짜, 인원, 방번호 순
		System.out.println("날짜를 수정해주세요  (예시 : 2024-01-01)");
		System.out.print("시작 날짜 : ");
		String start_date = scanner.nextLine();
		System.out.print("종료 날짜 : ");
		String end_date = scanner.nextLine();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startdate = format.parse(start_date);
			Date enddate = format.parse(end_date);
			if (startdate.compareTo(enddate) >= 0) {
				System.out.println("날짜를 잘못입력하셨습니다.");
				return false;
			}
			System.out.print("인원을 입력해주세요 : ");
			int rv_stay_person = scanner.nextInt();
			scanner.nextLine();
			// 몇일간 예약했는지 계산
			long days = calculate(startdate, enddate);
			Date date = new Date();
			// 시작 날짜, 종료 날짜 , 방 인원이 맞는방 출력(수정하려는 방 제외)
			List<RoomVO> roomlist1 = roomController.selectroom(startdate, enddate, rv_stay_person);
			if (roomlist1 == null) {
				System.out.println("해당하는 방이 없습니다.");
				return false;
			}
			int roomlist_size = roomlist1.size();
			int roomcount = 0;
			for (RoomVO roomper_person : roomlist1) {
				// 인원수 맞는 방
				int i = roomper_person.getRo_num();
				// 방 하나씩 가능한 날짜가 있는지 체크
				ReservationVO ress = reservationService.selectroom(startdate, enddate, i);
				if (ress == update_res) {
					roomcount++;
					continue;
				} else if (ress == null) {
					roomController.showroom(i);
				} else {
					roomcount++;
				}
				if (roomcount == roomlist_size) {
					System.out.println("해당하는 방이 없습니다.");
					return false;
				}
			}
			System.out.print("방을 선택해주세요 :");
			int rv_room_num = scanner.nextInt();
			scanner.nextLine();
			// 가격 비교해서 비싸면 추가결제, 싸면 차액 환불
			int room_price = (roomController.getroom_price(rv_room_num));
			room_price *= days;

			// 비싸면
			if (room_price - update_res.getRv_total_price() > 0) {

				System.out.println(
						"추가 금액 " + (room_price - update_res.getRv_total_price()) + "원 결제하시겠습니까? (Y/N) :");
				char pay = scanner.next().charAt(0);
				if (pay == 'y' || pay == 'Y') {
					if (reservationService.updatereservation(rv_id, startdate, enddate, rv_room_num,
							loginmember.getMb_id(), date, rv_stay_person, room_price)) {
						System.out.println("수정 완료");
						return true;
					}
				}
				System.out.println("결제가 취소되었습니다.");
				System.out.println("이전 화면으로 돌아갑니다.");
				return false;
			} else if (room_price == update_res.getRv_total_price()) {
				if (reservationService.updatereservation(rv_id, startdate, enddate, rv_room_num,
						loginmember.getMb_id(), date, rv_stay_person, room_price)) {
					System.out.println("예약정보가 수정되었습니다.");
					return true;
				}
			} else {
				room_price = update_res.getRv_total_price() - room_price;
				System.out.println("차액 " + room_price + "환불 해드리겠습니다.");
				if (reservationService.updatereservation(rv_id, startdate, enddate, rv_room_num,
						loginmember.getMb_id(), date, rv_stay_person, room_price)) {
					System.out.println("예약정보가 수정되었습니다.");
					return true;
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	} //예약 변경 기능

	public boolean cancelReservation(CustomerVO loginmember) {
		if (!confirmReservation(loginmember)) {
			return false;
		}
		// 삭제
		System.out.print("취소할 예약번호를 입력해주세요: ");
		int rv_id = scanner.nextInt();
		scanner.nextLine();
		
		if (reservationService.deletereservation(rv_id)) {
			System.out.println("예약이 취소되었습니다.");
			return true;
		}
		return false;
	} //예약 취소 기능 (취소시 예약 내역 삭제)

	public void checkInOut(int rv_id, String customerId) {
		ReservationVO res = reservationService.search_reservation(rv_id);
		
		if (!res.getMb_id().equals(customerId)) {
			System.out.println("회원정보가 일치하지 않습니다.");
			return;
		} //end not equals member
		
		if (res.getRv_status().contains("성공")) {
			// 이미 체크인 했으면
			if (res.getMb_id().equals(customerId)) {
				if (reservationService.can_checkOut(rv_id)) {
					reservationService.checkOut(rv_id);
					System.out.println(res.getMb_id() + "님 체크아웃 되었습니다.");
				} else {
					System.out.println("체크아웃에 실패했습니다.");
				}
			} //end 성공 equals 
			return;
		} //end 성공
		if (res.getMb_id().equals(customerId)) {
			// 회원 일치
			if (reservationService.can_checkIn(rv_id)) {
				reservationService.checkIn(rv_id);
				System.out.println(res.getMb_id() + "님 체크인 되었습니다.");
			} else {
				System.out.println("체크인에 실패했습니다.");
			}
		} // end equals start
	} //체크인 기능
	
	public List<ReservationVO> selectReservationList() {
		return reservationService.selectReservationList();
	} //예약 전체 리스트 확인 기능

}