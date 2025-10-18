package io.github.basusayantan.config.internal;

public final class Constants {

    // Config source names
    public static final String LOCAL = "LOCAL";
    public static final String ENVIRONMENT = "ENVIRONMENT";
    public static final String DATABASE = "DATABASE";
    public static final String OVERRIDE_FILE = "OVERRIDE_FILE";
    public static final String DEFAULT_FILE = "DEFAULT_FILE";

    public static final String ALL_STRING = "ALL";

    public static final String CONFIG_DB_CONN_POOL = "CONN_POOL";

    private Constants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
