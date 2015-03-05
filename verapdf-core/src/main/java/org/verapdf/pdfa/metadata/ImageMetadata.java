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
    private final int bitsPerComponent;
    private final boolean imageMask;
    private final boolean maskedImage;
    private final String colorSpace;

    private ImageMetadata() {
        this(0, 0, 0, false, false, null);
    }

    private ImageMetadata(final int width, final int height, final int bitsPerComponent,
                          final boolean imageMask, final boolean maskedImage, final String colorSpace) {
        this.width = width;
        this.height = height;
        this.bitsPerComponent = bitsPerComponent;
        this.imageMask = imageMask;
        this.maskedImage = maskedImage;
        this.colorSpace = colorSpace;
    }

    /**
     * @return the width
     */
    @JsonProperty
    public int getWidth() {
        return this.width;
    }

    /**
     * @return the height
     */
    @JsonProperty
    public int getHeight() {
        return this.height;
    }

    /**
     * @return the bitsPerComponent
     */
    @JsonProperty
    public int getBitsPerComponent() {
        return this.bitsPerComponent;
    }

    /**
     * @return the imageMask
     */
    @JsonProperty
    public boolean isImageMask() {
        return this.imageMask;
    }

    /**
     * @return the maskedImage
     */
    @JsonProperty
    public boolean isMaskedImage() {
        return this.maskedImage;
    }

    /**
     * @return the colorSpace
     */
    @JsonProperty
    public String getColorSpace() {
        return this.colorSpace;
    }

    /**
     * @author Timur Kamalov
     *
     */
    @SuppressWarnings({ "hiding", "synthetic-access" })
    public static final class Builder {

        private int width;
        private int height;
        private int bitsPerComponent;
        private boolean imageMask;
        private boolean maskedImage;
        private String colorSpace;

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
            this.bitsPerComponent = imageMd.bitsPerComponent;
            this.imageMask = imageMd.imageMask;
            this.maskedImage = imageMd.maskedImage;
            this.colorSpace = imageMd.colorSpace;
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
         * @param bitsPerComponent
         * @return the builder instance for chaining
         */
        public Builder bitsPerComponent(final int bitsPerComponent) {
            this.bitsPerComponent = bitsPerComponent;
            return this;
        }

        /**
         * @param imageMask
         * @return the builder instance for chaining
         */
        public Builder imageMask(final boolean imageMask) {
            this.imageMask = imageMask;
            return this;
        }

        /**
         * @param maskedImage
         * @return the builder instance for chaining
         */
        public Builder maskedImage(final boolean maskedImage) {
            this.maskedImage = maskedImage;
            return this;
        }

        /**
         * @param colorSpace
         * @return the builder instance for chaining
         */
        public Builder colorSpace(final String colorSpace) {
            this.colorSpace = colorSpace;
            return this;
        }

        /**
         * @return a ImageMetadata instance built from the builder value
         */
        public ImageMetadata build() {
            return new ImageMetadata(this.width, this.height, this.bitsPerComponent,
                    this.imageMask, this.maskedImage, this.colorSpace);
        }
    }
}
