<#import "/tags/util.ftl" as util>
<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body onload='document.f.j_username.focus();'>
	<h3>Login with Username and Password (Authentication with Database)</h3>
	<#if error??>
		<div class="errorblock">
			${Session.SPRING_SECURITY_LAST_EXCEPTION}

		</div>
	</#if>
	<form name='f' action="<@util.url "/j_spring_security_check"/>"
		method='POST'>

		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset" />
				</td>
			</tr>
		</table>

	</form>
</body>
</html>