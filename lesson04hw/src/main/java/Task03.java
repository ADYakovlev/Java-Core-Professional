/*
 *@author Yakovlev Alexandr
 * 3. Написать класс МФУ, на котором возможны одновременная печать и сканирование документов, при этом нельзя одновременно печатать или сканировать два документа
 * (при печати в консоль выводится сообщения «отпечатано 1, 2, 3,... страницы», при сканировании – «отсканировано...». Вывод в консоль все так же с периодом в 50 мс.)
 */
public class Task03 {
    public synchronized void print() throws InterruptedException {
        int counter = 0;
        while(true){
            System.out.println("Отпечатано " + counter + "страницы");
            Thread.sleep(50);
        }
    }

    public synchronized void scann() throws InterruptedException {
        int counter = 0;
        while (true) {
            System.out.println("Отсканированно " + counter);
            Thread.sleep(50);
        }
    }

}
