/**
 * 
 */
package org.verapdf.pdfa.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enumeration for PDF/A flavours, composed of version and level.
 *
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 */
public enum PdfaFlavour {
    /** PDF Version 1 Level A */
    PDFA_1_A(Version.VERSION_1, Level.LEVEL_A),
    /** PDF Version 1 Level B */
    PDFA_1_B(Version.VERSION_1, Level.LEVEL_B),
    /** PDF Version 2 Level A */
    PDFA_2_A(Version.VERSION_2, Level.LEVEL_A),
    /** PDF Version 2 Level B */
    PDFA_2_B(Version.VERSION_2, Level.LEVEL_B),
    /** PDF Version 3 Level A */
    PDFA_3_A(Version.VERSION_3, Level.LEVEL_A),
    /** PDF Version 3 Level B */
    PDFA_3_B(Version.VERSION_3, Level.LEVEL_B),
    /** PDF Version 3 Level U */
    PDFA_3_U(Version.VERSION_3, Level.LEVEL_U);
    final static String PDFA_STRING = "PDF/A-       "; //$NON-NLS-1$
    /** ISO Standard name for PDF/A 1 */
    public final static String ISO_19005_1 = "ISO 19005-1:2005"; //$NON-NLS-1$
    /** ISO Standard name for PDF/A 2 */
    public final static String ISO_19005_2 = "ISO 19005-2:2011"; //$NON-NLS-1$
    /** ISO Standard name for PDF/A 3 */
    public final static String ISO_19005_3 = "ISO 19005-3:2012"; //$NON-NLS-1$

    private final Version version;
    private final Level level;
    private final String code;
    private final String fullName;

    PdfaFlavour(final Version version, final Level level) {
        this.version = version;
        this.level = level;
        this.code = version.getCode() + level.getCode();
        this.fullName = PDFA_STRING + version.toString()
                + " " + level.toString(); //$NON-NLS-1$
    }

    /**
     * @return the version
     */
    @JsonProperty
    public final Version getVersion() {
        return this.version;
    }

    /**
     * @return the level
     */
    @JsonProperty
    public final Level getLevel() {
        return this.level;
    }

    /**
     * @return the code
     */
    @JsonProperty
    public final String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.fullName;
    }

    /**
     * Enumeration of PDF/A levels, 1-3
     * 
     * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
     */
    public static enum Version {
        /** PDF/A Version 1 */
        VERSION_1(1, ISO_19005_1),
        /** PDF/A Version 2 */
        VERSION_2(2, ISO_19005_2),
        /** PDF/A Version 3 */
        VERSION_3(3, ISO_19005_3);

        static final String VERSION_STRING = "Version "; //$NON-NLS-1$

        private final int id;
        private final String code;
        private final String specification;
        private final String fullName;

        Version(final int id, final String specification) {
            this.id = id;
            this.code = String.valueOf(id);
            this.specification = specification;
            this.fullName = VERSION_STRING + this.code;
        }

        /**
         * @return the version id as an integer
         */
        @JsonProperty
        public final int getId() {
            return this.id;
        }

        /**
         * @return the version code ("1", "2", or "3")
         */
        @JsonProperty
        public final String getCode() {
            return this.code;
        }

        /**
         * @return the ISO standard name for the PDF/A Version
         */
        @JsonProperty
        public final String getSpecification() {
            return this.specification;
        }

        @Override
        public String toString() {
            return this.fullName;
        }
    }

    /**
     * Enumeration for PDF/A Levels A, B & U
     *
     * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
     */
    public static enum Level {
        /** Level A */
        LEVEL_A("a"), //$NON-NLS-1$
        /** Level B */
        LEVEL_B("b"), //$NON-NLS-1$
        /** Level U */
        LEVEL_U("u"); //$NON-NLS-1$

        static final String LEVEL_STRING = "Level "; //$NON-NLS-1$

        private final String code;
        private final String fullName;

        Level(final String code) {
            this.code = code;
            this.fullName = LEVEL_STRING + code;
        }

        /**
         * @return the PDF/A Level code ("a", "b", or "u")
         */
        @JsonProperty
        public final String getCode() {
            return this.code;
        }

        @Override
        public String toString() {
            return this.fullName;
        }
    }
}
