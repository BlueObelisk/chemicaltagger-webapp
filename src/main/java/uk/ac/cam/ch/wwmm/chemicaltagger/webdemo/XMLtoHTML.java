package uk.ac.cam.ch.wwmm.chemicaltagger.webdemo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.commons.lang.StringUtils;

import uk.ac.cam.ch.wwmm.acpgeo.CoordinatesLoader;
import uk.ac.cam.ch.wwmm.chemicaltagger.ExtractFromXML;

public class XMLtoHTML {

	/*******************************
	 * @author lh359 Converts xml to spantag html
	 */
	public static String SPACE_DELIMITER = " ";
	public static String SPAN_BEGIN = "<span name=";
	public static String SPAN_END = "</span>";

	public Set<String> actionCheckSet;
	public Set<String> phraseCheckSet;
	public Set<String> conditionCheckSet;
	public Set<String> moleculeCheckSet;
	public Set<String> oscarOntCheckSet;
	public Set<String> quantityCheckSet;
	public Set<String> toolsCheckSet;
	public Set<String> citationCheckSet;
	public String taggedText;
	public HashMap<String, Set<String>> checkBoxes;
	private HashMap<String, String> geoInfo;
	public void convert(Document doc) {
		actionCheckSet = new HashSet<String>();
		phraseCheckSet = new HashSet<String>();
		conditionCheckSet = new HashSet<String>();
		moleculeCheckSet = new HashSet<String>();
		oscarOntCheckSet = new HashSet<String>();
		quantityCheckSet = new HashSet<String>();
		toolsCheckSet = new HashSet<String>();
		citationCheckSet = new HashSet<String>();
		
		taggedText = new String();
		
		taggedText = SPAN_BEGIN + "'Document'>" + getHTMLBody(doc.getRootElement(), SPAN_END
				+ SPACE_DELIMITER) + SPAN_END;


        checkBoxes = getCheckBoxContent();
		geoInfo =  getGeoInfo(doc);
		
	}




	public HashMap<String, String> getGeoInfo(Document doc) {
		CoordinatesLoader gawCoordinates = new CoordinatesLoader();
		HashMap<String, String> coordsMap = gawCoordinates.getSiteCoordsMap();
		HashMap<String, String> mapInfo = new HashMap<String, String>();
		
		Nodes locationNodes = doc.query("//LOCATION[descendant-or-self::NNP-STATION]");
//		if (locationNodes.size() == 0) 
//			locationNodes = doc.query("//LocationPhrase[descendant-or-self::NNP-STATION]");
		
		
		List<String> moleculeList = new ArrayList<String>();

		if (locationNodes.size() > 0) {
			String longitude = "";
			String latitude = "";
			String asl ="";
			String locationName = ExtractFromXML.getStringValue((Element)locationNodes.get(0)," ").trim();
			System.out.println(locationName);
			mapInfo.put("Location",locationName);
			
			Nodes campaignNodes = doc.query("//CAMPAIGN");
			if (campaignNodes.size() > 0) {
				mapInfo.put("Campaign", ExtractFromXML.getStringValue((Element)campaignNodes.get(0)," "));	
			}
			Nodes moleculeNodes = doc.query("//MOLECULE[ancestor-or-self::ActionPhrase]");
			for (int i = 0; i < moleculeNodes.size(); i++) {
				String molecule = ExtractFromXML.getStringValue((Element)moleculeNodes.get(i)," ");
				if (!moleculeList.contains(molecule))
					moleculeList.add(molecule);
			}
			if (coordsMap.containsKey(locationName)){
				String longLat[] = coordsMap.get(locationName).split(" ");
				System.out.println(longLat);
				longitude = longLat[0].split("\u00b0|\u00ba")[0].trim();
				latitude = longLat[1].split("\u00b0|\u00ba")[0].trim();
				asl = longLat[2].trim();
				if (longLat[0].contains("S")) longitude = "-"+longitude;
				if (longLat[1].contains("W")) latitude = "-"+latitude;
				mapInfo.put("Longitude", longitude);
				mapInfo.put("Latitude", latitude);
				mapInfo.put("Asl", asl);
			}
			mapInfo.put("Molecules",StringUtils.join(moleculeList.toArray(),", "));
			
			System.out.println(mapInfo);
		}	
		
		return mapInfo;
	}


