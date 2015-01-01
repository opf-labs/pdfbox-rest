/**
 * 
 */
package org.verapdf.pdfa.spec;


/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
public interface PdfaSpecification {
    /**
     * @return the PDF/A Flavour enum instance for this spec
     */
    public PdfaFlavour getFlavour();

    /**
     * @return the top level specification section
     */
    public Section getRootSection();
}
