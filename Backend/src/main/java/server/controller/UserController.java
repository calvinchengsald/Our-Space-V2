package server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.dao.UserDao;
import data.model.Error;
import data.model.User;
import data.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;

	public UserController() {
	}

	// public static void handleLogin(HttpServletRequest req, HttpServletResponse
	// res, JSONObject obj){
	//
	// try {
	// System.out.println("in handle login of user controller");
	// PrintWriter out = res.getWriter();
	// res.setContentType("application/json");
	// Error err = new Error();
	//
	// if(!obj.has("email") || !obj.has("password")) {
	// System.out.println("Please enter email/password");
	// err.setMessege("Please enter email/password");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	// String username = obj.getString("email");
	// String password = obj.getString("password");
	//
	// User u = UserService.selectById(username);
	// if(u==null) {
	// System.out.println("There was no user found with this email");
	// err.setMessege("There was no user found with this email");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	// if(!u.getEmail().equals(username) || !u.getPassword().equals(password)) {
	// System.out.println("IncorrectPassword");
	// err.setMessege("Incorrect password");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	//
	//
	// Cookie userCookie = new Cookie("username", username);
	// Cookie nameCookie = new Cookie("name", u.getFirstName() + "-" +
	// u.getLastName());
	// res.addCookie(userCookie);
	// res.addCookie(nameCookie);
	// out.write(new ObjectMapper().writeValueAsString(u));
	// req.getSession().setAttribute("user", u);
	//
	// return;
	//
	// } catch (JsonProcessingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	//
	// }

	/*
	 * Return User information if username and password exists
	 */
	@RequestMapping("/login.action")
	public @ResponseBody User handleLogin(HttpServletRequest req, HttpServletResponse res) {
		
		JSONObject obj = getObj(req);
		
		if (!obj.has("email") || !obj.has("password")) {
			System.out.println("Please enter email/password");
			return new User("Please enter email/password");			
		}
		
		 String username = obj.getString("email");
		 String password = obj.getString("password");

		// get user by email
		User user = userDao.selectById(username);

		// invalid username
		if (user == null) {
			System.out.println("Invalid User");
			return new User("There was no user found with this email");
		} /* if (null) */

		// check password
		if (!(user.getPassword().equals(password))) {
			System.out.println("Invalid password");
			return new User("Incorrect Password");
		} /* if (invalid password) */

		// set cookies and session parameters
		Cookie userCookie = new Cookie("username", username);
		Cookie nameCookie = new Cookie("name", user.getFirstName() + "-" + user.getLastName());
		res.addCookie(userCookie);
		res.addCookie(nameCookie);
		req.getSession().setAttribute("user", user);		
		

		return user;
	}/* handleLogin() */

	// public static void handleLogout(HttpServletRequest req, HttpServletResponse
	// res, JSONObject obj) {
	// try {
	// HttpSession session = req.getSession();
	// session.setAttribute("user", null);
	// Cookie[] cookies = req.getCookies();
	// if (cookies != null) {
	// for (Cookie cookie : cookies) {
	// cookie.setValue("");
	// cookie.setPath("/");
	// cookie.setMaxAge(0);
	// res.addCookie(cookie);
	// }
	// }
	// Error err = new Error();
	// err.setError(false);
	// err.setMessege("Successfully Logout");
	// res.getWriter().write(new ObjectMapper().writeValueAsString(err));
	// return;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/*
	 * Log the user out
	 */
	@RequestMapping("/logout.action")
	public @ResponseBody Error handleLogout(HttpServletRequest req, HttpServletResponse res) {

		HttpSession session = req.getSession();
		System.out.println("User: " + session.getAttribute("user"));
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

		return err;
	}/* logout() */

	// public static void handleRegister(HttpServletRequest req, HttpServletResponse
	// res, JSONObject obj) {
	//
	// try {
	// System.out.println("in handle register of user controller");
	// PrintWriter out = res.getWriter();
	// res.setContentType("application/json");
	// Error err = new Error();
	//
	// String username = req.getParameter("username");
	// String password = req.getParameter("password");
	// String first_name = req.getParameter("first_name");
	// String last_name = req.getParameter("last_name");
	// if (username == null || password == null || first_name == null || last_name
	// == null) {
	// System.out.println("Please fill out all fields");
	// err.setMessege("Please fill out all fields");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	// User u = UserService.selectById(username);
	// if (u != null) {
	// System.out.println("An account with this email arleady exist!");
	// err.setMessege("An account with this email arleady exist!");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	// u = new User(username, password, first_name, last_name);
	// if (!UserService.insertUser(u)) {
	// System.out.println("Error inserting user");
	// err.setMessege("There was an error with account registration");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	//
	// Cookie userCookie = new Cookie("username", username);
	// Cookie nameCookie = new Cookie("name", u.getFirstName() + "-" +
	// u.getLastName());
	// res.addCookie(userCookie);
	// res.addCookie(nameCookie);
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

	/*
	 * Register user
	 */
	@RequestMapping("/register.action")
	public @ResponseBody User handleRegister(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("In register Handle");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String first_name = req.getParameter("first_name");
		String last_name = req.getParameter("last_name");

		// validate input
		if (username == null || password == null || first_name == null || last_name == null) {
			System.out.println("Please fill out all fields");
			return new User("Please fill out all fields");
		}

		User u = UserService.selectById(username);
		if (u != null) {
			System.out.println("An account with this email arleady exist!");
			return new User("An account with this email arleady exist!");

		}
		u = new User(username, password, first_name, last_name);
		userDao.insert(u);

		Cookie userCookie = new Cookie("username", username);
		Cookie nameCookie = new Cookie("name", u.getFirstName() + "-" + u.getLastName());
		res.addCookie(userCookie);
		res.addCookie(nameCookie);

		return u;

	}/* handleRegister() */

	// public static void handleGetUser(HttpServletRequest req, HttpServletResponse
	// res, JSONObject obj) {
	//
	// try {
	// System.out.println("in handle get User of user controller");
	// PrintWriter out = res.getWriter();
	// res.setContentType("application/json");
	// Error err = new Error();
	//
	// String username = req.getParameter("username");
	// if (username == null) {
	// System.out.println("email was not found in the field");
	// err.setMessege("email was not found");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	// User u = UserService.selectById(username);
	// if (u == null) {
	// System.out.println("There are no accounts associated with this email");
	// err.setMessege("There are no accounts associated with this email");
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

	/*
	 * Return user specified by the email
	 */
	@RequestMapping("/getUser.action")
	public @ResponseBody User handleGetUser(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("In get user handle");
		String username = req.getParameter("username");
		if (username == null) {
			System.out.println("email was not found in the field");
			return new User("email was not found");
		}
		User u = userDao.selectById(username);
		if (u == null) {
			System.out.println("There are no accounts associated with this email");
			return new User("There are no accounts associated with this email");
		}
		return u;
	}/* handleGetUser() */

	// public static void handleDeleteUser(HttpServletRequest req,
	// HttpServletResponse res, JSONObject obj) {
	// try {
	// System.out.println("in handle delete User of user Controller");
	// PrintWriter out = res.getWriter();
	// res.setContentType("application/json");
	// Error err = new Error();
	//
	// String email = req.getParameter("email");
	// if (email == null) {
	// err.setMessege("please enter valid email");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	// User user = UserService.selectById(email);
	// if (user == null) {
	// err.setMessege("No account exist with this email");
	// out.write(new ObjectMapper().writeValueAsString(err));
	// return;
	// }
	// UserService.delete(user);
	// err.setMessege("deleted user");
	// err.setError(false);
	// out.write(new ObjectMapper().writeValueAsString(err));
	//
	// return;
	//
	// } catch (JsonProcessingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// }

	
	@RequestMapping("/deleteUser.action")
	public @ResponseBody Error handleDeleteUser(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("User Controller: in delete user");
		
		// create new error object
		Error err = new Error();

		// get email of user
		String email = req.getParameter("email");
		
		// verify email
		if (email == null) {
			err.setMessege("please enter valid email");
			return err;
		}
		
		// get user object
		User user = userDao.selectById(email);
		
		// verify that user exist
		if (user == null) {
			err.setMessege("No account exist with this email");
			return err;
		}
		
		userDao.delete(user);
		err.setMessege("deleted user");
		err.setError(false);
		return err;
	}/* handleDeleteUser() */
	
	
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
