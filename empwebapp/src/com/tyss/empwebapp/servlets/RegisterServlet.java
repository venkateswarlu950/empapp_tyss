package com.tyss.empwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tyss.empwebapp.dao.EmployeeDAO;
import com.tyss.empwebapp.dto.EmployeeInfo;
import com.tyss.empwebapp.util.EmployeeDaoManager;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("uid"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pwd = req.getParameter("psw");

		EmployeeInfo info = new EmployeeInfo();
		info.setEid(id);
		info.setEname(name);
		info.setEmail(email);
		info.setPassword(pwd);

		EmployeeDAO dao = EmployeeDaoManager.getEmployeeDAO();

		boolean check = dao.registerEmp(info);
		PrintWriter out = resp.getWriter();

		if(check) {
            out.println("<html>");
			out.println("<h4> Registration Completed </h4>");
			out.println("</html>");
		}else {
			out.println("<h4> Id can not be repeated </h4>");
		}

		RequestDispatcher disp = req.getRequestDispatcher("./login.html");
		disp.forward(req, resp);

	}


}
