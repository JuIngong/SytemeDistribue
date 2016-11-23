package fr.unice.miage.m1.tp3.ex3;

import java.util.List;

/**
 * Class created on 23/11/2016
 *
 * @author JuIngong
 */
public class Producteur implements Runnable {

    Liste list;
    String text;

    public Producteur(Liste list, String text) {
        this.list = list;
        this.text = text;
    }

    @Override
    public void run() {
        while (true) {
            list.add(text);
            try {
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
