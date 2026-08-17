package it.unicam.cs.mpgc.rpg130722.persistenza;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Giardino;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileGiardinoRepository implements GiardinoRepository{

    private final Path percorsoFile;

    public FileGiardinoRepository(Path percorsoFile)
    {
        this.percorsoFile = percorsoFile;
    }

    @Override
    public void salva(Giardino giardino) throws IOException
    {
        try (ObjectOutputStream out = new ObjectOutputStream(
                Files.newOutputStream(percorsoFile)))
        {
            out.writeObject(giardino);
        }
    }

    @Override
    public Optional<Giardino> carica() throws IOException
    {
        if(!Files.exists(percorsoFile))
        {
            return Optional.empty();
        }
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(percorsoFile)))
        {
            Giardino giardino = (Giardino) in.readObject();
            return Optional.of(giardino);
        }   catch (ClassNotFoundException e)
        {
            throw new IOException("Dati salvati non compatibili", e);
        }
    }
}
