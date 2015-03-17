<#import "/tags/util.ftl" as util>
<!DOCTYPE html>
<!--[if IE 8 ]><html lang="cs" class="ie ie8"><![endif]-->
<!--[if IE 9 ]><html lang="cs" class="ie ie9"><![endif]-->
<!--[if gt IE 9]><!--><html lang="cs" class="ie"><!--<![endif]-->
<!--[if !(IE)]><!--><html lang="cs"><!--<![endif]-->
	<head>
		<meta http-equiv="content-Type" content="text/html; charset=utf-8" />
		<title>${title}</title>
		<#-- css -->
		<link rel="stylesheet" href="<@util.url "/resources/css/content.css" />" type="text/css" media="projection,screen,print">	
		<link rel="stylesheet" href="<@util.url "/resources/css/layout.css" />" type="text/css" media="projection,screen">	
		<link rel="stylesheet" href="<@util.url "/resources/css/grid.css" />" type="text/css" media="projection,screen">	
		<link rel="stylesheet" href="<@util.url "/resources/css/icon.css" />" type="text/css" media="projection,screen">	
		<link rel="stylesheet" href="<@util.url "/resources/css/print.css" />" type="text/css" media="print">
		<link rel="stylesheet" href="<@util.url "/resources/css/lib/fancybox/jquery.fancybox.css" />" type="text/css" media="projection,screen">
		<link rel="stylesheet" href="<@util.url "/resources/css/lib/datepicker/jquery.datepicker.css" />" type="text/css" media="projection,screen">
		
		<script type="text/javascript">
			document.documentElement.className += " js";
		</script>
		${head!""}
	</head>
<body>

	<p id="nav-skip">
		<a href="#main" accesskey="2">Přejít k obsahu</a> 
		<span class="hidden">|</span> 
		<a href="#main-menu">Přejít k navigaci</a> 
	</p>

	<div id="header">
		<div class="row row-main">
			<div class="header-inner">
				<div class="header-inner-inner">
					<p id="logo">
						<a href="/" class="logo" title="Zpět na hlavní stranu">Atlant<strong>TAXI</strong></a>
						<span class="claim">Evidence taxi služby</span>
					</p>
										<p id="user-menu">
						<span class="ico ico-person"></span>Jiří Novák
						<a href=""><span class="ico ico-logout"></span>Odhlásit se</a>
					</p>
					
					<div id="main-menu">
						<ul class="reset">
							<li><a href="./hlavni-strana">Objednávky<strong class="newOrderCount">4</strong></a></li>
							<li><a href="">Oznámení</a></li>
							<li><a href="">Hodnocení</a></li>
							<li><a href="./ciselniky">Nastavení</a></li>
						</ul>
					</div>
					
					<div id="newOrder-form">
						<div class="inner">						
							<span class="inp-fix inp-fix-right-open"><input id="" class="inp-text" type="text" name="" placeholder="Telefonní číslo zákazníka" /></span>
							<button type="submit" class="btn btn-left-open"><span>Objednat</span></button>
						</div>		
					</div>		
				</div>
			</div>			
		</div>
	</div>

	<div id="main">
		<div class="row-main">
			<#if _isDial??>
	        	<h1>${title}</h1>
	        	<div class="col col-content">${body}</div>
				<#include "sitemenu.ftl">
			<#else>
				${body}
		    </#if>
		</div>
	</div>

	<div id="footer">
		<div class="row row-main">
			<p id="copyright">&copy; <a href="/">2015 Atlant<strong>TAXI</strong></a>, všechna práva vyhrazena<#if !_th.isProduction()> - ${_th.getEnvironment()}</#if></p>
		</div>
	</div>
	
	</div>
</div>

<script src="<@util.url "/resources/js/main.js" />" typei="text/javascript"></script>
<script src="<@util.url "/resources/js/widget.FormExpand.js" />" type="text/javascript"></script>
<script src="<@util.url "/resources/js/widget.js" />" type="text/javascript"></script>
<script src="<@util.url "/resources/js/lib/sockjs-0.3.4.js" />" type="text/javascript"></script>
<script src="<@util.url "/resources/js/lib/stomp.js" />" type="text/javascript"></script>
<script src="<@util.url "/resources/js/lib/jquery.min.js" />" type="text/javascript"></script>
<script src="<@util.url "/resources/js/lib/jquery.datepicker.js" />" type="text/javascript"></script><script src="<@util.url "/resources/js/lib/sockjs-0.3.4.js" />" type="text/javascript"></script>
<script src="<@util.url "/resources/js/lib/jquery.datepicker-cs.js" />" type="text/javascript"></script>
<script src="<@util.url "/resources/js/lib/fancybox/jquery.fancybox.js" />" type="text/javascript"></script><script src="<@util.url "/resources/js/lib/sockjs-0.3.4.js" />" type="text/javascript"></script>
<script src="<@util.url "/resources/js/lib/fancybox/jquery.mousewheel.js" />" type="text/javascript"></script>

</body>
</html>
