package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

/**
 * Rappresenta lo stato iniziale di ogni nuova pianta
 * se curata la pianta migliora e torna sana
 * se trascurata la pianta peggiora e diventa corrotta
 */

public class StatoAppassita extends StatoPiantaBase {

    @Override
    public boolean annaffia(Pianta p)
    {
        p.setStato(new StatoSana());
        return true;
    }

    @Override
    public boolean trascura(Pianta p)
    {
        p.setStato(new StatoCorrotta());
        return true;
    }

    @Override
    public String getDescrizione()
    {
        return "Appassita";
    }

    @Override
    double probabilitaDegrado()
    {
        return 0.3;
    }
}
