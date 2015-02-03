package org.verapdf.pdfbox.rest.views;

import io.dropwizard.views.View;
import org.verapdf.pdfbox.rest.api.environment.Environment;

/**
 * Created by Timur on 2/3/2015.
 */
public class AboutView extends View {

    private Environment environment;

    /**
     *
     */
    public AboutView(Environment environment) {
        super("about.mustache");
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
