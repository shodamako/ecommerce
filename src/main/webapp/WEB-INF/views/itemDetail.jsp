<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib uri="http://www.springframework.org/tags/form"  prefix="form" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../../css/ecHeader.css" />
<title>Insert title here</title>
</head>
<body>
	<header>
		<div id="userHeader" align="right">
			<p>こんにちは<c:out value="${user.name}"/>さん</p>
			<p>
				<c:if test="${user.id == null }">
				<a href="/loginUser">ログイン</a>
				</c:if>
			    <c:if test="${user.id != null}">
				  <a href="/logoutUser">ログアウト</a>
				</c:if>
			</p>
			<p>
				<a href="/cart">カートの中身を見る</a>
			</p>
		</div>
		<div id="linkHeader" align="left"></div>
		<h1 align="left">
			<a href="/serchItem/"><img src="../../img/rakus.jpg" width="50"
				height="50" alt="ロゴ画像">ＥＣサイトラクス</a>
		</h1>
		<div id="title" align="center"></div>
	</header>
	<h2 align="center">商品詳細</h2>
	<p align="center"><c:out value="${errorMessage}"/></p>
	
	<c:if test="${item != null}">
	<table border="1" align="center">
		<tr>
			<td colspan="2" rowspan="3"><img
				src="../../img/${item.imagePath}" width="150" height="150"
				alt="商品画像"></td>
			<th>商品名：</th>
			<td align="center"><c:out value="${item.name}" /></td>
		</tr>
		<tr>
			<th>価格：</th>
			<td align="center">&yen;<fmt:formatNumber value="${item.price}" pattern="###,###" /></td>
		</tr>
		<tr>
			<th>商品説明：</th>
			<td><pre><c:out value="${item.description}" /></pre></td>
		</tr>
	</table>

	<br>

	<div id="selectQuantity" align="center">
	<form:form modelAttribute="addCartForm" action="/addCart">
	<form:errors path="quantity" cssStyle="color:red"/><br>
	個数：<form:input path="quantity"/>
	
			<form:hidden path="itemId" value="${item.id}"/>
		<div class="sendCart" align="center"><p><input type="submit" value="カートに入れる"></p></div>
	
	</form:form>
	</c:if>

		<div class="sendCart" align="center"><p><a href="/serchItem/">商品一覧画面へ戻る</a></p></div>

</body>
</html>