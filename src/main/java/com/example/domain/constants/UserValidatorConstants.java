package com.example.domain.constants;

import java.util.regex.Pattern;

public final class UserValidatorConstants {

    private UserValidatorConstants() {
        // Utilidad, no instanciable
    }

    /* ─── Expresiones regulares ────────────────────────────────────────────── */
    public static final Pattern DOCUMENT_ID_PATTERN = Pattern.compile("^\\d+$");
    public static final Pattern PHONE_PATTERN       = Pattern.compile("^\\+?\\d{0,12}$");
    public static final Pattern EMAIL_PATTERN       = Pattern.compile("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    /* ─── Otros valores de negocio ─────────────────────────────────────────── */
    public static final int LEGAL_AGE_YEARS = 18;
}
