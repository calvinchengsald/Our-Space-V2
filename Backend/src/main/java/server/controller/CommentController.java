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

import data.dao.CommentDao;
import data.dao.PostDao;
import data.dao.UserDao;
import data.model.Comment;
import data.model.Error;
import data.model.Post;
import data.model.User;
import util.JSONUtil;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private PostDao postDao;

	@Autowired
	private UserDao userDao;

	// public static void handleGetComment(HttpServletRequest req,
	// HttpServletResponse res, JSONObject obj) {
	//
	// try {
	// System.out.println("in handle get Comment of Comment Controller");
	// PrintWriter out = res.getWriter();
	// res.setContentType("application/json");
	// Error err = new Error();
	//
	// int CommentId = Integer.parseInt(req.getParameter("commentId"));
	// if (CommentId == 0) {
	// err.setMessege("invalid Comment ID");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	// Comment u = CommentService.selectById(CommentId);
	// System.out.println(u);
	// if (u == null) {
	// System.out.println("This Comment does not exist");
	// err.setMessege("This Comment does not exist");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	//
	// out.write(new ObjectMapper().writeValueAsString(u));
	//
	// return;
	//
	// } catch (JsonProcessingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	// no argument constructor
	public CommentController() {
	}

	/*
	 * Return the comment if it exists
	 */
	@RequestMapping("/getComment.action")
	// public @ResponseBody Comment handleGetComment(@RequestParam("commentId") int
	// CommentId) {
	public @ResponseBody Comment handleGetComment(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("in handle get Comment of Comment Controller");

		JSONObject obj = JSONUtil.getObj(req);
		int CommentId = obj.getInt("commentId");
		if (CommentId == 0) { // if (id == 0)
			return new Comment("Error: Invalid comment id");
		} /* if() */

		Comment u = commentDao.selectById(CommentId);

		// verify that the comment exists
		if (u == null) {
			System.out.println("This Comment does not exist");
			return new Comment("This Comment does not exist");
		} /* if (null) */

		System.out.println(u);

		// return comment
		return u;
	}/* handleGetComment() */

	// public static void handleGetCommentFromPost(HttpServletRequest req,
	// HttpServletResponse res, JSONObject obj) {
	//
	// try {
	// System.out.println("in handle get Comment from user of Comment Controller");
	// PrintWriter out = res.getWriter();
	// res.setContentType("application/json");
	// Error err = new Error();
	//
	// int postId = Integer.parseInt(req.getParameter("postId"));
	// if (postId == 0) {
	// err.setMessege("invalid post id");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	// List<Comment> p = CommentService.selectAllCommentFromPost(postId);
	// if (p == null || p.size() == 0) {
	// err.setMessege("There are no Comments from this post");
	// err.setError(false);
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	// out.write(new ObjectMapper().writeValueAsString(p));
	//
	// return;
	//
	// } catch (JsonProcessingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	@RequestMapping("/getCommentFromPost.action")
	public @ResponseBody List<Comment> handleGetCommentFromPost(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Comment Controller: in get comment from post");

		JSONObject obj = JSONUtil.getObj(req);
		int postId = obj.getInt("postId");
		if (postId == 0) {
			ArrayList<Comment> al = new ArrayList<Comment>();
			al.add(new Comment("invalid id"));
			return al;
		}

		List<Comment> p = commentDao.selectAllByPost(postId);

		if (p == null || p.size() == 0) {
			ArrayList<Comment> al = new ArrayList<Comment>();
			al.add(new Comment("invalid id"));
			return al;
		}

		return p;
	}/* getCommentByPost() */

	/*
	 * Get all comments
	 */
	@RequestMapping("/getCommentFromAll.action")
	public @ResponseBody List<Comment> handleGetCommentFromAll(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Comment Controller: in get comment from all");
		List<Comment> p = commentDao.selectAll();
		if (p == null || p.size() == 0) {
			ArrayList<Comment> al = new ArrayList<Comment>();
			al.add(new Comment("There are no comments at all"));
			return al;
		}

		return p;
	}/* handleGetCommentFromAll() */

	@RequestMapping("/deleteComment.action")
	public @ResponseBody Comment handleDeleteComment(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("in handle delete Comment of Comment Controller");

		JSONObject obj = JSONUtil.getObj(req);
		int CommentId = obj.getInt("commentId");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return new Comment("please log in");
		}
		Comment p = commentDao.selectById(CommentId);
		if (p == null) {
			return new Comment("this comment does not exist");
		}
		commentDao.delete(p);

		return new Comment("deleted Comment");

	}

	@RequestMapping("/insertComment.action")
	public @ResponseBody Comment handleInsertComment(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("in handle insert Comment of Comment Controller");
		Error err = new Error();

		JSONObject obj = JSONUtil.getObj(req);
//		int postId = obj.getInt("postId");
//		String body = obj.getString("body");
//		HttpSession session = req.getSession();
		

		int postId = (obj.has("postId")?obj.getInt("postId"):0);
		String body = (obj.has("body")?obj.getString("body"):"");
		String email = (obj.has("email")?obj.getString("email"):"");

//		User user = (User) session.getAttribute("user");
		User user = userDao.selectById(email);

		// System.out.println(user);
		if (user == null) {
			return new Comment("Please log in");
		}
		
		Post p = postDao.selectbyId(postId);
		if (p == null) {
			return new Comment("This post does not exist");
		}
		Comment comment = new Comment(body, p, user);
		// if(body!=null) u.setBody(body);
		// if(imgsrc!=null) u.setImgSrc(imgsrc);
		// if(youtubelink!=null) u.setYoutubeLink(youtubelink);
		commentDao.insert(comment);
		return comment;

	}

	@RequestMapping("/updateComment.action")
	public @ResponseBody Comment handleUpdateComment(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("in handle update Comment of Comment Controller");

		JSONObject obj = JSONUtil.getObj(req);

		int CommentId = obj.getInt("commentId");
		String body = obj.getString("body");
		if (CommentId == 0) {
			return new Comment("invalid comment ID");
		}
		Comment u = commentDao.selectById(CommentId);
		if (u == null) {
			return new Comment("This comment does not exist");
		}
		if (body != null)
			u.setBody(body);
		commentDao.update(u);

		return u;
	}

}
