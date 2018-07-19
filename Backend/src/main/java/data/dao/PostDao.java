package data.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.Post;
import data.model.User;
import util.HibernateUtil;

public class PostDao {

	public PostDao() {
		super();
	}

	public static void insert(Post myPost) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(myPost);

		tx.commit();

	}

	public static void delete(Post myPost) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.delete(myPost);

		tx.commit();

	}

	public static void update(Post myPost) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(myPost);

		tx.commit();

	}

	public static Post selectbyId(int id) {
		Session ses = HibernateUtil.getSession();

		Post myPost = ses.get(Post.class, id);
		return myPost;
	}

	// public Post selectByName(String name) {
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

	public static List<Post> selectAll() {
		Session ses = HibernateUtil.getSession();

		List<Post> PostList = ses.createQuery("from Post").list();
		// List<Character> charList = ses.createCriteria(Character.class).list();

		// ses.close();
		return PostList;
	}

	public static List<Post> selectAllByUser(String email) {
		Session ses = HibernateUtil.getSession();

		List<Post> PostList = ses.createQuery("from Post where email='" + email + "'", Post.class).list();
		// List<Character> charList = ses.createCriteria(Character.class).list();

		// ses.close();
		return PostList;
	}

	// true if liked it, false if unliked it
	public static boolean likePost(String email, int postId) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		Post post = ses.get(Post.class, postId);
		User user = ses.get(User.class, email);
		if (post == null) {
			System.out.println("Post is null");
			tx.commit();
			return false;
		}
		if (user == null) {
			System.out.println("User is null");
			tx.commit();
			return false;
		}
		if (!post.getLikes().contains(user)) {
			post.getLikes().add(user);
			tx.commit();
			return true;
			// like it
		} else {
			post.getLikes().remove(user);
			tx.commit();
			return false;
		}

	}

}
