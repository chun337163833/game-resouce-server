<#import "/tags/grid.ftl" as grid/>
<#import "/tags/form.ftl" as form/>
<#import "/tags/util.ftl" as util/>
<html>
<head><title><@util.message _implClass+".list.title"/></title></head>
<body>
	<@grid.grid data=entities baseCaption=_implClass>
		<#--filter
		<@grid.filter showHeader=false>
		</@grid.filter>
		-->
		<#--list with pagination and sort-->
		<@grid.datalist idColumn="id" renderButtons=false showNewButton=true>
			<@grid.column name="userName" isDetail=true />
			<@grid.column name="lastName" />
			<@grid.column name="firstName" />
		</@grid.datalist>
		
	</@grid.grid>
</body>
</html>
