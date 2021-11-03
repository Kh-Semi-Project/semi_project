<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>donacle</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/homepage_introduce/introduce.css"/>
    <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
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
                        <ul><span><img src="https://i.ibb.co/vwMbtvG/door.png">로그인</span></ul>
                        <ul><span><img src="https://i.ibb.co/h8XQfKD/add-user-1.png">회원가입</span></ul>
                        <ul><span><img src="https://i.ibb.co/BygCMZw/cart.png">장바구니</span></ul>
                        <ul><span><img src="https://i.ibb.co/THQ11bq/user.png">마이페이지</span></ul>
                    </div>
                    <div id="category">
                        <li>PRODUCT</li>
                        <li>REVIEW</li>
                        <li>DONATION</li>
                    </div>
                </div>
            </div>
        </header>