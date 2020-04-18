package task1.task3;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task3 {

    static List<String> list = new ArrayList<>();
    static Set<String> words = new HashSet<>();
    static Thread thread = new SearchingThread();

    public static void main(String[] args)  {

        openFile(askFile());
        runThread();

    }


    static void runThread(){
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
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




    /*private static void showAllWords(){
        for (String word: words) {
            System.out.print(word + " ");
        }
    }
    */



}
