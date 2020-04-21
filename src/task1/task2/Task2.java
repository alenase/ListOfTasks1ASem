package task1.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task2 {


        static int firstDigit, lastDigit;
        static int quantityOfThreads;
        static int lastThreadIndex;
        static List<Integer> listOfPrimeNumbersGeneral = new ArrayList<>();
        static List<Integer> listOfPrimeNumbersParticular = new ArrayList<>();
        static ExecutorService executor;
        static long startTime = System.currentTimeMillis();

        public static void main(String[] args) {
            askNumbers();

            generateThreads();

            endThreads();

            showResultGeneral();

            showResultParticular();
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

        private static void showResultParticular(){
            long endOfPrimeNumbersParticular = System.currentTimeMillis();
            System.out.print("\nParticular list result: " );
            Collections.sort(listOfPrimeNumbersParticular);
            for (Integer integer : listOfPrimeNumbersParticular) {
                System.out.print(integer + " ");
            }
            System.out.print("\nThe function was running withing " + (endOfPrimeNumbersParticular - startTime ) + " sec");
        }


        private static void endThreads() {
            executor.shutdown();
        }

        private static void generateThreads(){
            checkQuantityOfPossibleThreads();
            executor = Executors.newFixedThreadPool(lastThreadIndex);
            for (int i = 0; i < lastThreadIndex; i++) {
            executor.execute(new PrimeNumberSearch2(i));
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


