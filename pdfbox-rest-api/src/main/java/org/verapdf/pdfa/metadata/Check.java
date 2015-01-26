/**
 * 
 */
package org.verapdf.pdfa.metadata;

import org.apache.pdfbox.preflight.ValidationResult.ValidationError;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that currently copies the PDFBox Preflight VaildationResult and ValidationError.
 *
 * TODO: PDFBox Preflight chose to effectively implement their own identifiers for checks.
 *       These are held in the org.apache.pdfbox.preflight.PreflghtConstants class. We need
 *       to use the "canonical" specification codes, i.e. 6.1 = group for File Structure,
 *       6.1.2 == File Header checks
 *       Questions: This numbering appears to be consistent across spec documents, is this the case?
 *       If so there needs to be a mapping between preflight codes and the spec ones. 
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 */
public class Check {
    /**
     * The default id for a non existent check
     */
    public final static String DEFAULT_ID = "-1"; //$NON-NLS-1$
    /**
     * The default code for a check
     */
    public final static String DEFAULT_CODE = "unknown"; //$NON-NLS-1$
    private final static Check DEFAULT_INSTANCE = new Check();
    private final String id;
    private final String code;
    
    private Check() {
        this(DEFAULT_ID, DEFAULT_CODE);
    }
    
    private Check(final String id, final String code) {
        this.id = id;
        this.code = code;
    }
    
    /**
     * @return the id for this condition
     */
    @JsonProperty
    public String getId() {
        return this.id;
    }

    /**
     * @return the code for the check
     */
    @JsonProperty
    public String getCode() {
        return this.code;
    }
    
    /**
     * @return the default and meaningless check instance, used for testing
     */
    public final static Check defaultInstance() {
        return DEFAULT_INSTANCE;
    }
    
    /**
     * @param preflightError a Preflight error to convert to a VeraPDF Check
     * @return a Check with details populated from the passed preflightError
     */
    public final static Check fromPreflightError(final ValidationError preflightError) {
        // FIXME: This is most definitely wrong but this requires thinking about the 
        //        IDs for checks than I have time for now.
        return new Check(preflightError.getErrorCode(), preflightError.getDetails());
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Check)) {
			return false;
		}
		Check other = (Check) obj;
		if (this.code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!this.code.equals(other.code)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
