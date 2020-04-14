import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task1 {

    static int firstDigit, lastDigit;
    static int quantityOfThreads;
    static int lastThreadIndex;
    static List<Integer> listOfPrimeNumbersGeneral = new ArrayList<>();
    static List<Integer> listOfPrimeNumbersParticular = new ArrayList<>();
    public static Map<Integer, Thread> mapOfThreads = new HashMap<>();
    static long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        askingNumbers();

        generatingThreads();

        endingThreads();

        showingResultGeneral();

        showingResultParticular();
        }


    private static void showingResultGeneral(){
        long endOfPrimeNumbersGeneral = System.currentTimeMillis();
        System.out.print("\nGeneral list result: " );
        Collections.sort(listOfPrimeNumbersGeneral);
        for (Integer integer : listOfPrimeNumbersGeneral) {
            System.out.print(integer + " ");
        }
        System.out.print("\nThe function was running withing " + (endOfPrimeNumbersGeneral - startTime) + " sec");
    }

    private static void showingResultParticular(){
        long endOfPrimeNumbersParticular = System.currentTimeMillis();
        System.out.print("\nParticular list result: " );
        Collections.sort(listOfPrimeNumbersParticular);
        for (Integer integer : listOfPrimeNumbersParticular) {
            System.out.print(integer + " ");
        }
        System.out.print("\nThe function was running withing " + (endOfPrimeNumbersParticular - startTime ) + " sec");
    }


    private static void endingThreads() {
        for (Map.Entry<Integer, Thread> pair : mapOfThreads.entrySet( )) {
            try {
                pair.getValue( ).join( );
            } catch (InterruptedException ignored) {
            }
        }
    }

    private static void generatingThreads(){
        checkingQuantityOfPossibleThreads();
        System.out.println(lastThreadIndex );
        for (int i = 0; i < lastThreadIndex; i++) {
            mapOfThreads.put(i, new PrimeNumberSearch());
            mapOfThreads.get(i).start();
        }
    }

    private static void checkingQuantityOfPossibleThreads(){
            lastThreadIndex = quantityOfThreads;
            if (quantityOfThreads > lastDigit - firstDigit ) {
                lastThreadIndex = Math.min(quantityOfThreads, (lastDigit - firstDigit));
                quantityOfThreads = lastDigit - firstDigit;
            }
    }

    private static void askingNumbers(){
        System.out.println("Type the first digit" );
        firstDigit = readerInteger();
        System.out.println("Type the last digit" );
        lastDigit = readerInteger();
        System.out.println("Type the quantity of threads" );
        quantityOfThreads = readerInteger();
    }

    private static int readerInteger(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace( );
        }
        return 1;
    }


}
