package task1.task2;

import java.util.ArrayList;
import java.util.List;


public class PrimeNumberSearch2 implements Runnable{

    int count;
    private static List<Integer> listOfPrimeNumbers = new ArrayList<>();
    private boolean stepCheck = false;

    public void run(){
        cycle();
        generatingValueParticularlist();

    }

    PrimeNumberSearch2(int count){
        this.count = count;
    }

    private void generatingValueParticularlist(){
        Task2.listOfPrimeNumbersParticular.addAll(listOfPrimeNumbers);
    }

    private void cycle() {
        System.out.println(firstIndex() + " " + lastIndex() ); //checking steps
        for (int i = firstIndex() ; i <= lastIndex(); i++) {
            if (ifPrimeNumber(i)) {
                Task2.listOfPrimeNumbersGeneral.add(i);
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
        if (Task2.lastDigit - Task2.firstDigit > Task2.quantityOfThreads){
            if (Task2.lastDigit - Task2.firstDigit % Task2.quantityOfThreads != 0) {
                stepCheck = true;
            }
            return (Task2.lastDigit - Task2.firstDigit)/ Task2.quantityOfThreads;
        }
        else {
            return 1;
        }
    }

    private int firstIndex(){
        return count * step( ) + Task2.firstDigit;

    }

    private int lastIndex(){
        if (count == Task2.lastThreadIndex -1 && stepCheck) {

            return Task2.lastDigit ;
        } else {
            return count * step( ) + step() + Task2.firstDigit;
        }

    }


}
