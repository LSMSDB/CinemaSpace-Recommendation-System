package interfaceFXML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.CastMember;
import main.java.CinemaSpaceArchive;
import main.java.CrewMember;
import main.java.Film;

public class FXMLControllerAdmin {
	//Switch page controller
	private Parent root;
	private Stage stage;
	
	//FXML elements
	@FXML private TextField fileNameField;
	@FXML private Button addFilmsButton;
	@FXML private Button uploadFileButton;
	@FXML private Button confirmButton;
	
	public FXMLControllerAdmin() {
		super();
	}
	
	@FXML protected void initialize() {
	}
	
	@FXML protected void handleHomeButtonAction (ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("home.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@FXML protected void handleHighestRatedButtonAction (ActionEvent event) {
		try {
			FXMLLoader load = new FXMLLoader(getClass().getResource("home.fxml"));
			root = load.load();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			FXMLControllerHome controller = load.<FXMLControllerHome>getController();
			controller.highestRated();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@FXML protected void handleMostPopularButtonAction (ActionEvent event) {
		try {
			FXMLLoader load = new FXMLLoader(getClass().getResource("home.fxml"));
			root = load.load();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			FXMLControllerHome controller = load.<FXMLControllerHome>getController();
			controller.mostPopular();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@FXML protected void handleAddFilmsButtonAction (ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("admin.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@FXML protected void handleDisconnectionButtonAction (ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("connection.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@FXML protected void handleUploadFileButtonAction (ActionEvent event) {
		FileChooser chooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
		chooser.getExtensionFilters().add(extFilter);
		File file = chooser.showOpenDialog(stage);
		fileNameField.setText(file.getAbsolutePath());
		
	}

	@FXML protected void handleConfirmAddFilmsButtonAction (ActionEvent event) {
		List<Film> films = new ArrayList<Film>();
        
		try {
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode node = mapper.readTree(new File(fileNameField.getText()));
	        Iterator<JsonNode> nodeFilms = node.iterator();
	        while (nodeFilms.hasNext()) {
	        	JsonNode film = nodeFilms.next();	        
	        	
	        	double budget = film.get("budget").doubleValue();
	        	
	        	List<String> genres = new ArrayList<String>();
            	Iterator<JsonNode> genresElements = film.get("genres").iterator();
            	while(genresElements.hasNext()) {
            		genres.add(genresElements.next().textValue());
            	}
            	
            	String homePage = film.get("homepage").textValue();
            	String originalLanguage = film.get("original_language").textValue();
            	String originalTitle = film.get("original_title").textValue();
            	String overview = film.get("overview").textValue();
            	String posterPath = film.get("poster_path").textValue();
            	
            	List<String> productionCompanies = new ArrayList<String>();
            	Iterator<JsonNode> pcElements = film.get("production_companies").iterator();
            	while(pcElements.hasNext()) {
            		productionCompanies.add(pcElements.next().textValue());
            	}
            	
            	List<String> productionCountries = new ArrayList<String>();
            	Iterator<JsonNode> pctElements = film.get("production_countries").iterator();
            	while(pctElements.hasNext()) {
            		productionCountries.add(pctElements.next().textValue());
            	}
            	
            	String releaseDate = film.get("release_date").textValue();
            	double revenue = film.get("revenue").doubleValue();
            	double runtime = film.get("runtime").doubleValue();
            	
            	List<String> spokenLanguages = new ArrayList<String>();
            	Iterator<JsonNode> slElements = film.get("spoken_languages").iterator();
            	while(slElements.hasNext()) {
            		spokenLanguages.add(slElements.next().textValue());
            	}
            	
            	String status = film.get("status").textValue();
            	String tagline = film.get("tagline").textValue();
            	String title = film.get("title").textValue();
            	
            	int numberOfVisits = 0;
            	
            	List<String> keywords = new ArrayList<String>();
            	Iterator<JsonNode> keywordElements = film.get("keywords").iterator();
            	while(keywordElements.hasNext()) {
            		keywords.add(keywordElements.next().textValue());
            	}
            	
            	double averageRating = 0;
            	int numberOfRatings = 0;
            	
            	List<CastMember> cast = new ArrayList<CastMember>();
            	Iterator<JsonNode> castElements = film.get("cast").iterator();
            	while(castElements.hasNext()) {
            		JsonNode member = castElements.next();
	                String character = member.get("character").textValue();
	                String name = member.get("name").textValue();
	                int order = member.get("order").intValue();
	                String profilePath = member.get("profile_path").textValue();
	                cast.add(new CastMember(character, name, order, profilePath));
            	}
            	
            	List<CrewMember> crew = new ArrayList<CrewMember>();
            	Iterator<JsonNode> crewElements = film.get("crew").iterator();
            	while(crewElements.hasNext()) {
            		JsonNode member = crewElements.next();
            		String department = member.get("department").textValue();
	                String job = member.get("job").textValue();
	                String name = member.get("name").textValue();
	                String profilePath = member.get("profile_path").textValue();
	                crew.add(new CrewMember(department, job, name, profilePath));
            	}
            	films.add(new Film(budget, genres, homePage, originalLanguage, originalTitle, overview, posterPath, 
            			productionCompanies, productionCountries, releaseDate, revenue, runtime, spokenLanguages, 
            			status, tagline, title, numberOfVisits, keywords, cast, crew, averageRating, numberOfRatings));
	        }
	        CinemaSpaceArchive.addFilms(films);
	        
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}

}
