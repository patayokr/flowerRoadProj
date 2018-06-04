package com.fr.jsp.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.jsp.product.model.service.ProductService;
import com.fr.jsp.product.model.vo.AdminProduct;
import com.fr.jsp.product.model.vo.AdminProductOrder;

@WebServlet("/stockLackProduct.admin")
public class AdminStockLackProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminStockLackProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService ps = new ProductService();
		// 등록된 모든 상품 수
		int admin_allProductCount = ps.admin_allProductList().size();
		// 오늘 등록한 상품 수
		int admin_todayRegiProductCount = ps.admin_todayRegiProductList().size();
		// 재고부족 상품 수
		int admin_stockLackProductCount = ps.admin_stockLackProductList().size();
		// 오늘 발주한 상품 수
		int admin_todayProductOrderCount = ps.admin_todayProductOrderList().size();
		
		// 재고부족 상품 리스트
		ArrayList<AdminProduct> admin_stockLackProductList = ps.admin_stockLackProductList();
		// 상품 카테고리 리스트
		ArrayList<String> admin_productCategoryList = ps.admin_productCategoryList();
		// 상품 원산지 리스트
		ArrayList<String> admin_productOriginList = ps.admin_productOriginList();
		// 발주 현황 리스트
		ArrayList<AdminProductOrder> admin_productOrderList = ps.admin_productOrderList();
		// connection close
		ps.closeCon();
		
		request.setAttribute("admin_allProductCount", admin_allProductCount);
		request.setAttribute("admin_todayRegiProductCount", admin_todayRegiProductCount);
		request.setAttribute("admin_stockLackProductCount", admin_stockLackProductCount);
		request.setAttribute("admin_todayProductOrderCount", admin_todayProductOrderCount);
		
		request.setAttribute("admin_productList", admin_stockLackProductList);
		request.setAttribute("admin_productCategoryList", admin_productCategoryList);
		request.setAttribute("admin_productOriginList", admin_productOriginList);
		request.setAttribute("admin_productOrderList", admin_productOrderList);
		
		request.getRequestDispatcher("views/admin_views/admin_Product.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
