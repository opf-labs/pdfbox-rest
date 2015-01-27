/**
 * 
 */
package org.verapdf.pdfa.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Class that gathers an individual check and the list results
 * of the check on a particular PDF/A document.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 */
@JacksonXmlRootElement
public class CheckAudit {
    private final static CheckAudit DEFAULT_INSTANCE = new CheckAudit(); 
    private final Check check;
    private final List<CheckResult> results;
    
    private CheckAudit() {
        this(Check.defaultInstance(), new ArrayList<CheckResult>());
    }
    
    private CheckAudit(final Check check, final List<CheckResult> results) {
        this.check = check;
        this.results = Collections.unmodifiableList(results);
    }

    /**
     * @return the check
     */
    @JsonProperty
    public final Check getCheck() {
        return this.check;
    }

    /**
     * @return the results
     */
    @JsonProperty
    @JacksonXmlElementWrapper(useWrapping=false)
    public final List<CheckResult> getResults() {
        return this.results;
    }
    
    /**
     * @return the meaningless default instance, for checking 
     */
    public final static CheckAudit defaultInstance() {
        return DEFAULT_INSTANCE;
    }
    
    /**
     * @param check the check that the results list pertains to
     * @param results the list of results of applications of the check
     * @return a new CheckAudit instance created from the values
     */
    public final static CheckAudit fromValues(final Check check, final List<CheckResult> results) {
        return new CheckAudit(check, results);
    }
}
