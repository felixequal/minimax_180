import java.util.ArrayList;


public abstract class Piece
	{
	private int locationX = 0;
	private int locationY = 0;
	private int pieceCode = 0;
	private char codeChar ='x';
	private char owner = 'x';
	private int gasleft = 3;
	private int codeModifier = 0;
	
	public int setLocation(int row, int column)
	{
	if (locationX < 0 || locationX > 6) return -1;
	if (locationY < 0 || locationY > 7) return -1;
	locationX = row;
	locationY = column;
	return 0;
	}
	
	public void setCodeModifier()
	{
		if (owner == 'c') codeModifier = -1;
		if (owner == 'h') codeModifier = 1;
		//System.out.println("CodeMod in Piece: " + codeModifier);
	}
	
	public int getCodeModifier()
	{
		return codeModifier;
	}
	
	public int getLocationX()
	{
		if (locationX < 0 || locationX > 6) return -1;
		else return locationX;
		
	
	}
		
	public int getLocationY()
	{
	if (locationY < 0 || locationY > 7) return -1;
	else return locationY;
	}

	public int getPieceCode() {
		return pieceCode;
	}

	public void setPieceCode(int pieceCode) {
		this.pieceCode = pieceCode;
	}

	public char getCodeChar() {
		return codeChar;
	}

	public void setCodeChar(char codeChar) {
		this.codeChar = codeChar;
	}

	public char getOwner() {
		return owner;
	}

	public void setOwner(char owner) {
		this.owner = owner;
		setCodeModifier();
	}

	public int getGasleft()
		{
		return gasleft;
		}

	public void setGasleft(int gasleft)
		{
		this.gasleft = gasleft;
		}
	
	
	
	public void print()
		{
			String output = "" +getCodeChar()+getGasleft();
			//System.out.printf("Owner: %c character code: %c gasleft %d\n", getOwner(), getCodeChar(), getGasleft());
			System.out.printf("%4s", output);
		}
	
	
	public ArrayList<String> theMoves(int[][] board)
	{
	return null;	
	}

	public ArrayList<String> getMoves()
		{
		// TODO Auto-generated method stub
		return null;
		}

	
	
	}
