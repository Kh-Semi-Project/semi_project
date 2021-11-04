<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include
	file="/WEB-INF/views/homepage_introduce/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login_join_and_management/findIdView.css">
<div class="container">
		<div class="findIdView">
			<h2>아이디 찾기</h2>
			<div class="content">
				<%
				if (!"".equals(result)) {
				%>
				<p>회원님의 아이디는 [ <span class = "idView"><%=result%></span> ] 입니다.</p>
				<div class="findIdNav">
				<nav class="nav">
					<a href="<%=request.getContextPath()%>/memberLogin">로그인</a> | 
					<a href="<%=request.getContextPath()%>/findPassword">비밀번호 찾기</a> | 
					<a href="<%=request.getContextPath()%>/">처음 화면으로</a>
				</nav>
				</div>
				
				<%
				} else {
				%>
				<p>회원으로 등록된 아이디가 없습니다.</p>
				<div class="findIdNav">
				<nav class="nav">
					<a href="<%=request.getContextPath()%>/memberJoin">회원가입</a> | 
					<a href="<%=request.getContextPath()%>/">처음으로</a>
				</nav>
				<%
				}
				%>
			</div>
		</div>
	</div>
</div>
<%@include
	file="/WEB-INF/views/login_join_and_management/common/footer.jsp"%>