package task2annotation.annotation;

public class ItemName {

    @Injection(stringName = "item.name")
    private String fieldName;
    public String getFieldName() {
        return fieldName;
    }

}
