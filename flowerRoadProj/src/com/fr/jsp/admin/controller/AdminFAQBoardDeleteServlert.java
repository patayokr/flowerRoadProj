package com.fr.jsp.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fr.jsp.admin.model.service.AdminService;
import com.fr.jsp.admin.model.service.FaqBoardService;
import com.fr.jsp.member.model.vo.Member;
import com.google.gson.Gson;

@WebServlet("/deleteFAQBoard.admin")
public class AdminFAQBoardDeleteServlert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminFAQBoardDeleteServlert() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String adminNum = (String) session.getAttribute("adminNum");
		String faqNum = request.getParameter("faqNum");
		String faqAnswerPw = request.getParameter("faqAnswerPw");
		
		AdminService as = new AdminService();
		Member admin = as.adminSecondPwCheck(adminNum);
		
		boolean faqCheck;
		if(admin.getSecondPw().equals(faqAnswerPw)){
			FaqBoardService fbs = new FaqBoardService();
			
			// FAQ 삭제
			fbs.admin_deleteFAQBoard(faqNum);
			
			// Connection close Method
			fbs.closeCon();
			
			faqCheck=true;
		}else{
			faqCheck=false;
		}
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(faqCheck, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
