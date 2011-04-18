<#include "base.ftl">
 <#macro chemicalContent>
     <h3>About:</h3>
ChemicalTagger is as a medium-depth, phrase-based semantic NLP tool for
the language of chemical experiments. Tagging is based on a modular architecture and uses a combination of
OSCAR, domain-specific regex and English taggers to identify parts-of-speech. ANTLR grammar is used to
structure this into tree-based phrases. 
It takes a string sentence as an input and produces an XML document as an output.


  <h3>Installation Instructions</h3>
  The source code resides on <a href="">bitbucket</a>
To use ChemicalTagger as a library add chemicalTagger-1.0.0-jar-with-dependencies.jar to your classpath, this can be downloaded here. 

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
  
       	   
 </#macro>
<@page_html/>   
