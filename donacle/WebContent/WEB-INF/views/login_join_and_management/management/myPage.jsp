<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include
	file="/WEB-INF/views/homepage_introduce/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login_join_and_management/myPage.css">
<div class="container">
		<div class="myPage">
			<div class="content">
					<h3><%=loginMember.getName() %> 님</h3>
					<p>(<%=loginMember.getEmail() %>)</p>
					
					<table>
						<tr>
							<td>
								<h4>회원 정보</h4>
								<br/>
								
								<p class="info">회원님의 소중한 개인정보를 관리합니다.</p>
								
								<input type="button" class = "btn" value="회원정보 관리" onclick = "location.href='<%=request.getContextPath()%>/member/memberDetail'"/>
								
							</td>
							<td>
								<h4>배송지 정보</h4>
								<br />
								<%if(MemberService.MEMBER_KIND_CUSTOMER.equals(loginMember.getKind())) { %>
								<p class = "info">회원님께서 구매하신 물건을 받아보실 <br /> 배송지 주소를 관리합니다.</p>
								<input type="button" class = "btn" value="배송지 관리" onclick = "location.href='<%=request.getContextPath()%>/member/memberAddress'" />
								<%} else { %>
								<p class = "info">판매자 회원님께서는 <br /> 다른 메뉴 이용을 부탁드립니다. :)</p>
								<%} %>
							</td>
						</tr>
						<tr>
							<td>
								<h4>회원 탈퇴</h4>
								<br/>
								
								<p class="info">Donacle 이용을 그만 두시겠습니까? :(</p>
								
								<input type="button" class = "deleteBtn" value="회원 탈퇴" onclick = "deleteMember();"/>
								
							</td>
							<td>
								<h4>미구현 구역</h4>
							</td>
						</tr>
					</table>
			
			</div>
	</div>
</div>
<form name = "memberDeleteFrm" action="<%= request.getContextPath() %>/member/memberDelete" method = "post">
<input type="hidden" name="id" value ="<%=loginMember.getId() %>" />
</form>
<%@include file = "/WEB-INF/views/homepage_introduce/footer.jsp" %>