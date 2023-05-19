package Presentation;



import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * ca sa extrag campurile pentru capul de tabel la fiecare interfata (client/product/orders)
 */
public class ExtrageFields {
    public static List<String> extractFields=new ArrayList<>();

    public static void retrieveProperties(Object object) {

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                extractFields.add(field.getName().toUpperCase());


            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
