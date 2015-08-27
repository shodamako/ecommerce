<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<link rel="stylesheet" type="text/css" href="../css/ecHeader.css" />
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
		<div id="linkHeader" align="left">
			<h1 align="left">
				<a href="/serchItem/"><img src="../img/rakus.jpg" width="50"
					height="50" alt="ロゴ画像">ＥＣサイトラクス</a>
			</h1>
			<div id="title" align="center"></div>
	</header>



	<h2 align="center">商品一覧</h2>

	<form:form modelAttribute="serchItemForm" action="/serchItem/"
		align="center" method="post">
		<form:input path="keyWord" placeholder="検索ワード" />
		<input type="submit" value="検索する">
	</form:form>
	<br>
	<c:choose>
		<c:when test="${serchItempage.childPage.size() != 0}">
		<table border="1" align="center">
				<tr>
					<th colspan="2">商品名</th>
					<th>価格</th>
				</tr>
				<c:forEach var="item" items="${serchItempage.childPage}">

					<tr>
						<td><a href="/showItem/findById/${item.id}" id="id" ><img src="../img/${item.imagePath}.jpg"
								width="150" height="125" alt="商品画像"></a></td>
						<td><a href="/showItem/findById/${item.id}" id="id"><c:out value="${item.name}"/></a></td>
						<td><fmt:formatNumber value="${item.price}" pattern="###,###" />
							円</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			該当商品はありません
		</c:otherwise>
	</c:choose>
</body>
</html>