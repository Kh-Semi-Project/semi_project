<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@page import="java.util.List"%>
 <%@page import="mvc.sale_product.product.model.vo.ProductWriting"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/productList.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/common.css"/>
<%@ include file="/WEB-INF/views/sale_product/common/productListMenu.jsp" %>
<%
	List<ProductWriting> list = (List<ProductWriting>) request.getAttribute("productWritinglist");
	List<ProductWriting> toplist = (List<ProductWriting>) request.getAttribute("productWritingtoplist");
%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<style>

		#pro_Counts {display : none;}
		
		#product-List-container {margin: auto; text-align:center;}
		
		.product_writings_row{float: left; width : 33%; padding-top : 10px; padding-bottom:10px;}
		
		.product_writings_row a{text-decoration:none; color : black;}
		
		div#pageBar{margin-top:10px; text-align:center; position: fixed; bottom: 0; width: 100%;}
		
		div#pageBar span.cPage{color: #0066ff; margin-right: 5px;}
		
		div#pageBar a{margin-right: 5px;}
		
		.top_product{
			background-color : #f6f6f6;
		}

</style>
	<section id = "product-List-container">
	<h2 style="padding-top:50px;">조회수가 높은 Top3 제품</h2>
		<!--조회수가 높은 제품 3개 -->
<%for(ProductWriting toppw : toplist){ %>
<div class = "product_writings_row top_product" >
			<a href="<%= request.getContextPath() %>/sale_product/ProductWritingView?code=<%= toppw.getProduct_writing_code() %>">
				<img src = "<%= toppw.getProduct_img() %>" width="100px" height="110px"/><br/>
				<%= toppw.getId() %><br/>
				<%= toppw.getProduct_name() %><br/>
				<%= toppw.getProduct_price() %>원<br/>
			</a>
</div>
<%} %>
		<!-- 모든 리스트 -->
		<!-- 
			리스트로 불러와서 출력하기  
			사진/판매자이름/제품명 클릭시 productDetil로 이동
			클릭시 form에 정보 저장 후 이동 -->	
		<h2 style="margin-top:230px;">모든 제품</h2>
<%for(ProductWriting pw : list){ %>
<div class = "product_writings_row" >
			<a href="<%= request.getContextPath() %>/sale_product/ProductWritingView?code=<%= pw.getProduct_writing_code() %>">
				<img src = "<%= pw.getProduct_img() %>" width="60px" height="70px"/><br/>
				<%= pw.getId() %><br/>
				<%= pw.getProduct_name() %><br/>
				<%= pw.getProduct_price() %>원<br/>
			</a>
</div>
<%} %>
		<div id='pageBar'><%= request.getAttribute("pagebar") %></div>


	</section>