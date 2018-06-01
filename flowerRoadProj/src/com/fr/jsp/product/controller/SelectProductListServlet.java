package com.fr.jsp.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.jsp.board.model.service.ReviewBoardService;
import com.fr.jsp.common.PageInfo;
import com.fr.jsp.product.model.service.ProductService;
import com.fr.jsp.product.model.vo.ProductSimple;
import com.google.gson.Gson;

/**
 * Servlet implementation class SelectProductListServlet
 */
@WebServlet("/productList.do")
public class SelectProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectProductListServlet() {
        super();
        // TODO Auto-generated constructor stub 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String event =null;
		String category =null;
		String order = null;
		ArrayList<ProductSimple> list = new ArrayList<ProductSimple>();
		
		ProductService ps = new ProductService();
		int currPage=1; 		//현재 페이지
		int limit=9; 			//한 페이지당 게시글을 몇개 보여줄건지
		String page ="";
		PageInfo pi =null;
		
		event  = request.getParameter("event");
		category = request.getParameter("category");
		order  =request.getParameter("order");
		
		
		if(event==null)
			event="none";
		if(category==null)
			category="none";
		if(order==null)
			order="none";
		
		
		if(request.getParameter("currPage")!=null){
			currPage = Integer.parseInt(request.getParameter("currPage"));
		}
		
		if(!event.equals("none")){
			
			pi = new PageInfo(currPage,ps.getEventListCount(event),limit);
			
			if(!order.equals("none"))	
				list = ps.getEventTypeOrderdProductList(event,order,pi);
			else
				list = ps.getEventTypeProductList(event,pi);

		}else if(!category.equals("none")){
			
			pi = new PageInfo(currPage,ps.getCategoryListCount(category),limit);
			
			if(!order.equals("none"))	
				list = ps.getCategorizedOrderdProductList(category,order,pi);
			else
				list= ps.getCategorizedProduct(category,pi);	
		}
	
		
		
		if(list!=null){
			
			page ="views/product/productList.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("event", event);
			request.setAttribute("category", category);
			request.setAttribute("order",order);
			
		}else{
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "상품 리스트를 불러올 수 없습니다.");
		}
		
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
