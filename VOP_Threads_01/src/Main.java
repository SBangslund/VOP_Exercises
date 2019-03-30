import java.io.File;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        File file = new File("output.obj");

        Printer printer = new Printer();
        Runnable lowerCase = new RunnableLowerCasePrinter(printer, 1_000_000);
        Runnable upperCase = new RunnableUpperCasePrinter(printer, 1_000_000);
        Runnable number = new RunnableNumberPrinter(printer, 1_000_000);

        Thread thread1 = new Thread(lowerCase);
        Thread thread2 = new Thread(upperCase);
        Thread thread3 = new Thread(number);

        thread1.start();
        thread2.start();
        thread3.start();

        long initTime = new Date().getTime();
        while(thread1.isAlive() && thread2.isAlive() && thread3.isAlive()) {
            // Waiting for the threads to finish.
        }

        printer.printToFile(file);
        System.out.println(new Date().getTime() - initTime);
    }
}
