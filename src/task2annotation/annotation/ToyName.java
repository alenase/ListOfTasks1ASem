package task2annotation.annotation;

public class ToyName {

    @Injection(stringName = "item.name")
    private String fieldName;

    public String getFieldName() {
        return fieldName;
    }
}
