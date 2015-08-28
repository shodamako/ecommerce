<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>決済をする</title>
<link rel="stylesheet" type="text/css" href="../css/ecHeader.css" />
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
		<div id="linkHeader" align="left">
			<h1 align="left">
				<a href="/serchItem/"><img src="../img/rakus.jpg" width="50"
					height="50" alt="ロゴ画像">ＥＣサイトラクス</a>
			</h1>
			<div id="title" align="center"></div>
	</header>
	<h2 align="center">ご注文内容</h2>
	<hr>
		<table border="1" width="380" align="center">
			<tr>
				<th>商品名</th>
				<th>価格</th>
				<th>個数</th>
				<th>小計</th>
				<th>税抜き価格</th>
			</tr>
			<c:forEach var="item" items="${makePaymentPage.childPage}">
				<tr>
					<td><c:out value="${item.name}"/><img src="../img/<c:out value="${item.imagePath}"/>" width="150"height="125" alt="商品画像"></td>
					<td align="right">￥<fmt:formatNumber value="${item.price}" pattern="###,###"/></td>
					<td align="right"><c:out value="${item.quantity}"/></td>
					<td align="right">￥<fmt:formatNumber value="${item.taxPrice}" pattern="###,###"/></td>
					<td align="right">￥<fmt:formatNumber value="${item.sumPrice}" pattern="###,###"/></td>
				</tr>
			</c:forEach>
			<tr>
				<td>消費税</td>
				<td align="right" colspan="4">￥<fmt:formatNumber value="${makePaymentPage.tax}" pattern="###,###"/></td>

			</tr>
			<tr>
				<td>送料一律</td>
				<td align="right" colspan="4">￥<fmt:formatNumber value="${makePaymentPage.portCost}" pattern="###,###"/></td>

			</tr>
			<tr>
				<td>商品合計</td>
				<td align="right" colspan="4">￥<fmt:formatNumber value="${makePaymentPage.totalPrice}" pattern="###,###"/></td>

			</tr>
		</table>
		<br>
		<h2 align="center">お届け先</h2>
		<hr>
		<div align="center">
			お名前：<c:out value="${makePaymentPage.name}"/><br>
			 メールアドレス：<c:out value="${makePaymentPage.email}"/><br>
			  住所：<c:out value="${makePaymentPage.address}"/><br>
			電話番号：<c:out value="${makePaymentPage.telephone}"/><br>
	<form:form action="/executePayment/insert" method="post">
			<br> <input type="submit" value="確定">
	</form:form>
	</div>
</body>
</html>