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
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource("");
////        if (Objects.isNull(fileUrl)) {
////            throw new NoSuchFileException(FILE_NAME);
////        }
//        System.out.println(resourceUrl.getPath() + FILE_NAME);
//        String defaultConfigPath = resourceUrl.getPath() + FILE_NAME;
//        props.load(new FileInputStream(defaultConfigPath));
//        URL fileUrl = loader.getResource(FILE_NAME);
//        System.out.println(loader.getResource(FILE_NAME));
//
//        if (Objects.isNull(fileUrl)) {
//            throw new NoSuchFileException(FILE_NAME);
//        }
//
//
//        BufferedReader reader = Files.newBufferedReader(Paths.get(fileUrl.getPath()));
//
//        props.load(reader);
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL fileUrl = loader.getResource(FILE_NAME);
        System.out.println(fileUrl );

        if (Objects.isNull(fileUrl)) {
            throw new NoSuchFileException(FILE_NAME);
        }

        BufferedReader reader = Files.newBufferedReader(Paths.get(fileUrl.getPath().replaceFirst("/", "")));
        //System.out.println(reader);
        props.load(reader);

        return new Factory();
    }

    public ToyName createFieldName() {
        ToyName toyName = new ToyName();

        Field[] fields = toyName.getClass().getDeclaredFields();

        for (Field f: fields) {
            if (f.isAnnotationPresent(Injection.class)) {
                setValue(f, toyName);
            }
        }

        return toyName;
    }

    public ToyPrice createFieldPrice() {
        ToyPrice toyPrice = new ToyPrice();
        Field[] fields = toyPrice.getClass().getDeclaredFields();

        for (Field f: fields) {
            if (f.isAnnotationPresent(Injection.class)) {
                setValue(f, toyPrice);
            }
        }
        return toyPrice;
    }


    public ToyQuantity createFieldQuantity() {
        ToyQuantity toyQuantity = new ToyQuantity();
        Field[] fields = toyQuantity.getClass().getDeclaredFields();

        for (Field f: fields) {
            if (f.isAnnotationPresent(Injection.class)) {
                setValue(f, toyQuantity);
            }
        }
        return toyQuantity;
    }

    public ToySerialNumber createFieldSerialNumber() {
        ToySerialNumber toySerialNumber = new ToySerialNumber();
        Field[] fields = toySerialNumber.getClass().getDeclaredFields();

        for (Field f: fields) {
            if (f.isAnnotationPresent(Injection.class)) {
                setValue(f, toySerialNumber);
            }
        }
        return toySerialNumber;
    }





    private void setValue(Field field, Object item) {
        Injection injector = field.getAnnotation(Injection.class);

        field.setAccessible(true);
        String value = props.getProperty(injector.stringName());
        final Class<?> type = field.getType();
        try {
            if (String.class.isAssignableFrom(type)) {
                field.set(item, value);
            } else if (int.class.isAssignableFrom(type)) {
                field.set(item, Integer.parseInt(value));
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
