<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="/css/item.css">
<link href="/css/jquery-ui-1.9.2.custom.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者登録</title>
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
				<div class="menu"><a href="/Admin/logout">ログアウト</a></div>
			</div>
		</div>
	</header>
	<hr>
<div align="center">
  <h2>管理者登録</h2>
  
  
  
<form:errors path="registerAdminForm.*" cssStyle="color:red"/>
  <form:form modelAttribute="registerAdminForm" action="/Admin/registShow/register">
  <table>
  	<tr>
  		<td id="tableName">ID:</td>
  		<td><c:out value="${adminUserNum}"/><input type="hidden" name="id" value="${adminUserNum}"></td>
  	</tr>
  	<tr>
   		<td id="tableName">名前:</td>
   		<td><form:input path="name" autofocus="autofocus"/></td>
    </tr>
    <tr>
   		<td id="tableName">メールアドレス:</td>
   		<td><form:input path="email"/></td>
    </tr>
    <tr>
    	<td id="tableName">パスワード:</td>
    	<td><form:password path="password"/></td>
    </tr>
    <tr>
  		<td id="tableName">確認用パスワード:</td>
  		<td><form:password path="confirmationPass"/></td>
  </tr>
  </table><br>
    <input type="submit" value="登録">
</form:form>
	<form action="/Admin/registShow">
    	<input type="submit" value="クリア">
    </form>
  <a href="/Admin/showMenu">メニューに戻る</a>
</div>
</body>
</html>