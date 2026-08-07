package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

/**
 * Rappresenta lo stato di una pianta
 * Definisce le azioni che possono essere eseguite su una pianta
 */
public interface StatoPianta {

    /**
     * Annaffia la pianta.
     * @param pianta specifica la pianta da annaffiare
     */
    void annaffia(Pianta pianta);

    /**
     * Trascura la pianta.
     * @param pianta specifica la pianta da trascurare
     */
    void trascura(Pianta pianta);

    /**
     * Purifica la pianta.
     * @param pianta specifica la pianta da purificare
     */
    void purifica(Pianta pianta);

    /**
     * Restituisce una descrizione testuale dello stato
     * @return la descrizione dello stato
     */
    String getDescrizione();
}