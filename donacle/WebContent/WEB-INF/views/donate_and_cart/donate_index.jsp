<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mvc.donate_and_cart.donate.model.vo.Donate" %>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<% 
	List<Donate> donate = (List<Donate>) request.getAttribute("donate_user_info");
	int donateCount = (int) request.getAttribute("donate_count");
%>
<title>Donate</title>
<style>

@font-face {
  font-family: 'Pretendard-ExtraLight';
  src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-ExtraLight.woff') format('woff');
  font-weight: 200;
  font-style: normal;
}

a {
	font-size: 30px;
	text-decoration: none;
	color: black;
}
h1 {
	font-family: 'Pretendard-ExtraLight';
	text-align: center;
}

h3, h4 {
	background-color: #ececec;
	font-family: 'Pretendard-ExtraLight';
	text-align: right;
}
#money-video {
	width: 100%;
	height: 300px;
}
#donate {
	margin: auto;
}
#play {
	display: block;
	margin: auto;
	margin-top: 20px;
	padding: 5px;
	background-color: #1c1e2d;
	color: white;
}
</style>
<% 
	int price = 0;
	String name = "";
	for(Donate d : donate){ 
	price += d.getDonate_price();
	name = d.getName();
	}
%>	
<section id = "donate">
	<a href="<%= request.getContextPath() %>">Donacle</a>
	<hr />
	<h1>후원</h1>
	<h3><%= name %> 님, 총 후원액</h3>
	<h4><%= price %>원</h4>
	<video src="<%= request.getContextPath() %>/css/donate_and_cart/money2.mp4" id="money-video"></video>
	<br />
	<input type="submit" id="play" value="후원하기"/>
	<form action="<%= request.getContextPath() %>/donate/insert" name="DonateFrm" method="POST"></form>
</section>
<script>
	$("#play").on('click', function(){
		document.getElementById("money-video").play();
		if(<%= donateCount %> >= 5){
			alert("하루 후원 가능 횟수를 초과하였습니다")
		}
		else {
			$("[name=DonateFrm]").submit();
		}
	})
</script>