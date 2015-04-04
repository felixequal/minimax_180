import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Stack;


public class Board
{
	long start = 0;
	int[][] board;
	int removeIndex = 99;
	ArrayList<Piece> pieces;
	Bishop hBishop1;
	Bishop hBishop2;
	Bishop cBishop1;
	Bishop cBishop2;
	Bishop hBishop3;
	Bishop cBishop3;
	Bishop hBishop4;
	Bishop cBishop4;
	Knight hKnight1;
	
	King compKing;
	Piece mostRecentlyMoved;
	King humanKing;
	Queen humanQueen;
	Queen compQueen;
	ArrayList<String> moves;
	ArrayList<String> humanMoves;
	ArrayList<String> compMoves;
	Stack<GameState> allBoards;
	//ArrayList<String>[] moves;
	private boolean cleanupFlag;
	boolean gameOver = false;
	String winner;
	int evalEnding = 0;
	GameState prevState;
	int depth = 0;
	int maxdepth = 6;
	private int moveIndex = 0;


	public Board()
		{
			allBoards = new Stack<GameState>();
			humanMoves = new ArrayList<String>();
			compMoves = new ArrayList<String>();
			moves = new ArrayList<String>();
			board = new int[7][8];
			pieces = new ArrayList<Piece>();
			pieces.add(humanQueen = new Queen(4,4,4,'h', 'q'));
			pieces.add(hBishop1 = new Bishop(6, 3, 3, 'h','b'));
			pieces.add(hBishop2 = new Bishop(6, 5, 3, 'h','b'));
			
			pieces.add(cBishop1 = new Bishop(0, 3, -3, 'c','B'));
			pieces.add(cBishop2 = new Bishop(0, 5, -3, 'c','B'));
			
			pieces.add(hKnight1 = new Knight(3, 5, 2, 'h','n'));
			
			
			
			
			pieces.add(humanKing = new King(6, 4, 1, 'h', 'k'));
			pieces.add(compKing = new King(0,4,-1, 'c', 'K'));
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
					int[][] prevBoard = new int[7][8];
					for (int x = 0; x < board.length; x++)
						{
							prevBoard[x] = board[x].clone();
						}
					prevState = new GameState(prevBoard);

					for (Piece piece: pieces)
						{
							int gasLeft = piece.getGasleft();
							if(piece.getLocationX() == newX && piece.getLocationY() == newY) 
								{
									removeIndex = pieces.indexOf(piece);
									//System.out.println("del piece" + piece);
									prevState.saveDeletedPiece(piece);
									cleanupFlag = true;
									//board[newX][newY]=0;
									//				System.out.println("REMOVE-Piece:" + piece.getCodeChar() + " " + piece.getOwner() + " " + piece.getPieceCode() +  " X:" + piece.getLocationX() + " Y: " + piece.getLocationY());
								}
							if(oldX == piece.getLocationX() && oldY == piece.getLocationY() && piece.getGasleft() > 0)
								{
									prevState.setPrevGas(piece.getGasleft());
									if(board[newX][newY] != 0)
										{
											if((piece.getPieceCode() < 0 && board[newX][newY] != -1)
													||(piece.getPieceCode() > 0 && board[newX][newY] != 1))
												{
													piece.setGasleft(3);
												}
										}
									else{
										piece.setGasleft(gasLeft-=1);
									}

									piece.setLocation(newX, newY);
									//piece.clearMoves();
									//mostRecentlyMoved = piece;
									prevState.setOldX(oldX);
									prevState.setOldY(oldY);
									prevState.setCurrentX(newX);
									prevState.setCurrentY(newY);

									board[oldX][oldY]=0;
									board[newX][newY]=piece.getPieceCode();

									//			System.out.println("YES Piece:" + piece.getCodeChar() + " " + piece.getOwner() + " " + piece.getPieceCode() +  " X:" + piece.getLocationX() + " Y: " + piece.getLocationY());
								}
							//	else System.out.println("NOPE. Piece: "  + piece.getCodeChar() + " " + piece.getOwner() + " " + piece.getPieceCode() + " X:" + piece.getLocationX() + " Y: " + piece.getLocationY());
						}
					//prevState.display();
					allBoards.push(prevState);
					cleanup();
					//System.out.println("previous state");
					//prevState.display();
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

	public void revertMove()
		{
			gameOver = false;
			evalEnding = 0;
			GameState prev =
					null;
			if(!allBoards.empty()){prev = allBoards.pop();
			//System.out.println("allBoards size:" + allBoards.size());
			int[][] savedBoard = prev.getBoard();
			for (int x = 0; x < savedBoard.length; x++)
				{
					for (int y = 0; y<savedBoard[0].length;y++)
						{
							board[x][y] = savedBoard[x][y];
						}

				}
			for (Piece piece: pieces)
				{
					if (piece.getLocationX() == prev.getCurrentX() && piece.getLocationY() == prev.getCurrentY())
						{
							piece.setLocation(prev.getOldX(), prev.getOldY());
							piece.setGasleft(prev.getPrevGas());
						}
				}
			Piece restoredPiece = prev.getDeletedPiece();
			if(restoredPiece != null)
				{
					pieces.add(restoredPiece);
					//	System.out.println("in revertMove. restoredPiece not null: ");
					//restoredPiece.print();
				}

			//mostRecentlyMoved.setLocation(mostRMoldX, mostRMoldY);
			}}

	public ArrayList<String> generateLegalMoves(char c)
	{
		moves.clear();
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
					ArrayList<String> temp = new ArrayList<String>();

					if(piece.getGasleft() > 0){
						piece.clearMoves();
						temp.addAll(piece.theMoves(tempBoard));
						moves.addAll(temp); 
						

						temp.clear();

					}

				}

			}//if (moves.isEmpty()) gameOver(c);

