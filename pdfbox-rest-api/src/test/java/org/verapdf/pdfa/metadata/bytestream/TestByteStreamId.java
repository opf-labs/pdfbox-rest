/**
 * 
 */
package org.verapdf.pdfa.metadata.bytestream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author  <a href="mailto:carl@openplanetsfoundation.org">Carl Wilson</a>
 *          <a href="https://github.com/carlwilson">carlwilson AT github</a>
 * @version 0.1
 * 
 * Created 20 Jul 2012:03:29:11
 */

@SuppressWarnings({ "nls", "static-method" })
public class TestByteStreamId {
	// Create a set of ByteStreams from the raw test data
	private static final Set<ByteStreamId> RAW_DETAILS;
	static {
		Set<ByteStreamId> details = new HashSet<>();
		try {
			for (File file : AllByteStreamTests.getRawFiles()) {
				ByteStreamId fromCC = TestByteStreamId.createByteStreamUsingCommonsCodec(file);
				details.add(fromCC);
			}
		} catch (FileNotFoundException excep) {
			excep.printStackTrace();
			fail(excep.getMessage());
		} catch (IOException excep) {
			excep.printStackTrace();
			fail(excep.getMessage());
		}
		RAW_DETAILS = Collections.unmodifiableSet(details);
	}

	/**
	 * Test method for {@link org.verapdf.pdfa.metadata.bytestream.ByteStreams#nullByteStreamId()}.
	 */
	@Test
	public void testNullStream() {
		// Get the null streamId
		ByteStreamId nullStreamId = ByteStreams.nullByteStreamId();
		// I static so should be same object the static instance
		assertTrue(nullStreamId == ByteStreamIdImpl.NULL_STREAM_ID);
	}
	
	/**
	 * Test method for {@link org.verapdf.pdfa.metadata.bytestream.ByteStreams#unidentifiedByteStreamId()}.
	 */
	@Test
	public void testUnidentifiedStream() {
		// Create our own version of the null stream details
		ByteStreamId unidentifiedStreamId = ByteStreams.unidentifiedByteStreamId();
		// I static so should be same object the static instance
		assertTrue(unidentifiedStreamId == ByteStreamIdImpl.UNIDENTIFIED_STREAM);
	}
	
	/**
	 * Test method for {@link org.verapdf.pdfa.metadata.bytestream.ByteStreams#idFromValues(long, String)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFromValuesBadLength() {
		// Try creating a byte stream with a length less than zero, use the valid empty hash values
		ByteStreams.idFromValues(-1L, ByteStreams.NULL_SHA1);
	}
	
	/**
	 * Test method for {@link org.verapdf.pdfa.metadata.bytestream.ByteStreams#idFromValues(long, String)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFromValuesEmptySHA1() {
		// Try creating a byte stream with an empty sha value
		ByteStreams.idFromValues(0L, "");
	}
	
	/**
	 * Test method for {@link org.verapdf.pdfa.metadata.bytestream.ByteStreams#idFromValues(long, String)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFromValuesNullSHA() {
		// Try creating a byte stream with a null sha value
		ByteStreams.idFromValues(0L, null);
	}
	
	/**
	 * Test method for {@link org.verapdf.pdfa.metadata.bytestream.ByteStreams#idFromValues(long, String)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFromValuesWrongLengthSHA1() {
		// Try creating a byte stream with an over long md5 value
		ByteStreams.idFromValues(0L, ByteStreams.NULL_SHA1);
	}
	
	/**
	 * Test method for {@link org.verapdf.pdfa.metadata.bytestream.ByteStreams#idFromValues(long, String)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFromValuesNonHexSHA1() {
		// Try creating a byte stream with a non hex sha value
		ByteStreams.idFromValues(0L, "1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdeX");
	}
	
	/**
	 * Test method for {@link org.verapdf.pdfa.metadata.bytestream.ByteStreams#idFromFile(File)}.
	 */
	@Test
	public void testFromFile() {
		// Create a null item from an empty file
		ByteStreamId nullStream = null;
		try {
			nullStream = ByteStreams.idFromFile(AllByteStreamTests.getEmptyFile());
		} catch (URISyntaxException excep) {
			fail(excep.getMessage());
		} catch (FileNotFoundException excep) {
			fail(excep.getMessage());
		} catch (IOException excep) {
			fail(excep.getMessage());
		}
		assertEquals("nullStream != NULL_STREAM_ID", nullStream, ByteStreams.nullByteStreamId());
		Set<ByteStreamId> details = new HashSet<>();
		try {
			for (File rawFile : AllByteStreamTests.getRawFiles()) {
				details.add(ByteStreams.idFromFile(rawFile));
			}
		} catch (FileNotFoundException excep) {
			fail(excep.getMessage());
		} catch (IOException excep) {
			fail(excep.getMessage());
		}
		// OK our set should be the same as the map values
		assertEquals("Sets should be the same size.", details.size(), RAW_DETAILS.size());
		
		// Now lets get the details and perform a few more tests
		Iterator<ByteStreamId> detailsIT = details.iterator();
		while (detailsIT.hasNext()) {
			ByteStreamId detail = detailsIT.next();
			// Our map should contain the SHA key
			assertTrue("SHA KEY not found in set.", RAW_DETAILS.contains(detail));
		}
	}
	
