package com.fr.jsp.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.jsp.member.model.service.AdminService;
import com.fr.jsp.member.model.vo.AdminAdmin;
import com.fr.jsp.member.model.vo.AdminMember;

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
		int admin_memberCount = as.admin_memberList().size();
		// 관리자 수 조회
		int admin_adminCount = as.admin_adminList().size();
		// 오늘 등록한 회원 수
		int admin_todayEnrollCount = as.admin_todayEnrollMemberList().size();
		// 탈퇴한 회원 수
		int admin_withdrawMemberCount = as.admin_withdrawMemberCount();
		// 회원 리스트
		ArrayList<AdminMember> admin_memberList = as.admin_memberList();
		// 관리자 리스트
		ArrayList<AdminAdmin> admin_adminList = as.admin_adminList();
		// connection close
		as.closeCon();
		
		request.setAttribute("admin_allMemberCount", admin_allMemberCount);
		request.setAttribute("admin_memberCount", admin_memberCount);
		request.setAttribute("admin_adminCount", admin_adminCount);
		request.setAttribute("admin_todayEnrollCount", admin_todayEnrollCount);
		request.setAttribute("admin_withdrawMemberCount", admin_withdrawMemberCount);
		request.setAttribute("admin_memberList", admin_memberList);
		request.setAttribute("admin_adminList", admin_adminList);		
		
		request.getRequestDispatcher("views/admin_views/admin_Member.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
