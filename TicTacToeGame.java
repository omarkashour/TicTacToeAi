import java.util.LinkedList;
import java.util.Random;

public class TicTacToeGame {

	public static String getStartingPlayer() {
		String[] players = { "Ai", "Human" };
		Random rand = new Random();
		int index = rand.nextInt(2);
		return players[index];
	}
	
	
	public static State getRandomNextMove(State state ,int player) {
		LinkedList<State> possibleStates = getNextStates(state, player);
		Random rand = new Random();
		int index = rand.nextInt(possibleStates.size());
		return possibleStates.get(index);
	}

	public static State getNextBestMove(State state, int player) {
		LinkedList<State> possibleStates = getNextStates(state, player);
		State bestNextStep = null;
		if (player == -1) { // ai is playing second
			int min = Integer.MAX_VALUE; // minimizing
			for (State s : possibleStates) {
				int value = maximize(s);
				if (value < min) { // minimum of maximum of child states
					min = value;
					bestNextStep = s;
				}

			}
		} else { // ai is playing first
			int max = Integer.MIN_VALUE; // maximizing
			for (State s : possibleStates) {
				int value = minimize(s);
				if (value > max) { // maximum of minimum of child states
					max = value;
					bestNextStep = s;
				}
			}

		}
		return bestNextStep;
	}

	public static int[][] getWinningTiles(State state) { // set the winning tile a value of 5
		int[][] board = state.getBoard();
		int[][] winningTiles = new int[3][3];
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
				winningTiles[i][0] = 5;
				winningTiles[i][1] = 5;
				winningTiles[i][2] = 5;
			}
			if (board[0][i] != 0 && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
				winningTiles[0][i] = 5;
				winningTiles[1][i] = 5;
				winningTiles[2][i] = 5;
			}
		}

		// check both diagonals
		if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
			winningTiles[0][0] = 5;
			winningTiles[1][1] = 5;
			winningTiles[2][2] = 5;
		}
		if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
			winningTiles[0][2] = 5;
			winningTiles[1][1] = 5;
			winningTiles[2][0] = 5;
		}

		return winningTiles; // no winners
	}

	public static boolean isFullBoard(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	public static boolean isWinState(State state) {
		return getWinner(state) != 0;
	}

	public static boolean isDraw(State state) {
		return isFullBoard(state.getBoard());
	}

	public static int getWinner(State state) {
		int[][] board = state.getBoard();

		for (int i = 0; i < 3; i++) {
			if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
				return board[i][0]; // return the winner (1 or -1)
			}
			if (board[0][i] != 0 && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
				return board[0][i];
			}
		}

		// check both diagonals
		if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
			return board[0][0];
		}
		if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
			return board[0][2];
		}

		return 0; // no winners
	}

	public static void printBoard(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static int maximize(State state) {
		if (isWinState(state)) {
			return getWinner(state);
		}

		if (isFullBoard(state.getBoard())) {
			return 0;
		}

		int maxEval = Integer.MIN_VALUE;
		LinkedList<State> possibleStates = getNextStates(state, 1);
		for (State s : possibleStates) {
			int eval = minimize(s);
			maxEval = Math.max(maxEval, eval);
		}
		return maxEval;
	}

	public static int minimize(State state) {
		if (isWinState(state)) {
			return getWinner(state);
		}

		if (isFullBoard(state.getBoard())) {
			return 0;
		}
		int minEval = Integer.MAX_VALUE;
		LinkedList<State> possibleStates = getNextStates(state, -1);
		for (State s : possibleStates) {
			int eval = maximize(s);
			minEval = Math.min(minEval, eval);
		}
		return minEval;
	}

	private static LinkedList<State> getNextStates(State s, int player) {
		LinkedList<State> possibleStates = new LinkedList<State>();
		int[][] board = s.getBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					int[][] newBoard = copyBoard(board);
					newBoard[i][j] = player;
					State nextState = new State(newBoard);
					possibleStates.add(nextState);
				}
			}
		}

		return possibleStates;
	}

	public static int[][] copyBoard(int[][] board) {
		int[][] copy = new int[board.length][board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				copy[i][j] = board[i][j];
			}
		}

		return copy;
	}

	public static boolean isGameOver(State state) {
		return isFullBoard(state.getBoard()) || isWinState(state);
	}
	
	public static int[][] getTileValues(State state, int player) {
		int[][] values = new int[3][3];
		int[][] board = state.getBoard();
		LinkedList<State> possibleStates = getNextStates(state, player);
		for(int i = 0 ; i < 3 ; i++) {
			for(int j  = 0 ; j < 3 ; j++) {
				if(board[i][j] == 0) {
					if(player == 1) {
						int value = minimize(possibleStates.removeFirst());
						values[i][j] = value;
					}
					else {
						int value = maximize(possibleStates.removeFirst());
						values[i][j] = value;
					}
				}
				else {
					values[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		return values;
	}
}
