/**
 * 
 */
package org.verapdf.pdfa.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Naive first cut at a class for recording the location for PDF Validation check fails.
 * 
 * TODO: This currently simply records the page number, and there's obviously no
 *       need for a class to do this. what's required is some kind of interface or
 *       abstract inheritance mechanism for different kinds of location, e.g.
 *       xmp metadata path, resources, etc.
 *       Someone who knows more about what's required than me to pick up the mantle here.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
public class Location {
    private final static Location DEFAULT_INSTANCE = new Location();
    private final int pageNumber;
    
    private Location() {
        this(-1);
    }
    
    private Location(final int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    /**
     * @return the page number that marks the location
     */
    @JsonProperty
    public int getPageNumber() {
        return this.pageNumber;
    }
    
    /**
     * @return the default location instance
     */
    public final static Location defaultInstance() {
        return DEFAULT_INSTANCE;
    }
    
    /**
     * @param pageNumber the page number on which the validation check failed
     * @return a new Location instance
     */
    public final static Location fromValues(final int pageNumber) {
        return new Location(pageNumber);
    }
}
