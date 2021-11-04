<%@page import="mvc.login_join_and_management.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/homepage_introduce/header.jsp" %>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login_join_and_management/updatePass.css">
<form name = "updatePasswordFrm" action="<%=request.getContextPath()%>/updatePassword" method = "post">
<input type="hidden" id = "id" name="id" value = "<%= findMember.getId()%>"/>
<div class="container">
	<div class="updatePassForm">
		<h2>비밀번호 변경</h2>
		<p>회원정보가 확인되었습니다. 비밀번호 변경을 실시합니다.</p>
		<div class="content">
		<h4>새 비밀번호</h4>
		<input type="password" id = "newPass" name = "newPass" placeholder = "새 비밀번호" oninput = "checkNewPassword()"/>
		</div>
		<div id="newPassMsg"></div>
		<div class="content">
		<h4>비밀번호 확인</h4>
		<input type="password"  id = "checkNewPass" placeholder = "비밀번호 확인" oninput = "checkNewPassword()"/>	
		</div>
		<div id="checkNewPassMsg"></div>
		<div class="submit">
		<input type="submit" value="확인" />			
		</div>
	</div>
</div>
</form>
<%@include file = "/WEB-INF/views/login_join_and_management/common/footer.jsp" %>