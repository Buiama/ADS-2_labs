package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, ArrayList<String>> h = new HashMap<>();

        Scanner in = new Scanner(System.in);
        System.out.print("Generate automatically? [Y/N]: ");
        String yn = in.nextLine();
        char yes = yn.charAt(0);

        if (yes == 'y' || yes == 'Y' || yes == '+' || yes == '1') {
            System.out.println("You chose automatic generation!");
            System.out.print("Enter the number of strings to hash: ");
            int count = in.nextInt();
            rand(count, h);
            in.nextLine();
        } else {
            System.out.println("You chose manual input!");
            File file = new File("Hash1.txt");
            Scanner scan = new Scanner(file);
            String input;
            while (scan.hasNextLine()) {
                input = scan.nextLine();
                hashing(input, h);
            }
        }
        System.out.println("\nHash table:");
        output(h);


        System.out.print("\nEnter a string to search: ");
        String toFind = in.nextLine();
        for(String key : h.get(search(toFind))) {
            if(toFind.equals(key)) {
                System.out.println(toFind);
            }
        }
        //System.out.println(h.get(search(toFind)));
    }

    public static void hashing(String str, HashMap<String, ArrayList<String>> h) {
        Fnv f = new Fnv();

        byte[] byteArray = str.getBytes(StandardCharsets.UTF_8);

        String hash = f.fnv1a_32(byteArray) + "";
        if (h.containsKey(hash)) {
            h.get(hash).add(str);
        } else {
            ArrayList<String> arr = new ArrayList<>();
            arr.add(str);
            h.put(hash, arr);
        }
    }

    public static void output(HashMap<String, ArrayList<String>> h) {
        for (String key : h.keySet()) {
            System.out.println(h.get(key) + " " + key);
        }
    }

    public static void rand(int counter, HashMap<String, ArrayList<String>> h) {
        int randomLen, random;
        String word;
        for (int i = 0; i < counter; i++) {
            word = "";
            randomLen = (int) (Math.random() * 200 + 1);
            for (int j = 0; j < randomLen; j++) {
                random = (int) (Math.random() * 94 + 33);
                word += (char) random;
            }
            hashing(word, h);
        }
    }

    public static String search(String str) {
        Fnv f = new Fnv();

        byte[] byteArray = str.getBytes(StandardCharsets.UTF_8);

        return f.fnv1a_32(byteArray)+"";
    }
}


//int a = 100;
//int g =a;
//byte[] bytes = new byte[Integer.BYTES];
//int length = bytes.length;
//for (int i = 0; i < length; i++) {
//    bytes[length - i - 1] = (byte) (a & 0xFF);
//    a >>= 8;
//}