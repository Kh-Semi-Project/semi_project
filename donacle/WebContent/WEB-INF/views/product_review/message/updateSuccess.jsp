<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	alert("후기가 변경 되었습니다.");
	location.href = "<%=request.getContextPath()%>/review/reviewList";
</script>