package com.fr.jsp.admin.model.service;

import static com.fr.jdbc.common.JDBCTemplate.close;
import static com.fr.jdbc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.fr.jsp.admin.model.dao.UserQuestionBoardDao;
import com.fr.jsp.board.model.vo.UserQuestionBoard;

public class UserQuestionBoardService {
	private Connection con;
	private UserQuestionBoardDao uqbDao;
	
	public UserQuestionBoardService() {
		super();
		con = getConnection();
		uqbDao = new UserQuestionBoardDao();
	}
	// Connection close Method
	public void closeCon(){
		close(con);
	}
	// 오늘 등록된 1:1 게시판 게시글
	public int admin_todayUserQuestion() {
		int admin_userQuestionCount = uqbDao.admin_todayUserQuestion(con);
		close(con);
		return admin_userQuestionCount;
	}
	// 전체 1:1 게시판 게시글
	public int admin_userQuestionBoardAllCount() {
		ArrayList<UserQuestionBoard> admin_userQuestionBoardAllList = uqbDao.admin_userQuestionBoardAllList(con);
		int admin_userQuestionBoardCount = admin_userQuestionBoardAllList.size();
		return admin_userQuestionBoardCount;
	}
	// 1:1 게시판 리스트
	public ArrayList<UserQuestionBoard> admin_userQuestionBoardList() {
		ArrayList<UserQuestionBoard> admin_userQuestionBoardAllList = uqbDao.admin_userQuestionBoardAllList(con);
		return admin_userQuestionBoardAllList;
	}

}
