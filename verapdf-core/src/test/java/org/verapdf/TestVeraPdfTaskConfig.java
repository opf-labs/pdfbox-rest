package org.verapdf;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("static-method")
public class TestVeraPdfTaskConfig {

	/**
	 * Test the hash and equals contract for the class using EqualsVerifier
	 */
	@Test
	public void testEqualsContract() {
		EqualsVerifier.forClass(VeraPdfTaskConfig.class).verify();
	}

	/**
	 * Test that default instance is always the same objec
	 */
	@Test
	public void testDefaultInstance() {
		VeraPdfTaskConfig defaultInstance = VeraPdfTaskConfig.defaultInstance();
		assertTrue("Default Instance should always be the same object", defaultInstance ==  VeraPdfTaskConfig.defaultInstance()); //$NON-NLS-1$
	}
    //TODO: fix tests
/*	*//**
	 * Test that null flavour raises the expected exception
	 *//*
	@Test(expected=NullPointerException.class)
	public void testFromValuesNullFlavour() {
		VeraPdfTaskConfig.fromValues(null, true, false, 0, 0);
	}

	*//**
	 * Test that legal flavours all work (including none)
	 *//*
	@Test
	public void testFromValuesAllFlavours() {
		for (PdfaFlavour flavour : PdfaFlavour.values()) {
			VeraPdfTaskConfig config = VeraPdfTaskConfig.fromValues(flavour, true, false, 0, 0);
			assertTrue(config.getFlavour() == flavour);
		}
	}

	*//**
	 * Test that legal flavours all work (including none)
	 *//*
	@Test(expected=IllegalArgumentException.class)
	public void testFromValuesIllegalStopErrors() {
		VeraPdfTaskConfig.fromValues(PdfaFlavour.PDFA_1_A, true, false, 0, -1);
	}

	*//**
	 * Test that legal flavours all work (including none)
	 *//*
	@Test
	public void testFromValuesLegalStopErrors() {
		for (int stopErrors = 0; stopErrors < 1000; stopErrors++) {
			VeraPdfTaskConfig config = VeraPdfTaskConfig.fromValues(PdfaFlavour.PDFA_1_A, true, false, 0, stopErrors);
			assertTrue(config.getStopErrors() == stopErrors);
		}
	}

	*//**
	 * Test that legal flavours all work (including none)
	 *//*
	@Test
	public void testFromValuesLegalVerbosity() {
		for (int verbosity = 0; verbosity < 10; verbosity++) {
			VeraPdfTaskConfig config = VeraPdfTaskConfig.fromValues(PdfaFlavour.PDFA_1_A, true, false, verbosity, 0);
			assertTrue(config.getVerbosity() == verbosity);
		}
	}

	*//**
	 * Test that legal flavours all work (including none)
	 *//*
	@Test(expected=IllegalArgumentException.class)
	public void testFromValuesVerbosityLessThanMin() {
		int verbosity = VeraPdfTaskConfig.VERBOSITY_MIN - 1;
		VeraPdfTaskConfig.fromValues(PdfaFlavour.PDFA_1_A, true, false, verbosity, 0);
	}

	*//**
	 * Test that legal flavours all work (including none)
	 *//*
	@Test(expected=IllegalArgumentException.class)
	public void testFromValuesVerbosityGreaterThanMax() {
		int verbosity = VeraPdfTaskConfig.VERBOSITY_MAX + 1;
		VeraPdfTaskConfig.fromValues(PdfaFlavour.PDFA_1_A, true, false, verbosity, 0);
	}*/
}
