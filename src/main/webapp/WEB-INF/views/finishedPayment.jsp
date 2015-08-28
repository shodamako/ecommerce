<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>決済完了画面</title>
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
			<p><a href="/cart">カートの中身を見る</a></p>
		</div>
				<div id="linkHeader" align="left">
			<h1 align ="left"><a href="/serchItem/"><img src="../img/rakus.jpg" width="50"
				height="50" alt="ロゴ画像">ＥＣサイトラクス</a></h1></div>
		<div id="title" align="center">
		</div>
</header>
	<h1 align="center">決済が完了しました！</h1>
	<h2 align="center">この度はご注文ありがとうございます。<br>
	お支払い先は、お送りしたメールに記載してありますのでご確認ください。</h2>
	<p align="center"><a href="/serchItem/">一覧画面へ戻る</a></p>
</body>
</html>