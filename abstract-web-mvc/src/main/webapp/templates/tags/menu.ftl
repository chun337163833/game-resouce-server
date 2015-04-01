<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "/tags/util.ftl" as util/>
<#macro print fromFactory style="default">
<div class="col col-side">
	<div id="side-menu">
		<@printSubmenu fromFactory.menu.submenu />
	</div>
</div>
</#macro>

<#macro printSubmenu list>
	<ul>
		<#list list as row>
			<#if hasAccess(row.link!"_NULL_")>
				<li>
					<@printTitle row.group>
						<#if row.link??>
							<@printLink path=row.link title=row.title />
						<#else>
							<@util.message row.title />
						</#if>
	
						<#if row.group>
							<@printSubmenu row.submenu />
						</#if>
					</@printTitle>
				</li>
			</#if>
		</#list>
	</ul>
</#macro>

<#function hasAccess link>
	<#if !util.isNull(link)>
		<@security.authorize url=_th.getUrl(link, _sc, true)>
			<#return true>
		</@security.authorize>
	<#else>
		<#return true>
	</#if>
	<#return false>
</#function>

<#macro printTitle isGroup>
<#if isGroup>
	<span class="inner expanded">
	<#nested>
	</span>
<#else>
	<#nested>
</#if>

</#macro>

<#macro printLink path title>
	<#local _isSelected=_th.isUrlPart("STARTWITH", [_th.getUrl(path, _sc, true)])>
	<a href="<@util.url path />" class="inner<#if _isSelected> selected</#if>">
		<#if _isSelected>
			<strong><@util.message title /></strong>
		<#else>
			<@util.message title />
		</#if>
	</a>
</#macro>