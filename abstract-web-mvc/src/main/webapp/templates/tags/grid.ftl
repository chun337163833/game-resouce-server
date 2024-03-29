<#import "util.ftl" as util />
<#import "form.ftl" as form />
<#macro grid data baseCaption="_NULL_" customGrid=false commandName="_NULL_">
	<#assign _baseCaptionGrid=baseCaption>
	<#assign _filteredList=data>
	<#assign _commandName=commandName>
	<#if util.isNull(_commandName)>
		<#assign _commandName="entities">
	</#if>
	<#if util.isNull(baseCaption)>
		<#assign _baseCaptionGrid="Grid">
	</#if>
	<#nested>
</#macro>
<#macro filter class="" showHeader=true>
	<@form.form method="get" id=id commandName=_commandName isFilter=true renderSaveButton=false baseCaption=_baseCaptionGrid class=class>
		<input type="hidden" name="_filterAction" value="filter" />
		<input type="hidden" name="page" id="page-input" value="${_formModel.readValue("page")}">
		<input type="hidden" name="sortBy" id="sortby-input" value="${_formModel.readValue("sortBy")}">
		<input type="hidden" name="sortOrder" id="sortorder-input" value="${_formModel.readValue("sortOrder")}">
		<#if showHeader>
			<div class="form-header">
				<h2 class="title"><@util.message (_implClass!"Grid")+".filter.title" /></h2>
			</div>
		</#if>
		<#nested>
		<@form.link href="?clearfilter" isBig=false>
			<@util.message (_implClass!"Grid")+".clearfilter.button" />
		</@form.link>
		<@form.submit commandName=_commandName title=(_implClass!"Grid")+".filter.button" isBig=false/>
	</@form.form>
</#macro>
<#macro datalist showNewButton=true renderButtons=true idColumn="_NULL_" multipleButtons=[] showHeader=true>
	<@list data=_filteredList.data renderButtons=renderButtons idColumn=idColumn multipleButtons=multipleButtons showHeader=showHeader showNewButton=showNewButton; row>
		<#nested row>
	</@list>
</#macro>
<#macro list data renderButtons=true idColumn="_NULL_" multipleButtons=[] showNewButton=true showHeader=true> 
	<#assign _listdata = data />
	<#assign _columnSettingsMapping=[]>
	<#local renderCheckbox=false>
	<#if !util.isNull(idColumn) && (multipleButtons?size>0)>
		<#local renderCheckbox=true>
	</#if>
	<#assign _columnscount=1>
	<div class="datagrid">
		<#if showHeader>
			<div class="datagrid-header">
				<h2 class="title"><@util.message _baseCaptionGrid + ".list.title" /></h2>
				<#if showNewButton>
					<div class="btns">
						<a class="btn add-comment" href="new">
							<span><@util.message "grid.list.button.new" /></span>
						</a>
					</div>
				</#if>
			</div>
		</#if>
		<table>
			<thead>
				<tr>
					<#if renderCheckbox>
						<th><input type="checkbox" name="_check_all" /><input type="hidden" name="__multid" value="on"/></th>
					</#if>
					<#nested>
					<#if renderButtons>
						<@renderActionButtons />
					</#if>
				</tr>
			</thead>
			<tbody>
			<#if (data?size > 0)>
				<#list data as row>
					<#assign _rowdata = row />
					<tr <#if !row_has_next>class="last"</#if>>
						<#if !util.isNull(idColumn)>
							<#assign _detailId=_th.evaluate(idColumn, _rowdata)>
						</#if>
						<#if renderCheckbox>
							<td>
								<input type="checkbox" name="_multid" value="${_detailId}"/>
							</td>
						</#if>
						<#nested _rowdata>
						<#if renderButtons>
							<@renderActionButtons />
						</#if>
					</tr>
				</#list>
			<#else>
				<tr>
					<td colspan="${_columnscount}" class="center">
						<@util.message "grid.list.noresult.found" />
					</td>
				</tr>
			</#if>
			</tbody>
		</table>
		<div class="datagrid-footer">
			<#if (multipleButtons?size > 0)>
				<div class="l datagrid-btns">
					<@util.message "grid.list.multiple.actions.title" />:
					<#list multipleButtons as btn>
						<button class="btn" id="multiple-${btn}"><span><@util.message "grid.list.button.multiple."+btn /></span></button>
					</#list>
				</div>
			</#if>
			<@pagination />
		</div>
	</div>
