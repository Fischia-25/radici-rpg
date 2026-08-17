package it.unicam.cs.mpgc.rpg130722.modello.entita;

import java.io.Serializable;

// Rappresenta il ricordo collegato alla pianta nel giardino
public class Ricordo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String nomeDefunto;
    private final String storia;

    // Creo un nuovo ricordo che sarà collegato a una sola pianta
    public Ricordo(String nomeDefunto, String storia)
    {
        this.nomeDefunto = nomeDefunto;
        this.storia = storia;
    }

    public String getNomeDefunto()
    {
        return nomeDefunto;
    }

    public String getStoria()
    {
        return storia;
    }

}
