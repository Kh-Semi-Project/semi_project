<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include
	file="/WEB-INF/views/homepage_introduce/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login_join_and_management/findId.css">
<form name="findIdFrm" action="<%=request.getContextPath()%>/findId"
	method="post">
	<div class="container">
		<div class="findIdForm">
			<h2>아이디 찾기</h2>
			<p>회원 가입 시 입력한 이름과 이메일을 입력해주세요.</p>
			<div class="content">
				<h4>이름</h4>
				<input type="text" id="name" name="name" placeholder="이름" />
			</div>
			<div class="content">
				<h4>이메일</h4>
				<input type="text" id="email" name="email" placeholder="이메일 주소" />
			</div>
			<div class="submit">
			<input type="submit" value="확인" />
			</div>
		</div>
	</div>
</form>
<%@include
	file="/WEB-INF/views/login_join_and_management/common/footer.jsp"%>