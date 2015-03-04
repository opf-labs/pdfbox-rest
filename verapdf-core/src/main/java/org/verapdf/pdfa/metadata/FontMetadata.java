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

    private FontMetadata() {
        this(DEFAULT_VALUE);
    }

    private FontMetadata(final String subtype) {
        this.subtype = subtype;
    }

    /**
     * @return the subtype
     */
    @JsonProperty
    public final String getSubtype() {
        return this.subtype;
    }

    /**
     * @author Timur Kamalov
     *
     */
    @SuppressWarnings({ "hiding", "synthetic-access" })
    public static final class Builder {

        private String subtype;

        /**
         *
         */
        public Builder() {
            this(FontMetadata.DEFAULT_INSTANCE);
        }

        /**
         * @param fontMd
         */
        public Builder(FontMetadata fontMd) {
            this.subtype = fontMd.subtype;
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
         * @return a FontMetadata instance built from the builder value
         */
        public FontMetadata build() {
            return new FontMetadata(this.subtype);
        }
    }


}
