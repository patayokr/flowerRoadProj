package com.fr.jsp.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.jsp.product.model.service.ProductService;

@WebServlet("/product.admin")
public class AdminProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService ps = new ProductService();
		// 등록된 모든 상품 수
		int admin_allProductCount = ps.admin_allProductCount();
		// 오늘 등록한 상품 수
		int admin_todayRegiProductCount = ps.admin_todayRegiProductCount();
		// 재고부족 상품 수
		int admin_stockLackProductCount = ps.admin_stockLackProductCount();
		// 오늘 발주한 상품 수
		int admin_todayProductOrderCount = ps.admin_todayProductOrderCount();
		// connection close
		ps.closeCon();
		
		request.setAttribute("admin_allProductCount", admin_allProductCount);
		request.setAttribute("admin_todayRegiProductCount", admin_todayRegiProductCount);
		request.setAttribute("admin_stockLackProductCount", admin_stockLackProductCount);
		request.setAttribute("admin_todayProductOrderCount", admin_todayProductOrderCount);
		
		request.getRequestDispatcher("views/admin_views/admin_Product.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
