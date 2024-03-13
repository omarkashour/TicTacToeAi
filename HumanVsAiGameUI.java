import java.util.Random;

import atlantafx.base.theme.PrimerLight;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class HumanVsAiGameUI extends BorderPane {
	static int[][] mainBoard = new int[3][3];
	static State state = new State(mainBoard);

	static boolean firstMoveDone = false;
	Image oImg = new Image("/images/oGraphic.png");
	Image xImg = new Image("/images/xGraphic.png");

	Image humanPlayerImg = new Image("/images/humanPlayer.png");
	Image aiPlayerImg = new Image("/images/aiPlayer.png");

	Image humanEndGameImg = new Image("/images/humanEndGame.png");
	Image aiPlayerEvilImg = new Image("/images/aiPlayerEvil.png");

	Image aiWinText1 = new Image("/images/aiWinText1.png");
	Image aiWinText2 = new Image("/images/aiWinText2.png");
	Image aiWinText3 = new Image("/images/aiWinText3.png");
	Image aiWinText4 = new Image("/images/aiWinText4.png");
	Image aiWinText5 = new Image("/images/aiWinText5.png");
	Image aiWinText6 = new Image("/images/aiWinText6.png");
	Image aiWinText7 = new Image("/images/aiWinText7.png");
	Image aiWinText8 = new Image("/images/aiWinText8.png");

	Image aiLoseText1 = new Image("/images/aiLoseText1.png");
	Image aiLoseText2 = new Image("/images/aiLoseText2.png");
	Image aiLoseText3 = new Image("/images/aiLoseText3.png");
	Image aiLoseText4 = new Image("/images/aiLoseText4.png");

	Image aiDrawText1 = new Image("/images/aiDrawText1.png");
	Image aiDrawText2 = new Image("/images/aiDrawText2.png");

	Image humanWinText1 = new Image("/images/humanWinText1.png");
	Image humanWinText2 = new Image("/images/humanWinText2.png");
	Image humanWinText3 = new Image("/images/humanWinText3.png");
	Image humanWinText4 = new Image("/images/humanWinText4.png");

	Image humanLostText1 = new Image("/images/humanLoseText1.png");
	Image humanLostText2 = new Image("/images/humanLoseText2.png");

	Image humanDrawText1 = new Image("/images/humanDrawText1.png");

	Image humanWinsImg = new Image("/images/humanWins.png");
	Image aiWinsImg = new Image("/images/aiWins.png");

	Image aiWonImg = new Image("/images/aiHasWon3.png");
	Image humanWonImg = new Image("/images/humanWon.png");

	Image drawImg = new Image("/images/itsADraw2.png");

	Image roundImg = new Image("/images/round.png");
	Image ofImg = new Image("/images/of.png");

	static int aiTurn = -1;

	static int numberOfRounds = 0;
	static String startingPlayer;
	static char playerSymbol;

	static int playerWins = 0;
	static int aiWins = 0;

	static int draw = 0;
	static TicTacToeGame game = new TicTacToeGame();

	static int currentRound = 1;

	Button backBtn = new Button("Back To Mode Menu");

	Button nextRoundBtn = new Button("Next Round");

	boolean random = false;

	String evilAiSoundUrl = getClass().getResource("/audio/aiLaugh.mp3").toExternalForm();
	Media evilAiSound = new Media(evilAiSoundUrl);
	MediaPlayer evilAiSoundPlayer = new MediaPlayer(evilAiSound);

	String drawSoundUrl = getClass().getResource("/audio/drawSoundEffect.mp3").toExternalForm();
	Media drawSound = new Media(drawSoundUrl);
	MediaPlayer drawSoundPlayer = new MediaPlayer(drawSound);

	String clickSoundUrl = getClass().getResource("/audio/clickSound.wav").toExternalForm();
	Media clickSound = new Media(clickSoundUrl);
	MediaPlayer clickSoundPlayer = new MediaPlayer(clickSound);

	Label playerWinsL = new Label("0");
	Label aiWinsL = new Label("0");
	GridPane humanPlayerGP = new GridPane();
	VBox leftVB = new VBox();
	ImageView humanPlayerImgView = new ImageView(humanPlayerImg);
	ImageView humanWinsImgView = new ImageView(humanWinsImg);
	ImageView aiWinsImgView = new ImageView(aiWinsImg);
	ImageView drawImgView = new ImageView(drawImg);
	ImageView aiWonImgView = new ImageView(aiWonImg);
	ImageView humanWonImgView = new ImageView(humanWonImg);

	GridPane aiPlayerGP = new GridPane();
	ImageView aiPlayerImgView = new ImageView(aiPlayerImg);
	GridPane playerStatsGP = new GridPane();
	GridPane aiStatsGP = new GridPane();
	VBox rightVB = new VBox();
	ImageView aiPlayerEvilImgView = new ImageView(aiPlayerEvilImg);
	ImageView humanEndGameImgView = new ImageView(humanEndGameImg);

	ImageView roundImgView = new ImageView(roundImg);
	ImageView ofImgView = new ImageView(ofImg);

	VBox topVB = new VBox();
	HBox bottomHB = new HBox();

	Label currentRoundL = new Label("");
	Label numberOfRoundsL = new Label("");
	TextArea statsTa = new TextArea();

	boolean addAiStats = false;
	public HumanVsAiGameUI(Stage primaryStage, Scene scene, int numberOfRounds, String startingPlayer,
			char playerSymbol) {
		this.numberOfRounds = numberOfRounds;
		this.playerSymbol = playerSymbol;
		setStyle("-fx-background-color:  #049CD8");
		if (startingPlayer.equals("Random")) {
			startingPlayer = game.getStartingPlayer();
			random = true;
		}
		this.startingPlayer = startingPlayer;

		GridPane gp = new GridPane();
		drawBoard(gp, mainBoard);

		aiPlayerImgView.setOnMouseClicked(e -> {
			statsTa.setMaxWidth(200);
			GridPane.setHalignment(statsTa, HPos.RIGHT);
			if(!addAiStats) {
			aiStatsGP.add(statsTa, 0, 2);
			}else {
				aiStatsGP.getChildren().remove(statsTa);
			}
			addAiStats = !addAiStats;

		});
		aiPlayerEvilImgView.setOnMouseClicked(e -> {
			if(!addAiStats) {
			aiStatsGP.add(statsTa, 0, 2);
			}else {
				aiStatsGP.getChildren().remove(statsTa);
			}
			addAiStats = !addAiStats;
		});

		bottomHB.getChildren().addAll(backBtn, nextRoundBtn);
		nextRoundBtn.setAlignment(Pos.TOP_RIGHT);
		bottomHB.setSpacing(15);
		bottomHB.setAlignment(Pos.CENTER);

		setBottom(bottomHB);
		setMargin(bottomHB, new Insets(15));

		setMargin(topVB, new Insets(15));
		runNewRound(gp);

		roundImgView.setPreserveRatio(true);
		roundImgView.setFitWidth(230);

		ofImgView.setPreserveRatio(true);
		ofImgView.setFitWidth(100);

		HBox topHB = new HBox(roundImgView, currentRoundL, ofImgView, numberOfRoundsL);
		currentRoundL.setText(currentRound + "");
		numberOfRoundsL.setText(numberOfRounds + "");

		numberOfRoundsL.setStyle("-fx-font-size: 70px;  -fx-text-fill: #FBD000;");
		currentRoundL.setStyle("-fx-font-size: 70px;   -fx-text-fill: #E52521;");

		topHB.setAlignment(Pos.CENTER);
		topHB.setSpacing(15);
		setTop(topHB);
		setMargin(topHB, new Insets(15));
		topVB.setAlignment(Pos.CENTER);

		humanWinsImgView.setPreserveRatio(true);
		humanWinsImgView.setFitWidth(320);

		aiWinsImgView.setPreserveRatio(true);
		aiWinsImgView.setFitWidth(220);

		aiWonImgView.setPreserveRatio(true);
		aiWonImgView.setFitWidth(450);

		drawImgView.setPreserveRatio(true);
		drawImgView.setFitWidth(450);

		System.out.println("Current Round: " + currentRound + " out of: " + numberOfRounds);

		humanPlayerImgView.setPreserveRatio(true);
		humanPlayerImgView.setFitWidth(150);

		humanPlayerGP.add(humanPlayerImgView, 0, 0);
		GridPane.setHalignment(humanPlayerImgView, HPos.LEFT);

		leftVB.getChildren().add(humanPlayerGP);
		leftVB.setMinWidth(400);
		rightVB.setMinWidth(400);
		setLeft(leftVB);
		setMargin(leftVB, new Insets(5));

		aiPlayerImgView.setPreserveRatio(true);
		aiPlayerImgView.setFitWidth(200);

		aiPlayerGP.add(aiPlayerImgView, 0, 0);
//		aiPlayerGP.add(aiWinsImgView, 0, 1);
		aiPlayerGP.setAlignment(Pos.CENTER);

		rightVB.getChildren().add(aiPlayerGP);
		setRight(rightVB);
		setMargin(rightVB, new Insets(5));

		playerStatsGP.setHgap(15);
		aiStatsGP.setHgap(15);

		playerStatsGP.add(humanWinsImgView, 0, 0);
		playerStatsGP.add(playerWinsL, 1, 0);
		playerWinsL.setStyle("-fx-font-size: 55.0px;  -fx-text-fill: #E52521;");

		aiStatsGP.add(aiWinsImgView, 0, 0);
		aiStatsGP.add(aiWinsL, 1, 0);
		aiWinsL.setStyle("-fx-font-size: 55.0px; -fx-text-fill: #E52521;");

		leftVB.getChildren().add(playerStatsGP);
		rightVB.getChildren().add(aiStatsGP);

		aiPlayerEvilImgView.setPreserveRatio(true);
		aiPlayerEvilImgView.setFitWidth(200);

		humanEndGameImgView.setPreserveRatio(true);
		humanEndGameImgView.setFitWidth(200);

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
			HumanVsAiMenuUI menu = new HumanVsAiMenuUI(primaryStage, scene);
			Scene menuScene = new Scene(menu, 1100, 650);
			menuScene.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
			menuScene.getStylesheets().add("style.css");
			primaryStage.setScene(menuScene);
			primaryStage.centerOnScreen();
		});

		gp.setAlignment(Pos.CENTER);
		setAlignment(gp, Pos.CENTER);

	}

	public void createAndShowAiStats() {
		Stage statsStage = new Stage();
		statsTa.setText(statsTa.getText() + "\n" + getAiStats());
		BorderPane statsBP = new BorderPane();
		statsBP.setCenter(statsTa);
		Scene s = new Scene(statsBP,400,300);
		s.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
		s.getStylesheets().add("style.css");
		statsTa.setStyle("-fx-font-size: 23px;");
		statsStage.setScene(s);
		statsBP.setMargin(statsTa, new Insets(15));
		statsStage.setTitle("Ai Tile Stats");
		
		statsStage.show();
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

	private ImageView getRandomAiLostMessage() {
		ImageView[] messages = new ImageView[4];
		messages[0] = new ImageView(aiLoseText1);
		messages[1] = new ImageView(aiLoseText2);
		messages[2] = new ImageView(aiLoseText3);
		messages[3] = new ImageView(aiLoseText4);

		int random = rand.nextInt(4);

		return messages[random];
	}

	private ImageView getRandomAiWinMessage() {
		ImageView[] messages = new ImageView[8];
		messages[0] = new ImageView(aiWinText1);
		messages[1] = new ImageView(aiWinText2);
		messages[2] = new ImageView(aiWinText3);
		messages[3] = new ImageView(aiWinText4);
		messages[4] = new ImageView(aiWinText5);
		messages[5] = new ImageView(aiWinText6);
		messages[6] = new ImageView(aiWinText7);
		messages[7] = new ImageView(aiWinText8);
		int random = rand.nextInt(8);

		return messages[random];
	}

	private ImageView getRandomHumanWinMessage() {
		ImageView[] messages = new ImageView[4];
		messages[0] = new ImageView(humanWinText1);
		messages[1] = new ImageView(humanWinText2);
		messages[2] = new ImageView(humanWinText3);
		messages[3] = new ImageView(humanWinText4);
		int random = rand.nextInt(4);

		return messages[random];
	}

	private ImageView getRandomHumanLoseMessage() {
		ImageView[] messages = new ImageView[2];
		messages[0] = new ImageView(humanLostText1);
		messages[1] = new ImageView(humanLostText2);

		int random = rand.nextInt(2);

		return messages[random];
	}

	private ImageView getRandomAiDrawMessage() {
		ImageView[] messages = new ImageView[2];
		messages[0] = new ImageView(aiDrawText1);
		messages[1] = new ImageView(aiDrawText2);

		int random = rand.nextInt(2);

		return messages[random];
	}

	private void resetAllValues() {
		mainBoard = new int[3][3];
		state = new State(mainBoard);
		firstMoveDone = false;
		aiTurn = -1;
		numberOfRounds = 0;
		startingPlayer = "";
		playerSymbol = ' ';
		playerWins = 0;
		aiWins = 0;
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

		if (startingPlayer.equals("Ai") && !firstMoveDone) {
			aiTurn = 1;
			statsTa.appendText("\n" + getAiStats());
			printTileValues();
			playAiTurn(gp, aiTurn);
			statsTa.setText("\n" + getAiStats());
			firstMoveDone = true;
		}

		drawBoard(gp, state.getBoard());
	}

	private void resetScreen(GridPane gp) {
		for (Node n : gp.getChildren()) {
			if (n instanceof StackPane) {
				((StackPane) n).getChildren().removeIf(node -> node instanceof ImageView);
			}
		}
	}

	private void playAiTurn(GridPane gp, int turn) {
		if (game.isWinState(state) || game.isFullBoard(state.getBoard()))
			return;
		State nextState;
		if (HumanVsAiMenuUI.difficulty.equals("Impossible"))
			nextState = game.getNextBestMove(state, turn); // next best move for the Ai
		else
			nextState = game.getRandomNextMove(state, turn);

		int[][] nextBoard = nextState.getBoard();
		int[][] currentBoard = state.getBoard();

		state = nextState;
		drawBoard(gp, state.getBoard());

		if (game.isWinState(state)) {
			return;
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
				if (board[i][j] == aiTurn) {
					if (playerSymbol == 'X') {
						sp.getChildren().add(oView); // the player is X, The Ai is O
					} else {
						sp.getChildren().add(xView); // the player is X, The Ai is O
					}
				} else if (board[i][j] == -aiTurn) {
					if (playerSymbol == 'X') {
						sp.getChildren().add(xView);
					} else {
						sp.getChildren().add(oView);
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
				if (aiTurn == -1) {
					b[i][j] = 1;
				} else {
					b[i][j] = -1;
				}
				if (playerSymbol == 'X') {
					sp.getChildren().add(xView);
				} else {
					sp.getChildren().add(oView);
				}

			} else {
				return;
			}
			clickSoundPlayer.stop();
			clickSoundPlayer.setVolume(0.5);
			clickSoundPlayer.play();

			if (endOnWin(gp) || endOnDraw()) {
				return;
			}

			statsTa.setText("\n" + getAiStats());
			printTileValues();

			playAiTurn(gp, aiTurn);

			if (endOnWin(gp) || endOnDraw()) {
				statsTa.setText("\n" + getAiStats());
				printTileValues();
				return;
			} 
			statsTa.setText("\n" + getAiStats());
			printTileValues();

		});
	}

	public boolean endOnDraw() {
		if (game.isFullBoard(state.getBoard())) {
			playerWinsL.setText(playerWins + "");
			aiWinsL.setText(aiWins + "");
			handleGameResult();
			if (currentRound >= numberOfRounds || (aiWins == (numberOfRounds / 2 + 1))) {
				if((aiWins == (numberOfRounds / 2 + 1)) && currentRound != numberOfRounds) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setContentText("Do you want to end the game here?");
				endHalfDraw = false;
				alert.showAndWait().ifPresent(r -> {
					if (r == ButtonType.OK) {
						bottomHB.getChildren().remove(nextRoundBtn);
						endHalfWin = true;
					}

				});
				if(!endHalfDraw)
					return false;
		}
				if (!displayedResult)
					displayFinalGameResults();
			}
			displayedResult = true;

			return true;
		} else {
			return false;
		}
	}

	static boolean endHalfDraw = false;
	static boolean endHalfWin = false;

	public boolean endOnWin(GridPane gp) {
		if (game.isWinState(state)) {
			drawWinningTiles(gp);
			handleGameResult();
			playerWinsL.setText(playerWins + "");
			aiWinsL.setText(aiWins + "");
			if (currentRound >= numberOfRounds || (aiWins == (numberOfRounds / 2 + 1))) {
				if((aiWins == (numberOfRounds / 2 + 1)) && currentRound != numberOfRounds) {
				endHalfWin = false;
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setContentText("Do you want to end the game here?");
				alert.showAndWait().ifPresent(r -> {
					if (r == ButtonType.OK) {
						bottomHB.getChildren().remove(nextRoundBtn);
						endHalfWin = true;
					}

				});
				if(!endHalfWin)
					return false;
				}
				if (!displayedResult)
					displayFinalGameResults();
			}
			displayedResult = true;
			return true;
		} else {
			return false;
		}
	}

	public void displayFinalGameResults() {
		setTop(topVB);
		if (aiWins > playerWins) {
			System.out.println("AI WON!");
			aiPlayerGP.getChildren().remove(aiPlayerImgView);
			ImageView randomMessage = getRandomAiWinMessage();
			StackPane sp2 = new StackPane(aiPlayerEvilImgView, randomMessage);
			randomMessage.setPreserveRatio(true);
			randomMessage.setFitWidth(400);
			StackPane.setAlignment(randomMessage, Pos.TOP_LEFT);
			aiPlayerGP.add(sp2, 0, 0);
			evilAiSoundPlayer.play();

			ImageView humanLoseMessageView = getRandomHumanLoseMessage();
			Pane pane = new Pane(humanEndGameImgView, humanLoseMessageView);
			humanLoseMessageView.setPreserveRatio(true);
			humanLoseMessageView.setFitWidth(400);
			humanPlayerGP.getChildren().remove(humanPlayerImgView);
			humanEndGameImgView.setY(humanEndGameImgView.getY() + 50);
			humanPlayerGP.add(pane, 0, 0);
			topVB.getChildren().add(aiWonImgView);

		} else if (playerWins > aiWins) {
			System.out.println("PLAYER WON!");
			System.out.println("ITS A DRAW!");

			ImageView randomMessage = getRandomAiLostMessage();
			aiPlayerGP.getChildren().remove(aiPlayerImgView);
			StackPane sp3 = new StackPane(aiPlayerImgView, randomMessage);
			randomMessage.setPreserveRatio(true);
			randomMessage.setFitWidth(400);
			StackPane.setAlignment(randomMessage, Pos.TOP_LEFT);
			aiPlayerGP.add(sp3, 0, 0);

			humanWonImgView.setFitWidth(600);
			humanWonImgView.setPreserveRatio(true);
			ImageView humanWonMessageView = getRandomHumanWinMessage();
			Pane pane = new Pane(humanEndGameImgView, humanWonMessageView);
			humanWonMessageView.setPreserveRatio(true);
			humanWonMessageView.setFitWidth(400);
			humanPlayerGP.getChildren().remove(humanPlayerImgView);
			humanEndGameImgView.setY(humanEndGameImgView.getY() + 50);
			humanPlayerGP.add(pane, 0, 0);
			drawSoundPlayer.stop();
			drawSoundPlayer.setVolume(2);
			drawSoundPlayer.play();
			topVB.getChildren().add(humanWonImgView);
		} else {
			System.out.println("ITS A DRAW!");
			ImageView randomMessage = getRandomAiDrawMessage();
			aiPlayerGP.getChildren().remove(aiPlayerImgView);
			StackPane sp3 = new StackPane(aiPlayerImgView, randomMessage);
			randomMessage.setPreserveRatio(true);
			randomMessage.setFitWidth(400);
			StackPane.setAlignment(randomMessage, Pos.TOP_LEFT);
			aiPlayerGP.add(sp3, 0, 0);

			ImageView humanDrawMessageView = new ImageView(humanDrawText1);
			Pane pane = new Pane(humanEndGameImgView, humanDrawMessageView);
			humanDrawMessageView.setPreserveRatio(true);
			humanDrawMessageView.setFitWidth(400);
			humanPlayerGP.getChildren().remove(humanPlayerImgView);
			humanEndGameImgView.setY(humanEndGameImgView.getY() + 50);
			humanPlayerGP.add(pane, 0, 0);
			drawSoundPlayer.stop();
			drawSoundPlayer.setVolume(2);
			drawSoundPlayer.play();
			topVB.getChildren().add(drawImgView);
		}
		playerWinsL.setText(playerWins + "");
		aiWinsL.setText(aiWins + "");
	}

	private void handleGameResult() {
		int winner = game.getWinner(state);
		if (winner == aiTurn) {
			aiWins++;
		} else if (winner == -aiTurn) {
			playerWins++;
			System.out.println("OKKK");
		} else {
			draw++;
		}

	}

	private void printTileValues() {
		System.out.println();
		int[][] tileValues = game.getTileValues(state, aiTurn);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tileValues[i][j] == Integer.MAX_VALUE) {
					System.out.print("full" + "\t");
				} else
					System.out.print(tileValues[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private String getAiStats() {
		String s = "";
		int[][] tileValues = game.getTileValues(state, aiTurn);
		int row = 0;
		int column = 0;
		int bestValue = 0;
		if (aiTurn == 1) {
			bestValue = Integer.MIN_VALUE;
		} else {
			bestValue = Integer.MAX_VALUE;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tileValues[i][j] == Integer.MAX_VALUE) {
					s += "full" + "\t\t";
				} else {
					if(tileValues[i][j] == 0) {
						s += "draw" + "\t\t";
					}
					else if(tileValues[i][j] == aiTurn) {
						s += "win" + "\t\t";
					}
					else if(tileValues[i][j] == -aiTurn) {
						s += "lose" + "\t\t";
					}
					
					if (aiTurn == 1) {
						if (tileValues[i][j] > bestValue) {
							bestValue = tileValues[i][j];
							row = i;
							column = j;
						}
					} else {
						if (tileValues[i][j] < bestValue) {
							bestValue = tileValues[i][j];
							row = i;
							column = j;
						}
					}
				}
			}
			s += "\n";
		}
		if (bestValue != Integer.MAX_VALUE && bestValue != Integer.MIN_VALUE)
			s += "Next Best move on tile: " + getTileNumber(row, column);
		s += "\n";
		return s;
	}

	private int getTileNumber(int i, int j) {
		if (i >= 3 || i < 0 || j >= 3 || j < 0)
			return -1;

		int[][] numbers = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		return numbers[i][j];
	}

}
