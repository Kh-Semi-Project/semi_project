<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include
	file="/WEB-INF/views/login_join_and_management/common/header.jsp"%>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login_join_and_management/memberList.css">
<%
List<Member> list = (List<Member>) request.getAttribute("list");
%>
<div class="container">
	<div class="listForm">
		<h2>회원 리스트</h2>
		<div class="list">
		<table>
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>회원구분</th>
					<th>가입일</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Member member : list) {
				%>
				<tr>
					<td><%=member.getId()%></td>

					<td><%=member.getName()%></td>
					<td>
						<%
						if (MemberService.MEMBER_KIND_ADMIN.equals(member.getKind())) {
						%>
						관리자 <%
						} else if (MemberService.MEMBER_KIND_SELLER.equals(member.getKind())) {
						%>
						판매자 <%
						} else {
						%> 구매자 <%
						}
						%>
					</td>
					<td><%=member.getJoinDate() %></td>
					<td><input type="button" class="memberBtn" value="회원관리"
						onclick="location.href='<%=request.getContextPath()%>/admin/memberDetail?id=<%=member.getId()%>'" />
					</td>
				</tr>

				<%
				}
				%>


			</tbody>

		</table>
		</div>
		<div id="pagebar"><%=request.getAttribute("pagebar")%></div>
		</div>
		</div>
<%@include
	file="/WEB-INF/views/login_join_and_management/common/footer.jsp"%>