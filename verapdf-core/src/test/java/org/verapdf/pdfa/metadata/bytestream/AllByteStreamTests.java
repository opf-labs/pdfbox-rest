package org.verapdf.pdfa.metadata.bytestream;

import static org.junit.Assert.fail;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.verapdf.AllTests;

/**
 * @author  <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 */
@RunWith(Suite.class)
@SuiteClasses({ TestByteStreamId.class })
@SuppressWarnings("nls")
public class AllByteStreamTests {
	/** Root test */
	private final static String TEST_ROOT = "org/verapdf";
	/** Root data */
	public final static String DATA_ROOT = TEST_ROOT + "/data";
	/** Root javaio fs */
	public final static String JAVAIO_ROOT = DATA_ROOT + "/javaiofs";
	/** Root javaio_dupes */
	/** Empty directory */
	public final static String EMPTY_DIR = DATA_ROOT + "/empty.dir";
	/** Empty file */
	public final static String EMPTY_FILE = DATA_ROOT + "/empty.file";
	/** Java IOFS Empty file */
	public final static String JAVAIO_EMPTY_FILE = JAVAIO_ROOT + "/empty.file";

	/**
	 * @return the list of files in the RAW ROOT dir
	 */
	public final static List<File> getRawFiles() {
		try {
			return Collections.unmodifiableList(AllByteStreamTests.getResourceFiles(JAVAIO_ROOT, true));
		} catch (URISyntaxException excep) {
			// TODO Auto-generated catch block
			excep.printStackTrace();
			fail("URI Exception getting test data: " + excep);
		}
		// this will never happen as fail condition thrown first
		return null;
	}

	private final static List<File> getResourceFiles(String resName,
			boolean recurse) throws URISyntaxException {
		File root = AllTests.getResourceAsFile(resName);
		return AllTests.getFileChildren(root, recurse);
	}

	/**
	 * @return the empty directory
	 * @throws URISyntaxException
	 */
	public final static File getEmptyDir() throws URISyntaxException {
		return new File(AllTests.getResourceAsFile(EMPTY_FILE).getParentFile()
				.getAbsolutePath() + EMPTY_DIR);
	}

	/**
	 * @return the empty file
	 * @throws URISyntaxException
	 */
	public final static File getEmptyFile() throws URISyntaxException {
		return AllTests.getResourceAsFile(EMPTY_FILE);
	}

	/**
	 * @return the java.io.File for the javaio File System root
	 * @throws URISyntaxException
	 */
	public final static File getJavaIORootFile() throws URISyntaxException {
		return AllTests.getResourceAsFile(JAVAIO_ROOT);
	}
}
