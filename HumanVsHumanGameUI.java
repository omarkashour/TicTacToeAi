import java.util.Random;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HumanVsHumanGameUI extends BorderPane {
	static int[][] mainBoard = new int[3][3];
	static State state = new State(mainBoard);

	static boolean firstMoveDone = false;
	Image oImg = new Image("/images/oGraphic.png");
	Image xImg = new Image("/images/xGraphic.png");

	Image player1Img = new Image("/images/player1Character.png");
	Image player2Img = new Image("/images/player2Character.png");
	Image currentTurnImg = new Image("/images/currentTurn.png");

	Image winsImg = new Image("/images/nameWins.png");

	Image wonImg = new Image("/images/nameHasWon.png");

	Image drawImg = new Image("/images/itsADraw2.png");

	Image roundImg = new Image("/images/round.png");
	Image ofImg = new Image("/images/of.png");

	static int player1Turn = 1;
	static int currentTurn = 1;
	
	
	static int numberOfRounds = 0;
	static String startingPlayer;
	static char player1Symbol;

	static int player1Wins = 0;
	static int player2Wins = 0;

	static int draw = 0;
	static TicTacToeGame game = new TicTacToeGame();

	static int currentRound = 1;

	Button backBtn = new Button("Back To Mode Menu");

	Button nextRoundBtn = new Button("Next Round");

	boolean random = false;

	String clickSoundUrl = getClass().getResource("/audio/clickSound.wav").toExternalForm();
	Media clickSound = new Media(clickSoundUrl);
	MediaPlayer clickSoundPlayer = new MediaPlayer(clickSound);

	Label player1WinsL = new Label("0");
	Label player2WinsL = new Label("0");
	GridPane player1GP = new GridPane();
	VBox leftVB = new VBox();
	ImageView player1ImgView = new ImageView(player1Img);
	ImageView player1WinsView = new ImageView(winsImg);
	ImageView player2WinsView = new ImageView(winsImg);
	ImageView drawImgView = new ImageView(drawImg);
	ImageView wonImgView = new ImageView(wonImg);

	GridPane player2GP = new GridPane();
	ImageView player2ImgView = new ImageView(player2Img);
	GridPane player1StatsGP = new GridPane();
	GridPane player2StatsGP = new GridPane();
	VBox rightVB = new VBox();

	ImageView roundImgView = new ImageView(roundImg);
	ImageView ofImgView = new ImageView(ofImg);

	VBox topVB = new VBox();
	HBox bottomHB = new HBox();

	Label currentRoundL = new Label("");
	Label numberOfRoundsL = new Label("");
	TextArea statsTa = new TextArea();

	String player1Name = "";
	String player2Name = "";

	Label player1NameL = new Label();
	Label player2NameL = new Label();

	Label currentPlayer = new Label();

	ImageView currentTurnImgView = new ImageView(currentTurnImg);
	String drawSoundUrl = getClass().getResource("/audio/drawSoundEffect.mp3").toExternalForm();
	Media drawSound = new Media(drawSoundUrl);
	MediaPlayer drawSoundPlayer = new MediaPlayer(drawSound);

	public HumanVsHumanGameUI(Stage primaryStage, Scene scene, int numberOfRounds, String startingPlayer,
			char player1Symbol, String player1Name, String player2Name) {
		this.numberOfRounds = numberOfRounds;
		this.player1Symbol = player1Symbol;
		this.player1Name = player1Name;
		this.player2Name = player2Name;
		player1NameL.setText(player1Name);
		player2NameL.setText(player2Name);

		player1NameL.setStyle("-fx-font-size: 50px;   -fx-text-fill: #E52521;");
		player2NameL.setStyle("-fx-font-size: 50px;   -fx-text-fill: #E52521;");
		currentPlayer.setStyle("-fx-font-size: 50px;   -fx-text-fill: #E52521;");
		
		setStyle("-fx-background-color:  #049CD8");
	
		
		if(startingPlayer.equals("Player1")) {
			player1Turn = 1;
			currentTurn = 1;
			currentPlayer.setText(player1Name + "'s");

		}
		else if(startingPlayer.equals("Player2")) {
			player1Turn = -1;
			currentTurn = -1;
			currentPlayer.setText(player2Name + "'s");
		}
		else if (startingPlayer.equals("Random")) {
			Random rand = new Random();
			boolean player1 = rand.nextBoolean();
			if(player1) {
				startingPlayer = "Player1";
			}
			else {
				startingPlayer = "Player2";
			}
			random = true;
		}
		currentPlayer.setText(startingPlayer + "'s");
		this.startingPlayer = startingPlayer;

		GridPane gp = new GridPane();
		drawBoard(gp, mainBoard);

		currentTurnImgView.setPreserveRatio(true);
		currentTurnImgView.setFitWidth(110);

		bottomHB.getChildren().addAll(backBtn, nextRoundBtn);
		nextRoundBtn.setAlignment(Pos.TOP_RIGHT);
		bottomHB.setSpacing(15);
		bottomHB.setAlignment(Pos.CENTER);

		setBottom(bottomHB);
		setMargin(bottomHB, new Insets(15));

		setMargin(topVB, new Insets(15));
		runNewRound(gp);

		player1WinsView.setPreserveRatio(true);
		player1WinsView.setFitWidth(140);
		player2WinsView.setPreserveRatio(true);
		player2WinsView.setFitWidth(140);

		wonImgView.setPreserveRatio(true);
		wonImgView.setFitWidth(400);

		roundImgView.setPreserveRatio(true);
		roundImgView.setFitWidth(180);

		ofImgView.setPreserveRatio(true);
		ofImgView.setFitWidth(70);

		HBox topHB = new HBox(roundImgView, currentRoundL, ofImgView, numberOfRoundsL);
		currentRoundL.setText(currentRound + "");
		numberOfRoundsL.setText(numberOfRounds + "");

		numberOfRoundsL.setStyle("-fx-font-size: 70px;  -fx-text-fill: #FBD000;");
		currentRoundL.setStyle("-fx-font-size: 70px;   -fx-text-fill: #E52521;");

		topHB.setAlignment(Pos.CENTER);
		topHB.setSpacing(15);
		HBox topHB2 = new HBox(currentPlayer, currentTurnImgView);
		setMargin(topHB, new Insets(15));
		topHB2.setAlignment(Pos.CENTER);
		topHB2.setSpacing(15);

		topVB.setAlignment(Pos.CENTER);
		topVB.getChildren().addAll(topHB, topHB2);
		setTop(topVB);

		drawImgView.setPreserveRatio(true);
		drawImgView.setFitWidth(450);

		System.out.println("Current Round: " + currentRound + " out of: " + numberOfRounds);

		player1ImgView.setPreserveRatio(true);
		player1ImgView.setFitWidth(160);

		player1GP.add(player1ImgView, 0, 0);

		leftVB.getChildren().add(player1GP);
		setLeft(leftVB);
		setMargin(leftVB, new Insets(5));

		player2ImgView.setPreserveRatio(true);
		player2ImgView.setFitWidth(200);

		player2GP.add(player2ImgView, 0, 0);
//		aiPlayerGP.add(aiWinsImgView, 0, 1);

		rightVB.getChildren().add(player2GP);
		setRight(rightVB);
		setMargin(rightVB, new Insets(5));

		player1StatsGP.setHgap(15);
		player2StatsGP.setHgap(15);

		player1StatsGP.add(player1NameL, 0, 0);
		player1StatsGP.add(player1WinsView, 1, 0);
		player1StatsGP.add(player1WinsL, 2, 0);
		player1WinsL.setStyle("-fx-font-size: 55.0px;  -fx-text-fill: #E52521;");

		player2StatsGP.add(player2NameL, 0, 0);
		player2StatsGP.add(player2WinsView, 1, 0);
		player2StatsGP.add(player2WinsL, 2, 0);
		player2WinsL.setStyle("-fx-font-size: 55.0px; -fx-text-fill: #E52521;");

		leftVB.getChildren().add(player1StatsGP);
		rightVB.getChildren().add(player2StatsGP);
		leftVB.setMinWidth(400);
		rightVB.setMinWidth(400);


		player2GP.setAlignment(Pos.CENTER);

		nextRoundBtn.setOnAction(e -> {
			if (!game.isGameOver(state))
				return;
			if (currentRound < numberOfRounds) {
				displayedResult = false; // winner results
				runNewRound(gp);
				currentRound++;
				System.out.println("Current Round: " + currentRound + " out of: " + numberOfRounds);
				currentRoundL.setText(currentRound + "");
				numberOfRoundsL.setText(numberOfRounds + "");
			}

		});
		setCenter(gp);

		backBtn.setOnAction(e -> {
			resetAllValues();
			HumanVsHumanMenuUI menu = new HumanVsHumanMenuUI(primaryStage, scene);
			Scene menuScene = new Scene(menu, 1100, 650);
			menuScene.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
			menuScene.getStylesheets().add("style.css");
			primaryStage.setScene(menuScene);
			primaryStage.centerOnScreen();
		});

		gp.setAlignment(Pos.CENTER);
		setAlignment(gp, Pos.CENTER);

	}

	private void drawWinningTiles(GridPane gp) {
		int[][] winningTiles = game.getWinningTiles(state);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (winningTiles[i][j] == 5) {
					Node n = getNodeFromGridPane(gp, j, i);
					if (n instanceof StackPane) {
						for (Node node : ((StackPane) n).getChildren()) {
							if (node instanceof Rectangle) {
								((Rectangle) node).setStyle(" -fx-fill: #E52521;");
							}
						}
					}
				}
			}
		}
	}

	private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}

	private static final Random rand = new Random();

	private void resetAllValues() {
		mainBoard = new int[3][3];
		state = new State(mainBoard);
		firstMoveDone = false;
		player1Turn = 1;
		numberOfRounds = 0;
		startingPlayer = "";
		player1Symbol = ' ';
		player1Wins = 0;
		player2Wins = 0;
		draw = 0;
		game = new TicTacToeGame();
		currentRound = 1;
		random = false;
		displayedResult = false;
	}

	public void runNewRound(GridPane gp) {
		state.setBoard(new int[3][3]);
		resetScreen(gp);
		firstMoveDone = false;
		if (random) {
			startingPlayer = game.getStartingPlayer();
		}
		System.out.println(startingPlayer + " is playing first.");

		drawBoard(gp, state.getBoard());
	}

	private void resetScreen(GridPane gp) {
		for (Node n : gp.getChildren()) {
			if (n instanceof StackPane) {
				((StackPane) n).getChildren().removeIf(node -> node instanceof ImageView);
			}
		}
	}

	public void drawBoard(GridPane gp, int[][] board) {
		gp.getChildren().clear();
		displayedResult = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				StackPane sp = new StackPane();
				Rectangle r = new Rectangle();
				r.setStyle(" -fx-fill: #43B047;");
				r.setStroke(Color.BLACK);
				r.setStrokeWidth(2);
				r.setWidth(180);
				r.setHeight(180);

				sp.getChildren().add(r);
				ImageView xView = new ImageView(xImg);
				ImageView oView = new ImageView(oImg);
				xView.setFitWidth(164);
				xView.setFitHeight(164);
				oView.setFitWidth(180);
				oView.setFitHeight(180);
				if (board[i][j] == player1Turn) {
					if (player1Symbol == 'X') {
						sp.getChildren().add(xView); // if player1 is X, player 2 is O
					} else {
						sp.getChildren().add(oView);
					}
				} else if (board[i][j] == -player1Turn) {
					if (player1Symbol == 'X') {
						sp.getChildren().add(oView); // flipped symbol
					} else {
						sp.getChildren().add(xView);
					}
				}

				addClickListener(gp, i, j, sp, xView, oView);

				if (!gp.getChildren().contains(sp))
					gp.add(sp, j, i); // the grid pane takes column,row. Hence why j and i seem flipped
				gp.setRowIndex(sp, i);
				gp.setColumnIndex(sp, j);
				gp.setRowIndex(r, i);
				gp.setColumnIndex(r, j);
			}
		}
	}

	static boolean displayedResult = false;

	public void addClickListener(GridPane gp, int i, int j, StackPane sp, ImageView xView, ImageView oView) {
		sp.setOnMouseClicked(e2 -> {
			if (displayedResult)
				return;

			int[][] b = state.getBoard();
			if (b[i][j] == 0) { // if the selected spot is empty
				if (currentTurn == 1) {
					b[i][j] = 1;
					if (player1Symbol == 'X') {
						sp.getChildren().add(xView);
					} else {
						sp.getChildren().add(oView);
					}
		
				} else {
					b[i][j] = -1;
					if (player1Symbol == 'X') {
						sp.getChildren().add(oView);
					} else {
						sp.getChildren().add(xView);
					}
				}

			} else {
				return;
			}
			clickSoundPlayer.stop();
			clickSoundPlayer.setVolume(0.5);
			clickSoundPlayer.play();

			if (endOnWin(gp)) {
				return;
			} else if (endOnDraw()) {
				return;
			}
			
			currentTurn = -currentTurn;
			if(currentTurn == 1)
			currentPlayer.setText(player1Name+"'s");
			else
				currentPlayer.setText(player2Name+"'s");

		});

	}

	public boolean endOnDraw() {
		if (game.isFullBoard(state.getBoard())) {
			player1WinsL.setText(player1Wins + "");
			player2WinsL.setText(player2Wins + "");
			handleGameResult();
			if (currentRound >= numberOfRounds || (player2Wins == (numberOfRounds / 2 + 1))) {
				bottomHB.getChildren().remove(nextRoundBtn);

				if (!displayedResult)
					displayFinalGameResults();
			}
			displayedResult = true;
			drawSoundPlayer.stop();
			drawSoundPlayer.play();
			return true;
		} else {
			return false;
		}
	}

	public boolean endOnWin(GridPane gp) {
		if (game.isWinState(state)) {
			drawWinningTiles(gp);
			handleGameResult();
			player1WinsL.setText(player1Wins + "");
			player2WinsL.setText(player2Wins + "");
			if (currentRound >= numberOfRounds || (player2Wins == (numberOfRounds / 2 + 1))) {
				bottomHB.getChildren().remove(nextRoundBtn);

				if (!displayedResult)
					displayFinalGameResults();
			}
			displayedResult = true;
			drawSoundPlayer.stop();
			drawSoundPlayer.play();
			return true;
		} else {
			return false;
		}
	}

	public void displayFinalGameResults() {
		topVB.getChildren().clear();
		if (player2Wins > player1Wins) {
			Label winnerLabel = new Label(player2Name);
			winnerLabel.setStyle("-fx-font-size: 65px;   -fx-text-fill: #E52521;");
			HBox winnerHB = new HBox(winnerLabel, wonImgView);
			winnerHB.setAlignment(Pos.CENTER);
			winnerHB.setSpacing(15);
			topVB.getChildren().add(winnerHB);

		} else if (player1Wins > player2Wins) {
			Label winnerLabel = new Label(player1Name);
			winnerLabel.setStyle("-fx-font-size: 65px;   -fx-text-fill: #E52521;");

			HBox winnerHB = new HBox(winnerLabel, wonImgView);
			winnerHB.setAlignment(Pos.CENTER);
			winnerHB.setSpacing(15);
			topVB.getChildren().add(winnerHB);
		} else {
			topVB.getChildren().add(drawImgView);
		}
		player1WinsL.setText(player1Wins + "");
		player2WinsL.setText(player2Wins + "");
	}

	private void handleGameResult() {
		int winner = game.getWinner(state);
		if (winner == player1Turn) {
			player1Wins++;
		} else if (winner == -player1Turn) {
			player2Wins++;
		} else {
			draw++;
		}

	}

}
