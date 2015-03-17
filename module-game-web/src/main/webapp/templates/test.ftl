<#import "/tags/form.ftl" as form>
<#import "/tags/util.ftl" as util>
<html>
<head>
<title>
	Text
</title>
</head>
<body>
	TODO: remove
	<@form.form commandName="sms" method="POST" baseCaption="Test">
		<@form.inputText path="message.message" />
		<@form.inputNumber path="priority"/>
		<@form.inputNumber path="smsCount"/>
		<input type="submit"/>
	</@form.form>
</body>
</html>