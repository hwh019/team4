package dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.vo.RoomVO;


public interface RoomDAO {

	List<RoomVO> selectRoom(@Param("rv_start_date") Date startdate, @Param("rv_end_date") Date enddate,
			@Param("rv_stay_person") int rv_stay_person);

	RoomVO showRoom(@Param("ro_num") int ro_num);

	List<RoomVO> showroom_all();
	
	boolean insertRoom(@Param("ro")RoomVO room);

	boolean updateRoom(@Param("new")RoomVO newRoom);

	boolean deleteRoom(@Param("ro")RoomVO room);
}
