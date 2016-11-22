package fr.unice.miage.m1.tp2;

/**
 * Class created on 14/10/2016
 *
 * @author JuIngong
 */
public class Petit_Job implements Runnable {
    private IntHolder notre_entier;
    private int increment;

    Petit_Job(IntHolder notre_entier, int increment) {
        this.increment = increment;
        this.notre_entier = notre_entier;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1e4; i++) {
            notre_entier.add(increment);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntHolder Compteur = new IntHolder(0);
        Petit_Job objex1 = new Petit_Job(Compteur, 1);
        Petit_Job objex2 = new Petit_Job(Compteur, -1);
        Thread tache1 = new Thread(objex1);
        Thread tache2 = new Thread(objex2);


        tache1.start();
        tache2.start();

        tache1.join();
        tache2.join();

        System.out.format("Le compteur vaut %s\n", Compteur);


        System.out.format("Le compteur vaut %s\n", Compteur);
    }

}
