import java.util.ArrayList;
import java.util.Arrays;


public class Bishop extends Piece
{
	//private int posX, posY, boardCode;
	//private char owner;
	///private char characterCode;
	//private ArrayList<String> moves;
	//private ArrayList<String> humanMoves;// = new ArrayList<String>();
	//private ArrayList<String> compMoves;// = new ArrayList<String>();
	
	
	public Bishop(int x, int y, int code, char owner, char pcode)
		{
			setLocation(x,y);
			//this.posX = x;
			//this.posY = y;
			//this.boardCode = code;
			setCodeChar(pcode);
			setOwner(owner);
			setPieceCode(code);
			//this.characterCode = pcode;
			setGasleft(3);
			//setLocation(posX, posY);
		//	humanMoves = new ArrayList<String>();
		//	compMoves = new ArrayList<String>();
			ArrayList<String> moves = new ArrayList<String>();
			setMoves(moves);
			//tempBoard = new int[7][8];
		}
	
	
	
	public ArrayList<String> theMoves(int[][] board)
	{
	//	int[][]tempBoard = new int[7][8];
		//tempBoard = board;
		goDiagonal(1,1,board);
		goDiagonal(-1,1, board);
		goDiagonal(1,-1,board);
		goDiagonal(-1,-1,board);
		return getMoves();
		//return moves;
	}
		
	public void goDiagonal(int incrementA, int incrementB, int[][] board)
		{
			int x,y,a,b, opposingPiece;
			int codeModifier = getCodeModifier();
			//System.out.println("codeMod init: " + codeModifier);
			int[][]tempBoard = new int[7][8];
			tempBoard = board;	
			a = getLocationX();
			b = getLocationY();
			a+=incrementA;
			b+=incrementB;
			x= a % 7;
			y= b % 8;
			while ((a>= 0 && a < 7 && b < 8 && b >= 0)) 
				{
					//int[][]tempBoard2 = new int[7][8];
					//tempBoard2 = board;
					opposingPiece = tempBoard[x][y];
					opposingPiece *= codeModifier;
					//System.out.println("Opposing piece = " + opposingPiece + "codeModifier = " + codeModifier);
					if((opposingPiece == 6 || opposingPiece < 0) && opposingPiece != 1)	//enemy piece
						{
					tempBoard[x][y] = 6;
					String move = (String.valueOf(getLocationX())) + String.valueOf(getLocationY()) + String.valueOf(x) + String.valueOf(y);
					getMoves().add(move);
					break;
					}
					if((opposingPiece == 6 || opposingPiece > 0) && opposingPiece != 1)	
						{
					tempBoard[x][y] = 7;
					String move = (String.valueOf(getLocationX())) + String.valueOf(getLocationY()) + String.valueOf(x) + String.valueOf(y);
					getMoves().add(move);
					break;
					}
					if(opposingPiece == 1) {break;} //own king: this will need to change if we can take own pieces
					if(opposingPiece == 0 || opposingPiece == 5)
					{
					//System.out.println("up-right a: " + a + " b: " + b);
					tempBoard[x][y] = 5;
					String move = (String.valueOf(getLocationX())) + String.valueOf(getLocationY()) + String.valueOf(x) + String.valueOf(y);
					getMoves().add(move);
					a+=incrementA;
					b+=incrementB;
					x= a % 7;
					y= b % 8;
					}
				}
		}

}
