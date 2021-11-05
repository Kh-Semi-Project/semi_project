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
							<!-- 소비자 -->
							<!-- 구매한 제품 리스트 확인 -->
							<%if("C".equals(loginMember.getKind())) {%>
								<h4>주문 리스트</h4>
								<br/>
								<p class = "info">회원님께서 구매하신 물건 내역을 관리합니다.</p>
								<input type="button" class = "btn" value="구매 내역 확인" onclick = "location.href='<%=request.getContextPath()%>/sale_product/productBuyList?id=<%=loginMember.getId()%>'"/>
							
							<!-- 판매자 -->							
							<!-- 주문 리스트, 제품 리스트-->
							<%}else if("S".equals(loginMember.getKind())){ %>
								<h4>제품</h4>
								<br/>
								<p class = "info">판매자 회원님께서 올리신 <br>제품 주문 내역과 제품을 관리합니다.</p>
								<input type="button" class = "btn" value="주문 확인" onclick = "location.href='<%=request.getContextPath()%>/sale_product/productOrderList?id=<%=loginMember.getId()%>'"/>
								<input type="button" class = "btn" value="제품 목록" onclick = "location.href='<%=request.getContextPath()%>/sale_product/productList?id=<%=loginMember.getId()%>'"/>
								<br/>
								<input type="button" class = "btn" value="제품추가" onclick="location.href='<%= request.getContextPath() %>/sale_product/productAddpage';" style="margin-top:6px;">
	
							
							<!-- 관리자 -->
							<!-- 승인요청 리스트 확인 -->
							<%}else{ %>
								<h4>승인 요청</h4>
								<br/>
								<p class = "info">관리자 회원님께 요청온<br>승인 요청 글을 관리합니다.</p>
								<input type="button" class = "btn" value="승인 요청 목록" onclick = "location.href='<%=request.getContextPath()%>/sale_product/productWritingAdminCheckList'"/>
								
							<%} %>
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