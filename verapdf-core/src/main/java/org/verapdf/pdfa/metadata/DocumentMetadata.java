/**
 * 
 */
package org.verapdf.pdfa.metadata;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.google.common.base.Strings;

/**
 * Class that encapsualtes the PDF Document metadata. TODO: Terminology lesson
 * for terms for this metadata.
 * 
 * Instances are immutable
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 */
public class DocumentMetadata {
    /**
     * 
     */
    final static DocumentMetadata DEFAULT_INSTANCE = new DocumentMetadata();
    private final static String DEFAULT_VALUE = "unknown"; //$NON-NLS-1$
    private final String title;
    private final String author;
    private final String subject;
    private final String keywords;
    private final Date creationDate;
    private final Date modificationDate;
    private final String creator;
    private final String producer;
    private final float version;
    private final int pageCount;
    private final String trapped;
    private final List<FontMetadata> fonts;
    private final List<ImageMetadata> images;

    private DocumentMetadata() {
        this(DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE, 0L,
                0L, DEFAULT_VALUE, DEFAULT_VALUE, 0.0f, 0, DEFAULT_VALUE, null, null);
    }

    private DocumentMetadata(final String title, final String author,
            final String subject, final String keywords,
            final Date creationDate, final Date modificationDate,
            final String creator, final String producer, final float version,
            final int pageCount, final String trapped, final List<FontMetadata> fonts,
            final List<ImageMetadata> images) {
        this(title, author, subject, keywords, creationDate.getTime(),
                modificationDate.getTime(), creator, producer, version,
                pageCount, trapped, fonts, images);
    }

    private DocumentMetadata(final String title, final String author,
            final String subject, final String keywords,
            final long creationDate, final long modificationDate,
            final String creator, final String producer, final float version,
            final int pageCount, final String trapped, final List<FontMetadata> fonts,
            final List<ImageMetadata> images) {
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.keywords = keywords;
        this.creationDate = new Date(creationDate);
        this.modificationDate = new Date(modificationDate);
        this.creator = creator;
        this.producer = producer;
        this.version = version;
        this.pageCount = pageCount;
        this.trapped = trapped;
        this.fonts = fonts;
        this.images = images;
    }

    /**
     * @return the title
     */
    @JsonProperty
    public final String getTitle() {
        return this.title;
    }

    /**
     * @return the author
     */
    @JsonProperty
    public final String getAuthor() {
        return this.author;
    }

    /**
     * @return the subject
     */
    @JsonProperty
    public final String getSubject() {
        return this.subject;
    }

    /**
     * @return the keywords
     */
    @JsonProperty
    public final String getKeywords() {
        return this.keywords;
    }

