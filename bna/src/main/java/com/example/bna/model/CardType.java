package com.example.bna.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CardType {
    VISA, MASTERCARD, AMEX, OTHER;

    @JsonCreator
    public static CardType fromValue(String value) {
        if (value == null) return OTHER;
        String v = value.trim().toUpperCase();

        // accept product-like inputs and map to base brand
        if (v.startsWith("VISA")) return VISA;              // VISA_ALTIUS, VISA_GOLD_...
        if (v.startsWith("MASTER")) return MASTERCARD;      // MASTER..., MASTERCARD_CIBT
        if (v.startsWith("MASTERCARD")) return MASTERCARD;
        if (v.startsWith("AMEX") || v.startsWith("AMERICAN")) return AMEX;

        // direct values
        switch (v) {
            case "VISA": return VISA;
            case "MASTERCARD": return MASTERCARD;
            case "AMEX": return AMEX;
            default: return OTHER;
        }
    }
}
