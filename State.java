import java.util.Arrays;

public class State {
	int[][] board = new int[3][3]; // game board

	public State() {

	}

	public State(int[][] board) {
		this.board = board;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		return Arrays.deepEquals(board, other.board);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}

}
