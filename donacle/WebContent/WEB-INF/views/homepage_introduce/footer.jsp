<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<footer>
            <div class="copyright">
                <span>상호: (주)도나클</span><b> | </b>
                <span>이메일: contact@donacle.com</span><b> | </b>
                <span>대표전화: 0000-0000</span><br>
                <span>주소: 서울특별시 강남구 테헤란로14길 6 남도빌딩</span><br>
                ⓒ2021.donacle all rights reserved.
            </div>
            <a href="#" id="tothetop"><img src="https://i.ibb.co/KK0H2Pz/free-icon-up-arrow-156924.png" alt=""></a>
        </footer>
    </div>
<script>
	//top버튼 클릭시 상단으로 이동
	jQuery( '#tothetop' ).click( function() {
		var htmloffset = jQuery( 'html' ).offset();
		jQuery( 'html, body' ).animate( { scrollTop : htmloffset.top }, 1000 );
	});
</script>
</body>
</html>