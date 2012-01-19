<#include "base.ftl">
 <#macro chemicalContent>
     <h2>About</h2>

<p>ChemicalTagger is a phrase-based semantic NLP tool for parsing the language of chemical experiments. It takes a string as input and produces an XML document as output. Tagging is based on a modular architecture and uses a combination of OSCAR4, domain-specific regex and English taggers to identify parts-of-speech. An ANTLR grammar is then used to
structure the tagged tokens into tree-based phrases which are then converted into an XML document. </p>

<h2>License and Warranty</h2>

<p>ChemicalTagger is licensed under the <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache License Version 2.0 </a> </p>
<p>ChemicalTagger is made available in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. </p>
<h2>Installation Instructions</h2>
<p>This online version of ChemicalTagger is a demonstration and can be downloaded from <a href="https://bitbucket.org/wwmm/chemicaltagger-webapp">here</a>.</p> 
<p>To use ChemicalTagger as a library either:</p>

<p>Download the chemicalTagger-1.3-jar-with-dependencies.jar from the <a href="https://bitbucket.org/wwmm/chemicaltagger/downloads">downloads page</a>.<p> 

<p>Or through maven by adding the following to your pom file:</p>


<p>Add our repository:</p>
  <xmp> <repository>
            <id>ucc-repo</id>
            <name>UCC Repository</name>
            <url>http://maven.ch.cam.ac.uk/m2repo</url>
 </repository> </xmp>     
      <p>  Then add the following under dependencies:</p>
  <xmp> <dependency>
            <groupId>uk.ac.cam.ch</groupId>
            <artifactId>chemicalTagger</artifactId>
            <version>1.3</version>
 </dependency> </xmp> 
  
 <p>The latest version of the code can be downloaded from our <a href="https://bitbucket.org/wwmm/chemicaltagger">bitbucket repository</a>.</p>
  
 <h2>ChemicalTagger Components</h2>
  
    <p>It has 2 main classes:</p>
    
    
       <h3> ChemistryPOSTagger</h3>
       <p> 
           This class adds syntactic structure to the input text. 
	       <p>It first performs some pre-processesing by:</p>
	        <ul> 
	       		<li>Normalising the text</li>
	       		<li>Running the SpectraTagger (Optional and only used for detecting NMR Spectra)</li>
	        </ul>
	        <p>It then tokenises the text using one of the following tokenisers:</p>
	        <ul> 
	       		<li>OscarTokeniser (default tokeniser and used for chemistry text)</li>
	       		<li>WhitespaceTokeniser (used for mainly non-chemistry text)</li>
	        </ul>
	        <p>Then finally it runs the following 3 taggers against the text:</p>
	        <ul>
	             <li>OSCAR (for chemical entities)</li>
	             <li>Regex (for recognising chemistry related entities)</li>
	             <li>OpenNLP (for English parts-of-speech)</li>
	       </ul>      
       </p>      
       <h3>ChemistrySentenceParser</h3> 
      
        <p>This class converts a tagged sentence into a parse tree as well as an XML document.</p>
        <p>It first outputs the AST(Abstract Syntax Tree) by using the generated Lexer and Parser files (generated from compiling the ANTLR ChemicalChunker.g file).</p>     
        <p>The AST is then converted into an XML document.</p>
        
     
  
  <h2>Running ChemicalTagger</h2>
  <p> To run ChemicalTagger you can either use the Utils convenience method</p>
  <blockquote>     
  	<p>Document doc = Utils.runChemicalTagger(text);<p>
  </blockquote>

  <p>Or for a more step-by-step method try the following:</p>
  <blockquote>     
    <p> ChemistryPOSTagger chemPos = ChemistryPOSTagger.getDefaultInstance();</p>
    <p>// Alternatively, if you want to reconfigure the tokenisers and taggers then try the following command</p>
    <p>//ChemistryPOSTagger chemPos = new ChemistryPOSTagger(ctTokeniser, oscarTagger, regexTagger, openNLPTagger)</p>
	<p>POSContainer posContainer = chemPos.runTaggers(text);</p>
	<p>// If you want to toggle priotiseOscar and useSpectraTagger then use the following command.</p>
	<p>//POSContainer posContainer = chemPos.runTaggers(inputSentence, prioritiseOscar, useSpectraTagger)</p>
	<p>ChemistrySentenceParser chemistrySentenceParser = new ChemistrySentenceParser(posContainer);</p>
	<p>	chemistrySentenceParser.parseTags();</p>
	<p>Document doc = chemistrySentenceParser.makeXMLDocument();</p>
  	
  </blockquote>
    
 	   
 </#macro>
  
<@page_html/>   
