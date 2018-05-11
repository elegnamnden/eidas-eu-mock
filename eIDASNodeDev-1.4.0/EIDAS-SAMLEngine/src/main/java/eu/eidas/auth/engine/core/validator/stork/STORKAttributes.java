package eu.eidas.auth.engine.core.validator.stork;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 * User: s228576
 * Date: 4/03/14
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */

@Deprecated
@SuppressWarnings("all")
public interface STORKAttributes {

    String STORK10_NS = "urn:eu:stork:names:tc:STORK:1.0:assertion";
    String STORK10_PREFIX = "stork";
    String STORKP10_NS = "urn:eu:stork:names:tc:STORK:1.0:protocol";
    String STORKP10_PREFIX = "storkp";
    String STORK_ATTRIBUTE_NAME_PREFIX = "http://www.stork.gov.eu/1.0/";
    String STORK_ATTRIBUTE_NAME_SUFFIX_EIDENTIFIER = "eIdentifier";
    String STORK_ATTRIBUTE_EIDENTIFIER = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_EIDENTIFIER;
    String STORK_ATTRIBUTE_NAME_SUFFIX_GIVENNAME = "givenName";
    String STORK_ATTRIBUTE_GIVENNAME = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_GIVENNAME;
    String STORK_ATTRIBUTE_NAME_SUFFIX_SURNAME = "surname";
    String STORK_ATTRIBUTE_SURNAME = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_SURNAME;
    String STORK_ATTRIBUTE_NAME_SUFFIX_INHERITED_FAMILYNAME = "inheritedFamilyName";
    String STORK_ATTRIBUTE_INHERITED_FAMILYNAME = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_INHERITED_FAMILYNAME;
    String STORK_ATTRIBUTE_NAME_SUFFIX_ADOPTED_FAMILYNAME = "adoptedFamilyName";
    String STORK_ATTRIBUTE_ADOPTED_FAMILYNAME = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_ADOPTED_FAMILYNAME;
    String STORK_ATTRIBUTE_NAME_SUFFIX_GENDER = "gender";
    String STORK_ATTRIBUTE_GENDER = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_GENDER;
    String STORK_ATTRIBUTE_NAME_SUFFIX_DATEOFBIRTH = "dateOfBirth";
    String STORK_ATTRIBUTE_DATEOFBIRTH = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_DATEOFBIRTH;
    String STORK_ATTRIBUTE_NAME_SUFFIX_COUNTRYCODEOFBIRTH = "countryCodeOfBirth";
    String STORK_ATTRIBUTE_COUNTRYCODEOFBIRTH = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_COUNTRYCODEOFBIRTH;
    String STORK_ATTRIBUTE_NAME_SUFFIX_NATIONALITYCODE = "nationalityCode";
    String STORK_ATTRIBUTE_NATIONALITYCODE = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_NATIONALITYCODE;
    String STORK_ATTRIBUTE_NAME_SUFFIX_MARTIALSTATUS = "maritalStatus";
    String STORK_ATTRIBUTE_MARTIALSTATUS = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_MARTIALSTATUS;
    String STORK_ATTRIBUTE_NAME_SUFFIX_TEXT_RESIDENCE_ADDRESS = "textResidenceAddress";
    String STORK_ATTRIBUTE_TEXT_RESIDENCE_ADDRESS = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_TEXT_RESIDENCE_ADDRESS;
    String STORK_ATTRIBUTE_NAME_SUFFIX_TEXT_CANONICAL_ADDRESS = "canonicalResidenceAddress";
    String STORK_ATTRIBUTE_TEXT_CANONICAL_ADDRESS = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_TEXT_CANONICAL_ADDRESS;
    String STORK_ATTRIBUTE_NAME_SUFFIX_EMAIL = "eMail";
    String STORK_ATTRIBUTE_EMAIL = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_EMAIL;
    String STORK_ATTRIBUTE_NAME_SUFFIX_TITLE = "title";
    String STORK_ATTRIBUTE_TITLE = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_TITLE;
    String STORK_ATTRIBUTE_NAME_SUFFIX_RESIDENCE_PERMIT = "residencePermit";
    String STORK_ATTRIBUTE_RESIDENCE_PERMIT = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_RESIDENCE_PERMIT;
    String STORK_ATTRIBUTE_NAME_SUFFIX_PSEUDONYM = "pseudonym";
    String STORK_ATTRIBUTE_PSEUDONYM = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_PSEUDONYM;
    String STORK_ATTRIBUTE_NAME_SUFFIX_AGE = "age";
    String STORK_ATTRIBUTE_AGE = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_AGE;
    String STORK_ATTRIBUTE_NAME_SUFFIX_ISAGEOVER = "isAgeOver";
    String STORK_ATTRIBUTE_ISAGEOVER = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_ISAGEOVER;
    String STORK_ATTRIBUTE_NAME_SUFFIX_SIGNEDDOC = "signedDoc";
    String STORK_ATTRIBUTE_SIGNEDDOC = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_SIGNEDDOC;
    String STORK_ATTRIBUTE_NAME_SUFFIX_FISCALNUMBER = "fiscalNumber";
    String STORK_ATTRIBUTE_FISCALNUMBER = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_FISCALNUMBER;
    String STORK_ATTRIBUTE_NAME_SUFFIX_CITIZENQAALEVEL = "citizenQAALevel";
    String STORK_ATTRIBUTE_CITIZENQAALEVEL = STORK_ATTRIBUTE_NAME_PREFIX + STORK_ATTRIBUTE_NAME_SUFFIX_CITIZENQAALEVEL;
    Set<String> ATTRIBUTES_SET = new HashSet<String>(Arrays.asList(new String[]{STORK_ATTRIBUTE_EIDENTIFIER,
            STORK_ATTRIBUTE_GIVENNAME,
            STORK_ATTRIBUTE_SURNAME,
            STORK_ATTRIBUTE_INHERITED_FAMILYNAME,
            STORK_ATTRIBUTE_ADOPTED_FAMILYNAME,
            STORK_ATTRIBUTE_GENDER,
            STORK_ATTRIBUTE_DATEOFBIRTH,
            STORK_ATTRIBUTE_COUNTRYCODEOFBIRTH,
            STORK_ATTRIBUTE_NATIONALITYCODE,
            STORK_ATTRIBUTE_MARTIALSTATUS,
            STORK_ATTRIBUTE_TEXT_RESIDENCE_ADDRESS,
            STORK_ATTRIBUTE_TEXT_CANONICAL_ADDRESS,
            STORK_ATTRIBUTE_EMAIL,
            STORK_ATTRIBUTE_TITLE,
            STORK_ATTRIBUTE_RESIDENCE_PERMIT,
            STORK_ATTRIBUTE_PSEUDONYM,
            STORK_ATTRIBUTE_AGE,
            STORK_ATTRIBUTE_ISAGEOVER,
            STORK_ATTRIBUTE_SIGNEDDOC,
            STORK_ATTRIBUTE_FISCALNUMBER,
            STORK_ATTRIBUTE_CITIZENQAALEVEL}));

