package Task1Subtask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task1 {

    static int firstDigit, lastDigit;
    static int quantityOfThreads;
    static int lastThreadIndex;
    static List<Integer> listOfPrimeNumbersParticular = new ArrayList<>();
    public static List<Thread> listOfThreads = new ArrayList<>();
    static long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        askingNumbers();

        generatingThreads();

        endingThreads();



        showingResultParticular();
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
        for (Thread thread : listOfThreads) {
            try{
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace( );
            }
        }
    }

    private static void generatingThreads(){
        checkingQuantityOfPossibleThreads();
        //System.out.println(lastThreadIndex );
        for (int i = 0; i < lastThreadIndex; i++) {
            listOfThreads.add(new PrimeNumberSearch(i));
            listOfThreads.get(i).start();
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