import java.util.ArrayList;


public class Board
{
	int[][] board;
	ArrayList<Piece> pieces;
	Bishop hBishop1;
	Bishop hBishop2;
	Bishop cBishop1;
	Bishop cBishop2;
	ArrayList<String> moves;

public Board(int[][] board)
	{
	moves = new ArrayList<String>();
	this.board = board;
	pieces = new ArrayList<Piece>();
	pieces.add(hBishop1 = new Bishop(0, 3, 3, 'h',"b"));
	pieces.add(hBishop2 = new Bishop(0, 5, 3, 'h',"b"));
	pieces.add(cBishop1 = new Bishop(6, 3, 3, 'c',"B"));
	pieces.add(cBishop2 = new Bishop(6, 5, 3, 'c',"B"));
	}

public void generateLegalMoves()
	{
	for (Piece piece :pieces) 
		{int[][] tempBoard = board;
		ArrayList<String> temp = new ArrayList<String>();
		temp.addAll(piece.theMoves(tempBoard));
		moves.addAll(temp);
		displayBoard(tempBoard);
		System.out.println("Start position: " + piece.getLocationX() + piece.getLocationY());
		System.out.println("Owner: "+ piece.getOwner() + "piece: " +piece.getCodeChar());
		System.out.println(moves);
		}
	}

public void printMoves()
{
	for (String move : moves)
		move.toString();
}

public void displayBoard(int[][] b)
	{
		
		int[][] board = b;
		
		for (int x = 0;x < 7;x++)
			{
				System.out.print(7-x +"  ");
				for (int y = 0; y< 8;y++)
					{
						System.out.printf("%3d ", board[x][y]);
					}
				System.out.println();
			}
		System.out.println("     -----------------------------");
		System.out.println("     A   B   C   D   E   F   G   H");
		
	}

}
