package hotel.dao;

import org.apache.ibatis.annotations.Param;

import hotel.model.vo.RoomVO;

public interface RoomDAO {

	RoomVO selectRoom();

	RoomVO roomList(@Param("ro")RoomVO room);

	boolean insertRoom(@Param("ro")RoomVO room);

	boolean updateRoom(@Param("new")RoomVO newRoom);

	boolean deleteRoom(@Param("ro")RoomVO room);


}
