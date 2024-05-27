package org.example;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    public Address(int streetNo, String street, String city, String province, String postalCode, String country) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase(); // Convert to uppercase
            this.country = country;
        } else {
            this.streetNo = 0;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
            this.country = null;
        }
    }

    public static boolean isPostalCodeValid(String postalCode) {
        // Check if postal code is null or empty
        if (postalCode == null || postalCode.isEmpty()) {
            return false;
        }

        // Remove white spaces manually
        StringBuilder noSpacesPostalCode = new StringBuilder();
        for (int i = 0; i < postalCode.length(); i++) {
            char c = postalCode.charAt(i);
            if (!Character.isWhitespace(c)) {
                noSpacesPostalCode.append(c);
            }
        }
        postalCode = noSpacesPostalCode.toString();

        // Check length
        if (postalCode.length() != 6) {
            return false;
        }

        // Check format manually
        for (int i = 0; i < postalCode.length(); i++) {
            char c = postalCode.charAt(i);
            if (i % 2 == 0) {
                // Characters should be letters
                if (!Character.isLetter(c)) {
                    return false;
                }
            } else {
                // Characters should be digits
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }
}
