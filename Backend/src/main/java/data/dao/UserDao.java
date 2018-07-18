package data.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.Post;
import data.model.User;
import util.HibernateUtil;

public class UserDao {

	public UserDao() {
		super();
	}

	public static void insert(User myUser) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(myUser);

		tx.commit();

	}

	public static void update(User myUser) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(myUser);

		tx.commit();

	}

	public static void delete(User myUser) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.delete(myUser);

		tx.commit();

	}

	public static User selectbyId(String email) {
		Session ses = HibernateUtil.getSession();

		User myUser = ses.get(User.class, email);
		return myUser;
	}

	// public User selectByName(String name) {
	// Session ses = HibernateUtil.getSession();
	//
	// // HQL
	// // refers to object in java side
	// List<Character> charList = ses.createQuery("from Character" + " where name="
	// + name, Character.class).list();
	//
	// // List<String> genderList = ses.createQuery("select gender from Character" +
	// "
	// // where name="+name, String.class).list();
	// // System.out.println(genderList);
	//
	// // Criteria API
	// // name= "Kylo Ren"; //without single quotes
	// // List<Character> charList =
	// // ses.createCriteria(Character.class).add(Restrictions.like("name",
	// // name)).list();
	//
	// // Native SQL
	// // refers to actual table name in DB
	// // List<Character> charList = ses.createNativeQuery("select * from Characters
	// // where name="+name, Character.class).list();
	//
	// if (charList.get(0) == null) {
	// return null;
	// }
	// // ses.close();
	// return charList.get(0);
	// }

	public static List<User> selectAll() {
		Session ses = HibernateUtil.getSession();

		List<User> UserList = ses.createQuery("from User").list();
		// List<Character> charList = ses.createCriteria(Character.class).list();

		// ses.close();
		return UserList;
	}

}
