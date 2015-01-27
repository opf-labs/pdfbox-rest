/**
 * 
 */
package org.verapdf.pdfa.metadata;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.preflight.PreflightConstants;
import org.verapdf.pdfa.spec.Section.Id;
import org.verapdf.pdfa.spec.SectionImpl.IdImpl;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
public class PdboxErrorMapper {
    private final static Map <String, Id> ID_MAPPER;
    static {
        Id rootId = IdImpl.fromValues(6);
        Id file = IdImpl.fromValues(1, rootId);
        Id fileHeader = IdImpl.fromValues(2, file);
        Id fileTrailer = IdImpl.fromValues(3, file);
        Id crossRef = IdImpl.fromValues(4, file);
        Id fileDid = IdImpl.fromValues(5, file);
        Id stringObjects = IdImpl.fromValues(6, file);
        Id streamObjects = IdImpl.fromValues(7, file);
        Id indirectObjects = IdImpl.fromValues(8, file);
        Id filters = IdImpl.fromValues(10, file);
        Id embeddedFiles = IdImpl.fromValues(11, file);
        Id implementationLimits = IdImpl.fromValues(12, file);
        Id optionalContent = IdImpl.fromValues(13, file);
        Id graphics = IdImpl.fromValues(2, rootId);
        Id ouptutIntent = IdImpl.fromValues(2, graphics);
        Id colourSpaces = IdImpl.fromValues(3, graphics);
        Id iccColourSpace = IdImpl.fromValues(2, colourSpaces);
        Id uncalColourSpace = IdImpl.fromValues(3, colourSpaces);
        Id devicenColourSpace = IdImpl.fromValues(4, colourSpaces);
        Id images = IdImpl.fromValues(4, graphics);
        Id contentStreams = IdImpl.fromValues(10, graphics);
        Id fonts = IdImpl.fromValues(3, rootId);
        Id fontTypes = IdImpl.fromValues(2, fonts);
        Id compositeFonts = IdImpl.fromValues(3, fonts);
        Id compFontsGeneral = IdImpl.fromValues(1, compositeFonts);
        Id CidFonts = IdImpl.fromValues(2, compositeFonts);
        Id CMaps = IdImpl.fromValues(2, compositeFonts);
        Id embeddedFonts = IdImpl.fromValues(4, fonts);
        Id fontSubsets = IdImpl.fromValues(5, fonts);
        Id fontMetrics = IdImpl.fromValues(6, fonts);
        Id fontEncoding = IdImpl.fromValues(7, fonts);
        Id unicocdeMaps = IdImpl.fromValues(8, fonts);
        Id transparency = IdImpl.fromValues(4, rootId);
        Id annotations = IdImpl.fromValues(5, rootId);
        Id annotationTypes = IdImpl.fromValues(2, annotations);
        Id annotationDicts = IdImpl.fromValues(3, annotations);
        Id actions = IdImpl.fromValues(6, rootId);
        Id actionGeneral = IdImpl.fromValues(1, actions);
        Id triggers = IdImpl.fromValues(2, actions);
        Id metadata = IdImpl.fromValues(7, rootId);
        Id mdProperties = IdImpl.fromValues(2, metadata);
        Id mdDid = IdImpl.fromValues(3, metadata);
        Id xmpHeader = IdImpl.fromValues(5, metadata);
        Id extensionSchemas = IdImpl.fromValues(8, metadata);
        Id validation = IdImpl.fromValues(9, metadata);
        Id versionConformance = IdImpl.fromValues(11, metadata);
        Id structure = IdImpl.fromValues(8, rootId);
        Id taggedPdf = IdImpl.fromValues(2, structure);
        Id taggedMid = IdImpl.fromValues(2, taggedPdf);
        Id forms = IdImpl.fromValues(9, rootId);
        Map<String, Id> tempMap = new HashMap<>();
        tempMap.put(PreflightConstants.ERROR_SYNTAX_HEADER, fileHeader);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_TRAILER_ENCRYPT, fileTrailer);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_TRAILER_EOF, fileTrailer);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_TRAILER_MISSING_ID, fileTrailer);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_TRAILER_ID_CONSISTENCY, fileTrailer);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_CROSS_REF, crossRef);
        tempMap.put(PreflightConstants.ERROR_METADATA_MISMATCH, fileDid);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_HEXA_STRING_EVEN_NUMBER, stringObjects);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_HEXA_STRING_INVALID, stringObjects);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_STREAM_LENGTH_INVALID, streamObjects);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_STREAM_DELIMITER, streamObjects);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_STREAM_FX_KEYS, streamObjects);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_OBJ_DELIMITER, indirectObjects);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_STREAM_INVALID_FILTER, filters);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_EMBEDDED_FILES, embeddedFiles);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_ARRAY_TOO_LONG, implementationLimits);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_NAME_TOO_LONG, implementationLimits);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_NUMERIC_RANGE, implementationLimits);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_TOO_MANY_ENTRIES, implementationLimits);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_LITERAL_TOO_LONG, implementationLimits);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_INDIRECT_OBJ_RANGE, implementationLimits);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_TOO_MANY_GRAPHIC_STATES, implementationLimits);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_INVALID_COLOR_SPACE_TOO_MANY_COMPONENTS_DEVICEN, implementationLimits);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_TRAILER_CATALOG_OCPROPERTIES, optionalContent);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_OUTPUT_INTENT_S_VALUE_INVALID, ouptutIntent);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_OUTPUT_INTENT_ICC_PROFILE_INVALID, ouptutIntent);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_OUTPUT_INTENT_ICC_PROFILE_TOO_RECENT, ouptutIntent);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_OUTPUT_INTENT_ICC_PROFILE_MULTIPLE, ouptutIntent);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_INVALID_COLOR_SPACE_RGB, uncalColourSpace);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_INVALID_COLOR_SPACE_CMYK, uncalColourSpace);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_INVALID_COLOR_SPACE_MISSING, uncalColourSpace);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_UNEXPECTED_KEY, images);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_UNEXPECTED_VALUE_FOR_KEY, images);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_CONTENT_STREAM_UNSUPPORTED_OP, contentStreams);
        tempMap.put(PreflightConstants.ERROR_FONTS_DICTIONARY_INVALID, fontTypes);
        tempMap.put(PreflightConstants.ERROR_FONTS_TYPE1_DAMAGED, fontTypes);
        tempMap.put(PreflightConstants.ERROR_FONTS_TRUETYPE_DAMAGED, fontTypes);
        tempMap.put(PreflightConstants.ERROR_FONTS_CID_DAMAGED, fontTypes);
        tempMap.put(PreflightConstants.ERROR_FONTS_GLYPH_MISSING, fontTypes);
        tempMap.put(PreflightConstants.ERROR_FONTS_UNKNOWN_FONT_REF, fontTypes);
        tempMap.put(PreflightConstants.ERROR_FONTS_CIDKEYED_SYSINFO, compFontsGeneral);
        tempMap.put(PreflightConstants.ERROR_FONTS_CIDKEYED_CIDTOGID, CidFonts);
        tempMap.put(PreflightConstants.ERROR_FONTS_ENCODING_ERROR, CMaps);
        tempMap.put(PreflightConstants.ERROR_FONTS_CIDKEYED_CMAP_INVALID_OR_MISSING, CMaps);
        tempMap.put(PreflightConstants.ERROR_FONTS_FONT_FILEX_INVALID, embeddedFonts);
        tempMap.put(PreflightConstants.ERROR_FONTS_CHARSET_MISSING_FOR_SUBSET, fontSubsets);
        tempMap.put(PreflightConstants.ERROR_FONTS_CIDSET_MISSING_FOR_SUBSET, fontSubsets);
        tempMap.put(PreflightConstants.ERROR_FONTS_METRICS, fontMetrics);
        tempMap.put(PreflightConstants.ERROR_FONTS_ENCODING, fontEncoding);
        tempMap.put(PreflightConstants.ERROR_TRANSPARENCY_EXT_GS_SOFT_MASK, transparency);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_TRANSPARENCY_GROUP, transparency);
        tempMap.put(PreflightConstants.ERROR_TRANSPARENCY_EXT_GS_BLEND_MODE, transparency);
        tempMap.put(PreflightConstants.ERROR_TRANSPARENCY_EXT_GS_CA, transparency);
        tempMap.put(PreflightConstants.ERROR_GRAPHIC_TRANSPARENCY_GROUP, transparency);
        tempMap.put(PreflightConstants.ERROR_ANNOT_FORBIDDEN_SUBTYPE, annotationTypes);
        tempMap.put(PreflightConstants.ERROR_ANNOT_INVALID_CA, annotationDicts);
        tempMap.put(PreflightConstants.ERROR_ANNOT_MISSING_FIELDS, annotationDicts);
        tempMap.put(PreflightConstants.ERROR_ANNOT_FORBIDDEN_FLAG, annotationDicts);
        tempMap.put(PreflightConstants.ERROR_ANNOT_FORBIDDEN_COLOR, annotationDicts);
        tempMap.put(PreflightConstants.ERROR_ANNOT_MISSING_AP_N_CONTENT, annotationDicts);
        tempMap.put(PreflightConstants.ERROR_ANNOT_INVALID_AP_CONTENT, annotationDicts);
        tempMap.put(PreflightConstants.ERROR_ACTION_FORBIDDEN_ACTIONS_NAMED, actionGeneral);
        tempMap.put(PreflightConstants.ERROR_ACTION_FORBIDDEN_ACTIONS_EXPLICITLY_FORBIDDEN, actionGeneral);
        tempMap.put(PreflightConstants.ERROR_ACTION_FORBIDDEN_ADDITIONAL_ACTION, triggers);
        tempMap.put(PreflightConstants.ERROR_ACTION_FORBIDDEN_WIDGET_ACTION_FIELD, triggers);
        tempMap.put(PreflightConstants.ERROR_METADATA_FORMAT, mdProperties);
        tempMap.put(PreflightConstants.ERROR_METADATA_PROPERTY_UNKNOWN, mdProperties);
        tempMap.put(PreflightConstants.ERROR_METADATA_MISMATCH, mdDid);
        tempMap.put(PreflightConstants.ERROR_METADATA_XPACKET_DEPRECATED, xmpHeader);
        tempMap.put(PreflightConstants.ERROR_METADATA_ABSENT_DESCRIPTION_SCHEMA, extensionSchemas);
        tempMap.put(PreflightConstants.ERROR_METADATA_WRONG_NS_URI, extensionSchemas);
        tempMap.put(PreflightConstants.ERROR_METADATA_PROPERTY_MISSING, extensionSchemas);
        tempMap.put(PreflightConstants.ERROR_METADATA_UNKNOWN_VALUETYPE, extensionSchemas);
        tempMap.put(PreflightConstants.ERROR_METADATA_ABSENT_DESCRIPTION_SCHEMA, extensionSchemas);
        tempMap.put(PreflightConstants.ERROR_METADATA_MAIN, extensionSchemas);
        tempMap.put(PreflightConstants.ERROR_METADATA_PDFA_ID_MISSING, versionConformance);
        tempMap.put(PreflightConstants.ERROR_METADATA_INVALID_PDFA_CONFORMANCE, versionConformance);
        tempMap.put(PreflightConstants.ERROR_METADATA_INVALID_PDFA_VERSION_ID, versionConformance);
        tempMap.put(PreflightConstants.ERROR_SYNTAX_DICT_INVALID, forms);
        tempMap.put(PreflightConstants.ERROR_ACTION_FORBIDDEN_ADDITIONAL_ACTIONS_FIELD, forms);
        ID_MAPPER = Collections.unmodifiableMap(tempMap);
    }
    
    /**
     * @param preflightCode
     * @return the mapped SectionID
     */
    public static Id getMappedSectionId(final String preflightCode) {
        return ID_MAPPER.get(preflightCode);
    }
    
    private PdboxErrorMapper() {
        throw new AssertionError("Shouldn't be in default constructor"); //$NON-NLS-1$
    }
}
