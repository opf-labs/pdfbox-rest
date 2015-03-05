package org.verapdf.pdfa.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Timur Kamalov
 */
public class ImageMetadata {

    final static ImageMetadata DEFAULT_INSTANCE = new ImageMetadata();
    private final static String DEFAULT_VALUE = "unknown"; //$NON-NLS-1$
    private final int width;
    private final int height;

    private ImageMetadata() {
        this(0, 0);
    }

    private ImageMetadata(final Integer width, final Integer height) {
        this.width = width;
        this.height = height;
    }

    /**
     * @return the width
     */
    @JsonProperty
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    @JsonProperty
    public int getHeight() {
        return height;
    }

    /**
     * @author Timur Kamalov
     *
     */
    @SuppressWarnings({ "hiding", "synthetic-access" })
    public static final class Builder {

        private int width;
        private int height;

        /**
         *
         */
        public Builder() {
            this(DEFAULT_INSTANCE);
        }

        /**
         * @param imageMd
         */
        public Builder(ImageMetadata imageMd) {
            this.width = imageMd.width;
            this.height = imageMd.height;
        }

        /**
         * @param width
         * @return the builder instance for chaining
         */
        public Builder width(final int width) {
            this.width = width;
            return this;
        }

        /**
         * @param height
         * @return the builder instance for chaining
         */
        public Builder height(final int height) {
            this.height = height;
            return this;
        }

        /**
         * @return a ImageMetadata instance built from the builder value
         */
        public ImageMetadata build() {
            return new ImageMetadata(this.width, this.height);
        }
    }
}