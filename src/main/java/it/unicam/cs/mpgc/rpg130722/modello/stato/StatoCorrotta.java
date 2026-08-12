package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

/**
 * Rappresenta lo stato peggiore, ormai annaffiarla non serve più a niente
 * solo la purificazione ha effetto sulla pianta
 */
public class StatoCorrotta extends StatoPiantaBase{

    @Override
    public void purifica(Pianta pianta)
    {
        pianta.setStato(new StatoCurata());
    }

    @Override
    public String getDescrizione()
    {
        return "Corrotta";
    }
}
