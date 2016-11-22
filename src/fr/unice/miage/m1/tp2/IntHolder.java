package fr.unice.miage.m1.tp2;

/**
 * Class created on 14/10/2016
 *
 * @author JuIngong
 */
public class IntHolder
{
    private int ma_valeur;

    public IntHolder(int i)
    {
        ma_valeur = 0;
        System.out.format("Valeur partagee initialisee a %d\n", ma_valeur);
    }

    public void plus()
    {
        ma_valeur++;
    }

    public void moins()
    {
        ma_valeur--;
    }

    public String toString()
    {
        return Integer.toString(ma_valeur);
    }

    public int val()
    {
        return ma_valeur;
    }

    public synchronized void add(int i)
    {
        ma_valeur += i;
    }

}
