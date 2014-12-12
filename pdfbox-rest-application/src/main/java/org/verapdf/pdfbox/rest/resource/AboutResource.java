/**
 * 
 */
package org.verapdf.pdfbox.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.verapdf.pdfbox.rest.api.environment.Environment;
import org.verapdf.pdfbox.rest.api.environment.Environments;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
@Path("/about")
public class AboutResource {
    private final Environment environment;

    /**
     * 
     */
    public AboutResource() {
	this.environment = Environments.getEnvironment();
    }
  
    /**
     * @return the java environment representation
     * 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Environment about() {
	return this.environment;
    }
}
