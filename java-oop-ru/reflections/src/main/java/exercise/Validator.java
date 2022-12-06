package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        List<String> notValidFields = new ArrayList<>();

        for (Field field : fields) {
            String fieldArgument = null;
            String fieldName = null;
            try {
                field.setAccessible(true);
                fieldArgument = (String) field.get(address);
                fieldName = field.toString();
                fieldName = fieldName.substring(fieldName.lastIndexOf(".") + 1);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (isNullWithNotNull(field, fieldArgument)) {
                notValidFields.add(fieldName);
            }
        }

        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidator(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        Map<String, List<String>> notValidFields = new LinkedHashMap<>();

        for (Field field : fields) {
            String fieldArgument = null;
            String fieldName = null;
            try {
                field.setAccessible(true);
                fieldArgument = (String) field.get(address);
                fieldName = field.toString();
                fieldName = fieldName.substring(fieldName.lastIndexOf(".") + 1);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (isNullWithNotNull(field, fieldArgument)) {
                notValidFields.put(fieldName, List.of("can not be null"));

            } else if (isBelowMinLengthAnnotation(field, fieldArgument)) {
                notValidFields.put(fieldName, List.of("length less than " + getMinLength(field)));
            }
        }
        return notValidFields;
    }

    private static boolean isNullWithNotNull(Field field, String fieldArgument) {
        NotNull notNull = field.getAnnotation(NotNull.class);
        return notNull != null && fieldArgument == null;
    }

    private static boolean isBelowMinLengthAnnotation(Field field, String fieldArgument) {
        MinLength minLength = field.getAnnotation(MinLength.class);
        return minLength != null && minLength.minLength() > fieldArgument.length();
    }
    private static String getMinLength(Field field) {
        MinLength minLength = field.getAnnotation(MinLength.class);
        return String.valueOf(minLength.minLength());
    }
}
// END
