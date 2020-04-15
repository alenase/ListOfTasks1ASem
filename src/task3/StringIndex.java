package task3;

import java.util.ArrayList;
import java.util.List;

public class StringIndex {
    String string;
    List<Integer> startIndex = new ArrayList<>();
    int quantity;

    StringIndex(String string, int startIndex){
        this.string = string;
        this.startIndex.add(startIndex);
        quantity = 1;
    }
}
