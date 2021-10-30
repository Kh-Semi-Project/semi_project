<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<footer>
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