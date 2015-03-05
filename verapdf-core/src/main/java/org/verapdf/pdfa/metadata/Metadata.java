/**
 * 
 */
package org.verapdf.pdfa.metadata;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.verapdf.pdfa.metadata.DocumentMetadata.Builder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
     * @return a DocumentMetadata object populated from the PDDocument
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
        builder.trapped(docInfo.getTrapped());
        builder.fonts(extractFontInfoFromPdfBoxPages(doc.getPages().iterator()));
        builder.images(extractImageInfoFromPdfBoxPages(doc.getPages().iterator()));
        return builder.build();
    }

    /**
     * Populates a FontMetadata list instance from a PDFBox pages
     *
     * @param pages
     *            A PDFBox pages used to populate the FontMetadata list.
     * @return a FontMetadata list populated from the pages
     */
    private final static List<FontMetadata> extractFontInfoFromPdfBoxPages(final Iterator<PDPage> pages) throws IOException {
        List<FontMetadata> fonts = new ArrayList<>();
        while (pages.hasNext()) {
            PDPage curPage = pages.next();
            PDResources pdResources = curPage.getResources();
            Iterable<COSName> fontNames = pdResources.getFontNames();
            for (COSName fontName : fontNames) {
                PDFont font = pdResources.getFont(fontName);
                fonts.add(convertPDFont(font));
            }
        }
        return fonts;
    }

    /**
     * Converts PDFBox PDFont object to FontMetadata
     *
     * @param font
     * @return a FontMetadata object converted from PDFont
     */
    private final static FontMetadata convertPDFont(PDFont font) {
        FontMetadata.Builder builder = new FontMetadata.Builder();
        builder.subtype(font.getCOSObject().getNameAsString(COSName.SUBTYPE));
        builder.name(font.getCOSObject().getNameAsString(COSName.NAME));
        builder.baseName(font.getCOSObject().getNameAsString(COSName.BASE_FONT));
        builder.firstChar(font.getCOSObject().getInt(COSName.FIRST_CHAR));
        builder.lastChar(font.getCOSObject().getInt(COSName.LAST_CHAR));

        //TODO: some secure checks required
        COSArray widthsArray = (COSArray) font.getCOSObject().getItem(COSName.WIDTHS);
        builder.widths((List<Integer>) widthsArray.toList());

        builder.embedded(font.isEmbedded());
        return builder.build();
    }

    /**
     * Populates a ImageMetadata list instance from a PDFBox pages
     *
     * @param pages
     *            A PDFBox pages used to populate the ImageMetadata list.
     * @return a ImageMetadata list populated from the pages
     */
    private final static List<ImageMetadata> extractImageInfoFromPdfBoxPages(final Iterator<PDPage> pages) throws IOException {
        List<ImageMetadata> images = new ArrayList<>();
        while (pages.hasNext()) {
            PDPage curPage = pages.next();
            PDResources pdResources = curPage.getResources();
            Iterable<COSName> imageNames = pdResources.getXObjectNames();
            for (COSName imageName : imageNames) {
                PDXObject xObject = pdResources.getXObject(imageName);
                if (xObject instanceof PDImageXObject) {
                    images.add(convertPDImageXObject((PDImageXObject) xObject));
                }
            }

        }
        return images;
    }

    /**
     * Converts PDFBox PDImageXObject to ImageMetadata
     *
     * @param image
     * @return a ImageMetadata object converted from PDImageXObject
     */
    private final static ImageMetadata convertPDImageXObject(PDImageXObject image) {
        ImageMetadata.Builder builder = new ImageMetadata.Builder();
        builder.width(image.getWidth());
        builder.height(image.getHeight());
        return builder.build();
    }

    /**
     * @param input an java.io.InputStream that's believed to be a PDF Document stream
     * @return a DocumentMetadata instance populated from the parsed stream
     * @throws IOException when a problem occurs reading the stream
     */
    public final static DocumentMetadata fromInputStream(final InputStream input)
            throws IOException {
        try (PDDocument pdDoc = PDDocument.load(input)) {
            DocumentMetadata docMd = fromPdfBoxDocInfo(pdDoc);
            return docMd;
        }
    }

    /**
     * @return default DocumentMetadata instance, mostly used for testing
     */
    public final static DocumentMetadata defaultInstance() {
        return DocumentMetadata.DEFAULT_INSTANCE;
    }
}
