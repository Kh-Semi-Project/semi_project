<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

<section id="product-order-container">
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th colspan="2">제품정보</th> <!-- 제품 사진, 제품명 --> 
			<th>판매자</th>
			<th>연락처</th>
			<th>구매일</th>
			<th>수량</th>
			<th>총금액</th>
			<th>배송출발</th> <!-- 판매자가 배송을 시작했는지 확인 -->
			<th>배송완료여부</th> <!-- 사용자가 물건을 받았는지 확인 -->
		</tr>
	</table>
	
	
	
		<!-- 판매자가 배송을 시작했으면 버튼을 통해 클릭시 서버에 (판매자는 물건을 출발시켰다는) 정보를 전달,
			 사용자가 주문확인 페이지에서 확인 가능하게 하기 위함 -->
		<form 
			name = "productOrderFrm"
			action="" 
			method="POST"
		>
		<input type = "hidden" name = "tansCheck" />
	</form>
</section>
<script>
	/* 
		리스트 출력 후, 판매자가 배송출발 버튼을 클릭하면 서버로 정보 전달
		("[name=productOrderFrm]") */
</script>