package server.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Main;

public class MasterServlet extends HttpServlet {


	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4155518073695374612L;
	/**
	 * 
	 */
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		System.out.println("we inside universal get");
		
		//session.invalidate()
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Main.test();
		
		
		
	}
}
