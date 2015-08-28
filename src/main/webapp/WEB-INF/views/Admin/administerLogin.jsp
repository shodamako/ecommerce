<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/item.css">
<link href="/css/jquery-ui-1.9.2.custom.css" rel="stylesheet">
<title>管理者ログイン画面</title>
</head>
<body>

	</head>
<body>
	<header>
		<div id="linkHeader">
			<h1 align="left">
				<a href="/Admin"> <img src="/img/rakus.jpg" width="50"
					height="50" alt="ロゴ画像"></a>
			</h1>
		</div>
	</header>
	<hr>
	<div align="center">
		<h2>ログイン</h2>



		<form:errors path="loginAdminForm.*" cssStyle="color:red" />
		<form:form modelAttribute="loginAdminForm" action="/Admin/login">
			<font color="red"><c:out value="${error}" /></font>
			<br>
			<table>
			<tr>
    			<td id="tableName">メールアドレス：</td>
    			<td><form:input path="email" autofocus="autofocus"/></td>
			</tr>
			<tr>
    			<td id="tableName">パスワード： </td>
    			<td><form:password path="password" /></td>
			</tr>
			</table><br>
			<input type="submit" value="ログイン">
		</form:form>
	</div>
</body>
</html>