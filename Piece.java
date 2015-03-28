import java.util.ArrayList;


public class Piece
	{
	private int locationX = 0;
	private int locationY = 0;
	private int pieceCode = 0;
	private String codeChar;
	private char owner;
	private int gasleft = 3;
	
	public int setLocation(int row, int column)
	{
	if (locationX < 0 || locationX > 6) return -1;
	if (locationY < 0 || locationY > 7) return -1;
	locationX = row;
	locationY = column;
	return 0;
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

	public String getCodeChar() {
		return codeChar;
	}

	public void setCodeChar(String codeChar) {
		this.codeChar = codeChar;
	}

	public char getOwner() {
		return owner;
	}

	public void setOwner(char owner) {
		this.owner = owner;
	}

	public int getGasleft()
		{
		return gasleft;
		}

	public void setGasleft(int gasleft)
		{
		this.gasleft = gasleft;
		}
	
	
	public ArrayList<String> theMoves(int[][] board)
	{
		return null;
	}
	
	
	}
