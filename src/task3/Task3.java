package task3;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task3 {

    static List<String> list = new ArrayList<>();
    static Set<String> words = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {

        openFile(askFile());
        Thread thread = new SearchingThread();
        thread.start();
        thread.join();

    }

    private static String askFile(){
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace( );
        }
        return null;
    }

    private static void openFile(String filename) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            readFromFile(reader);
        } catch (IOException e) {
            e.printStackTrace( );
        }
    }

    private static void readFromFile(BufferedReader reader) throws IOException {
        while (reader.ready()){
            list.add(reader.readLine());
        }

    }




    /*private static void showResult(){
        for (String word: words) {
            System.out.print(word + " ");
        }
    }
    */



}
