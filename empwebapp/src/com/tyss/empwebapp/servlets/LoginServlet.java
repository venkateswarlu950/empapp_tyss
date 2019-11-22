package com.tyss.empwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tyss.empwebapp.dao.EmployeeDAO;
import com.tyss.empwebapp.dto.EmployeeInfo;
import com.tyss.empwebapp.util.EmployeeDaoManager;


@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("uid"));
		String pwd = req.getParameter("psw");

		EmployeeDAO dao = EmployeeDaoManager.getEmployeeDAO();

		EmployeeInfo info = dao.auth(id, pwd);

		if(info==null) {
			PrintWriter out = resp.getWriter();
			out.println("<h4 style='color : Red'>Invalid id or password </h4>");
			RequestDispatcher disp = req.getRequestDispatcher("/login.html");

			disp.include(req, resp);
		} 
		else {
			HttpSession session = req.getSession(true);
			session.setAttribute("info", info);
			RequestDispatcher disp = req.getRequestDispatcher("/home");

			disp.forward(req, resp);
		}
	}

}
