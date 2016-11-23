package fr.unice.miage.m1.tp3.ex3;

import java.util.ArrayList;
import java.util.List;

/**
 * Class created on 23/11/2016
 *
 * @author JuIngong
 */
public class Liste {
    List<String> noJobs;

    public Liste() {
        this.noJobs = new ArrayList<>();
    }

    public void add(String text){
        synchronized (noJobs) {
            while (noJobs.size() >= 10) {
                try {
                    noJobs.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            noJobs.add(text);
            System.out.println(noJobs.toString());
            noJobs.notify();
        }
    }

    public  void remove(){
        synchronized (noJobs) {
            while (noJobs.size() == 0) {
                try {
                    noJobs.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            noJobs.remove(0);
            System.out.println(noJobs.toString());
            noJobs.notify();
        }

    }

    public synchronized int size(){
        return noJobs.size();
    }



    public static void main(String[] args) {
        Liste liste = new Liste();
        Thread t1 = new Thread(new Producteur(liste, "BigMac"));
        Thread t2 = new Thread(new Producteur(liste, "McChicken"));
        Thread t3 = new Thread(new Producteur(liste, "Fish"));
        Thread t4 = new Thread(new Producteur(liste, "Crock'Mac"));
        Thread t5 = new Thread(new Consumer(liste));
        Thread t6 = new Thread(new Consumer(liste));
        Thread t7 = new Thread(new Consumer(liste));
        Thread t8 = new Thread(new Consumer(liste));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t8.start();
        t7.start();
        t6.start();
        t5.start();
    }

}
