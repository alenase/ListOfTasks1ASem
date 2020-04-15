package task3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchingThread extends Thread{

    @Override
    public void run() {
        search();
        showingLongestString();
    }




    private static void search() {
        StringBuilder word = new StringBuilder( );
        for (String s : Task3.list) {
            for (int j = 0; j < s.length( ); j++) {
                for (int k = j + 1; k < s.length( ); k++) {

                    if (s.charAt(j) == s.charAt(k)) {
                        searchInLine(s, j, k-j);
                    }
                }
            }
        }


        for (int i = 0; i < Task3.list.size(); i++) {//по строкам
            for (int j = i+1; j < Task3.list.size(); j++) {//по символам строки

                for (int k = 0; k < (Task3.list.get(i).length()); k++) {
                    for (int l = k; l < Task3.list.get(j).length(); l++) {

                        if(Task3.list.get(i).charAt(k) == Task3.list.get(j).charAt(l)) {
                            searchInLines(Task3.list.get(i).substring(k), Task3.list.get(j).substring(l));

                        }


                    }
                }
            }
        }
    }

    private static void searchInLine(String s, int start, int difference){
        String word = "";
        for (int i = difference + start; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - difference)){
                word += s.charAt(i);
            } else {
                Task3.words.add(word);
                word = "";
            }
        }
    }

    private static void searchInLines(String s1, String s2) {
        String word = "";
        int length = Math.min(s1.length( ), s2.length( ));
        for (int i = 0; i < length; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                word += s1.charAt(i);
            } else {
                Task3.words.add(word);
                word = "";
            }
        }
    }

    private static String findLongestString(){
        String longestString = "";
        for (String word: Task3.words) {
            if (word.length() > longestString.length())
                longestString = word;
        }
        return longestString;
    }

    private static void showingLongestString(){
        int count = 0;
        String longestString= findLongestString();
        System.out.println("Longest string is " + longestString );
        for (String string: Task3.list) {
            int k = 0;
            while (string.contains(longestString) && string.length() >= longestString.length() && count < 2) {
                    System.out.println("And its index is " +
                            (string.indexOf(longestString) + k));

                count++;
                string = string.substring(Task3.list.indexOf(longestString) + longestString.length());
                k += Task3.list.indexOf(longestString) + longestString.length();

            }
        }
    }
}
