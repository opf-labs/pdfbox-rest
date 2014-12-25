/**
 * 
 */
package org.verapdf.pdfbox.rest.app;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.verapdf.pdfbox.rest.resources.ApplicationResource;
import org.verapdf.pdfbox.rest.resources.PdfBoxResource;
import org.verapdf.pdfbox.rest.resources.ByteStreamResource;

import com.yunspace.dropwizard.xml.XmlBundle;

/**
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
public class PdfBoxRestApplication extends Application<PdfBoxRestConfiguration> {
    private static final String NAME = "pdfbox-rest"; //$NON-NLS-1$

    /**
     * Main method for Jetty server application. Simply calls the run method
     * with command line args.
     * 
     * @param args
     *            command line arguments as string array.
     * @throws Exception
     *             passes any exception thrown by run
     */
    public static void main(String[] args) throws Exception {
        new PdfBoxRestApplication().run(args);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void initialize(Bootstrap<PdfBoxRestConfiguration> bootstrap) {
        // Dropwizard bundle to handle Jackson XML serialisation
        final XmlBundle xmlBundle = new XmlBundle();
        bootstrap.addBundle(xmlBundle);
        // Allow us to serve views
        bootstrap.addBundle(new ViewBundle());
        // Allow us to serve static assets for CSS and Javascript
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    @Override
    public void run(PdfBoxRestConfiguration configuration,
            Environment environment) throws Exception {
        // Create & register our REST resources
        final ApplicationResource about = new ApplicationResource();
        environment.jersey().register(about);
        final ByteStreamResource byteStream = new ByteStreamResource();
        environment.jersey().register(byteStream);
        final PdfBoxResource pdfbox = new PdfBoxResource();
        environment.jersey().register(pdfbox);
        // Set up cross domain REST
        setupCORS(environment);
    }

    private static void setupCORS(Environment environment) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors = environment.servlets()
                .addFilter("CORS", CrossOriginFilter.class); //$NON-NLS-1$

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*"); //$NON-NLS-1$ //$NON-NLS-2$
        cors.setInitParameter(
                "allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin"); //$NON-NLS-1$ //$NON-NLS-2$
        cors.setInitParameter(
                "allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD"); //$NON-NLS-1$ //$NON-NLS-2$

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),
                true, "/*"); //$NON-NLS-1$
    }
}
