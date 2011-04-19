<#include "base.ftl">
 <#macro chemicalContent>
     <h3>About:</h3>

ChemicalTagger is as a phrase-based semantic NLP tool for parsing the language of chemical experiments. Tagging is based on a modular architecture and uses a combination of OSCAR, domain-specific regex and English taggers to identify parts-of-speech. ANTLR grammar is used to
structure this into tree-based phrases. It takes a string as input and produces an XML document as output. A demo of ChemicalTagger can be found <a href="http://chemicaltagger.ch.cam.ac.uk/">here</a>


 <h3>Installation Instructions</h3>
  
 The source code resides on <a href="https://bitbucket.org/lh359/chemicaltagger">bitbucket</a>
To use ChemicalTagger as a library add chemicalTagger-1.0.jar to your classpath, this can be downloaded <a href="https://maven.ch.cam.ac.uk/content/repositories/releases/chemicalTagger/chemicalTagger/1.0/chemicalTagger-1.0.jar">here</a>. 

If you are using Maven then do the following:
Add our repository:
  <p>        <repository>
            <id>ucc-repo</id>
            <name>UCC Repository</name>
            <url>http://maven.ch.cam.ac.uk/m2repo</url>
        </repository>
  </p>      
        Then add:
          <p>
          <dependency>
            <groupId>uk.ac.cam.ch</groupId>
            <artifactId>chemicalTagger</artifactId>
            <version>1.0.1</version>
        </dependency>
  </p> 
  
  <h3>ChemicalTagger Components</h3>
  
    This package is used for marking up experimental sections in chemistry papers:
    It has 3 main classes:
    
       <h4> ChemistryPOSTagger</h4> 
        
        This class takes a sentence and runs it against three taggers:
             <li>OSCAR (for chemical entities)</li>
             <li>Regex (for recognising chemistry related entities)</li>
             <li>OpenNLP (for english parts of speech)</li>
             
       <h4>ChemistrySentenceParser</h4> 
        
           This class converts a tagged sentence into a parseTree. It uses a lexer and parser generated
        by the Antlr grammar.     
                 
        <h4>ASTtoXML</h4>
        This class converts an abstract tree into an XML document.
     
  
  <h3>Running ChemicalTagger</h3>
  To run ChemicalTagger you can either use the Utils convenience method     
  Document doc = Utils.runChemicalTagger(text);
  Documentation for this class can be found here
  
 	   
 </#macro>
  
<@page_html/>   