    Map<String, String> ATTRIBUTES_SET_NAMES = Collections.unmodifiableMap(
            new HashMap<String, String>() {
                {
                    put(STORK_ATTRIBUTE_EIDENTIFIER, STORK_ATTRIBUTE_NAME_SUFFIX_EIDENTIFIER);
                    put(STORK_ATTRIBUTE_GIVENNAME, STORK_ATTRIBUTE_NAME_SUFFIX_GIVENNAME);
                    put(STORK_ATTRIBUTE_SURNAME, STORK_ATTRIBUTE_NAME_SUFFIX_SURNAME);
                    put(STORK_ATTRIBUTE_INHERITED_FAMILYNAME, STORK_ATTRIBUTE_NAME_SUFFIX_INHERITED_FAMILYNAME);
                    put(STORK_ATTRIBUTE_ADOPTED_FAMILYNAME, STORK_ATTRIBUTE_NAME_SUFFIX_ADOPTED_FAMILYNAME);
                    put(STORK_ATTRIBUTE_GENDER, STORK_ATTRIBUTE_NAME_SUFFIX_GENDER);
                    put(STORK_ATTRIBUTE_DATEOFBIRTH, STORK_ATTRIBUTE_NAME_SUFFIX_DATEOFBIRTH);
                    put(STORK_ATTRIBUTE_COUNTRYCODEOFBIRTH, STORK_ATTRIBUTE_NAME_SUFFIX_COUNTRYCODEOFBIRTH);
                    put(STORK_ATTRIBUTE_NATIONALITYCODE, STORK_ATTRIBUTE_NAME_SUFFIX_NATIONALITYCODE);
                    put(STORK_ATTRIBUTE_MARTIALSTATUS, STORK_ATTRIBUTE_NAME_SUFFIX_MARTIALSTATUS);
                    put(STORK_ATTRIBUTE_TEXT_RESIDENCE_ADDRESS, STORK_ATTRIBUTE_NAME_SUFFIX_TEXT_RESIDENCE_ADDRESS);
                    put(STORK_ATTRIBUTE_TEXT_CANONICAL_ADDRESS, STORK_ATTRIBUTE_NAME_SUFFIX_TEXT_CANONICAL_ADDRESS);
                    put(STORK_ATTRIBUTE_EMAIL, STORK_ATTRIBUTE_NAME_SUFFIX_EMAIL);
                    put(STORK_ATTRIBUTE_TITLE, STORK_ATTRIBUTE_NAME_SUFFIX_TITLE);
                    put(STORK_ATTRIBUTE_RESIDENCE_PERMIT, STORK_ATTRIBUTE_NAME_SUFFIX_RESIDENCE_PERMIT);
                    put(STORK_ATTRIBUTE_PSEUDONYM, STORK_ATTRIBUTE_NAME_SUFFIX_PSEUDONYM);
                    put(STORK_ATTRIBUTE_AGE, STORK_ATTRIBUTE_NAME_SUFFIX_AGE);
                    put(STORK_ATTRIBUTE_ISAGEOVER, STORK_ATTRIBUTE_NAME_SUFFIX_ISAGEOVER);
                    put(STORK_ATTRIBUTE_SIGNEDDOC, STORK_ATTRIBUTE_NAME_SUFFIX_SIGNEDDOC);
                    put(STORK_ATTRIBUTE_FISCALNUMBER, STORK_ATTRIBUTE_NAME_SUFFIX_FISCALNUMBER);
                    put(STORK_ATTRIBUTE_CITIZENQAALEVEL, STORK_ATTRIBUTE_NAME_SUFFIX_CITIZENQAALEVEL);
                }
            }
    );

}
