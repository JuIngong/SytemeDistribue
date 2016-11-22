package fr.unice.miage.m1.tp3;

/**
 * Class created on 22/11/2016
 *
 * @author JuIngong
 */
public class GenClient implements Runnable {
    String name;

    public GenClient(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        new MiniClient("127.0.0.1", 12000, name).connect();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new GenClient("name"+i)).start();
        }
    }
}
