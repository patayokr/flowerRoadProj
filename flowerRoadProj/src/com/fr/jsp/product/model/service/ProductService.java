package com.fr.jsp.product.model.service;

import static com.fr.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.fr.jsp.common.PageInfo;
import com.fr.jsp.product.model.dao.ProductDao;

import com.fr.jsp.product.model.vo.ProductDetail;
import com.fr.jsp.product.model.vo.ProductSimple;

public class ProductService {

	private Connection con;
	private ProductDao pDao;
	
	public ProductService() {
		super();
		con = getConnection();
		pDao = new ProductDao();
	}
	
	//모든 상품 리스트(페이지네이션 X)
	public ArrayList<ProductDetail> getProductList() {
		
		
		ArrayList<ProductDetail> list = null;
		
		con = getConnection();
		
		list =pDao.getProductList(con); 
		
		
		if(list!=null)commit(con);
		else rollback(con);
		
		
		return list;
	}

	
	//모든 상품 숫자
	public int getListCount() {
		con = getConnection();
		
		int result= 0;
		
		result =pDao.getProductCount(con);
		
		
		return result;
	}

	
	//페이지 네이션 적용 상품 리스트 가져오기
	public ArrayList<ProductSimple> getProductList(PageInfo pi) {
		ArrayList<ProductSimple> list =null;
		con = getConnection();
		
		list = pDao.getProductList(con,pi);
	
		if(list!=null)commit(con);
		else rollback(con);
		
		
		return list;
	}

	
	//상품 하나만 가져오는거
	public ProductDetail getOneProduct(String productNum) {
		
		con = getConnection();
		
		ProductDetail p =pDao.getOneProduct(con,productNum); 
		
		if(p!=null)commit(con);
		else rollback(con);
		
		return p;
	}

	
	//특정 카테고리에 해당하는 상품의 리스트를 가져오기
	public ArrayList<ProductSimple> getCategorizedProduct(String category,PageInfo pi) {
		ArrayList<ProductSimple> list = null;
		con = getConnection();
		
		list = pDao.getCategorizedProductList(con,category,pi);
		
		if(list!=null)commit(con);
		else rollback(con);
		
		return list;
	}

	
	//특정 이벤트속성을 가지는 상품의 리스트 가져오기
	public ArrayList<ProductSimple> getEventTypeProductList(String event ,PageInfo pi) {
		
		ArrayList<ProductSimple> list = null;
		con = getConnection();
		
		list =pDao.getEventTypeProductList(con,event,pi);
		
		if(list!=null)commit(con);
		else rollback(con);
		
		return list;
	}

	
	//특정 이벤트속성을 가지는 상품의 갯수 가져오기
	public int getEventListCount(String event) {
		con = getConnection();
		
		int result= 0;
		
		result = pDao.getEventProductCount(con,event);
		
		
		return result;
	}


	public int getCategoryListCount(String category) {
		Connection con = getConnection();
		
		int result= 0;
		
		result = new ProductDao().getCategoryProductCount(con,category);
		return result;
	}


	public ArrayList<ProductSimple> getEventTypeOrderdProductList(String event, String order, PageInfo pi) {
		ArrayList<ProductSimple> list = null;
		con = getConnection();
		
		String orderQuery = null;
		if(order.equals("review"))
			orderQuery=" ORDER BY 8 DESC";
		else if(order.equals("priceDesc"))
			orderQuery=" ORDER BY 3 DESC";
		else if(order.equals("priceAsc"))
			orderQuery=" ORDER BY 3";
		else if(order.equals("byName"))
			orderQuery=" ORDER BY 2";
		
		list =pDao.getEventOrderdProductList(con,event,orderQuery,pi);
		
		if(list!=null)commit(con);
		else rollback(con);
		
		return list;
	}


	public ArrayList<ProductSimple> getCategorizedOrderdProductList(String category, String order, PageInfo pi) {
		
		ArrayList<ProductSimple> list =null;
		con = getConnection();
		
		String orderQuery = null;
		if(order.equals("review"))
			orderQuery=" ORDER BY 7 DESC";
		else if(order.equals("priceDesc"))
			orderQuery=" ORDER BY 3 DESC";
		else if(order.equals("priceAsc"))
			orderQuery=" ORDER BY 3";
		else if(order.equals("byName"))
			orderQuery=" ORDER BY 2";
		else orderQuery ="";
		
		list = pDao.getCategorizedOrderdProductList(con,category,orderQuery,pi);
		
		if(list!=null)commit(con);
		else rollback(con);
		
		return list;
	}


	public ArrayList<ProductSimple> getOptionProductList() {
		Connection con = getConnection();
		
		ArrayList<ProductSimple> list;
		
		list = new ProductDao().getOptionProductList(con);
		return list;
	}
	
	// Connection close Method
		public void closeCon() {
			close(con);
		}
		// 등록된 모든 상품 수
		public int admin_allProductCount() {
			ArrayList<ProductDetail> admin_allProductList = pDao.admin_allProductList(con);
			int admin_allProductCount = admin_allProductList.size();
			return admin_allProductCount;
		}
		// 오늘 등록한 상품 수
		public int admin_todayRegiProductCount() {
			int admin_todayRegiProductCount = pDao.admin_todayRegiProductCount(con);
			return admin_todayRegiProductCount;
		}
		// 재고부족 상품 수
		public int admin_stockLackProductCount() {
			int admin_stockLackProductCount = pDao.admin_stockLackProductCount(con);
			return admin_stockLackProductCount;
		}
		// 오늘 발주한 상품 수
		public int admin_todayProductOrderCount() {
			int admin_todayProductOrderCount = pDao.admin_todayProductOrderCount(con);
			return admin_todayProductOrderCount;
		}

}
