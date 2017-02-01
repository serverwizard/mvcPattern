<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사진 목록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>

<h1>사진목록2</h1>
<p><a href=''>새로운 사진</a></p>
<c:forEach var="image" items="${images}"> 
${image.no},
${image.URL}, 
<img src="${image.URL}" alt="photo" height="100" width="100">, 
${image.createdDate}
<br>
</c:forEach>

<jsp:include page="/Tail.jsp"/>
</body>
</html>