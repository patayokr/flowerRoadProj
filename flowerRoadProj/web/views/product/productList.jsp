<%@page import="com.fr.jsp.product.model.vo.ProductSimple"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.* ,com.fr.jsp.product.model.vo.ProductDetail,com.fr.jsp.common.PageInfo"%>
    
<% ArrayList<ProductSimple> list = (ArrayList<ProductSimple>)request.getAttribute("list"); 
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int reviewCount = (Integer)request.getAttribute("reviewCount");
	String event = (String)request.getAttribute("event");
	String category = (String)request.getAttribute("category");
	String order = (String)request.getAttribute("order");
	String member_num = (String)session.getAttribute("memberNum");
	
	if(event==null)
		event="none";
	
	if(category==null)
		category="none";
	
	
	if(order==null)
		order="none";
	
%>
<!DOCTYPE html>
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>

<meta charset="UTF-8">
	
    
	<title>상품목록</title>
	<style>		
		@media (max-width: 767px){ 
                .container{
                    width: 760px;
                }
                .container-fluid{
                	width: 760px;
                }
            }
        
        .hrline{
        	background: black;
        } 
        .top-margin{
        	margin-top: 1.5em;
        }
        .linein{
        	display: inline;
        } 
        .test-border{
        	border: 1px solid black;
        }
        .rightAlign{
        	text-align: right;
        }
        .font-gray{
        	color: gray;
        }
        .border-gray{
        	border: 1px solid gray;
        }
        .font-title{
        	font-size: 1.2em;
        	color: #4a4a4a;
			padding-left: 0;
			text-overflow: ellipsis;
			white-space: nowrap;
			overflow: hidden;
			display:block;
        }
        .tag{
        	position: relative;
        	top: -3em;
        	right: -1em;
        }
        
        .decrease_padding{
        	padding-left:3px;
        	padding-right:3px;
        }
        .thumbnail img:HOVER{
        
        	opacity: 0.5;
   			filter: alpha(opacity=50);
        	animation-name: opaque;
    		animation-duration: 10s;
        }
        
       
        .items{
        	width:auto;
        	height: 540px;
        }
        
        .test{
        	border:1px solid;
        }
     
        
	</style>


</head>
<body>




<%@include file="../common/header.jsp"%>
<br /><br /><br /><br /><br /><br />
<%-- <%@ include file="../common/eventMenu.jsp"%> --%>

<jsp:include page="../common/eventMenu.jsp">

<jsp:param name="category" value="<%=event %>" />
 
