package server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.dao.CommentDao;
import data.model.Comment;
import data.model.Error;
import data.model.Post;
import data.model.User;
import data.service.CommentService;
import data.service.PostService;

public class CommentController {
	@Autowired
	private CommentDao commentDao;

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
	public @ResponseBody Comment handleGetComment(@RequestParam("commentId") int CommentId) {

		System.out.println("in handle get Comment of Comment Controller");		

		if (CommentId == 0) { // if (id == 0)
			return new Comment("Error: Invalid comment id");
		} /* if() */

		Comment u = commentDao.selectById(CommentId);
		System.out.println(u);

		// verify that the comment exists
		if (u == null) {
			System.out.println("This Comment does not exist");
			return new Comment("This Comment does not exist");
		} /* if (null) */

		// return comment
		return u;
	}/* handleGetComment() */

	
	
	public static void handleGetCommentFromPost(HttpServletRequest req, HttpServletResponse res, JSONObject obj) {

//		try {
//			System.out.println("in handle get Comment from user of Comment Controller");
//			PrintWriter out = res.getWriter();
//			res.setContentType("application/json");
//			Error err = new Error();
//
//			int postId = Integer.parseInt(req.getParameter("postId"));
//			if (postId == 0) {
//				err.setMessege("invalid post id");
//				out.write(new ObjectMapper().writeValueAsString(err));
//				return;
//			}
//			List<Comment> p = CommentService.selectAllCommentFromPost(postId);
//			if (p == null || p.size() == 0) {
//				err.setMessege("There are no Comments from this post");
//				err.setError(false);
//				out.write(new ObjectMapper().writeValueAsString(err));
//				return;
//			}
//			out.write(new ObjectMapper().writeValueAsString(p));
//
//			return;
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public static void handleGetCommentFromAll(HttpServletRequest req, HttpServletResponse res, JSONObject obj) {

//		try {
//			System.out.println("in handle get Comment from all of Comment Controller");
//			PrintWriter out = res.getWriter();
//			res.setContentType("application/json");
//			Error err = new Error();
//
//			List<Comment> p = CommentService.selectAllCommentFromAll();
//			if (p == null || p.size() == 0) {
//				err.setMessege("There are no Comments at all");
//				err.setError(false);
//				out.write(new ObjectMapper().writeValueAsString(err));
//				return;
//			}
//			out.write(new ObjectMapper().writeValueAsString(p));
//
//			return;
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public static void handleDeleteComment(HttpServletRequest req, HttpServletResponse res, JSONObject obj) {
//		try {
//			System.out.println("in handle delete Comment of Comment Controller");
//			PrintWriter out = res.getWriter();
//			res.setContentType("application/json");
//			Error err = new Error();
//
//			int CommentId = Integer.parseInt(req.getParameter("commentId"));
//			HttpSession session = req.getSession();
//			User user = (User) session.getAttribute("user");
//			if (user == null) {
//				err.setMessege("please log in");
//				out.write(new ObjectMapper().writeValueAsString(err));
//				return;
//			}
//			Comment p = CommentService.selectById(CommentId);
//			if (p == null) {
//				err.setMessege("this Comment does not exist");
//				out.write(new ObjectMapper().writeValueAsString(err));
//				return;
//			}
//			CommentService.delete(p);
//			err.setMessege("deleted Comment");
//			err.setError(false);
//			out.write(new ObjectMapper().writeValueAsString(err));
//
//			return;
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	public static void handleInsertComment(HttpServletRequest req, HttpServletResponse res, JSONObject obj) {
//		try {
//			System.out.println("in handle insert Comment of Comment Controller");
//			PrintWriter out = res.getWriter();
//			res.setContentType("application/json");
//			Error err = new Error();
//
//			System.out.println("read post id id is " + req.getParameter("postId"));
//			String body = req.getParameter("body");
//
//			int postId = Integer.parseInt(req.getParameter("postId"));
//			HttpSession session = req.getSession();
//
//			User user = (User) session.getAttribute("user");
//			if (user == null) {
//				err.setMessege("please log in");
//				out.write(new ObjectMapper().writeValueAsString(err));
//				return;
//			}
//			Post p = PostService.selectById(postId);
//			if (p == null) {
//				err.setMessege("This post does not exist");
//				out.write(new ObjectMapper().writeValueAsString(err));
//				return;
//			}
//			Comment comment = new Comment(body, p, user);
//			// if(body!=null) u.setBody(body);
//			// if(imgsrc!=null) u.setImgSrc(imgsrc);
//			// if(youtubelink!=null) u.setYoutubeLink(youtubelink);
//			CommentService.insert(comment);
//			out.write(new ObjectMapper().writeValueAsString(p));
//
//			return;
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public static void handleUpdateComment(HttpServletRequest req, HttpServletResponse res, JSONObject obj) {

//		try {
//			System.out.println("in handle update Comment of Comment Controller");
//			PrintWriter out = res.getWriter();
//			res.setContentType("application/json");
//			Error err = new Error();
//
//			int CommentId = Integer.parseInt(req.getParameter("commentId"));
//			String body = req.getParameter("body");
//			if (CommentId == 0) {
//				err.setMessege("invalid Comment ID");
//				out.write(new ObjectMapper().writeValueAsString(err));
//				return;
//			}
//			Comment u = CommentService.selectById(CommentId);
//			if (u == null) {
//				System.out.println("This Comment does not exist");
//				err.setMessege("This Comment does not exist");
//				out.write(new ObjectMapper().writeValueAsString(err));
//				return;
//			}
//			if (body != null)
//				u.setBody(body);
//			CommentService.update(u);
//			out.write(new ObjectMapper().writeValueAsString(u));
//
//			return;
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
