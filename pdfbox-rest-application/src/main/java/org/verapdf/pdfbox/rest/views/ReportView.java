/**
 * 
 */
package org.verapdf.pdfbox.rest.views;

import io.dropwizard.views.View;

import org.verapdf.pdfa.reports.ValidationReport;

/**
 * Dropwizard {@link io.dropwizard.views.View View} to generate an HTML
 * representation of a {@link org.verapdf.pdfa.reports.ValidationReport PDF/A
 * validation report}. This uses the mustache template reportfragment.mustache
 * mapped from the main resources folder matching the View's package in this
 * case org.verapdf.pdfbox.rest.views.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.
 */
public class ReportView extends View {

    private ValidationReport report;

    /**
     * @param report
     *            the {@link org.verapdf.pdfa.reports.ValidationReport
     *            ValidationReport} to display as HTML
     */
    public ReportView(ValidationReport report) {
        super("reportfragment.mustache"); //$NON-NLS-1$
        this.report = report;
    }

    /**
     * @return the {@link org.verapdf.pdfa.reports.ValidationReport
     *         ValidationReport}, required by mustache to access report data in
     *         template
     */
    public ValidationReport getReport() {
        return this.report;
    }
}
