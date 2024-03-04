package ua.vholovetskyi.amazonsalesstatistics.utils;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-02-28
 */
public class FieldPatterns {

    private FieldPatterns() {
        throw new UnsupportedOperationException("It is not allowed to create an object!!!");
    }

    public static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    public static final String EMAIL_PATTERN = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";



}
