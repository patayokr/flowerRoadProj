package com.fr.jsp.admin.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import static com.fr.jdbc.common.JDBCTemplate.*;

import com.fr.jsp.member.model.vo.Member;
import com.fr.jsp.order.model.vo.Order;

public class AdminDao {
	private Properties prop;
	
	public AdminDao() {
		super();
		prop = new Properties();
		String fileName = AdminDao.class.getResource("/config/member/admin_query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 관리자 2차 보안
	public Member adminSecondPwCheck(Connection con, String adminId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member admin = null;
		
		try { 
			String query = prop.getProperty("baseData");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, adminId);
			rset = pstmt.executeQuery();
			while(rset.next()){
				admin = new Member();
				admin.setMemberName(rset.getString("MEMBER_NAME"));
				admin.setSecondPw(rset.getString("SECOND_PW"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return admin;
	}
	// 관리자 메인 이름, 사진
	public Member adminData(Connection con, String adminId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member admin = null;
		
		try { 
			String query = prop.getProperty("baseData");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, adminId);
			rset = pstmt.executeQuery();
			while(rset.next()){
				admin = new Member();
				admin.setMemberName(rset.getString("MEMBER_NAME"));
				admin.setImagePath(rset.getString("IMAGE_PATH"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
//			close(pstmt);
		}
		
		return admin;
	}
	// 관리자 메시지 알림
	public int admin_uncheckedMessage(Connection con, String adminId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int admin_messageCount = 0;
		
		try { 
			String query = prop.getProperty("admin_messageCount");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, adminId);
			rset = pstmt.executeQuery();
			while(rset.next()){
				admin_messageCount = Integer.parseInt(rset.getString("COUNT(RECEIVE_MEMBER_NUM)"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return admin_messageCount;
	}
	// 모든 회원 리스트
	public ArrayList<Order> admin_allMemberList(Connection con) {
		// TODO Auto-generated method stub
		return null;
	}
	// 회원 수 조회
	public int admin_memberCount(Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}
	// 관리자 수 조회
	public int admin_adminCount(Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}
	// 오늘 등록한 회원 수 조회
	public int admin_todayEnrollCount(Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}

}
