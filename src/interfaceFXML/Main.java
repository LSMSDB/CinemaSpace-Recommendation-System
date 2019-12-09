package interfaceFXML;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {	
	@Override
	public void start(Stage stage){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("connection.fxml"));
			Scene sceneConnection = new Scene(root);
			stage.setTitle("CinemaSpace");
			stage.setScene(sceneConnection);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

