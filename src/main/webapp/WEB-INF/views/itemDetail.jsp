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
			<p>こんにちはゲストさん</p>
			<p>
				<a href="/loginUser">ログイン</a>
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
			<td><c:out value="${item.description}" /></td>
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
		<div class="sendCart" align="center"><p><a href="/serchItem/">商品一覧画面へ戻る</a></p></div>
<%-- 
		<form action="/addCart" method="post">

			個数：<select name="quantity">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
			</select> <input type="hidden" name="itemId" value="${item.id}">
			<div class="sendCart" align="center">
				<p>
					<input type="submit" value="カートに入れる">
				</p>
			</div>
		</form>

	</div>

	<div class="sendCart" align="center">
		<p>
			<a href="itemList.html">商品一覧画面へ戻る</a>
		</p>
		--%>
	</div>

</body>
</html>