import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrimeNumberSearch extends Thread {

    private static List<Integer> listOfPrimeNumbers = new ArrayList<>();
    private boolean stepCheck = false;

    public void run(){
        cycle();
        putValueParticular();

    }

    private void putValueParticular(){
        Task1.listOfPrimeNumbersParticular.addAll(listOfPrimeNumbers);
    }

    private void cycle() {
        //System.out.println(firstIndex() + " " + lastIndex() ); //checking steps
        for (int i = firstIndex() ; i <= lastIndex(); i++) {
            if (ifPrimeNumber(i) && !isInterrupted()) {
                Task1.listOfPrimeNumbersGeneral.add(i);
                listOfPrimeNumbers.add(i);
            }
        }
    }

    private boolean ifPrimeNumber(int number){
        if (number == 1) return false;

        for (int j = 2; j <= number / 2 ; j++) {
            if (number % j == 0) {
                return false;
            }
        }
        return true;
    }






    private int step(){
        if (Task1.lastDigit - Task1.firstDigit > Task1.quantityOfThreads){
            if (Task1.lastDigit - Task1.firstDigit % Task1.quantityOfThreads != 0) {
                stepCheck = true;
            }

                return (Task1.lastDigit - Task1.firstDigit)/ Task1.quantityOfThreads;
        }
        else {
            return 1;
        }
    }

    private int firstIndex(){
        int k = 0;
        for (Map.Entry<Integer, Thread> pair : Task1.mapOfThreads.entrySet( )) {
            if (Thread.currentThread( ).getName().equals(pair.getValue().getName()))
                k = pair.getKey();
        }

        return k * step( )  + Task1.firstDigit;

    }

    private int lastIndex(){
        int k = 0;
        for (Map.Entry<Integer, Thread> pair : Task1.mapOfThreads.entrySet( )) {
            if (Thread.currentThread( ).getName().equals(pair.getValue().getName()))
                k = pair.getKey();
        }

        if (k == Task1.lastThreadIndex -1 && stepCheck) {

            return Task1.lastDigit ;
        } else {
            return k * step( ) + step() + Task1.firstDigit;
        }

    }

    /*
    private int step(){
        if (Task1.lastDigit > Task1.quantityOfThreads){
            if (Task1.lastDigit % 2 != 0) {
                return ((Task1.lastDigit + 1) / Task1.quantityOfThreads);
            } else if (Task1.quantityOfThreads % 2 != 0) {
                return ((Task1.lastDigit ) / Task1.quantityOfThreads +1);
            } else
                return (Task1.lastDigit + 1 / Task1.quantityOfThreads);

        }
        else {
            return 1;
        }

    }

    private int firstIndex(){
        int k = 0;
        for (Map.Entry<Integer, Thread> pair : Task1.mapOfThreads.entrySet( )) {
            if (Thread.currentThread( ).getName().equals(pair.getValue().getName()))
                k = pair.getKey();
        }

        if (k < Task1.quantityOfThreads) {
            return k * step( ) + 1;
        }
        return 0;
    }

    private int lastIndex(){
        int k = 0;
        for (Map.Entry<Integer, Thread> pair : Task1.mapOfThreads.entrySet( )) {
            if (Thread.currentThread( ).getName().equals(pair.getValue().getName()))
                k = pair.getKey();
        }

        if (k < Task1.quantityOfThreads) {
            return k * step( ) + step();
        }
        return 0;
    }



     */




}
