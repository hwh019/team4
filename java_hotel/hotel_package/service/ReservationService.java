package service;

import java.util.Date;
import java.util.List;

import model.vo.ReservationVO;


public interface ReservationService {

	ReservationVO search_reservation(Date startdate, Date enddate, int rv_room_num, String mb_id);

	ReservationVO is_reservated(int rv_room_num, Date startdate, Date enddate);

	boolean insert_reservation(Date startdate, Date enddate, int rv_room_num, String mb_id, Date date,
			int rv_stay_person, long room_price);

	ReservationVO selectroom(Date startdate, Date enddate, int i);

	List<ReservationVO> search_reservation_id(String mb_id);

	ReservationVO search_reservation(int rv_id);

	boolean deletereservation(int rv_id);

	boolean updatereservation(int rv_id, Date startdate, Date enddate, int rv_room_num, String mb_id, Date date,
			int rv_stay_person, int rv_total_price);

	boolean can_checkIn(int rv_id);

	void checkIn(int rv_id);

	boolean can_checkOut(int rv_id);

	void checkOut(int rv_id);

	List<ReservationVO> selectReservationList();

	boolean deleteMember(String mb_id);

}
