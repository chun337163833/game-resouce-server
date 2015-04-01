<#import "/tags/grid.ftl" as grid/>
<#import "/tags/form.ftl" as form/>
<#import "/tags/util.ftl" as util/>
<html>
<head><title><@util.message _implClass+".list.title"/></title></head>
<body>
	<@grid.grid data=entities baseCaption="TxUser">
		<#--filter
		<@grid.filter showHeader=false>
		</@grid.filter>
		-->
		<#--list with pagination and sort-->
		<@grid.datalist idColumn="id" renderButtons=false showNewButton=true>
			<@grid.column name="userName" isDetail=true />
			<@grid.column name="firstName" />
			<@grid.column name="lastName" />
			<@grid.column name="cellPhone" />
			<@grid.column name="email" />
		</@grid.datalist>
		
	</@grid.grid>
</body>
</html>
