<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mvc.donate_and_cart.donate.model.vo.Donate" %>
<% 
	List<Donate> donate = (List<Donate>) request.getAttribute("donate_info");
%>
<title>myDonateInfo</title>
<table>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>후원 금액</th>
					<th>후원 날짜</th>
					<th>후원 방식</th>
				</tr>
<% 
	int price = 0;
	int index = 1;
	for(Donate d : donate){
		price += d.getDonate_price();
%>
				<tr>
					<td><%= index++ %></td>
					<td><%= d.getName() %></td>
					<td><%= d.getDonate_price() %></td>
					<td><%= d.getDonate_time() %></td>
					<td><%= d.getHow_donate().equals("C") ? "클릭 후원" : "구매 후원" %></td>				
				</tr>

<% 
	}
%>	
						
				<tr>
					<th id="total-donate-price">총 후원금액: <%= price %>원</th>
				</tr>
			</table>