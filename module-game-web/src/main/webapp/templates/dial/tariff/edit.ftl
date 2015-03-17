<#import "/spring.ftl" as spring />
<#import "/tags/form.ftl" as form/>
<#import "/tags/util.ftl" as util/>
<html>
<head><title><@util.message _implClass+".title"/></title></head>
<body>
<@form.form commandName="entity" baseCaption=_implClass renderTitle=true additionalErrors=["basicDiscountExist"]>
		
		<div class=" col grid-t col-t-3">
			<div class="box">
				<div class="box-header">
					<h3 class="title"><@util.message "Form.section.basicParams" /></h3>
				</div>
				<div class="box-content">
					<div class="box-content-inner">
						<ul class="reset params">
							<li><@form.checkbox path="basicDiscount" breakLabel=false labelFirst=false /></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col grid-2t col-t-1">
			<@form.inputText path="tariffName" />
			<@form.inputNumber path="discount" />
		</div>
</@form.form>		
</body>
</html>
