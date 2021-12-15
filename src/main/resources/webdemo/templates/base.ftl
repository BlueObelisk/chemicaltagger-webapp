<#macro page_head> <title>ChemicalTagger Demonstration</title></#macro>
<#macro chemicalContent> 
	          Insert chemical content here
	          </#macro>
<#macro page_html> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
   <head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link rel="stylesheet" href="res/global/style/layout.css" type="text/css" media="all" />
   <link rel="stylesheet" href="res/global/style/print.css" type="text/css" media="print" />
   <link rel="Shortcut Icon" href="http://www.cam.ac.uk/templates/favicon.ico" />
   <meta http-equiv='Content-type' content='text/html;charset=UTF-8'/>
   		      <script type='text/javascript' src='res/jquery-latest.js'></script>
		      <script type='text/javascript' src='res/functions.js'></script>
		      <script type='text/javascript' src='res/imgpreview.0.23.js'></script>
		
		      <script type="text/javascript"
    src="http://maps.google.com/maps/api/js?sensor=true"></script>
   <link rel='stylesheet' type='text/css' href='res/style-extract.css'/>  
           <@page_head/> 
<style type="text/css">
  html { height: 100% }
  body { height: 100%; margin: 0px; padding: 0px }
  map_canvas { height: 100% ; width:100%; }
</style>
   </head>
   <#if Location??>
   <body onload="initialize(${Longitude},${Latitude},'${Location}','${Campaign}','${Molecules}')">
<#else>
<body onload="initialize(-25.363882,131.044922,'Australia','CHABLIS','water, THF, DMAP')">
</#if>
   <div id="skip"> <a href="#skip-content" accesskey="2">Skip to content</a> </div>
   <div id="header">
	   <div id="branding"><a href="http://www.cam.ac.uk/" accesskey="1"><img src="res/global/images/identifier4.gif" alt="University of Cambridge" class="ucam" /></a></div>
   </div><!--- #header ends -->
   <div id="dept-title">

      <h1>ChemicalTagger</h1>
   </div>
   
   <div id="container" > <a name="skip-content" id="skip-content"></a>
   
      <div id="content" >
         <div id="content-primary" >
           <ul id="nav-breadcrumb">
            <li class="first"><a href="http://www.cam.ac.uk/">University of Cambridge</a></li>

            <li><a href="http://www.ch.cam.ac.uk/">Department of Chemistry</a></li>
            <li><a href="http://www-cmi.ch.cam.ac.uk/">Centre for Molecular Informatics</a></li>
          </ul>
         <div>
                   <@chemicalContent/> 
            
            </div>
         </div>
         
	     <div id="content-secondary">
					  <dl class="links">
					    <dt>Links</dt>
					    <dd><a href="index.html">Web Interface</a></dd>
					    <dd><a href="instructions.html">Instructions</a></dd>
					    <dd><a href="http://www.javadoc.io/doc/uk.ac.cam.ch.wwmm/chemicalTagger/1.6.1">Documentation</a></dd>
					    <dd><a href="http://www.jcheminf.com/content/3/1/17">Publication</a></dd>
					    <dd><a href="https://github.com/BlueObelisk/chemicaltagger">GitHub Project</a></dd>
					    <dd><a href="https://github.com/BlueObelisk/chemicaltagger/releases">Downloads</a></dd>
					  </dl>
		</div>
      </div>
	  <!-- end of #content -->
			
	  <ul id="site-info">
	  <li class="copy">&copy; 2009-2012 <a href="mailto:lhawizy[atsign]gmail.com">Lezan Hawizy</a>, David Jessop, Daniel Lowe, Hannah Barjat, Centre for Molecular Informatics</li>
  	  <li class="link last"><a href="http://www.cam.ac.uk/about-this-site/privacy-policy">Privacy</a></li>
	  <li class="link"><a href="http://www.cam.ac.uk/about-this-site/accessibility">Accessibility</a></li>
  	  </ul>


   </div>
   
   </body>
</html>
</#macro> 
