import java.util.ArrayList;


public class Board
{
	int[][] board;
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
				if(piece.getGasleft() != 0){
				temp.addAll(piece.theMoves(tempBoard));
				if (piece.getOwner() == 'c') compMoves.addAll(temp);
				if (piece.getOwner() == 'h') humanMoves.addAll(temp);
				convertMoves(piece.getMoves());
				}
				
				
				//piece.print();
				System.out.println("indiv moves: " + piece.getMoves());
				System.out.println("Start position: " + piece.getLocationX() + piece.getLocationY());
				displayBoard();
				displayActualBoard(tempBoard);
				//System.out.println("Owner: "+ piece.getOwner() + " piece: " +piece.getCodeChar());
				System.out.println("all moves: " + moves);
				
				}
			//convertMoves(humanMoves);
			System.out.println("all human moves: " + humanMoves);
			System.out.println("all Comp moves: " + compMoves);
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
	//char c = (char)fromRow;
	
	System.out.println("converted from-row from input: " + fromRow);
	System.out.println("converted to-row from input: " + toRow);
	//x = Integer.valueOf(s)
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
	
}
