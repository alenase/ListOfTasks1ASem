package task3;

import java.io.*;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

public class Task3 {


    private static List<String> list = new ArrayList<>();
    private static List<String> words = new ArrayList<>();
    public static void main(String[] args) {

        openFile(askFile());
        search();
        showResult();
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

    static void search(){
        boolean theSame = false;
        StringBuilder word = new StringBuilder( );
        for (int i = 0; i < list.size(); i++) {//по строкам
            for (int j = i+1; j < list.size(); j++) {//по символам строки

                    for (int k = 0; k < (list.get(i).length()); k++) {
                        for (int l = k; l < list.get(j).length(); l++) {

                            if(list.get(i).charAt(k) == list.get(j).charAt(l)){

                                word.append(list.get(i).charAt(k));
                                theSame = true;
                                break;
                            }
                            else{
                                if (!word.toString( ).equals("")){
                                    words.add(word.toString( ));
                                }
                                word = new StringBuilder( );
                                theSame = false;
                            }


                        }
                        //if (theSame) continue;
                    }
            }
        }
    }

    private static void showResult(){
        for (String word: words) {
            System.out.print(word + " ");
        }
    }




}
