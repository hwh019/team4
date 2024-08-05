package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.RoomDAO;
import model.vo.RoomVO;


public class RoomServiceImp implements RoomService {

	private RoomDAO roomDao;

	public RoomServiceImp() {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			roomDao = session.getMapper(RoomDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RoomVO> selectRoom(Date startdate, Date enddate, int rv_stay_person) {
		return roomDao.selectRoom(startdate, enddate, rv_stay_person);
	}

	@Override
	public RoomVO showroom(int ro_num) {
		return roomDao.showRoom(ro_num);
	}

	@Override
	public List<RoomVO> showroom_all() {
		return roomDao.showroom_all();
	}
	
	@Override
	public boolean insertRoom(RoomVO room) {
		if(room == null) {
			return false;
		}
		
		RoomVO dbroom = roomDao.showRoom(room.getRo_num());
		if(dbroom != null) {
			return false;
		}

		return roomDao.insertRoom(room);
	} //end boolean insertRoom

	@Override
	public boolean updateRoom(RoomVO room, RoomVO newRoom) {
		if(room == null || newRoom == null) {
			return false;
		}
		room = roomDao.showRoom(room.getRo_num());
		
		RoomVO dbRoom = roomDao.showRoom(newRoom.getRo_num());
		if(dbRoom != null && !room.equals(dbRoom)) {
			return false;
		}
		newRoom.setRo_id(room.getRo_id());
		return roomDao.updateRoom(newRoom);
	} //end boolean updateRoom

	@Override
	public boolean deleteRoom(RoomVO room) {
		if(room == null) {
			return false;
		}
		return roomDao.deleteRoom(room);
	} //end boolean deleteRoom

}
