<%@page import="mvc.product_review.review.model.vo.BuyLog"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BuyLog> list = (List<BuyLog>) request.getAttribute("buyLogList");
%>
<!--<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/product_review/review.css?v=2" />
<script>
/**
* boardEnrollFrm 유효성 검사
*/
function boardValidate(e){
	const $title = $("[name=reviewTitle]");
	const $content = $("[name=reviewContent]");
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^.+$/.test($title.val())){
		alert("제목을 입력하세요.");
		return false;
	}
					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	// .(임의의 문자)에는 \n(개행문자)가 포함되지 않는다.
	if(!/^(.|\n)+$/.test($content.val())){
		alert("내용을 입력하세요.");
		return false;
	}
	return true;
}

$(() => {
	$(document.boardEnrollFrm).submit(boardValidate);
});
</script>
<section id="review-container">
	<h2>제품후기 | donacle의 제품후기를 남겨주세요 :) </h2>
	<div>
		<form name="reviewForm" action="<%=request.getContextPath() %>/review/reviewEnroll"  method="post" enctype="multipart/form-data">
			<table id="tbl-review-uploadpage">
			<tr>
				<th>제 목</th>
				<td><input class="inp-inner" type="text" name="reviewTitle" required></td>
			</tr>
			<tr>
				<th>주문 번호</th>
				<td>
					<select class="inp-inner" name="productBuyCode" required="required">
						<option disabled="disabled">후기를 남길구매 상품코드를 선택해 주세요.</option>
						<%for(BuyLog log :list){%>
							<option value="<%=log.getPurchaseBuyCode() %>"><%=log.getPurchaseBuyCode() %></option>
						<%}%>
					</select>
				</td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td><input class="inp-inner" type="file" name="upFile"></td>
			</tr>
			<tr> 
				<th></th>
				<td><textarea class="inp-inner text" rows="5" cols="40" name="reviewContent"></textarea></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="등록하기">
				</th>
			</tr>
		</table>
		</form>
	</div>
</section>
<!--<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>-->
