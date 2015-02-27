package org.verapdf;

public class Output {

    private final boolean inputpath;
    private final String path;
    private final boolean url;

    Output(boolean inputpath, String path, boolean url) {
        this.inputpath = inputpath;
        this.path = path;
        this.url = url;
    }

    public boolean isInputpath() {
        return inputpath;
    }

    public String getPath() {
        return path;
    }

    public boolean isUrl() {
        return url;
    }
}
