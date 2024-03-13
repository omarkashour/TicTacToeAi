import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu extends Application {

	Image gameTitleImg = new Image("/images/tictactoe.png");
	Image selectGameModeImg = new Image("/images/selectGameMode.png");

	Image humanVsHumanImg = new Image("/images/humanVShuman.png");
	Image humanVsAiImg = new Image("/images/humanVSai.png");

	static String soundTrackUrl = MainMenu.class.getResource("/audio/soundtrack.mp3").toExternalForm();
	static Media soundTrack = new Media(soundTrackUrl);
	static MediaPlayer soundTrackPlayer = new MediaPlayer(soundTrack);

	static String soundTrack2Url = MainMenu.class.getResource("/audio/soundTrack2.mp3").toExternalForm();
	static Media soundTrack2 = new Media(soundTrack2Url);
	static MediaPlayer soundTrack2Player = new MediaPlayer(soundTrack2);

	static String soundTrack3Url = MainMenu.class.getResource("/audio/soundTrack3.mp3").toExternalForm();
	static Media soundTrack3 = new Media(soundTrack3Url);
	static MediaPlayer soundTrack3Player = new MediaPlayer(soundTrack3);

	static String clickSoundUrl = MainMenu.class.getResource("/audio/click.wav").toExternalForm();
	static Media clickSound = new Media(clickSoundUrl);
	static MediaPlayer clickSoundPlayer = new MediaPlayer(clickSound);

	Font customFontRegular = Font.loadFont(HumanVsHumanMenuUI.class.getResourceAsStream("/fonts/Gamtex.ttf"), 23);

	Image developerOmarImg = new Image("/images/byOmar.png");

	Image soundOnImg = new Image("/images/soundOn.png");
	Image soundOffImg = new Image("/images/soundOff.png");

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		soundTrackPlayer.setVolume(0.3);
		soundTrackPlayer.play();
		System.out.println(soundTrackUrl);
		ImageView humanVsHumanImgView = new ImageView(humanVsHumanImg);
		ImageView humanVsAiImgView = new ImageView(humanVsAiImg);
		humanVsHumanImgView.setPreserveRatio(true);
		humanVsHumanImgView.setFitWidth(510);
		humanVsAiImgView.setPreserveRatio(true);
		humanVsAiImgView.setFitWidth(500);
		Runnable runSecondTrack = new Runnable() {
			@Override
			public void run() {
				soundTrack2Player.stop();
				soundTrack2Player.play();

			}

		};

		Runnable runFirstTrack = new Runnable() {
			@Override
			public void run() {
				soundTrackPlayer.stop();
				soundTrackPlayer.play();

			}

		};

		Runnable runThirdTrack = new Runnable() {
			public void run() {
				soundTrack3Player.setVolume(0.5);
				soundTrack3Player.stop();
				soundTrack3Player.play();

			}
		};
		MainMenu.soundTrackPlayer.setVolume(0.3);
		soundTrackPlayer.setOnEndOfMedia(runSecondTrack);
		soundTrack2Player.setOnEndOfMedia(runThirdTrack);
		soundTrack3Player.setOnEndOfMedia(runFirstTrack);

		BorderPane bp = new BorderPane();

		StackPane sp1 = new StackPane();
		StackPane sp2 = new StackPane();

		sp1.getChildren().add(humanVsAiImgView);
		sp2.getChildren().add(humanVsHumanImgView);
		sp1.setMaxHeight(humanVsAiImgView.getFitHeight());
		sp2.setMaxHeight(humanVsHumanImgView.getFitHeight());
		sp1.setStyle(
				"-fx-border-color: #FBD000;-fx-background-color: #43B047; -fx-border-width: 5px; -fx-border-radius: 10px;");
		sp2.setStyle(
				"-fx-border-color: #FBD000;-fx-background-color: #43B047; -fx-border-width: 5px; -fx-border-radius: 10px;");

		HBox centerHB = new HBox(sp1, sp2);
		centerHB.setAlignment(Pos.CENTER);
		centerHB.setSpacing(50);
		ImageView soundOnImgView = new ImageView(soundOnImg);
		ImageView soundOffImgView = new ImageView(soundOffImg);
		soundOnImgView.setPreserveRatio(true);
		soundOnImgView.setFitWidth(40);

		soundOffImgView.setPreserveRatio(true);
		soundOffImgView.setFitWidth(50);

		ImageView gameTitleImgView = new ImageView(gameTitleImg);

		gameTitleImgView.setFitWidth(700);
		gameTitleImgView.setPreserveRatio(true);

		ImageView developerOmarImgView = new ImageView(developerOmarImg);
		developerOmarImgView.setPreserveRatio(true);
		developerOmarImgView.setFitWidth(350);

		ImageView selectGameModeImgView = new ImageView(selectGameModeImg);
		selectGameModeImgView.setPreserveRatio(true);
		selectGameModeImgView.setFitWidth(600);

		VBox centerVB = new VBox(selectGameModeImgView, centerHB);
		centerVB.setAlignment(Pos.CENTER);
		centerVB.setSpacing(15);

		VBox topVB = new VBox(gameTitleImgView, developerOmarImgView);
		topVB.setAlignment(Pos.CENTER);
		topVB.setSpacing(5);
		bp.setTop(topVB);
		bp.setMargin(topVB, new Insets(15));
		bp.setAlignment(topVB, Pos.CENTER);

		bp.setStyle("-fx-background-color:  #049CD8");

		Scene scene = new Scene(bp, 1100, 650);

		Rectangle r1 = new Rectangle(510, 250);
		r1.setFill(Color.rgb(0, 0, 0, 0)); // RGB color with 50% transparency
		Rectangle r2 = new Rectangle(500, 250);
		r2.setFill(Color.rgb(0, 0, 0, 0));

		sp1.getChildren().add(r1);
		sp2.getChildren().add(r2);

		r1.setMouseTransparent(false); // it still clicks even if its transparent
		r2.setMouseTransparent(false);

		r1.setOnMouseClicked(e -> {

			clickSoundPlayer.stop();
			clickSoundPlayer.play();
			HumanVsAiMenuUI menu = new HumanVsAiMenuUI(primaryStage, scene);
			Scene menuScene = new Scene(menu, 1100, 650);
			menuScene.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
			menuScene.getStylesheets().add("style.css");
			primaryStage.setScene(menuScene);
		});

		r2.setOnMouseClicked(e -> {

			clickSoundPlayer.stop();
			clickSoundPlayer.play();
			HumanVsHumanMenuUI menu = new HumanVsHumanMenuUI(primaryStage, scene);

			Scene menuScene = new Scene(menu, 1100, 650);
			menuScene.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
			menuScene.getStylesheets().add("style.css");
			primaryStage.setScene(menuScene);
		});

		bp.setCenter(centerVB);
		Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
		scene.getStylesheets().add("style.css");
		Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("Algorithms Project 4 - Omar Kashour - 1210082");
		primaryStage.show();

	}

}
