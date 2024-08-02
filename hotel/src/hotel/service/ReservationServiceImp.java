package hotel.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import hotel.dao.ReservationDAO;

public class ReservationServiceImp implements ReservationService {

	private ReservationDAO reservationDao;
	
	public ReservationServiceImp(){
		String resource = "hotel/config/mybatis-config.xml";
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
	} //end ReservationServiceImp
}
