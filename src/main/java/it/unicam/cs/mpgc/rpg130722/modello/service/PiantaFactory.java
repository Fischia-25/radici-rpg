package it.unicam.cs.mpgc.rpg130722.modello.service;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;
import it.unicam.cs.mpgc.rpg130722.modello.entita.Ricordo;

/**
 *
 * Crea le piante e le associa al ricordo
 */
public class PiantaFactory {

    private PiantaFactory()
    {

    }

    public static Pianta creaPianta (String nome, String nomeDefunto, String storia)
    {
        Ricordo ricordo = new Ricordo(nomeDefunto, storia);
        return new Pianta(nome, ricordo);
    }


}
