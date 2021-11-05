<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/homepage_introduce/header.jsp" %>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login_join_and_management/memberDetail.css">
<div class="container">
	<div class="detailForm">
	<h2>내 회원 정보</h2>
		<div class="detail">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<%= loginMember.getId()%>
				</td>
			</tr> 
			<tr>
				<th>이름</th>
				<td>	
				<%= loginMember.getName()%>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>	
					<%= loginMember.getEmail()%>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<%= MemberService.MEMBER_GENDER_MALE.equals(loginMember.getGender()) ? "남" : "여" %> 
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<%= loginMember.getBirthday()%>
				</td>
			</tr> 
			<tr>
				<th>회원구분</th>
				<td>
				<%=MemberService.MEMBER_KIND_ADMIN.equals(loginMember.getKind()) ? "관리자" 
						: MemberService.MEMBER_KIND_CUSTOMER.equals(loginMember.getKind()) ? "구매자"
						: "판매자"
					%>
				</td>
			</tr> 
			<tr>
				<td colspan ="2">
				<input type="button" class = "Btn" value="수정" onclick = "location.href='<%=request.getContextPath()%>/member/memberUpdate'" />
				<input type="button" class = "Btn"value="돌아가기" onclick = "location.href='<%=request.getContextPath()%>/member/myPage'">
				</td>
				
				
			
			</tr>
								
		
		</table>
		</div>
	</div>
</div>
<%@include file = "/WEB-INF/views/homepage_introduce/footer.jsp" %>