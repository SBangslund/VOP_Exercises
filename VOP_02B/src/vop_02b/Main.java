package vop_02b;

import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String userInput = "";

        while (!userInput.equals("quit")) {
            System.out.print("Enter a hexidecimal ('quit' to exit): ");
            userInput = input.nextLine().toLowerCase();

            if (userInput.equals("quit")) break;
            
            try {
                System.out.println("The decimal value for hex number "
                        + userInput + " is " + hexToDecimal(userInput.toUpperCase()));
            } catch (NumberFormatException e) {
                System.err.println("Not a string of hexidecimals: " + e.getMessage());
            }
        }
    }

    public static int hexToDecimal(String hex) throws NumberFormatException {
        int decimalValue = 0;
        for (int i = 0; i < hex.length(); i++) {
            char hexChar = hex.charAt(i);
            decimalValue = decimalValue * 16 + hexCharToDecimal(hexChar);
        }
        return decimalValue;
    }

    public static int hexCharToDecimal(char ch) throws NumberFormatException {
        if(ch >= 'A' && ch <= 'F') {
            return 10 + ch - 'A';
        } else if(ch >= '0' && ch <= '9') {
            return ch - '0';
        } else {
            throw new NumberFormatException(ch + " is not a hexidecimal");
        }
    }
}
