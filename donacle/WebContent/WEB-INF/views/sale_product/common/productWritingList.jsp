<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@page import="java.util.List"%>
 <%@page import="mvc.sale_product.product.model.vo.ProductWriting"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/productList.css" />
<%
	String msg2 = (String) request.getAttribute("msg");
	Member member = (Member) session.getAttribute("loginMember");

	List<ProductWriting> list = (List<ProductWriting>) request.getAttribute("productWritinglist");
	List<ProductWriting> toplist = (List<ProductWriting>) request.getAttribute("productWritingtoplist");
%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<style>

		#pro_Counts {display : none;}
		
		#product-List-container {margin: auto; text-align:center; margin-top:280px;}
		
		.product_writings_row{float: left; width : 33%; padding-top : 10px;padding-bottom:10px; }
		
		.product_writings_row a{text-decoration:none; color : black;}
		
		div#pageBar{margin-top:10px; text-align:center; margin-top: 500px; bottom: 0; width: 100%; font-size:20px;}
		
		div#pageBar span.cPage{color: #0066ff; margin-right: 5px;}
		
		div#pageBar a{margin-right: 5px;}
		
		.top_product{
			background-color : #f6f6f6;
		}

</style>
<%if(member != null){ %>
<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %>
<%@ include file="/WEB-INF/views/sale_product/common/productListMenu.jsp" %>

	<section id = "product-List-container" style="height: auto;">
	<h2 style="padding-top:20px;">조회수가 높은 Top3 제품</h2>
		<!--조회수가 높은 제품 3개 -->
<% if(!toplist.isEmpty()){%>
	<div class = "top_product top_product" style="text-align:center; height:220px; margin:auto">
	<%for(ProductWriting toppw : toplist){ %>
	<div class = "product_writings_row" style="text-align:center;padding-top:20px;">
				<a href="<%= request.getContextPath() %>/sale_product/ProductWritingView?code=<%= toppw.getProduct_writing_code() %>&&pcode=<%= toppw.getProduct_code()%>">
					<img src = "<%= toppw.getProduct_img() %>" width="100px" height="120px"/><br/>
					<%= toppw.getId() %><br/>
					<%= toppw.getProduct_name() %><br/>
					<%= toppw.getProduct_price() %>원<br/>
				</a>
	</div>
	<%} %>
</div>
<%} %>

<% if(toplist.isEmpty()){%>
<div class = "product_writings_row top_product" style="width : 100%; padding-top : 10px; text-align:center; height:180px; padding-top:20px;">
	<h5>현재 판매하는 제품이 없습니다.</h5>
</div>
<%} %>
		<!-- 모든 리스트 -->
		<!-- 
			리스트로 불러와서 출력하기  
			사진/판매자이름/제품명 클릭시 productDetil로 이동
			클릭시 form에 정보 저장 후 이동 -->	
		<h3>모든 제품</h3>
<%for(ProductWriting pw : list){ %>
<div class = "product_writings_row" >
			<a href="<%= request.getContextPath() %>/sale_product/ProductWritingView?code=<%= pw.getProduct_writing_code()%>&&pcode=<%= pw.getProduct_code()%>">
				<img src = "<%= pw.getProduct_img() %>" width="60px" height="70px"/><br/>
				<%= pw.getId() %><br/>
				<%= pw.getProduct_name() %><br/>
				<%= pw.getProduct_price() %>원<br/>
			</a>
</div>
<%} %>
<% if(list.isEmpty()){%>
	<h5>현재 판매하는 제품이 없습니다.</h5>
<%} %>
		<div id='pageBar'><%= request.getAttribute("pagebar") %></div>
<% } else { %>
<script>
		alert('로그인 후 이용 가능합니다.');
		location.href='<%=request.getContextPath()%>/memberLogin';
</script>
<% } %>
	</section>
	
<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>