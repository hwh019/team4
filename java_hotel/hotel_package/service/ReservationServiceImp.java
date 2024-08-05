package java_hotel.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java_hotel.dao.MemberDAO;
import java_hotel.dao.ReservationDAO;
import java_hotel.model.vo.ReservationVO;

public class ReservationServiceImp implements ReservationService {

	private ReservationDAO reservationDao;

	public ReservationServiceImp() {
		String resource = "java_hotel/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			reservationDao = session.getMapper(ReservationDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ReservationVO search_reservation(Date startdate, Date enddate, int rv_room_num, String mb_id) {
		return reservationDao.selectRes(startdate, enddate, rv_room_num);
	}

	@Override
	public ReservationVO is_reservated(int rv_room_num, Date startdate, Date enddate) {
		return reservationDao.is_reservated(rv_room_num, startdate, enddate);
	}

	@Override
	public ReservationVO selectroom(Date startdate, Date enddate, int rv_room_num) {
		return reservationDao.selectroom(startdate, enddate, rv_room_num);
	}

	@Override // Date startdate, Date enddate, int rv_room_num, String mb_id, Date date, int
			// rv_stay_person, int room_price
	public boolean insert_reservation(Date startdate, Date enddate, int rv_room_num, String mb_id, Date date,
			int rv_stay_person, long rv_total_price) {
		return reservationDao.insert_reservation(startdate, enddate, rv_room_num, mb_id, date, rv_stay_person,
				rv_total_price);
	}

	@Override
	public List<ReservationVO> search_reservation_id(String mb_id) {
		return reservationDao.select_Res(mb_id);
	}

	@Override
	public ReservationVO search_reservation(int rv_id) {
		return reservationDao.select_Res_rv_id(rv_id);
	}

	@Override
	public boolean deletereservation(int rv_id) {
		return reservationDao.delete_reservation(rv_id);
	}

	@Override
	public boolean updatereservation(int rv_id, Date startdate, Date enddate, int rv_room_num, String mb_id,
			Date date, int rv_stay_person, int rv_total_price) {
		return reservationDao.updateerservation(rv_id, startdate, enddate, rv_room_num, mb_id, date, rv_stay_person,
				rv_total_price);
	}

	@Override
	public boolean can_checkIn(int rv_id) {
		ReservationVO res = reservationDao.select_Res_rv_id(rv_id);
		Date date = new Date();
		Date start_date = res.getRv_start_date();
		int dif_date = date.compareTo(start_date);
		if (dif_date == 0) {
			return true;
			// 예약 시작일 이후 날짜
		} else if (dif_date > 0) {
			dif_date = date.compareTo(res.getRv_end_date());
			// 예약 종료일 전 날짜
			if (dif_date < 0) {
				return true;
			}
		}
		System.out.println("체크인 불가");
		return false;
	}

	@Override
	public void checkIn(int rv_id) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		String datecheckin = date + "체크인 성공";
		reservationDao.update_res_status(rv_id, datecheckin);
		return;
	}

	@Override
	public boolean can_checkOut(int rv_id) {
		ReservationVO res = reservationDao.select_Res_rv_id(rv_id);
		Date date = new Date();
		int dif_date = date.compareTo(res.getRv_end_date());
		// 예약 종료일과 같으면 체크아웃 가능
		if (res.getRv_end_date().before(date)) {
			return true;
		}
		return false;
	}

	@Override
	public void checkOut(int rv_id) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		String datecheckin = date + "체크아웃";
		reservationDao.update_res_status(rv_id, datecheckin);
		return;
	}

}
