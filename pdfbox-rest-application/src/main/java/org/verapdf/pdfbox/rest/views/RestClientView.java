/**
 * 
 */
package org.verapdf.pdfbox.rest.views;

import io.dropwizard.views.View;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
public class RestClientView extends View {
    
    /**
     * 
     */
    public RestClientView() {
        super("restclient.mustache"); //$NON-NLS-1$
    }

}
