package org.verapdf.pdfa.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;

import java.util.List;

/**
 * @author Timur Kamalov
 */
public class FontMetadata {

    final static FontMetadata DEFAULT_INSTANCE = new FontMetadata();
    private final static String DEFAULT_VALUE = "unknown"; //$NON-NLS-1$
    private final String subtype;
    private final String name;
    private final String baseName;
    private final int firstChar;
    private final int lastChar;
    private List<Integer> widths;
    private boolean embedded;

    private FontMetadata() {
        this(DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE, 0, 0, null, false);
    }

    private FontMetadata(final String subtype, final String name, final String baseName,
                         final int firstChar, final int lastChar, final List<Integer> widths,
                         final boolean embedded) {
        this.subtype = subtype;
        this.name = name;
        this.baseName = baseName;
        this.firstChar = firstChar;
        this.lastChar = lastChar;
        this.widths = widths;
        this.embedded = embedded;
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
        return this.name;
    }

    /**
     * @return the baseName
     */
    @JsonProperty
    public String getBaseName() {
        return baseName;
    }

    /**
     * @return the firstChar
     */
    @JsonProperty
    public int getFirstChar() {
        return firstChar;
    }

    /**
     * @return the lastChar
     */
    @JsonProperty
    public int getLastChar() {
        return lastChar;
    }

    /**
     * @return the widths
     */
    @JsonProperty
    public List<Integer> getWidths() {
        return widths;
    }

    /**
     * @return the embedded
     */
    @JsonProperty
    public boolean isEmbedded() {
        return embedded;
    }

    /**
     * @author Timur Kamalov
     *
     */
    @SuppressWarnings({ "hiding", "synthetic-access" })
    public static final class Builder {

        private String subtype;
        private String name;
        private String baseName;
        private int firstChar;
        private int lastChar;
        private List<Integer> widths;
        private boolean embedded;

        /**
         *
         */
        public Builder() {
            this(DEFAULT_INSTANCE);
        }

        /**
         * @param fontMd
         */
        public Builder(FontMetadata fontMd) {
            this.subtype = fontMd.subtype;
            this.name = fontMd.name;
            this.baseName = fontMd.baseName;
            this.firstChar = firstChar;
            this.lastChar = lastChar;
            this.widths = fontMd.widths;
            this.embedded = fontMd.embedded;
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
         * @param baseName
         * @return the builder instance for chaining
         */
        public Builder baseName(final String baseName) {
            this.baseName = Strings.nullToEmpty(baseName);
            return this;
        }

        /**
         * @param firstChar
         * @return the builder instance for chaining
         */
        public Builder firstChar(final int firstChar) {
            this.firstChar = firstChar;
            return this;
        }

        /**
         * @param lastChar
         * @return the builder instance for chaining
         */
        public Builder lastChar(final int lastChar) {
            this.lastChar = lastChar;
            return this;
        }

        /**
         * @param widths
         * @return the builder instance for chaining
         */
        public Builder widths(final List<Integer> widths) {
            this.widths = widths;
            return this;
        }

        /**
         * @param embedded
         * @return the builder instance for chaining
         */
        public Builder embedded(final boolean embedded) {
            this.embedded = embedded;
            return this;
        }

        /**
         * @return a FontMetadata instance built from the builder value
         */
        public FontMetadata build() {
            return new FontMetadata(this.subtype, this.name, this.baseName,
                    this.firstChar, this.lastChar, this.widths, this.embedded);
        }
    }
}
