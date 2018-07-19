package server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.model.Error;
import data.model.User;
import data.service.UserService;

public class UserController {

	
	public static void handleLogin(HttpServletRequest req, HttpServletResponse res, JSONObject obj){
		
		try {
			System.out.println("in handle login of user controller");
			PrintWriter out = res.getWriter();			
			res.setContentType("application/json");
			Error err = new Error();
			
			if(!obj.has("email") || !obj.has("password")) {
				System.out.println("Please enter email/password");
				err.setMessege("Please enter email/password");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			String username = obj.getString("email");
			String password = obj.getString("password");
			
			User u = UserService.selectById(username);
			if(u==null) {
				System.out.println("There was no user found with this email");
				err.setMessege("There was no user found with this email");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			if(!u.getEmail().equals(username) || !u.getPassword().equals(password)) {
				System.out.println("IncorrectPassword");
				err.setMessege("Incorrect password");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			
	
			Cookie userCookie = new Cookie("username", username);
			Cookie nameCookie = new Cookie("name", u.getFirstName() + "-" + u.getLastName());
			res.addCookie(userCookie);
			res.addCookie(nameCookie);
			out.write(new ObjectMapper().writeValueAsString(u));
			req.getSession().setAttribute("user", u);
			
			return;
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void handleLogout(HttpServletRequest req, HttpServletResponse res, JSONObject obj) {
		try {
			HttpSession session = req.getSession();
			session.setAttribute("user", null);
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            cookie.setValue("");
		            cookie.setPath("/");
		            cookie.setMaxAge(0);
		            res.addCookie(cookie);
		        }
			}
			Error err = new Error();
			err.setError(false);
			err.setMessege("Successfully Logout");
			res.getWriter().write(new ObjectMapper().writeValueAsString(err));
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void handleRegister(HttpServletRequest req, HttpServletResponse res, JSONObject obj){
		
		try {
			System.out.println("in handle register of user controller");
			PrintWriter out = res.getWriter();			
			res.setContentType("application/json");
			Error err = new Error();
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String first_name = req.getParameter("first_name");
			String last_name = req.getParameter("last_name");
			if(username==null || password==null || first_name==null || last_name==null ) {
				System.out.println("Please fill out all fields");
				err.setMessege("Please fill out all fields");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			User u = UserService.selectById(username);
			if(u!=null) {
				System.out.println("An account with this email arleady exist!");
				err.setMessege("An account with this email arleady exist!");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			u = new User(username, password, first_name, last_name);
			if(!UserService.insertUser(u)) {
				System.out.println("Error inserting user");
				err.setMessege("There was an error with account registration");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
	
			Cookie userCookie = new Cookie("username", username);
			Cookie nameCookie = new Cookie("name", u.getFirstName() + "-" + u.getLastName());
			res.addCookie(userCookie);
			res.addCookie(nameCookie);
			out.write(new ObjectMapper().writeValueAsString(u));
			
			return;
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void handleGetUser(HttpServletRequest req, HttpServletResponse res, JSONObject obj){
		
		try {
			System.out.println("in handle get User of user controller");
			PrintWriter out = res.getWriter();			
			res.setContentType("application/json");
			Error err = new Error();
			
			String username = req.getParameter("username");
			if(username==null ) {
				System.out.println("email was not found in the field");
				err.setMessege("email was not found");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			User u = UserService.selectById(username);
			if(u==null) {
				System.out.println("There are no accounts associated with this email");
				err.setMessege("There are no accounts associated with this email");
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
	public static void handleDeleteUser(HttpServletRequest req, HttpServletResponse res, JSONObject obj) {
		try {
			System.out.println("in handle delete User of user Controller");
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			Error err = new Error();

			String email = req.getParameter("email");
			if(email==null) {
				err.setMessege("please enter valid email");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			User user = UserService.selectById(email);
			if (user == null) {
				err.setMessege("No account exist with this email");
				out.write(new ObjectMapper().writeValueAsString(err));
				return;
			}
			UserService.delete(user);
			err.setMessege("deleted user");
			err.setError(false);
			out.write(new ObjectMapper().writeValueAsString(err));

			return;

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
