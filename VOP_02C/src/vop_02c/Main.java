package vop_02c;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(">");
        String input = "";
        
        while(!input.equals("quit")) {
            input = scanner.nextLine();
            
            if(input.equals("quit")) break;
            
            writeTextFile(input, "output.txt");
            System.out.print(">");
        }
    }

    public static void writeTextFile(String line, String filename) {
        File file = new File(filename);
        List<String> data = readTextFile("output.txt");
        try {
            PrintWriter print = new PrintWriter(file);
            for(String fileLine : data) {
                print.append(fileLine + "\n");
            }
            print.append(String.format("%s : %s", new Date(), line));
            print.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to load file: " + file.getAbsolutePath());
        }
    }

    public static List<String> readTextFile(String filename) {
        File file = new File(filename);
        List<String> data = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to load file: " + file.getAbsolutePath());
        }
        return data;
    }
}
