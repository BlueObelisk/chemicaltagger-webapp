package uk.ac.cam.ch.wwmm.chemicaltagger.webdemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import nu.xom.Document;
import nu.xom.Serializer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import uk.ac.cam.ch.wwmm.chemicaltagger.ChemistryPOSTagger;
import uk.ac.cam.ch.wwmm.chemicaltagger.ChemistrySentenceParser;
import uk.ac.cam.ch.wwmm.chemicaltagger.POSContainer;

/**
 * @author sea36
 * @author dmj30
 * @author lh359
 */
public class SubmitResource extends ServerResource {

    @Post("form:txt")
    public Representation doForm(Form form) throws IOException {
    	
        String body = form.getFirstValue("body");
        WebdemoApplication webDemo = (WebdemoApplication) getApplication();

        POSContainer container = ChemistryPOSTagger.getInstance().runTaggers(body);
        InputStream taggedStream = IOUtils.toInputStream(container.getTokenTagTupleAsString(), "UTF-8");
        ChemistrySentenceParser parser = new ChemistrySentenceParser(taggedStream);
        parser.parseTags();
        Document doc = parser.makeXMLDocument();
        Map<String,Object> model = new HashMap<String, Object>();
        XMLtoHTML xmltoHTML = new XMLtoHTML();
        xmltoHTML.convert(doc);
       
//        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
//    	Serializer serializer = new Serializer(bytestream, "UTF-8");
//		serializer.write(doc);
		
        String xmlDocument = StringEscapeUtils.escapeHtml(doc.toXML());
        model.put("xmlContent",xmlDocument);
        model.put("taggedText",xmltoHTML.getTaggedText());
        model.put("checkBoxes", xmltoHTML.getCheckBoxes());
        
        return webDemo.getTemplateRepresentation("tagged.ftl", model, MediaType.TEXT_HTML);
        
    }


}
