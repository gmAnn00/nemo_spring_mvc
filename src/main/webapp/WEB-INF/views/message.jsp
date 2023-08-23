<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네모</title>
<c:if test="${data.message != null}">
	<script>
		alert("${data.message}");
	</script>
</c:if>
<c:if test="${data.href != null}">
	<script>
		location.href="${data.href}";
	</script>
</c:if>

</head>
<body>
</body>
</html>