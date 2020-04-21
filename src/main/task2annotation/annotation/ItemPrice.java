package main.task2annotation.annotation;

public class ItemPrice {

    @Injection(stringName = "item.price")
    private double fieldPrice;

    public double getFieldPrice() {
        return fieldPrice;
    }
}
