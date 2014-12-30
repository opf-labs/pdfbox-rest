/**
 * 
 */
package org.verapdf.pdfa.spec;

import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
public final class SectionImpl implements Section {
    /** String separator for Section and Sub-Section */
    public final static String SEPARATOR = "."; //$NON-NLS-1$
    static final Section DEFAULT_INSTANCE = new SectionImpl();

    private final Id id;
    private final String name;
    private final Section parent;
    private final SortedSet<Section> subSections;

    private SectionImpl() {
        this(IdImpl.DEFAULT_ID, "DEFAULT", null, new TreeSet<Section>()); //$NON-NLS-1$
    }

    SectionImpl(final Id id, final String name, final Section parent,
            final SortedSet<Section> subSections) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.subSections = new TreeSet<>(subSections);
        if (this.parent != null)
            this.parent.addSubSection(this);
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public Id getId() {
        return this.id;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public String getTitle() {
        return this.getId() + " " + this.getName(); //$NON-NLS-1$
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public Section getParent() {
        return this.parent;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public boolean isRoot() {
        return (this.parent == null);
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public boolean isParentOf(Section isChild) {
        return this.isChildOfThis(isChild, false);
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public boolean isAncestorOf(Section isDescendant) {
        return this.isChildOfThis(isDescendant, true);
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public boolean isParentOf(Id isChild) {
        return this.isChildOfThis(isChild, false);
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public boolean isAncestorOf(Id isDescendant) {
        return this.isChildOfThis(isDescendant, true);
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public SortedSet<Section> getSubSections() {
        return Collections.unmodifiableSortedSet(this.subSections);
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public boolean addSubSection(Section subSection) {
        return this.subSections.add(subSection);
    }


    /**
     * { @inheritDoc }
     */
    @Override
    public Section getSubSection(Id toFind) {
        return this.getSubSectionById(toFind);
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public String toString() {
        return "SectionImpl [id=" + this.id + ", name=" //$NON-NLS-1$ //$NON-NLS-2$
                + this.name + ", children=" + this.subSections + "]"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Section)) {
            return false;
        }
        Section other = (Section) obj;
        if (this.id == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!this.id.equals(other.getId())) {
            return false;
        }
        return true;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public int compareTo(Section o) {
        if (o == null) {
            throw new NullPointerException("Passed Section parm o == null"); //$NON-NLS-1$
        }
        return this.getId().compareTo(o.getId());
    }

    private boolean isChildOfThis(Section toTest, boolean recurse) {
        if (this.equals(toTest)) {
            return false;
        }
        for (Section subSection : this.getSubSections()) {
            if (subSection.equals(toTest)) {
                return true;
            }
            if (recurse && subSection.isAncestorOf(toTest)) {
                return true;
            }
        }
        return false;
    }

    private boolean isChildOfThis(Id toTest, boolean recurse) {
        if (this.getId().equals(toTest)) {
            return false;
        }
        for (Section subSection : this.getSubSections()) {
            if (subSection.getId().equals(toTest)) {
                return true;
            }
            if (recurse && subSection.isAncestorOf(toTest)) {
                return true;
            }
        }
        return false;
    }

    private Section getSubSectionById(Id toFind) {
        if (this.getId().equals(toFind)) {
            return this;
        }
        if  (!this.isAncestorOf(toFind)) {
            return null;
        }
        for (Section subSection : this.getSubSections()) {
            if (subSection.getId().equals(toFind)) {
                return subSection;
            }
            Section test = subSection.getSubSection(toFind);
            if (test != null)
                return test;
        }
        return null;
    }

    /**
     *
     */
    public static final class IdImpl implements Id {
        final static Integer DEFAULT_VALUE = Integer.valueOf(0);
        final static IdImpl DEFAULT_ID = new IdImpl();
        private final String value;

        private IdImpl() {
            this(DEFAULT_VALUE);
        }

        private IdImpl(final String value) {
            this.value = value;
        }

        private IdImpl(final Integer ordinal) {
            this(ordinal.toString());
        }

        private IdImpl(final Integer ordinal, final Id parent) {
            this(parent.getValue() + SEPARATOR + ordinal.toString());
        }

        /**
         * { @inheritDoc }
         */
        @Override
        public String getValue() {
            return this.value;
        }

        /**
         * { @inheritDoc }
         */
        @Override
        public String toString() {
            return "IdImpl [value=" + this.value + "]"; //$NON-NLS-1$ //$NON-NLS-2$
        }

        /**
         * { @inheritDoc }
         */
        @Override
        public int compareTo(Id o) {
            if (o == null) {
                throw new NullPointerException("Passed Section parm o == null"); //$NON-NLS-1$
            }
            return this.value.compareTo(o.getValue());
        }

        /**
         * { @inheritDoc }
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((this.value == null) ? 0 : this.value.hashCode());
            return result;
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
            if (!(obj instanceof IdImpl)) {
                return false;
            }
            IdImpl other = (IdImpl) obj;
            if (this.value == null) {
                if (other.value != null) {
                    return false;
                }
            } else if (!this.value.equals(other.value)) {
                return false;
            }
            return true;
        }

        static IdImpl defaultInstance() {
            return DEFAULT_ID;
        }

        public static IdImpl fromValues(final int ordinal) {
            return new IdImpl(Integer.valueOf(ordinal));
        }

        static IdImpl fromValues(final Integer ordinal) {
            return new IdImpl(ordinal);
        }

        public static IdImpl fromValues(final int ordinal, final Id id) {
            return new IdImpl(Integer.valueOf(ordinal), id);
        }

        static IdImpl fromValues(final Integer ordinal, final Id id) {
            return new IdImpl(ordinal, id);
        }
    }

    /**
     * Static Builder class for Section instances
     */
    @SuppressWarnings("hiding")
    public static class Builder {
        private Id id = IdImpl.DEFAULT_ID;
        private String name = ""; //$NON-NLS-1$
        private Section parent = null;
        private SortedSet<Section> subSections = new TreeSet<>();

        private Builder() {
            this(SectionImpl.DEFAULT_INSTANCE);
        }

        private Builder(final Section section) {
            this(section.getId(), section.getName(), section.getParent(), section.getSubSections());
        }
        
        private Builder(final Id id, final String name, final Section parent, final Set<Section> subSections) {
            this.id = id;
            this.name = name;
            this.parent = parent;
            this.subSections = new TreeSet<>(subSections);
        }
        
        private Builder(final int ordinal, final String name, final Section parent) {
            this.id = IdImpl.fromValues(ordinal, parent.getId());
            this.name = name;
        }

        /**
         * @param id
         *            new value for the String id
         * @return the Builder instance
         */
        public Builder id(final Id id) {
            this.id = id;
            return this;
        }

        /**
         * @param name
         *            the name of the section
         * @return the Builder instance
         */
        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @param parent
         *            the parent of the section
         * @return the Builder instance
         */
        public Builder parent(final Section parent) {
            this.parent = parent;
            return this;
        }

        /**
         * @return a Section object created from the values
         */
        public SectionImpl build() {
            return new SectionImpl(this.id, this.name, this.parent,
                    this.subSections);
        }
        
        static Builder defaultInstance() {
            return new Builder();
        }

        static Builder fromSection(final Section section) {
            return new Builder(section);
        }

        static Builder fromValues(final int ordinal, final String name, final Section parent) {
            return new Builder(ordinal, name, parent).parent(parent);
        }
    }
}
