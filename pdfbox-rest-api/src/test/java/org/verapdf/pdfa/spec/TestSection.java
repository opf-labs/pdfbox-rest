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
    	// Run equals verifier on the IdImpl class
        EqualsVerifier.forClass(IdImpl.class).verify();
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#defaultInstance()}.
     */
    @Test
    public final void testDefaultIdInstance() {
        Id defaultId = IdImpl.defaultInstance();
    	// Check that the default instance is always the same instance
        assertTrue(defaultId == IdImpl.defaultInstance());
    }
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#fromValues(int)}.
     */
    @Test
    public final void testIdfromInt() {
    	Id minId = IdImpl.fromValues(IdImpl.MINIMUM_VALUE);
    	// Check that the default instance isn't the same instance as min value
        assertFalse(IdImpl.defaultInstance() == minId);
        // Not the same instance so see if not equals is associative, it should be
        assertFalse(IdImpl.defaultInstance().equals(minId));
        assertFalse(minId.equals(IdImpl.defaultInstance()));
    	Id minIdCopy = IdImpl.fromValues(IdImpl.MINIMUM_VALUE);
    	// Check that min value copy isn't the same instance as min value
        assertFalse(minIdCopy == minId);
        // Not the same instance but see of equals is associative, it should be
        assertTrue(minIdCopy.equals(minId));
        assertTrue(minId.equals(minIdCopy));
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#fromValues(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testIdfromIntNegativeInt() {
    	// We're not allowed values less than 1
    	IdImpl.fromValues(IdImpl.MINIMUM_VALUE - 1);
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#fromValues(int, Id)}.
     */
    @Test
    public final void testIdfromIntAndId() {
    	// Check that a parent and child id aren't equal
    	Id zeroId = IdImpl.fromValues(IdImpl.MINIMUM_VALUE);
    	Id zeroChild = IdImpl.fromValues(IdImpl.MINIMUM_VALUE, zeroId);
        assertFalse(zeroId.toString() + ".equals(" + zeroChild.toString() + ") == true", zeroId.equals(zeroChild));
        assertFalse(zeroChild.toString() + ".equals(" + zeroId.toString() + ") == true", zeroChild.equals(zeroId));
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#fromValues(int, Id)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testIdfromIntAndIdNegativeInt() {
    	// We want a legal potential parent, but an illegal ordinal value
        Id zeroId = IdImpl.fromValues(IdImpl.MINIMUM_VALUE);
        IdImpl.fromValues(IdImpl.MINIMUM_VALUE - 1, zeroId);
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#fromValues(int, Id)}.
     */
    @Test(expected = NullPointerException.class)
    public final void testIdfromIntAndIdNullId() {
    	// This time a legal ordinal and null parent
        IdImpl.fromValues(IdImpl.MINIMUM_VALUE, null);
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#isRoot()}.
     */
    @Test
    public final void testIsRoot() {
    	// Only default should be root
    	assertTrue(IdImpl.defaultInstance().isRoot());
    	Id notRoot = IdImpl.fromValues(IdImpl.MINIMUM_VALUE);
    	assertFalse(notRoot.isRoot());
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#isParentOf(Id) }.
     */
    @Test
    public final void testIsParentOf() {
    	// Three generations to test
    	Id parent = IdImpl.fromValues(IdImpl.MINIMUM_VALUE);
    	Id child = IdImpl.fromValues(IdImpl.MINIMUM_VALUE, parent);
    	Id grandChild = IdImpl.fromValues(IdImpl.MINIMUM_VALUE, child);
    	// Parent is the parent of child
    	assertTrue(parent.isParentOf(child));
    	// Parent is not a child of child
    	assertFalse(child.isParentOf(parent));
    	// Child is not a child of itself
    	assertFalse(child.isParentOf(child));
    	// Parent is not a child of itself
    	assertFalse(parent.isParentOf(parent));
    	// Child is parent of grandChild
    	assertTrue(child.isParentOf(grandChild));
    	// Parent is NOT parent of grandChild
    	assertFalse(parent.isParentOf(grandChild));
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#isAncestorOf(Id) }.
     */
    @Test
    public final void testIsAncestorOf() {
    	Id parent = IdImpl.fromValues(IdImpl.MINIMUM_VALUE);
    	Id child = IdImpl.fromValues(IdImpl.MINIMUM_VALUE, parent);
    	Id grandChild = IdImpl.fromValues(IdImpl.MINIMUM_VALUE, child);
    	// Parent is an ancestor of parent
    	assertTrue(parent.isAncestorOf(child));
    	// Child is not an ancestor of parent
    	assertFalse(child.isAncestorOf(parent));
    	// Child is not an ancestor of child
    	assertFalse(child.isAncestorOf(child));
    	// Parent is not an ancestor of parent
    	assertFalse(parent.isAncestorOf(parent));
    	// Parent is ancestor of grandChild
    	assertTrue(parent.isAncestorOf(grandChild));
    	// Child is ancestor of grandChild
    	assertTrue(child.isAncestorOf(grandChild));
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#compareTo(org.verapdf.pdfa.spec.Section.Id)}.
     */
    @Test(expected = NullPointerException.class)
    public final void testIdCompareToNull() {
        IdImpl parent = IdImpl.fromValues(IdImpl.MINIMUM_VALUE);
        // Expect compareTo(null) to throw null exception
        parent.compareTo(null);
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#compareTo(org.verapdf.pdfa.spec.Section.Id)}.
     */
    @Test
    public final void testIdCompareTo() {
    	// Parent for a bunch of ids
        IdImpl topParent = IdImpl.fromValues(IdImpl.MINIMUM_VALUE);
        // List of children & grandchildren
        List<Id> children = new ArrayList<>();
        List<Id> grandChildren = new ArrayList<>();
        
        // Populate children and test them
        for (int index = IdImpl.MINIMUM_VALUE; index < 20; index++) {
            children.add(IdImpl.fromValues(index, topParent));
            for (int index2 = 0; index2 < (index - 1); index2++) {
            	compareIds(children.get(index2), children.get(index - 1));
            }
        }
        
        Id lesser = topParent;
        for (Id greater : children) {
            compareIds(lesser, greater);
            lesser = greater;
        }
        
        lesser = children.get(0);
        Id greater = IdImpl.fromValues(IdImpl.MINIMUM_VALUE, children.get(1));
        compareIds(lesser, greater);
        lesser = children.get(1);
        greater = IdImpl.fromValues(IdImpl.MINIMUM_VALUE, children.get(2));
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
        Builder sectionBuilder = Builder.defaultInstance();
        sectionBuilder.id(IdImpl.fromValues(IdImpl.MINIMUM_VALUE)).name("Section:" + IdImpl.MINIMUM_VALUE);
        Section parent = sectionBuilder.build();
        parent.compareTo(null);
        fail("Should never reach here.");
    }
    /**
     * Test method for { @link org.verapdf.pdfa.spec.SectionImpl#compareTo(org.verapdf.pdfa.spec.Section) }.
     */
    @Test
    public final void testSectionCompareTo() {
        Builder sectionBuilder = Builder.defaultInstance();
        sectionBuilder.id(IdImpl.fromValues(IdImpl.MINIMUM_VALUE)).name("Section:" + IdImpl.MINIMUM_VALUE);
        Section root = sectionBuilder.build();
        sectionBuilder = Builder.fromSection(root);
        assertTrue(root.equals(sectionBuilder.build()));
        assertTrue(root.isRoot());
        assertFalse(root.isAncestorOf(root));
        assertTrue(root.getSubSection(root.getId()).equals(root));
        List<Section> subSections = new ArrayList<>();
        for (int index = IdImpl.MINIMUM_VALUE; index < 10; index++) {
            sectionBuilder = Builder.fromValues(index, root.getName() + IdImpl.DEFAULT_SEPARATOR + index, root);
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
        Section parent = subSections.get(IdImpl.MINIMUM_VALUE);
        sectionBuilder = Builder.fromValues(IdImpl.MINIMUM_VALUE, parent.getName() + IdImpl.DEFAULT_SEPARATOR + 1, parent);
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
        sectionBuilder = Builder.fromValues(2, parent.getName() + IdImpl.DEFAULT_SEPARATOR + 1, parent);
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
        Builder sectionBuilder = Builder.defaultInstance();
        Section section = sectionBuilder.id(IdImpl.fromValues(IdImpl.MINIMUM_VALUE)).name("Section:" + IdImpl.MINIMUM_VALUE).build();
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
