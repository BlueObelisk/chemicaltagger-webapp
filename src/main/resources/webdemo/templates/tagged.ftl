<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.0 Transitional//EN' 'http://www.w3.org/TR/1998/REC-html40-19980424/loose.dtd'> 
<html>
   <head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link rel="stylesheet" href="res/global/style/layout.css" type="text/css" media="all" />
   <link rel="stylesheet" href="res/global/style/print.css" type="text/css" media="print" />
    <link rel="Shortcut Icon" href="http://www.cam.ac.uk/templates/favicon.ico" />

     <meta http-equiv='Content-type' content='text/html;charset=UTF-8'/>
	      <script type='text/javascript' src='res/jquery-latest.js'></script>
	      <script type='text/javascript' src='res/functions.js'></script>
      <title>ChemicalTagger Demonstration</title>
      <link rel='stylesheet' type='text/css' href='res/style-extract.css'/>
   
   <style type="text/css" media="all">
  div#content-primary img#depiction {
    display: inline;
    float: none;
    border: 0;    
  }
  pre#cml {
    max-width: 100%;
    overflow: scroll;
  }
  span#last-update {
    font-weight: bold; 
  }
</style>
   </head>
   <body class="home">
   <div id="skip"> <a href="#skip-content" accesskey="2">Skip to content</a> </div>
   <div id="header">
   <div id="branding"><a href="http://www.cam.ac.uk/" accesskey="1"><img src="res/global/images/identifier4.gif" alt="University of Cambridge" class="ucam" /></a><a href="http://www-ucc.ch.cam.ac.uk"><img src="res/global/ucc.png" alt="Unilever Centre for Molecular Science Informatics" class="ucc-logo" width="118" height="50" /></a></div>
   </div><!--- #header ends -->
   <div id="dept-title">
  <h1>ChemicalTagger</h1>
</div>
   <div id="container"> <a name="skip-content" id="skip-content"></a>

<div id="content-primary">
<ul id="nav-breadcrumb">
  <li class="first"><a href="http://www.cam.ac.uk/">University of Cambridge</a></li>
  <li><a href="http://www.ch.cam.ac.uk/">Department of Chemistry</a></li>
  <li><a href="http://www-ucc.ch.cam.ac.uk/">Unilever Centre for Molecular Science Informatics</a></li>
</ul>
	     <div id='taggedReaction'>
	     ${taggedText}
	     </div>
	     
	     <#list checkBoxes?keys?sort as chkBoxName>
	            <input type='checkbox' name=${chkBoxName} onclick='checkedAll(${chkBoxName}Form,checked);' ><b>${chkBoxName}</b>:
	      <form id ='${chkBoxName}Form'>
	      
	          
	          <#list checkBoxes[chkBoxName] as chkBoxContent>      
	                 	  <span name=${chkBoxContent}><input type='checkbox' name='${chkBoxContent}' onClick='highlight("${chkBoxContent}",checked)'>${chkBoxContent}</span>
	          
	       	</#list>
	      </form>
	
	     </#list>
	     <form action="/viewXML" method="POST" >
	           <textarea style="display:none" rows="20" cols="10" name="xml" type="hidden">${xmlContent}</textarea>
	           <input type='submit'  value="View XML">
	     </form>
	     </div>
	     </div>
	</body>
	</html>