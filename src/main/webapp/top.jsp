<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>top</title>
<style>
body {
    border: solid 2px #000080;
    display: inline-block;
    padding: 5px;
    
}
</style>
</head>
<body>
  
  <h1>検索条件を入力してください</h1>
  <c:if test="${not empty msg}">
    <p>${msg}</p>
  </c:if>
  <form action="serch" method="post">
    product id: <input type="text" name="id" value="${fn:escapeXml(param.id)}"><br>
    price: <input type="text" name="price" value="${fn:escapeXml(param.price)}"><br>
    <button type="submit">検索</button>
  </form>
 
</body>
</html>