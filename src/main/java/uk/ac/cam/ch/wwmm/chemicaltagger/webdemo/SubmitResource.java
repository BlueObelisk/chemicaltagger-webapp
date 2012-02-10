package uk.ac.cam.ch.wwmm.chemicaltagger.webdemo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import nu.xom.Document;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import uk.ac.cam.ch.wwmm.acpgeo.ACPSentenceParser;
import uk.ac.cam.ch.wwmm.acpgeo.ACPTagger;
import uk.ac.cam.ch.wwmm.chemicaltagger.ChemistryPOSTagger;
import uk.ac.cam.ch.wwmm.chemicaltagger.ChemistrySentenceParser;
import uk.ac.cam.ch.wwmm.chemicaltagger.POSContainer;
import uk.ac.cam.ch.wwmm.chemicaltagger.SentenceParser;
import uk.ac.cam.ch.wwmm.oscar.Oscar;

/**
 * @author sea36
 * @author dmj30
 * @author lh359
 */
public class SubmitResource extends ServerResource {

	private static class INSTANCE_HOLDER {
		private static Oscar oscarInstance = new Oscar();
	}
	public static Oscar getOscarInstance() {
		return INSTANCE_HOLDER.oscarInstance;
	}

    @Post("form:txt")
    public Representation doForm(Form form) throws IOException {
        String body = form.getFirstValue("body");
        String chemistryType = form.getFirstValue("ChemistryType");
        WebdemoApplication webDemo = (WebdemoApplication) getApplication();

        SentenceParser parser;
        if (chemistryType.equalsIgnoreCase("Atmospheric")){
        	POSContainer container = ACPTagger.getInstance().runTaggers(body);
        	parser = new ACPSentenceParser(container.getTokenTagTupleAsString());
        }
        else {
        	POSContainer container = ChemistryPOSTagger.getDefaultInstance().runTaggers(body);
        	parser = new ChemistrySentenceParser(container.getTokenTagTupleAsString());
        }
        parser.parseTags();
        
        Document doc = parser.makeXMLDocument();
        Map<String,Object> model = new HashMap<String, Object>();
        XMLtoHTML xmltoHTML = new XMLtoHTML();
        xmltoHTML.convert(doc);		
        String xmlDocument = StringEscapeUtils.escapeHtml(doc.toXML());
        model.put("xmlContent",xmlDocument);
        model.put("taggedText",Jsoup.parseBodyFragment(xmltoHTML.getTaggedText()).toString());
        model.put("checkBoxes", xmltoHTML.getCheckBoxes());
        model.putAll(xmltoHTML.getGeoInfo());
        return webDemo.getTemplateRepresentation("tagged.ftl", model, MediaType.TEXT_HTML);
    }
}
