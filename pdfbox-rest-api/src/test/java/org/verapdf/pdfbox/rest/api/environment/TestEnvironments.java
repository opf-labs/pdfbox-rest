/**
 * 
 */
package org.verapdf.pdfbox.rest.api.environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;


/**
 * TODO JavaDoc for TestEnvironments
 * TODO Tests for TestEnvironments
 * TODO Implementation for TestEnvironments
 * 
 * @author  <a href="mailto:carl@openplanetsfoundation.org">Carl Wilson</a>
 *          <a href="https://github.com/carlwilson">carlwilson AT github</a>
 * @version 0.1
 * 
 * Created 27 Jul 2012:13:21:26
 */
@SuppressWarnings("static-method")
public class TestEnvironments {

	/**
	 * Just compare to the property for now
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getHostName()}.
	 */
	@Test
	public void testGetHostName() {
		try {
			assertEquals("Host name inconsistent.", Environments.getHostName(), InetAddress.getLocalHost().getHostName()); //$NON-NLS-1$
			assertFalse("Environments.getHostName().isEmpty() == true", Environments.getHostName().isEmpty()); //$NON-NLS-1$
		} catch (UnknownHostException excep) {
			excep.printStackTrace();
			fail("Illegal length IP address, check your setup: " + excep.getMessage()); //$NON-NLS-1$
			throw new IllegalStateException();
		}
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getCPUIsa()}.
	 */
	@Test
	public void testGetCPUIsa() {
		assertEquals("Inconsistent CPU Details", System.getProperty(Environments.JAVA_CPU_ISA_PROP), Environments.getCPUIsa()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getHostSummary()}.
	 */
	@Test
	public void testGetHostSummary() {
		assertFalse("HostSummary().isEmpty() == true", Environments.getHostSummary().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getJavaArch()}.
	 */
	@Test
	public void testGetJavaArch() {
		assertEquals("Inconsistent Java Arch", "x" + System.getProperty(Environments.JAVA_ARCH_PROP), Environments.getJavaArch()); //$NON-NLS-1$ //$NON-NLS-2$
		assertFalse("Environments.getJavaArch().isEmpty() == true", Environments.getJavaArch().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getJavaVendor()}.
	 */
	@Test
	public void testGetJavaVendor() {
		assertEquals("Inconsistent Java Vendor", System.getProperty(Environments.JAVA_VM_VENDOR_PROP), Environments.getJavaVendor()); //$NON-NLS-1$
		assertFalse("Environments.getJavaVendor().isEmpty() == true", Environments.getJavaVendor().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getJavaVersion()}.
	 */
	@Test
	public void testGetJavaVersion() {
		assertEquals("Inconsistent Java Version", System.getProperty(Environments.JAVA_VERSION_PROP), Environments.getJavaVersion()); //$NON-NLS-1$
		assertFalse("Environments.getJavaVersion().isEmpty() == true", Environments.getJavaVersion().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getJavaHome()}.
	 */
	@Test
	public void testGetJavaHome() {
		assertEquals("Inconsistent Java Home", System.getProperty(Environments.JAVA_HOME_PROP), Environments.getJavaHome()); //$NON-NLS-1$
		assertFalse("Environments.getJavaHome().isEmpty() == true", Environments.getJavaHome().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getJavaSummary()}.
	 */
	@Test
	public void testGetJavaSummary() {
		assertFalse("getJavaSummary().isEmpty() == true", Environments.getJavaSummary().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getUserName()}.
	 */
	@Test
	public void testGetUserName() {
		assertEquals("Inconsistent User Name", System.getProperty(Environments.USER_NAME_PROP), Environments.getUserName()); //$NON-NLS-1$
		assertFalse("Environments.getUserName().isEmpty() == true", Environments.getUserName().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getUserHome()}.
	 */
	@Test
	public void testGetUserHome() {
		assertEquals("Inconsistent User home", System.getProperty(Environments.USER_HOME_PROP), Environments.getUserHome()); //$NON-NLS-1$
		assertFalse("Environments.getUserHome().isEmpty() == true", Environments.getUserHome().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getUserCountry()}.
	 */
	@Test
	public void testGetUserCountry() {
		assertEquals("Inconsistent User country", System.getProperty(Environments.USER_COUNTRY_PROP), Environments.getUserCountry()); //$NON-NLS-1$
		assertFalse("Environments.getUserCountry().isEmpty() == true", Environments.getUserCountry().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getUserLanguage()}.
	 */
	@Test
	public void testGetUserLanguage() {
		assertEquals("Inconsistent User lang", System.getProperty(Environments.USER_LANGUAGE_PROP), Environments.getUserLanguage()); //$NON-NLS-1$
		assertFalse("Environments.getUserLanguage().isEmpty() == true", Environments.getUserLanguage().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getUserSummary()}.
	 */
	@Test
	public void testGetUserSummary() {
		assertFalse("getUserSummary().isEmpty() == true", Environments.getUserSummary().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getOSName()}.
	 */
	@Test
	public void testGetOSName() {
		assertEquals("Inconsistent OS Name", System.getProperty(Environments.OS_NAME_PROP), Environments.getOSName()); //$NON-NLS-1$
		assertFalse("Environments.getOSName().isEmpty() == true", Environments.getOSName().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getOSVersion()}.
	 */
	@Test
	public void testGetOSVersion() {
		assertEquals("Inconsistent OS Version", System.getProperty(Environments.OS_VERSION_PROP), Environments.getOSVersion()); //$NON-NLS-1$
		assertFalse("Environments.getOSVersion().isEmpty() == true", Environments.getOSVersion().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getOSArch()}.
	 */
	@Test
	public void testGetOSArch() {
		assertEquals("Inconsistent OS Arch", System.getProperty(Environments.OS_ARCH_PROP), Environments.getOSArch()); //$NON-NLS-1$
		assertFalse("Environments.getOSArch().isEmpty() == true", Environments.getOSArch().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link org.verapdf.pdfbox.rest.api.environment.Environments#getOSSummary()}.
	 */
	@Test
	public void testGetOSSummary() {
		assertFalse("getOSSummary().isEmpty() == true", Environments.getOSSummary().isEmpty()); //$NON-NLS-1$
	}
}
