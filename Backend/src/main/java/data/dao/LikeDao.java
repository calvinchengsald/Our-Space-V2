package data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.model.Like;

@Repository("likeDao")
@Transactional
public class LikeDao {

	
	@Autowired
	private SessionFactory sesFact;

	
	
	public LikeDao() {
		super();
	}
	
//	public int getLikeCountByPostId(int postId) {
//		
//		Session ses = sesFact.getCurrentSession();
//		ses.get(Post.class, postId);
//		ses.get(U, id)
//		List<Like> likeList = sesFact.getCurrentSession().createQuery("from Comment ", Like.class).list();
//		for()
//		
//	}
	
	public List<Like> getLikeByPostId(int postId) {
		List<Like> likeList = sesFact.getCurrentSession().createQuery("from Like ", Like.class).list();
		List<Like> newList = new ArrayList<Like>();
		for( int i =0; i<likeList.size(); i++) {
			if(likeList.get(i).getId().getPostId() == postId) {
				newList.add(likeList.get(i));
			}
		}
		return newList;
	}
	
	public boolean insertLike(String email, int postId) {
		List<Like> list = sesFact.getCurrentSession().createQuery("from Like ", Like.class).list();
		boolean removed = false;
		for( int i =0; i<list.size(); i++) {
			if(list.get(i).getId().getPostId() == postId && list.get(i).getId().getEmail().equals(email)) {
				sesFact.getCurrentSession().delete(list.get(i));
				list.remove(i);
				removed = true;
				return false;
			}
		}
		
		if(!removed) {
			Like l = new Like(email, postId);
			sesFact.getCurrentSession().save(l);
			list.add(l);
			return true;
		}
		
		
		return false;
	}
	
	
	public boolean hasLike(List<Like> list, int postId, String email) {
		for( int i =0; i<list.size(); i++) {
			if(list.get(i).getId().getPostId() == postId && list.get(i).getId().getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	


	public List<Like> selectAllByPost(int postId) {
		// Session ses = HibernateUtil.getSession();

		// List<Comment> commentList = ses.createQuery("from Comment where postId='" +
		// postId + "'", Comment.class).list();

		List<Like> likeList = sesFact.getCurrentSession()
				.createQuery("from Like ", Like.class).list();

		return likeList;
	}
	
	public List<Like> selectAll() {
		// Session ses = HibernateUtil.getSession();

		// List<Comment> commentList = ses.createQuery("from Comment where postId='" +
		// postId + "'", Comment.class).list();

		List<Like> likeList = sesFact.getCurrentSession()
				.createQuery("from Like ", Like.class).list();

		return likeList;
	}
	
	
}
