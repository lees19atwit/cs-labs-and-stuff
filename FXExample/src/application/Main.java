package application;
	
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;


public class Main extends Application implements Initializable{
	
	@FXML
	Button myButton; 
	@FXML
	Circle c1; 
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = new FXMLLoader.load(getClass().getClassLoader().getResource("application/Main.fxml")); 
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		myButton.setOnAction(e -> System.out.printf("Hello%n"));
		//
	}
}
