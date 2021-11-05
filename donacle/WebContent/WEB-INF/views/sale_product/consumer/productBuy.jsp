<%@page import="mvc.login_join_and_management.model.vo.Address"%>
<%@page import="mvc.login_join_and_management.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mvc.sale_product.product.model.vo.ProductBuy"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/common.css"/>
<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %>
<%@ include file="/WEB-INF/views/sale_product/common/productListMenu.jsp" %>
<%
	Address address = (Address) request.getAttribute("address");
	ProductBuy pb = (ProductBuy) request.getAttribute("ProductBuyInfo");
	Member member = (Member) session.getAttribute("loginMember");	
	
%>
<%if(member != null){ %>
	<style>
	body {
		text-align : center;
	}
	table {
		margin : auto;
		width : 75%;
		border-spacing: 0px;
	}
	table tr td {
		text-align : center;
	}
	table tr th {
		background-color : #e5d1b4;
		padding : 5px;
	}
	input[name=orderBtn]{
		width : 100px;
	}
	input[name=orderBtn]:hover{
		background-color : black;
		color : white;
	}
	</style>
	<section id = "product-buy-container">
	<h2 style="margin-top:300px;">물건 구매하기</h2>
	<form
		name = "productBuyFrm"
		action = "<%= request.getContextPath() %>/sale_product/productBuy"
		method = "post"
	>
		<table>
			<tr>
				<th colspan = "2">상품정보</th>
				<th rowspan = "2">판매자</th>
				<th rowspan = "2">배송비</th>
				<th rowspan = "2">수량</th>
				<th colspan ="2">가격</th>
				<th rowspan = "2">총금액</th>
			</tr>
			<tr>
				<th>메인사진</th>
				<th>제품이름</th>
				<th>제품 가격</th>
				<th>후원 금액</th>
			</tr>
			<tr>
				<td><img src="<%=pb.getProduct_img() %>" width="100px" height="150px"/></td>
				<td><%=pb.getProduct_name() %></td>
				<td><%=pb.getId() %></td>
				<td><%=pb.getShipping_fee() %>원</td>
				<td><%=pb.getProduct_buy_count() %>개</td>
				<td><%=pb.getProduct_price() %>원</td> <!-- 제품 가격 -->
				<td><%=pb.getProduct_donate_price() %>원</td> <!-- 후원되는 금액 -->
				<td><%= pb.getPrice_sum()%>원</td>
			</tr>
			<tr>
				<th colspan ="8"><input name = "orderBtn" type="button" value="주문하기"/></th>
			</tr>
		</table>
		<input type = "hidden" name = "buy_id" value = "<%=member.getId() %>">
		<input type = "hidden" name = "product_code" value = "<%=pb.getProduct_code() %>">
		<input type = "hidden" name = "shipping_fee" value = "<%=pb.getShipping_fee() %>">
		<input type = "hidden" name = "product_buy_count" value = "<%=pb.getProduct_buy_count() %>">
		<input type = "hidden" name = "product_buy_price" value = "<%=pb.getProduct_buy_price() %>">
		<input type = "hidden" name = "product_donate_price" value = "<%=pb.getProduct_donate_price() %>">
		<input type = "hidden" name = "price_sum" value = "<%= pb.getPrice_sum()%>">

	</form>
	<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>
	</section>
<script>
//order
$("[name=orderBtn]").on('click',function(){
    var IMP = window.IMP; 
    IMP.init('imp06098389'); 
    IMP.request_pay({
    	pg : "kakaopay", 
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : '<%=pb.getProduct_name() %>결제',
        amount : <%=pb.getProduct_buy_count()%>,
        buyer_email : '<%=member.getEmail()%>',
        buyer_name : '<%=member.getName()%>',
        buyer_tel : '01062860083',
        buyer_addr : '<%=address.getAddress() + address.getDetailAddress()%>',
        buyer_postcode : '<%=address.getZipCode()%>',
    }, function(rsp) {
        if ( rsp.success ) {
            var msg = '결제가 완료되었습니다.';
            $("[name=productBuyFrm]").submit();
        } else {
            var msg = '결제에 실패하였습니다.';
            rsp.error_msg;
            
        }
    });
    return false;
});
</script>
<%}else{%>
	<script>
	alert('로그인 후 이용 가능합니다.');
	location.href='<%=request.getContextPath()%>/memberLogin';
	</script>
<%}%>