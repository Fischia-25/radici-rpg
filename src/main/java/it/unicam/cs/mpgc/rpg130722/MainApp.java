package it.unicam.cs.mpgc.rpg130722;

import it.unicam.cs.mpgc.rpg130722.service.GiardinoService;
import it.unicam.cs.mpgc.rpg130722.persistenza.FileGiardinoRepository;
import it.unicam.cs.mpgc.rpg130722.persistenza.GiardinoRepository;
import it.unicam.cs.mpgc.rpg130722.ui.controllers.GiardinoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        GiardinoRepository repository = new FileGiardinoRepository(Path.of("giardino.dat"));
        GiardinoService service = new GiardinoService(repository);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/it/unicam/cs/mpgc/rpg130722/ui/fxml/giardino-view.fxml")
        );
        Parent root = loader.load();

        GiardinoController controller = loader.getController();
        controller.impostaService(service);

        Scene scene = new Scene(root, 640, 480);
        primaryStage.setTitle("Radici");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            try {
                service.salva();
            }   catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}