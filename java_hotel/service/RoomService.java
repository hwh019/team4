package java_hotel.service;

import java.util.Date;
import java.util.List;

import java_hotel.model.vo.RoomVO;

public interface RoomService {

	List<RoomVO> selectRoom(Date startdate, Date enddate, int rv_stay_person);

	RoomVO showroom(int ro_num);


}