		return moves;
	}

	public int eval(String move)
		{
			//System.out.println("eval this: " + move);
			int score=0;
			int x = (int)move.charAt(2)-48;
			int y = (int)move.charAt(3)-48;
			//System.out.println(" x: " + x + " y: " + y +" value: " + board[x][y]);
			//displayActualBoard(board);
			if(board[x][y] != 0) score = 5;
			//System.out.println(" score: " + score);
			return score;
		}

	void minimax(int[][] board) 
		{
			//ArrayList<String> minimaxMoves = new ArrayList<String>();
			//(generateLegalMoves('c'));
			//String bestMove;
			start = System.nanoTime()/1000000000;
			int bestScore = -9999;
			int currentScore;
			//System.out.println("minimax depth:" + depth);
			ArrayList<String> theseMoves = new ArrayList<String>();
			theseMoves = generateLegalMoves('c');
			if (theseMoves.isEmpty()) gameOver('c');
			System.out.println("these moves" + theseMoves);
			String[] argh = new String[theseMoves.size()];
			for(int x = 0;x<theseMoves.size();x++)
				{
					argh[x] = theseMoves.get(x);
				}
			//System.out.println("starting Moves at depth " + depth + " : " + theseMoves);
			String bestMove = "";
			//try {
			for(int x = 0;x<argh.length;x++)
				{

					//}

					makeMove(argh[x]);
					//	System.out.println("move: " + argh[x]);
					currentScore = min(argh[x], depth+1);
					if (currentScore > bestScore) { /*System.out.println("Bestmove" + bestMove);*/ bestMove = argh[x]; }

					revertMove();
				} //}catch (Exception e) {}

			//System.out.println("BESTMOVE???????????????" + bestMove);
			makeMove(bestMove);
			System.out.println("!!!!Computer Move:" + bestMove + " !!!!");
			depth = 0;
		}

	int min(String move, int depth)
		{
			//	moveIndex++;
			//int depth = 0;
//			displayBoard();
			ArrayList<String> minMoves = new ArrayList<String>();
			minMoves = generateLegalMoves('h');
			if (minMoves.isEmpty()) gameOver('h');
			String[] mins = new String[minMoves.size()];
			for(int x = 0;x<minMoves.size();x++)
				{
					mins[x] = minMoves.get(x);
				}
			//	System.out.println("minMoves at depth" + depth + " : " + minMoves);
			//moves.add(minMoves);
			//minMoves = moves.get(moveIndex);
			String bestMove;
			int currentScore;
			int bestScore = 9999;
			//depth++;
			//System.out.println("min depth:" + depth + "maxDepth: " + maxdepth + "moves: " + moves);
			//return 0;

			if(isGameOver()) return evalEnding;
			else if (depth == maxdepth || ((System.nanoTime()/1000000000)-start) > 4.9) 
				{return eval(move);}
			//	return 0;
			else{
				//minMoves = generateLegalMoves('h');
				for(int x = 0;x<mins.length;x++)
					{
						makeMove(mins[x]);
						currentScore = max(mins[x], depth+1);
						//	System.out.println("MIN:currentScore:" + currentScore);
						if (currentScore < bestScore) bestMove = mins[x];
						revertMove();
					}

			}
			//moveIndex--;
			//depth--;
			return bestScore;
		}

	int max(String move, int depth)
		{
			//int depth = 0;
			//	moveIndex++;
			//moves.add();
	//		displayBoard();
			ArrayList<String> maxMoves = new ArrayList<String>();
			maxMoves = generateLegalMoves('c');
			if(maxMoves.isEmpty()) gameOver('c');
			String[] maxs = new String[maxMoves.size()];
			for(int x = 0;x<maxMoves.size();x++)
				{
					maxs[x] = maxMoves.get(x);
				}
			//moves.add(maxMoves);
			//System.out.println("maxMoves at depth" + depth + " : "  + maxMoves);
			//System.out.println("max depth:" + depth + "maxDepth: " + maxdepth + "moves: " + moves);
			//maxMoves = moves.get(moveIndex);
			String bestMove = null;
			int currentScore;
			int bestScore = -9999;
			//depth++;
			//	System.out.println("max depth:" + depth);
			if(isGameOver()) return evalEnding;
			else if (depth == maxdepth || ((System.nanoTime()/1000000000)-start) > 4.9) {return eval(move);}
			else{
				//maxMoves = generateLegalMoves('c');
				for(int x = 0; x < maxs.length;x++)
					{
						makeMove(maxs[x]);
						currentScore = min(maxs[x], depth+1);
						//		System.out.println("MAX:currentScore:" + currentScore);
						//		System.out.println("MAX:bestScore:" + bestScore);
						//		System.out.println("MAXb:bestmove:" + bestMove);
						//		System.out.println("MAX:currentmove:" + bestMove);

						if (currentScore > bestScore) bestMove = maxs[x];
						//	System.out.println("MAXa:bestmove:" + bestMove);
						revertMove();
					}
			}
			//depth--;
			//moveIndex--;
			return bestScore;
		}





	public void displayBoard()
		{

			//	int[][] board = b;
			boolean blank = true;
			String blankTile = "--";
			System.out.println("Pieces:" + pieces);
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
			//System.out.println("converted moves: ");
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

					//	System.out.print(", ");
					//char temp = move.charAt(x);
					//int value = Integer.valueOf(temp);
					//System.out.printf("HI %d\n", value);

				}
			//System.out.println();

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
				//	System.out.print("Piece to be removed: ");
				//(pieces.get(removeIndex)).info();
				pieces.remove(removeIndex);
				//humanMoves.clear();
				//compMoves.clear();
				cleanupFlag = false;
				removeIndex = 99;
			}
		}

	public void gameOver(char flag)
		{
			if (flag == 'h') winner = "Computer Wins"; evalEnding = -999;
			if(flag == 'c') winner = "Human Wins"; evalEnding = 999;
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

	public Stack<GameState> getAllBoards()
	{
		return allBoards;
	}

	public void setAllBoards(Stack<GameState> allBoards)
		{
			this.allBoards = allBoards;
		}

}
