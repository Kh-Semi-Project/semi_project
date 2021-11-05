<%@page import="mvc.login_join_and_management.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mvc.sale_product.product.model.vo.Product"%>
<script src="https://www.gstatic.com/firebasejs/5.0.4/firebase.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/common.css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<%
	Product pw_data = (Product) request.getAttribute("pw_data");
	Member member = (Member) session.getAttribute("loginMember");
%>
<%if(member != null){ %>
	<section id = "product-writing_add-container">
	<h2>제품 판매글 추가</h2>
	<form
		name = "productWritingAddFrm"
		action = ""
		method = "post"
	>
		<table>
			<tr>
				<th colspan = "4">제품 정보</th>
			</tr>
			<tr>
				<th rowspan="2">판매자</th><td rowspan="2">판매자이름</td> <!-- session에서 아이디 가져오기 -->
				<th>제품 사진</th><td><img src="<%= pw_data.getProduct_img() %>" width="100px" height="150px"/></td>
			</tr>
			<tr>
				<th>제품명</th><td><%= pw_data.getProduct_name() %></td>
			</tr>
			<tr>
				<th colspan="2">판매 글 하위 이미지 추가</th>
				<td colspan="2"><input name = "Add" type="submit" value="추가하기"/></td></td> 
				<!-- 
					해당 판매자가 올린 제품 리스트 중, 글작성이 안된 제품을 라디오 박스로 출력
					제품 추가하기 버튼 추가, 해당 버튼 클릭시 제품 추가 사이트로 이동
					로 진행하려고 했으나
					올린 제품 리스트가 많으면 감당되지 않아서 변경
				-->
			</tr>
		</table>
		<table>
			<tr>
				<td rowspan="2">이미지 선택</td>
				<td>
						<img id = "photo_print" src ="" alt ="제품사진" onerror= "this.style.display='none'" width = 300px height = 500px><br/>
						<!-- 파일 추가-->
						1. <input id="photo" class="file" type="file" name="mainimage" 
						                   value="" onchange="getfile()"><br><br>
						진행률 : <progress value="0" id="uploader" max="100">0%</progress><br><br>
				</td>
				<td></td>	
				<td></td>	
			</tr>
			<tr>
				<td><input id = "img_delBtn1" type="button" value="삭제하기" style="display:none;"/></td>
				<td><input id = "img_delBtn2" type="button" value="삭제하기"/></td>
				<td><input id = "img_delBtn3" type="button" value="삭제하기"/></td>

			</tr>
		</table>
	</form>
	</section>

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
	          myfunction(); 
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
	                $("#img_delBtn1").css("display", "block");
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
 <%}else{%>
 <script>
 		alert('로그인 후 이용 가능합니다.');
		location.href='<%=request.getContextPath()%>/memberLogin';
 </script>
 <%}%>