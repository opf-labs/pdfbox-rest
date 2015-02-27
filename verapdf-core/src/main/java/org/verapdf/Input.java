package org.verapdf;

public class Input {

    private final String path;
    private final boolean url;

    Input(String path, boolean url) {
        this.path = path;
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public boolean isUrl() {
        return url;
    }
}
