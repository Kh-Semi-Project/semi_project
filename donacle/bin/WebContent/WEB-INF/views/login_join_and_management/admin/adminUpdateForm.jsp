<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/login_join_and_management/common/header.jsp" %>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login_join_and_management/updateMember.css">
<%
	Member memberById = (Member) request.getAttribute("memberById");
%>
	<form name="adminMemberUpdateFrm" action="<%= request.getContextPath() %>/admin/memberUpdate" method="POST">
	<div class="container">
		<div class="updateForm">
		<h2><%= memberById.getName()%>님 정보 수정</h2>
			<div class="content">
			<h4>아이디</h4>
				<input type="text" placeholder="아이디" name="id" id="id" value = "<%= memberById.getId()%>" readonly>
			</div>
			<div class="content">
			<h4>이름</h4>
				<input type="text"  name="name" id="name" value = "<%= memberById.getName()%>"><br>
			</div>
			<div class="content">
			<h4>이메일</h4>
				<input type="email" placeholder="abc@xyz.com" name="email" id="email" value = "<%= memberById.getEmail()%>"><br>
			</div>
			<div class="content">
			<h4>성별</h4>
				<select name="gender" id="gender">
					<option value="M" <%=MemberService.MEMBER_GENDER_MALE.equals(memberById.getGender()) ? "selected" : "" %>>남자</option>
					<option value="F" <%=MemberService.MEMBER_GENDER_FEMALE.equals(memberById.getGender()) ? "selected" : "" %>>여자</option>
				</select>
			</div>
			<div class="content">
				<h4>생년월일</h4>
				<input type="text" placeholder= "생년월일 (ex)19940803)"name="birthday" id="birthday" value = "<%= memberById.getBirthday()%>">
			</div>
			<div class="content">
				<h4>가입 구분</h4>
				<input type="text" name="kind" id="kind" value = "<%=MemberService.MEMBER_KIND_ADMIN.equals(memberById.getKind()) ? "관리자" 
						: MemberService.MEMBER_KIND_CUSTOMER.equals(memberById.getKind()) ? "구매자"
						: "판매자"
					%>" readonly/>
			</div>
				<% if(MemberService.MEMBER_KIND_CUSTOMER.equals(memberById.getKind())) { %>
			<div class="content">
			<div id = "address">
			<h4>배송지 주소</h4>
				<input type="text" placeholder="우편번호" name="zipCode" id="zipCode" value = "<%=memberById.getAddress().getZipCode() %>"><br />
				<input type="text" placeholder="주소" name="address" id="address" value= "<%=memberById.getAddress().getAddress() %>"><br />
				<input type="text" placeholder="상세주소" name="detailAddress" id="detailAddress" value ="<%=memberById.getAddress().getDetailAddress() %>">
			</div>
			</div>
				 <% } %>
			<div class="content">
			<div class="btnContent">
				<input type="submit" class = "submitBtn"value="수정" >
				<input type="button" class = "cancelBtn" value="취소" onclick = "history.go(-1);">
			</div>
			</div>
			
		</div>
	</div>
	</form>

<%@include file = "/WEB-INF/views/login_join_and_management/common/footer.jsp" %>