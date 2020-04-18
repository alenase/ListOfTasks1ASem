package task2annotation.annotation;

public class ItemQuantity {

    @Injection(stringName = "item.quantity")
    private int fieldQuantity;

    public int getFieldQuantity(){
        return fieldQuantity;
    }
}
