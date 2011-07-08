package uk.ac.cam.ch.wwmm.chemicaltagger.webdemo;

import java.io.IOException;

import junit.framework.Assert;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Test;

import uk.ac.cam.ch.wwmm.chemicaltagger.Utils;


public class XMLtoHTMLTest {

	@Test
	public void testGeoMap() throws ValidityException, ParsingException, IOException {

		Document doc = new Builder().build(Utils.getInputStream(
				this.getClass(), "/xml/atmosph.xml"));
		Assert.assertTrue(doc!=null);
	}
}
