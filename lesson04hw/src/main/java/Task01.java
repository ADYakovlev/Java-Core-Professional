import java.util.List;


/*
 *@author Yakovlev Alexandr
 */
public class Task01 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start Task 1");
        List<Thread> printers = List.of(
                new Thread(new PrinterLetter (Letter.A)::print),
                new Thread(new PrinterLetter (Letter.B)::print),
                new Thread(new PrinterLetter (Letter.C)::print)

        );

        printers.forEach(Thread::start);
        for(Thread printer: printers){
            printer.join();
        }

        System.out.println("\nFinish task 1");

    }

}
