<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
안녕
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<%-- <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script> --%>
<body>
      <form
			name="introduceFrm"
        	action ="<%= request.getContextPath() %>/homepage_introduce/introduce"
        	method="GET">
      </form>
<script>
$("[name=introduceFrm]").submit();
</script>
	<input type="button" value="판매글리스트" onclick="location.href='<%= request.getContextPath() %>/sale_product/productwritingList?category=0';">
	<input type="button" value="제품리스트" onclick="location.href='<%= request.getContextPath() %>/sale_product/productList?id=test0';">
	<input type="button" value="제품구매리스트" onclick="location.href='<%= request.getContextPath() %>/sale_product/productBuyList?id=test0';">
	<input type="button" value="관리자승인요청리스트" onclick="location.href='<%= request.getContextPath() %>/sale_product/productWritingAdminCheckList';">
	<input type="button" value="제품추가" onclick="location.href='<%= request.getContextPath() %>/sale_product/productAddpage';">
	
	<br/>
</body>
</html>
<!-- 메뉴 부분 로고, 로그인 회원가입 마이페이지 -->
