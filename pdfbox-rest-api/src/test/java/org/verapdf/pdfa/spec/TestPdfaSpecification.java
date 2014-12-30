/**
 * 
 */
package org.verapdf.pdfa.spec;

import static org.junit.Assert.fail;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
@SuppressWarnings({"nls", "static-method"})
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
     * Test method for {@link org.verapdf.pdfa.spec.PdfaFlavour#PdfaSpecification(org.verapdf.pdfa.spec.PdfaFlavour., org.verapdf.pdfa.spec.PdfaFlavour.Level)}.
     */
    @Test
    public final void testPdfaSpecification() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.PdfaFlavour#getVersion()}.
     */
    @Test
    public final void testGetVersion() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.PdfaFlavour#getLevel()}.
     */
    @Test
    public final void testGetLevel() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.PdfaFlavour#getCode()}.
     */
    @Test
    public final void testGetCode() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.PdfaFlavour#getName()}.
     */
    @Test
    public final void testGetName() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.PdfaFlavour#toString()}.
     */
    @Test
    public final void testToString() {
        fail("Not yet implemented"); // TODO
    }

}
