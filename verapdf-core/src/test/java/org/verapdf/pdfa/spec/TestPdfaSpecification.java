/**
 * 
 */
package org.verapdf.pdfa.spec;

import static org.junit.Assert.assertNotNull;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.apache.pdfbox.preflight.PreflightConstants;
import org.junit.Test;
import org.verapdf.pdfa.metadata.PdboxErrorMapper;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
@SuppressWarnings({"static-method"})
public class TestPdfaSpecification {

    /**
     * Test the hash and equals contract for the class using EqualsVerifier
     */
    @Test
    public void testFlavourEqualsContract() {
        EqualsVerifier.forClass(PdfaFlavour.class).verify();
    }

    /**
     * Test the hash and equals contract for the class using EqualsVerifier
     */
    @Test
    public void testLevelEqualsContract() {
        EqualsVerifier.forClass(PdfaFlavour.Level.class).verify();
    }

    /**
     * Test the hash and equals contract for the class using EqualsVerifier
     */
    @Test
    public void testStandardEqualsContract() {
        EqualsVerifier.forClass(PdfaFlavour.IsoStandard.class).verify();
    }

    /**
     * Test the hash and equals contract for the class using EqualsVerifier
     */
    @Test
    public void testStandardSeriesEqualsContract() {
        EqualsVerifier.forClass(PdfaFlavour.IsoStandardSeries.class).verify();
    }

    /**
     * Test the hash and equals contract for the class using EqualsVerifier
     */
    @Test
    public void testSpecificationEqualsContract() {
        EqualsVerifier.forClass(PdfaSpecificationImpl.class).verify();
    }


    /**
     * Test the hash and equals contract for the class using EqualsVerifier
     */
    @Test
    public void testFlavourToString() {
        for (PdfaFlavour flavour : PdfaFlavour.values()) {
            System.out.println(flavour.toString());
        }
    }


    /**
     * Test the hash and equals contract for the class using EqualsVerifier
     */
    @Test
    public void testLevelToString() {
        for (PdfaFlavour.Level level : PdfaFlavour.Level.values()) {
            System.out.println(level.toString());
        }
    }

    /**
     * Test the hash and equals contract for the class using EqualsVerifier
     */
    @Test
    public void testStandardToString() {
        for (PdfaFlavour.IsoStandard standard : PdfaFlavour.IsoStandard.values()) {
            System.out.println(standard.toString());
        }
    }

    /**
     * Test the hash and equals contract for the class using EqualsVerifier
     */
    @Test
    public void testStandardSeriesToString() {
        for (PdfaFlavour.IsoStandardSeries series : PdfaFlavour.IsoStandardSeries.values()) {
            System.out.println(series.toString());
        }
    }

    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.PdfaSpecifications#byFlavour(PdfaFlavour)}.
     */
    @Test
    public final void testPdfaSpecification() {
        PdfaSpecification spec = PdfaSpecifications.byFlavour(PdfaFlavour.PDFA_1_B);
        assertNotNull(spec);
        System.out.println(spec.toString());
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.PdfaFlavour#toString()}.
     */
    @Test
    public final void testToString() {
        PdfaSpecificationImpl.DEFAULT_INSTANCE.toString();
    }

    /**
     * 
     */
    @Test
    public final void testMapper() {
        assertNotNull(PdboxErrorMapper.getMappedSectionId(PreflightConstants.ERROR_SYNTAX_OBJ_DELIMITER));
    }
}
