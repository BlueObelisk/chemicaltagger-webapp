<#include "base.ftl">
 <#macro chemicalContent>

 <div id='taggedReaction'>
					     	${taggedText}
					    </div>
					     
						  <#list checkBoxes?keys?sort as chkBoxName>
						    <input type='checkbox' name="${chkBoxName}" onclick='checkedAll($("#${chkBoxName}Form"),checked);' ><b>${chkBoxName}</b>:
					           <form id ='${chkBoxName}Form'>
						          <#list checkBoxes[chkBoxName] as chkBoxContent>      
						          <span name='${chkBoxContent}'><input type='checkbox' name="${chkBoxContent}" onClick='highlight("${chkBoxContent}",checked)'>${chkBoxContent}</span>
						          </#list>
					          </form>
				          </#list>
				          
				      
					     <form action="/viewXML" method="post" >
					           <textarea style="display:none" rows="20" cols="10" name="xml" type="hidden">${xmlContent}</textarea>
					           <input type='submit'  value="View XML">
					     </form>
					     
 </#macro>
<@page_html/>   
