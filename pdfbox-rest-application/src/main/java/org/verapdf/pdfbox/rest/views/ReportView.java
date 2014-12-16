/**
 * 
 */
package org.verapdf.pdfbox.rest.views;

import io.dropwizard.views.View;

import org.verapdf.pdfa.metadata.ValidationReport;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
public class ReportView extends View {

    private ValidationReport report;
    /**
     * @param report 
     * 
     */
    public ReportView(ValidationReport report) {
        super("reportfragment.mustache"); //$NON-NLS-1$
        this.report = report;
    }

    /**
     * @return
     */
    public ValidationReport getReport() {
        return this.report;
    }
}
