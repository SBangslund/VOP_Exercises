public class RunnableLowerCasePrinter implements Runnable {

    private final Printer printer;
    private final int timesToPrint;

    public RunnableLowerCasePrinter(Printer printer, int timesToPrint) {
        this.printer = printer;
        this.timesToPrint = timesToPrint;
    }

    @Override
    public void run() {
        for (int i = 0; i < timesToPrint; i++) {
            printer.write(Character.toString(createRandomLowerCase()));
        }
    }

    public char createRandomLowerCase() {
        return (char)Math.round(Math.random() * 25 + 97);
    }
}
