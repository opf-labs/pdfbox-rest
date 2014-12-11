/**
 * 
 */
package org.verapdf.pdfbox.rest.app;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

/**
 * Configuration object for the Dropwizard app.
 * Reads defaults from configuration YAML file.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 */
public class PdfBoxRestConfiguration extends Configuration {
    @NotEmpty
    private int port;

    /**
     * @return the TCP/IP port used
     */
    @JsonProperty
    public int getPort() {
	return this.port;
    }
    
    /**
     * @param port numeric value of TCP/IP port to listen to
     */
    @JsonProperty
    public void setPort(int port) {
	this.port = port;
    }
}