</jsp:include>
   	
   <!-- 여기부터 내용을 넣겠다 -->
	<div class="container">
	<div class="col-lg-12 visible-lg " style="margin-top: 4em"></div>
		
		<div class="col-lg-12 top-margin"> <!-- 높은가격순? -->
			
			<div class="col-lg-12" ><!-- 여기에 상품 리스트. 사진들 들어갈거야 -->
				<!-- <div class="row"> -->
				<div class="linein">
				<a href="<%=request.getContextPath()%>/productList.do?event=<%=event%>&category=<%=category %>&order=review" type="button" class="btn btn-primary">평점순</a></div>
				<div class="linein">
				<a href="<%=request.getContextPath()%>/productList.do?event=<%=event%>&category=<%=category %>&order=priceDesc" type="button" class="btn btn-success">높은 가격순</a></div>
				<div class="linein">
				<a href="<%=request.getContextPath()%>/productList.do?event=<%=event%>&category=<%=category %>&order=priceAsc" type="button" class="btn btn-info">낮은 가격순</a></div>
				<div class="linein">
				<a href="<%=request.getContextPath()%>/productList.do?event=<%=event%>&category=<%=category %>&order=byName" type="button" class="btn btn-warning">이름순</a></div>
				<hr class="col-lg-12 hrline" style="float:center;">
				
				<%if(list!=null){ %>
				<%for(int i=0;i<list.size();++i) {%>
				
  					<div class="col-xs-6 col-sm-6 col-md-4 col-lg-4">
    					<div class="col-lg-12 thumbnail items ">
    					<a href="<%=request.getContextPath()%>/productDetail.do?productNum=+<%=list.get(i).getProductNum()%>">
      						<img src="<%=request.getContextPath()%>/resources/images/product/<%=list.get(i).getImage()%>" alt="...">
      					</a>
      						<span class="label label-primary tag">Primary</span>
      						<span class="label label-success tag">success</span>
      						<div class="col-lg-12 caption">        						
        						<h4 class="col-lg-12 "><br /><b class="font-title text-truncate"><%=list.get(i).getProductName() %></b></h4>        						
        						<h4 class=" col-lg-12 "><%=list.get(i).getProductPrice() %>원</h4>
        						<h5 class="col-lg-12 font-gray">리뷰 <span><%=list.get(i).getReviewCount() %></span></h5>  
        						<div class="col-lg-12 ">  
        							<%System.out.println(list.get(i).getProductTypeName()); %>
        							<%if(!list.get(i).getProductTypeName().equals("디저트")&&
        									!list.get(i).getProductTypeName().equals("메시지태그")&&
        									!list.get(i).getProductTypeName().equals("카드")) {%>
        							 <a class="col-lg-4 btn btn-default" role="button" onclick="addBasket('<%=list.get(i).getProductNum()%>');">장바구니</a> 
        							<a class="col-lg-7 col-lg-offset-1 btn btn-primary" role="button" 
        							onclick="location.href='<%=request.getContextPath()%>/productDetail.do?productNum=+<%=list.get(i).getProductNum()%>'">바로구매</a>
        							<%}else{ %>
        							 <a class="col-lg-12 btn btn-default" role="button" onclick="addBasket('<%=list.get(i).getProductNum()%>');">장바구니</a> 
        							
        							<%} %>
        							 	
        							
      							</div>	 
      						</div>
    					</div>
  					</div>
  					<%} %>
  					<%} %>
  					
				<!-- </div> -->
			
			</div>
			

			
		</div>
		
   		
   	   
	</div>
	
	<!--페이지 네이션  -->
	<%if(pi!=null){ %>
	<div class="container" style="text-align: center;">
		
		<div class="col-lg-12" style="float:none;">
			<ul class="pagination">
   				<li>
      				<a href="<%=request.getContextPath()%>/productList.do?event=<%=event%>&currPage=<%=pi.getStartPage() %>&category=<%=category %>&order=<%=order%>" aria-label="Previous">
        				<span aria-hidden="true">&laquo;</span>
      				</a>
    			</li>
    	
    	
    			<%if(pi.getCurrPage()<=pi.getStartPage()){ %>
    				<li class="disabled"><a >&lt;</a></li>
    			<%} else{ %>
    				<li><a href="<%=request.getContextPath()%>/productList.do?event=<%=event%>&currPage=<%=pi.getCurrPage()-1%>&category=<%=category %>&order=<%=order%>">&lt;</a></li>
    			<%} %>
    			
    				<%for(int i=pi.getStartPage();i<=pi.getEndPage();++i){ %>
    			
    					<%if(i==pi.getCurrPage()){ %>
    						<li class="active"><a><%=i%></a></li>
    					<%} else { %>
    						<li><a href="<%=request.getContextPath()%>/productList.do?event=<%=event%>&currPage=<%=i%>&category=<%=category %>&order=<%=order%>"><%=i%></a></li>
    				<%} %>
    			<%} %>
    			
    			<%if(pi.getCurrPage()>=pi.getMaxPage()){ %>
    				<li class="disabled"><a >&gt;</a></li>
    			<%}else{ %>
    				<li><a href="<%=request.getContextPath()%>/productList.do?event=<%=event%>&currPage=<%=pi.getCurrPage()+1%>&category=<%=category %>&order=<%=order%>">&gt;</a></li>
    			<% } %>
    			<li>
     				<a href="<%=request.getContextPath()%>/productList.do?event=<%=event%>&currPage=<%=pi.getEndPage()%>&category=<%=category %>&order=<%=order%>" aria-label="Next">
        				<span aria-hidden="true">&raquo;</span>
      				</a>
    			</li>
  			</ul>
		
		
		</div>
	
	</div>
   <%} %>
 
   
   

<%@include file="../common/footer.jsp"%>

<script>
	function addBasket(productNum){
		
		$.ajax({
			url:"addBasket.do",
			data:{pno:productNum},
			success:function(data){
				
				if(data>0)
					alert("장바구니에 성공적으로 추가!!");
				else if(data==-1)
					alert("로그인을 해주세요");
				
			},error:function(data){
				alert("장바구니에 추가 실패!!");
			}
		
		});
	
	}
		
</script>


</body>
</html>