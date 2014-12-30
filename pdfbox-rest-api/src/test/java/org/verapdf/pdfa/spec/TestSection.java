/**
 * 
 */
package org.verapdf.pdfa.spec;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;
import org.verapdf.pdfa.spec.Section.Id;
import org.verapdf.pdfa.spec.SectionImpl.Builder;
import org.verapdf.pdfa.spec.SectionImpl.IdImpl;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
@SuppressWarnings({"nls", "static-method"})
public class TestSection {
    /**
     * Test the hash and equals contract for the class using EqualsVerifier
     */
    @Test
    public void testIdEqualsContract() {
        EqualsVerifier.forClass(IdImpl.class).verify();
    }

    /**
     * 
     */
    @Test
    public final void testIdImplConstructors() {
        IdImpl defaultId = IdImpl.defaultInstance();
        IdImpl zeroId = IdImpl.fromValues(IdImpl.DEFAULT_VALUE);
        IdImpl zeroChild = IdImpl.fromValues(IdImpl.DEFAULT_VALUE, zeroId);
        assertTrue(defaultId.toString() + ".equals(" + zeroId.toString() + ") == false", defaultId.equals(zeroId));
        assertFalse(defaultId.toString() + ".equals(" + zeroChild.toString() + ") == true", defaultId.equals(zeroChild));
        assertFalse(zeroId.toString() + ".equals(" + zeroChild.toString() + ") == true", zeroId.equals(zeroChild));
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#compareTo(org.verapdf.pdfa.spec.Section.Id)}.
     */
    @Test(expected = NullPointerException.class)
    public final void testIdCompareToNull() {
        int starter = 1;
        IdImpl parent = IdImpl.fromValues(starter);
        parent.compareTo(null);
        fail("Should never reach here.");
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#compareTo(org.verapdf.pdfa.spec.Section.Id)}.
     */
    @Test
    public final void testIdCompareTo() {
        int starter = 1;
        IdImpl topParent = IdImpl.fromValues(starter);
        List<IdImpl> childIds = new ArrayList<>();
        for (int index = starter; index < 10; index++) {
            childIds.add(IdImpl.fromValues(index, topParent));
        }
        
        Id lesser = topParent;
        for (Id greater : childIds) {
            compareIds(lesser, greater);
            lesser = greater;
        }
        
        lesser = childIds.get(0);
        Id greater = IdImpl.fromValues(starter, childIds.get(1));
        compareIds(lesser, greater);
        lesser = childIds.get(1);
        greater = IdImpl.fromValues(starter, childIds.get(2));
        compareIds(lesser, greater);
    }

    /**
     * Test the hash and equals contract for the class using EqualsVerifier
     */
    @Test
    public void testSectionEqualsContract() {
        EqualsVerifier.forClass(SectionImpl.class).verify();
    }

    /**
     * Test method for { @link org.verapdf.pdfa.spec.SectionImpl#compareTo(org.verapdf.pdfa.spec.Section) }.
     */
    @Test(expected = NullPointerException.class)
    public final void testSectionCompareToNull() {
        int starter = 1;
        Builder sectionBuilder = Builder.defaultInstance();
        sectionBuilder.id(IdImpl.fromValues(Integer.valueOf(starter))).name("Section:" + starter);
        Section parent = sectionBuilder.build();
        parent.compareTo(null);
        fail("Should never reach here.");
    }
    /**
     * Test method for { @link org.verapdf.pdfa.spec.SectionImpl#compareTo(org.verapdf.pdfa.spec.Section) }.
     */
    @Test
    public final void testSectionCompareTo() {
        int starter = 1;
        Builder sectionBuilder = Builder.defaultInstance();
        sectionBuilder.id(IdImpl.fromValues(Integer.valueOf(starter))).name("Section:" + starter);
        Section root = sectionBuilder.build();
        sectionBuilder = Builder.fromSection(root);
        assertTrue(root.equals(sectionBuilder.build()));
        assertTrue(root.isRoot());
        assertFalse(root.isAncestorOf(root));
        assertTrue(root.getSubSection(root.getId()).equals(root));
        List<Section> subSections = new ArrayList<>();
        for (int index = starter; index < 10; index++) {
            sectionBuilder = Builder.fromValues(index, root.getName() + SectionImpl.SEPARATOR + index, root);
            Section subSection = sectionBuilder.build();
            root.addSubSection(subSection);
            subSections.add(subSection);
            assertTrue(root.isParentOf(subSection));
            assertTrue(root.isAncestorOf(subSection));
            assertTrue(root.getSubSection(subSection.getId()).equals(subSection));
            assertFalse(subSection.isRoot());
            assertFalse(subSection.isAncestorOf(subSection));
            assertNull(subSection.getSubSection(root.getId()));
        }
        
        Section lesser = root;
        for (Section greater : subSections) {
            compareSections(lesser, greater);
            lesser = greater;
        }

        
        lesser = subSections.get(0);
        Section parent = subSections.get(1);
        sectionBuilder = Builder.fromValues(1, parent.getName() + SectionImpl.SEPARATOR + 1, parent);
        Section greater = sectionBuilder.build();
        parent.addSubSection(greater);
        assertTrue(parent.isParentOf(greater));
        assertTrue(parent.isAncestorOf(greater));
        assertTrue(parent.getSubSection(greater.getId()).equals(greater));
        assertNull(greater.getSubSection(parent.getId()));
        assertTrue(root.isAncestorOf(greater));
        assertTrue(root.getSubSection(greater.getId()).equals(greater));
        assertNull(greater.getSubSection(root.getId()));
        compareSections(lesser, greater);
        lesser = subSections.get(1);
        parent = subSections.get(2);
        sectionBuilder = Builder.fromValues(2, parent.getName() + SectionImpl.SEPARATOR + 1, parent);
        greater = sectionBuilder.build();
        assertTrue(parent.isParentOf(greater));
        assertTrue(parent.isAncestorOf(greater));
        assertTrue(parent.getSubSection(greater.getId()).equals(greater));
        assertNull(greater.getSubSection(parent.getId()));
        assertTrue(root.isAncestorOf(greater));
        assertTrue(root.getSubSection(greater.getId()).equals(greater));
        assertNull(greater.getSubSection(root.getId()));
        compareSections(lesser, greater);
}

    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl#getTitle()}.
     */
    @Test
    public final void testGetTitle() {
        int starter = 1;
        Builder sectionBuilder = Builder.defaultInstance();
        Section section = sectionBuilder.id(IdImpl.fromValues(Integer.valueOf(starter))).name("Section:" + starter).build();
        assertTrue(section.getTitle().contains(section.getName()));
    }

    private final static void compareIds(Id lesser, Id greater) {
        assertTrue(greater.toString() + ".compareTo(" + lesser.toString() + ") <=  0", greater.compareTo(lesser) > 0);
        assertTrue(lesser.toString() + ".compareTo(" + greater.toString() + ") >=  0", lesser.compareTo(greater) < 0);
        assertTrue(greater.toString() + ".compareTo(" + greater.toString() + ") !=  0", greater.compareTo(greater) == 0);
        assertTrue(greater.toString() + ".equals(" + greater.toString() + ") == false", greater.equals(greater));
        assertTrue(lesser.toString() + ".compareTo(" + lesser.toString() + ") !=  0", lesser.compareTo(lesser) == 0);
        assertTrue(lesser.toString() + ".equals(" + lesser.toString() + ") == false", lesser.equals(lesser));
    }

    private final static void compareSections(Section lesser, Section greater) {
        assertTrue(greater.toString() + ".compareTo(" + lesser.toString() + ") <=  0", greater.compareTo(lesser) > 0);
        assertTrue(lesser.toString() + ".compareTo(" + greater.toString() + ") >=  0", lesser.compareTo(greater) < 0);
        assertTrue(greater.toString() + ".compareTo(" + greater.toString() + ") !=  0", greater.compareTo(greater) == 0);
        assertTrue(greater.toString() + ".equals(" + greater.toString() + ") == false", greater.equals(greater));
        assertTrue(lesser.toString() + ".compareTo(" + lesser.toString() + ") !=  0", lesser.compareTo(lesser) == 0);
        assertTrue(lesser.toString() + ".equals(" + lesser.toString() + ") == false", lesser.equals(lesser));
    }
}
