package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

import java.util.Random;

public abstract class StatoPiantaBase implements StatoPianta {

    @Override
    public void annaffia(Pianta p) {}

    @Override
    public void trascura(Pianta p) {}

    @Override
    public void purifica(Pianta p) {}

    @Override
    public void passaGiorno(Pianta p, Random r)
    {
        if (r.nextDouble() < probabilitaDegrado())
        {
            trascura(p);
        }
    }

    double probabilitaDegrado()
    {
        return 0.2;
    }
}
