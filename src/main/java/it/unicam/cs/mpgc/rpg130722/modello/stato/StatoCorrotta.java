package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

/**
 * Rappresenta lo stato peggiore, ormai annaffiarla non serve più
 * solo la purificazione ha effetto per curarla
 */
public class StatoCorrotta implements StatoPianta{

    @Override
    public void purifica(Pianta pianta)
    {
        pianta.setStato(new StatoCurata());
    }
    @Override
    public String getDescrizione()
    {
        return "Corrota";
    }

    // questi metodi non hanno effetto sulla pianta corrotta
    @Override
    public void annaffia(Pianta pianta){}
    public void trascura(Pianta pianta){}

}
