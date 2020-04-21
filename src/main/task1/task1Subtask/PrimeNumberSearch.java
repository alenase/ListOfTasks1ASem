package main.task1.task1Subtask;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberSearch extends Thread {
    int count;
    private static List<Integer> listOfPrimeNumbers = new ArrayList<>();
    private boolean stepCheck = false;

    PrimeNumberSearch(int count){
        this.count = count;
    }

    public void run(){
        cycle();
        generateValueParticularlist();

    }

    private void generateValueParticularlist(){
        Task1.listOfPrimeNumbersParticular.addAll(listOfPrimeNumbers);
    }

    private void cycle() {
        for (int i = firstIndex() ; i <= lastIndex(); i++) {
            if (ifPrimeNumber(i) && !isInterrupted()) {
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
        return count * step( ) + Task1.firstDigit;

    }

    private int lastIndex(){
        if (count == Task1.lastThreadIndex -1 && stepCheck) {

            return Task1.lastDigit ;
        } else {
            return count * step( ) + step() + Task1.firstDigit;
        }

    }


}