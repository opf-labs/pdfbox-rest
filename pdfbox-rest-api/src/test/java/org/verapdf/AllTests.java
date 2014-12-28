package org.verapdf;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.verapdf.pdfa.metadata.bytestream.AllByteStreamTests;
import org.verapdf.pdfbox.rest.api.environment.TestEnvironments;

/**
 * Test suite for all tests.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 */
@RunWith(Suite.class)
@SuiteClasses({TestEnvironments.class, AllByteStreamTests.class })
public class AllTests {

}
