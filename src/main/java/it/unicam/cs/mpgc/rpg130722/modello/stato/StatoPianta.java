package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

import java.io.Serializable;
import java.util.Random;


/**
 * Rappresenta lo stato base di una pianta,
 * Definisce le azioni che possono essere eseguite su una pianta
 */
public interface StatoPianta extends Serializable {

    boolean annaffia(Pianta p);

    boolean trascura(Pianta p);

    boolean purifica(Pianta p);

    void passaGiorno(Pianta p, Random r);

    String getDescrizione();
}