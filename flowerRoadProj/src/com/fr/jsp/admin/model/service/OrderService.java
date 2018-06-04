package com.fr.jsp.order.model.service;

import static com.fr.jdbc.common.JDBCTemplate.close;
import static com.fr.jdbc.common.JDBCTemplate.commit;
import static com.fr.jdbc.common.JDBCTemplate.getConnection;
import static com.fr.jdbc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.fr.jsp.order.model.dao.OrderDao;
import com.fr.jsp.order.model.vo.AdminOrder;
import com.fr.jsp.order.model.vo.Order;

public class OrderService {
	private Connection con;
	private OrderDao oDao;
	
	public OrderService() {
		super();
		con = getConnection();
		oDao = new OrderDao();
	}
	// Connection close Method
	public void closeCon(){
		close(con);
	}
	// 오늘 등록된 주문 수
	public int admin_todayOrder() {
		int admin_orderCount = oDao.admin_todayOrder(con);
		return admin_orderCount;
	}
	// 전체 주문/배송 리스트
	public ArrayList<AdminOrder> admin_allDeliveryList() {
		ArrayList<AdminOrder> admin_allDeliveryList = oDao.admin_allDeliveryList(con);
		return admin_allDeliveryList;
	}
	// 결제 완료 주문 리스트
	public ArrayList<AdminOrder> admin_paycompleteList() {
		ArrayList<AdminOrder> admin_paycompleteList = oDao.admin_paycompleteList(con);
		return admin_paycompleteList;
	}
	// 상품 준비 주문 리스트
	public ArrayList<AdminOrder> admin_productReadyList() {
		ArrayList<AdminOrder> admin_productReadyList = oDao.admin_productReadyList(con);
		return admin_productReadyList;
	}
	// 배송 출발 주문 리스트
	public ArrayList<AdminOrder> admin_deliverystartList() {
		ArrayList<AdminOrder> admin_deliverystartList = oDao.admin_deliverystartList(con);
		return admin_deliverystartList;
	}
	// 배송 완료 주문 리스트
	public ArrayList<AdminOrder> admin_deliverycompleteList() {
		ArrayList<AdminOrder> admin_deliverycompleteList = oDao.admin_deliverycompleteList(con);
		return admin_deliverycompleteList;
	}
	// 구매 취소 주문 리스트
	public ArrayList<AdminOrder> admin_ordercancleList() {
		ArrayList<AdminOrder> admin_ordercancleList = oDao.admin_ordercancleList(con);
		return admin_ordercancleList;
	}
	// 상품 주문 코드 변경
	public void admin_updateOrderState(Order order) {
		int updateOrderStateCount = oDao.admin_updateOrderState(con, order);
		if(updateOrderStateCount>0) commit(con);
		else rollback(con);
	}
	// 주문/배송 환불
	public void admin_updateRefundOrder(Order order) {
		int updateRefundOrderCount = oDao.admin_updateRefundOrder(con, order);
		if(updateRefundOrderCount>0) commit(con);
		else rollback(con);
	}

}
