<%@page import="java.util.List"%>
<%@page import="mvc.donate_and_cart.cart.model.vo.CartList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<CartList> cl = (List<CartList>) request.getAttribute("cartList"); 
%>
<title>구매창</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
