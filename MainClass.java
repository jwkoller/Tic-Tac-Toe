package koller.practice.tictactoe;

import java.util.Scanner;

public class MainClass 
{
	public static final String[] MENU_OPTIONS = {"Main Menu", ".........", "New Tic-Tac-Toe Game", "Quit Program"};
	
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		
		displayWelcomeBanner();
		
		while(validateMainMenu(input) == 'A')
		{
			game.setNewGame();

			while(game.getGameStatus() == 0)
			{
				displayGameBoard(game.getGameBoard());
				game.setPlayerSelection(validatePlayerInput(input, game.getPlayers(), game.getPlayerTurn(), game.getGameBoard()));
			}
			
			displayGameBoard(game.getGameBoard());
			displayGameResult(game.getPlayerTurn(), game.getPlayers(), game.getGameStatus());
		}
		
		displayFarewellBanner();

	}	//end of main method
	
	public static void displayWelcomeBanner()
	{
		System.out.println("************************************************************");
		System.out.printf("%1s%23s%-35s%1s", "*", "", "Tic-Tac-Toe", "*");
		System.out.printf("\n%1s%4s%-54s%1s", "*", "", "This program will let two players play the classic", "*");
		System.out.printf("\n%1s%20s%-38s%1s", "*", "", "Tic-Tac-Toe game.", "*");
		System.out.println("\n************************************************************");
	}
	
	public static void displayFarewellBanner()
	{
		System.out.println("************************************************************");
		System.out.printf("%1s%12s%-46s%1s", "*", "", "Thank you for playing Tic-Tac_Toe.", "*");
		System.out.printf("\n%1s%13s%-45s%1s", "*", "", "Enjoy your the rest of your day!", "*");
		System.out.println("\n************************************************************");
	}
	
	public static void displayMainMenu()
	{
		System.out.printf("\n%1s%-59s", "", "__________________________________________________________");
		System.out.printf("\n%1s%59s", "|", "|");
		System.out.printf("\n%1s%24s%-34s%1s", "|", "", MENU_OPTIONS[0], "|");
		System.out.printf("\n%1s%24s%-34s%1s", "|", "", MENU_OPTIONS[1], "|");
		System.out.printf("\n%1s%17s%-4s%-37s%1s", "|", "", "[A]", MENU_OPTIONS[2], "|");
		System.out.printf("\n%1s%17s%-4s%-37s%1s", "|", "", "[Q]", MENU_OPTIONS[3], "|");
		System.out.printf("\n%60s","|__________________________________________________________|\n");
		System.out.print("\nPlease enter your selection here: ");
	}
	
	public static void displayInvalidEntry()
	{
		System.out.println("\n............................................................");
		System.out.printf("%13s%-47s", "", "Invalid entry.  Please try again.");
		System.out.println("\n............................................................");
	}
	
	public static char validateMainMenu(Scanner borrowedInput)
	{
		char localMenuSelect = ' ';
		
		displayMainMenu();
		localMenuSelect = borrowedInput.next().toUpperCase().charAt(0);
		
		while(localMenuSelect != 'A' && localMenuSelect != 'Q')
		{
			displayInvalidEntry();
			displayMainMenu();
			localMenuSelect = borrowedInput.next().toUpperCase().charAt(0);
		}
		
		return localMenuSelect;
	}

	public static void displayGameBoard(char[][] borrowedGameBoard)
	{
		System.out.println("\n-------------------");
		System.out.printf("%1s%3s%3s%3s%3s%3s%3s%41s", "|", borrowedGameBoard[0][0], "|", borrowedGameBoard[0][1], "|", borrowedGameBoard[0][2], "|", "");
		System.out.println("\n-------------------");
		System.out.printf("%1s%3s%3s%3s%3s%3s%3s%41s", "|", borrowedGameBoard[1][0], "|", borrowedGameBoard[1][1], "|", borrowedGameBoard[1][2], "|", "");
		System.out.println("\n-------------------");
		System.out.printf("%1s%3s%3s%3s%3s%3s%3s%41s", "|", borrowedGameBoard[2][0], "|", borrowedGameBoard[2][1], "|", borrowedGameBoard[2][2], "|", "");
		System.out.println("\n-------------------");
	}
	
	public static void displayPlayerRowPrompt(String borrowedPlayer)
	{
		System.out.println("\nEnter a row (1 top, 2 middle, 3 bottom) for Player " + borrowedPlayer + ".");
		System.out.print("Row Number: ");
	}
	
	public static void displayPlayerColumnPrompt(String borrowedPlayer)
	{
		System.out.println("\nEnter a column (1 left, 2 middle, 3 right) for Player " + borrowedPlayer + ".");
		System.out.print("Column number: ");
	}
	
	public static void displayInvalidSquare()
	{
		System.out.println("\nThat square is already taken, please choose another.");
	}
	
	public static String[] validatePlayerInput(Scanner borrowedInput, char[] borrowedPlayers, int borrowedPlayerTurn, char[][] borrowedGameBoard)
	{
		boolean localValidSquare = false;
		String[] localInput = new String[2];
		
		while(localValidSquare == false)
		{
			displayPlayerRowPrompt(String.valueOf(borrowedPlayers[borrowedPlayerTurn]));
			localInput[0] = borrowedInput.next().trim();
			
			while(!localInput[0].equals("1") && !localInput[0].equals("2") && !localInput[0].equals("3"))
			{
				displayInvalidEntry();
				displayPlayerRowPrompt(String.valueOf(borrowedPlayers[borrowedPlayerTurn]));
				localInput[0] = borrowedInput.next().trim();
			}
			
			displayPlayerColumnPrompt(String.valueOf(borrowedPlayers[borrowedPlayerTurn]));
			localInput[1] = borrowedInput.next().trim();
			
			while(!localInput[1].equals("1") && !localInput[1].equals("2") && !localInput[1].equals("3"))
			{
				displayInvalidEntry();
				displayPlayerColumnPrompt(String.valueOf(borrowedPlayers[borrowedPlayerTurn]));
				localInput[1] = borrowedInput.next().trim();
			}
			
			if(borrowedGameBoard[Integer.parseInt(localInput[0]) - 1][Integer.parseInt(localInput[1]) - 1] == 'X' || 
					borrowedGameBoard[Integer.parseInt(localInput[0]) - 1][Integer.parseInt(localInput[1]) - 1] == 'O')
			{
				displayInvalidSquare();
			}
			else
			{
				localValidSquare = true;
			}
		}
		
		return localInput;
	}
	
	public static void displayGameResult(int borrowedPlayerTurn, char[] borrowedPlayers, int borrowedGameStatus)
	{
		System.out.println("\n************************************************************");
		System.out.printf("%-30s%30s", "*", "*");
		if(borrowedGameStatus == 1)
		{
			if(borrowedPlayerTurn == 0)
			{
				System.out.printf("\n%1s%17s%21s%1s%1s%19s", "*", "", "The winner is Player ", String.valueOf(borrowedPlayers[1]), "!", "*");
			}
			else
			{
				System.out.printf("\n%1s%17s%21s%1s%1s%19s", "*", "", "The winner is Player ", String.valueOf(borrowedPlayers[0]), "!", "*");
			}
		}
		else
		{
			System.out.printf("\n%1s%16s%-42s%1s", "*", "", "The game ended in a draw!", "*");
		}
		System.out.printf("\n%-30s%30s", "*", "*");
		System.out.println("\n************************************************************");
		
	}
	
}	//end of main class
