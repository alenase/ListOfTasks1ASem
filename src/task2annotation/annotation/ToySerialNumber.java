package task2annotation.annotation;

public class ToySerialNumber {

    @Injection(stringName = "item.serial.number")
    private int fieldSerialNumber;

    public int getFieldSerialNumber(){
        return fieldSerialNumber;
    }
}
