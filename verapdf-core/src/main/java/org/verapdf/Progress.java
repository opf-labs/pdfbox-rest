package org.verapdf;

public class Progress {

    private final boolean stdout;
    private final String path;
    private final boolean url;

    Progress(boolean stdout, String path, boolean url) {
        this.stdout = stdout;
        this.path = path;
        this.url = url;
    }

    public boolean isStdout() {
        return stdout;
    }

    public String getPath() {
        return path;
    }

    public boolean isUrl() {
        return url;
    }
}