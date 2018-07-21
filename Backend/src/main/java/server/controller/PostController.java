package server.controller;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

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
			al.add(new Post("there are no post for this user"));
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

	@RequestMapping("/deletePost.action")
	public @ResponseBody Post handleDeletePost(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("in handle delete Post of post Controller");

		JSONObject obj = JSONUtil.getObj(req);
		int postId = (obj.getInt("postId"));
		HttpSession session = req.getSession();
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

	@RequestMapping("/insertPost.action")
	public @ResponseBody Post handleInsertPost(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("in handle insert Post of post Controller");

		JSONObject obj = JSONUtil.getObj(req);
		String imgsrc = (obj.has("imgsrc")?obj.getString("imgsrc"):"");
		String youtubelink = (obj.has("youtubelink")?obj.getString("youtubelink"):"");
		String body = (obj.has("body")?obj.getString("body"):"");
		String email = (obj.has("email")?obj.getString("email"):"");
		
		User user = userDao.selectById(email);
		
//		System.out.println(user);
		if (user == null) {
			return new Post("Please log in");
		}
		Post p = new Post(body, imgsrc, youtubelink, user);
		// if(body!=null) u.setBody(body);
		// if(imgsrc!=null) u.setImgSrc(imgsrc);
		// if(youtubelink!=null) u.setYoutubeLink(youtubelink);
		postDao.insert(p);

		return p;
	}

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

}
