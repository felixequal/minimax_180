import java.util.ArrayList;


public class Board
{
	int[][] board;
	int removeIndex = 99;
	ArrayList<Piece> pieces;
	Bishop hBishop1;
	Bishop hBishop2;
	Bishop cBishop1;
	Bishop cBishop2;
	Bishop cBishop3;
	Bishop cBishop5;
	King humanKing;
	ArrayList<String> moves;
	ArrayList<String> humanMoves;
	ArrayList<String> compMoves;
	private boolean cleanupFlag;
	boolean gameOver = false;
	String winner;

	public Board()
		{
			humanMoves = new ArrayList<String>();
			compMoves = new ArrayList<String>();
			moves = new ArrayList<String>();
			board = new int[7][8];
			pieces = new ArrayList<Piece>();
			pieces.add(hBishop1 = new Bishop(0, 3, 3, 'h','b'));
			pieces.add(hBishop2 = new Bishop(0, 5, 3, 'h','b'));
			pieces.add(cBishop1 = new Bishop(6, 3, -3, 'c','B'));
			pieces.add(cBishop2 = new Bishop(6, 5, -3, 'c','B'));
			pieces.add(humanKing = new King(1, 4, 1, 'h', 'k'));
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

	public void makeMove(String legalMove)
		{
			//generateLegalMoves();
			if(!isGameOver())
				{
			int oldX =0, oldY =0, newX =0, newY =0;	
			oldX = (int)legalMove.charAt(0)-48;
			oldY = (int)legalMove.charAt(1)-48;
			newX = (int)legalMove.charAt(2)-48;
			newY = (int)legalMove.charAt(3)-48;
		//	System.out.println("legalmove X: " + oldX + "Y: " + oldY);
			int[][] tempBoard = new int[7][8];
			for (int x = 0; x < board.length; x++)
				{
					tempBoard[x] = board[x].clone();
				}
			for (Piece piece: pieces)
				{

					if(piece.getLocationX() == newX && piece.getLocationY() == newY) 
						{
							removeIndex = pieces.indexOf(piece);
							cleanupFlag = true;
							board[newX][newY]=0;
			//				System.out.println("REMOVE-Piece:" + piece.getCodeChar() + " " + piece.getOwner() + " " + piece.getPieceCode() +  " X:" + piece.getLocationX() + " Y: " + piece.getLocationY());
							}
					if(oldX == piece.getLocationX() && oldY == piece.getLocationY())
						{
							piece.setLocation(newX, newY);
							piece.clearMoves();
							board[oldX][oldY]=0;
							board[newX][newY]=piece.getPieceCode();

				//			System.out.println("YES Piece:" + piece.getCodeChar() + " " + piece.getOwner() + " " + piece.getPieceCode() +  " X:" + piece.getLocationX() + " Y: " + piece.getLocationY());
						}
				//	else System.out.println("NOPE. Piece: "  + piece.getCodeChar() + " " + piece.getOwner() + " " + piece.getPieceCode() + " X:" + piece.getLocationX() + " Y: " + piece.getLocationY());
				}
			//initBoard();
			//System.out.println("BOARD>MAKEMOVE: cleared human moves:" + humanMoves);
			//System.out.println("BOARD>MAKEMOVE: cleared comp moves:" + compMoves);
			
			//change gameboard.
			//change piece location.
			//remember last piece location.
			//remember gameboard (add to array).
			//set piece deletion.
			//remember deleted piece.
				}
		}

	public void generateLegalMoves(char c)
		{
			
			//displayBoard();
			for (Piece piece :pieces) 
				{ if (piece.getOwner() == c) 
					{
					//System.out.print("Start position: " + piece.getLocationX() + piece.getLocationY() + " ");
					int[][] tempBoard = new int[7][8];
					for (int x = 0; x < board.length; x++)
						{
							tempBoard[x] = board[x].clone();
						}
					//displayActualBoard(tempBoard);
					//tempBoard = board.clone(); doesn't work. durp. java's array.clone only works properly on 1-d arrays.

					ArrayList<String> temp = new ArrayList<String>();
					System.out.println("temp init: " + temp);
		//			System.out.println("moves GLM: " + piece.getMoves());
					if(piece.getGasleft() != 0){
						piece.clearMoves();
						System.out.println("owner: " + c + "Piece moves: " + piece.getMoves());
						temp.addAll(piece.theMoves(tempBoard));
						System.out.println("temp after addall: " + temp);
						if (c == 'c') compMoves.addAll(temp);
						if (c == 'h') humanMoves.addAll(temp);
						//else {System.out.println("no moves!");}
						//System.out.println("BOARD>generateLegalMoves:Owner: "+ piece.getOwner() + " piece: " +piece.getCodeChar());
						System.out.println("BOARD>generateLegalMoves:indiv moves: " + piece.getMoves());
						convertMoves(piece.getMoves());
						temp.clear();
					}

					//System.out.println("BOARD>generateLegalMoves:Owner: "+ piece.getOwner() + " piece: " +piece.getCodeChar());
					//System.out.println("BOARD>generateLegalMoves:indiv moves: " + piece.getMoves());
					//System.out.println("BOARD>generateLegalMoves:Display Board");
					//System.out.println("BOARD>generateLegalMoves:actual board");
					//displayActualBoard(tempBoard);
				}
			
			//convertMoves(humanMoves);
		//	if (humanMoves.isEmpty()) gameOver(0);
		//	if(compMoves.isEmpty()) gameOver(1);
			
				}
		}

	public void printMoves()
		{
			for (String move : moves)
				move.toString();
		}

	public void displayBoard()
		{

			//	int[][] board = b;
			boolean blank = true;
			String blankTile = "--";
			for (int x = 0;x < 7;x++)
				{
					System.out.print(7-x +"  ");
					for (int y = 0; y< 8;y++)
						{ blank = true;
						for(Piece piece: pieces)
							{
								if (piece.getLocationX() == x && piece.getLocationY() == y)
									{
										piece.print(); blank = false;
									}
							}
						if(blank == true) { System.out.printf("%4s", blankTile);}
						//System.out.printf("%3d ", b[x][y]);
						}
					System.out.println();
				}
			System.out.println("     -----------------------------");
			System.out.println("     A   B   C   D   E   F   G   H");

		}

	public void displayActualBoard(int[][] b)
		{

			//	int[][] board = b;
			int blank = 0;
			for (int x = 0;x < 7;x++)
				{
					System.out.print(x +"  ");
					for (int y = 0; y< 8;y++)
						{
							System.out.printf("%3d ", b[x][y]);
						}
					System.out.println();
				}
			System.out.println("     -----------------------------");
			System.out.println("     0   1   2   3   4   5   6   7");

		}

	public String convertHumanInput(String S)
		{
			String converted = "0000";
			if (S.length() != 4) {System.out.println("Too long"); return null;}
			int fromRow = 0;
			int toRow = 0;
			String temp = S.substring(0, 1);
			//integer.p
			try{
				fromRow = Integer.parseInt(temp, 10);
				fromRow=7-fromRow;
				temp = S.substring(2, 3);
				toRow = Integer.parseInt(temp, 10);
				toRow=7-toRow;
			} catch (NumberFormatException e){
				System.out.println("Row first. Row number then column letter. e.g. 7D"); return converted;	
			}
			System.out.println("converted from-row from input: " + fromRow);
			System.out.println("converted to-row from input: " + toRow);
			char c = 'x';
			char d = 'x';
			if(S.charAt(1) == 'A' || S.charAt(1) == 'a') c = '0';
			if(S.charAt(1) == 'B' || S.charAt(1) == 'b') c = '1';
			if(S.charAt(1) == 'C' || S.charAt(1) == 'c') c = '2';
			if(S.charAt(1) == 'D' || S.charAt(1) == 'd') c = '3';
			if(S.charAt(1) == 'E' || S.charAt(1) == 'e') c = '4';
			if(S.charAt(1) == 'F' || S.charAt(1) == 'f') c = '5';
			if(S.charAt(1) == 'G' || S.charAt(1) == 'g') c = '6';
			if(S.charAt(1) == 'H' || S.charAt(1) == 'h') c = '7';
			if(S.charAt(3) == 'A' || S.charAt(3) == 'a') d = '0';
			if(S.charAt(3) == 'B' || S.charAt(3) == 'b') d = '1';
			if(S.charAt(3) == 'C' || S.charAt(3) == 'c') d = '2';
			if(S.charAt(3) == 'D' || S.charAt(3) == 'd') d = '3';
			if(S.charAt(3) == 'E' || S.charAt(3) == 'e') d = '4';
			if(S.charAt(3) == 'F' || S.charAt(3) == 'f') d = '5';
			if(S.charAt(3) == 'G' || S.charAt(3) == 'g') d = '6';
			if(S.charAt(3) == 'H' || S.charAt(3) == 'h') d = '7';
			if (c == 'x' || d == 'x') {System.out.println("error typing in columns. Use upper or lowercase a-h"); return converted;}
			converted = ""+fromRow+c+toRow+d;
			return converted;

		}
	public void convertMoves(ArrayList<String> moveList)
		{
			System.out.println("converted moves: ");
			for(String move: moveList)
				{
					
					//int row = Integer.parseInt(move.(0));
					//System.out.println(Integer.valueOf(7-String.valueOf(move.charAt(0)));
					if (move.charAt(0) == '0') System.out.print("7");
					if (move.charAt(0) == '1') System.out.print("6");
					if (move.charAt(0) == '2') System.out.print("5");
					if (move.charAt(0) == '3') System.out.print("4");
					if (move.charAt(0) == '4') System.out.print("3");
					if (move.charAt(0) == '5') System.out.print("2");
					if (move.charAt(0) == '6') System.out.print("1");
					if (move.charAt(1) == '0') System.out.print("A");
					if (move.charAt(1) == '1') System.out.print("B");
					if (move.charAt(1) == '2') System.out.print("C");
					if (move.charAt(1) == '3') System.out.print("D");
					if (move.charAt(1) == '4') System.out.print("E");
					if (move.charAt(1) == '5') System.out.print("F");
					if (move.charAt(1) == '6') System.out.print("G");
					if (move.charAt(1) == '7') System.out.print("H");
					if (move.charAt(2) == '0') System.out.print("7");
					if (move.charAt(2) == '1') System.out.print("6");
					if (move.charAt(2) == '2') System.out.print("5");
					if (move.charAt(2) == '3') System.out.print("4");
					if (move.charAt(2) == '4') System.out.print("3");
					if (move.charAt(2) == '5') System.out.print("2");
					if (move.charAt(2) == '6') System.out.print("1");
					if (move.charAt(3) == '0') System.out.print("A");
					if (move.charAt(3) == '1') System.out.print("B");
					if (move.charAt(3) == '2') System.out.print("C");
					if (move.charAt(3) == '3') System.out.print("D");
					if (move.charAt(3) == '4') System.out.print("E");
					if (move.charAt(3) == '5') System.out.print("F");
					if (move.charAt(3) == '6') System.out.print("G");
					if (move.charAt(3) == '7') System.out.print("H");

					System.out.print(", ");
					//char temp = move.charAt(x);
					//int value = Integer.valueOf(temp);
					//System.out.printf("HI %d\n", value);

				}
			System.out.println();

			//if (String.valueOf(0)
			//System.out.println(String.valueOf(temp));

		}


	public ArrayList<String> getHumanMoves()
	{
		return humanMoves;
	}

	public void setHumanMoves(ArrayList<String> humanMoves)
		{
			this.humanMoves = humanMoves;
		}

	public ArrayList<String> getCompMoves()
	{
		return compMoves;
	}

	public void setCompMoves(ArrayList<String> compMoves)
		{
			this.compMoves = compMoves;
		}

	public void cleanup()
		{
			if (cleanupFlag == true){
			System.out.print("Piece to be removed: ");
			(pieces.get(removeIndex)).info();
			pieces.remove(removeIndex);
			humanMoves.clear();
			compMoves.clear();
			cleanupFlag = false;
			}
		}
	
	public void gameOver( char flag)
	{
		if (flag == 'h') winner = "Computer Wins";
		if(flag == 'c') winner = "Human Wins";
		gameOver = true;
	}
	
	public String getWinner()
		{
		return winner;
		}

	public boolean isGameOver()
	{
		return gameOver;
		
	}

}
