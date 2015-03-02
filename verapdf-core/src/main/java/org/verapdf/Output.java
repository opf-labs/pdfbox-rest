package org.verapdf;

/**
 * This class holds all params defining output pdf stream.
 * @author Timur Kamalov
 */
public class Output {

    private final boolean inputpath;
    private final String path;
    private final boolean url;

    Output(boolean inputpath, String path, boolean url) {
        this.inputpath = inputpath;
        this.path = path;
        this.url = url;
    }

    /**
     * @return inputPath
     */
    public boolean isInputpath() {
        return inputpath;
    }

    /**
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return url
     */
    public boolean isUrl() {
        return url;
    }
}
