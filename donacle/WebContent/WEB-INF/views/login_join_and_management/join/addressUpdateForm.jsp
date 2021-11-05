<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/homepage_introduce/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/login_join_and_management/updateAddress.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<form name="addressUpdateFrm" action="<%= request.getContextPath() %>/member/addressUpdate" method="POST">
	<input type="hidden" name="id" value = "<%=loginMember.getId()%>"/>
	<div class="container">
		<div class="updateForm">
		<h2>배송지 정보 수정</h2>
			<div class = "content" >
			<div class = "address">
			<h4>주소</h4>
					<input type="text" placeholder="우편번호" name="zipCode" id="zipCode" value ="<%= loginMember.getAddress().getZipCode()%>" readonly>
					<input type="button" id = "findAddr" value="우편번호 찾기" onclick ="findAddress();"/>
					<input type="text" placeholder="주소" name="address" id="myAddress" value ="<%= loginMember.getAddress().getAddress()%>" readonly>
					<input type="text" placeholder="상세주소 (직접입력)" name="detailAddress" id="detailAddress" value= "<%= loginMember.getAddress().getDetailAddress()%>" >
					<input type="text" placeholder="부가주소" name="subAddress" id="subAddress" readonly>
			</div>
			</div>
			<div class="content">
			<div class="btnContent">
				<input type="submit" class = "submitBtn"value="수정" >
				<input type="button" class = "cancelBtn" value="취소" onclick = "history.go(-1);">
			</div>
			</div>
		</div>
	</div>
	</form>

<%@include file = "/WEB-INF/views/homepage_introduce/footer.jsp" %>