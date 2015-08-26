<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>利用者ログイン画面</title>
<link rel="stylesheet" type="text/css" href="../css/ecHeader.css" />
</head>
<body>
<header>
		<div id="userHeader" align="right">
			<p>こんにちはゲストさん</p>
			<p><a href="viewShoppingCart.html">カートの中身を見る</a></p>
		</div>
				<div id="linkHeader" align="left">
			<h1 align ="left"><a href="itemList.html"><img src="../img/rakus.jpg" width="50"
				height="50" alt="ロゴ画像">ＥＣサイトラクス</a></h1>
		<div id="title" align="center">
		</div>
</header>
  <div align="center">
  <h2>ログイン</h2>
  <form:errors path="loginUserForm.*"/>
<form:form modelAttribute="loginUserForm" action="/loginUser">
メールアドレス：<form:input path="mailAddress" placeholder="email"/><br>
パスワード：<form:password path="password" placeholder="password"/><br>
<input type="submit" value="ログイン">
</form:form>
  <a href="registerUser.jsp">新規登録はこちら</a>
  </div>
</body>
</html>
