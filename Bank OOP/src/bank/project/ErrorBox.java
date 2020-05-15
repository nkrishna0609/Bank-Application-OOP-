package bank.project;

import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Modality;

public class ErrorBox {
        public static void createErrorBox(String s){
            Stage stage;
            stage = new Stage();
            stage.setTitle("Error!");
            
            GridPane gridError = new GridPane();
            
            Scene sceneError = new Scene(gridError, 350, 150);
            
            gridError.setPadding(new Insets(20,20,20,20));
        gridError.setVgap(10);
        gridError.setHgap(10);
            
            Button exit = new Button("OK");
            GridPane.setConstraints(exit, 4,1);
            exit.setOnAction(e -> stage.close());
            
            Label errorMessage = new Label(s);
            GridPane.setConstraints(errorMessage, 4,0);
            
            gridError.getChildren().addAll(exit, errorMessage);
            stage.setScene(sceneError);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
        }
}
