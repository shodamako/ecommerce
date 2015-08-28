<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録画面</title>
<link rel="stylesheet" href="/css/item.css">
<link href="/css/jquery-ui-1.9.2.custom.css" rel="stylesheet">
<script src="/js/jquery-1.8.3.js"></script>
<script src="/js/jquery-ui-1.9.2.custom.js"></script>
<script>
	$(function() {
		$('.menu').click(function() {
			$('.menuList').slideToggle();
		});
	});
</script>
</head>
<body>
	<header>
		<div id="linkHeader">
			<h1 align="left">
				<a href="/Admin/showMenu"> <img src="/img/rakus.jpg" width="50"
					height="50" alt="ロゴ画像"></a>
			</h1>
		</div>
		<div id="userHeader">
			<div class="userHeaderContents">
				<p>
					管理者 <br> [
					<c:out value="${page.loginName }" />
					]さん
				</p>
			</div>
			<div class="userHeaderContents">
				<div class="menu">メニュー▼</div>
				<div class="menuList">
					<ul>
						<li><a href="/Admin/showMenu">メニュー</a></li>
						<li><a href="/Admin/Item">商品管理</a></li>
						<li><a href="/Admin/orderList">注文一覧</a></li>
						<li><a href="/Admin/logout">ログアウト</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<hr>
	<h2>商品追加</h2>
	<br><span class="msg"><c:out value="${insert}" /></span>
	<br>
	<br>
	<form:form modelAttribute="insertItemForm" action="/Admin/Insert"
		method="POST" enctype="multipart/form-data">
	ID：<c:out value="${maxId}"/>
		<form:hidden path="id" value="${maxId}" />
		<br>
		<form:errors path="id" cssStyle="color:red" />
		<br>
		<br>
	名前：<form:input path="name" autofocus="autofocus"/>
		<br>
		<form:errors path="name" cssStyle="color:red" />
		<br>
		<br>
	説明：<form:textarea path="description" />
		<br>
		<form:errors path="description" cssStyle="color:red" />
		<br>
		<br>
	単価：<form:input path="price" />
		<br>
		<form:errors path="price" cssStyle="color:red" />
		<br>
		<br>
	画像：<input type="file" name="file" id="file" />
		<br>
		<form:errors path="file" cssStyle="color:red" />
		<br>
		<br>
		<br>
		<br>
		<input type="submit" value="登録">
	</form:form>
	<br>
	<input type="submit" value="戻る" onclick="location.href='/Admin/Item'">
	<br>
	<br>
</body>
</html>