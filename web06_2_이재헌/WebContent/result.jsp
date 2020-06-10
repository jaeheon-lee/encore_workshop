<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1 align="center">결과 페이지</h1><P>
		<h2 align="center">정상적으로 저장 되었습니다.</h1><P><P><P><P><P><P>
		<h4 align="center"><%=request.getParameter("title")%> 책이 등록되었습니다.</h4>
		<div align="center" style="background-color:RGB(210,210,210);"><a href="book/book.jsp">추가등록</a>   <a href="ShowAllBooks?command=ShowAllBooks" align="center">도서목록</a></div>
</body>
</html>