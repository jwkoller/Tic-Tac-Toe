package koller.practice.tictactoe;

public class TicTacToe 
{
	private final char[] PLAYERS = {'X', 'O'};
	private final int ROWS = 3;
	private final int COLUMNS = 3;
	private final int RESET_VALUE = 0;
	private final int MAX_TURNS = 9;
	private final int GAME_WIN = 1;
	private final int GAME_DRAW = 2;
	private char[][] gameBoard;
	private int playerTurn = 0;
	private int totalTurns = 0;
	
	public void setNewGame()
	{
		gameBoard = new char[ROWS][COLUMNS];
		playerTurn = RESET_VALUE;
		totalTurns = RESET_VALUE;
	}
	
	public void setPlayerSelection(String[] borrowedPlayerSelection)
	{
		if(playerTurn == 0)
		{
			gameBoard[Integer.parseInt(borrowedPlayerSelection[0]) - 1][Integer.parseInt(borrowedPlayerSelection[1]) - 1] = PLAYERS[0];
			playerTurn++;
		}
		else
		{
			gameBoard[Integer.parseInt(borrowedPlayerSelection[0]) - 1][Integer.parseInt(borrowedPlayerSelection[1]) - 1] = PLAYERS[1];
			playerTurn--;
		}
		totalTurns++;
	}
	
	public int getGameStatus()
	{
		int localRow = 0;
		int localColumn = 0;
		int localGameState = 0;

		//Check diagonals first if there's a winner
		if(((gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2] == 'X'+'X'+'X') || (gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2] == 'O'+'O'+'O')) ||
				((gameBoard[2][0] + gameBoard[1][1] + gameBoard[0][2] == 'X'+'X'+'X') || (gameBoard[2][0] + gameBoard[1][1] + gameBoard[0][2] == 'O'+'O'+'O')))
		{
			localGameState = GAME_WIN;
		}

		else
		{
			while(localRow < ROWS)	//check each row
			{
				if((gameBoard[localRow][localColumn] + gameBoard[localRow][localColumn + 1] + gameBoard[localRow][localColumn + 2] == 'X'+'X'+'X') || 
						(gameBoard[localRow][localColumn] + gameBoard[localRow][localColumn + 1] + gameBoard[localRow][localColumn + 2] == 'O'+'O'+'O'))
				{
					localGameState = GAME_WIN;	//game has a winner
					localRow = ROWS;
				}
				else
				{
					localRow++;
				}
			}
			localRow = 0;
			
			while(localColumn < COLUMNS)	//check each column
			{
				if((gameBoard[localRow][localColumn] + gameBoard[localRow + 1][localColumn] + gameBoard[localRow + 2][localColumn] == 'X'+'X'+'X') || 
						(gameBoard[localRow][localColumn] + gameBoard[localRow + 1][localColumn] + gameBoard[localRow + 2][localColumn] == 'O'+'O'+'O'))
				{
					localGameState = GAME_WIN;	//game has a winner
					localColumn = COLUMNS;
				}
				else
				{
					localColumn++;
				}
			}
		}
		
		if(localGameState == 0 && totalTurns == MAX_TURNS)
		{
			localGameState = GAME_DRAW;		//game ends in draw
		}
		
		return localGameState;
	}
	
	public int getPlayerTurn()
	{
		return playerTurn;
	}
	
	public char[][] getGameBoard()
	{
		return gameBoard;
	}
	
	public char[] getPlayers()
	{
		return PLAYERS;
	}
	
}	//end of TIcTacToe class
