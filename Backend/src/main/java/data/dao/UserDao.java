package data.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.model.User;

/**
 * 
 * @author Calvin Cheng, Jerry Affricot
 *
 */
@Repository("userDao")
@Transactional
public class UserDao {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private SessionFactory sesFact;

	/*
	 * public constructor
	 */
	public UserDao() {
		super();
	}/* UserDao() */

	/*
	 * insert new User object into the database
	 */
	public void insert(User myUser) {
		sesFact.getCurrentSession().save(myUser);

	}/* insert() */

	/*
	 * Update User object
	 */
	public void update(User myUser) {
		sesFact.getCurrentSession().update(myUser);

	}/* update()*/

	/*
	 * Delete User object from database
	 */
	public void delete(User myUser) {
		// Session ses = HibernateUtil.getSession();
		// Transaction tx = ses.beginTransaction();
		//
		// ses.delete(myUser);
		//
		// tx.commit();
		sesFact.getCurrentSession().delete(myUser);

	}/* delete() */

	/*
	 * Select User object by email
	 */
	public User selectById(String email) {
		// Session ses = HibernateUtil.getSession();
		//
		// User myUser = ses.get(User.class, email);
		// return myUser;

		return sesFact.getCurrentSession().get(User.class, email);
	}/* selectById()*/

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

	/*
	 * Select all user records
	 */
	public List<User> selectAll() {
		// Session ses = HibernateUtil.getSession();

		// List<User> UserList = ses.createQuery("from User").list();

		List<User> UserList = sesFact.getCurrentSession().createQuery("from User", User.class).list();

		return UserList;
	}/* selectAll() */

}