    /**
     * @return the creationDate
     */
    @JsonProperty
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="", timezone="UTC")
    public final Date getCreationDate() {
        return new Date(this.creationDate.getTime());
    }

    /**
     * @return the modificationDate
     */
    @JsonProperty
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="", timezone="UTC")
    public final Date getModificationDate() {
        return new Date(this.modificationDate.getTime());
    }

    /**
     * @return the creator
     */
    @JsonProperty
    public final String getCreator() {
        return this.creator;
    }

    /**
     * @return the producer
     */
    @JsonProperty
    public final String getProducer() {
        return this.producer;
    }

    /**
     * @return the version
     */
    @JsonProperty
    public final float getVersion() {
        return this.version;
    }

    /**
     * @return the pageCount
     */
    @JsonProperty
    public final int getPageCount() {
        return this.pageCount;
    }

    /**
     * @return the trapped
     */
    @JsonProperty
    public String getTrapped() {
        return this.trapped;
    }

    /**
     * @return the fonts
     */
    @JsonProperty
    @JacksonXmlElementWrapper(localName = "fonts")
    @JacksonXmlProperty(localName = "font")
    public List<FontMetadata> getFonts() {
        return this.fonts;
    }

    /**
     * @return the images
     */
    @JsonProperty
    @JacksonXmlElementWrapper(localName = "images")
    @JacksonXmlProperty(localName = "image")
    public List<ImageMetadata> getImages() {
        return this.images;
    }

    /**
     * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
     *
     */
    @SuppressWarnings({ "hiding", "synthetic-access" })
    public static final class Builder {
        private String title;
        private String author;
        private String subject;
        private String keywords;
        private Date creationDate;
        private Date modificationDate;
        private String creator;
        private String producer;
        private float version;
        private int pageCount;
        private String trapped;
        private List<FontMetadata> fonts;
        private List<ImageMetadata> images;

        /**
	    *
	    */
        public Builder() {
            this(DocumentMetadata.DEFAULT_INSTANCE);
        }

        /**
         * @param docMd
         */
        public Builder(DocumentMetadata docMd) {
            this.title = docMd.title;
            this.author = docMd.author;
            this.subject = docMd.subject;
            this.keywords = docMd.keywords;
            this.creationDate = new Date(docMd.creationDate.getTime());
            this.modificationDate = new Date(docMd.modificationDate.getTime());
            this.creator = docMd.creator;
            this.producer = docMd.producer;
            this.version = docMd.version;
            this.pageCount = docMd.pageCount;
            this.trapped = docMd.trapped;
            this.fonts = docMd.fonts;
            this.images = docMd.images;
        }

        /**
         * @param title
         * @return the builder instance for chaining
         */
        public Builder title(final String title) {
            this.title = Strings.nullToEmpty(title);
            return this;
        }

        /**
         * @param author
         * @return the builder instance for chaining
         */
        public Builder author(final String author) {
            this.author = Strings.nullToEmpty(author);
            return this;
        }

        /**
         * @param subject
         * @return the builder instance for chaining
         */
        public Builder subject(final String subject) {
            this.subject = Strings.nullToEmpty(subject);
            return this;
        }

        /**
         * @param keywords
         * @return the builder instance for chaining
         */
        public Builder keywords(final String keywords) {
            this.keywords = Strings.nullToEmpty(keywords);
            return this;
        }

        /**
         * @param creationDate
         * @return the builder instance for chaining
         */
        public Builder creationDate(final Date creationDate) {
            if (creationDate != null)
                this.creationDate = new Date(creationDate.getTime());
            return this;
        }

        /**
         * @param modificationDate
         * @return the builder instance for chaining
         */
        public Builder modificationDate(final Date modificationDate) {
            if (modificationDate != null)
                this.modificationDate = new Date(modificationDate.getTime());
            return this;
        }

        /**
         * @param creationDate
         * @return the builder instance for chaining
         */
        public Builder creationDate(final Calendar creationDate) {
            if (creationDate != null)
                this.creationDate = new Date(creationDate.getTimeInMillis());
            return this;
        }

        /**
         * @param modificationDate
         * @return the builder instance for chaining
         */
        public Builder modificationDate(final Calendar modificationDate) {
            if (modificationDate != null)
                this.modificationDate = new Date(
                        modificationDate.getTimeInMillis());
            return this;
        }

        /**
         * @param timeInMillis
         * @return the builder instance for chaining
         */
        public Builder creationDate(final long timeInMillis) {
            this.creationDate = new Date(timeInMillis);
            return this;
        }

        /**
         * @param timeInMillis
         * @return the builder instance for chaining
         */
        public Builder modificationDate(final long timeInMillis) {
            this.modificationDate = new Date(timeInMillis);
            return this;
        }

        /**
         * @param creator
         * @return the builder instance for chaining
         */
        public Builder creator(final String creator) {
            this.creator = Strings.nullToEmpty(creator);
            return this;
        }

        /**
         * @param producer
         * @return the builder instance for chaining
         */
        public Builder producer(final String producer) {
            this.producer = Strings.nullToEmpty(producer);
            return this;
        }

        /**
         * @param version
         * @return the builder instance for chaining
         */
        public Builder version(final float version) {
            this.version = version;
            return this;
        }

        /**
         * @param pageCount
         * @return the builder instance for chaining
         */
        public Builder pageCount(final int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        /**
         * @param trapped
         * @return the builder instance for chaining
         */
        public Builder trapped(final String trapped) {
            this.trapped = trapped;
            return this;
        }

        /**
         * @param fonts
         * @return the builder instance for chaining
         */
        public Builder fonts(final List<FontMetadata> fonts) {
            this.fonts = fonts;
            return this;
        }

        /**
         * @param images
         * @return the builder instance for chaining
         */
        public Builder images(final List<ImageMetadata> images) {
            this.images = images;
            return this;
        }

        /**
         * @return a DocumentMetadata instance built from the builder value
         */
        public DocumentMetadata build() {
            return new DocumentMetadata(this.title, this.author, this.subject,
                    this.keywords, this.creationDate, this.modificationDate,
                    this.creator, this.producer, this.version, this.pageCount,
                    this.trapped, this.fonts, this.images);
        }
    }
}
