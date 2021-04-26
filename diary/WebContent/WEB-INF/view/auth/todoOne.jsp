<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todoOne</title>
</head>
<body>
	<div><a href="${pageContext.request.contextPath}/auth/diary">다이어리</a></div>

	<h1>todoOne</h1>
	<table border="1">
		<tr>
			<th>todoDate</th>
           	<td>${todoOne.todoDate}</td>
		</tr>
		<tr>
			<th>todoTitle</th>
           	<td>${todoOne.todoTitle}</td>
		</tr>
		<tr>
			<th>todoContent</th>
           	<td>${todoOne.todoContent}</td>
		</tr>
		<tr>
			<th>todoFontColor</th>
           	<td>${todoOne.todoFontColor}</td>
		</tr>
		<tr>
			<th>todoAddDate</th>
           	<td>${todoOne.todoAddDate}</td>
		</tr>
	</table>
	<a href="${pageContext.request.contextPath}/auth/modifyTodoOne?todoNo=${todoOne.todoNo}">수정</a>
	<a href="${pageContext.request.contextPath}/auth/removeTodoOne?todoNo=${todoOne.todoNo}">삭제</a>
</body>
</html> 