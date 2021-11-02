<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="mvc.sale_product.product.model.vo.ProductWriting"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/common.css"/>
<%
	List<ProductWriting> productList = (List<ProductWriting>) request.getAttribute("ProductList");
%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<h2>제품 리스트</h2>
<section id="product-list-container">
	<table id="product-list">
		<tr>
			<th>번호</th>
			<th colspan="2">제품정보</th> <!-- 제품 사진, 제품명 클릭시 해당 판매 글 페이지로 이동 -->
			<th>가격</th>
			<th>수량</th>
			<th>배송비</th>
			<th>제품정보</th>
			<th>글작성 여부</th>
			<th>관리자승인 여부</th>
			<th>제품 수정하기</th>
			<th>선택</th>
		</tr>

<!-- 사용자가 추가한 제품 리스트 출력 -->
<%
int index = 0;
for(ProductWriting pw : productList){
index++;
%>
		<tr>
			<td colspan="10" class="tdclass">donacle<td>
		</tr>
		<tr>
			<td><%=index %></td>
			<!-- 제품사진 --><td><img src="<%= pw.getProduct_img()!= null ? pw.getProduct_img() : "" %>" alt="사진" width = "100px" height="100px"/></td>
			<!-- 제품명 --><td><%= pw.getProduct_name() %></td>
			<td><%= pw.getProduct_price() %>원</td>
			<td><%= pw.getProduct_count() %>개</td>
			<td><%= pw.getShipping_fee() %>원</td>
			<td><%= pw.getProduct_content()%></td>
			<td>
			<!-- 판매자가 제품 정보 수정하기 위해 제품 코드 servlet으로 전송 -->
			<form 
				name = "productupdateFrm<%=index %>"
				action="<%= request.getContextPath() %>/sale_product/productView" 
				method="POST"
				style = "display : none;"
			>
				<input type = "hidden" name = "product_name" value="<%= pw.getProduct_name() %>"/>
				<input type = "hidden" name = "product_img" value="<%= pw.getProduct_img() %>"/>
				<input type = "hidden" name = "product_code" value="<%=pw.getProduct_code() %>"/>
			</form>
<% if(pw.getAdmin_check() == null|| pw.getAdmin_check().length() == 0){%>
			<input type="button" name = "productWritingBtn" id = "<%=index %>" value="작성 하기"/>
<% }else{%>
			작성 완료
<%}%>
			</td>
			<td>
<!-- 관리자 승인 -->			
<% if(pw.getAdmin_check() == null|| pw.getAdmin_check().length() == 0){ %>
		<!-- 글 추가를 하지 않은 제품 -->
<%}else if(pw.getAdmin_check().equals("n")){%>
		미승인
<%}else{%>
		승인
<%}%><!-- 왜 빨간줄 떴다가 안떴다가 그러는지 모르겠음 -->
			</td>
			<td><input type="button" name = "productupdateBtn" id = "<%= index %>" value="제품 수정"/></td>
			<td>
<!-- 체크 박스 선택 들기 -->			
<!-- 배송출발, 수령완료가 되지 않은 제품만 주문 취소 가능 -->

				<input type="checkbox" class = "" name="checkboxs" value="<%=pw.getProduct_code() %>">
			</td>
		</tr>
<%}%>
		<tr><!-- 삭제 버튼 만들기 -->
			<td><input type="button" name = "productdeleteBtn" value="제품 삭제"/></td>
		</tr>
	</table>
</section>
<script>

	/*
		사용자가 제품 삭제 버튼을 클릭하면 삭제하기
	*/

	/*
		제품 수정 만들기
		제품 수정하면 관리자 승인도 다시 받아야 됨
	*/
	$("[name=productupdateBtn]").click(function(){
		const index = $(this).attr('id');
		const name = "[name=productupdateFrm" + index+"]";
		if(confirm("제품을 수정하면 관리자 승인도 다시 받아야 됩니다.\n계속 진행하시겠습니까?") == true){			
			$(name).submit();
		}
	});

	$("[name=productWritingBtn]").click(function(){
		const index = $(this).attr('id');
		const name = "[name=productupdateFrm" + index+"]";
		$(name).attr("action","<%= request.getContextPath() %>/sale_product/productWritingAdd");
		
		$(name).submit();
	});

//	$("[name=productWritingBtn]").click(function(){
//	const index = $(this).attr('id');
//	const name = "[name=productupdateFrm" + index+"]";
//	$(name).attr("action","request추가 필요/sale_product/productWritingForm");
//	
//	$(name).submit();
//});
	
	// 제품 삭제 클릭시 진행
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
	
	$("[name=productdeleteBtn]").click(function(){
		//선택된 제품이 있는지 확인
		if(checkboxCode.length == 0){
			alert("선택된 제품이 없습니다.")
		
		//선택된 제품이 있다면 json으로 데이터 전달
		//한개의 데이터가 아닌 여러개의 데이터 한번에 삭제 가능
		}else{
			if(confirm("정말로 제품을 삭제하겠습니까?") == true){
				var a = JSON.stringify(checkboxCode);
				//alert("ajax 데이터 전달전"+checkboxCode + ", " + a);
				$.ajax({
					url : "<%= request.getContextPath() %>/sale_product/productDelete",
					type : "post",
					dataType : "json",
					data : {codes : a},
					success(data){
						console.log(data)
						alert(data);
						location.reload();
					},
					error : console.log
				});
			}
		}
	});
</script>