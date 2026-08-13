package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

/**
 * Rappresenta lo stato base di una pianta,
 * Definisce le azioni che possono essere eseguite su una pianta
 */
public interface StatoPianta {

    void annaffia(Pianta pianta);

    void trascura(Pianta pianta);

    void purifica(Pianta pianta);

    String getDescrizione();
}