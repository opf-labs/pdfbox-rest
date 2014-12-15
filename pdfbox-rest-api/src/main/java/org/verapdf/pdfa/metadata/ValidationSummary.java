/**
 * 
 */
package org.verapdf.pdfa.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Place to hold the report summary fields, just about serialisation for now.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
public class ValidationSummary {
    /**
     * The default (impossible) values for the summary fields
     */
    public final static int DEFAULT_VALUE = -1;
    private final static ValidationSummary DEFAULT_INSTANCE = new ValidationSummary();
    private final int passedChecks;
    private final int failedChecks;
    private final int completedFixes;
    private final int failedFixes;
    private final int warnings;

    private ValidationSummary() {
        this(DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE,
                DEFAULT_VALUE);
    }

    private ValidationSummary(final int passedChecks, final int failedChecks,
            final int completedFixes, final int failedFixes, final int warnings) {
        this.passedChecks = passedChecks;
        this.failedChecks = failedChecks;
        this.completedFixes = completedFixes;
        this.failedFixes = failedFixes;
        this.warnings = warnings;
    }

    /**
     * @return the passedChecks
     */
    @JsonProperty
    @JacksonXmlProperty(isAttribute=true)
    public final int getPassedChecks() {
        return this.passedChecks;
    }

    /**
     * @return the failedChecks
     */
    @JsonProperty
    @JacksonXmlProperty(isAttribute=true)
    public final int getFailedChecks() {
        return this.failedChecks;
    }

    /**
     * @return the completedFixes
     */
    @JsonProperty
    @JacksonXmlProperty(isAttribute=true)
    public final int getCompletedFixes() {
        return this.completedFixes;
    }

    /**
     * @return the failedFixes
     */
    @JsonProperty
    @JacksonXmlProperty(isAttribute=true)
    public final int getFailedFixes() {
        return this.failedFixes;
    }

    /**
     * @return the warnings
     */
    @JsonProperty
    @JacksonXmlProperty(isAttribute=true)
    public final int getWarnings() {
        return this.warnings;
    }

    /**
     * @return the meaningless default instance, mostly for testing
     */
    public final static ValidationSummary defaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /**
     * @param passedChecks
     *            the total number of passed checks
     * @param failedChecks
     *            the total number of failed checks
     * @param completedFixes
     *            the total number of completed fixes
     * @param failedFixes
     *            the total number of failed fixes
     * @param warnings
     *            the total number of warnings
     * @return a new ValidationSummary instance created from the values
     */
    public final static ValidationSummary fromValues(final int passedChecks,
            final int failedChecks, final int completedFixes,
            final int failedFixes, final int warnings) {
        return new ValidationSummary(passedChecks, failedChecks,
                completedFixes, failedFixes, warnings);
    }

    /**
     * Convenience factory method that assumes no fixes.
     * 
     * @param passedChecks
     *            the total number of passed checks
     * @param failedChecks
     *            the total number of failed checks
     * @param warnings
     *            the total number of warnings
     * @return a new ValidationSummary instance created from the values
     */
    public final static ValidationSummary fromValuesNoFixes(
            final int passedChecks, final int failedChecks, final int warnings) {
        return new ValidationSummary(passedChecks, failedChecks, 0, 0, warnings);
    }

    /**
     * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
     */
    public final static class Counter {
        private int passedChecks = 0;
        private int failedChecks = 0;
        private int completedFixes = 0;
        private int failedFixes = 0;
        private int warnings = 0;

        /**
         * Increments the passedChecks counter and returns the current total
         * 
         * @return the new total number of passed checks
         */
        public int checkPassed() {
            return ++this.passedChecks;
        }

        /**
         * Increments the failedChecks counter and returns the current total
         * 
         * @return the new total number of failed checks
         */
        public int checkFailed() {
            return ++this.failedChecks;
        }

        /**
         * Increments the completedFixes counter and returns the current total
         * 
         * @return the new total number of completed fixes
         */
        public int fixCompleted() {
            return ++this.completedFixes;
        }

        /**
         * Increments the failedFixes counter and returns the current total
         * 
         * @return the new total number of failed fixes
         */
        public int fixFailed() {
            return ++this.failedFixes;
        }

        /**
         * Increments the warnings counter
         * 
         * @return the new total number of warnings
         */
        public int warning() {
            return ++this.warnings;
        }

        /**
         * @return a ValidationSummary from the current totals
         */
        @SuppressWarnings("synthetic-access")
        public final ValidationSummary createSummary() {
            return new ValidationSummary(this.passedChecks, this.failedChecks,
                    this.completedFixes, this.failedFixes, this.warnings);
        }
    }
}
