import java.io.BufferedReader;
import java.util.Scanner;


public class Phobos
{
	private static Scanner scan;
	
	public static void main(String[] args)
		{
			String humanMove;
			/*int[][] board = 
			{
						{0, 0, 0,-3, 0,-3, 0, 0},
						{0, 0, 0, 0, 2, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 2, 0,-2, 0,-1, 0},
						{0, 0, 0, 3, 0, 3, 0, 0}
			};
			*/
			Board gameBoard = new Board();
			int[][] board = gameBoard.initBoard();
			//Bishop testBishop = new Bishop(0, 4, -3, 'c',"b");
			gameBoard.generateLegalMoves();
			gameBoard.printMoves();
			//testBishop.theMoves(board);
			
			
			gameBoard.displayBoard(board);
			scan = new Scanner(System.in);
			humanMove = getInput();
			System.out.println("Move = " + humanMove);
			//testBishop.print();
			//testBishop.setGasleft(2);
			//testBishop.print();
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
	
	
	
	
	
	
	
	public static void displayBoardNew(int[][] b)
		{
			String s = new String();
			int[][] board = b;
			
			for (int x = 0;x < 7;x++)
				{
					System.out.print(7-x +"  ");
					for (int y = 0; y< 8;y++)
						{
							switch(board[x][y])
							{
								case (0):
									s = "--";
								break;
								case (3):
									s = "b";
								break;
								case(-3):
									s = "B";
								break;
								default:
									s = "0";
									break;
							}
							System.out.printf("%3s ", s);
						}
					System.out.println();
				}
			System.out.println("     -----------------------------");
			System.out.println("     A   B   C   D   E   F   G   H");
		}
}
