/**
 * 
 */
package org.verapdf.pdfbox.rest.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.verapdf.pdfbox.rest.resource.AboutResource;

import com.yunspace.dropwizard.xml.XmlBundle;

/**
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
public class PdfBoxRestApplication extends Application<PdfBoxRestConfiguration> {
    private static final String NAME = "pdfbox-rest";  //$NON-NLS-1$
    
    /**
     * Main method for Jetty server application. Simply calls the
     * run method with command line args.
     * 
     * @param args command line arguments as string array.
     * @throws Exception passes any exception thrown by run
     */
    public static void main(String[] args) throws Exception{
	new PdfBoxRestApplication().run(args);
    }

    @Override
    public String getName() {
	return NAME;
    }

    @Override
    public void initialize(Bootstrap<PdfBoxRestConfiguration> bootstrap) {
        final XmlBundle xmlBundle = new XmlBundle();
        bootstrap.addBundle(xmlBundle);
    }

    @Override
    public void run(PdfBoxRestConfiguration configuration,
	    Environment environment) throws Exception {
	final AboutResource resource = new AboutResource();
	environment.jersey().register(resource);
    }

    
}
