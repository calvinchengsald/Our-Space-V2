package server.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import data.dao.PostDao;
import data.dao.UserDao;
import data.model.Post;
import data.model.User;
import util.JSONUtil;

@Controller
@CrossOrigin(origins= "http://localhost:4200")
public class PostController {

	
	
	

	public PostController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private PostDao postDao;

	@Autowired
	private UserDao userDao;
	

	@RequestMapping("/getPost.action")
	public @ResponseBody Post handleGetPost(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("in handle get Post of post Controller");

		JSONObject obj = JSONUtil.getObj(req);
		int postId = (obj.getInt("postId"));
		System.out.println(postId + " is this");
		if (postId == 0) {
			return new Post("invalid post ID");
		}
		Post u = postDao.selectbyId(postId);
		if (u == null) {
			return new Post("This post does not exist");
		}
		return u;
	}


	@RequestMapping("/getPostFromUser.action")
	public @ResponseBody List<Post> handleGetPostFromUser(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("in handle get Post from user of post Controller");

		JSONObject obj = JSONUtil.getObj(req);
		String email = obj.getString("email");
		if (email == null) {
			ArrayList<Post> al = new ArrayList<Post>();
			al.add(new Post("invalid user email"));
			return al;
		}
		List<Post> p = postDao.selectAllByUser(email);
		if (p == null || p.size() == 0) {
			ArrayList<Post> al = new ArrayList<Post>();
			al.add(new Post("there are no posts for this user"));
			return al;
		}

		return p;

	}

	@RequestMapping("/getPostFromAll.action")
	public @ResponseBody List<Post> handleGetPostFromAll(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("in handle get Post from all of post Controller");

	//	System.out.println("User from sesion is: " + req.getSession().getAttribute("user"));
		List<Post> p = postDao.selectAll();
		if (p == null || p.size() == 0) {

			ArrayList<Post> al = new ArrayList<Post>();
			al.add(new Post("There are no post at all"));
			return al;
		}

		return p;
	}
	

	/*
	 * Remove post object from database
	 */
	@RequestMapping("/deletePost.action")
	public @ResponseBody Post handleDeletePost(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("in handle delete Post of post Controller");

		JSONObject obj = JSONUtil.getObj(req);
		int postId = (obj.getInt("postId"));
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return new Post("Please log in");
		}
		Post p = postDao.selectbyId(postId);
		if (p == null) {
			return new Post("This post does not exist");
		}
		postDao.delete(p);

		return new Post("deleted post");


	}

	/*
	 * insert new post object into the database
	 */
	@RequestMapping("/insertPost.action")
	public @ResponseBody Post handleInsertPost(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("in handle insert Post of post Controller");
		HttpSession session = req.getSession(false);
		if(session==null) {
			return new Post("Please log in");
		}
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return new Post("Please log in");
		}

		JSONObject obj = JSONUtil.getObj(req);
		String imgsrc = (obj.has("imgsrc")?obj.getString("imgsrc"):"");
		String youtubelink = (obj.has("youtubelink")?obj.getString("youtubelink"):"");
		String body = (obj.has("body")?obj.getString("body"):"");

		String email = (obj.has("email")?obj.getString("email"):"");
		Timestamp created = new Timestamp(System.currentTimeMillis());
		
		User user1 = userDao.selectById(email);
		
		//User user = userDao.selectById(email);
		
		Post p = new Post(body, imgsrc, youtubelink, user1, created);

		postDao.insert(p);

		return p;
	}
	
	

	/*
	 * Update post object into the database
	 */
	@RequestMapping("/updatePost.action")
	public @ResponseBody Post handleUpdatePost(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("in handle update Post of post Controller");

		JSONObject obj = JSONUtil.getObj(req);
		int postId = (obj.getInt("postId"));
		String body = obj.getString("body");
		String imgsrc = obj.getString("imgsrc");
		String youtubelink = obj.getString("youtubelink");
		if (postId == 0) {
			return new Post("invalid post ID");
		}
		Post u = postDao.selectbyId(postId);
		if (u == null) {
			return new Post("this post does not exist");
		}
		if (body != null)
			u.setBody(body);
		if (imgsrc != null)
			u.setImgSrc(imgsrc);
		if (youtubelink != null)
			u.setYoutubeLink(youtubelink);
		postDao.update(u);

		return u;
	}
	
	@RequestMapping("/updatePostLikes.action")
	public @ResponseBody Post handleUpdatePostLikes(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("in handle update Post likes of post Controller");

		JSONObject obj = JSONUtil.getObj(req);
//		if (!obj.has("likes")) {
//			return new Post("Invalid paramteres");
//		}
//
//		int postId = (obj.has("postId")?obj.getInt("postId"):0);
//		JSONArray likes = obj.getJSONArray("likes");
//		List<User> likesList = new ArrayList<User>();
//		for( int i = 0; i < likes.length(); i++) {
//			JSONObject like = likes.getJSONObject(i);
//			String username = like.getString("email");
//			String password = like.getString("password");
//			String first_name = like.getString("first_name");
//			String last_name = like.getString("last_name");
//			likesList.add( new User(username, password, first_name, last_name));
//		}
		int postId = (obj.has("postId")?obj.getInt("postId"):0);
		String email = (obj.has("email")?obj.getString("email"):"");
		
		if (postId == 0) {
			return new Post("invalid post ID");
		}
		Post u = postDao.selectbyId(postId);
		if (u == null) {
			return new Post("this post does not exist");
		}
		User uu = userDao.selectById(email);
		if (uu == null) {
			return new Post("this user does not exist");
		}
		if(postDao.updateLike(uu, u)) {
			return postDao.selectbyId(postId);
		}

		return new Post("There was an error with the like update");
	}

}