	/****************************************
	 * Parses an XML document and converts to an HTML string
	 * 
	 * @param sourceFile
	 *            (String)
	 * @return docContainer (DocumentContainer)
	 ****************************************/
	public String getHTMLBody(Element xmlTag, String Delimiter) {
		StringBuilder stringValue = new StringBuilder();
		for (int i = 0; i < xmlTag.getChildCount(); i++) {
			if (xmlTag.getChild(i) instanceof nu.xom.Text) {
				stringValue.append(xmlTag.getChild(i).getValue().trim());

			} else {
				if (!(xmlTag.getChild(i) instanceof nu.xom.ProcessingInstruction)) {
					Element sub = (Element) xmlTag.getChild(i);
					stringValue.append(makeSpanBegin(sub));

					if (sub.getChildCount() > 0) {
						if (sub.getLocalName().equals("MOLECULE"))
							stringValue.append(getHTMLBody(sub, Delimiter)+ "</a>"+Delimiter);
						else 	stringValue.append(getHTMLBody(sub, Delimiter)+ Delimiter);
					} else if (hasMoreChildren(sub)) {
						if (sub.getLocalName().equals("MOLECULE"))
							stringValue.append(getHTMLBody(sub, Delimiter)+ "</a>"+Delimiter);
						else 	
						stringValue.append(getHTMLBody(sub, Delimiter)+Delimiter);
					} else {
							
						stringValue.append(sub.getValue() + Delimiter);
						
					}
				}
			}
		}
		/***************
		 * Replace the non-breaking space with a normal space
		 */
		return stringValue.toString().replace(" ", " ").replace("=", "=")
				.replace("   ", " ");
	}

	private String makeSpanBegin(Element xmlTag) {
		StringBuilder spanStart = new StringBuilder();
		String name = xmlTag.getLocalName();
		String ahrefString = "";
		if (name.contains("Action")) {
			name = xmlTag.getAttributeValue("type");
			actionCheckSet.add(name);
		}
		else if (name.startsWith("LOCATION")|| name.startsWith("CAMPAIGN") || name.startsWith("LocationPhrase") )
			actionCheckSet.add(name);
		if (name.contains("OSCARONT")) {
			name = "Oscar";
			oscarOntCheckSet.add(name);
		}
		else if (name.contains("MOLECULE")) {
			name = "Other";
			if (StringUtils.isNotEmpty(xmlTag.getAttributeValue("role"))) name =  xmlTag.getAttributeValue("role");
			moleculeCheckSet.add(name);
			try {
				ahrefString = "<a href='http://opsin.ch.cam.ac.uk/opsin/"+getOscarCM(xmlTag)+".png'>";
			
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		else if (name.contains("AEROSOL")) {
			moleculeCheckSet.add(name);
		}
		if (name.contains("QUANTITY") || name.contains("UNIT") || name.contains("MATH")) {
			quantityCheckSet.add(name);
      }	
		if (name.startsWith("MODEL") || (name.startsWith("NNP-MODEL") ||  name.startsWith("APPARATUS") ||  name.startsWith("NNP-APPARATUS") || name.contains("PLATFORM") || name.contains("SATELLITE")) {
			toolsCheckSet.add(name);
      }	
		if (name.contains("CITATION")) {
         name = "Citation";
      }
		else if (name.startsWith("Temp") ||name.startsWith("Time") || name.startsWith("Atmosphere"))
			conditionCheckSet.add(name);
		else if (name.contains("Phrase") && !name.contains("Location")) phraseCheckSet.add(name);
		spanStart.append(SPAN_BEGIN + "'"+name + "'>"+ahrefString);

		return spanStart.toString();
	}



	private String getOscarCM(Element xmlTag) throws UnsupportedEncodingException {
		String oscarCM="";
		Nodes nodes = xmlTag.query(".//OSCAR-CM");
		for (int i = 0; i < nodes.size(); i++) {
			oscarCM += nodes.get(i).getValue()+" ";
		}
		
		return URLEncoder.encode(oscarCM.trim(),"UTF-8");
	}


	private HashMap<String, Set<String>> getCheckBoxContent() {

		HashMap<String,Set<String>> checkboxHashMap = new HashMap<String,Set<String>>();
		if (actionCheckSet.size()> 0) checkboxHashMap.put("Actions", actionCheckSet);
		if (conditionCheckSet.size()> 0) checkboxHashMap.put("Conditions", conditionCheckSet);
		if (moleculeCheckSet.size()> 0)checkboxHashMap.put("Molecules", moleculeCheckSet);
		if (oscarOntCheckSet.size()> 0) checkboxHashMap.put("Ontology_Terms", oscarOntCheckSet);
		if (quantityCheckSet.size()> 0) checkboxHashMap.put("Quantitative_Terms", quantityCheckSet);
		if (toolsCheckSet.size()> 0) checkboxHashMap.put("Apparatus_or_Tools", toolsCheckSet);
		if (phraseCheckSet.size()> 0) checkboxHashMap.put("Phrases", phraseCheckSet);
		if (citationCheckSet.size()> 0) checkboxHashMap.put("Citations", citationCheckSet);
		return checkboxHashMap;
	}


	private boolean hasMoreChildren(Element sub) {

		if (sub.getChildCount() == 0) {
			return false;
		}
		if (sub.getChild(0).getChildCount() > 0) {
			return true;
		}
		return false;
	}

	public String getTaggedText() {
		return taggedText;
	}

	public HashMap<String, Set<String>> getCheckBoxes() {
		return checkBoxes;
	}




	public HashMap<String, String> getGeoInfo() {
		return geoInfo;
	}
}
