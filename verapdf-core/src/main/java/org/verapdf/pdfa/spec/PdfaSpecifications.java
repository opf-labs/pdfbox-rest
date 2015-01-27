/**
 * 
 */
package org.verapdf.pdfa.spec;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.verapdf.pdfa.spec.Section.Id;
import org.verapdf.pdfa.spec.SectionImpl.Builder;
import org.verapdf.pdfa.spec.SectionImpl.IdImpl;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
public final class PdfaSpecifications {
	final static String NONE = "none";
    final static String PDFA_STRING_PREFIX = "PDF/A-"; //$NON-NLS-1$
    final static String ISO = "ISO"; //$NON-NLS-1$
    final static String ISO_PREFIX = ISO + " "; //$NON-NLS-1$
    final static int NONE_ID = 0;
    final static int ISO_19005_ID = 19005;
    final static String ISO_19005_DESCRIPTION = "Document management -- Electronic document file format for long-term preservation"; //$NON-NLS-1$
    final static int ISO_32000_ID = 32000;
    final static String ISO_32000_DESCRIPTION = "Document management -- Portable document format"; //$NON-NLS-1$
    final static int ISO_19005_1_PART = 1;
    final static String ISO_19005_1_YEAR = "2005"; //$NON-NLS-1$
    final static String ISO_19005_1_DESCRIPTION = "Use of PDF 1.4"; //$NON-NLS-1$
    final static int ISO_19005_2_PART = 2;
    final static String ISO_19005_2_YEAR = "2011"; //$NON-NLS-1$
    final static String ISO_19005_2_DESCRIPTION = "Use of ISO 32000-1"; //$NON-NLS-1$
    final static int ISO_19005_3_PART = 3;
    final static String ISO_19005_3_YEAR = "2012"; //$NON-NLS-1$
    final static String ISO_19005_3_DESCRIPTION = "Use of ISO 32000-1 with support for embedded files"; //$NON-NLS-1$
    final static String ISO_REFERENCE_SUFFIX = "(E)"; //$NON-NLS-1$
    final static String LEVEL_PREFIX = "Level "; //$NON-NLS-1$
    final static String LEVEL_A_CODE = "a "; //$NON-NLS-1$
    final static String LEVEL_B_CODE = "b "; //$NON-NLS-1$
    final static String LEVEL_U_CODE = "u"; //$NON-NLS-1$

