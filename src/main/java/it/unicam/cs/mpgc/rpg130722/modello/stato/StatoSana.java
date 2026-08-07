package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

/** Rappresenta lo stato sano
 * annaffiarla non porta a nulla
 * in questo stadio la pianta può solo peggiorare e diventare trascurata
 */
public class StatoSana implements StatoPianta {

    @Override
    public void trascura(Pianta pianta)
    {
        pianta.setStato(new StatoAppassita());
    }

    @Override
    public String getDescrizione()
    {
        return "Sana";
    }

    // Questi metodi non hanno effetti su una pianta sana
    @Override
    public void annaffia(Pianta pianta){}
    public void purifica(Pianta pianta){}

}
