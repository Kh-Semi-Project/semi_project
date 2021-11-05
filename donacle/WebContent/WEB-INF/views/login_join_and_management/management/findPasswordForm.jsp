<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include
	file="/WEB-INF/views/homepage_introduce/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login_join_and_management/findPass.css">
<form name="findPasswordFrm"  action="<%=request.getContextPath()%>/findPassword" method="post">
	<div class="container">
		<div class="findPassForm">
			<h2>비밀번호 찾기</h2>
			<p>아이디와 이메일을 입력해주세요.</p>
			<div class="content">
				<h4>아이디</h4>
				<input type="text" id="id" name="id" placeholder="아이디" />
			</div>
			<div class="content">
				<h4>이메일</h4>
				<input type="text" id="findPassEmail" name="findPassEmail" placeholder="이메일 주소" />
				<br>
				<input type="button" value="인증번호 발송" id = "findPassSendEmail" />
				<input type="password" placeholder = "인증번호를 입력해주세요."  id="findPassWriteKey" style = "display:none;" />
				<input type="button" value="인증하기" id="findPassSendKey" style = "display:none;"/><br />
			<div id="findPassEmailMsg"></div>
			</div>
			<div class="submit">
			<input type="submit" value="확인" />
			</div>
		</div>
	</div>
</form>
<%@include file = "/WEB-INF/views/homepage_introduce/footer.jsp" %>