</#macro>

<#-------custom column ----->
<#macro customColumn title sortable=true defaultCaption="_NULL_" captionArgs=[]>
	<#if _rowdata??>
		<#nested _rowdata>
	<#else>
		<@headColumn name=name title=title defaultCaption=defaultCaption captionArgs=captionArgs sortable=sortable />
	</#if>
</#macro>

<#------- resolver column ----->
<#macro column name sortable=true isId=false isPublish=false defaultCaption="_NULL_" captionArgs=[] isDetail=false localized=false>
	<#if _rowdata??>
		<@bodyColumn columnName=name isId=isId isDetail=isDetail localized=localized isPublish=isPublish; e, f, v, n>
			<#nested e, f, v, n>
		</@bodyColumn>
	<#else>
		<@headColumn sortable=sortable columnName=name defaultCaption=defaultCaption captionArgs=captionArgs />
	</#if>
</#macro>

<#-------body column ----->
<#macro bodyColumn columnName isId=false isDetail=false localized=false isPublish=false>
	<#assign _value=_th.evaluate(columnName, _rowdata)!>
	<#local _nestedContent><#nested _rowdata, columnName, _value, false></#local>
	<@printTableCell>
		<#if _nestedContent?? && (_nestedContent?trim?length>0)>
			${_nestedContent}
		<#else>
			<#local _convertedValue=_th.convertToStringWithSource(columnName, _rowdata)!"">
			<#if localized>
				<#local _convertedValue=util.getMessage(_convertedValue)>
			</#if>
			<#if isId>
				<a href="${_convertedValue}">${_convertedValue}</a>
			<#elseif isDetail && _detailId??>
				<a href="${_detailId}">${_convertedValue}</a>
			<#else>
				<#if _value?is_boolean && isPublish>
					<a href="?_option=${_value?string('false', 'true')}&_multid=${_th.resolveId(_rowdata)}" class="prompt post">
						<#if _value>
							<span class="ico ico-public" title="<@util.message "grid.list.published" />"></span>
						<#else>
							<span class="ico ico-unpublic" title="<@util.message "grid.list.unpublished" />"></span>
						</#if>
					</a>
				<#else>
					${_convertedValue}
				</#if>
			</#if>
		</#if>
	</@printTableCell>
</#macro>



<#-------header column----->
<#macro headColumn columnName="_NULL_" title="_NULL_" defaultCaption="_NULL_" captionArgs=[] sortable=true>
	<#local _key="${form.buildMessageString(_baseCaptionGrid, columnName)}" />
	<#if !util.isNull(defaultCaption)>
		<#local _key=defaultCaption>
	</#if>
	<#local _sortOrder=_filteredList.filter.sortBy.sortDirection>
	<#local _sortBy=_filteredList.filter.sortBy.column!RequestParameters.sortBy!"">
	<th class="sort">
		<#t>
		<#local _isSorted=_sortBy==columnName>
		<#local _title>
			<#if !util.isNull(title)>
				${title}
			<#else>
				<@util.message code=_key arguments=captionArgs />
			</#if>
			<#if _isSorted>
				<span class="ico ico-sort-<#if _sortOrder=="DESC">down<#else>up</#if>"></span>
			</#if>
		</#local>
		<#if sortable>
			<#local _filterString=createFilterUrl(["sortBy", "sortOrder"])>
			<#local pagingClass="">
			<#local _pageParameter="?"+_filterString+"page=">
			<#local _currentOrder=_sortOrder>
			<#if _sortOrder=="DESC"><#local _currentOrder="ASC">
			<#else><#local _currentOrder="DESC"></#if>
			<a href="?${_filterString}sortOrder=${_currentOrder}&sortBy=${columnName}&page=${_filteredList.currentPage}" class="<#if _isSorted> sorted</#if>">${_title}</a>
		<#else>
			${_title}
		</#if>
	</th>
</#macro>

<#--- pagination ----->

