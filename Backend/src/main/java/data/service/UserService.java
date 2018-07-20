package data.service;

import data.dao.PostDao;
import data.dao.UserDao;
import data.model.Post;
import data.model.User;

public class UserService {

	
	public static User selectById(String email) {
		
//		return UserDao.selectbyId(email);
		return null;
		
	}
	
	public static void delete(User u) {
//		UserDao.delete(u);
	}
	public static boolean insertUser(User u) {
//		UserDao.insert(u);
		return true;
	}
}
