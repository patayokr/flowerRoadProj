package com.fr.jsp.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fr.jsp.admin.model.service.AdminService;
import com.fr.jsp.member.model.vo.Member;
import com.google.gson.Gson;

@WebServlet("/secondPwCheck.admin")
public class AdminSecondPwCheckServlert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminSecondPwCheckServlert() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminId = request.getParameter("adminId");
		String answerPw = request.getParameter("answerPw");
		System.out.println(adminId);
		System.out.println(answerPw);
		HttpSession session = request.getSession();
		session.setAttribute("adminId", adminId);
		
		AdminService as = new AdminService();
		Member admin = as.adminSecondPwCheck(adminId, answerPw);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(admin, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
