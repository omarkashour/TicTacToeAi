import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HumanVsAiMenuUI extends BorderPane {

	Button startBtn = new Button("Start Game");
	Image gameTitleImg = new Image("/images/tictactoe.png");
	Image numberOfRoundsImg = new Image("/images/numberOfRounds.png");
	Image startingPlayerImg = new Image("/images/startingPlayer.png");
	Image chooseSymbolImg = new Image("/images/chooseYourSymbol.png");
	Image difficuiltyImg = new Image("/images/difficulty.png");

	Image humanImg = new Image("/images/human.png");
	Image aiImg = new Image("/images/ai.png");
	Image randomImg = new Image("/images/random.png");

	Image easyImg = new Image("/images/easy.png");
	Image impossibleImg = new Image("/images/impossible.png");

	Button backBtn = new Button("Back To Main Menu");

	
	ComboBox<Integer> numberOfRoundsCB = new ComboBox<Integer>(); // 1 to 100
	ToggleGroup startingPlayerTG = new ToggleGroup();
	RadioButton humanRB = new RadioButton();
	RadioButton aiRB = new RadioButton();
	RadioButton randomRB = new RadioButton();

	ToggleGroup symbolTG = new ToggleGroup();
	RadioButton xRB = new RadioButton();
	RadioButton oRb = new RadioButton();

	String startSoundUrl = getClass().getResource("/audio/start.mp3").toExternalForm();
	Media startSound = new Media(startSoundUrl);
	MediaPlayer startSoundPlayer = new MediaPlayer(startSound);

	String clickSoundUrl = getClass().getResource("/audio/click.wav").toExternalForm();
	Media clickSound = new Media(clickSoundUrl);
	MediaPlayer clickSoundPlayer = new MediaPlayer(clickSound);

	String soundTrackUrl = getClass().getResource("/audio/soundtrack.mp3").toExternalForm();
	Media soundTrack = new Media(soundTrackUrl);
	MediaPlayer soundTrackPlayer = new MediaPlayer(soundTrack);

	String soundTrack2Url = getClass().getResource("/audio/soundTrack2.mp3").toExternalForm();
	Media soundTrack2 = new Media(soundTrack2Url);
	MediaPlayer soundTrack2Player = new MediaPlayer(soundTrack2);

	String soundTrack3Url = getClass().getResource("/audio/soundTrack3.mp3").toExternalForm();
	Media soundTrack3 = new Media(soundTrack3Url);
	MediaPlayer soundTrack3Player = new MediaPlayer(soundTrack3);

	Font customFontRegular = Font.loadFont(HumanVsAiMenuUI.class.getResourceAsStream("/fonts/Gamtex.ttf"), 23);

	Image oImg = new Image("/images/oGraphic.png");
	Image xImg = new Image("/images/xGraphic.png");
	Image developerOmarImg = new Image("/images/byOmar.png");

	Image soundOnImg = new Image("/images/soundOn.png");
	Image soundOffImg = new Image("/images/soundOff.png");
	Image soundtrackImg = new Image("/images/soundtrack.png");

	
	static String difficulty = "Easy";


	public  HumanVsAiMenuUI(Stage primaryStage, Scene scene) {
		MainMenu.soundTrackPlayer.setVolume(0.3);

		System.out.println(customFontRegular);
		GridPane gp = new GridPane();
//		
//		numberOfRoundsL.setStyle("-fx-text-fill: #E52521");
//		startingPlayerL.setStyle("-fx-text-fill: #43B047");
//		chooseSymbolL.setStyle("-fx-text-fill: #FBD000");

		ImageView soundtrackImgView = new ImageView(soundtrackImg);
		ImageView soundOnImgView = new ImageView(soundOnImg);
		ImageView soundOffImgView = new ImageView(soundOffImg);
		soundOnImgView.setPreserveRatio(true);
		soundOnImgView.setFitWidth(40);

		soundOffImgView.setPreserveRatio(true);
		soundOffImgView.setFitWidth(50);

		soundtrackImgView.setPreserveRatio(true);
		soundtrackImgView.setFitWidth(250);

		ImageView gameTitleImgView = new ImageView(gameTitleImg);
		ImageView numberOfRoundsImgView = new ImageView(numberOfRoundsImg);
		ImageView startingPlayerImgView = new ImageView(startingPlayerImg);
		ImageView chooseSymbolImgView = new ImageView(chooseSymbolImg);

		ImageView humanImgView = new ImageView(humanImg);
		ImageView aiImgView = new ImageView(aiImg);
		ImageView randomImgView = new ImageView(randomImg);

		ImageView difficultyImgView = new ImageView(difficuiltyImg);

		ImageView easyImgView = new ImageView(easyImg);
		ImageView impossibleImgView = new ImageView(impossibleImg);

		ToggleGroup difficultyGroup = new ToggleGroup();
		RadioButton easyRB = new RadioButton();
		RadioButton impossibleRB = new RadioButton();

		numberOfRoundsCB.setValue(1);
		
		backBtn.setOnAction(e -> {
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
		});

		
		
		easyImgView.setFitWidth(80);
		easyImgView.setPreserveRatio(true);

		impossibleImgView.setFitWidth(170);
		impossibleImgView.setPreserveRatio(true);

		easyRB.setGraphic(easyImgView);
		impossibleRB.setGraphic(impossibleImgView);

		difficultyGroup.getToggles().addAll(easyRB, impossibleRB);
		difficultyGroup.selectToggle(impossibleRB);

		HBox difficultyHB = new HBox(easyRB,impossibleRB);
		difficultyHB.setSpacing(15);
		
		difficultyImgView.setFitHeight(45);
		difficultyImgView.setPreserveRatio(true);
		GridPane.setHalignment(difficultyImgView, HPos.RIGHT);

		humanImgView.setFitWidth(110);
		humanImgView.setPreserveRatio(true);

		aiImgView.setFitWidth(30);
		aiImgView.setPreserveRatio(true);

		randomImgView.setFitWidth(110);
		randomImgView.setPreserveRatio(true);

		humanRB.setGraphic(humanImgView);
		aiRB.setGraphic(aiImgView);
		randomRB.setGraphic(randomImgView);

		gameTitleImgView.setFitWidth(700);
		gameTitleImgView.setPreserveRatio(true);

		numberOfRoundsImgView.setFitWidth(400);
		numberOfRoundsImgView.setPreserveRatio(true);

		startingPlayerImgView.setFitWidth(300);
		startingPlayerImgView.setPreserveRatio(true);

		chooseSymbolImgView.setFitWidth(400);
		chooseSymbolImgView.setPreserveRatio(true);

		ImageView xView = new ImageView(xImg);
		xView.setFitWidth(60);
		xView.setFitHeight(60);
		ImageView oView = new ImageView(oImg);
		oView.setFitWidth(60);
		oView.setFitHeight(60);

		xRB.setGraphic(xView);
		oRb.setGraphic(oView);

		fillNumberOfRoundsCB();
		startingPlayerTG.getToggles().addAll(humanRB, aiRB, randomRB);
		symbolTG.getToggles().addAll(xRB, oRb);

		ImageView developerOmarImgView = new ImageView(developerOmarImg);
		developerOmarImgView.setPreserveRatio(true);
		developerOmarImgView.setFitWidth(350);

		HBox startingPlayerHB = new HBox(humanRB, aiRB, randomRB);
		startingPlayerHB.setSpacing(15);
		HBox symbolHB = new HBox(xRB, oRb);
		symbolHB.setSpacing(15);
		GridPane.setHalignment(startingPlayerImgView, HPos.RIGHT);

		gp.add(numberOfRoundsImgView, 0, 0);
		gp.add(difficultyImgView, 0, 1);
		gp.add(startingPlayerImgView, 0, 2);
		gp.add(chooseSymbolImgView, 0, 3);
		gp.add(soundtrackImgView, 0, 4);

		gp.add(difficultyHB, 1, 1);
		gp.add(numberOfRoundsCB, 1, 0);
		gp.add(startingPlayerHB, 1, 2);
		gp.add(symbolHB, 1, 3);
		gp.add(soundOnImgView, 1, 4);
		GridPane.setHalignment(soundtrackImgView, HPos.RIGHT);

		soundOnImgView.setOnMouseClicked(e -> {
			MainMenu.soundTrackPlayer.stop();
			MainMenu.soundTrack2Player.stop();
			MainMenu.soundTrack3Player.stop();
			gp.getChildren().remove(soundOnImgView);
			gp.add(soundOffImgView, 1, 4);

		});
		soundOffImgView.setOnMouseClicked(e -> {
			MainMenu.soundTrackPlayer.play();
			gp.getChildren().remove(soundOffImgView);
			gp.add(soundOnImgView, 1, 4);
		});

		VBox topVB = new VBox(gameTitleImgView, developerOmarImgView);
		topVB.setAlignment(Pos.CENTER);
		topVB.setSpacing(5);
		setTop(topVB);
		setMargin(topVB, new Insets(15));
		setAlignment(topVB, Pos.CENTER);
		
		HBox bottomHB = new HBox(backBtn,startBtn);
		bottomHB.setAlignment(Pos.CENTER);
		bottomHB.setSpacing(15);
		setBottom(bottomHB);
		setMargin(bottomHB, new Insets(15));
		setStyle("-fx-background-color:  #049CD8");

		setCenter(gp);
		setMargin(gp, new Insets(15));

		gp.setVgap(15);
		gp.setHgap(15);
		gp.setAlignment(Pos.CENTER);
		startBtn.setOnAction(e -> {
			startSoundPlayer.stop();
			startSoundPlayer.play();
			int numberOfRounds = numberOfRoundsCB.getValue();
			String startingPlayer = "";
			RadioButton startingPlayerToggle = (RadioButton) startingPlayerTG.getSelectedToggle();
			if (startingPlayerToggle.equals(aiRB)) {
				startingPlayer = "Ai";
			} else if (startingPlayerToggle.equals(humanRB)) {
				startingPlayer = "Human";
			} else {
				startingPlayer = "Random";
			}

			RadioButton symbolToggle = (RadioButton) symbolTG.getSelectedToggle();
			char playerSymbol = ' ';
			if (symbolToggle.equals(xRB)) {
				playerSymbol = 'X';
			} else if (symbolToggle.equals(oRb)) {
				playerSymbol = 'O';
			}

			
			RadioButton difficultyToggle = (RadioButton) difficultyGroup.getSelectedToggle();
			if(difficultyToggle.equals(easyRB)) {
				difficulty = "Easy";
			}
			else {
				difficulty = "Impossible";
			}
			HumanVsAiGameUI gameUI = new HumanVsAiGameUI(primaryStage, scene, numberOfRounds, startingPlayer, playerSymbol);
			Scene gameScene = new Scene(gameUI, 1250, 700); // PC Size
			
			gameScene.getStylesheets().add("style.css");
			primaryStage.setScene(gameScene);
			primaryStage.centerOnScreen();
		
		});
		
		startingPlayerTG.selectToggle(randomRB);
		symbolTG.selectToggle(xRB);
		
		Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("Algorithms Project 4 - Omar Kashour - 1210082");
		primaryStage.show();

		// sounds
		numberOfRoundsCB.setOnMouseClicked(e -> {
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
		aiRB.setOnMouseClicked(e -> {
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
		humanRB.setOnMouseClicked(e -> {
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
		randomRB.setOnMouseClicked(e -> {
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
		oRb.setOnMouseClicked(e -> {
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
		xRB.setOnMouseClicked(e -> {
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
		
		easyRB.setOnMouseClicked(e->{
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
		impossibleRB.setOnMouseClicked(e->{
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
	}

	private void fillNumberOfRoundsCB() {
		numberOfRoundsCB.getItems().clear();
		for (int i = 1; i <= 30; i++) {
			numberOfRoundsCB.getItems().add(i);
		}
	}

}
