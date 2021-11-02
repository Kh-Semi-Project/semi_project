<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mvc.sale_product.product.model.vo.ProductWriting"%>
 
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/common.css"/>
  
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<%
	List<ProductWriting> pwlist = (List<ProductWriting>) request.getAttribute("ProductWritingAdminList");
%> 

<style>
body{
	text-align  :center;
}

input[name=AdminCheckBtn]{
	width : 100%;
	height : 100%;
}

table tr td{
	text-align : left;
}
</style>
<h2>승인 요청 리스트 확인</h2>
<section id="product-order-container">
	<table id="tbl-board">
		<tr>
			<th colspan="3">제품정보</th> <!-- 제품 사진, 제품명 클릭시 해당 판매 글 페이지로 이동 -->
			<th>승인</th>
			<!-- 전체 선택 추가하기 -->
		</tr>

<!-- 사용자가 구매했던 리스트 출력 -->
<%
int index = 0;
for(ProductWriting pw : pwlist){
index++;
%>
		<tr>
			<td colspan="6" class="tdclass">donacle<td>
		</tr>
			<tr>
				<td rowspan="6"><img src="<%= pw.getProduct_img()%>" width="120px" height="150px"></td>
				<td>판매자 이름</td>
				<td>: <%= pw.getId()%></td>
				<td rowspan="6"><input type="button" name = "AdminCheckBtn" id = "<%= index%>" value="승인"/></td>
			</tr>
			<tr>
				<td>제품 이름</td>
				<td>: <%= pw.getProduct_name()%></td>
			</tr>
			<tr>
				<td>제품 내용</td>
				<td>: <%= pw.getProduct_content()%></td>
			</tr>
			<tr>
				<td>판매 가격</td>
				<td>: <%= pw.getProduct_price()%>원</td>
			</tr>
			<tr>
				<td>배송비</td>
				<td>: <%= pw.getShipping_fee()%>원</td>
			</tr>
			<tr>
				<td>판매 수량</td>
				<td>: <%= pw.getProduct_count()%>개</td>
				<td style = "display : none;">
					<form 
						name = "productupdateFrm<%=index %>"
						action="<%= request.getContextPath() %>/sale_product/productWritingAdminCheckUpdate" 
						method="POST"
					>
						<input type = "hidden" name = "product_writing_code" value="<%= pw.getProduct_writing_code() %>"/>
					</form>
				</td>
			</tr>
<%} %>
	</table>
</section>
<script>

	/* 
		
	*/
	 $("[name=AdminCheckBtn]").click(function(){
		const index = $(this).attr('id');
		const name = "[name=productupdateFrm" +index+"]";
		$(name).submit();
	 });
</script>