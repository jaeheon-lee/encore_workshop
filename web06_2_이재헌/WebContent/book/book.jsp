<%@page import="servlet.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${vo==null}">
	<h3>로그인부터 시작하세요.</h3>
	<a href="../login.html">Login</a>
	</c:when>
	<c:otherwise>
	<h1>도서 등록 화면</h1>
	<form action="../front.do" method="post" name="frm" id="frm">
	<input type="hidden" name="command" value="register">
		<table>
			<thead>
				<tr>
					<th colspan="2" align="right" style="color:gray;"><span style="color: orange;">*</span>표시가 된 항목은
						필수 입력 항목입니다.</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<td colspan="2"></td>
				</tr>
				<tr>
					<td><b style="color:orange;">*</b>도서번호</td>
					<td><input type="text" name="isbn">-<input
						type="text" name="isbn">-<input type="text"
						name="isbn"></td>
				</tr>
				<tr>
					<td><span style="color: orange;">*</span>도서제목</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td><span style="color: orange;">*</span>도서종류</td>
					<td><select name="catalogue">
							<option value="">해당항목을 선택하세요.</option>
							<option>프로그래밍</option>
							<option>소설</option>
							<option>과학</option>
							<option>종교</option>
					</select></td>
				</tr>
				<tr>
					<td>*출판국가</td>
					<td><input type="radio" name="nation" value="domestic">국내도서
						<input type="radio" name="nation" value="foreign">국외도서</td>
				</tr>
				<tr>
					<td>*출 판 일</td>
					<td><input type="date" name="publish_date"></td>
				</tr>
				<tr>
					<td>*출 판 사</td>
					<td><select name="publisher">
							<option value="">해당항목을 선택하세요.</option>
							<option>한빛미디어</option>
							<option>엔코아</option>
							<option>동아시아</option>
							<option>교학사</option>
					</select></td>
				</tr>
				<tr>
					<td><span style="color: orange;">*</span>저자</td>
					<td><input type="text" name="author"></td>
				</tr>
				<tr>
					<td>*도서가격</td>
					<td><input type="text" name="price"> <select
						name="currency">
							<option>원</option>
							<option>달러</option>
							<option>위안</option>
					</select></td>
				</tr>
				<tr>
					<td>요약내용</td>
					<td><textarea name="description" cols="100" rows="5" maxlength="200"></textarea></td>
				</tr>
			</tbody>
		</table>
		<div id="add">
			<input type="submit" name="submit" value="도서등록"><input
				type="reset" name="reset" value="취소">
		</div>
	</form>
	<div id="footer">
		사전 도움말-이용약관-개인정보취급방침-책임의 한계와 법적 고지
	</div>
	<div id="copyRight">Copyrightⓒ2020. HaBaRee. AllRight Reserved</div>
	
	
	</c:otherwise>
</c:choose>

    
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">


tr td:first-child{
text-align:center;
padding:5px;
}

h1{
text-align:center;
background-color: RGB(200, 200, 200);
}
table,td,tr,th{
	border: 1px solid gray;
	border-collapse: collapse;
}
tbody tr:first-child{
height: 20px;
background-color: RGB(200, 200, 200); 
}
table{
margin-right:auto;
margin-left:auto;
}

input, textarea,select {
	margin-left:5px;
}
textarea{
vertical-align: middle;
}

input[type="submit"], input[type="reset"]{
background-color:gray;
color: white;
}
#add{
text-align: center;
margin-top: 20px;
background-color: rgb(215, 215 ,215);
}

#copyRight, #footer{
text-align: center;
color: #a5cdef;

}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {

		$('#frm').submit('click', function(event) {

			var isbn = $('input[name="isbn"]');
			for (var i = 0; i < bookNumber.length; i++) {

				if (isbn.eq(i).val() == "") {
					alert("도서번호를 모두 입력하여주십시오.")
					isbn.eq(i).focus();
					event.preventDefault();
				}
			}

			if ($('input[name="title"]').val() == "") {
				alert("도서제목을 입력하여주십시오.")
				$('input[name="title"]').focus();
				event.preventDefault();
			}

			if ($('select[name=catalogue]').val() == "") {
				alert("도서종류를 선택하여주십시오.")
				$('select[name=catalogue]').focus();
				event.preventDefault();
			}

			if ($('input[name="author"]').val() == "") {
				alert("저자를 입력하여주십시오.")
				$('input[name="auther"]').focus();
				event.preventDefault();
			}
			if ($('input[name="price"]').val() == "") {
				alert("금액을 입력하여주십시오.")
				$('input[name="price"]').focus();
				event.preventDefault();
			}
		});
	});
</script>
</head>
<body>
	
</body>
</html>
