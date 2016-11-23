package fr.unice.miage.m1.tp3;

/**
 * Class created on 22/11/2016
 *
 * @author JuIngong
 */
public class From0toInfinite implements Runnable {

    private volatile boolean done = false;
    private int i = 0;

    public void endIt() {
        done = true;
    }

    @Override
    public void run() {
        while (!done){
            System.out.println(i++);
        }
    }

    public static void main(String[] args) {
        From0toInfinite from0toInfinite = new From0toInfinite();
        Thread thread = new Thread(from0toInfinite);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        from0toInfinite.endIt();
    }
}
