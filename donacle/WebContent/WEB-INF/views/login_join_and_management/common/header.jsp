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
<meta charset="UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/member.js"></script>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login_join_and_management/login.css" /> --%>
<meta charset="UTF-8">
<script>
<% if(msg!=null) { %>
	// 사용자 메세지 전달
	alert("<%=msg%>");
<% } %>
</script>
<title>Insert title here</title>
</head>
<body>
