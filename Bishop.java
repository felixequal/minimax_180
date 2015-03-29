import java.util.ArrayList;
import java.util.Arrays;


public class Bishop extends Piece
{
	//private int posX, posY, boardCode;
	//private char owner; //what
	///private char characterCode;
	private ArrayList<String> moves;
	
	
	public Bishop(int x, int y, int code, char owner, char pcode )
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
			moves = new ArrayList<String>();
			//tempBoard = new int[7][8];
		}
	
	
	
	public ArrayList<String> theMoves(int[][] board)
	{
		String move;
		int a = getLocationX();
		int b = getLocationY();
		int x,y;
		int opposingPiece;
		int[][]tempBoard = new int[7][8];
		tempBoard = board;
		goDiagonal(1,1,tempBoard);
		goDiagonal(-1,1, tempBoard);
		goDiagonal(1,-1,tempBoard);
		goDiagonal(-1,-1,tempBoard);
		//System.out.println("Current Location: " + a + ", " + b);
		
		
		//if((setLocation(a+1,b+1)) == 0){ a+=1; b+=1;}
		//System.out.println("Current Location: " + a + ", " + b);
		/* x= (++a) % 7;
		y= (++b) % 8;
		while ((a> 0 && a < 7 && b < 8 && b > 0)) 
			{
				//int[][]tempBoard = new int[7][8];
				//tempBoard = board;
				opposingPiece = tempBoard[x][y];
				if(-(opposingPiece) < 0) {tempBoard[x][y] = 5; break;}
				if(-(opposingPiece) > 0) {break;}
				//	System.out.println("down-right before a: " + a + " b: " + b);
				tempBoard[x][y] = 5;
				move = String.valueOf(getLocationX()) + String.valueOf(getLocationY()) + String.valueOf(x) + String.valueOf(y);
				moves.add(move);
				x= (++a) % 7;
				y= (++b) % 8;
				//System.out.println("down-right after a: " + a + " b: " + b);
				//System.out.println("down-right after x: " + x + " y: " + y);
			} 
		a = getLocationX();
		b = getLocationY();
		x= (--a) % 7;
		y= (++b) % 8;
		while ((a>= 0 && a < 7 && b < 8 && b >= 0)) 
			{
				//int[][]tempBoard2 = new int[7][8];
				//tempBoard2 = board;
				opposingPiece = tempBoard[x][y];
				if(-(opposingPiece) < 0) {tempBoard[x][y] = 5; break;}
				if(-(opposingPiece) > 0) {break;}
				//System.out.println("up-right a: " + a + " b: " + b);
				tempBoard[x][y] = 5;
				move = String.valueOf(getLocationX()) + String.valueOf(getLocationY()) + String.valueOf(x) + String.valueOf(y);
				moves.add(move);
				x= (--a) % 7;
				y= (++b) % 8;
				//System.out.println("up-right after a: " + a + " b: " + b);
				//System.out.println("up-right after x: " + x + " y: " + y);
			}
		a = getLocationX();
		b = getLocationY();
		//System.out.println("reset a: " + a + " b: " + b);
		x= (++a) % 7;
		y= (--b) % 8;
		
		while ((a> 0 && a < 7 && b < 8 && b > 0)) 
			{
				//int[][]tempBoard3 = new int[7][8];
				//tempBoard3 = board;
				opposingPiece = tempBoard[x][y];
				if(-(opposingPiece) < 0) {tempBoard[x][y] = 5; break;}
				if(-(opposingPiece) > 0) {break;}
				//	System.out.println("down-left a: " + a + " b: " + b);
				tempBoard[x][y] = 5;
				move = String.valueOf(getLocationX()) + String.valueOf(getLocationY()) + String.valueOf(x) + String.valueOf(y);
				moves.add(move);
				//System.out.println("tempBoard x: " + x + " y: " + y + "value: " + tempBoard[x][y]);
				x= (++a) % 7;
				y= (--b) % 8;
				//	System.out.println("down-left after a: " + a + " b: " + b);
				//System.out.println("down-left after x: " + x + " y: " + y);
				
			}
		
		a = getLocationX();
		b = getLocationY();
		x= (--a) % 7;
		y= (--b) % 8;
		while (a > 0 && a < 7 && b < 8 && b >= 0) 
			{
				//int[][]tempBoard4 = new int[7][8];
				//tempBoard4 = board;
				opposingPiece = tempBoard[x][y];
				if(-(opposingPiece) == 6) 
					{
						tempBoard[x][y] = 6;
						move = String.valueOf(getLocationX()) + String.valueOf(getLocationY()) + String.valueOf(x) + String.valueOf(y);
						moves.add(move);
						break;
					}
				
				if(-(opposingPiece) > 0) {break;}
				if(-(opposingPiece) == 0 || -(opposingPiece) == 5)
					
					//System.out.println("up-left a: " + a + " b: " + b);
					tempBoard[x][y] = 5;
				move = String.valueOf(getLocationX()) + String.valueOf(getLocationY()) + String.valueOf(x) + String.valueOf(y);
				moves.add(move);
				//	System.out.println("tempBoard x: " + x + " y: " + y + "value: " + tempBoard[x][y]);
				x= (--a) % 7;
				y= (--b) % 8;
				//	System.out.println("up-left after a: " + a + " b: " + b);
				//	System.out.println("up-left after x: " + x + " y: " + y);
				
			} 
			*/
		return moves;
		
	}
	
	
	public ArrayList<String> returnMoves(int[][] board)
	{
		return moves;
	}
	
	public ArrayList<String> getMoves() {
		return moves;
	}
	
	public void setMoves(ArrayList<String> moves) {
		this.moves = moves;
	}
	
	public void goDiagonal(int incrementA, int incrementB, int[][] board)
		{
			int x,y,a,b, opposingPiece;
			int codeModifier = getCodeModifier();
			System.out.println("codeMod init: " + codeModifier);
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
					System.out.println("Opposing piece = " + opposingPiece + "codeModifier = " + codeModifier);
					if(opposingPiece == 6 || opposingPiece < 0)	
						{
					tempBoard[x][y] = 6;
					String move = (String.valueOf(7-getLocationX())) + String.valueOf(getLocationY()) + String.valueOf(7-x) + String.valueOf(y);
					moves.add(move);
					break;
					}
					if(opposingPiece > 0  && opposingPiece != 5 && opposingPiece != 6) {break;} //this will need to change if we can take own pieces
					if(opposingPiece == 0 || opposingPiece == 5)
					{
					//System.out.println("up-right a: " + a + " b: " + b);
					tempBoard[x][y] = 5;
					String move = (String.valueOf(7-getLocationX())) + String.valueOf(getLocationY()) + String.valueOf(7-x) + String.valueOf(y);
					moves.add(move);
					a+=incrementA;
					b+=incrementB;
					x= a % 7;
					y= b % 8;
					}
				}
		}
	
}
