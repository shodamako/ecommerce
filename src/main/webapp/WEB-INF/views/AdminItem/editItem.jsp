<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/item.css">
<link href="/css/jquery-ui-1.9.2.custom.css" rel="stylesheet">
<script src="/js/jquery-1.8.3.js"></script>
<script src="/js/jquery-ui-1.9.2.custom.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
		$('.menu').click(function() {
			$('.menuList').slideToggle();
		});
	});
</script>
<title>管理画面：商品詳細</title>
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
						<li><a href="">注文一覧</a></li>
						<li><a href="/Admin/logout">ログアウト</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<hr>
	<!-- 商品画像編集 -->
	<div class="editTable">
	<form:form action="/Admin/EditImage" modelAttribute="editItemForm" enctype="multipart/form-data">
	<h2  align="center">商品詳細</h2>
	<table border="1">
		<tr>
			<td colspan="3"><img src="../img/${editItemForm.imagePath}" width="150"
				height="150" alt="商品画像">
			</td>
		</tr>
		<tr>
			<th>商品画像：</th>
			<td class="cel"><input type="file" name="image" id="image"/><form:errors path="image" cssStyle="color:red"/></td>
			<td class="cel">
				<input type="hidden" name="id" value="${editItemForm.id}">
				<input type="hidden" name="name" value="${editItemForm.name}">
				<input type="hidden" name="description" value="${editItemForm.description}">
				<input type="hidden" name="price" value="${editItemForm.price}">
				<input type="hidden" name="imagePath" value="${editItemForm.imagePath}">
				<input type="hidden" name="deleted" value="${editItemForm.deleted}">
				<input type="submit" value="画像を変更する">
			</td>
		</tr>
	</table>
	</form:form>
	<br>
	<br>
	<!-- 商品情報編集 -->
	<form:form action="/Admin/EditItem" modelAttribute="editItemForm" method="POST">
	<table border="1">
		<tr>
			<th>商品名：</th>
			<td align="center"><form:input path="name" /><form:errors path="name" cssStyle="color:red"/></td>
		</tr>
		<tr>
			<th>価格：</th>
			<td class="cel"><form:input path="price" /><form:errors path="price" cssStyle="color:red"/></td>
		</tr>
		<tr>
			<th>商品説明：</th>
			<td class="cel"><pre><form:textarea rows="5" cols="21" path="description"/><form:errors path="description" cssStyle="color:red"/></pre></td>
		</tr>
	</table>
		<div align="center"><p>
			<input type="hidden" name="id" value="${editItemForm.id}">
			<input type="hidden" name="imagePath" value="${editItemForm.imagePath}">
			<input type="hidden" name="deleted" value="${editItemForm.deleted}">
			<input type="submit" value="商品情報を変更する">
		</p></div>
	</form:form>
		<!-- 商品削除 -->
		<form:form action="/Admin/DeleteItem" modelAttribute="deleteItemForm" method="POST">
			<input type="hidden" name="id" value="${editItemForm.id}">
			<input type="hidden" name="deleted" value="${editItemForm.deleted}">
			<div align="center"><p><input type="submit" value="商品を削除する"></p></div>
		</form:form>
		
		<!-- 商品再表示　テスト用 -->
		<form:form action="/Admin/ReturnItem" modelAttribute="returnItemForm" method="POST">
			<input type="hidden" name="id" value="${editItemForm.id}">
			<input type="hidden" name="deleted" value="${editItemForm.deleted}">
			<div align="center"><p><input type="submit" value="削除した商品を再表示する"></p></div>
		</form:form>
		
		<!-- 管理者商品一覧へのリンク -->
		<div align="center"><p><a href="/Admin/Item">商品一覧画面へ戻る</a></p></div>
			</div>
</body>
</html>