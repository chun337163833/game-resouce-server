<#import "/spring.ftl" as spring />
<#import "/tags/form.ftl" as form/>
<#import "/tags/util.ftl" as util/>
<html>
<head><title><@util.message _implClass+".title"/></title></head>
<body>
<@form.form commandName="entity" baseCaption="TxUser" renderTitle=true>
		
		<div class="col grid-2t col-t-1">
			<@form.inputText path="userName" />
			<@form.inputText path="title" defaultCaption="Customer.customerTitle" />
			<@form.inputText path="firstName" />
			<@form.inputText path="lastName" />
			<@form.inputNumber path="cellPhone" forceRequired=true />
			<@form.inputNumber path="email" />
			<@form.inputText path="address.street" />
	
			<div class="row">
				<div class="col grid-t col-t-1">
					<@form.inputText path="address.zip" />
				</div>
				<div class="col grid-2t col-t-2">
					<@form.inputText path="address.city" />
				</div>
			</div>
			<div class="row">
				<div class="col grid-h col-h-1">
					<@form.inputText path="birthDate"  />
				</div>
				<div class="col grid-h col-h-2">
					<@form.inputText path="basicDiscountPaidDate" />
				</div>
			</div>						
		</div>
		<div class="col grid-t col-t-3">
			<@form.checkbox path="blackListed" />
		</div>
</@form.form>		
</body>
</html>
