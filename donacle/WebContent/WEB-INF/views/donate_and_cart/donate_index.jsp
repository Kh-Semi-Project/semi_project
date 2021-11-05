<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mvc.donate_and_cart.donate.model.vo.Donate" %>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<%
	String msg2 = (String) request.getAttribute("msg");
	Member member = (Member) request.getAttribute("loginMember");
%>
<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %>
<title>Donate</title>
<style>

@font-face {
  font-family: 'Pretendard-ExtraLight';
  src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-ExtraLight.woff') format('woff');
  font-weight: 200;
  font-style: normal;
}

.subVeiewTitle {
	font-family: 'Pretendard-ExtraLight';
	text-align: center;
}

h3 {
	margin-top: 50px;
}
.donate-font {

	font-family: 'Pretendard-ExtraLight';
	text-align: right;
}
#money-video {
	width: 100%;
	height: 300px;
	margin-top: 100px;
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
<% if(member != null){ %>
<%
	List<Donate> donate = (List<Donate>) request.getAttribute("donate_user_info");
	int donateCount = (int) request.getAttribute("donate_count");
%>
<% 
	int price = 0;
	String name = "";
	for(Donate d : donate){ 
	price += d.getDonate_price();
	}
	name = member.getName();
%>	
<section id = "donate">
<div id="cart_title" style="background:#f7f3d8; text-align:center; width: 1300px; height:180px; padding-top:50px; margin-top:200px; ">
     	<h1 class="subVeiewTitle">후 원</h1>
     	
        <span class="subVeiewcontent">흩날리는 민들레 처럼 작은 후원이 모여 기적을 만듭니다.</span>
</div>
<% System.out.println(donateCount); %>
	<h1 class="donate-font"><%= name %> 님, 총 후원액</h1>
	<h4 class="donate-font"><%= price %>원</h4>
	<% if(price >= 500) { %>
	<div id="donate-pic" style="width: 100%; text-align: center;" ><img src="<%= request.getContextPath()%>/css/donate_and_cart/enough.png" alt="" width="1000px"/></div>
	<% } else { %>

	<video src="<%= request.getContextPath() %>/css/donate_and_cart/money2.mp4" id="money-video"></video>
	<br />
	<input type="submit" id="play" value="후원하기"/>

	<form action="<%= request.getContextPath() %>/donate/insert" name="DonateFrm" method="POST"></form>
	<% } %>
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
	});
	
	$(document).ready(function(){
		shake();
		
	});
	function shake(){
		$("#donate-pic").animate({'opacity':'1','margin-left':'40px'},500, function(){
			$("#donate-pic").animate({'opacity':'1','margin-left':'-40px'},500, function(){shake();})
		});
	}
</script>

<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>
<% } else { %>
<script>
		alert('<%= msg2 %>');
		location.href = "<%= request.getContextPath() %>/memberLogin";
</script>
<% } %>