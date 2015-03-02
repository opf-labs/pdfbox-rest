package org.verapdf;

/**
 * This class holds all params defining the destination for the progress reporting.
 * @author Timur Kamalov
 */
public class Progress {

    private final boolean stdout;
    private final String path;
    private final boolean url;

    Progress(boolean stdout, String path, boolean url) {
        this.stdout = stdout;
        this.path = path;
        this.url = url;
    }

    /**
     * @return the stdout
     */
    public boolean isStdout() {
        return stdout;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the url
     */
    public boolean isUrl() {
        return url;
    }
}