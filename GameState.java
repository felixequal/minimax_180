
public class GameState
	{

		int[][] savedBoard;
		Piece deletedPiece;
		int oldX, oldY, currentX, currentY;
		int prevGas;
		
	public int getPrevGas()
			{
			return prevGas;
			}


		public void setPrevGas(int prevGas)
			{
			this.prevGas = prevGas;
			}


	public int getOldX()
			{
			return oldX;
			}


		public void setOldX(int oldX)
			{
			this.oldX = oldX;
			}


		public int getOldY()
			{
			return oldY;
			}


		public void setOldY(int oldY)
			{
			this.oldY = oldY;
			}


		public int getCurrentX()
			{
			return currentX;
			}


		public void setCurrentX(int currentX)
			{
			this.currentX = currentX;
			}


		public int getCurrentY()
			{
			return currentY;
			}


		public void setCurrentY(int currentY)
			{
			this.currentY = currentY;
			}


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
