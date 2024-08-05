package service;

import java.util.Date;
import java.util.List;

import model.vo.RoomVO;


public interface RoomService {

	List<RoomVO> selectRoom(Date startdate, Date enddate, int rv_stay_person);

	RoomVO showroom(int ro_num);

	List<RoomVO> showroom_all();

}
