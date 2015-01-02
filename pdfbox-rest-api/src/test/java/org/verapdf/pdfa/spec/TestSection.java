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
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#defaultInstance()}.
     */
    @Test
    public final void testDefaultIdInstance() {
    	// Check that the default instance is always the same instance
        Id defaultId = IdImpl.defaultInstance();
        assertTrue(defaultId == IdImpl.defaultInstance());
    }
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#fromValues(int)}.
     */
    @Test
    public final void testIdfromInt() {
    	// Check that the default instance isn't the same instance as zero value, but it IS equal
    	Id defaultId = IdImpl.defaultInstance();
    	Id zeroId = IdImpl.fromValues(IdImpl.DEFAULT_VALUE);
        assertFalse(defaultId == zeroId);
        assertTrue(defaultId.toString() + ".equals(" + zeroId.toString() + ") == false", defaultId.equals(zeroId));
        assertTrue(zeroId.toString() + ".equals(" + defaultId.toString() + ") == false", zeroId.equals(defaultId));
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#fromValues(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testIdfromIntNegativeInt() {
    	IdImpl.fromValues(-1);
    }

    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#fromValues(int, Id)}.
     */
    @Test
    public final void testIdfromIntAndId() {
    	// Check that a parent and child id aren't equal
    	Id zeroId = IdImpl.fromValues(IdImpl.DEFAULT_VALUE);
    	Id zeroChild = IdImpl.fromValues(IdImpl.DEFAULT_VALUE, zeroId);
        assertFalse(zeroId.toString() + ".equals(" + zeroChild.toString() + ") == true", zeroId.equals(zeroChild));
        assertFalse(zeroChild.toString() + ".equals(" + zeroId.toString() + ") == true", zeroChild.equals(zeroId));
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#fromValues(int, Id)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testIdfromIntAndIdNegativeInt() {
    	// Check that a parent and child id aren't equal
        Id zeroId = IdImpl.fromValues(IdImpl.DEFAULT_VALUE);
        IdImpl.fromValues(-1, zeroId);
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#fromValues(int, Id)}.
     */
    @Test(expected = NullPointerException.class)
    public final void testIdfromIntAndIdnullId() {
        IdImpl.fromValues(IdImpl.DEFAULT_VALUE, null);
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#isRoot()}.
     */
    @Test
    public final void testIsRoot() {
    	Id defId = IdImpl.defaultInstance();
    	assertTrue(defId.isRoot());
    	Id root = IdImpl.fromValues(1);
    	assertTrue(root.isRoot());
    	Id nonRoot = IdImpl.fromValues(1, root);
    	assertFalse(nonRoot.isRoot());
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#getParentId() }.
     */
    @Test
    public final void testGetParentId() {
    	Id root = IdImpl.fromValues(1);
    	Id child = IdImpl.fromValues(1, root);
    	Id parentId = child.getParentId();
    	assertTrue(root.equals(parentId));
    	assertTrue(parentId.equals(root));
    	assertFalse(root.equals(child));
    	assertFalse(parentId.equals(child));
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#isParentOf(Id) }.
     */
    @Test
    public final void testIsParentOf() {
    	Id parent = IdImpl.fromValues(1);
    	Id child = IdImpl.fromValues(1, parent);
    	assertTrue(parent.isParentOf(child));
    	assertFalse(child.isParentOf(parent));
    	assertFalse(child.isParentOf(child));
    	assertFalse(parent.isParentOf(parent));
    }
    
    /**
     * Test method for {@link org.verapdf.pdfa.spec.SectionImpl.IdImpl#isAncestorOf(Id) }.
     */
    @Test
    public final void testIsAncestorOf() {
    	Id parent = IdImpl.fromValues(1);
    	Id child = parent.createChildId(1);
    	Id grandChild = child.createChildId(1);
    	assertTrue(parent.isAncestorOf(child));
    	assertFalse(child.isAncestorOf(parent));
    	assertFalse(child.isAncestorOf(child));
    	assertFalse(parent.isAncestorOf(parent));
    	assertTrue(parent.isAncestorOf(grandChild));
    	assertTrue(child.isAncestorOf(grandChild));
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
    	// Parent for a bunch of ids
        int starter = 1;
        IdImpl topParent = IdImpl.fromValues(starter);
        // List of children & grandchildren
        List<Id> children = new ArrayList<>();
        
        for (int index = starter; index < 20; index++) {
            children.add(topParent.createChildId(index));
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
        Id greater = IdImpl.fromValues(starter, children.get(1));
        compareIds(lesser, greater);
        lesser = children.get(1);
        greater = IdImpl.fromValues(starter, children.get(2));
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
        sectionBuilder.id(IdImpl.fromValues(starter)).name("Section:" + starter);
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
        sectionBuilder.id(IdImpl.fromValues(starter)).name("Section:" + starter);
        Section root = sectionBuilder.build();
        sectionBuilder = Builder.fromSection(root);
        assertTrue(root.equals(sectionBuilder.build()));
        assertTrue(root.isRoot());
        assertFalse(root.isAncestorOf(root));
        assertTrue(root.getSubSection(root.getId()).equals(root));
        List<Section> subSections = new ArrayList<>();
        for (int index = starter; index < 10; index++) {
            sectionBuilder = Builder.fromValues(index, root.getName() + IdImpl.SEPARATOR + index, root);
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
        sectionBuilder = Builder.fromValues(1, parent.getName() + IdImpl.SEPARATOR + 1, parent);
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
        sectionBuilder = Builder.fromValues(2, parent.getName() + IdImpl.SEPARATOR + 1, parent);
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
        Section section = sectionBuilder.id(IdImpl.fromValues(starter)).name("Section:" + starter).build();
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
