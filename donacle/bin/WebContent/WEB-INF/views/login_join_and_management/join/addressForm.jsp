<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/login_join_and_management/common/header.jsp" %>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login_join_and_management/memberDetail.css">
<div class="container">
	<div class="detailForm">
	<h2>배송지 주소 관리</h2>
		<div class="detail">
		<table>
			<tr>
				<th>우편번호</th>
				<td>
					<%= loginMember.getAddress().getZipCode()%>
				</td>
			</tr> 
			<tr>
				<th>주소</th>
				<td>	
				<%= loginMember.getAddress().getAddress()%>
				</td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td>	
					<%= loginMember.getAddress().getDetailAddress()%>
			</tr>
			<tr>
				<td colspan ="2">
				<input type="button" class = "Btn" value="수정" onclick = "location.href='<%=request.getContextPath()%>/member/addressUpdate'" />
				<input type="button" class = "Btn"value="돌아가기" onclick = "location.href='<%=request.getContextPath()%>/'">
				</td>
			</tr>
		</table>
		</div>
	</div>
</div>
<form name = "memberDeleteFrm" action="<%= request.getContextPath() %>/member/memberDelete" method = "post">
				<input type="hidden" name="id" value ="<%=loginMember.getId() %>" />
				</form>
<%@include file = "/WEB-INF/views/login_join_and_management/common/footer.jsp" %>