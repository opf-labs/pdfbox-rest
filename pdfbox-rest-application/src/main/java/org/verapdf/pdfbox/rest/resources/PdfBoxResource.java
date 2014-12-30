/**
 * 
 */
package org.verapdf.pdfbox.rest.resources;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.verapdf.pdfa.metadata.DocumentMetadata;
import org.verapdf.pdfa.metadata.Metadata;
import org.verapdf.pdfa.reports.ValidationReport;
import org.verapdf.pdfbox.rest.views.ReportView;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * The REST resource definition for PDFBox services, these are JERSEY
 * REST services and it's the annotations that perform the magic of
 * handling content types and serialisation.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 */
@Path("/pdfbox")
public class PdfBoxResource {
    /**
     * 
     */
    public PdfBoxResource() {

    }

    /**
     * POST method that consumes a file upload from a multi-part form. The
     * uploaded stream is expected to be a PDF, and is used to create a
     * {@link org.verapdf.pdfa.metadata.DocumentMetadata} object populated using
     * a PDF Box parser.
     * 
     * @param uploadedInputStream
     *            InputStream for the uploaded file
     * @param contentDispositionHeader
     *            extra info about the uploaded file, currently unused.
     * @return a serialized version of a
     *         {@link org.verapdf.pdfa.metadata.DocumentMetadata} instance
     *         either as XML or JSON depending upon the content type requested.
     */
    @SuppressWarnings("static-method")
    @POST
    @Path("/docinfo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public DocumentMetadata getDocumentMetadata(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") final FormDataContentDisposition contentDispositionHeader) {
        try {
            DocumentMetadata docMd = Metadata
                    .fromInputStream(uploadedInputStream);
            uploadedInputStream.close();
            return docMd;// return
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Metadata.defaultInstance();// return
                                          // ByteStreams.nullByteStreamId();
    }

    /**
     * @param uploadedInputStream
     *            InputStream for the uploaded file
     * @param contentDispositionHeader
     *            extra info about the uploaded file, currently unused.
     * @return the java environment representation
     * @throws IOException
     * 
     */
    @SuppressWarnings("static-method")
    @POST
    @Path("/validate")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public ValidationReport validate(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") final FormDataContentDisposition contentDispositionHeader)
            throws IOException {
        ValidationReport result = ValidationReport
                .fromPdfStream(uploadedInputStream);
        uploadedInputStream.close();
        return result;// return
    }

    /**
     * @param uploadedInputStream
     *            InputStream for the uploaded file
     * @param contentDispositionHeader
     *            extra info about the uploaded file, currently unused.
     * @return the java environment representation
     * @throws IOException
     * 
     */
    @SuppressWarnings("static-method")
    @POST
    @Path("/validate")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({ MediaType.TEXT_HTML })
    public ReportView validateHTML(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") final FormDataContentDisposition contentDispositionHeader)
            throws IOException {
        ValidationReport result = ValidationReport
                .fromPdfStream(uploadedInputStream);
        uploadedInputStream.close();
        return new ReportView(result);// return
    }
}
