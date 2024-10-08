package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static List<String> validate(Object obj) {
        List<String> invalidFields = new ArrayList<>();
        Class<?> objClass = obj.getClass();

        // Проходим по всем полям класса
        for (Field field : objClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true); // Делаем приватные поля доступными
                try {
                    Object value = field.get(obj);
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
}
