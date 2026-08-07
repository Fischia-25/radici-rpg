package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

/**
 * Rappresenta lo stato iniziale di ogni nuova pianta
 * se curata la pianta migliora e torna sana
 * se trascurata la pianta peggiora e diventa corrotta
 */
public class StatoAppassita implements StatoPianta{

    @Override
    public void annaffia(Pianta pianta)
    {
        pianta.setStato(new StatoSana());
    }

    @Override
    public void trascura(Pianta pianta)
    {
        pianta.setStato(new StatoCorrotta());
    }

    @Override
    public String getDescrizione()
    {
        return "Appassita";
    }

    // La pianta non è ancora corrotta, la purificazione non ha effetti
    @Override
    public void purifica(Pianta pianta){}


}
