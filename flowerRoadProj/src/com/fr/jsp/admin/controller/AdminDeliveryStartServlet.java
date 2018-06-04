package com.fr.jsp.order.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.jsp.order.model.service.OrderService;
import com.fr.jsp.order.model.vo.AdminOrder;

@WebServlet("/deliveryStart.admin")
public class AdminDeliveryStartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminDeliveryStartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService os = new OrderService();
		// 전체 주문/배송 수
		int admin_allDeliveryCount = os.admin_allDeliveryList().size();
		// 결제 완료된 주문 수
		int admin_paycompleteCount = os.admin_paycompleteList().size();
		// 상품 준비중인 주문 수
		int admin_productReadyCount = os.admin_productReadyList().size();
		// 배송 출발한 주문 수
		int admin_deliverystartCount = os.admin_deliverystartList().size();
		// 배송 완료된 주문 수
		int admin_deliverycompleteCount = os.admin_deliverycompleteList().size();
		// 구매 취소된 주문 수
		int admin_ordercancleCount = os.admin_ordercancleList().size();
		// 배송 출발 주문 리스트
		ArrayList<AdminOrder> admin_deliverystartList = os.admin_deliverystartList();
		
		request.setAttribute("admin_allDeliveryCount", admin_allDeliveryCount);
		request.setAttribute("admin_paycompleteCount", admin_paycompleteCount);
		request.setAttribute("admin_productReadyCount", admin_productReadyCount);
		request.setAttribute("admin_deliverystartCount", admin_deliverystartCount);
		request.setAttribute("admin_deliverycompleteCount", admin_deliverycompleteCount);
		request.setAttribute("admin_ordercancleCount", admin_ordercancleCount);
		request.setAttribute("admin_orderList", admin_deliverystartList);
		
		request.getRequestDispatcher("views/admin_views/admin_Delivery.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
