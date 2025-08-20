<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="logout" method="post"> <!--8-3.jsp:로그아웃 처리--> 
${userId}님 로그인        
<input type="submit" value="로그아웃"> 
</form>
</body>
</html>