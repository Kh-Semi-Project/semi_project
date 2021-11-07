<%@page import="mvc.sale_product.product.model.vo.BuyAddress"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mvc.sale_product.product.model.vo.ProductBuy"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/common.css"/>  
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<%
	List<BuyAddress> pblist = (List<BuyAddress>) request.getAttribute("ProductBuyList");
	Member member = (Member) session.getAttribute("loginMember");
%>   
<style>
input[name=productOrderCancelBtn]{
	margin : auto;
}
input[name=productOrderCheckBtn]:hover, input[name=productOrderCancelBtn]:hover{
	background-color : black;
	color : white;
}
</style>
<%if(member != null){ %>
<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %>
<h2>주문 리스트 확인</h2>
<section id="product-order-container" style="<%if(pblist.size() > 5){%>height: auto;<%}%>">
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th colspan="2">제품정보</th> <!-- 제품 사진, 제품명 클릭시 해당 판매 글 페이지로 이동 -->
			<th>구매 수량</th>
			<th>총금액</th>
			<th>구매일</th>
			<th>배송출발여부</th> <!-- 판매자가 배송을 시작했는지 확인 -->
			<th>수령완료여부</th> <!-- 사용자가 물건을 받았는지 버튼을 통해 확인 -->
			<th>선택</th> <!-- 제품 선택해서 주문 취소하기 -->
			<!-- 전체 선택 추가하기 -->
		</tr>

<!-- 사용자가 구매했던 리스트 출력 -->
<%
int index = 0;
for(BuyAddress pb : pblist){
index++;
%>
		<tr>
			<td colspan="9" class="tdclass">donacle<td>
		</tr>
		<tr>
			<td><%=index %></td>
			<td><img src="<%=pb.getProduct_img() %>" width="100px" height="150px"/></td>
			<td><%=pb.getProduct_name() %></td>
			<td><%=pb.getProduct_buy_count() %>개</td>
			<td><%=pb.getPrice_sum()+pb.getProduct_donate_price() %>원</td>
			<td><%=pb.getProduct_buy_date() %></td>
			<td>
			
<!-- 판매자가 배송을 시작했는지 확인 하기 위함 -->
<% if(pb.getProduct_shipping_status().equals("n")) {%>
	미배송
<%} else{%>
	배송
<%} %>
			</td>
			<td>
			
		<!-- 사용자가 주문확인 페이지에서 수령완료 체크 하기 위함 -->
		<form 
			name = "productOrderCheckFrm<%=index %>"
			action="<%= request.getContextPath() %>/sale_product/productOrderCheck" 
			method="POST"
			style = "display : none;"
		>
			<input type = "hidden" name = "product_buy_code" value="<%=pb.getProduct_buy_code() %>"/>
			<input type = "hidden" name = "product_donate_price" value="<%=pb.getProduct_donate_price() %>"/>
			<input type = "hidden" name = "product_shipping_status<%=index %>" value="<%=pb.getProduct_shipping_status() %>"/>
		</form>
<% 
if(pb.getProduct_receipt_yn().equals("n")) {%>
			<input type="button" name = "productOrderCheckBtn" id = "<%=index %>" value="수령 미완료"/><%} 
else{%>수령 완료<%}%><!-- 왜 빨간줄 떴다가 안떴다가 그러는지 모르겠음 -->
			</td>
			<td>
<!-- 체크 박스 선택 들기 -->			
<!-- 배송출발, 수령완료가 되지 않은 제품만 주문 취소 가능 -->
<%
if(pb.getProduct_shipping_status().equals("n") && pb.getProduct_receipt_yn().equals("n")){
%>
				<input type="checkbox" class = "" name="checkboxs" value="<%=pb.getProduct_buy_code() %>">
<%}%>
			</td>
		</tr>
		<tr>
			<td colspan="9" style="border-bottom: 1px solid black; border-top: 1px solid black; ">
					<div style="margin-top:10px">
						배송지 정보 <br/>
						(<%=pb.getZip_code()%>) <%=pb.getAddress() + pb.getDetail_address()%> <br/>
					</div>
			</td>
		</tr>
<%} %>
<% if(pblist == null|| pblist.isEmpty()){%>
	<tr><td colspan="9"><img src="<%= request.getContextPath() %>/css/sale_product/buy.png" alt="" width="400px;"/></td></tr>
<%}%>
		<tr><!-- 주문취소 버튼 만들기 -->
			<td colspan="9"><input type="button" name = "productOrderCancelBtn" value="주문취소"/></td>
		</tr>
	</table>
</section>
<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>
<script>

	/*
		사용자가 주문 취소를 누르면 취소 가능하게 만들기
	*/

	// 주문 취소할 데이터들을 담는 변수
	var checkboxCode = [];
	$("input:checkbox[name='checkboxs']").on({
		change : function(e){
			//체크를 눌렀을 때 값 추가
			if($(this).is(":checked") == true) {
				checkboxCode.push($(this).val());
			}else{
				// 체크 풀면 해당 값 제거
				let filtered = checkboxCode.filter((element) => element !== $(this).val());
				// 제거된 리스트 새롭게 추가하기
				checkboxCode = filtered;
			}
		}
	});
	
	$("[name=productOrderCancelBtn]").click(function(){
		//선택된 제품이 있는지 확인
		if(checkboxCode.length == 0){
			alert("선택된 제품이 없습니다.")
		
		//선택된 제품이 있다면 json으로 데이터 전달
		//한개의 데이터가 아닌 여러개의 데이터 한번에 삭제 가능
		}else{
			var msgs = "";
			if(confirm("정말로 제품 주문을 취소하겠습니까?") == true){
				var a = JSON.stringify(checkboxCode);
				//alert("ajax 데이터 전달전"+checkboxCode + ", " + a);
				$.ajax({
					url : "<%= request.getContextPath() %>/sale_product/productBuyDelete",
					type : "post",
					dataType : "json",
					data : {codes : a},
					success(data){
						msgs += data
						console.log(data)
					},
					error : console.log
				});
			}
			setTimeout(function() {
				alert(msgs);
				location.reload();
			}, 2000);
		}
	});
	/* 
		리스트 출력 후, 사용자가 수령완료 버튼을 클릭하면 서버로 정보 전달
		- 수령완료 클릭할 때는 확인창 한번 떠 띄우기
		- 수령 완료 시 form 제출
		-> form 제출을 좀 더 쉽게 하는 방법이 있던 것 같은데 기억안나서 약간 hard 코딩...
	 */
	 $("[name=productOrderCheckBtn]").click(function(){
		const index = $(this).attr('id');				
		 const name = "[name = product_shipping_status"+index+"]"
		 if($(name).val() == 'n'){
			if(confirm("제품이 아직 배송되지 않았습니다.\n제품 수령 완료 후 배송은 책임지지 않으며,\n해당 정보를 수정할 수 없습니다.\n제품 수령하겠습니까?") == true){
				//alert("index : " + index); // test
				$("[name=productOrderCheckFrm"+index+"]").submit();
			} 
		 }else{
				if(confirm("제품 수령 완료 후 배송은 책임지지 않으며,\n해당 정보를 수정할 수 없습니다.\n제품 수령하겠습니까?") == true){
					//alert("index : " + index); // test
					$("[name=productOrderCheckFrm"+index+"]").submit();
				} 
		 }
	 });
</script>
<%}else{%>
 <script>
 		alert('로그인 후 이용 가능합니다.');
		location.href='<%=request.getContextPath()%>/memberLogin';
 </script>
<%}%>