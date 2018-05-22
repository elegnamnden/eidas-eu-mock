package se.swedenconnect.eidas.test.cef20demohub.data.user;

import lombok.Getter;

@Getter
public enum TestPersonNatural {

    xavi("javier", "Garcia", "1234567890", "1964-12-31", "Male", "Old Town", "Garcia", TestAddress.address02),
    fridaKranstege("Frida", "Kranstege", "197705232382", "1977-05-23", "Female", null, null, null);


    private String givenName;
    private String surname;
    private String personIdentifier;
    private String dateOfBirth;
    private String gender;
    private String placeOfBirth;
    private String birthName;
    private TestAddress address;

    TestPersonNatural(String givenName, String surname, String personIdentifier, String dateOfBirth, String gender, String placeOfBirth, String birthName, TestAddress address) {
        this.givenName = givenName;
        this.surname = surname;
        this.personIdentifier = personIdentifier;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.placeOfBirth = placeOfBirth;
        this.birthName = birthName;
        this.address = address;
    }
}
