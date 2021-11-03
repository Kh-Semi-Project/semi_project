<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

	<style>
		#pro_Counts {
			display : none
		}
	</style>
	<section id = "product-add-container">
	<h2>물건 추가</h2>
	<form
		name = "productAddFrm"
		action = ""
		method = "get"
	>
		<table>
			<tr>
				<td rowspan ="6">사진</td>
				<th>판매자</th>
				<td>: 판매자이름</td>
			</tr>
			<tr>
				<th>제품</th>
				<td>: <input type = "text" name = "productName" id ="productName" required></td>
			</tr>
			<tr>
				<th>가격</th>
				<td>: <input type = "text" name = "productPrice" id ="productPrice" required>원</td>
			</tr>
			<tr>
				<th>배송정보</th>
				<td>: <input type = "text" name = "transPrice" id ="transPrice" required>원</td>
			</tr>
			<tr>
				<th>개수</th>
				<td>
					<input type="radio" name = "pro_Count" id ="pro_Count10" value ="10개" checked><label for="pro_Count10">10개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count30" value ="30개"><label for="pro_Count30">30개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count50" value ="50개"><label for="pro_Count50">50개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count70" value ="70개"><label for="pro_Count70">70개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count00" value ="직접입력"><label for="pro_Count00">직접입력</label>
				</td>
				<td>
					<input type="text" name = "pro_Count" id ="pro_Counts">
				</td>
			</tr>
			<tr>
				<td><input name = "Add" type="submit" value="추가하기"/></td>
			</tr>
		</table>
	</form>
	</section>
	<script>
	
		$("[name=pro_Count]").on('change', function(){ 
			if ($("#pro_Count00").prop('checked')){ 
				$("#pro_Counts").css("display", "block"); 
			} else { 
				$("#pro_Counts").css("display", "none"); 
			} 
		}); 

		
		$("[name=productAddFrm]").submit(function(){	
		
		});
	</script>