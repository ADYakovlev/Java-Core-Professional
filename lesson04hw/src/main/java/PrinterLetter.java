/*
 *@author Yakovlev Alexandr
 */
public class PrinterLetter {
    public static final int PRINT_COUNT = 5;

    private static Object lock = new Object();
    private static volatile Letter currentLetter = Letter.A;

    private Letter printedLetter;

    public PrinterLetter(Letter letter){this.printedLetter = letter;}

    public void print(){
        for (int i = 0; i < PRINT_COUNT; i++) {
            synchronized (lock){
                while (printedLetter != currentLetter){
                    waitLetter();
            }
                System.out.println(printedLetter.getLetter());
                currentLetter = currentLetter.getNextLetter();
                lock.notify();
            }
        }
    }

    private void  waitLetter(){
        try{
            lock.wait();
        } catch (InterruptedException e) {
            System.out.println("Print has been interrupted");
        }
    }
}
