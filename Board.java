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

public Board()
	{
	moves = new ArrayList<String>();
	board = new int[7][8];
	pieces = new ArrayList<Piece>();
	pieces.add(hBishop1 = new Bishop(0, 3, 3, 'h','b'));
	pieces.add(hBishop2 = new Bishop(0, 5, 3, 'h','b'));
	pieces.add(cBishop1 = new Bishop(6, 3, -3, 'c','B'));
	pieces.add(cBishop2 = new Bishop(6, 5, -3, 'c','B'));
	pieces.add(cBishop2 = new Bishop(5, 4, 3, 'h','b'));
	pieces.add(cBishop2 = new Bishop(1, 4, -3, 'c','B'));
	}

public int[][] initBoard()
{
	for(Piece piece: pieces)
		{
			int x=piece.getLocationX();
			int y=piece.getLocationY();
			board[x][y] = piece.getPieceCode();
		}
	
	return board;
	
}

public void generateLegalMoves()
	{
	for (Piece piece :pieces) 
		{int[][] tempBoard = new int[7][8];
		for (int x = 0; x < board.length; x++)
			{
				tempBoard[x] = board[x].clone();
			}
		//tempBoard = board.clone(); doesn't work. durp. java's array.clone only works properly on 1-d arrays.
		
		ArrayList<String> temp = new ArrayList<String>();
		temp.addAll(piece.theMoves(tempBoard));
		moves.addAll(temp);
		
		piece.print();
		System.out.println("indiv moves: " + piece.getMoves());
		System.out.println("Start position: " + piece.getLocationX() + piece.getLocationY());
		displayBoard(tempBoard);
		//System.out.println("Owner: "+ piece.getOwner() + " piece: " +piece.getCodeChar());
		System.out.println("all moves: " + moves);
		convertMoves(moves);
		}
	}

public void printMoves()
{
	for (String move : moves)
		move.toString();
}

public void displayBoard(int[][] b)
	{
		
	//	int[][] board = b;
		
		for (int x = 0;x < 7;x++)
			{
				System.out.print(7-x +"  ");
				for (int y = 0; y< 8;y++)
					{
						System.out.printf("%3d ", b[x][y]);
					}
				System.out.println();
			}
		System.out.println("     -----------------------------");
		System.out.println("     A   B   C   D   E   F   G   H");
		
	}

public void convertMoves(ArrayList<String> moveList)
{
	for(String move: moveList)
		{
			for(int x = 0;x>4;x++)
				{
					char temp = move.charAt(x);
					int value = Integer.valueOf(temp);
					System.out.printf("HI %d\n", value);
					
				}
			
		//if (String.valueOf(0)
			//System.out.println(String.valueOf(temp));
		}
}

}
