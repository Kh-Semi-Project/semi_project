<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

	<section id = "product-detail-container">
	<h2>물건 구매</h2>
	<form
		name = "productBuyFrm"
		action = ""
		method = "post"
	>
		<table>
			<tr>
				<td rowspan ="6">사진</td>
				<th>판매자</th>
				<td>: 판매자이름</td>
			</tr>
			<tr>
				<th>제품</th>
				<td>: 제품이름</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>: 1000</td>
			</tr>
			<tr>
				<th>배송정보</th>
				<td>: 3000원</td>
			</tr>
			<tr>
				<th>개수</th>
				<td>: 선택 select</td>
			</tr>
			<tr>
				<td><input name = "cart" type="button" value="장바구니"/></td>
				<td><input name = "order" type="submit" value="주문하기"/></td>
			</tr>
		</table>
	</form>
	</section>