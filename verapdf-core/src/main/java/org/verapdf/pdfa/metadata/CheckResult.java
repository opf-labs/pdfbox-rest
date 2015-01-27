/**
 * 
 */
package org.verapdf.pdfa.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
public class CheckResult {
    /**
     * Default value of cause
     */
    public final static String DEFAULT_CAUSE = "unknown"; //$NON-NLS-1$
    private final static CheckResult DEFAULT_INSTANCE = new CheckResult();
    /**
     * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
     *
     */
    public static enum Status {
        /** Check passed */
        PASSED("passed"), //$NON-NLS-1$
        /** Check failed */
        FAILED("failed"), //$NON-NLS-1$
        /** Unknown default status */
        UNKNOWN("unknown"); //$NON-NLS-1$
        
        private final String name;
        
        Status(final String name) {
            this.name = name;
        }
        
        /**
         * @return the name of the status
         */
        @JsonProperty
        public String getName() {
            return this.name;
        }
    }

    private final Status status;
    private final Location location;
    private final String cause;

    private CheckResult() {
        this(Status.UNKNOWN, Location.defaultInstance(), DEFAULT_CAUSE);
    }
    
    private CheckResult(final Status status, final Location location, final String cause) {
        this.status = status;
        this.location = location;
        this.cause = cause;
    }

    /**
     * @return the status
     */
    @JsonProperty
    public final Status getStatus() {
        return this.status;
    }

    /**
     * @return the location
     */
    @JsonProperty
    public final Location getLocation() {
        return this.location;
    }

    /**
     * @return the cause
     */
    @JsonProperty
    public final String getCause() {
        return this.cause;
    }
    
    /**
     * @return the default instance, it's meaningless and really used for testing
     */
    public final static CheckResult defaultInstance() {
        return DEFAULT_INSTANCE;
    }
    
    /**
     * @param status the pass / fail status of the result
     * @param location the location of the result
     * @param cause the cause of any problem
     * @return a CheckResult instance populated from the passed values
     */
    public final static CheckResult fromValues(final Status status, final Location location, final String cause) {
        return new CheckResult(status, location, cause);
    }
}
