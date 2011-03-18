package uk.ac.cam.ch.wwmm.chemicaltagger.webdemo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import uk.ac.cam.ch.wwmm.chemicaltagger.ChemistryPOSTagger;
import uk.ac.cam.ch.wwmm.chemicaltagger.ChemistrySentenceParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sea36
 * @author dmj30
 * @author lh359
 */
public class WebdemoApplication extends Application {

	private Configuration freemarker;

	public WebdemoApplication() throws IOException {
		freemarker = new Configuration();
		freemarker.setClassForTemplateLoading(getClass(), "/webdemo/templates");
	}

	@Override
	public Restlet createInboundRoot() {
        Directory dir = new Directory(getContext(), LocalReference.createClapReference("class/webdemo/resources"));
        dir.setNegotiatingContent(false);

		Router router = new Router(getContext());
        router.attach("/", DefaultResource.class);
        router.attach("/submit", SubmitResource.class);
        router.attach("/viewXML", ViewXML.class);
        router.attach("/res", dir);

		return router;
	}

    @Override
    public void start() throws Exception {
        super.start();
        // Do init
        ChemistryPOSTagger.getInstance().runTaggers("");
        ChemistrySentenceParser chemParser = new ChemistrySentenceParser(IOUtils.toInputStream(""));
        chemParser.parseTags();
        chemParser.makeXMLDocument();
    }

    public TemplateRepresentation getTemplateRepresentation(String name, MediaType mediaType) throws IOException {
        Map<String,Object> model = new HashMap<String,Object>();
        return getTemplateRepresentation(name, model, mediaType);
    }

    public TemplateRepresentation getTemplateRepresentation(String name, Map<String, ?> model, MediaType mediaType) throws IOException {
    	Template template = freemarker.getTemplate(name);
        return new TemplateRepresentation(template, model, mediaType);
    }

}
