package main;

import data.dao.CommentDao;
import data.dao.PostDao;
import data.dao.UserDao;
import data.model.Comment;
import data.model.Post;
import data.model.User;
import util.HibernateUtil;

public class Main {
	public static CommentDao commentDao = new CommentDao();
	public static UserDao userDao = new UserDao();
	public static PostDao postDao = new PostDao();

	public static void main(String[] args) {
		System.out.println("hi THIS IS A MAIN METHOD CALL");
		//insertInitialValues();

		System.out.println("First the Comment");
		System.out.println("All the Comment: " + commentDao.selectAll());
		System.out.println("Now the User");
		System.out.println("All the User: " + userDao.selectAll());
		System.out.println("Now the Post");
		System.out.println("All the Post: " + postDao.selectAll());

		System.out.println("All the Post by calvin@mail.com: " + postDao.selectAllByUser("calvin@mail.com"));

		// System.out.println("Search for Kylo Ren: " + chardao.selectByName("'Kylo
		// Ren'"));
		HibernateUtil.closeSes();

	}

	public static void test() {
		//insertInitialValues();

		System.out.println("First the Comment");
		System.out.println("All the Comment: " + commentDao.selectAll());
		System.out.println("Now the User");
		System.out.println("All the User: " + userDao.selectAll());
		System.out.println("Now the Post");
		System.out.println("All the Post: " + postDao.selectAll());

		// System.out.println("Search for Kylo Ren: " + chardao.selectByName("'Kylo
		// Ren'"));
//		HibernateUtil.closeSes();
	}

//
}
