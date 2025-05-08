package com.example.infrastructure.constants;


public final class DataIntegrityConstants {

    private DataIntegrityConstants() {
        // Utility class, not instantiable
    }

    public static final String DUPLICATE_FIELD_MESSAGE = "El valor %s ya se encuentra registrado";
    public static final String DUPLICATE_KEY_CODE = "DUPLICATE_KEY";
    public static final String FIELD_PATTERN = "\\((.*?)\\)=";

}
