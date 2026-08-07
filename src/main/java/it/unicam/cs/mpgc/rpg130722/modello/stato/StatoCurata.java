package it.unicam.cs.mpgc.rpg130722.modello.stato;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;

/**
 * Rappresenta lo stato ultimo della pianta, il completamento della storia.
 * Nessun metodo ha effetti sulla pianta
 */
public class StatoCurata implements StatoPianta{

    @Override
    public void annaffia(Pianta pianta){}
    public void trascura(Pianta pianta){}
    public void purifica(Pianta pianta){}

    @Override
    public String getDescrizione()
    {
        return "Curata";
    }
}
