<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/homepage_introduce/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/login_join_and_management/login.css">
<form name="loginFrm" action="<%=request.getContextPath()%>/memberLogin" method="POST">
	<div class="container">
		<div class="loginForm">
			<h2>LOGIN</h2>
			<div class="content">
				<h4>ID</h4>
				<input type="text" name="id" id="id" placeholder="아이디">
			</div>
			<div class="content">
				<h4>Password</h4>
				<input type="password" name="password" id="password" placeholder="비밀번호">
			</div>
			<div class="loginMsg" id="loginMsg"></div>
			<div class="loginNav">
				<nav class="nav">
					<a href="<%=request.getContextPath()%>/memberJoin">회원가입</a> | 
					<a href="<%=request.getContextPath()%>/findId">아이디 찾기</a> |
					<a href="<%=request.getContextPath()%>/findPassword">비밀번호 찾기</a>
				</nav>
			</div>
			<div class="submit">
				<input type="submit" value="로그인">
			</div>
		</div>
	</div>
</form>
<%@include file = "/WEB-INF/views/homepage_introduce/footer.jsp" %>