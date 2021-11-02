<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="mvc.sale_product.product.model.vo.ProductWriting"%>

<%
	ProductWriting pw = (ProductWriting) request.getAttribute("ProductWriting");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/common.css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="https://www.gstatic.com/firebasejs/5.0.4/firebase.js"></script>
	<style>
		#photo {
			display : inline;
		}
		#photo_print {
			width : 250px;
			height : 350px;
		}
	</style>
	<section id = "product-add-container">
	<h2>물건 추가</h2>
	<form
		name = "productAddFrm"
		action = "<%=request.getContextPath() %>/sale_product/productUpdate"
		method = "post"
	>
		<table>
			<tr>
				<td rowspan ="6">
						<img id = "photo_print" src ="<%=pw.getProduct_img() %>" alt ="제품사진" onerror= "this.style.display='none'" width = 300px height = 500px><br/>
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
				<td>: <input type = "text" name = "sale_id" value="<%=pw.getId()%>" readonly /></td>
			</tr>
			<tr>
				<th>제품명</th>
				<td>: <input type = "text" name = "productName" id ="productName" value="<%=pw.getProduct_name() %>" required></td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td>: <select id = "category">
					<option value = "1" <%if(pw.getCategory_code() == 1){ %>selected<% }%>>목걸이</option>
					<option value = "2" <%if(pw.getCategory_code() == 2){ %>selected<% }%>>팔찌</option>
					<option value = "3" <%if(pw.getCategory_code() == 3){ %>selected<% }%>>뱃지</option>
					<option value = "4" <%if(pw.getCategory_code() == 4){ %>selected<% }%>>키링</option>
					</select>
					<input type="hidden" name = category_code id = "category_code" style="display:none;" value="<%=pw.getCategory_code()%>"/>
				</td>
			</tr>
			<tr>
				<th>제품내용</th>
				<td>: <input type = "text" name = "productContent" id ="productContent" value="<%=pw.getProduct_content()%>"></td>
			</tr>
			<tr>
				<th>가격</th>
				<td>: <input type="text" name="productPrice" id="productPrice" onkeyup="$(this).val($(this).val().replace(/\D/g,''));" value="<%=pw.getProduct_price()%>"required />원</td>
			</tr>
			<tr>
				<th>배송비</th>
				<td>: <input type = "text" name = "shipping_fee" id ="shipping_fee" onkeyup="$(this).val($(this).val().replace(/\D/g,''));" value="<%=pw.getShipping_fee()%>" required>원</td>
			</tr>
			<tr>
				<th>개수</th>
				<td>
					<input type="radio" name = "pro_Count" id ="pro_Count10" value ="10"><label for="pro_Count10">10개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count30" value ="30"><label for="pro_Count30">30개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count50" value ="50"><label for="pro_Count50">50개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count70" value ="70"><label for="pro_Count70">70개</label>
					<input type="radio" name = "pro_Count" id ="pro_Count00" value ="<%=pw.getProduct_count() %>" checked><label for="pro_Count00">직접입력</label>
				</td>
				<td id = "none_block">
					<input type="text" name = "pro_Counts" id ="pro_Counts" value ="<%=pw.getProduct_count() %>" onkeyup="$(this).val($(this).val().replace(/\D/g,''));">개
				</td>
			</tr>
			<tr>
				<td><input name = "Add" type="submit" value="수정하기"/></td>
			</tr>
		</table>
		<input type = "hidden" name="img_url" style="display:none;" value="<%=pw.getProduct_img()%>"/>
		<input type = "hidden" name="productCode" style="display:none;" value="<%=pw.getProduct_code()%>"/>
	</form>
	</section>
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
	
	<script type="text/javascript">
	
		//firebase api
		const firebaseConfig = {
				  apiKey: "AIzaSyAR8HFStaQVavryJT8o1KLTQgTmWRnNxfo",
				  authDomain: "kh-semi-project-73858.firebaseapp.com",
				  projectId: "kh-semi-project-73858",
				  storageBucket: "kh-semi-project-73858.appspot.com",
				  messagingSenderId: "352378251318",
				  appId: "1:352378251318:web:2946b3f9c81a6dc94c9097",
				  measurementId: "G-E62QFPND2K"
				};
	
		// firebase 초기화
		firebase.initializeApp(firebaseConfig);

        var selectedFile;
      
        // 파일 가져오기
        function getfile()
        {
	          var pic = document.getElementById("photo");
	  
	           // selected file is that file which user chosen by html form
	          selectedFile = pic.files[0];
	  
	           // 파일선택을 해서 파일을 업로드 하면 firebase에 저장하는 id='submit_link' 활성화
	          document.getElementById('submit_link').removeAttribute('disabled');
	     }
        
        // firebase에 저장하는 함수
	      function myfunction()
	      {
	          // 시간을 통해 고유한 이름 만들기
	          // Date.now() is function that give current timestamp
	          var name="123"+Date.now();
	  
	          // images 폴더에 저장하기
	          var storageRef = firebase.storage().ref('/images/'+ name);
	  
	          // firebase에 선택한 파일 넣기
	          var uploadTask = storageRef.put(selectedFile);
	  
	          // 업로드 수치, 잘 되고 있는지 확인
	          uploadTask.on('state_changed', function(snapshot){
	            var progress = 
	             (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
	              var uploader = document.getElementById('uploader');
	              uploader.value=progress;
	              switch (snapshot.state) {
	                case firebase.storage.TaskState.PAUSED:
	                  console.log('Upload is paused');
	                  break;
	                case firebase.storage.TaskState.RUNNING:
	                  console.log('Upload is running');
	                  break;
	              }
	          }, function(error) {console.log(error);
	          }, function() {
	  
	               // 업로드한 url 반환
	               uploadTask.snapshot.ref.getDownloadURL().then(
	                function(downloadURL) {
	  
	               // 다운로드 된 url -> downloadURL 변수에 저장
	                console.log('File available at', downloadURL);
	                $("#photo_print").attr("src", downloadURL);
	                $("#photo_print").css("display", "block");
	                $("[name=img_url]").val(downloadURL);
	                
	               // url 주소 출력
	               console.log(downloadURL);
	               // firebase에 저장하는 save 버튼 비활성화
	               document.getElementById('submit_link').setAttribute('disabled', 'true');
	            });
	          });
	      };
	 </script>