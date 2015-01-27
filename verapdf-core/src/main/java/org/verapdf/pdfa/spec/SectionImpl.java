/**
 * 
 */
package org.verapdf.pdfa.spec;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.base.Preconditions;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
public final class SectionImpl implements Section {
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
	public boolean isParentOf(final Section isChild) {
		return this.getId().isParentOf(isChild.getId());
	}

	/**
	 * { @inheritDoc }
	 */
	@Override
	public boolean isAncestorOf(final Section isDescendant) {
		return this.getId().isAncestorOf(isDescendant.getId());
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
	public boolean addSubSection(final Section subSection) {
		return this.subSections.add(subSection);
	}

	/**
	 * { @inheritDoc }
	 */
	@Override
	public Section getSubSection(final Id toFind) {
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
	public final boolean equals(final Object obj) {
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
	public int compareTo(final Section o) {
		if (o == null) {
			throw new NullPointerException("Passed Section parm o == null"); //$NON-NLS-1$
		}
		return this.getId().compareTo(o.getId());
	}

	private Section getSubSectionById(final Id toFind) {
		if (this.getId().equals(toFind)) {
			return this;
		}
		if (!this.getId().isAncestorOf(toFind)) {
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
		/** String separator for Section and Sub-Section */
		public final static char DEFAULT_SEPARATOR = '.';
		final static int MINIMUM_VALUE = 1;
		final static IdImpl DEFAULT_ID = new IdImpl();
		private final int[] idStack;
		private final String value;

		private IdImpl() {
			this(new int[0]);
		}

		private IdImpl(final int ordinal) {
			this(new int[] { ordinal });
		}

		private IdImpl(final int[] idStack) {
			this.idStack = idStack.clone();
			this.value = joinArray(idStack, DEFAULT_SEPARATOR);
		}

		@Override
		public int[] getIntParts() {
			if (this.idStack == null) return null;
			return this.idStack.clone();
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
		public boolean isRoot() {
			return this.getIntParts().length == 0;
		}

		/**
		 * { @inheritDoc }
		 */
		@Override
		public boolean isParentOf(final Id isChild) {
			return this.isDescendantOfThis(isChild, false);
		}

		/**
		 * { @inheritDoc }
		 */
		@Override
		public boolean isAncestorOf(final Id isDescendant) {
			return this.isDescendantOfThis(isDescendant, true);
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
		public int compareTo(final Id o) {
			if (o == null) {
				throw new NullPointerException("Passed Section parm o == null"); //$NON-NLS-1$
			}
			int minLength = (o.getIntParts().length > this.getIntParts().length) ? this.getIntParts().length : o.getIntParts().length;
			for (int index = 0; index < minLength; index++) {
				if (this.getIntParts()[index] != o.getIntParts()[index]) {
					return this.getIntParts()[index] - o.getIntParts()[index];
				}
			}
			return (this.getIntParts().length - o.getIntParts().length);
		} 

		/**
		 * { @inheritDoc }
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(this.idStack);
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
			if (!(obj instanceof Id)) {
				return false;
			}
			Id other = (Id) obj;
			if (!Arrays.equals(this.getIntParts(), other.getIntParts())) {
				return false;
			}
			return true;
		}

		/**
		 * Utility method similar to PHP's join. It takes an array of ints and a
		 * "glue" character. It returns a string made of the string ints joined
		 * by the glue character. Example: array = {0, 1, 2, 3} and glue = '.'
		 * would return "0.1.2.3"
		 * 
		 * @param array
		 *            the array to join
		 * @param glue
		 *            the "glue" character used to join the array elements
		 * @return a string made of the array elements joined by the glue
		 *         character
		 */
		public static String joinArray(final int[] array, final char glue) {
			Preconditions.checkNotNull(array);
			if (array.length < 1) {
				return ""; //$NON-NLS-1$
			}
			StringBuilder builder = new StringBuilder(String.valueOf(array[0]));
			for (int index = 1; index < array.length; index++) {
				builder.append(glue);
				builder.append(String.valueOf(array[index]));
			}
			return builder.toString();
		}

		static IdImpl defaultInstance() {
			return DEFAULT_ID;
		}

		/**
		 * Creates a top level section id from a single integer
		 * 
		 * @param ordinal
		 *            the ordinal for the id.
		 * @return an id created from the passed integer value
		 */
		public static IdImpl fromValues(final int ordinal) {
			Preconditions.checkArgument(ordinal >= MINIMUM_VALUE);
			return new IdImpl(ordinal);
		}

		/**
		 * @param ordinal the ordered number of this part in the parentIds children
		 * @param parentId the Id that you wish to create a new child of
		 * @return a new ID created as a child of the parent with a new ordinal.
		 */
		public static IdImpl fromValues(final int ordinal, final Id parentId) {
			Preconditions.checkArgument(ordinal >= MINIMUM_VALUE);
			Preconditions.checkNotNull(parentId);
			int[] idStack = Arrays.copyOf(parentId.getIntParts(),
					parentId.getIntParts().length + 1);
			idStack[idStack.length - 1] = ordinal;
			return new IdImpl(idStack);
		}

		private boolean isDescendantOfThis(final Id toTest, final boolean recurse) {
			if (toTest.getIntParts().length > this.getIntParts().length) {
				for (int index = 0; index < this.getIntParts().length; index++) {
					if (this.getIntParts()[index] != toTest.getIntParts()[index]) {
						return false;
					}
				}
				return (recurse) ? true : (toTest.getIntParts().length - this.getIntParts().length) == 1;
			}
			return false;
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
			this(section.getId(), section.getName(), section.getParent(),
					section.getSubSections());
		}

		private Builder(final Id id, final String name, final Section parent,
				final Set<Section> subSections) {
			this.id = id;
			this.name = name;
			this.parent = parent;
			this.subSections = new TreeSet<>(subSections);
		}

		private Builder(final int ordinal, final String name,
				final Section parent) {
			this.id = IdImpl.fromValues(ordinal, parent.getId());
			this.name = name;
		}

		/**
		 * @param id
		 *            new value for the String id
		 * @return the Builder instance
		 */
		public Builder id(final Id id) {
			Preconditions.checkNotNull(id);
			this.id = id;
			return this;
		}

		/**
		 * @param name
		 *            the name of the section
		 * @return the Builder instance
		 */
		public Builder name(final String name) {
			Preconditions.checkNotNull(name);
			this.name = name;
			return this;
		}

		/**
		 * @param parent
		 *            the parent of the section
		 * @return the Builder instance
		 */
		public Builder parent(final Section parent) {
			Preconditions.checkNotNull(parent);
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
			Preconditions.checkNotNull(section);
			return new Builder(section);
		}

		static Builder fromValues(final int ordinal, final String name,
				final Section parent) {
			Preconditions.checkArgument(ordinal > -1);
			Preconditions.checkNotNull(name);
			return new Builder(ordinal, name, parent).parent(parent);
		}
	}
}
