package uk.ac.cam.ch.wwmm.chemicaltagger.webdemo;

import org.restlet.Component;
import org.restlet.Context;
import org.restlet.data.Protocol;

import java.io.IOException;

/**
 * @author Sam Adams
 */
public class WebdemoComponent extends Component {

    public WebdemoComponent() throws IOException {
        getClients().add(Protocol.CLAP);
        WebdemoApplication app = new WebdemoApplication();
        getDefaultHost().attachDefault(app);
    }

}
