/**
 * 
 */
package org.verapdf.pdfa.spec;


/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
public final class PdfaSpecificationImpl implements PdfaSpecification {
    static final PdfaSpecificationImpl DEFAULT_INSTANCE = new PdfaSpecificationImpl();
    private final PdfaFlavour flavour;
    private final Section root;

    private PdfaSpecificationImpl() {
        this(PdfaFlavour.PDFA_1_B, SectionImpl.DEFAULT_INSTANCE);
    }
    
    PdfaSpecificationImpl(final PdfaFlavour flavour, final Section root) {
        this.flavour = flavour;
        this.root = root;
    }
    
    /**
     * { @inheritDoc }
     */
    @Override
    public PdfaFlavour getFlavour() {
        return this.flavour;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public Section getRootSection() {
        return this.root;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.flavour == null) ? 0 : this.flavour.hashCode());
        result = prime * result + ((this.root == null) ? 0 : this.root.hashCode());
        return result;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public String toString() {
        return "PdfaSpecificationImpl [flavour=" + this.getFlavour() + ", root=" + this.getRootSection() //$NON-NLS-1$ //$NON-NLS-2$
                + "]"; //$NON-NLS-1$
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PdfaSpecificationImpl)) {
            return false;
        }
        PdfaSpecificationImpl other = (PdfaSpecificationImpl) obj;
        if (this.flavour != other.flavour) {
            return false;
        }
        if (this.root == null) {
            if (other.root != null) {
                return false;
            }
        } else if (!this.root.equals(other.root)) {
            return false;
        }
        return true;
    }

}
