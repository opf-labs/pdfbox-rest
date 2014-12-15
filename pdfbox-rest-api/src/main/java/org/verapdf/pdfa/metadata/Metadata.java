/**
 * 
 */
package org.verapdf.pdfa.metadata;

import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.verapdf.pdfa.metadata.DocumentMetadata;
import org.verapdf.pdfa.metadata.DocumentMetadata.Builder;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
public class Metadata {
    private Metadata() {
        // Should never be in private constructor
    }

    /**
     * Populates a DocumentMetadata instance from a PDFBox PDDocument.
     * 
     * @param doc
     *            A PDFBox PDDocument used to populate the DocumentMetadata
     *            instance.
     * @return a DocumentMetada object populated from the PDDocument
     * @throws IOException
     */
    public final static DocumentMetadata fromPdfBoxDocInfo(final PDDocument doc)
            throws IOException {
        PDDocumentInformation docInfo = doc.getDocumentInformation();
        Builder builder = new Builder();
        builder.title(docInfo.getTitle()).author(docInfo.getAuthor())
                .subject(docInfo.getSubject()).keywords(docInfo.getKeywords())
                .creator(docInfo.getCreator()).producer(docInfo.getProducer());
        builder.version(doc.getDocument().getVersion()).pageCount(
                doc.getNumberOfPages());
        builder.creationDate(docInfo.getCreationDate());
        builder.modificationDate(docInfo.getModificationDate());
        return builder.build();
    }

    /**
     * @param input an java.io.InputStream that's believed to be a PDF Document stream 
     * @return a DocumentMetadata instance populated from the parsed stream
     * @throws IOException when a problem occurs reading the stream
     */
    public final static DocumentMetadata fromInputStream(final InputStream input)
            throws IOException {
        PDDocument pdDoc = PDDocument.load(input);
        DocumentMetadata docMd = fromPdfBoxDocInfo(pdDoc);
        pdDoc.close();
        return docMd;
    }

    /**
     * @return default DocumentMetadata instance, mostly used for testing
     */
    public final static DocumentMetadata defaultInstance() {
        return DocumentMetadata.DEFAULT_INSTANCE;
    }
}
