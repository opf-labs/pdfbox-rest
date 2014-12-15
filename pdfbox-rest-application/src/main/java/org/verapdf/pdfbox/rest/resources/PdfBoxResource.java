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
import org.verapdf.pdfa.metadata.ValidationReport;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
@Path("/pdfbox")
public class PdfBoxResource {
    public PdfBoxResource() {

    }

    /**
     * @param uploadedInputStream
     * @return the java environment representation
     * @throws IOException
     * 
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
}
