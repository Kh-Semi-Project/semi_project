<%@page import="mvc.product_review.review.model.vo.BuyLog"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BuyLog> list = (List<BuyLog>) request.getAttribute("buyLogList");
%>
<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %>
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
	<div style= "padding: 200px 300px;">
		<h2>제품후기 | donacle의 제품후기를 남겨주세요 :) </h2>
		<form name="reviewForm" action="<%=request.getContextPath() %>/review/reviewEnroll"  method="post" enctype="multipart/form-data">
			<table id="tbl-review-uploadpage">
			<tr>
				<th style="text-align: center; background-color: #eee; width: 70px;">제 목</th>
				<td style="border: 1px solid #ccc; padding 5px;"><input  style="float: left; margin: 4px; width: 350px; height: 30px;" type="text" name="reviewTitle" required></td>
			</tr>
			<tr>
				<th style="text-align: center; background-color: #eee; width: 70px;">주문 번호</th>
				<td style="border: 1px solid #ccc; padding 5px; ">
					<select style="float: left; margin: 4px; width: 250px; height: 35px;" name="productBuyCode" required="required">
						<option disabled="disabled">후기를 남길구매 상품코드를 선택해 주세요.</option>
						<%for(BuyLog log :list){%>
							<option value="<%=log.getPurchaseBuyCode() %>"><%=log.getPurchaseBuyCode() %></option>
						<%}%>
					</select>
				</td>
			</tr>
			
			<tr>
				<th style="text-align: center; background-color: #eee; width: 70px;">첨부파일</th>
				<td style="border: 1px solid #ccc; padding 5px;"><input style="float: left; margin: 4px;" type="file" name="upFile"></td>
			</tr>
			<tr> 
				<th style="text-align: center; background-color: #eee; width: 70px;">내 용</th>
				<td style="border: 1px solid #ccc; padding 5px;"><textarea style="float: left; margin: 4px;  width: 805px; height: 163px;" rows="5" cols="40" name="reviewContent"></textarea></td>
			</tr>
			<tr>
				<th colspan="2">
					<input style= "margin-top: 15px; background:dimgrey; color: white; border-radius: 3px; height: 30px;" type="submit" value="등록하기">
				</th>
			</tr>
		</table>
		</form>
	</div>
</section>
<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>
