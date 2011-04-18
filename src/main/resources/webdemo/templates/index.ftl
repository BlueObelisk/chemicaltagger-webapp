<#include "base.ftl">
 <#macro chemicalContent>
 	          <form action="/submit" method="post">
	            <p>ChemicalTagger is an open-source tool that uses OSCAR and NLP techniques for tagging and parsing experimental sections in the chemistry literature.</p>
		        <p>To use this demo, enter some chemical text and click the 'Process Text' button</p>
                      <p>
		        <textarea rows="20" cols="80" name="body">To a stirred solution of 4-hydroxypiperidine (0.97 g, 9.60 mmol) in anhydrous dimethylformamide (20 mL) at 0°C was added 1-(bromomethyl)-4-methoxybenzene (1.93 g, 9.60 mmol) and triethylamine (2.16 g, 21.4 mmol). The reaction mixture was then warmed to room temperature and stirred overnight. After this time the mixture was concentrated under reduced pressure and the resulting residue was dissolved in ethyl acetate (40 mL), washed with water (20 mL) and brine (20 mL) before being dried over sodium sulfate. The drying agent was filtered off and the filtrate concentrated under reduced pressure. The residue obtained was purified by flash chromatography (silica gel, 0-5% methanol/methylene chloride) to afford 1-(4-methoxybenzyl)piperidin-4-ol as a brown oil (1.70 g, 80%).</textarea> 

                <br/>
		        <input type="submit" value="Process Text" /></p>
	          </form>
 </#macro>
<@page_html/>   
