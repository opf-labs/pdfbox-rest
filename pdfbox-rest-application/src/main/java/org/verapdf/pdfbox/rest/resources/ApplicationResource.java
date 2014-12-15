/**
 * 
 */
package org.verapdf.pdfbox.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.verapdf.pdfbox.rest.api.environment.Environment;
import org.verapdf.pdfbox.rest.api.environment.Environments;
import org.verapdf.pdfbox.rest.views.RestClientView;

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
    @GET
    @Path("/about")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Environment about() {
        return Environments.getEnvironment();
    }
}
