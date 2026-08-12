package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

public interface StatoPianta {


    void annaffia(Pianta pianta);

    void trascura(Pianta pianta);

    void purifica(Pianta pianta);

    String getDescrizione();
}