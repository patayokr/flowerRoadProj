package com.fr.jsp.product.model.service;

import static com.fr.jdbc.common.JDBCTemplate.*;
import static com.fr.jdbc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.fr.jsp.product.model.dao.ProductDao;
import com.fr.jsp.product.model.vo.AdminProduct;
import com.fr.jsp.product.model.vo.AdminProductEvent;
import com.fr.jsp.product.model.vo.AdminProductImage;
import com.fr.jsp.product.model.vo.AdminProductOrder;
import com.fr.jsp.product.model.vo.Product;

public class ProductService {
	private Connection con;
	private ProductDao pDao;
	
	public ProductService() {
		super();
		con = getConnection();
		pDao = new ProductDao();
	}
	// Connection close Method
	public void closeCon() {
		close(con);
	}
	// 등록된 모든 상품 리스트
	public ArrayList<AdminProduct> admin_allProductList() {
		ArrayList<AdminProduct> admin_allProductList = pDao.admin_allProductList(con);
		return admin_allProductList;
	}
	// 오늘 등록한 상품 리스트
	public ArrayList<AdminProduct> admin_todayRegiProductList() {
		ArrayList<AdminProduct> admin_todayRegiProductList = pDao.admin_todayRegiProductList(con);
		return admin_todayRegiProductList;
	}
	// 재고부족 상품 리스트
	public ArrayList<AdminProduct> admin_stockLackProductList() {
		ArrayList<AdminProduct> admin_stockLackProductList = pDao.admin_stockLackProductList(con);
		return admin_stockLackProductList;
	}
	// 오늘 발주한 상품 리스트
	public ArrayList<AdminProductOrder> admin_todayProductOrderList() {
		ArrayList<AdminProductOrder> admin_todayProductOrderList = pDao.admin_todayProductOrderList(con);
		return admin_todayProductOrderList;
	}
	// 상품 카테고리 리스트
	public ArrayList<String> admin_productCategoryList() {
		ArrayList<String> admin_productCategoryList = pDao.admin_productCategoryList(con);
		return admin_productCategoryList;
	}
	// 상품 원산지 리스트
	public ArrayList<String> admin_productOriginList() {
		ArrayList<String> admin_productOriginList = pDao.admin_productOriginList(con);
		return admin_productOriginList;
	}
	// 발주 현황 리스트
	public ArrayList<AdminProductOrder> admin_productOrderList() {
		ArrayList<AdminProductOrder> admin_productOrderList = pDao.admin_productOrderList(con);
		return admin_productOrderList;
	}
	// 상품 수정
	public Product admin_updateProduct(AdminProduct product) {
		Product updateProduct = new Product();
		AdminProductImage updatePImage = new AdminProductImage();
		AdminProductEvent updatePEvent = new AdminProductEvent();
		String productCategory = pDao.admin_checkProductCategory(product.getProductCategoryName(),con);
		String productOrigin = pDao.admin_checkProductOrigin(product.getProductOriginName(),con);
		
		updateProduct.setProductNum(product.getProductNum());
		updateProduct.setProductName(product.getProductName());
		updateProduct.setProductCategory(productCategory);
		updateProduct.setProductOrigin(productOrigin);
		updateProduct.setProductQuantity(product.getProductQuantity());
		updateProduct.setProductCost(product.getProductCost());
		updateProduct.setProductPrice(product.getProductPrice());
		
		// 상품 정보 수정
		int updateCount = pDao.admin_updateProduct(con, updateProduct);
		if(updateCount>0) commit(con);
		else rollback(con);
		
		// 상품 이미지 수정
		if(product.getImagePath()==""){ 
			
		}else{
			updatePImage.setProductNum(product.getProductNum());
			updatePImage.setImagePath(product.getImagePath());
			int updateProductImageCount = pDao.admin_updateProductImage(con, updatePImage);
			if(updateProductImageCount>0) commit(con);
			else rollback(con);
		}
		
		// 상품 이벤트 수정
		updatePEvent.setProductNum(product.getProductNum());
		updatePEvent.setProductEvent(product.getProductEvent());
		int updateProductEventCount = pDao.admin_updateProductEvent(con, updatePEvent);
		if(updateProductEventCount>0) commit(con);
		else rollback(con);
		
		return updateProduct;
	}
	// 상품 정보,이미지,이벤트 등록
	public void admin_insertProduct(AdminProduct product) {
		Product insertProduct = new Product();
		AdminProductImage insertPImage = new AdminProductImage();
		AdminProductEvent insertPEvent = new AdminProductEvent();
		String productCategory = pDao.admin_checkProductCategory(product.getProductCategoryName(),con);
		String productOrigin = pDao.admin_checkProductOrigin(product.getProductOriginName(),con);
		
		insertProduct.setProductName(product.getProductName());
		insertProduct.setProductCategory(productCategory);
		insertProduct.setProductOrigin(productOrigin);
		insertProduct.setProductQuantity(product.getProductQuantity());
		insertProduct.setProductCost(product.getProductCost());
		insertProduct.setProductPrice(product.getProductPrice());
		
		// 상품 정보 등록
		int insertCount = pDao.admin_insertProduct(con, insertProduct);
		if(insertCount>0) commit(con);
		else rollback(con);
		
		// 상품 번호 조회
		String insertPNum = pDao.admin_selectProductNum(con,insertProduct);
		insertProduct.setProductNum(insertPNum);
		
		// 상품 이미지 등록
		insertPImage.setProductNum(insertPNum);
		insertPImage.setImagePath(product.getImagePath());
		int insertProductImageCount = pDao.admin_insertProductImage(con, insertPImage);
		if(insertProductImageCount>0) commit(con);
		else rollback(con);
		
		// 상품 이벤트 등록
		if(product.getProductEvent()!=""){
			insertPEvent.setProductNum(insertProduct.getProductNum());
			insertPEvent.setProductEvent(product.getProductEvent());
			int insertProductEventCount = pDao.admin_insertProductEvent(con, insertPEvent);
			if(insertProductEventCount>0) commit(con);
			else rollback(con);
		}
		
	}
	// 상품 발주 등록
	public void admin_insertProductOrder(AdminProductOrder productOrder) {
		int insertProductOrderCount = pDao.admin_insertProductOrder(con, productOrder);
		if(insertProductOrderCount>0) commit(con);
		else rollback(con);
	}

}
