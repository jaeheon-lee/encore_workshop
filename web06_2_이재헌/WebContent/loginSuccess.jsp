<%@page import="servlet.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${vo==null}">
	<h3>로그인부터 시작하세요.</h3>
	<a href="login.html">로그인</a>
	</c:when>
	<c:otherwise>
	<body>
		<h2 align="center">${param.id}님이 로그인 되었습니다.</h2><P><P><P><P><P><P>
		<div align="center"><a href="book/book.jsp">도서등록</a></div><br>
		<div align="center"><a href="front.do?command=ShowAllBooks">도서목록</a></div><br>
		<div align="center"><a href="front.do?command=logout" align="center">로그아웃</a></div>
	</body>
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
</html>