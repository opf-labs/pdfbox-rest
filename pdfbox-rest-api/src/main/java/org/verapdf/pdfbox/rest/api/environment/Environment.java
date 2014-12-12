/**
 * 
 */
package org.verapdf.pdfbox.rest.api.environment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;



/**
 * TODO JavaDoc for Environment.</p>
 * TODO Tests for Environment.</p>
 * TODO Implementation for Environment.</p>
 * 
 * @author  <a href="mailto:carl@openplanetsfoundation.org">Carl Wilson</a>.</p>
 *          <a href="https://github.com/carlwilson">carlwilson AT github</a>.</p>
 * @version 0.1
 * 
 * Created 14 Sep 2012:10:42:22
 */
@JacksonXmlRootElement
public class Environment {
    	final static Environment INSTANCE = new Environment();
	private final ServerDetails hardware = ServerDetails.getInstance();
	private final OsDetails os = OsDetails.getInstance();
	private final JvmDetails java = JvmDetails.getInstance();

	private Environment() {
		// Do nothing
	}
	
	/**
	 * @return the Server details
	 */
	@JsonProperty
	public ServerDetails getServer() {
		return this.hardware;
	}
	
	/**
	 * @return the os details
	 */
	@JsonProperty
	public OsDetails getOS() {
		return this.os;
	}
	/**
	 * @return the Java details
	 */
	@JsonProperty
	public JvmDetails getJava() {
		return this.java;
	}
}
