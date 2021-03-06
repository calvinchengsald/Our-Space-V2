package server.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import data.dao.ActivationDao;
import data.dao.UserDao;
import data.model.Activation;
import data.model.Error;
import data.model.Post;
import data.model.User;
import data.service.UserService;
import util.EmailUtil;
import util.HashedPassword;
import util.JSONUtil;

@Controller
@CrossOrigin(origins= "http://localhost:4200", allowCredentials="true")
public class UserController {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ActivationDao activationDao;

	public UserController() {
	}

	/*
	 * Return User information if username and password exists
	 */
	
	@CrossOrigin
	@RequestMapping("/checkLogin.action")
	public @ResponseBody User handleCheckLogin(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(false);

		if(session == null || session.getAttribute("user") == null) {
			System.out.println("checkin null");

			return null;
		}else {
			return (User) session.getAttribute("user");
		}
		
	}
	
	@RequestMapping("/getUserFromAll.action")
	public @ResponseBody List<User> handleGetUserFromAll(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("in handle get User from all of user Controller");

	//	System.out.println("User from sesion is: " + req.getSession().getAttribute("user"));
		List<User> p = userDao.selectAll();
		if (p == null || p.size() == 0) {

			ArrayList<User> al = new ArrayList<User>();
			al.add(new User("There are no user at all"));
			return al;
		}

		return p;
	}
	
	
	@CrossOrigin
	@RequestMapping("/login.action")
	public @ResponseBody User Login(HttpServletRequest req, HttpServletResponse res) {

		JSONObject obj = JSONUtil.getObj(req);

		if (!obj.has("email") || !obj.has("password")) {
			System.out.println("Please enter email/password");
			return new User("Please enter email/password");
		}

		String username = obj.getString("email");
		String password = obj.getString("password");
		try {
			password = HashedPassword.getHash(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

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
		if(!user.isActivated()) {
			System.out.println("not activated");
			return new User("Not activated, check you email");
		}
		
		

		HttpSession session = req.getSession();
		session.setAttribute("user", user);		


		return user;
	}/* handleLogin() */

	/*
	 * Log the user out
	 */
	@CrossOrigin
	@RequestMapping("/logout.action")
	public @ResponseBody User handleLogout(HttpServletRequest req, HttpServletResponse res) {

		HttpSession session = req.getSession(false);
		if(session == null) {
			System.out.println("session is null");
			return new User("no");
		}
		session.invalidate();
		return new User("yes");
	}/* logout() */

	/*
	 * Register user
	 */
	@CrossOrigin
	@RequestMapping("/register.action")
	public @ResponseBody User Register(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("In register Handle");

		JSONObject obj = JSONUtil.getObj(req);
		String username = obj.getString("username");
		String password = obj.getString("password");
		try {
			password = HashedPassword.getHash(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		u = new User(username, first_name, last_name, password, false);
		userDao.insert(u);

		String activationKey = UUID.randomUUID().toString().replace("-", "");
		activationDao.insert(new Activation(activationKey, username));
		String body = "Thank you for registering with our super duper website. Please click on the link below to activate \n" +
				"http://localhost:4200/OurSpace/activation?activationKey="+activationKey;

		EmailUtil.sendEmail(username, "Successful registration", body);

		return u;

	}/* handleRegister() */
	@CrossOrigin
	@RequestMapping("/updateUser.action")
	public @ResponseBody User handleUpdateUser(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In update Handle");
		
		JSONObject obj = JSONUtil.getObj(request);
		HttpSession session = request.getSession(false);
		if(session != null) {
			System.out.println("not null");
//			System.out.println(session.getAttribute("user"));
		}
		User usr = (User) request.getSession().getAttribute("user");
		String username = usr.getEmail();
		String password = obj.getString("password");
		if(password != null && !password.equals("")) {
			try {
				password = HashedPassword.getHash(password);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		
		String first_name = (obj.has("first_name"))?obj.getString("first_name"):"";
		String last_name = (obj.has("last_name"))?obj.getString("last_name"):"";
		System.out.println("password: " + password);
		String profilePicture = (obj.has("profilePicture"))?obj.getString("profilePicture"):"";


		// validate input
		if (username == null || first_name == null || last_name == null) {
			System.out.println("Please fill out all fields");
			return new User("Please fill out all fields");
		}
		

		User u = userDao.selectById(username);
//		System.out.println("user = " + u);
		
		// validate email
		if (u == null) {
			return new User("Invalid username!!!");
		}
		
		// update  first name
		if (!(first_name.equals(""))) {
			u.setFirstName(first_name);
		}
		
		// update last name
		if (!(last_name.equals(""))) {
			u.setLastName(last_name);
		}
		u.setLastName(last_name);
		
		// update password if not null
		if (!(password.equals(""))) {
			u.setPassword(password);
			System.out.println("here " + u.getPassword());
		}
		
//		System.out.println(u);

		// update database
		userDao.update(u);	
		request.getSession(false).setAttribute("user",u);
		
		return u;
	}/* updateUser() */
	
	@RequestMapping("/profilePicture.action")
	public @ResponseBody Error handleProfilePicture(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("in profile picture handle");
		JSONObject obj = JSONUtil.getObj(req);		
		User user = (User) req.getSession(false).getAttribute("user");
		String picturePath = obj.getString("picture");
		System.out.println("picture path: " + picturePath);
		Error err = new Error();
		
		if (user == null) {
			err.setMessege("Invalid username");
			return err;
		}
		
		user.setProfilePicture(picturePath);
		
//		System.out.println("User = " + user);
		
		userDao.update(user);
		err.setMessege("Picture uploaded successfully");
		err.setError(false);		
		
		return err;
	}

	/*
	 * Return user specified by the email
	 */
	@RequestMapping("/getUser.action")
	public @ResponseBody User handleGetUser(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("In get user handle");
		JSONObject obj = JSONUtil.getObj(req);
		String username = obj.getString("email");
		if (username == null) {
			System.out.println("email was not found in the field");
			return new User("email was not found");
		}
		User u = userDao.selectById(username);
		if (u == null) {
			System.out.println("There are no accounts associated with this email");
			return new User("There are no accounts associated with this email");
		}
		u.setPassword("");
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
	
	@RequestMapping("/activation.action")
	public @ResponseBody User activate(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = JSONUtil.getObj(request);
		String key = obj.getString("activationKey");
		System.out.println("key "+key);
		Activation act = activationDao.selectById(key);
		System.out.println(act);
		if(act !=null) {
			User user =userDao.selectById(act.getEmail());
			user.setActivated(true);
			userDao.update(user);
			activationDao.delete(act);
			return user;
		}else {
			return null;
		}
	}
	/*
	 * Reset password of user
	 */
	@RequestMapping("/reset.action")
	public @ResponseBody Error resetPassword(HttpServletRequest req, HttpServletResponse resp) {
		String subject = "OurSpace: Reset your password";
		String body = "Your password has been succesfully reset \n\n";
		// get email of user
		JSONObject obj = JSONUtil.getObj(req);
		String email = obj.getString("email");
		Error err = new Error();
		User user = userDao.selectById(email);
		
		// check that user exists;
		if (user == null) {
			err.setMessege("Invalid email");
			return err;
		}
		
		// generate character array
		ArrayList<Character> ch = new ArrayList<Character>();
		String new_password = "";
		
		// lower case
		for (char cx = 'a'; cx <='z'; cx++) {
			ch.add(cx);
		}
		
		// upper case
		for (char cx = 'A'; cx <='Z'; cx++) {
			ch.add(cx);
		}
		
		// numbers
		for (char cx = '0'; cx <='9'; cx++) {
			ch.add(cx);
		}
		
		// generate random password
		for (int lcv =0; lcv <10; lcv++) {
			Collections.shuffle(ch);
			int index = (int) (Math.random() * (ch.size()-1) );
			new_password += ch.get(index);			
		}
		
		body += "Your new credentials are listed below: \n\n";
		
		body += "Email: " + email + "\n";
		body += "New Password: " + new_password;

		// send email
		if (EmailUtil.sendEmail(email, subject, body) == 1) {
			err.setMessege("Email sent succesfully");
			err.setError(false);
			
			// update password in the database
			String pwd = new_password;
			try {
				pwd = HashedPassword.getHash(new_password);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setPassword(pwd);
			userDao.update(user);
			
		} else {
			err.setMessege("Email not sent");
		}

		return err;
	}/* resetPassword()*/
	
	/*
	 * 
	 */

}
