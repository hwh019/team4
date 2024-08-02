package hotel.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import hotel.dao.RoomDAO;
import hotel.model.vo.RoomVO;

public class RoomServiceImp implements RoomService {
	
	private RoomDAO roomDao;
	
	public RoomServiceImp(){
		String resource = "hotel/config/mybatis-config.xml";
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
	} //end RoomServiceImp
	
	public boolean insertRoom(RoomVO room) {
		if(room == null) {
			return false;
		}
		
		RoomVO dbroom = roomDao.roomList(room);
		if(dbroom != null) {
			return false;
		}

		return roomDao.insertRoom(room);
	} //end boolean insertRoom
	
	public boolean contains(RoomVO room) {
		if(room == null) {
			return false;
		}
		RoomVO dbroom = roomDao.roomList(room);
		return dbroom !=  null;
	} //end boolean contains
	
	public boolean updateRoom(RoomVO room, RoomVO newRoom) {
		if(room == null || newRoom == null) {
			return false;
		}
		room = roomDao.roomList(room);
		
		RoomVO dbRoom = roomDao.roomList(newRoom);
		if(dbRoom != null && !room.equals(dbRoom)) {
			return false;
		}
		newRoom.setRo_id(room.getRo_id());
		return roomDao.updateRoom(newRoom);
	} //end boolean updateRoom
	
	public boolean deleteRoom(RoomVO room) {
		if(room == null) {
			return false;
		}
		return roomDao.deleteRoom(room);
	} //end boolean deleteRoom
	
	public RoomVO selectRoom() {
		return roomDao.selectRoom();
	} //end RoomVO selectRoom
	
	public RoomVO roomList(RoomVO room) {
		return roomDao.roomList(room);
	}
}
