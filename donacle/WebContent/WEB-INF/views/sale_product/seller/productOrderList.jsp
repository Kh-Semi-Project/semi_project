<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mvc.sale_product.product.model.vo.ProductBuy"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/common.css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<%
	List<ProductBuy> pbs = (List<ProductBuy>) request.getAttribute("ProductOrderList");
%>
<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %>
<style>
	input[name=productShppingStatusBtn]:hover{
		background-color:black;
		color:white;
	}
</style>
<section id="product-order-container" style="margin-top:200px;">
	<table id="product-order" style="margin-bottom:50px;">
		<tr>
			<th>번호</th>
			<th colspan="2">제품정보</th> <!-- 제품 사진, 제품명 --> 
			<th>구매자</th>
			<th>총금액</th>
			<th>구매일</th>
			<th>배송출발</th> <!-- 판매자가 배송을 시작했는지 확인 -->
			<th>배송완료여부</th> <!-- 사용자가 물건을 받았는지 확인 -->
		</tr>
<%
int index = 0;
for(ProductBuy pb : pbs){ 
index++;
%>
		<tr>
			<td colspan="9" class="tdclass">donacle<td>
		</tr>
		<tr>
			<td rowspan="2"><%=index %></td>
			<td rowspan ="2"><img src="<%=pb.getProduct_img() %>" width="120px" height="150px"/></td>
			<td><%=pb.getProduct_name() %></td>
			<td rowspan="2"><%=pb.getId()%></td>
			<td rowspan="2"><%=pb.getPrice_sum()%>원</td>
			<td rowspan="2"><%=pb.getProduct_buy_date()%></td>
			<td rowspan="2">
<%if(pb.getProduct_shipping_status().equals("n")){%>
				<input type ="button" name = "productShppingStatusBtn" id = "<%=index %>" value="배송"/>
<%} else{%>
				배송 완료
<%} %>
</td>
			<td rowspan="2"><%=pb.getProduct_receipt_yn().equals("n") == true ? "<b>미수령</b>" : "수령"%>
						<form 
							name = "productShppingStatusFrm<%=index %>"
							action="<%= request.getContextPath() %>/sale_product/productShppingStatus"
							method="POST"
						>
							<input type = "hidden" name = "product_buy_code" value="<%=pb.getProduct_buy_code() %>"/>
							<input type = "hidden" name = "product_shipping_status<%=index %>" value="<%=pb.getProduct_shipping_status() %>"/>
						</form>
			</td>
		</tr>
		<tr>
			<td><%=pb.getProduct_buy_count()%>개</td>
		</tr>
<%}%>
<%if(pbs.isEmpty()){ %>
<tr><td colspan ="9"><img src="<%= request.getContextPath() %>/css/sale_product/orderlist.png" alt="" width="500px;"/></td></tr>
<%} %>
	</table>
	
	
	
		<!-- 판매자가 배송을 시작했으면 버튼을 통해 클릭시 서버에 (판매자는 물건을 출발시켰다는) 정보를 전달,
			 사용자가 주문확인 페이지에서 확인 가능하게 하기 위함 -->
<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>
</section>
<script>
	/* 
		리스트 출력 후, 판매자가 배송출발 버튼을 클릭하면 서버로 정보 전달
		("[name=productOrderFrm]") */
		 $("[name=productShppingStatusBtn]").click(function(){
				const index = $(this).attr('id');				
				const name = "[name = productShppingStatusFrm"+index+"]";
				if(confirm("해당 기능은 수정할 수 없습니다.\n정보를 변경하시겠습니까?") == true){
					$(name).submit();
				}

		 });
</script>