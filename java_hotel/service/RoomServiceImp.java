package java_hotel.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java_hotel.dao.RoomDAO;
import java_hotel.model.vo.RoomVO;

public class RoomServiceImp implements RoomService{
	  
	private RoomDAO roomDao; 

	public RoomServiceImp() {
		String resource = "java_hotel/config/mybatis-config.xml";
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

}
