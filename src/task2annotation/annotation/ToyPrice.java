package task2annotation.annotation;

public class ToyPrice {

    @Injection(stringName = "item.price")
    private double fieldPrice;

    public double getFieldPrice() {
        return fieldPrice;
    }
}
