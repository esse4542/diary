<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeMember</title>
</head>
<body>
	<!-- 회원 탈퇴 -->
	<h2>회원 탈퇴</h2>
	<form action="${pageContext.request.contextPath}/auth/removeMember" method="post">
		<div>
			memberPw : 
		</div>
		<div>
			<input type="password" name="memberPw">
		</div>
		<div>
			<button type="submit">회원탈퇴</button>
			<button type="button" onclick="history.back()">취소</button>
		</div>
	</form>
</body>
</html>