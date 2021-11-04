<%@page import="mvc.login_join_and_management.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/homepage_introduce/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/login_join_and_management/join.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/member.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<form name="memberJoinFrm" action="<%= request.getContextPath() %>/memberJoin" method="POST">
	<div class="container">
	<div class = "joinForm">
		<h2>회원가입</h2>
			<div class = "content">
			<h4>아이디</h4>
			<input type="text" placeholder="아이디" id="id" name="id" oninput = "checkId()"><br />
			</div>
			<div id="idMsg"></div>
			<div class = "content">
			<h4>비밀번호</h4>
				<input type="password" placeholder = "비밀번호" name="password" id="password" oninput = "checkPassword()" ><br>
			</div>
			<div id="passMsg"></div>
			<div class = "content">
			<h4>비밀번호 확인</h4>
					<input type="password" placeholder = "비밀번호 확인" id="checkPass" oninput = "checkPassword()" ><br>
			</div>  
			<div id="checkPassMsg"></div>
			<div class = "content">
			<h4>이름</h4>
				<input type="text"  placeholder = "이름" name="name" id="name" ><br>
			</div>
			<div id="nameMsg"></div>
			<div class = "content">
			<h4>이메일</h4>
					<input type="text" placeholder = "이메일" name="emailId" id="email" class = "email">
       				<input type="button" value="인증번호 발송" id="sendEmail"  />	
					<input type="password" placeholder = "인증번호를 입력해주세요." id="writeKey" style = "display:none;" />
					<input type="button" value="인증하기" id="sendKey" name="sendKey" style = "display:none;"/><br />
			</div>
			<div id="emailMsg"></div>
			<div class = "content">
			<h4>생년월일</h4>
				<input type="text" placeholder= "생년월일 (ex)19940803)"name="birthday" id="birthday" ><br />
			</div> 
			<div id="birthdayMsg"></div>
			<div class="content">
			<h4>성별</h4>
				<select name="gender" id="gender">
					<option value="M" selected>남자</option>
					<option value="F">여자</option>
				</select>
			</div>
			<div class="content">
				<h4>가입 구분</h4>
				<select name="kind" id="kind">
					<option value="<%=MemberService.MEMBER_KIND_CUSTOMER%>" selected>구매자</option>
					<option value="<%=MemberService.MEMBER_KIND_SELLER%>">판매자</option>
				</select>
			</div>
				
			<div class = "content" >
			<div class = "address">
			<h4>배송지 주소</h4>
					<input type="text" placeholder="우편번호" name="zipCode" id="zipCode" readonly>
					<input type="button" id = "findAddr" value="우편번호 찾기" onclick ="findAddress();"/>
					<input type="text" placeholder="주소" name="address" id="myAddress" readonly>
					<input type="text" placeholder="상세주소 (직접입력)" name="detailAddress" id="detailAddress">
					<input type="text" placeholder="부가주소" name="subAddress" id="subAddress" readonly>
			</div>
			</div>
			<div id="addressMsg"></div>
			<div class="content" id = "btnContent">
			<input type="submit" value="가입" class = "signupBtn" >
			<input type="button" value="취소" class = "cancelBtn" onclick = "location.href = '<%=request.getContextPath()%>/'"/>
			</div>
		</div>
</div>
	</form>
<%@include file = "/WEB-INF/views/login_join_and_management/common/footer.jsp" %>