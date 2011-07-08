package uk.ac.cam.ch.wwmm.chemicaltagger.webdemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import nu.xom.Document;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

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
		ChemistryPOSTagger chemPos;
		SentenceParser parser;
		Document doc;
		String body = form.getFirstValue("body");
		WebdemoApplication webDemo = (WebdemoApplication) getApplication();

		chemPos = ChemistryPOSTagger.getDefaultInstance();

		POSContainer container = chemPos.runTaggers(body);
		InputStream taggedStream = IOUtils.toInputStream(container
				.getTokenTagTupleAsString(), "UTF-8");
		parser = new ChemistrySentenceParser(taggedStream);

		parser.parseTags();

		doc = parser.makeXMLDocument();
		Map<String, Object> model = new HashMap<String, Object>();
		XMLtoHTML xmltoHTML = new XMLtoHTML();
		xmltoHTML.convert(doc);
		String xmlDocument = StringEscapeUtils.escapeHtml(doc.toXML());
		model.put("xmlContent", xmlDocument);
		model.put("taggedText", Jsoup.parseBodyFragment(
				xmltoHTML.getTaggedText()).toString());
		model.put("checkBoxes", xmltoHTML.getCheckBoxes());


		return webDemo.getTemplateRepresentation("tagged.ftl", model,
				MediaType.TEXT_HTML);

	}

}
