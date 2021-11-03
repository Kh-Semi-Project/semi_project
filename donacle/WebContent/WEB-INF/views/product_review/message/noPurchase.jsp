<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	alert("후기 작성이 가능한 구매 내역이 없습니다.");
	location.href = "<%=request.getContextPath()%>/review/reviewList";
</script>