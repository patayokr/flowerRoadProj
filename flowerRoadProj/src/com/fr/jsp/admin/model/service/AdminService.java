package com.fr.jsp.admin.model.service;

import static com.fr.jdbc.common.JDBCTemplate.close;
import static com.fr.jdbc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.fr.jsp.admin.model.dao.AdminDao;
import com.fr.jsp.member.model.vo.Member;
import com.fr.jsp.order.model.vo.Order;

public class AdminService {
	private Connection con;
	private AdminDao aDao;
	
	public AdminService() {
		super();
		con = getConnection();
		aDao = new AdminDao();
	}
	// Connection close Method
	public void closeCon(){
		close(con);
	}
	// 관리자 2차 보안
	public Member adminSecondPwCheck(String adminId, String answerPw) {
		Member admin = aDao.adminSecondPwCheck(con,adminId);
		return admin;
	}
	// 관리자 메인 이름, 사진
	public Member adminData(String adminId) {
		Member admin = aDao.adminData(con,adminId);
		return admin;
	}
	// 관리자 메시지 알림
	public int admin_uncheckedMessage(String adminId) {
		int admin_messageCount = aDao.admin_uncheckedMessage(con,adminId);
		return admin_messageCount;
	}
	// 모든 회원 수 조회
	public int admin_allMemberCount() {
		ArrayList<Order> admin_allMemberList = aDao.admin_allMemberList(con);
		int admin_allMemberCount = admin_allMemberList.size();
		return admin_allMemberCount;
	}
	// 회원 수 조회
	public int admin_memberCount() {
		int admin_memberCount = aDao.admin_memberCount(con);
		return admin_memberCount;
	}
	// 관리자 수 조회
	public int admin_adminCount() {
		int admin_adminCount = aDao.admin_adminCount(con);
		return admin_adminCount;
	}
	// 오늘 등록한 회원 수 조회
	public int admin_todayEnrollCount() {
		int admin_todayEnrollCount = aDao.admin_todayEnrollCount(con);
		return admin_todayEnrollCount;
	}

}
