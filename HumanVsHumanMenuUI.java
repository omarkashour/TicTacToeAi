import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HumanVsHumanMenuUI extends BorderPane {

	Button startBtn = new Button("Start Game");
	Image gameTitleImg = new Image("/images/tictactoe.png");
	Image numberOfRoundsImg = new Image("/images/numberOfRounds.png");
	Image startingPlayerImg = new Image("/images/startingPlayer.png");
	Image player1Img = new Image("/images/player1.png");
	Image player2Img = new Image("/images/player2.png");
	Image choosePlayer1SymbolImg = new Image("/images/choosePlayer1Symbol.png");
	Image enterPlayer1NameImg = new Image("/images/enterPlayer1Name.png");
	Image enterPlayer2NameImg = new Image("/images/enterPlayer2Name.png");

	ComboBox<Integer> numberOfRoundsCB = new ComboBox<Integer>(); // 1 to 100
	ToggleGroup startingPlayerTG = new ToggleGroup();

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

	Font customFontRegular = Font.loadFont(HumanVsHumanMenuUI.class.getResourceAsStream("/fonts/Gamtex.ttf"), 23);

	Image oImg = new Image("/images/oGraphic.png");
	Image xImg = new Image("/images/xGraphic.png");
	Image developerOmarImg = new Image("/images/byOmar.png");

	Image soundOnImg = new Image("/images/soundOn.png");
	Image soundOffImg = new Image("/images/soundOff.png");
	Image soundtrackImg = new Image("/images/soundtrack.png");

	TextField player1NameTF = new TextField("Player1");
	TextField player2NameTF = new TextField("Player2");

	static String difficulty = "Easy";

	Button backBtn = new Button("Back To Main Menu");
	
	Image randomImg = new Image("/images/random.png");
	RadioButton randomRB = new RadioButton();
	
	public  HumanVsHumanMenuUI(Stage primaryStage, Scene scene) {
		MainMenu.soundTrackPlayer.setVolume(0.3);

		GridPane gp = new GridPane();
		
		ImageView randomImgView = new ImageView(randomImg);
		randomImgView.setFitWidth(115);
		randomImgView.setPreserveRatio(true);
		
		ImageView player1ImgView = new ImageView(player1Img);
		ImageView player2ImgView = new ImageView(player2Img);
		ImageView choosePlayer1SymbolImgView = new ImageView(choosePlayer1SymbolImg);
		ImageView enterPlayer1NameImgView = new ImageView(enterPlayer1NameImg);
		ImageView enterPlayer2NameImgView = new ImageView(enterPlayer2NameImg);
		player1ImgView.setPreserveRatio(true);
		player2ImgView.setPreserveRatio(true);
		choosePlayer1SymbolImgView.setPreserveRatio(true);
		enterPlayer1NameImgView.setPreserveRatio(true);
		enterPlayer2NameImgView.setPreserveRatio(true);
		player1ImgView.setFitWidth(120);
		player2ImgView.setFitWidth(120);
		choosePlayer1SymbolImgView.setFitWidth(430);
		enterPlayer1NameImgView.setFitWidth(420);
		enterPlayer2NameImgView.setFitWidth(420);

		GridPane.setHalignment(player1ImgView, HPos.RIGHT);
		GridPane.setHalignment(player2ImgView, HPos.RIGHT);
		GridPane.setHalignment(choosePlayer1SymbolImgView, HPos.RIGHT);
		GridPane.setHalignment(enterPlayer1NameImgView, HPos.RIGHT);
		GridPane.setHalignment(enterPlayer2NameImgView, HPos.RIGHT);

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

		numberOfRoundsCB.setValue(1);

		gameTitleImgView.setFitWidth(700);
		gameTitleImgView.setPreserveRatio(true);

		numberOfRoundsImgView.setFitWidth(400);
		numberOfRoundsImgView.setPreserveRatio(true);

		startingPlayerImgView.setFitWidth(300);
		startingPlayerImgView.setPreserveRatio(true);

		ImageView xView = new ImageView(xImg);
		xView.setFitWidth(60);
		xView.setFitHeight(60);
		ImageView oView = new ImageView(oImg);
		oView.setFitWidth(60);
		oView.setFitHeight(60);

		xRB.setGraphic(xView);
		oRb.setGraphic(oView);

		fillNumberOfRoundsCB();

		symbolTG.getToggles().addAll(xRB, oRb);

		ImageView developerOmarImgView = new ImageView(developerOmarImg);
		developerOmarImgView.setPreserveRatio(true);
		developerOmarImgView.setFitWidth(350);

		HBox symbolHB = new HBox(xRB, oRb);
		symbolHB.setSpacing(15);
		GridPane.setHalignment(startingPlayerImgView, HPos.RIGHT);

		RadioButton player1RB = new RadioButton();
		RadioButton player2RB = new RadioButton();
		player1RB.setGraphic(player1ImgView);
		player2RB.setGraphic(player2ImgView);

		randomRB.setGraphic(randomImgView);

		
		startingPlayerTG.getToggles().addAll(player1RB, player2RB,randomRB);
		startingPlayerTG.selectToggle(player1RB);
		HBox startingHB = new HBox(player1RB, player2RB,randomRB);
		startingHB.setSpacing(15);

		gp.add(numberOfRoundsImgView, 0, 0);
		gp.add(enterPlayer1NameImgView, 0, 1);
		gp.add(enterPlayer2NameImgView, 0, 2);
		gp.add(startingPlayerImgView, 0, 3);
		gp.add(choosePlayer1SymbolImgView, 0, 4);
		gp.add(soundtrackImgView, 0, 5);

		gp.add(startingHB, 1, 3);
		gp.add(player1NameTF, 1, 1);
		gp.add(player2NameTF, 1, 2);
		gp.add(numberOfRoundsCB, 1, 0);
		gp.add(symbolHB, 1, 4);
		gp.add(soundOnImgView, 1, 5);
		GridPane.setHalignment(soundtrackImgView, HPos.RIGHT);
		GridPane.setHalignment(numberOfRoundsImgView, HPos.RIGHT);

		soundOnImgView.setOnMouseClicked(e -> {
			MainMenu.soundTrackPlayer.stop();
			MainMenu.soundTrack2Player.stop();
			MainMenu.soundTrack3Player.stop();
			gp.getChildren().remove(soundOnImgView);
			gp.add(soundOffImgView, 1, 5);

		});
		soundOffImgView.setOnMouseClicked(e -> {
			MainMenu.soundTrackPlayer.play();
			gp.getChildren().remove(soundOffImgView);
			gp.add(soundOnImgView, 1, 5);
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

			if (startingPlayerToggle.equals(player1RB)) {
				startingPlayer = "Player1";
			} else if (startingPlayerToggle.equals(player2RB)) {
				startingPlayer = "Player2";
			} else {
				startingPlayer = "Random";
			}
			
			System.out.println(startingPlayer);
			
			RadioButton symbolToggle = (RadioButton) symbolTG.getSelectedToggle();
			char playerSymbol = ' ';
			if (symbolToggle.equals(xRB)) {
				playerSymbol = 'X';
			} else if (symbolToggle.equals(oRb)) {
				playerSymbol = 'O';
			}
			
			String player1Name = player1NameTF.getText().trim();
			String player2Name = player2NameTF.getText().trim();
			
			if(player1Name.length() == 0) {
				player1Name = "Player1";
			}
			
			if(player2Name.length() == 0) {
				player2Name = "Player2";
			}
			
			HumanVsHumanGameUI ui = new HumanVsHumanGameUI(primaryStage,scene,numberOfRounds,startingPlayer,playerSymbol,player1Name,player2Name);
			Scene gameScene = new Scene(ui, 1250, 700); // PC Size

			gameScene.getStylesheets().add("style.css");
			primaryStage.setScene(gameScene);
			primaryStage.centerOnScreen();
		});

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
		oRb.setOnMouseClicked(e -> {
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
		xRB.setOnMouseClicked(e -> {
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
		player1RB.setOnMouseClicked(e -> {
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
		player2RB.setOnMouseClicked(e -> {
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
		
		randomRB.setOnMouseClicked(e -> {
			clickSoundPlayer.stop();
			clickSoundPlayer.play();
		});
			
		
		backBtn.setOnAction(e -> {
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
		});
	}

	private void fillNumberOfRoundsCB() {
		numberOfRoundsCB.getItems().clear();
		for (int i = 1; i <= 30; i++) {
			numberOfRoundsCB.getItems().add(i);
		}
	}

}
