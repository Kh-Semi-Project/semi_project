<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="https://www.gstatic.com/firebasejs/5.0.4/firebase.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/sale_product/firebaseImage.js"></script>

	<style>
		#none_block {
			display : none;
		}
		#photo {
			display : inline;
		}
		#photo_print {
			width : 200px;
			height : 200px;
		}
		#productAddT{
			width : 70%;
			height : 400px;
			margin : auto;
		}
		body{
			text-align : center;
		}
		input[name=Add]:hover{
			background-color : black;
			color : white;
		}
		#pro_Counts {
			margin-left: -15px;
			margin-top: 35px;
		}
	</style>
<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %>
<%
	Member member = (Member) session.getAttribute("loginMember");
%>
<%if(member != null){ %>
	<section id = "product-add-container" style="margin-top:200px;">
	<h2>물건 추가</h2>
	<form
		name = "productAddFrm"
		action = "<%=request.getContextPath() %>/sale_product/productAdd"
		method = "post"
	>
		<table id = "productAddT">
			<tr>
				<td rowspan ="7">
						<img id = "photo_print" src ="" alt ="제품사진" onerror= "this.style.display='none'" ><br/>
						<!-- 파일 추가-->
						1. <input id="photo" class="file" type="file" name="mainimage" 
						                   value="" onchange="getfile()"><br><br>
						진행률 : <progress value="0" id="uploader" max="100">0%</progress><br><br>
						<!-- firebase로 제출-->
						2. <button id="submit_link" type="button" name="button" onclick="myfunction()" disabled>Save</button>
				</td>
				<th>판매자</th>
				<!--  ★-------------------★  -->
				<!-- 후에 id값 가져와야됨 -->
				<td>: <input type = "text" name = "sale_id" readonly value="<%=member.getId()%>"/></td>
			</tr>
			<tr>
				<th>제품명</th>
				<td>: <input type = "text" name = "productName" id ="productName" required></td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td>: <select id = "category" style="width:170px;">
					<option value = "1" selected>목걸이</option>
					<option value = "2">팔찌</option>
					<option value = "3">뱃지</option>
					<option value = "4">키링</option>
					</select>
					<input type="hidden" name = category_code id = "category_code" style="display:none;" value="1"/>
				</td>
			</tr>
			<tr>
				<th>제품내용</th>
				<td>: <input type = "text" name = "productContent" id ="productContent"></td>
			</tr>
			<tr>
				<th>가격</th>
				<td>: <input type="text" name="productPrice" id="productPrice" onkeyup="$(this).val($(this).val().replace(/\D/g,''));" required />원</td>
			</tr>
			<tr>
				<th>배송비</th>
				<td>: <input type = "text" name = "shipping_fee" id ="shipping_fee" onkeyup="$(this).val($(this).val().replace(/\D/g,''));" required>원</td>
			</tr>
			<tr>
				<th>개수</th>
				<td>
					<input type="radio" name = "pro_Count" id ="pro_Count10" value ="10" checked><label for="pro_Count10">10개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count30" value ="30"><label for="pro_Count30">30개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count50" value ="50"><label for="pro_Count50">50개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count70" value ="70"><label for="pro_Count70">70개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count00" value ="직접입력"><label for="pro_Count00">직접입력</label>
				</td>
				<td id = "none_block" style="margin:auto;">
					<input type="text" name = "pro_Counts" id ="pro_Counts" value ="10" onkeyup="$(this).val($(this).val().replace(/\D/g,''));">개
				</td>
			</tr>
			<tr>
				<td colspan="3" style="text-align:center;"><input name = "Add" type="submit" value="추가하기"/></td>
			</tr>
		</table>
		<input type = "hidden" name="img_url" style="display:none;"/>
	</form>
	</section>
	<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>
	<script>
		//직접 입력할 수 있게 하는 부분 만들기
		$("[name=pro_Count]").on('change', function(){
			if($("#pro_Count00").prop('checked')){ 
				$("#pro_Counts").val("");//초기화
				$("#none_block").css("display", "block");
			} else{ 
				$("#pro_Counts").val($(this).val());
				$("#none_block").css("display", "none"); 
			} 
		}); 
		$("#category").on('change',function(){
			$("#category_code").val($("#category").val());
		});
		//값 체크
		$("[name=productAddFrm]").submit(function(){
			var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
			var number_pattern = /\d/;
			const type = $('#photo_print').attr("src");
			const counts = $("#pro_Counts").val();
			const contents = $("#productContent").val();
			//추가한 이미지가 없다면 제출 불가능

			if(typeof type == "undefined" || type == "" || type == null){
				alert("이미지를 추가해주세요.");
				return false;
			//제품명에 특수문자 사용 불가능
			}else if(checkSpecual($("#productName").val(),special_pattern)){
				alert("특수문자를 사용하실 수 없습니다.")
				 $('#productName').focus();
				return false;
			//pro_Count 값이 없는지 체크
			}else if(counts == "" || !counts || counts == null){
				alert("값을 추가해주세요.")
				$('#pro_Counts').focus();
				return false;
			}else if(contents == "" || !contents || contents == null){
				if(confirm("제품 정보에 대한 내용이 없습니다.\n그냥 진행하시겠습니까?") == false){
					$('#productContent').focus();
					return false;
				}
			}
			return true;
		});
		
		function checkSpecual(str,checkStr){
			if(checkStr.test(str) == true) { 
				return true; 
			} else { 
				return false; 
			}
		}
	</script>
<%}else{%>
<script>
	alert('로그인 후 이용 가능합니다.');
	location.href='<%=request.getContextPath()%>/memberLogin';
</script>
<%} %>