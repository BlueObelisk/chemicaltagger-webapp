package uk.ac.cam.ch.wwmm.chemicaltagger.webdemo;

import java.io.IOException;

import org.restlet.Component;
import org.restlet.data.Protocol;

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
