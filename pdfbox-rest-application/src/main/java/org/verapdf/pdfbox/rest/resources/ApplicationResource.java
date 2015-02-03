/**
 * 
 */
package org.verapdf.pdfbox.rest.resources;

import org.verapdf.pdfbox.rest.api.environment.Environments;
import org.verapdf.pdfbox.rest.views.AboutView;
import org.verapdf.pdfbox.rest.views.RestClientView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
@Path("/")
@SuppressWarnings("static-method")
public class ApplicationResource {
    /**
     * 
     */
    public ApplicationResource() {
    }

    /**
     * @return the java environment representation
     * 
     */
    @GET
    @Produces({ MediaType.TEXT_HTML })
    public RestClientView client() {
        return new RestClientView();
    }

    /**
     * @return the java environment representation
     * 
     */

    /*
    @GET
    @Path("/about")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Environment about() {
        return Environments.getEnvironment();
    }
    */

    @GET
    @Path("/about")
    @Produces({ MediaType.TEXT_HTML })
    public AboutView about() {
        return new AboutView(Environments.getEnvironment());
    }
}
