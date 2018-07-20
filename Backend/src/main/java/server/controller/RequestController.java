package server.controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class RequestController {

	public static void handleRequest(HttpServletRequest req, HttpServletResponse res) {
		
		JSONObject obj = getObj(req);
		String[] paths = req.getRequestURI().split("/"); 
		String path = paths[paths.length - 1].substring(0, paths[paths.length - 1].indexOf("."));
		// System.out.println("we inside universal post with path: " + path);
		switch (path) {
		case "login":
//			UserController.handleLogin(req, res, obj);
			break;
		case "register":
//			UserController.handleRegister(req, res,obj );
			break;
		case "logout":
//			UserController.handleLogout(req, res, obj);
			break;
		case "getUser":
//			UserController.handleGetUser(req, res, obj);
			break;
		case "deleteUser":
//			UserController.handleDeleteUser(req, res, obj);
			break;
		case "getPost":
			PostController.handleGetPost(req, res, obj);
			break;
		case "getPostFromUser":
			PostController.handleGetPostFromUser(req, res, obj);
			break;
		case "getPostFromAll" :
			PostController.handleGetPostFromAll(req, res, obj);
			break;
		case "insertPost":
			PostController.handleInsertPost(req, res, obj);
			break;
		case "updatePost":
			PostController.handleUpdatePost(req, res, obj);
			break;
		case "deletePost":
			PostController.handleDeletePost(req, res, obj);
			break;
		case "getComment":
//			CommentController.handleGetComment(req, res, obj);
			break;
		case "getCommentFromPost":
//			CommentController.handleGetCommentFromPost(req, res, obj);
			break;
		case "getCommentFromAll" :
//			CommentController.handleGetCommentFromAll(req, res, obj);
			break;
		case "insertComment":
//			CommentController.handleInsertComment(req, res, obj);
			break;
		case "updateComment":
			CommentController.handleUpdateComment(req, res, obj);
			break;
		case "deleteComment":
			CommentController.handleDeleteComment(req, res, obj);
			break;
		
		default:
			handle404();
		}
	}
	public static void handle404(){
		
	}
	
	public static JSONObject getObj(HttpServletRequest req) {

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
		    BufferedReader reader = req.getReader();
		    while ((line = reader.readLine()) != null)
		    jb.append(line);
		} catch (Exception e) { e.printStackTrace(); }
		 	
		JSONObject obj = null;
		try {
			obj = new JSONObject(jb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
