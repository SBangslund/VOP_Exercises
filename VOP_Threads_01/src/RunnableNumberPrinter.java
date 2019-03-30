public class RunnableNumberPrinter implements Runnable {

    private final Printer printer;
    private final int timesToPrint;

    public RunnableNumberPrinter(Printer printer, int timesToPrint) {
        this.printer = printer;
        this.timesToPrint = timesToPrint;
    }

    @Override
    public void run() {
        for (int i = 0; i < timesToPrint; i++) {
            printer.write(Integer.toString(createRandomNumber()));
        }
    }

    public int createRandomNumber() {
        return (int)Math.round(Math.random() * 9);
    }
}
