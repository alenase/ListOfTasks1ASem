package task2annotation.annotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class Factory {

    private static final Properties props = new Properties();
    private static final String FILE_NAME = "annotation.properties";

    private Factory() {
    }

    public static Factory getInstance() throws IOException {
        return createContextInitializer();
    }

    private static Factory createContextInitializer() throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL fileUrl = loader.getResource(FILE_NAME);

        if (Objects.isNull(fileUrl)) {
            throw new NoSuchFileException(FILE_NAME);
        }

        BufferedReader reader = Files.newBufferedReader(Paths.get(fileUrl.getPath().replaceFirst("/", "")));
        props.load(reader);

        return new Factory();
    }

    public ItemName createFieldName() {
        ItemName itemName = new ItemName();

        Field[] fields = itemName.getClass().getDeclaredFields();

        for (Field f: fields) {
            if (f.isAnnotationPresent(Injection.class)) {
                setValue(f, itemName);
            }
        }

        return itemName;
    }

    public ItemPrice createFieldPrice() {
        ItemPrice itemPrice = new ItemPrice();
        Field[] fields = itemPrice.getClass().getDeclaredFields();

        for (Field f: fields) {
            if (f.isAnnotationPresent(Injection.class)) {
                setValue(f, itemPrice);
            }
        }
        return itemPrice;
    }


    public ItemQuantity createFieldQuantity() {
        ItemQuantity itemQuantity = new ItemQuantity();
        Field[] fields = itemQuantity.getClass().getDeclaredFields();

        for (Field f: fields) {
            if (f.isAnnotationPresent(Injection.class)) {
                setValue(f, itemQuantity);
            }
        }
        return itemQuantity;
    }


    private void setValue(Field field, Object item) {
        Injection injector = field.getAnnotation(Injection.class);

        field.setAccessible(true);

        String value = props.getProperty(injector.stringName());
        Class<?> type = field.getType();
        try {
            if (String.class.isAssignableFrom(type)) {
                field.set(item, value);
            } else if (int.class.isAssignableFrom(type)) {
                field.set(item, Integer.parseInt(value));
            } else if (double.class.isAssignableFrom(type)){
                field.set(item, Double.parseDouble(value));
            } else {
                System.out.println("Can not find assignable type for: " + field.toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(false);
        }
    }
}
