package fr.unice.miage.m1.tp2;

import java.util.ArrayList;
import java.util.List;

/**
 * Class created on 14/10/2016
 *
 * @author JuIngong
 */
public class Sleeper implements Runnable {

    private String name;
    private List<Thread> threads;

    public Sleeper(String name, List<Thread> threads) {
        this.name = name;
        this.threads = threads;
    }

    @Override
    public void run() {
        try {
            if (threads.size() == 1) {
                synchronized (threads.get(0)) {
                    threads.get(0).wait();
                }
            }
            Thread.sleep(500);
            System.out.println("Je suis " + name + ", j'ai finit;");
            synchronized (this) {
                this.notify();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }



    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            List<Thread> list = new ArrayList<>();
            if (threads.size() > 0) {
                list.add(threads.get(threads.size() - 1));
            }
            threads.add(new Thread(new Sleeper(i + 1 + "", list)));
        }

        for (int i = threads.size(); i > 0; i--) {
            threads.get(i - 1).start();
        }
    }
}
