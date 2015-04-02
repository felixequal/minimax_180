
public class GameState
	{

		int[][] savedBoard;
		Piece deletedPiece;
		
	public GameState(int[][] oldBoard)
		{
			savedBoard = new int[7][8];
			for (int x = 0; x < oldBoard.length; x++)
				{
					for (int y = 0; y<oldBoard[0].length;y++)
						{
							savedBoard[x][y] = oldBoard[x][y];
						}
					
				}
			
		
		//savedBoard = oldBoard;
		}
	
	
	public int[][] getBoard()
	{
		return savedBoard;
	}
	
	public void saveDeletedPiece(Piece piece)
	{
		deletedPiece = piece;
	}
	
	public Piece getDeletedPiece()
	{
		return deletedPiece;
		
	}
	
	public void display()
	{
		int blank = 0;
		for (int x = 0;x < 7;x++)
			{
				System.out.print(x +"  ");
				for (int y = 0; y< 8;y++)
					{
						System.out.printf("%3d ", savedBoard[x][y]);
					}
				System.out.println();
			}
		System.out.println("     -----------------------------");
		System.out.println("     0   1   2   3   4   5   6   7");
		System.out.println("Deleted Piece----------------");
		if(deletedPiece!= null){
			deletedPiece.info();	
		}
		
		//System.out.println(deletedPiece.getOwner() + deletedPiece.getGasleft())
		
	}

	}
