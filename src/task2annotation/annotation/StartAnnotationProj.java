package task2annotation.annotation;

import java.io.IOException;

public class StartAnnotationProj {
    public static void main(String[] args) throws IOException {

            ToyName toyName = Factory.getInstance( ).createFieldName( );
            ToyPrice toyPrice = Factory.getInstance( ).createFieldPrice( );
            ToyQuantity toyQuantity = Factory.getInstance( ).createFieldQuantity( );
            ToySerialNumber toySerialNumber = Factory.getInstance( ).createFieldSerialNumber( );

            System.out.println(toyName.getFieldName( ));
            System.out.println(toyPrice.getFieldPrice( ));
            System.out.println(toyQuantity.getFieldQuantity( ));
            System.out.println(toySerialNumber.getFieldSerialNumber( ));


    }

}
