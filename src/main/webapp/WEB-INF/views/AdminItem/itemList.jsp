<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="UTF-8">
<title>商品管理画面</title>
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
						<li><a href="/Admin/InsertItem">商品追加</a></li>
						<li><a href="">注文一覧</a></li>
						<li><a href="/Admin/logout">ログアウト</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<hr>
	<div id="main">
		<div id="search">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">あいまい</a></li>
					<li><a href="#tabs-2">完全一致</a></li>
					<li><a href="#tabs-3">詳細</a></li>
				</ul>
				<div id="tabs-1">
					あいまい検索
					<form:form action="/Admin/Item" modelAttribute="showItemForm">
						<form:hidden path="id" value="1"/>
						商品名に<form:input path="keyword" />を含む
						<input type="submit" value="検索">
					</form:form>
				</div>
				<div id="tabs-2">
					完全一致検索
					<form:form action="/Admin/Item" modelAttribute="showItemForm">
						<form:hidden path="id" value="2"/>
						商品名が<form:input path="keyword" />に完全一致する
						<input type="submit" value="検索">
					</form:form>
				</div>
				<div id="tabs-3">
					詳細検索
					<form:form action="/Admin/Item" modelAttribute="showItemForm">
						<form:hidden path="id" value="3"/>
						商品名に
						<form:input path="keyword" />
						を含む
						<br>
						<form:errors path="price"/>
						<br>
						値段が
						<form:input path="price" required="required" />
						円以下
						<br>
						<br>
						<input type="submit" value="検索">
					</form:form>
				</div>
			</div>
		</div>
		<div class="table">
			<h2>商品一覧</h2>
			…<span class="descRed"><font style="background-color:#fa8072">商品名</font></span>は削除済み商品
			<br>
			<br><input type="button" onclick="location.href='/Admin/Item'" value="一覧表示" />
			<br> <span class="msg"><c:out value="${itemPage.msg}" /><c:out value="${insert}" /></span>
			<br>
			<table>
				<tr class="cel">
					<th colspan="2" class="cel">商品名</th>
					<th class="cel">価格</th>
					<th></th>
				</tr>
				<c:forEach var="item" items="${itemPage.childPage}">
					<tr class="cel">
						<td class="cel">
							<img src="/img/<c:out value="${item.imagePath}"/>" width="150" height="150" />
						</td>
						<td class="cel" style="background-color:<c:out value="${item.color}"/>"><c:out value="${item.name}" /></td>
						<td class="cel price">
							<fmt:formatNumber value="${item.price}" pattern="###,###,###" />円
						</td>
						<td>
							<form:form action="/Admin/ShowItemDetail" modelAttribute="showItemDetailForm" method="POST">
								<input type="hidden" name="id"
									value="<c:out value="${item.id}" />" />
								<input type="submit" value="編集">
							</form:form></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>