package org.verapdf;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.verapdf.pdfa.metadata.bytestream.AllByteStreamTests;
import org.verapdf.pdfa.spec.AllSpecificationTests;
import org.verapdf.pdfbox.rest.api.environment.TestEnvironments;

/**
 * Test suite for all tests.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 */
@RunWith(Suite.class)
@SuiteClasses({ TestEnvironments.class, AllByteStreamTests.class, AllSpecificationTests.class })
public class AllTests {
	/**
	 * @param resName
	 *            the name of the resource to retrieve a file for
	 * @return the java.io.File for the named resource
	 * @throws URISyntaxException
	 *             if the named resource can't be converted to a URI
	 */
	public final static File getResourceAsFile(String resName)
			throws URISyntaxException {
		return new File(ClassLoader.getSystemResource(resName).toURI());
	}

	/**
	 * Helper method to get the file children of a directory with the option to
	 * recurse sub-directories.
	 * 
	 * @param root
	 *            Root file to get children of, should be a directory.
	 * @param recurse
	 *            if true recurse sub-directories, if false just direct children
	 *            returned
	 * @return a List of File objects for each non hidden, file found as child
	 *         of root.
	 */
	public final static List<File> getFileChildren(File root, boolean recurse) {
		List<File> children = new ArrayList<>();
		for (File file : root.listFiles()) {
			if (file.isDirectory() && !file.isHidden() && recurse) {
				children.addAll(getFileChildren(file, recurse));
			} else if (file.isFile() && !file.isHidden()) {
				children.add(file);
			}
		}
		return children;
	}
}
