package java_hotel.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import java_hotel.model.vo.ReservationVO;

public interface ReservationDAO {

	ReservationVO selectRes(@Param("rv_start_date")Date rv_start_date, @Param("rv_end_date")Date rv_end_date, @Param("rv_room_num")int rv_room_num);

	ReservationVO is_reservated(@Param("rv_room_num")int rv_room_num, @Param("rv_start_date")Date startdate, @Param("rv_end_date")Date enddate);

	boolean insert_reservation(@Param("rv_start_date")Date startdate, @Param("rv_end_date")Date enddate, @Param("rv_room_num")int rv_room_num, @Param("mb_id")String mb_id, @Param("rv_date")Date date, @Param("rv_stay_person")int rv_stay_person, @Param("rv_total_price")int rv_total_price);

	ReservationVO selectroom(@Param("rv_start_date")Date startdate, @Param("rv_end_date")Date enddate, @Param("rv_room_num")int rv_room_num);

	List<ReservationVO> select_Res(@Param("mb_id")String mb_id);

	ReservationVO select_Res_rv_id(@Param("rv_id")int rv_id);

	boolean delete_reservation(@Param("rv_id")int rv_id);

	boolean updateerservation(@Param("rv_id")int rv_id, @Param("rv_start_date")Date startdate, @Param("rv_end_date")Date enddate, @Param("rv_room_num")int rv_room_num, @Param("mb_id")String mb_id, @Param("rv_date")Date date,
			@Param("rv_stay_person")int rv_stay_person, @Param("rv_total_price")int rv_total_price);

}
