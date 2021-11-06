import="mvc.login_join_and_management.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@page import="mvc.donate_and_cart.cart.model.vo.CartList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg2 = (String) request.getAttribute("msg");
	Member member = (Member) request.getAttribute("loginMember");
	List<CartList> cl = (List<CartList>) request.getAttribute("cartList");
%>
<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %>
<title>Cart</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<style>
@font-face {
  font-family: 'Pretendard-ExtraLight';
  src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-ExtraLight.woff') format('woff');
  font-weight: 200;
  font-style: normal;
}
hr {
	color: #ECECEC;
}
h1 {
	font-family: 'Pretendard-ExtraLight';
	text-align: center;
}
table {
	border-collapse: collapse;
	margin: auto;
	margin-top: 100px;
	border: 1px solid black;
}
th, td {
	font-family: 'Pretendard-ExtraLight';
	border-bottom: 1px solid black;
}
th {
	border-bottom: #ECECEC;
	background-color: #F8F4F3;
	padding: 5px;
}
td {
	padding: 15px;
	border-bottom: #ECECEC;
	font-size: 20px;
	text-align: center;
}
#cart {
	margin: auto;
}
#check-all {
	margin-right: 5px;
}
#total-price {
	background-color: #F7F7F7;
	text-align: right;
}
#shopping-continue {
	background-color: white;
	border: 1px solid blue;
	color : blue;
	margin-right: 10px;
}
#order {
	padding: 5px;
	background-color: #1C1E2D;
	color: white;
}
.title-l {
	padding: 5px;
	height: 50px;
	text-align: left;
	border-top: 1px solid #ECECEC;
	border-bottom: 1px solid #ECECEC;
}
#shipping-price {
	height: 100px;
}
</style>
<%if(member != null){ %>
<section id = "cart" <%if(cl.size()>5){%>style="height: auto;<%}%>">
<div id="cart_title" style="background:#f7f3d8; text-align:center; width: 1300px; height:180px; padding-top:50px; margin-top:200px; ">
     	<h1 class="subVeiewTitle">장 바 구 니</h1>
     	
        <span class="subVeiewcontent">여러분의 구매는 누군가에게 큰 힘이 됩니다. :D</span>
</div>
			<table>
				<tr>
					<th width="100" ><input type="checkbox" id="check-all"/></th>
					<th colspan="2" width="400">상품 정보</th>
					<th width="250">상품 금액</th>
					<th width="100">상품 갯수</th>
					<th width="150">배송비</th>
					<th width="110">장바구니 삭제</th>
					<th width="110">주문하기</th>
				</tr>
<%
	int price = 0;
	for(CartList c : cl){
	price += (c.getProduct_price() * c.getProduct_cart_count()) + c.getShipping_fee();
%>
				<tr>
					<td colspan="8" class="title-l"><span class="title-left">donacle</span></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="product" id="<%= (c.getProduct_price() * c.getProduct_cart_count()) + c.getShipping_fee() %>"/></td>
					<td><img src="<%= c.getProduct_img() %>" alt="" width="100px" height="100px" /></td>
					<td><%= c.getProduct_name() %></td>
					<td><%= c.getProduct_price() %>원</td>
					<td><%= c.getProduct_cart_count() %>개</td>
					<td id="shipping-price"><%= c.getShipping_fee() %>원</td>
					<td><input type="button" value="삭제하기" name="delete-btn" id = <%= c.getCartList_no() %> /></td>
					<td><input type="button" value="주문하기" name="buy-btn" id = "cartList<%= c.getCartList_no()%>" /></td>
				</tr>
				<tr style="display:none;"><td>
					<form
						id="cartList<%= c.getCartList_no() %>"
						name="cartList<%= c.getCartList_no() %>"
						action="<%= request.getContextPath() %>/CartList/delete"
						method="POST">
						<input type="hidden" name="cartListNo" id ="cartListNo" value="<%= c.getCartList_no() %>"/>
						<input type="hidden" name="product_code" id ="product_code" value="<%= c.getProduct_code() %>"/>
						<input type="hidden" name="product_buy_count" id ="product_buy_count" value="<%= c.getProduct_cart_count() %>"/>
						<input type="hidden" name="product_buy_price" id ="product_buy_price" value="<%=c.getProduct_price()%>"/>
						<input type="hidden" name="seller_id" id ="seller_id" value="<%= c.getId() %>"/>
						<input type="hidden" name="product_donate_price" id ="product_donate_price" value="<%= (int)(c.getProduct_cart_count() * c.getProduct_price() * 0.1) %>"/>
						<input type="hidden" name="sum_price" id ="sum_price" value="<%= c.getProduct_cart_count() * c.getProduct_price() %>"/>
					</form>
				</td></tr>
							
<% }%>
<% if(cl.isEmpty()) { %>
				<tr>
					<td colspan="8"><img src="<%= request.getContextPath() %>/css/donate_and_cart/tung.png" alt="" width="500px;"/></td>
				</tr>
<% } %>
				<tr>
					<td style="display:none;"><input type="hidden" name = "price_sum_input" value="<%=price%>"/></td>
					<th colspan="8" id="total-price">총 결제금액(상품금액 + 배송비) : <label id = "price_sum_label">0</label>원</th>
				</tr>
			</table>
<script>
$("[name=buy-btn]").on('click',function(){
	$("[name="+$(this).attr("id")+"]").attr('action',"<%= request.getContextPath() %>/sale_product/productBuyInfo" );
	$("[name="+$(this).attr("id")+"]").submit();
});
$("#check-all").on('click', function(){
	if($("#check-all").is(":checked")){
		const number = Number($("[name=price_sum_input]").val());
		$("#price_sum_label").text(number);
		$("[name=product]").prop("checked", true);
	}
	else {
		$("#price_sum_label").text("0");
		$("[name=product]").prop("checked", false);
	}
});
$("[name=delete-btn]").on('click', function(){
	const a = "[name=cartList"+$(this).attr("id")+"]";
	$(a).submit();
});
// 체크 박스 개별 선택 / 해제 시 전체 선택 체크 박스 제어문
$("[name=product]").on('change', function(){
	const check_lenghts = $("input:checkbox[name=product]:checked").length;
	const $price = $(this).attr("id");
	if($(this).is(":checked")){
		$("#price_sum_label").text(Number($("#price_sum_label").text()) + Number($price))
	}
	else {
		$("#price_sum_label").text(Number($("#price_sum_label").text()) - Number($price))
	}
	if(check_lenghts != <%= cl.size() %>){
		$("input:checkbox[id=check-all]").prop("checked", false);
	}else{
		$("input:checkbox[id=check-all]").prop("checked", true);	
	}
});
</script>
<% } else { %>
<script>
		alert('<%=msg2%>');
		location.href='<%=request.getContextPath()%>/memberLogin';
</script>
<% } %>
</section>
<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>