<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/login_join_and_management/common/header.jsp" %>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login_join_and_management/memberDetail.css">
<%
	Member memberById = (Member) request.getAttribute("memberById");
%>
<div class="container">
	<div class="detailForm">
	<h2><%=memberById.getName() %>님 회원정보</h2>
		<div class="detail">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<%= memberById.getId()%>
				</td>
			</tr> 
			<tr>
				<th>이름</th>
				<td>	
				<%= memberById.getName()%>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>	
					<%= memberById.getEmail()%>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<%= MemberService.MEMBER_GENDER_MALE.equals(memberById.getGender()) ? "남" : "여" %> 
				</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>	
				<%= memberById.getBirthday()%>
				</td>
			</tr> 
			<tr>
				<th>회원구분</th>
				<td>
				<%=MemberService.MEMBER_KIND_ADMIN.equals(memberById.getKind()) ? "관리자" 
						: MemberService.MEMBER_KIND_CUSTOMER.equals(memberById.getKind()) ? "구매자"
						: "판매자"
					%>
				</td>
			</tr> 
			
			<%if(memberById.getAddress() != null) { %>
			<tr>
				<th>배송지 주소</th>
				<td>	
					<%=memberById.getAddress().getZipCode() %><br>
					<%=memberById.getAddress().getAddress() %><br>
					<%=memberById.getAddress().getDetailAddress() %>
				</td>
			</tr>
			<% } %>
			<tr>
				<td colspan ="2">
				<input type="button" class = "Btn" value="수정" onclick = "location.href='<%=request.getContextPath()%>/admin/memberUpdate?id=<%=memberById.getId() %>'" />
				<input type="button" class = "Btn" value="삭제" onclick = "adminMemberDelete();" />
				<input type="button" class = "Btn"value="목록으로" onclick = "location.href = '<%=request.getContextPath()%>/admin/memberList'">
				</td>
				
				
			
			</tr>
								
		
		</table>
		</div>
	</div>
</div>
<form name = "adminMemberDeleteFrm" action="<%= request.getContextPath() %>/admin/memberDelete" method = "post">
				<input type="hidden" name="id" value ="<%=memberById.getId() %>" />
				</form>
<%@include file = "/WEB-INF/views/login_join_and_management/common/footer.jsp" %>