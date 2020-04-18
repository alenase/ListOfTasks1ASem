package task1.task1;

public class PrimeNumberSearch extends Thread {
    int count;

    private boolean stepCheck = false;

    PrimeNumberSearch(int count){
        this.count = count;
    }

    public void run(){
        cycle();


    }

    private void cycle() {
        //System.out.println(firstIndex() + " " + lastIndex() ); //checking steps
        for (int i = firstIndex() ; i <= lastIndex(); i++) {
            if (ifPrimeNumber(i) && !isInterrupted()) {
                Task1.listOfPrimeNumbersGeneral.add(i);
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