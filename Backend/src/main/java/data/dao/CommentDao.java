package data.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.Comment;
import util.HibernateUtil;

public class CommentDao {

	public CommentDao() {
		super();
	}

	 public void insert(Comment myComment) {
        Session ses = HibernateUtil.getSession();
        Transaction tx= ses.beginTransaction();
        
        ses.save(myComment);
        
        tx.commit();
        
    }
	 public void update(Comment myComment) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(myComment);

		tx.commit();

	}

	public Comment selectbyId(int id) {
		Session ses = HibernateUtil.getSession();

		Comment myComment = ses.get(Comment.class, id);
		return myComment;
	}

//	public Comment selectByName(String name) {
//		Session ses = HibernateUtil.getSession();
//
//		// HQL
//		// refers to object in java side
//		List<Character> charList = ses.createQuery("from Character" + " where name=" + name, Character.class).list();
//
//		// List<String> genderList = ses.createQuery("select gender from Character" + "
//		// where name="+name, String.class).list();
//		// System.out.println(genderList);
//
//		// Criteria API
//		// name= "Kylo Ren"; //without single quotes
//		// List<Character> charList =
//		// ses.createCriteria(Character.class).add(Restrictions.like("name",
//		// name)).list();
//
//		// Native SQL
//		// refers to actual table name in DB
//		// List<Character> charList = ses.createNativeQuery("select * from Characters
//		// where name="+name, Character.class).list();
//
//		if (charList.get(0) == null) {
//			return null;
//		}
//		// ses.close();
//		return charList.get(0);
//	}

	public List<Comment> selectAll() {
		Session ses = HibernateUtil.getSession();

		List<Comment> commentList = ses.createQuery("from Comment").list();
		// List<Character> charList = ses.createCriteria(Character.class).list();

		// ses.close();
		return commentList;
	}

}


