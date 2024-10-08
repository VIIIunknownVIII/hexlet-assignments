package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public static List<String> validate(Object obj) {
        List<String> invalidFields = new ArrayList<>();

        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(NotNull.class)) {
                    Object value = field.get(obj);
                    if (value == null) {
                        invalidFields.add(field.getName());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return invalidFields;
    }

    public static Map<String, List<String>> advancedValidate(Object obj) {
        Map<String, List<String>> errors = new HashMap<>();

        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                List<String> fieldErrors = new ArrayList<>();


                if (field.isAnnotationPresent(NotNull.class)) {
                    Object value = field.get(obj);
                    if (value == null) {
                        fieldErrors.add("can not be null");
                    }
                }

                if (field.isAnnotationPresent(MinLength.class)) {
                    Object value = field.get(obj);
                    if (value != null && value instanceof String) {
                        String stringValue = (String) value;
                        int minLength = field.getAnnotation(MinLength.class).minLength();
                        if (stringValue.length() < minLength) {
                            fieldErrors.add("length less than " + minLength);
                        }
                    }
                }

                if (!fieldErrors.isEmpty()) {
                    errors.put(field.getName(), fieldErrors);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return errors;
    }
}
