/**
 * 
 */
package org.verapdf.pdfbox.rest.resources;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.verapdf.pdfa.metadata.bytestream.ByteStreamId;
import org.verapdf.pdfa.metadata.bytestream.ByteStreams;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
@Path("/sha1")
public class Sha1Resource {
    /**
     * 
     */
    public Sha1Resource() {
    }

    /**
     * @param uploadedInputStream
     * @return the java environment representation
     * @throws IOException
     * 
     */
    @SuppressWarnings("static-method")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public ByteStreamId getSha1(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") final FormDataContentDisposition contentDispositionHeader) {
        try {
            ByteStreamId id = ByteStreams.idFromStream(uploadedInputStream);
            uploadedInputStream.close();
            return id;// return
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ByteStreams.nullByteStreamId();// return
                                              // ByteStreams.nullByteStreamId();
    }

    /**
     * @return the java environment representation
     * 
     */
    @SuppressWarnings("static-method")
    @GET
    @Path("/null")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public ByteStreamId getEmptySha1() {
        return ByteStreams.nullByteStreamId();
    }
}
