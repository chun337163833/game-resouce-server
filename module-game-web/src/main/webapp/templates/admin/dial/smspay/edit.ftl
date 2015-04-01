<#import "/spring.ftl" as spring />
<#import "/tags/form.ftl" as form/>
<#import "/tags/util.ftl" as util/>
<html>
<head><title><@util.message _implClass+".title"/></title></head>
<body>
<@form.form commandName="entity" baseCaption=_implClass renderTitle=true>
		
		<div>
			<@form.inputNumber path="erikaId" />
			<@form.inputNumber path="phoneNumber" />
			<@form.inputText path="smsText" />
			<@form.inputText path="smsReceptionTime" />
			<@form.inputText path="paymentTime" />
			<@form.inputNumber path="paymentStatus" />
			<@form.inputText path="paymentError" />
			<@form.inputText path="operator" />
			<@form.inputNumber path="shortCode" />
			<@form.inputText path="createdOn" />
			<@form.inputText path="modifiedOn" />
		</div>
</@form.form>		
</body>
</html>
