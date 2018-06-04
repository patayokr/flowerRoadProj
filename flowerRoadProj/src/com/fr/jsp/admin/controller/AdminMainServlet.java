package com.fr.jsp.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fr.jsp.board.model.service.ReviewBoardService;
import com.fr.jsp.board.model.service.UserQuestionBoardService;
import com.fr.jsp.member.model.service.AdminService;
import com.fr.jsp.member.model.vo.Member;
import com.fr.jsp.order.model.service.OrderService;

@WebServlet("/main.admin")
public class AdminMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AdminMainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String adminNum = (String) session.getAttribute("adminNum");
		
		AdminService as = new AdminService();
		// 관리자 메인 이름, 사진
		Member admin = as.adminData(adminNum);
		// 관리자 메시지 알림
		int admin_messageCount = as.admin_uncheckedMessage(adminNum);
		// Connection close
		as.closeCon();
		
		OrderService os = new OrderService();
		// 오늘 등록된 주문 수
		int admin_orderCount = os.admin_todayOrder();
		// Connection close
		os.closeCon();
		
		ReviewBoardService rbs = new ReviewBoardService();
		// 오늘 등록된 리뷰 게시판 게시글 수
		int admin_reviewCount = rbs.admin_todayReview();
		// Connection close
		rbs.closeCon();
		
		UserQuestionBoardService uqbs = new UserQuestionBoardService();
		// 오늘 등록된 1:1 게시판 게시글 수
		int admin_userQuestionCount = uqbs.admin_todayUserQuestion();
		// Connection close
		uqbs.closeCon();
		
		session.setAttribute("admin", admin);
		session.setAttribute("admin_messageCount", admin_messageCount);
		session.setAttribute("admin_orderCount", admin_orderCount);
		session.setAttribute("admin_reviewCount", admin_reviewCount);
		session.setAttribute("admin_userQuestionCount", admin_userQuestionCount);
		request.getRequestDispatcher("views/admin_views/admin_Main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
