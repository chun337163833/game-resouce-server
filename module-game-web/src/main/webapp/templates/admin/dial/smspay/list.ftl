<#import "/tags/grid.ftl" as grid/>
<#import "/tags/form.ftl" as form/>
<#import "/tags/util.ftl" as util/>
<html>
<head><title><@util.message _implClass+".list.title"/></title></head>
<body>
	<@grid.grid data=entities baseCaption=_implClass>
		<@grid.filter class="Grid" showHeader=false>
			<div class="row">			
				<div class="col grid-f col-f-1">
					<@form.inputNumber path="phoneNumber"/>
				</div>
			</div>		
		</@grid.filter>
		<#--list with pagination and sort-->
		<@grid.datalist idColumn="id" renderButtons=false showNewButton=true>
			<@grid.column name="id" />
			<@grid.column name="erikaId" isDetail=true/>
			<@grid.column name="phoneNumber"; _rowdata, columnName, _value>
				+${_th.convertToStringWithSource(columnName, _rowdata)!""}
			</@grid.column>
			<@grid.column name="smsText" />
			<@grid.column name="paymentStatus" ; _rowdata, columnName, _value>
				<#if _value==0>
					<@util.message (_implClass)+".status.running" />
				<#elseif _value==1>
					<@util.message (_implClass)+".status.confirmed" />
				<#else>
					<@util.message (_implClass)+".status.failed" />
				</#if>
			</@grid.column>
			<@grid.column name="smsReceptionTime" />
			<@grid.column name="paymentTime" />
			<@grid.column name="createdOn" />
		</@grid.datalist>
		
	</@grid.grid>
</body>
</html>
