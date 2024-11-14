package be.vinci.compteur_runnable;

import be.vinci.sync.Compteur;

import java.time.LocalDateTime;
import java.util.stream.IntStream;
import java.util.concurrent.atomic.AtomicInteger;

public class CompteurRunnableWithRaceCondition extends Compteur implements Runnable {
    private String nom;
    private int max;
    //TODO: 1. ajouter un attribut de classe qui retient l'ordre d'arrivée.

    private static AtomicInteger ordreArrivee = new AtomicInteger(0);

    public CompteurRunnableWithRaceCondition(String nom, int max) {
        super(nom, max);
        this.nom = nom;
        this.max = max;
    }

    @Override
    public void run() {
        count();
    }

    @Override
    public void count() {
        //TODO: 2. Quand le compte est terminé, afficher que le compteur a finit de compter
        //         et indiquer son ordre d'arrivée.
        //      3. Veuillez ajouter un délai supplémentaire de 10ms après avoir déterminé l'ordre d'arrivée.
        IntStream.rangeClosed(1, max).forEach(i -> {
            System.out.println(nom + " : " + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ordreArrivee.incrementAndGet();
        System.out.println(nom + " est arrivé en " + ordreArrivee + "e position.");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nom + " a finit de compter jusqu'à " + max);
    }
}