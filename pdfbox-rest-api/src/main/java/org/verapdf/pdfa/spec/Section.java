/**
 * 
 */
package org.verapdf.pdfa.spec;

import java.util.SortedSet;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
public interface Section extends Comparable<Section> {
    /**
     * @return the identifier of the specification section, in a normal section
     *         sub-section form, i.e. n.n.n where n is numberic.
     */
    public Id getId();

    /**
     * @return a descriptive name for the section
     */
    public String getName();

    /**
     * @return the full title, which is the id and name
     */
    public String getTitle();

    /**
     * @return the parent section
     */
    public Section getParent();

    /**
     * @return true if this section has no parents, i.e. it's the root
     */
    public boolean isRoot();

    /**
     * @param isChild
     *            Section object to be checked to see if it's a sub-section of
     *            this section
     * 
     * @return true if isChild is a direct child of this Section
     */
    public boolean isParentOf(Section isChild);

    /**
     * @param isDescendant
     *            Section object to be checked to see if it's in the descendants
     *            tree at all
     * @return true if isDescendant is a sub-section of this section or its
     *         sub-sections
     */
    public boolean isAncestorOf(Section isDescendant);

    /**
     * @param isChild
     *            Section object to be checked to see if it's a sub-section of
     *            this section
     * 
     * @return true if isChild is a direct child of this Section
     */
    public boolean isParentOf(Id isChild);

    /**
     * @param isDescendant
     *            Section object to be checked to see if it's in the descendants
     *            tree at all
     * @return true if isDescendant is a sub-section of this section or its
     *         sub-sections
     */
    public boolean isAncestorOf(Id isDescendant);

    /**
     * @return any child sub-sections
     */
    public SortedSet<Section> getSubSections();
    
    /**
     * @param subSection adds the subsection in natural ordering
     * @return true if sub-section added successfully
     */
    public boolean addSubSection(Section subSection);
    
    /**
     * @param toFind
     * @return
     */
    public Section getSubSection(Id toFind);
    
    /**
     *
     */
    public static interface Id extends Comparable<Id> {
        /**
         * @return the String value of the Section id
         */
        public String getValue();
    }
}
