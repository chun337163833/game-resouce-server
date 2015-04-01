<#import "/tags/grid.ftl" as grid/>
<#import "/tags/form.ftl" as form/>
<#import "/tags/util.ftl" as util/>
<html>
<head><title><@util.message _implClass+".list.title"/></title></head>
<body>
	<@grid.grid data=entities baseCaption=_implClass>
		<#--filter
		
		-->

		<@grid.filter showHeader=false>
			<@form.inputText path="discount" />
		</@grid.filter>

		<#assign _isDetail=true>
		<#assign _mbuttons=["publish", "unpublish"]>
		<#assign _publicable=true>
		<#assign _showNewButton=true>
		<#--list with pagination and sort-->
		<@grid.datalist idColumn="id" renderButtons=false showNewButton=_showNewButton>
			<@grid.column name="tariffName" isDetail=_isDetail />
			<@grid.column name="discount" />
			<@grid.column name="basicDiscount" />
		</@grid.datalist>
		
	</@grid.grid>
</body>
</html>
