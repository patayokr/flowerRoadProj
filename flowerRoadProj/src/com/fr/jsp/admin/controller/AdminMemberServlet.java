package com.fr.jsp.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.jsp.admin.model.service.AdminService;

@WebServlet("/member.admin")
public class AdminMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminMemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService as = new AdminService();
		// 모든 회원 수 조회
		int admin_allMemberCount = as.admin_allMemberCount();
		// 회원 수 조회
		int admin_memberCount = as.admin_memberCount();
		// 관리자 수 조회
		int admin_adminCount = as.admin_adminCount();
		// 오늘 등록한 회원 수
		int admin_todayEnrollCount = as.admin_todayEnrollCount();
		// connection close
		as.closeCon();
		
		request.setAttribute("admin_allMemberCount", admin_allMemberCount);
		request.setAttribute("admin_memberCount", admin_memberCount);
		request.setAttribute("admin_adminCount", admin_adminCount);
		request.setAttribute("admin_todayEnrollCount", admin_todayEnrollCount);
		request.getRequestDispatcher("views/admin_views/admin_Member.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
