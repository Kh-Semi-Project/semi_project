<%@page import="mvc.login_join_and_management.model.service.MemberService"%>
<%@page import="mvc.login_join_and_management.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
    	Member loginMember = (Member)session.getAttribute("loginMember");
    	String msg = (String) session.getAttribute("msg");
    	Member findMember = (Member) session.getAttribute("findMember");
    	String result = (String) request.getAttribute("result");
    	if(msg != null) session.removeAttribute("msg");

    %>
<!DOCTYPE html>
<html>
<head>
    <title>donacle</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/homepage_introduce/introduce.css"/>
    <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/member.js"></script>

    <script>
<% if(msg!=null) { %>
	// 사용자 메세지 전달
	alert("<%=msg%>");
<% } %>
</script>
</head>
<body>
    <div id="layout">
        <header>
            <div id="header-wrap">
                <div id="left-menu">
                    <a href="<%= request.getContextPath()%>"><img src="https://i.ibb.co/3Wxb9x5/donacle-1.jpg" alt=""></a>
                </div>
                <div id="right-menu">
                    <div id="sub-menu">
                    <%if(loginMember != null ) { %>
                        <% if(MemberService.MEMBER_KIND_ADMIN.equals(loginMember.getKind())) { %>
                        <ul><span><img src="https://i.ibb.co/vwMbtvG/door.png"><%=loginMember.getName() %>님</span></ul>
                        <ul><span onclick = "location.href='<%=request.getContextPath()%>/admin/memberList'"><img src="https://i.ibb.co/THQ11bq/user.png">회원관리</span></ul>
                        <ul><span onclick = "location.href='<%=request.getContextPath()%>/sale_product/productWritingAdminCheckList'"><img src="https://i.ibb.co/1Qmrw5Z/approve.png">승인요청관리</span></ul>
                        <ul><span onclick = "location.href='<%=request.getContextPath()%>/member/memberLogout'"><img src="https://i.ibb.co/h8XQfKD/add-user-1.png">로그아웃</span></ul>
                        <% }else if(MemberService.MEMBER_KIND_SELLER.equals(loginMember.getKind())){%>
                        <ul><span><img src="https://i.ibb.co/vwMbtvG/door.png"><%=loginMember.getName() %>님</span></ul>
                        <ul><span onclick = "location.href='<%=request.getContextPath()%>/member/myPage'"><img src="https://i.ibb.co/THQ11bq/user.png">마이페이지</span></ul>
                        <ul><span onclick = "location.href='<%=request.getContextPath()%>/member/memberLogout'"><img src="https://i.ibb.co/h8XQfKD/add-user-1.png">로그아웃</span></ul>	
                        <% }else { %>
                        <ul><span><img src="https://i.ibb.co/vwMbtvG/door.png"><%=loginMember.getName() %>님</span></ul>
                        <ul><span onclick = "location.href='<%=request.getContextPath()%>/CartList'"><img src="https://i.ibb.co/BygCMZw/cart.png">장바구니</span></ul>
                        <ul><span onclick = "location.href='<%=request.getContextPath()%>/member/myPage'"><img src="https://i.ibb.co/THQ11bq/user.png">마이페이지</span></ul>
                        <ul><span onclick = "location.href='<%=request.getContextPath()%>/member/memberLogout'"><img src="https://i.ibb.co/h8XQfKD/add-user-1.png">로그아웃</span></ul>
                       <% } %> 
                    <%} else { %>
                    	<ul><span onclick = "location.href='<%=request.getContextPath()%>/memberLogin'"><img src="https://i.ibb.co/vwMbtvG/door.png">로그인</span></ul>
                        <ul><span onclick = "location.href='<%=request.getContextPath()%>/memberJoin'"><img src="https://i.ibb.co/h8XQfKD/add-user-1.png">회원가입</span></ul>
                        <ul><span onclick = "location.href='<%=request.getContextPath()%>/CartList'"><img src="https://i.ibb.co/BygCMZw/cart.png">장바구니</span></ul>
                        <ul><span onclick = "location.href='<%=request.getContextPath()%>/member/myPage'"><img src="https://i.ibb.co/THQ11bq/user.png">마이페이지</span></ul>
                    <%} %> 
                    </div>
                    <div id="category">
                        <li><a href="<%= request.getContextPath() %>/sale_product/productwritingList?category=0">PRODUCT</a></li>
                        <li><a href="<%= request.getContextPath() %>/review/reviewList">REVIEW</a></li>
                        <li><a href="<%= request.getContextPath() %>/donate">DONATION</a></li>
                    </div>
                </div>
            </div>
        </header>