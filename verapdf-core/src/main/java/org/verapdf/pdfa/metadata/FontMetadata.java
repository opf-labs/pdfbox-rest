package org.verapdf.pdfa.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;

/**
 * @author Timur Kamalov
 */
public class FontMetadata {

    final static FontMetadata DEFAULT_INSTANCE = new FontMetadata();
    private final static String DEFAULT_VALUE = "unknown"; //$NON-NLS-1$
    private final String subtype;
    private final String name;

    private FontMetadata() {
        this(DEFAULT_VALUE, DEFAULT_VALUE);
    }

    private FontMetadata(final String subtype, final String name) {
        this.subtype = subtype;
        this.name = name;
    }

    /**
     * @return the subtype
     */
    @JsonProperty
    public final String getSubtype() {
        return this.subtype;
    }

    /**
     * @return the name
     */
    @JsonProperty
    public String getName() {
        return name;
    }

    /**
     * @author Timur Kamalov
     *
     */
    @SuppressWarnings({ "hiding", "synthetic-access" })
    public static final class Builder {

        private String subtype;
        private String name;

        /**
         *
         */
        public Builder() {
            this(DEFAULT_INSTANCE);
        }

        /**
         * @param fontMd
         * @return the builder instance for chaining
         */
        public Builder(FontMetadata fontMd) {
            this.subtype = fontMd.subtype;
            this.name = fontMd.name;
        }

        /**
         * @param subtype
         * @return the builder instance for chaining
         */
        public Builder subtype(final String subtype) {
            this.subtype = Strings.nullToEmpty(subtype);
            return this;
        }

        /**
         * @param name
         * @return the builder instance for chaining
         */
        public Builder name(final String name) {
            this.name = Strings.nullToEmpty(name);
            return this;
        }

        /**
         * @return a FontMetadata instance built from the builder value
         */
        public FontMetadata build() {
            return new FontMetadata(this.subtype, this.name);
        }
    }
}
