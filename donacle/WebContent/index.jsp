<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<body>
      <form
			name="introduceFrm"
        	action ="<%= request.getContextPath() %>/homepage_introduce/introduce"
        	method="GET">
      </form>
<script>
$("[name=introduceFrm]").submit();
</script>
</body>
</html>