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