/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Trump
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InventoryManagementApplication extends Application{

        public void start(Stage stage) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("minecraftApp.fxml")));

            Scene scene = new Scene(root);

            stage.setResizable(false);
            //Code for the application displaying
            stage.setTitle("Minecraft Inventory Manager");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
}
