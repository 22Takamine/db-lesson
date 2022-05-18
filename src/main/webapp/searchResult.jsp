<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
<style>
body {
    border: solid 2px #000080;
    display: inline-block;
    padding: 5px;
    
}
</style>
</head>
<body>
  <h1>検索結果</h1>
  <table border="1">
    <tr>
      <th>product_id</th>
      <th>product_name</th>
      <th>price</th>
    </tr>
    <c:forEach var="product" items="${productList}">
      <tr>
        <td>${fn:escapeXml(product.productId)}</td>
        <td>${fn:escapeXml(product.productName)}</td>
        <td>${fn:escapeXml(product.price)}</td>
      </tr>
    </c:forEach>
  </table>
  <form action="top.jsp">
    <button type="submit" name="btn" value="back">戻る</button>
  </form>
 
</body>
</html>