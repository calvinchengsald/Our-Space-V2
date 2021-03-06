package data.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.model.Post;
import data.model.User;
import util.ComparisonUtil;
import util.HibernateUtil;

@Repository("postDao")
@Transactional
public class PostDao {

	
	@Autowired
	private SessionFactory sesFact;

	
	
	public PostDao() {
		super();
	}

	
	public void insert(Post myPost) {
//		Session ses = HibernateUtil.getSession();
//		Transaction tx = ses.beginTransaction();
//
//		ses.save(myPost);
//
//		tx.commit();

		myPost.setCreated(new Timestamp(System.currentTimeMillis()));
		sesFact.getCurrentSession().save(myPost);

	}

	public  void delete(Post myPost) {
//		Session ses = HibernateUtil.getSession();
//		Transaction tx = ses.beginTransaction();
//
//		ses.delete(myPost);
//
//		tx.commit();


		sesFact.getCurrentSession().delete(myPost);
	}

	public  void update(Post myPost) {
//		Session ses = HibernateUtil.getSession();
//		Transaction tx = ses.beginTransaction();
//
//		ses.update(myPost);
//
//		tx.commit();


		sesFact.getCurrentSession().update(myPost);
	}

	public Post selectbyId(int id) {
//		Session ses = HibernateUtil.getSession();
//
//		Post myPost = ses.get(Post.class, id);
//		return myPost;
		
		Post u = sesFact.getCurrentSession().get(Post.class, id);
		
//		System.out.println("Inside select by ID post : " + u.getBody());
//		System.out.println(u.getComments());
		return u;
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

	public  List<Post> selectAll() {
//		Session ses = HibernateUtil.getSession();
//
//		List<Post> PostList = ses.createQuery("from Post").list();
//		// List<Character> charList = ses.createCriteria(Character.class).list();
//
//		// ses.close();
//		return PostList;
		List<Post> pList = sesFact.getCurrentSession().createQuery("from Post order by created desc", Post.class).list();

		for(int i = 0; i < pList.size(); i++) {
			System.out.println("Comments in post with body: " + pList.get(i).getBody());
//			pList.get(i).orderComments();
			System.out.println( pList.get(i).getComments());
		}

		return pList;
	}

	public List<Post> selectAllByUser(String email) {
//		Session ses = HibernateUtil.getSession();
//
//		List<Post> PostList = ses.createQuery("from Post where email='" + email + "'", Post.class).list();
		// List<Character> charList = ses.createCriteria(Character.class).list();

		// ses.close();
//		return PostList;
		List<Post> pList = sesFact.getCurrentSession().createQuery("from Post where email='"+email+"' order by created desc", Post.class).list();

		return pList;
	}
	
//	public boolean updateLike(int postId, String userId) {
//		
//		System.out.println("Read in userID : " + userId);
//		User u = sesFact.getCurrentSession().get(User.class, userId);
//		Post p = sesFact.getCurrentSession().get(Post.class, postId);
//		System.out.println(u.getLikedPosts());
////		System.out.println("---");
//		System.out.println(p.getLikedUsers());
////		System.out.println("---");
////		boolean hasPost = ComparisonUtil.userHasPostLike(p,	 u);
////		boolean hasUser = ComparisonUtil.postHasUserLike(p,	 u);
//		boolean hasPost = u.getLikedPosts().contains(p);
//		boolean hasUser = p.getLikedUsers().contains(u);
//		System.out.println("User has liked post: " + u.getLikedPosts().contains(p));
//		System.out.println("Post has liked user: " + p.getLikedUsers().contains(u));
//		System.out.println(hasPost + " && " + hasUser);
//		if(hasPost && hasUser) {
//			
////			ComparisonUtil.removeUser(p,u);
////			ComparisonUtil.removePost(p,u);
//			u.getLikedPosts().remove(p);
//			p.getLikedUsers().remove(u);
//			System.out.println("removed");
//		}
//		else if (!hasPost && !hasUser) {
//
//			u.getLikedPosts().add(p);
//			p.getLikedUsers().add(u);
//			System.out.println("added");
//		}
//		else {
//			System.out.println("There was a mismatch with the table data");
//			return false;
//		}
//			
//			Session s = sesFact.getCurrentSession();
//		System.out.println("Before" + u.getLikedPosts().size());
////		s.merge(u);
//		System.out.println("After: " + u.getLikedPosts().size());
////		s.update(p);
//		return true;
//		
//	}

	// true if liked it, false if unliked it
//	public static boolean likePost(String email, int postId) {
//		Session ses = HibernateUtil.getSession();
//		Transaction tx = ses.beginTransaction();
//		Post post = ses.get(Post.class, postId);
//		User user = ses.get(User.class, email);
//		if (post == null) {
//			System.out.println("Post is null");
//			tx.commit();
//			return false;
//		}
//		if (user == null) {
//			System.out.println("User is null");
//			tx.commit();
//			return false;
//		}
//		if (!post.getLikedUsers().contains(user)) {
//			post.getLikedUsers().add(user);
//			tx.commit();
//			return true;
//			// like it
//		} else {
//			post.getLikedUsers().remove(user);
//			tx.commit();
//			return false;
//		}
//
//	}

}
