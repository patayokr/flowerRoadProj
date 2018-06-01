package com.fr.jsp.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.jsp.order.model.service.OrderService;

@WebServlet("/delivery.admin")
public class AdminDeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminDeliveryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService os = new OrderService();
		// 전체 주문/배송 수
		int admin_allDeliveryCount = os.admin_allDeliveryCount();
		// 결제 완료된 주문 수
		int admin_paycompleteCount = os.admin_paycompleteCount();
		// 배송 출발한 주문 수
		int admin_deliverystartCount = os.admin_deliverystartCount();
		// 배송 완료된 주문 수
		int admin_deliverycompleteCount = os.admin_deliverycompleteCount();
		// 구매 취소된 주문 수
		int admin_ordercancleCount = os.admin_ordercancleCount();
		// Connection close
		os.closeCon();
		
		request.setAttribute("admin_allDeliveryCount", admin_allDeliveryCount);
		request.setAttribute("admin_paycompleteCount", admin_paycompleteCount);
		request.setAttribute("admin_deliverystartCount", admin_deliverystartCount);
		request.setAttribute("admin_deliverycompleteCount", admin_deliverycompleteCount);
		request.setAttribute("admin_ordercancleCount", admin_ordercancleCount);
		
		request.getRequestDispatcher("views/admin_views/admin_Delivery.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
