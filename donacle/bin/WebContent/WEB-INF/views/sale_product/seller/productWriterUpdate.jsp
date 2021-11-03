<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

	<section id = "product-writing_update-container">
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
				<th>제품선택</th>
				<td>: 제품리스트</td> <!-- 
										해당 판매자가 올린 제품 리스트 중, 글작성이 안된 제품을 라디오 박스로 출력
										상품을 클릭???
										제품 추가하기 버튼 추가, 해당 버튼 클릭시 제품 추가 사이트로 이동
									-->
			</tr>
			<tr>
				<td><input name = "update" type="submit" value="수정하기"/></td>
			</tr>
		</table>
	</form>
	</section>