package task1.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task1 {

    static int firstDigit, lastDigit;
    static int quantityOfThreads;
    static int lastThreadIndex;
    static List<Integer> listOfPrimeNumbersGeneral = new ArrayList<>();
    public static List<Thread> listOfThreads = new ArrayList<>();
    static long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        askNumbers();

        generateThread();

        stopThreads();

        showResultGeneral();

    }


    private static void showResultGeneral(){
        long endOfPrimeNumbersGeneral = System.currentTimeMillis();
        System.out.print("\nGeneral list result: " );
        Collections.sort(listOfPrimeNumbersGeneral);
        for (Integer integer : listOfPrimeNumbersGeneral) {
            System.out.print(integer + " ");
        }
        System.out.print("\nThe function was running withing " + (endOfPrimeNumbersGeneral - startTime) + " sec");
    }



    private static void stopThreads() {
        for (Thread thread : listOfThreads) {
            try{
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace( );
            }
        }
    }

    private static void generateThread(){
        checkQuantityOfPossibleThreads();
        for (int i = 0; i < lastThreadIndex; i++) {
            listOfThreads.add(new PrimeNumberSearch(i));
            listOfThreads.get(i).start();
        }
    }

    private static void checkQuantityOfPossibleThreads(){
        lastThreadIndex = quantityOfThreads;
        if (quantityOfThreads > lastDigit - firstDigit ) {
            lastThreadIndex = Math.min(quantityOfThreads, (lastDigit - firstDigit));
            quantityOfThreads = lastDigit - firstDigit;
        }
    }

    private static void askNumbers(){
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