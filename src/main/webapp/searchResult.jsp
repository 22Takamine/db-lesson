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
  <p>データを取得しました</p>
  
  <p>product_id:${fn:escapeXml(productData.productId)}</p>
  <p>product_name:${fn:escapeXml(productData.productName)}</p>
  <p>price:${fn:escapeXml(productData.price)}</p>
  
  <form action="top.jsp">
    <button type="submit" name="btn" value="back">戻る</button>
  </form>
 
</body>
</html>