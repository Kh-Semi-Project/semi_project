<%@page import="mvc.product_review.review.model.vo.Review"%>
<%@page import="mvc.product_review.review.model.vo.BuyLog"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Review review = (Review) request.getAttribute("review");
%>
<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/product_review/review.css" />
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
		<form name="reviewForm" action="<%=request.getContextPath() %>/review/reviewUpdate"  method="post" enctype="multipart/form-data">
			<input type="hidden" name="reviewNo" value="<%=review.getReviewNo()%>"/>
			<table id="tbl-review-uploadpage">
				<tr>
					<th>제 목</th>
					<td><input class="inp-inner" type="text" name="reviewTitle" required value="<%=review.getReviewTitle() %>"></td>
				</tr>
				<tr>
					<th>제 목</th>
					<td>
						<input class="inp-inner" name="productBuyCode" required="required" value="<%=review.getProductBuyCode() %>" />
					</td>
				</tr>
				
				<tr>
					<th>첨부파일</th>
					<td>
						<%if(review.getAttach() != null && review.getAttach().getOriginalFilename() != null){%>
							<span><%=review.getAttach().getOriginalFilename() %></span>
						<%}%>
						<label for="fileChk">기존 파일 삭제</label><input id="fileChk" type="checkbox" name="originDelete" /> 
						<input class="inp-inner" type="file" name="upFile">
						<input type="hidden" name="attachNo" value="<%=review.getAttach() != null ? review.getAttach().getAttachNo() : 0 %>" />
					</td>
				</tr>
				<tr>
					<th></th>
					<td><textarea class="inp-inner" rows="5" cols="40" name="reviewContent" ><%=review.getReviewContent() %></textarea></td>
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
<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>