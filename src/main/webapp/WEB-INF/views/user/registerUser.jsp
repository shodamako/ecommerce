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
			<p>こんにちはゲストさん</p>
			<p>
				<a href="userLogin.html">ログイン</a>
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
		
		<form:form modelAttribute="RegisterUserForm" action="/registerUser/create" method="post" >
			<br>
				<form:errors path="name"/>
				<form:errors path="email"/>
				<form:errors path="address"/>
				<form:errors path="telephone"/>
				<form:errors path="password"/>
				<form:errors path="confirmPassword"/>
			<table border="">
				<tr>
					<td><p id="nameId" style="display: none; color: red;">
							名前を入力して下さい</p> 名前</td>
					<td><input type="text" name="name"></td>

				</tr>
				<tr>
					<td><p id="emailId" style="display: none; color: red;">アドレスを入力して下さい</p>
						<p id="emailId2" style="display: none; color: red;">アドレスが不正です</p>
						メールアドレス</td>
					<td><input type="text" name="email"></td>

				</tr>
				<tr>
					<td><p id="addressId" style="display: none; color: red;">
							住所を入力して下さい</p> 住所</td>
					<td><input type="text" name="address"></td>

				</tr>
				<tr>
					<td><p id="telId" style="display: none; color: red;">
							電話を入力して下さい</p> 電話番号</td>
					<td><input type="text" name="tel"></td>

				</tr>
				<tr>
					<td><p id="passwordId" style="display: none; color: red;">
							パスワードを入力して下さい</p> パスワード<br> <font color="red"><small>8桁以上16桁以下で設定してください</small></font></td>
					<td><input type="password" name="password"></td>

				</tr>
				<tr>
					<td><p id="confirmPasswordId"
							style="display: none; color: red;">確認用パスワードを入力して下さい</p>
						<br> 確認用パスワード<br> <font color="red"><small>設定したパスワードを再度入力してください</small></font>
					</td>
					<td><input type="password" name="confirmPassword"></td>

				</tr>
			</table>

			<br> <br>
			<input type="submit" value="お客様情報を登録する">
			<input type="reset" value="入力内容をクリアする">	

		</form:form>
		
		
		<!-- 以下は以前のもの -->
		
		<form action="/registerUser/create" method="post" name="form1"
			onsubmit="return check()">
			<br>
			<table border="">
				<tr>
					<td><p id="nameId" style="display: none; color: red;">
							名前を入力して下さい</p> 名前</td>
					<td><input type="text" name="name"></td>

				</tr>
				<tr>
					<td><p id="mailId" style="display: none; color: red;">アドレスを入力して下さい</p>
						<p id="mailId2" style="display: none; color: red;">アドレスが不正です</p>
						メールアドレス</td>
					<td><input type="text" name="mail"></td>

				</tr>
				<tr>
					<td><p id="addressId" style="display: none; color: red;">
							住所を入力して下さい</p> 住所</td>
					<td><input type="text" name="address"></td>

				</tr>
				<tr>
					<td><p id="telId" style="display: none; color: red;">
							電話を入力して下さい</p> 電話番号</td>
					<td><input type="text" name="tel"></td>

				</tr>
				<tr>
					<td><p id="passwordId" style="display: none; color: red;">
							パスワードを入力して下さい</p> パスワード<br> <font color="red"><small>8桁以上16桁以下で設定してください</small></font></td>
					<td><input type="password" name="password"></td>

				</tr>
				<tr>
					<td><p id="confirmPasswordId"
							style="display: none; color: red;">確認用パスワードを入力して下さい</p>
						<br> 確認用パスワード<br> <font color="red"><small>設定したパスワードを再度入力してください</small></font>
					</td>
					<td><input type="password" name="confirmPassword"></td>

				</tr>
			</table>

			<br> <br>
			<input type="submit" value="お客様情報を登録する"> <input type="reset"
				value="入力内容をクリアする">

		</form>
	</div>

</body>
</html>