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
		insertInitialValues();

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
		insertInitialValues();

		System.out.println("First the Comment");
		System.out.println("All the Comment: " + commentDao.selectAll());
		System.out.println("Now the User");
		System.out.println("All the User: " + userDao.selectAll());
		System.out.println("Now the Post");
		System.out.println("All the Post: " + postDao.selectAll());

		// System.out.println("Search for Kylo Ren: " + chardao.selectByName("'Kylo
		// Ren'"));
		HibernateUtil.closeSes();
	}

	public static void insertInitialValues() {
		// users
		User u1 = new User("calvin@mail.com", "calvin", "cheng", "securepass");
		User u2 = new User("jerry@mail.com", "jerry", "affricot", "jerryspringer");
		User u3 = new User("tylor@mail.com", "tylor", "clemons", "tSwift");
		User u4 = new User("mac@mail.com", "mac", "kubena", "macAttack");
		User u5 = new User("kevin@mail.com", "kevin", "huang", "archermaster");

		// post
		Post p1 = new Post("look at my cool bow", "archery.png", "", u5);
		Post p2 = new Post("i love cows", "cow.png", "madcow.utube.com", u1);
		Post p3 = new Post("more cow stuff", "cowsszz.png", "", u1);
		Post p4 = new Post("mac plays mac in smash", "smash.png", "worldtourney.youtube.com", u4);
		Post p5 = new Post("T-SWIZZLE  is my jame", "hotswift.png", "plasticbag.youtube.com", u3);
		//
		// comments
		Comment c1 = new Comment("eww why t swizle", p5, u2);
		Comment c2 = new Comment("COWS ARE THE BEST", p2, u3);

		userDao.insert(u1);
		userDao.insert(u2);
		userDao.insert(u3);
		userDao.insert(u4);
		userDao.insert(u5);

		postDao.insert(p1);
		postDao.insert(p2);
		postDao.insert(p3);
		postDao.insert(p4);
		postDao.insert(p5);

		commentDao.insert(c1);
		commentDao.insert(c2);
		postDao.likePost("calvin@mail.com", 1);
		postDao.likePost("jerry@mail.com", 2);
		postDao.likePost("calvin@mail.com", 1);
		postDao.likePost("calvin@mail.com", 2);
	}
}
