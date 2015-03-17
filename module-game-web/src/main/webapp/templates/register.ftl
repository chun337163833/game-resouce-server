<#import "/tags/form.ftl" as form>
<#import "/tags/util.ftl" as util>
<html>
<head>
<title>
	<@util.message "Registration.title" />
</title>
</head>
<body>
	<@form.form commandName="user" method="POST" baseCaption="Registration" additionalErrors=["passwordsMatch", "userExist"]>
		<@form.inputText path="userName" />
		<@form.inputText path="firstName" />
		<@form.inputText path="lastName" />		
		<@form.inputText path="email" />
		<@form.inputPassword path="password" />
		<@form.inputPassword path="matchPassword" />
		<input type="submit"/>
	</@form.form>
	
	
</body>
</html>