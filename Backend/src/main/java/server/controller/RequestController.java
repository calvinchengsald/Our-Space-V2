package server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestController {

	public static void handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String[] paths = req.getRequestURI().split("/");
		String path = paths[paths.length - 1].substring(0, paths[paths.length - 1].indexOf("."));
		// System.out.println("we inside universal post with path: " + path);
		switch (path) {
		case "login":
			UserController.handleLogin(req, res);
			break;
		case "register":
			UserController.handleRegister(req, res);
			break;
		case "logout":
			UserController.handleLogout(req, res);
			break;
		case "getUser":
			UserController.handleGetUser(req, res);
			break;
		case "deleteUser":
			UserController.handleDeleteUser(req, res);
			break;
		case "getPost":
			PostController.handleGetPost(req, res);
			break;
		case "getPostFromUser":
			PostController.handleGetPostFromUser(req, res);
			break;
		case "getPostFromAll" :
			PostController.handleGetPostFromAll(req, res);
			break;
		case "insertPost":
			PostController.handleInsertPost(req, res);
			break;
		case "updatePost":
			PostController.handleUpdatePost(req, res);
			break;
		case "deletePost":
			PostController.handleDeletePost(req, res);
			break;
		case "getComment":
			CommentController.handleGetComment(req, res);
			break;
		case "getCommentFromPost":
			CommentController.handleGetCommentFromPost(req, res);
			break;
		case "getCommentFromAll" :
			CommentController.handleGetCommentFromAll(req, res);
			break;
		case "insertComment":
			CommentController.handleInsertComment(req, res);
			break;
		case "updateComment":
			CommentController.handleUpdateComment(req, res);
			break;
		case "deleteComment":
			CommentController.handleDeleteComment(req, res);
			break;
		
		default:
			handle404();
		}
	}
	public static void handle404(){
		
	}
}
