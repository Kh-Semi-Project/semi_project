<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

	<section id = "product-buy-container">
	<h2>물건 구매하기</h2>
	<form
		name = "productBuyFrm"
		action = ""
		method = "post"
	>
		<table>
			<tr>
				<td colspan ="2">상품정보</td>
				<td>판매자</td>
				<td>배송비</td>
				<td>수량</td>
				<td>가격</td>
				<td>총금액</td>
			</tr>
			<tr>
				<td>메인사진</td>
				<td>제품이름</td>
				<td>판매자이름</td>
				<td>배송비</td>
				<td>수량</td>
				<td>삼품가격</td>
				<td>총금액</td>
			</tr>

			<tr>
				<td><input name = "order" type="submit" value="주문하기"/></td>
			</tr>
		</table>
	</form>
	</section>