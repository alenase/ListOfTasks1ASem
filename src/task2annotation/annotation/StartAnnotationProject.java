package task2annotation.annotation;

import java.io.IOException;

public class StartAnnotationProject {
    public static void main(String[] args) throws IOException {

        ItemName itemName = Factory.getInstance( ).createFieldName( );
        ItemPrice itemPrice = Factory.getInstance( ).createFieldPrice( );
        ItemQuantity itemQuantity = Factory.getInstance( ).createFieldQuantity( );

        System.out.println(itemName.getFieldName( ));
        System.out.println(itemPrice.getFieldPrice( ));
        System.out.println(itemQuantity.getFieldQuantity( ));



    }

}
