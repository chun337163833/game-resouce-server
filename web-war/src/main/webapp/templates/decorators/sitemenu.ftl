<#import "/tags/util.ftl" as util/>
<div class="col col-side">
	<div id="side-menu">
		<#--ul>
			<li><@printLink path="/dial/service/" title="Service.title"/></li>	
			<li><@printLink path="/dial/carrental/" title="CarRental.title"/></li>		
			<li><@printLink path="/dial/representative/" title="Representative.title"/></li>
			<li><@printLink path="/dial/servicepackage/" title="ServicePackage.title"/></li>	
			<li><span class="inner expanded<#if tagTemplateHelper.isUrlPart("STARTWITH", ["/app/localizeddial/"])> selected</#if>"><@util.message "Dials.common.title"/></span>
				<ul>
					<li><@printLink path="/localizeddial/cartype/" title="CarType.title"/></li>	
					<li><@printLink path="/localizeddial/missingequipment/" title="MissingEquipment.title"/></li>	
					<li><@printLink path="/localizeddial/optionalequipment/" title="OptionalEquipment.title"/></li>	
					<li><@printLink path="/localizeddial/cardamage/" title="CarDamage.title"/></li>	
					<li><@printLink path="/localizeddial/damagetype/" title="DamageType.title"/></li>	
					<li><@printLink path="/localizeddial/staticdescription/" title="CarStaticDescriptionDamageCar.title"/></li>	
				</ul>
			</li>	
		</ul-->
			<ul>
		<li><a href="" class="inner">Zákazníci</a></li>	
		<li><a class="inner selected"><strong>Vozidla</strong></a></li>	
		<li><a href="" class="inner">Řidiči</a></li>
		<li><a href="" class="inner">Firmy</a></li>
		<li><a href="" class="inner">Uživatelé</a></li>
		<li><span class="inner expanded">Číselník položek</span>
			<ul>
				<li><a href="" class="inner">Typ zákazníka</a></li>	
				<li><a href="" class="inner">Počet osob</a></li>	
				<li><a href="" class="inner">Poškození vozidla</a></li>	
			</ul>
		</li>	
	</ul>
	</div>
</div>

<#macro printLink path title>
	<#local _fullpath="/app" + path>
	<#local _isSelected=tagTemplateHelper.isUrlPart("STARTWITH", [_fullpath])>
	<a href="<@util.url _fullpath />" class="inner<#if _isSelected> selected</#if>">
		<#if _isSelected>
			<strong><@util.message title /></strong>
		<#else>
			<@util.message title />
		</#if>
	</a>
</#macro>