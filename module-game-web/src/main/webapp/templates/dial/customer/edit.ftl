<#import "/spring.ftl" as spring />
<#import "/tags/form.ftl" as form/>
<#import "/tags/util.ftl" as util/>
<html>
<head><title><@util.message _implClass+".title"/></title></head>
<body>
<@form.form commandName="entity" baseCaption=_implClass renderTitle=true additionalErrors=["basicDiscountExist"]>
		<div class="col grid-2t col-t-1">
			<@form.inputText path="userName" />
			<@form.inputText path="lastName" />
			<@form.inputText path="firstName" />
			<@form.inputText path="firstName" />
		</div>
</@form.form>		
</body>
</html>
