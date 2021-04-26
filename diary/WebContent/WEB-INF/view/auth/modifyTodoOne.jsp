<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyTodoOne</title>
</head>
<body>
	<h1>modifyTodoOne</h1>
	<form method="post" action="${pageContext.request.contextPath}/auth/modifyTodoOne">
		<div>
			<input type="hidden" name="todoNo" value="${todoOne.todoNo}">
		</div>
		<div>
			todoTitle:
			<input type="text" name="todoTitle">
		</div>
		<div>
			todoContent:
			<input type="text" name="todoContent">
		</div>
		<div>
			todoFontColor:
			<input type="color" name="todoFontColor">
		</div>
		<div>
			<button type="submit">수정</button>
		</div>
	</form>
</body>
</html> 