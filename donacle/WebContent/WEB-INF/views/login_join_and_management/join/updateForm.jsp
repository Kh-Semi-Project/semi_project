<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/homepage_introduce/header.jsp" %>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login_join_and_management/updateMember.css">
	<form name="memberUpdateFrm" action="<%= request.getContextPath() %>/member/memberUpdate" method="POST">
	<div class="container">
		<div class="updateForm">
		<h2>회원 정보 수정</h2>
			<div class="content">
			<h4>아이디</h4>
				<input type="text" placeholder="아이디" name="id" id="id" value = "<%= loginMember.getId()%>" readonly>
			</div>
			<div class="content">
			<h4>이름</h4>
				<input type="text"  name="name" id="name" value = "<%= loginMember.getName()%>"><br>
			</div>
			<div class="content">
			<h4>이메일</h4>
				<input type="email" placeholder="abc@xyz.com" name="email" id="email" value = "<%= loginMember.getEmail()%>"><br>
			</div>
			<div class="content">
			<h4>성별</h4>
				<select name="gender" id="gender">
					<option value="M" <%=MemberService.MEMBER_GENDER_MALE.equals(loginMember.getGender()) ? "selected" : "" %>>남자</option>
					<option value="F" <%=MemberService.MEMBER_GENDER_FEMALE.equals(loginMember.getGender()) ? "selected" : "" %>>여자</option>
				</select>
			</div>
			<div class="content">
				<h4>생년월일</h4>
				<input type="text" placeholder= "생년월일 (ex)19940803)"name="birthday" id="birthday" value = "<%= loginMember.getBirthday()%>">
			</div>
			<div class="content">
				<h4>가입 구분</h4>
				<input type="text" name="kind" id="kind" value = "<%=MemberService.MEMBER_KIND_ADMIN.equals(loginMember.getKind()) ? "관리자" 
						: MemberService.MEMBER_KIND_CUSTOMER.equals(loginMember.getKind()) ? "구매자"
						: "판매자"
					%>" readonly/>
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