    final static String ACTIONS = "Actions"; //$NON-NLS-1$
    final static String ALTERNATE_DESCRIPTIONS = "Alternate descriptions"; //$NON-NLS-1$
    final static String ANNOTATIONS = "Annotation"; //$NON-NLS-1$
    final static String ANNOTATION_DICTIONARIES = "Annotation dictionaries"; //$NON-NLS-1$
    final static String ANNOTATION_TYPES = "Annotation types"; //$NON-NLS-1$
    final static String ARTIFACTS = "Artifacts"; //$NON-NLS-1$
    final static String CMAPS = "CMaps"; //$NON-NLS-1$
    final static String CHARACTER_ENCODING = "Character encodings"; //$NON-NLS-1$
    final static String CID_FONTS = "CIDFonts"; //$NON-NLS-1$
    final static String COLOUR_SPACES = "Content streams"; //$NON-NLS-1$
    final static String COMPOSITE_FONTS = "Composite fonts"; //$NON-NLS-1$
    final static String CONTENT_STREAMS = "Colour spaces"; //$NON-NLS-1$
    final static String CROSS_REF_TABLE = "Cross reference table"; //$NON-NLS-1$
    final static String DID = "Document information dictionary"; //$NON-NLS-1$
    final static String EMBEDDED_FILES = "Embedded files"; //$NON-NLS-1$
    final static String EMBEDDED_FONTS = "Embedded fonmt programs"; //$NON-NLS-1$
    final static String EXPANSIONS = "Expansions of abbreviations and acronyms"; //$NON-NLS-1$
    final static String EXTENDED_GRAPHICS = "Extended graphics state"; //$NON-NLS-1$
    final static String EXTENSION_SCHEMAS = "Extension schemas"; //$NON-NLS-1$
    final static String FILE = "File Structure"; //$NON-NLS-1$
    final static String FILE_HEADER = "File header"; //$NON-NLS-1$
    final static String FILE_IDENTIFIERS = "File identifiers"; //$NON-NLS-1$
    final static String FILE_PROVENANCE = "File provenance information"; //$NON-NLS-1$
    final static String FILE_TRAILER = "File trailer"; //$NON-NLS-1$
    final static String FILTERS = "Filters"; //$NON-NLS-1$
    final static String FONTS = "Fonts"; //$NON-NLS-1$
    final static String FONT_METRICS = "Font metrics"; //$NON-NLS-1$
    final static String FONT_METADATA = "Font metadata"; //$NON-NLS-1$
    final static String FONT_SUBSETS = "Font subsets"; //$NON-NLS-1$
    final static String FONT_TYPES = "Font typess"; //$NON-NLS-1$
    final static String FORMS = "Interactive forms"; //$NON-NLS-1$
    final static String FORM_XOBJECTS = "Forms XObjects"; //$NON-NLS-1$
    final static String GENERAL = "General"; //$NON-NLS-1$
    final static String GRAPHICS = "Graphics"; //$NON-NLS-1$
    final static String HYPERTEXT_LINKS = "Hypertext links"; //$NON-NLS-1$
    final static String ICC_COLOUR_SPACES = "ICCBased colour spaces"; //$NON-NLS-1$
    final static String IMAGES = "Images"; //$NON-NLS-1$
    final static String IMPLEMENTATION_LIMITS = "Implementation limits"; //$NON-NLS-1$
    final static String INDIRECT_OBJECTS = "Indirect objects"; //$NON-NLS-1$
    final static String LINEARIZED = "Linearized PDF"; //$NON-NLS-1$
    final static String METADATA = "Metadata"; //$NON-NLS-1$
    final static String MID = "Mark information dictionary"; //$NON-NLS-1$
    final static String NATURAL_LANG = "Natural language specification"; //$NON-NLS-1$
    final static String NONTEXT_ANNOTATIONS = "Non-textual annotations"; //$NON-NLS-1$
    final static String NORMALIZATION = "Normalization"; //$NON-NLS-1$
    final static String OPTIONAL_CONTENT = "Optional content"; //$NON-NLS-1$
    final static String OUTPUT_INTENT = "Output intent"; //$NON-NLS-1$
    final static String POSTSCRIPT_XOBJECTS = "PostScript XObjects"; //$NON-NLS-1$
    final static String PROPERTIES = "Properties"; //$NON-NLS-1$
    final static String REFERENCE_XOBJECTS = "Reference XObjects"; //$NON-NLS-1$
    final static String STRING_OBJECTS = "String objects"; //$NON-NLS-1$
    final static String REDERING_INTENTS = "Rendering intents"; //$NON-NLS-1$
    final static String REPLACEMENT_TEXT = "Replacement text"; //$NON-NLS-1$
    final static String REQUIREMENTS = "Technical Requirements"; //$NON-NLS-1$
    final static String SEPARATION_COLOUR_SPACES = "Separation and DeviceN colour spaces"; //$NON-NLS-1$
    final static String STREAM_OBJECTS = "Stream objects"; //$NON-NLS-1$
    final static String STRUCUTRE = "Logical Structure"; //$NON-NLS-1$
    final static String STRUCUTRE_HIERARCHY = "Structure hierarchy"; //$NON-NLS-1$
    final static String STRUCUTRE_TYPES = "Structure types"; //$NON-NLS-1$
    final static String TAGGED_PDF = "Tagged PDF"; //$NON-NLS-1$
    final static String TRANSPARENCY = "Transparency"; //$NON-NLS-1$
    final static String TRIGGER_EVENTS = "Triogger events"; //$NON-NLS-1$
    final static String UNICODE_MAPS = "Unicode character maps"; //$NON-NLS-1$
    final static String UNCALIBRATED_COLOUR_SPACES = "Uncalibrated colour spaces"; //$NON-NLS-1$
    final static String VALIDATION = "Validation"; //$NON-NLS-1$
    final static String VERSION_CONFORMANCE = "Version and conformance level identification"; //$NON-NLS-1$
    final static String WORD_BREAKS = "Word breaks"; //$NON-NLS-1$
    final static String XMP_HEADER = "XMP header"; //$NON-NLS-1$

