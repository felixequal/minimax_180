import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Phobos
{
	private static Scanner scan;
	
	public static void main(String[] args) throws InterruptedException
		{
			ArrayList<String> currentCompLegalMoves;
			ArrayList<String> currentHumanLegalMoves;
			String humanMove;
			String checkLegal = "0000";
			boolean legalFlag = false;
			Board gameBoard = new Board();
			int[][] board = gameBoard.initBoard();
			//gameBoard.generateLegalMoves();
			//gameBoard.printMoves();
			//testBishop.theMoves(board);
				
			//gameBoard.displayBoard();
			scan = new Scanner(System.in);
			while (true)
				{
					currentHumanLegalMoves = gameBoard.generateLegalMoves('h');
					//currentHumanLegalMoves = gameBoard.getHumanMoves();
				/*	for(String move: currentHumanLegalMoves)
						{
							gameBoard.eval(move);
						}
				*/	
					if(currentHumanLegalMoves.isEmpty()) gameBoard.gameOver('h');
					if (gameBoard.isGameOver()) break;
			while (checkLegal == "0000" || legalFlag == false)
			{
				gameBoard.displayBoard();
			humanMove = getInput();
			System.out.println("Move = " + humanMove);
			checkLegal = convertHumanInput(humanMove);
			if(checkLegal == "0000") {System.out.println(" --Illegal input-- ");}
			if(currentHumanLegalMoves.contains(checkLegal)) {legalFlag = true;}
			else System.out.println(" **Illegal move** ");
			}
			System.out.println("Correct legal human move entered. converted human input to: " + checkLegal);
			System.out.println("PHOBOS: all human moves: " + gameBoard.humanMoves);
			gameBoard.makeMove(checkLegal);
			
			legalFlag = false;
			checkLegal = "0000";
			gameBoard.cleanup();
			board = gameBoard.initBoard();
			gameBoard.displayBoard();
			currentHumanLegalMoves.clear();
			Thread.sleep(2000);
			/*System.out.println("retrieve last gameState?");
			String yesno = "";
			yesno = scan.nextLine();
			System.out.println("entered: " + yesno);
			if(yesno.contains("yes"))
				{
					System.out.println("here is the last gameState");
					
					ArrayList<GameState> oldBoards = gameBoard.getAllBoards();
					System.out.println("blah" + oldBoards);
					int size = oldBoards.size();
					GameState test = (GameState)oldBoards.get(size-1);
					test.display();
					System.out.println("Would you like to revert?");
					yesno = scan.nextLine();
					if( yesno.contains("yes"))
						{
							gameBoard.revertMove(test);
						}
				}
				*/
			//gameBoard.generateLegalMoves('c');
			
			System.out.println("PHOBOS: all Comp moves: " + gameBoard.compMoves);
			//currentCompLegalMoves = gameBoard.getCompMoves();
			//if(currentCompLegalMoves.isEmpty()) gameBoard.gameOver('c');
			//if (gameBoard.isGameOver()) break;
			gameBoard.minimax(board);
			//Random rand = new Random();
			//int r = rand.nextInt();
			//r= r % currentCompLegalMoves.size();
			//gameBoard.makeMove(currentCompLegalMoves.get(0));
			board = gameBoard.initBoard();
			gameBoard.displayBoard();
			
			
			legalFlag = false;
			checkLegal = "0000";
			gameBoard.cleanup();
			//currentCompLegalMoves.clear();
			//if (gameBoard.isGameOver()) break;
				}
			gameBoard.displayActualBoard(board);
			gameBoard.displayBoard();
			System.out.println(gameBoard.getWinner());
			
		}
	
	public static String getInput()
		{
			String humanInput = "";
			System.out.println("Enter move: Start = ");
			humanInput = scan.nextLine();
			System.out.println("Enter move: Finish = ");
			humanInput += scan.nextLine();
			return humanInput;
		}
	
	
	
	public static String convertHumanInput(String S)
		{
		String converted = "0000";
		if (S.length() != 4) {System.out.println("Too long"); return converted;}
		int fromRow = 0;
		int toRow = 0;
		String temp = S.substring(0, 1);
		try{
		fromRow = Integer.parseInt(temp, 10);
		fromRow=7-fromRow;
		temp = S.substring(2, 3);
		toRow = Integer.parseInt(temp, 10);
		toRow=7-toRow;
		} catch (NumberFormatException e){
		System.out.println("Row first. Row number then column letter. e.g. 7D"); return converted;	
		}
		System.out.println("converted from-row to input: " + fromRow);
		System.out.println("converted to-row to input: " + toRow);
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
	
	
}
