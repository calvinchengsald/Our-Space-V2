package server.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import data.dao.UserDao;
import data.model.Error;
import data.model.User;
import data.service.UserService;
import util.EmailUtil;
import util.JSONUtil;

@Controller
@CrossOrigin(origins= "http://localhost:4200")
public class UserController {
	@Autowired
	private UserDao userDao;

	public UserController() {
	}

	/*
	 * Return User information if username and password exists
	 */
	@CrossOrigin
	@RequestMapping("/login.action")
	public @ResponseBody User handleLogin(HttpServletRequest req, HttpServletResponse res) {

		JSONObject obj = JSONUtil.getObj(req);

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
<<<<<<< HEAD
<<<<<<< HEAD
		Cookie userCookie = new Cookie("username", username);
		Cookie nameCookie = new Cookie("name", user.getFirstName() + "-" + user.getLastName());
		res.addCookie(userCookie);
		res.addCookie(nameCookie);
		req.getSession().setAttribute("user", user);
=======
//		Cookie userCookie = new Cookie("username", username);
//		Cookie nameCookie = new Cookie("name", user.getFirstName() + "-" + user.getLastName());
//		res.addCookie(userCookie);
//		res.addCookie(nameCookie);
//		System.out.println(user);
		req.getSession().setAttribute("user", user);	
=======

		HttpSession session = req.getSession();
		session.setAttribute("user", user);		

>>>>>>> 775f1e6803518e171db0a778d95dbb732f76f27d
		
		
>>>>>>> df6949f867ed1d54115fd77d6351a376bc8e0716

		return user;
	}/* handleLogin() */

	/*
	 * Log the user out
	 */
	@CrossOrigin
	@RequestMapping("/logout.action")
	public @ResponseBody String handleLogout(HttpServletRequest req, HttpServletResponse res) {

		HttpSession session = req.getSession(false);
		if(session == null) {
			System.out.println("session is null");
			return "{info: 'session is null'}";
		}
		System.out.println("User: " + session.getAttribute("user"));
		session.invalidate();
		return "{info: 'Successfully Logout'}";
	}/* logout() */

	/*
	 * Register user
	 */
	@RequestMapping("/register.action")
	public @ResponseBody User handleRegister(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("In register Handle");

		JSONObject obj = JSONUtil.getObj(req);
		String username = obj.getString("username");
		String password = obj.getString("password");
		String first_name = obj.getString("first_name");
		String last_name = obj.getString("last_name");

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

		String body = "Thank you for registering with our super duper website";

		EmailUtil.sendEmail(username, "Successful registration", body);

		return u;

	}/* handleRegister() */

	/*
	 * Return user specified by the email
	 */
	@RequestMapping("/getUser.action")
	public @ResponseBody User handleGetUser(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("In get user handle");
		JSONObject obj = JSONUtil.getObj(req);
		String username = obj.getString("username");
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

	@RequestMapping("/deleteUser.action")
	public @ResponseBody Error handleDeleteUser(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("User Controller: in delete user");

		// create new error object
		Error err = new Error();

		// get email of user
		JSONObject obj = JSONUtil.getObj(req);
		String email = obj.getString("email");

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

	/*
	 * Reset password of user
	 */
	@RequestMapping("/reset.action")
	public @ResponseBody Error resetPassword(HttpServletRequest req, HttpServletResponse resp) {
		String subject = "OurSpace: Reset your password";
		String body = "Please reset your password";
		// get email of user
		JSONObject obj = JSONUtil.getObj(req);
		String email = obj.getString("email");
		Error err = new Error();

		if (EmailUtil.sendEmail(email, subject, body) == 1) {
			err.setMessege("Email sent succesfully");
			err.setError(false);
		} else {
			err.setMessege("Email not sent");
		}

		return err;
	}/* resetPassword()*/

}
