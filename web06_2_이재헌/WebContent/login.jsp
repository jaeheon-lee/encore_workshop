<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
box-sizing: border-box;
margin: 0;
padding:0;
}
form{
width: 560px;
height: 180px;
line-height: 180px;
background-color: gray;
}
img{
width:150px;
}
#div1{
width:150px;
height:150px;
vertical-align: middle;
margin-left:20px;
margin-top:15px;
}
#div2{
width:300px;
color:white;
line-height:normal;
display: inline;
vertical-align: middle;
margin-top: 38.889px;
margin-bottom: 38.889px;
}
#helper{
clear: both;
display: block;
}

div{
text-align:right;
float:left;
}
h3{
margin-bottom:10px;
}
input[value="Login"]{
background-color: green;
color: white;
width: 100px;

}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
$('#frm').submit('click', function(event) {
	if($('input[name="id"]').val()==""){
		alert("id 값을 입력해 주세요.");
		$('input[name="id"]').focus();
		event.preventDefault();
		
	} else if($('input[name="pass"]').val()==""){
		alert("pass 값을 입력해 주세요.");
		$('input[name="pass"]').focus();
		event.preventDefault();
	}		
});
});
</script>
</head>
<body>
<form action="front.do" method="post" id="frm">
<input type="hidden" name="command" value="login">
<div id="div1">
	<img src="img/book.png">
</div>
<div id="div2">
	<h3>로그인하여주세요</h3>
	ID<input type="text" name="id"><br>
	PASSWORD<input type="password" name="password"><br>
	<input type="submit" value="Login">
</div>
<div id="helper"></div>
</form>
</body>
</html>