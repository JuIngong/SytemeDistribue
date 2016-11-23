package fr.unice.miage.m1.tp3.ex3;

/**
 * Class created on 23/11/2016
 *
 * @author JuIngong
 */
public class Consumer implements Runnable {
    Liste list;

    public Consumer(Liste list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            list.remove();
            try {
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
