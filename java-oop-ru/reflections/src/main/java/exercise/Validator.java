package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    // Метод для валидации только по аннотации @NotNull
    public static List<String> validate(Object object) {
        List<String> invalidFields = new ArrayList<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true); // Позволяет доступ к приватным полям
                try {
                    Object value = field.get(object);
                    if (value == null) {
                        invalidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return invalidFields;
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        Map<String, List<String>> validationResult = new HashMap<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            List<String> errors = new ArrayList<>();
            field.setAccessible(true);

            try {
                Object value = field.get(object);

                // Проверка @NotNull
                if (field.isAnnotationPresent(NotNull.class) && value == null) {
                    errors.add("can not be null");
                }

                // Проверка @MinLength
                if (field.isAnnotationPresent(MinLength.class) && value != null) {
                    MinLength minLength = field.getAnnotation(MinLength.class);
                    if (value instanceof String && ((String) value).length() < minLength.minLength()) {
                        errors.add("length less than " + minLength.minLength());
                    }
                }

                if (!errors
