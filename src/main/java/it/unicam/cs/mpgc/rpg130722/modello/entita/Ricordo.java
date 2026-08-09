package it.unicam.cs.mpgc.rpg130722.modello.entita;


// Rappresenta il ricordo collegato alla pianta nel giardino
public class Ricordo {

    private final String nomeDefunto;
    private final String storia;

    /**
     * Creo un nuovo ricordo, che sarà collegato a una sola pianta
     * @param nomeDefunto è il nome della persona a cui il ricordo è legato
     * @param storia è il testo narrativo associato al ricordo
     */
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
