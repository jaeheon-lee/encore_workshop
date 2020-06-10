<%@page import="servlet.model.BookVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
box-sizing: border-box;
margin:0;
padding:0;
}
h1{
margin-top: 20px;
text-align: center;
}
div{
text-align: right;
height:30px;
}
table {
width: 100%;
}
th, td {
    border: 1px solid #444444;
    text-align: center;
  }
th{
    background-color: gray;
    color: white;
  }

input, select{
	display: inline-block;
	height: 30px;
vertical-align: middle;
}

</style>
</head>
<body>
<h1>도서목록 화면</h1>
<form action="front.do" method="post">
<input type="hidden" name="command" value="search">
<div>
<select name = "method">
	<option value="all" >전체</option>
	<option value="title">도서명</option>
	<option value="publisher">출판사</option>
	<option value="price">가격</option>
</select>
<input type="text" name="content"  height="20px"><input type = "submit" value="검색">
</div>
</form>


	<c:choose>
		<c:when test="${vo==null}">
			<h2 align="center">등록된 도서 목록이 없습니다.</h2>
		</c:when>
		<c:otherwise>
		<table>
		<tr>
			<th>도서번호</th><th>도서명</th><th>도서분류</th><th>저자</th>
		<tr>
			<c:forEach items="${vo}" var="book">
				<tr>
					<td width="15%">${book.isbn[0]}-${book.isbn[1]}-${book.isbn[2]}</td><td>${book.title}</td><td width="15%">${book.catalogue}</td><td width="15%">${book.author}</td>
				<tr>		
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<h5 align="center">도서등록</h5>

</body>
</html>