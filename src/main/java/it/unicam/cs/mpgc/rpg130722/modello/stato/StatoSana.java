package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

/**
 * Rappresenta lo stato sano
 * in questo stadio la pianta può solo peggiorare e diventare trascurata
 */
public class StatoSana extends StatoPiantaBase {

    @Override
    public void trascura(Pianta p)
    {
        p.setStato(new StatoAppassita());
    }

    @Override
    public String getDescrizione()
    {
        return "Sana";
    }

    @Override
    double probabilitaDegrado()
    {
        return 0.1;
    }

}
