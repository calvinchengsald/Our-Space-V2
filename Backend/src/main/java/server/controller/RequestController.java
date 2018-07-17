package server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestController {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		String[] paths = req.getRequestURI().split("/");
		String path = paths[paths.length - 1].substring(0, paths[paths.length - 1].indexOf("."));
		// System.out.println("we inside universal post with path: " + path);
		switch (path) {
		case "register":
			UserController.handleLogin(req, res);
			//UserService.handleRegister(req, res, obj);
			break;
		
		default:
			handle404();
		}
	}
	public void handle404(){
		
	}
}
