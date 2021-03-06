<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録画面</title>
<link rel="stylesheet" type="text/css" href="../css/ecHeader.css" />

<script type="text/javascript">
<!--
	function check() {
		var flag = 0;
		if (document.form1.name.value == "") {
			document.form1.name.focus();
			flag = 1;
			document.getElementById('nameId').style.display = "block";
		} else {
			document.getElementById('nameId').style.display = "none";

		}
		if (document.form1.mail.value == "") {
			document.form1.mail.focus();
			flag = 1;
			document.getElementById('mailId').style.display = "block";
		} else {
			document.getElementById('mailId').style.display = "none";
		}

		if (!document.form1.mail.value.match(/.+@.+\..+/)) {
			document.form1.mail.focus();
			flag = 1;
			document.getElementById('mailId2').style.display = "block";
		} else {
			document.getElementById('mailId2').style.display = "none";
		}

		if (document.form1.address.value == "") {
			document.form1.address.focus();
			flag = 1;
			document.getElementById('addressId').style.display = "block";
		} else {
			document.getElementById('addressId').style.display = "none";
		}

		if (document.form1.password.value == "") {
			document.form1.password.focus();
			flag = 1;
			document.getElementById('passwordId').style.display = "block";
		} else {
			document.getElementById('passwordId').style.display = "none";
		}

		if (document.form1.confirmPassword.value == "") {
			document.form1.confirmPassword.focus();
			flag = 1;
			document.getElementById('confirmPasswordId').style.display = "block";
		} else {
			document.getElementById('confirmPasswordId').style.display = "none";
		}

		if (document.form1.tel.value == "") {
			document.form1.tel.focus();
			flag = 1;
			document.getElementById('telId').style.display = "block";
		} else {
			document.getElementById('telId').style.display = "none";
		}

		if (flag) {

			return false;
		} else {
			return true;
		}
	}

	-->
</script>

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


	<div align="center">
		<h1>新規利用者登録画面</h1>
		<br> <br> お客様の情報を入力し、「お客様情報を登録する」ボタンをクリックしてください。 <br> <br>
		<form:errors path="registerUserForm.*" />
		<form:form modelAttribute="registerUserForm" enctype="multipart/form-data"
			action="/registerUser/create" method="post">
			<br>

			<table border="">
			
				<tr>
					<td>名前</td>
					<td><form:input path="name"/></td>		
				</tr>
				<tr>
					<td>メールアドレス</td>
					<td><form:input path="email"/></td>		
				</tr>
				<tr>
					<td>住所</td>
					<td><form:input path="address"/></td>		
				</tr>
				<tr>
					<td>電話番号 (例：090-1234-5678)</td>
					<td><form:input path="telephone1" size="3"/>-<form:input path="telephone2" size="3"/>-<form:input path="telephone3" size="3"/></td>		
				</tr>
				<tr>
					<td>パスワード</td>
					<td><form:password path="password"/></td>		
				</tr>
				<tr>
					<td>確認用パスワード</td>
					<td><form:password path="confirmPassword"/></td>		
				</tr>
				
			</table>

			<br>
			<br>
			<input type="submit" value="お客様情報を登録する">
			<input type="reset" value="入力内容をクリアする">

		</form:form>
				
				
				
				
<!--  				<tr>
	

					<td><input type="text" name="email"></td>

				</tr>
				<tr>
					<td><p id="addressId" style="display: none; color: red;">
							住所を入力して下さい</p> 住所</td>
					<td><input type="text" name="address"></td>

				</tr>
				<tr>
					<td><p id="telephoneId" style="display: none; color: red;">
							電話を入力して下さい</p> 電話番号</td>
					<td><input type="text" name="telephone"></td>

				</tr>
				<tr>
					<td><p id="passwordId" style="display: none; color: red;">
							パスワードを入力して下さい</p> パスワード<br> <font color="red"><small>8桁以上16桁以下で設定してください</small></font></td>
					<td><input type="password" name="password"></td>

				</tr>
				<tr>
					<td><p id="confirmPasswordId"
							style="display: none; color: red;">確認用パスワードを入力して下さい</p> <br>
						確認用パスワード<br> <font color="red"><small>設定したパスワードを再度入力してください</small></font>
					</td>
					<td><input type="password" name="confirmPassword"></td>

				</tr>
				
				-->

	</div>

</body>
</html>