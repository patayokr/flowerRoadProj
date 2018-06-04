package com.fr.jsp.admin.model.service;

import static com.fr.jdbc.common.JDBCTemplate.close;
import static com.fr.jdbc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.fr.jsp.board.model.dao.ReviewBoardDao;
import com.fr.jsp.board.model.vo.ReviewBoard;

public class ReviewBoardService {
	private Connection con;
	private ReviewBoardDao rbDao;
	
	public ReviewBoardService() {
		super();
		con = getConnection();
		rbDao = new ReviewBoardDao();
	}
	// Connection close Method
	public void closeCon(){
		close(con);
	}
	// 오늘 등록된 리뷰 게시판 게시글
	public int admin_todayReview() {
		int admin_reviewCount = rbDao.admin_todayReview(con);
		return admin_reviewCount;
	}
	// 전체 리뷰 게시판 게시글
	public int admin_reviewBoardAllCount() {
		ArrayList<ReviewBoard> admin_reviewBoardAllList = rbDao.admin_reviewBoardAllList(con);
		int admin_reviewBoardCount = admin_reviewBoardAllList.size();
		return admin_reviewBoardCount;
	}
	// 리뷰 게시판 리스트
	public ArrayList<ReviewBoard> admin_reviewBoardList() {
		ArrayList<ReviewBoard> admin_reviewBoardAllList = rbDao.admin_reviewBoardAllList(con);
		return admin_reviewBoardAllList;
	}

}
