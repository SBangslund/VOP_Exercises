public class RunnableUpperCasePrinter implements Runnable {

    private final Printer printer;
    private final int timesToPrint;

    public RunnableUpperCasePrinter(Printer printer, int timesToPrint) {
        this.printer = printer;
        this.timesToPrint = timesToPrint;
    }

    @Override
    public void run() {
        for (int i = 0; i < timesToPrint; i++) {
            printer.write(Character.toString(createRandomUpperCase()));
        }
    }

    public char createRandomUpperCase() {
        return (char)Math.round(Math.random() * 25 + 65);
    }
}
