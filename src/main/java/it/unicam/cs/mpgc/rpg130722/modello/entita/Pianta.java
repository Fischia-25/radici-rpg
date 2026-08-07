package it.unicam.cs.mpgc.rpg130722.modello.entita;

import it.unicam.cs.mpgc.rpg130722.modello.stato.StatoPianta;
import it.unicam.cs.mpgc.rpg130722.modello.stato.StatoAppassita;

// Rappresenta una pianta del giardino che è collegata a un ricordo
public class Pianta {

    private final String nome;
    private StatoPianta stato;
    private final Ricordo ricordoCollegato;

    /**
     * Creo una nuova pianta sempre nello stato iniziale di appassita
     * rappresenta una pianta appena scoperta
     * quindi il giocatore non se ne è ancora preso cura
     * @param nome il nome che identifica la pianta
     * @param ricordoCollegato il ricordo legato a questa pianta
     */
    public Pianta(String nome, Ricordo ricordoCollegato)
    {
        this.nome = nome;
        this.ricordoCollegato = ricordoCollegato;
        this.stato = new StatoAppassita();
    }
    // Annaffio la pianta, delegando il comportamento allo stato
    public void annaffia()
    {
        stato.annaffia(this);
    }
    // Trascuro la pianta, delegando il comportamento allo stato
    public void trascura()
    {
        stato.trascura(this);
    }
    // Purifico la pianta, delegando il comportamento allo stato
    public void purifica()
    {
        stato.purifica(this);
    }

    /**
     * Cambia lo stato corrente della pianta
     * @param stato il nuovo stato in cui passerà la pianta
     */
    public void setStato(StatoPianta stato)
    {
        this.stato = stato;
    }

    /**
     * Recupero lo stato in cui si trova ora la pianta
     * @return stato.getDescrizione()
     */
    public String getDescrizioneStato()
    {
        return stato.getDescrizione();
    }

    /**
     * Recupero il nome della pianta
     * @return nome
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * Recupero il ricordo collegato alla pianta
     * @return ricordoCollegato
     */
    public Ricordo getRicordoCollegato()
    {
        return ricordoCollegato;
    }

}
