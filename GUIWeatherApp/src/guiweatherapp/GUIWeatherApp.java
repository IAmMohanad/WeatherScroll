/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guiweatherapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.*;

//import javafx.scene.control.Slider;
/**
 *
 * @author mas34
 */
public class GUIWeatherApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("WeatherScroll");
        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, 320, 480));
        Label locationLabel = new Label("City: ");
        locationLabel.setTranslateY(100);

        root.getChildren().add(locationLabel);
        
        
        /*Slider slider = new Slider(0, 1, 0.5); 
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(0.25f);
        slider.setBlockIncrement(0.1f);
        root.getChildren().add(slider);
        
        */
         primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
