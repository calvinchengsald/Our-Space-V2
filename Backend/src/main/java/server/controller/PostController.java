package server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.model.Error;
import data.model.Post;
import data.model.User;
import data.service.PostService;

public class PostController {

	public static void handleGetPost(HttpServletRequest req, HttpServletResponse res) {

		try {
			System.out.println("in handle get Post of post Controller");
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			Error err = new Error();

			int postId = Integer.parseInt(req.getParameter("postId"));
			System.out.println(postId + " is this");
			if (postId == 0) {
				err.setMessege("invalid post ID");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			Post u = PostService.selectById(postId);
			System.out.println(u);
			if (u == null) {
				System.out.println("This post does not exist");
				err.setMessege("This post does not exist");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}

			out.write(new ObjectMapper().writeValueAsString(u));

			return;

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void handleGetPostFromUser(HttpServletRequest req, HttpServletResponse res) {

		try {
			System.out.println("in handle get Post from user of post Controller");
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			Error err = new Error();

			String email = req.getParameter("email");
			if (email == null) {
				err.setMessege("invalid user email");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			List<Post> p = PostService.selectAllPostFromUser(email);
			if (p == null || p.size() == 0) {
				err.setMessege("There are no posts from this user");
				err.setError(false);
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			out.write(new ObjectMapper().writeValueAsString(p));

			return;

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void handleGetPostFromAll(HttpServletRequest req, HttpServletResponse res) {

		try {
			System.out.println("in handle get Post from all of post Controller");
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			Error err = new Error();

			List<Post> p = PostService.selectAllPostFromAll();
			if (p == null || p.size() == 0) {
				err.setMessege("There are no posts at all");
				err.setError(false);
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			out.write(new ObjectMapper().writeValueAsString(p));

			return;

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void handleDeletePost(HttpServletRequest req, HttpServletResponse res) {
		try {
			System.out.println("in handle delete Post of post Controller");
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			Error err = new Error();

			int postId = Integer.parseInt(req.getParameter("postId"));
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				err.setMessege("please log in");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			Post p = PostService.selectById(postId);
			if (p == null) {
				err.setMessege("this post does not exist");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			PostService.delete(p);
			err.setMessege("deleted post");
			err.setError(false);
			out.write(new ObjectMapper().writeValueAsString(err));

			return;

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void handleInsertPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			System.out.println("in handle insert Post of post Controller");
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			Error err = new Error();

			String body = req.getParameter("body");
			String imgsrc = req.getParameter("imgsrc");
			String youtubelink = req.getParameter("youtubelink");
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				err.setMessege("please log in");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			Post p = new Post(body, imgsrc, youtubelink, user);
			// if(body!=null) u.setBody(body);
			// if(imgsrc!=null) u.setImgSrc(imgsrc);
			// if(youtubelink!=null) u.setYoutubeLink(youtubelink);
			PostService.insert(p);
			out.write(new ObjectMapper().writeValueAsString(p));

			return;

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void handleUpdatePost(HttpServletRequest req, HttpServletResponse res) {

		try {
			System.out.println("in handle update Post of post Controller");
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			Error err = new Error();

			int postId = Integer.parseInt(req.getParameter("postId"));
			String body = req.getParameter("body");
			String imgsrc = req.getParameter("imgsrc");
			String youtubelink = req.getParameter("youtubelink");
			if (postId == 0) {
				err.setMessege("invalid post ID");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			Post u = PostService.selectById(postId);
			if (u == null) {
				System.out.println("This post does not exist");
				err.setMessege("This post does not exist");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			if (body != null)
				u.setBody(body);
			if (imgsrc != null)
				u.setImgSrc(imgsrc);
			if (youtubelink != null)
				u.setYoutubeLink(youtubelink);
			PostService.update(u);
			out.write(new ObjectMapper().writeValueAsString(u));

			return;

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
