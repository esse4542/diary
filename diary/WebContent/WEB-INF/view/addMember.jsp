<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertMember</title>
</head>
<body>
	<!-- 회원가입 -->
	<h2>회원가입</h2>
	
	<form action="${pageContext.request.contextPath}/addMember" method="post">
		<div> memberId : <input type="text" name="memberId" required></div>
		<div> memberPw : <input type="password" name="memberPw" required></div>
		
		<button type="submit">회원 가입</button>
		<button type="button" onclick="history.back()">취소</button>
	</form>
</body>
</html> 