	/**
	 * Test method for {@link org.verapdf.pdfa.metadata.bytestream.ByteStreams#idFromFile(File)}.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFromFileNullFile() throws FileNotFoundException, IOException {
		// OK try a null file for the exception
		ByteStreams.idFromFile(null);
	}

	/**
	 * Test method for {@link org.verapdf.pdfa.metadata.bytestream.ByteStreams#idFromStream(java.io.InputStream)}.
	 */
	@Test
	public void testFromInputStream() {
		// Create a null item from an empty file
		try (FileInputStream fis = new FileInputStream(AllByteStreamTests.getEmptyFile())){
	        ByteStreamId nullStreamId = null;
            nullStreamId = ByteStreams.idFromStream(fis);
            assertEquals("nullStream != NULL_STREAM_ID", nullStreamId, ByteStreams.nullByteStreamId());
		} catch (FileNotFoundException excep) {
			fail("Can't find empty file.");
		} catch (URISyntaxException excep) {
			fail("URI Exception for empty file resource.");
		} catch (IOException e) {
            fail("IOException for empty file resource.");
        }
		Set<ByteStreamId> details = new HashSet<>();
		for (File rawFile : AllByteStreamTests.getRawFiles()) {
			try {
				details.add(ByteStreams.idFromStream(new FileInputStream(rawFile)));
			} catch (IOException excep) {
				fail("Can't  open raw file " + rawFile);
			}
		}
		// OK our set should be the same as the map values
		assertEquals("Sets should be the same size.", details.size(), RAW_DETAILS.size());
		
		// Now lets get the details and perform a few more tests
		Iterator<ByteStreamId> detailsIT = details.iterator();
		while (detailsIT.hasNext()) {
			ByteStreamId detail = detailsIT.next();
			// Our map should contain the SHA key
			assertTrue("SHA KEY not found in map.", RAW_DETAILS.contains(detail));
		}
	}

	/**
	 * Test method for {@link org.verapdf.pdfa.metadata.bytestream.ByteStreams#idFromStream(java.io.InputStream)}.
	 * @throws IOException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFromInputStreamNullStream() throws IOException {
		// OK try a null file for the exception
		ByteStreams.idFromStream(null);
	}

	/**
	 * Test the hash and equals contract for the class using EqualsVerifier
	 */
	@Test
	public void testEqualsContract() {
		EqualsVerifier.forClass(ByteStreamIdImpl.class).verify();
	}

	/**
	 * Test compareTo method
	 */
	@Test
	public void TestCompareTo() {
		// Create from value entries from the current map and fill a set
		Set<ByteStreamId> details = new HashSet<>();
		for (ByteStreamId detailId : RAW_DETAILS) {
			details.add(ByteStreams.idFromValues(detailId.getLength(), detailId.getHexSHA1()));
		}
		// OK our set should be the same as the map values
		assertEquals("Sets should be the same size.", details.size(), RAW_DETAILS.size());
		
		// Iterate through set and test
		for (ByteStreamId detailId : details) {
			// Our map should contain the SHA key
			assertTrue("SHA KEY not found in map.", RAW_DETAILS.contains(detailId));
			
			// Now test against the other ids
			for (ByteStreamId rawId : RAW_DETAILS) {
				// if they're equal test consistency with compareTo
				if (detailId.equals(rawId)) {
					assertTrue(rawId.equals(detailId));
					assertTrue(detailId.compareTo(rawId) == 0);
					assertTrue(rawId.compareTo(detailId) == 0);
				} else if ((detailId.compareTo(rawId) == 0) || (rawId.equals(detailId)) || (rawId.compareTo(detailId) == 0)) {
					// This shouldn't happen as should be caught by initial equals test
					fail(detailId + " and " + rawId + ", fail equals compareTo consistency.");
				} else if (detailId.compareTo(rawId) < 0) {
					// tests for less than
					assertTrue(rawId.compareTo(detailId) > 0);
				} else {
					assertTrue(detailId.compareTo(rawId) > 0);
				}
			}
		}
	}

	/**
	 * @param file
	 * @return a
	 *         {@link org.verapdf.pdfa.metadata.bytestream.ByteStreamId}
	 *         created using values as commons-codec for testing.
	 * @throws IOException
	 */
	public static final ByteStreamId createByteStreamUsingCommonsCodec(
			File file) throws IOException {
		long length = file.length();
		try (FileInputStream fis = new FileInputStream(file)) {
	        String hexSHA1 = null;
            hexSHA1 = DigestUtils.shaHex(fis);
	        return ByteStreams.idFromValues(length, hexSHA1);
		}
	}
}
