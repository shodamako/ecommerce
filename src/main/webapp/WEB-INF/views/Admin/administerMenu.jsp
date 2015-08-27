<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>メニュー画面</title>
<style>
#userHeader{
	float: right;
}
#linkHeader{
	float: light;
}
</style>
</head>
<body>
	<header>
	<div id="userHeader" align="right">
			こんにちは管理者[<c:out value="${page.loginName }" />]さん<br>
			<a href="/Admin/logout">ログアウト</a>
	</div>
	<div id="linkHeader" align="left">
		<h1 align="left">
			<a href="/Admin/showMenu"><img src="../../img/rakus.jpg"
				width="50" height="50" alt="ロゴ画像"></a>
		</h1>
	</div>
	<div id="title" align="center"></div>
	</header>
	<hr>

	<div align="center">
		<h2>管理者メニュー画面</h2>
		<a href="/Admin/Item">商品管理</a><br> <br> <a
			href="/Admin/orderList">注文一覧</a><br> <br> <a
			href="/Admin/registShow">管理者新規登録</a> <br> <br> <br>

	</div>
</body>
</html>