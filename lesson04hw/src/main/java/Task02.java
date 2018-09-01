import java.io.*;

/*
 *@author Yakovlev Alexandr
 *  2. Написать совсем небольшой метод, в котором 3 потока построчно пишут данные в файл (по 10 записей с периодом в 20 мс)
 */
public class Task02 {
    public static void main(String[] args) throws IOException {
        String string = "line";
        writeString(string);
        new Thread(()->{for (int i = 0; i < 10; i++) {
            try {writeString(string+i+"\n");} catch (IOException e) {e.printStackTrace();}
            try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
        }}).start();
        new Thread(()->{for (int i = 0; i < 10; i++) {
            try {writeString(string+i+"\n");} catch (IOException e) {e.printStackTrace();}
            try {Thread.sleep(20);} catch (InterruptedException e){e.printStackTrace();}
        }}).start();
        new Thread(()->{for (int i = 0; i < 10; i++) {
            try {writeString(string+i+"\n");} catch (IOException e) {e.printStackTrace();}
            try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
        }}).start();
    }

    private synchronized static void writeString(String string) throws IOException {
        DataOutputStream out = null;
        out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("C:\\projects\\lesson04hw\\file.txt",true)));
        try {
            out.write(string.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {out.close();}
    }

}
