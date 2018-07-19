package data.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.model.Comment;
import util.HibernateUtil;

@Repository("commentDao")
@Transactional
public class CommentDao {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private SessionFactory sesFact;

	// public no-arg constructor
	public CommentDao() {
	}

	
	// inserts new entry into the database
	public void insert(Comment myComment) {
		// Session ses = HibernateUtil.getSession();
		// Transaction tx = ses.beginTransaction();
		//
		// ses.save(myComment);
		//
		// tx.commit();

		sesFact.getCurrentSession().save(myComment);

	}

	
	// update current value in database
	public void update(Comment myComment) {
		// Session ses = HibernateUtil.getSession();
		// Transaction tx = ses.beginTransaction();
		//
		// ses.update(myComment);
		//
		// tx.commit();
		sesFact.getCurrentSession().update(myComment);

	}

	public void delete(Comment myComment) {
		// Session ses = HibernateUtil.getSession();
		// Transaction tx = ses.beginTransaction();
		//
		// ses.delete(myComment);
		//
		// tx.commit();
		sesFact.getCurrentSession().delete(myComment);

	}

	public Comment selectById(int id) {
		// Session ses = HibernateUtil.getSession();
		//
		// Comment myComment = ses.get(Comment.class, id);
		// return myComment;

		return sesFact.getCurrentSession().get(Comment.class, id);
	}

	public List<Comment> selectAllByPost(int postId) {
		// Session ses = HibernateUtil.getSession();

		// List<Comment> commentList = ses.createQuery("from Comment where postId='" +
		// postId + "'", Comment.class).list();

		List<Comment> commentList = sesFact.getCurrentSession()
				.createQuery("from Comment where postId='" + postId + "'", Comment.class).list();

		return commentList;
	}

	// public Comment selectByName(String name) {
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

	
	// get all available comments
	public List<Comment> selectAll() {
		//Session ses = HibernateUtil.getSession();

		//List<Comment> commentList = ses.createQuery("from Comment").list();
		// List<Character> charList = ses.createCriteria(Character.class).list();

		// ses.close();
		
		List<Comment> commentList = sesFact.getCurrentSession().createQuery("from Comment").list();
		
		return commentList;
	}

}
