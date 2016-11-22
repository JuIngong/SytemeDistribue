package fr.unice.miage.m1.tp2;

/**
 * Class created on 14/10/2016
 *
 * @author JuIngong
 */
public class TestOrdre0 implements Runnable
{
    int pasDeComptage = 0;
    String nom = null;
    int maxV = 0;

    public TestOrdre0(String nom, int increment, int max)
    {
        this.pasDeComptage = increment;
        this.nom = nom;
        this.maxV = max;
    }

    public void run()
    {
        System.out.format("Ici le  thread %s, je debute!\n", nom);

        for (int i = 0; i < maxV; i++)
        {
            System.out.format("[%s] dit  %d\n", nom, i);
            i += pasDeComptage;

        }
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length != 2)
        {
            System.out.format("Ouch ! utilisation: java %s   nbthreads   maxvalue \nCe programme Utilise deux arguments que vous devez fournir\n",
                    TestOrdre0.class.getCanonicalName());
            System.exit( - 1);
        }

        int nbThreads = Integer.parseInt(args[0]);
        int maxValue = Integer.parseInt(args[1]);

        Thread[] mesJobs = new Thread[100];
        for (int t = 0; t < nbThreads; t++)
        {
            String jobName = String.format("Job_%d", t);
            TestOrdre0 tiJob = new TestOrdre0(jobName, t, maxValue);
            System.out.format("Creating thread %s\n", jobName);
            mesJobs[t] = new Thread(tiJob);
            mesJobs[t].start();
        }

        System.out.format("Main :Fini ici  !\n");
    }

    public static void waithere(int num)
    {
        try
        {
            Thread.sleep((int) (Math.random()) * num);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
