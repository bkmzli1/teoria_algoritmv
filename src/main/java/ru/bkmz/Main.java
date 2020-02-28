package ru.bkmz;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public static final Logger logger = LogManager.getLogger();
    @Override
    public void start(Stage stage) {
        logger.info("start loader FXML");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/main.fxml")));
        try {
            loader.load();
        } catch (IOException e) {
            logger.warn("load fxml", e);
        }

        Parent root = loader.getRoot();
        logger.info("stop loader FXML");
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("");

        InputStream inputStream = ClassLoader.class.getResourceAsStream("/img/icon.png");
        try {
            Image image = new Image(inputStream);
            stage.getIcons().add(image);
        } catch (NullPointerException e) {
            logger.warn("img null");
        }
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });

        stage.show();


    }
}
