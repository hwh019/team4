package java_hotel.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import java_hotel.model.vo.RoomVO;

public interface RoomDAO {

	List<RoomVO> selectRoom(@Param("rv_start_date")Date startdate, @Param("rv_end_date")Date enddate, @Param("rv_stay_person")int rv_stay_person);

	RoomVO showRoom(@Param("ro_num")int ro_num);


}
