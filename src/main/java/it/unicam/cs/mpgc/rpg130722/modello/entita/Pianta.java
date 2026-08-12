package it.unicam.cs.mpgc.rpg130722.modello.entita;

import it.unicam.cs.mpgc.rpg130722.modello.stato.StatoPianta;
import it.unicam.cs.mpgc.rpg130722.modello.stato.StatoAppassita;

// Rappresenta una pianta del giardino che è collegata a un ricordo
public class Pianta {

    private final String nome;
    private StatoPianta stato;
    private final Ricordo ricordoCollegato;

    /**
     * Creo una nuova pianta sempre nello stato iniziale di appassita.
     * Rappresenta una pianta appena scoperta, quindi il giocatore non se ne è ancora preso cura
     */
    public Pianta(String nome, Ricordo ricordoCollegato)
    {
        this.nome = nome;
        this.ricordoCollegato = ricordoCollegato;
        this.stato = new StatoAppassita();
    }

    public void annaffia()
    {
        stato.annaffia(this);
    }

    public void trascura()
    {
        stato.trascura(this);
    }

    public void purifica()
    {
        stato.purifica(this);
    }

    public void setStato(StatoPianta stato)
    {
        this.stato = stato;
    }

    public String getDescrizioneStato()
    {
        return stato.getDescrizione();
    }

    public String getNome()
    {
        return nome;
    }

    public Ricordo getRicordoCollegato()
    {
        return ricordoCollegato;
    }

}