<#macro pagination nonjs=false>
	<#local _currentPage=_filteredList.filter.page.currentPageNumber/>
	<#local _recPerPage=_filteredList.filter.page.objectsPerPage/>
	<#local _fullSize=_filteredList.fullDataSize/>
	<#local _lastPage=(_fullSize/_recPerPage)?ceiling/>
	<#if (_fullSize >_filteredList.filter.page.objectsPerPage)>
		<#local _filterString=createFilterUrl(["page"])>
		<#local pagingClass="">
		<#local sortingString= "sortBy=" + _filteredList.filter.sortBy.column + "&sortOrder=" + _filteredList.filter.sortBy.sortDirection + "&">
		<#local _pageParameter= "?" + _filterString + sortingString + "page=">

		<div class="r pager">
	
			<#if _currentPage!=1>
				<a href="${_pageParameter}${_currentPage-1}" class="prev ${pagingClass}"><span class="ico ico-prev"></span><@util.message code="grid.filter.paging.previous" /></a>
			</#if>
			<span class="class="paging">
			<#list 1.._lastPage as i>
				<#if i==_currentPage>
					<strong>${i}</strong>
				<#elseif (i == _lastPage) || (i == 1) || (i >= _currentPage-2 && i <= _currentPage+2)>
					<a href="${_pageParameter}${i}" class="${pagingClass}">${i}</a>
				<#elseif (i < _currentPage-2 && i >= _currentPage-3) || (i > _currentPage+2 && i <= _currentPage+3)>
					&hellip;
				</#if>
			</#list>
			</span>
			<#if (_currentPage<_lastPage)>
				<a href="${_pageParameter}${_currentPage+1}" class="next ${pagingClass}"><@util.message code="grid.filter.paging.next" /><span class="ico ico-next"></span></a>
			</#if>			
		</ul>
		</div>
	</#if>
</#macro>

<#-- action buttons base on javascript --->
<#macro renderActionButtons renderEdit=true renderCancel=true renderRemove=true buttons=[]>
	<#local _deff=buttons>
	<#if (buttons?size == 0)>
		<#local _deff=createDefaultButtonsDefinition(renderEdit, renderCancel, renderRemove)>
	</#if>
	<#list _deff as def>
		<#if !_rowdata??>
			<#assign _columnscount=_columnscount+1>
			<th></th>
		<#else>
			<#assign lck= def.locked?? && def.locked>
			<td>
			<#if !lck>
				<a href="${def.url}" title="${def.title}" <#if def.placeholder??>placeholder="${def.placeholder}"</#if> <#if def.class??>class="${def.class}"</#if>>
				${def.text}
				</a>
			<#else>
				${def.text}
			</#if>
			</td>
		</#if>
	</#list>
	
</#macro>


<#---- useful functions ----->
<#macro printTableCell>
	<td>
		<#nested/>
	</td>
</#macro>

<#function createDefaultButtonsDefinition renderEdit renderCancel renderRemove>
	<#local _id=_th.resolveId(_rowdata)!"#">
	<#local _buttons=[]>
	<#if renderEdit>
		<#local _buttons=_buttons + [{"url":_id, "title":util.getMessage("grid.list.button.edit"), "text":'<span class="ico ico-edit"></span>'}]>
	</#if>
	<#if renderCancel>
		<#local _buttons=_buttons + [{"url":_id, "title":util.getMessage("grid.list.button.cancel"), "text":'<span class="ico ico-cancel"></span>'}]>
	</#if>
	<#if renderRemove>
		<#local _buttons=_buttons + [{"url":_id, "title":util.getMessage("grid.list.button.delete"), "text":'<span class="ico ico-storno"></span>'}]>
	</#if>
	<#return _buttons>
</#function>

<#function createFilterUrl ignoreKeys=[]>
	<#local d=""/>
	<#list _filteredList.filter.conditions?keys as con>
		<#if !isKeyIgnored(con, ignoreKeys)>
			<#local d=d+con+"="+_filteredList.filter.conditions[con]/>
			<#local d=d + "&"/>
		</#if>
	</#list>
	<#return d />
</#function>

<#function isKeyIgnored key ignoreKeys>
	<#list ignoreKeys as k>
		<#if key==k>
			<#return true>
		</#if>
	</#list>
	<#return false>
</#function>






























