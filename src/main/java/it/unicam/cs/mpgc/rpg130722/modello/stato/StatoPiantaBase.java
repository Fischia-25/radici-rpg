package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

/**
 * Rappresenta lo stato base di una pianta,
 * Definisce le azioni che possono essere eseguite su una pianta
 */
public abstract class StatoPiantaBase implements StatoPianta {

    @Override
    public void annaffia(Pianta pianta) {}

    @Override
    public void trascura(Pianta pianta) {}

    @Override
    public void purifica(Pianta pianta) {}

}