    private final static Section PART_1_SECTION;
    static {
        List<String> level1names = Arrays.asList(new String[] { FILE, GRAPHICS,
                FONTS, TRANSPARENCY, ANNOTATIONS, ACTIONS, METADATA, STRUCUTRE,
                FORMS });
        List<String> fileNames = Arrays.asList(new String[] { GENERAL,
                FILE_HEADER, FILE_TRAILER, CROSS_REF_TABLE, DID,
                STRING_OBJECTS, STREAM_OBJECTS, INDIRECT_OBJECTS, LINEARIZED,
                FILTERS, EMBEDDED_FILES, IMPLEMENTATION_LIMITS,
                OPTIONAL_CONTENT });
        List<String> graphicsNames = Arrays.asList(new String[] { GENERAL,
                OUTPUT_INTENT, COLOUR_SPACES, IMAGES, FORM_XOBJECTS,
                REFERENCE_XOBJECTS, POSTSCRIPT_XOBJECTS, EXTENDED_GRAPHICS, REDERING_INTENTS,
                CONTENT_STREAMS, EMBEDDED_FILES, IMPLEMENTATION_LIMITS,
                OPTIONAL_CONTENT });
        List<String> colourSpaceNames = Arrays.asList(new String[] { GENERAL,
                ICC_COLOUR_SPACES, UNCALIBRATED_COLOUR_SPACES, SEPARATION_COLOUR_SPACES });
        List<String> fontsNames = Arrays.asList(new String[] { GENERAL,
                FONT_TYPES, COMPOSITE_FONTS, EMBEDDED_FONTS, FONT_SUBSETS,
                FONT_METRICS, CHARACTER_ENCODING, UNICODE_MAPS });
        List<String> compositeFontNames = Arrays.asList(new String[] { GENERAL,
                CID_FONTS, CMAPS });
        List<String> annotationNames = Arrays.asList(new String[] { GENERAL,
                ANNOTATION_TYPES, ANNOTATION_DICTIONARIES });
        List<String> actionNames = Arrays.asList(new String[] { GENERAL,
                TRIGGER_EVENTS, HYPERTEXT_LINKS, CROSS_REF_TABLE, DID,
                STRING_OBJECTS, STREAM_OBJECTS, INDIRECT_OBJECTS, LINEARIZED,
                FILTERS, EMBEDDED_FILES, IMPLEMENTATION_LIMITS,
                OPTIONAL_CONTENT });
        List<String> metadataNames = Arrays.asList(new String[] { GENERAL,
                PROPERTIES, DID, NORMALIZATION, XMP_HEADER,
                FILE_IDENTIFIERS, FILE_PROVENANCE, EXTENSION_SCHEMAS, VALIDATION,
                FONT_METADATA, VERSION_CONFORMANCE });
        List<String> structureNames = Arrays.asList(new String[] { GENERAL,
                TAGGED_PDF, ARTIFACTS, NATURAL_LANG, ALTERNATE_DESCRIPTIONS, NONTEXT_ANNOTATIONS,
                REPLACEMENT_TEXT, EXPANSIONS });
        List<String> taggedNames = Arrays.asList(new String[] { GENERAL, MID });
        List<String> artifactNames = Arrays.asList(new String[] { GENERAL,
                WORD_BREAKS, STRUCUTRE_HIERARCHY, STRUCUTRE_TYPES });

        Id root = IdImpl.fromValues(6);
        Builder builder = Builder.defaultInstance().id(root).name(REQUIREMENTS);
        PART_1_SECTION = builder.build();
        createChildren(PART_1_SECTION, level1names);
        for (Section parent : PART_1_SECTION.getSubSections()) {
            if (parent.getId().equals(IdImpl.fromValues(1, PART_1_SECTION.getId()))) {
                createChildren(parent, fileNames);
            } else if (parent.getId().equals(IdImpl.fromValues(2, PART_1_SECTION.getId()))) {
                createChildren(parent, graphicsNames);
                for (Section graphicsParent : parent.getSubSections()) {
                    if (graphicsParent.getId().equals(IdImpl.fromValues(3, parent.getId()))) {
                        createChildren(parent, colourSpaceNames);
                    }
                }
            } else if (parent.getId().equals(IdImpl.fromValues(3, PART_1_SECTION.getId()))) {
                createChildren(parent, fontsNames);
                for (Section fontsParent : parent.getSubSections()) {
                    if (fontsParent.getId().equals(IdImpl.fromValues(3, parent.getId()))) {
                        createChildren(parent, compositeFontNames);
                    }
                }
            } else if (parent.getId().equals(IdImpl.fromValues(5, PART_1_SECTION.getId()))) {
                createChildren(parent, annotationNames);
            } else if (parent.getId().equals(IdImpl.fromValues(6, PART_1_SECTION.getId()))) {
                createChildren(parent, actionNames);
            } else if (parent.getId().equals(IdImpl.fromValues(7, PART_1_SECTION.getId()))) {
                createChildren(parent, metadataNames);
            } else if (parent.getId().equals(IdImpl.fromValues(8, PART_1_SECTION.getId()))) {
                createChildren(parent, structureNames);
                for (Section structureParent : parent.getSubSections()) {
                    if (structureParent.getId().equals(IdImpl.fromValues(2, parent.getId()))) {
                        createChildren(parent, taggedNames);
                    } else if (structureParent.getId().equals(IdImpl.fromValues(1, parent.getId()))) {
                        createChildren(parent, artifactNames);
                    }
                }
            } 
        }
    }
    private final static Map<String, PdfaFlavour> FLAVOUR_LOOKUP;
    static {
    	FLAVOUR_LOOKUP = new HashMap<>();
    	FLAVOUR_LOOKUP.put(NONE, PdfaFlavour.NONE);
    	FLAVOUR_LOOKUP.put(ISO_19005_1_PART + LEVEL_A_CODE, PdfaFlavour.PDFA_1_A);
    	FLAVOUR_LOOKUP.put(ISO_19005_1_PART + LEVEL_B_CODE, PdfaFlavour.PDFA_1_B);
    	FLAVOUR_LOOKUP.put(ISO_19005_2_PART + LEVEL_A_CODE, PdfaFlavour.PDFA_2_A);
    	FLAVOUR_LOOKUP.put(ISO_19005_2_PART + LEVEL_B_CODE, PdfaFlavour.PDFA_2_B);
    	FLAVOUR_LOOKUP.put(ISO_19005_3_PART + LEVEL_A_CODE, PdfaFlavour.PDFA_3_A);
    	FLAVOUR_LOOKUP.put(ISO_19005_3_PART + LEVEL_B_CODE, PdfaFlavour.PDFA_3_B);
    	FLAVOUR_LOOKUP.put(ISO_19005_3_PART + LEVEL_U_CODE, PdfaFlavour.PDFA_3_U);
    }
    private final static Map<PdfaFlavour, PdfaSpecification> SPEC_LOOKUP;
    static {
        SPEC_LOOKUP = new HashMap<>();
        SPEC_LOOKUP.put(PdfaFlavour.PDFA_1_B, new PdfaSpecificationImpl(PdfaFlavour.PDFA_1_B, PART_1_SECTION));
    }

    private PdfaSpecifications() {
        throw new AssertionError(
                "Should never enter utility class constructor."); //$NON-NLS-1$
    }

    private static void createChildren(final Section parent,
            final List<String> childNames) {
        for (int index = 0; index < childNames.size(); index++) {
            Builder.fromValues(index + 1, childNames.get(index), parent)
                    .build();
        }
    }
    
    /**
     * @param flavour
     * @return the full specifcation object for the flavor
     */
    public static PdfaSpecification byFlavour(PdfaFlavour flavour) {
        return SPEC_LOOKUP.get(flavour);
    }
    
    public static PdfaFlavour byFlavourString(String flavourString) {
    	return FLAVOUR_LOOKUP.get(flavourString);
    }
}
