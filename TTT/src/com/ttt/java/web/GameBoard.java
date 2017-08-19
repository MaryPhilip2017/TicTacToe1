package com.ttt.java.web;

import java.io.Serializable;
import java.util.Random;

public class GameBoard implements Serializable {
	
	private int boardSize = 3;
	private char [][] board = new char[boardSize][boardSize];
	private char user = 'U';
	private char machine = 'M';
	
	private char currentPlayer = ' ';
	private String winner;
	private int maxNumOfMoves = boardSize * boardSize;
	private int numOfMovesMade = 0;
	boolean gameOver = false;
	
	public GameBoard()
	{
		boardInitialize();
	}

	public String getWinner()
	{
		return winner;
	}
	
	public char[][] getBoard()
	{
		return board;
	}
	
	/**
	 * Re-initializes the board and restarts game.
	 */
	public void boardInitialize() 
	{
	    for (int i = 0; i < boardSize ; i++)
	    {
	        for (int j = 0; j < boardSize ; j++)
	        {
	            board [i][j]= '-';
	        }
	    }
	    winner = "None";
	    currentPlayer = 0;
	    numOfMovesMade = 0;
	}
	
	/*
	 * User makes a move.
	 */
	public void userPlay(int row, int column)
	{
		play(user, row, column);
		// Now that user completes his move, system makes its own move.
		play(machine, generateRandomRow(), generateRandomColumn());
			
	}

	/**
	 * If the proposed move is not illegal, make the change. In either case, determine the winner.
	 * @param player
	 * @param row
	 * @param col
	 */
	private void play(char player, int row, int col)
	{
		if (!isIllegalMove(row, col))
		{
			currentPlayer = player;
			changeBoard(player, row, col);
			
			System.out.println("Number of moves completed = " + numOfMovesMade);
			determineTheWinner();
		}
		else
		{
			System.out.println("Player "+ player + " attempted an illegal move at [" + row + ", " + col + "]");
			System.out.println("Number of moves completed = " + numOfMovesMade);
			determineTheWinner();
			return;
		}
		
	}
	
	private void determineTheWinner()
	{
		if (checkIfWinner() || !checkIfTie() || (numOfMovesMade >= maxNumOfMoves))
		{
			gameOver = true;
			winner = String.valueOf(currentPlayer);
		}
	}
	
	private boolean isIllegalMove(int row, int column) 
	{
		if( (row > 2 || column > 2) || (row < 0 || column < 0) )
		{
			System.out.println("The cell is outside the board. Row = "+ row + " column = " + column);
	        return true;
		}
	     else 
	    	 if(board[row][column] == 'U' || board[row][column] == 'M')
	    	 {
	    		 System.out.println("This cell [" + row + ", " + column + "] is already full = "+ board[row][column]);
	    		 return true;
	    	 }
	    	 else
	    	 {
	    		 System.out.println("This cell [" + row + ", " + column + "] is empty");
	    		 return false;
	    	 }
	}

	// Updating the board to make requested move, if move is valid and game is still on.
	private void changeBoard(char player, int row, int column)
	{
	    board[row][column]=player;
	    numOfMovesMade++;

	}


	public void displayBoard() 
	{

	    System.out.println("  0  " + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
	    System.out.println("    --+-+--");
	    System.out.println("  1  " + board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
	    System.out.println("    --+-+--");
	    System.out.println("  2  " + board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
	    System.out.println("     0 1 2 ");
	}


	public boolean checkIfWinner() {
		boolean isWinner = checkRowsForThisPlayer(currentPlayer) || checkColumnsForThisPlayer(currentPlayer) || checkDiagonalsForThisPlayer(currentPlayer) ;
		return isWinner;
	}

	private boolean checkRowsForThisPlayer(char player)
	{
		boolean isWinner = false;
		for (int i = 0; i < boardSize ; i++)
		{
			char[] row = board[i];
			for (int j = 0 ; j < boardSize; j++)
			{
				if (row[j] == player)
					isWinner = true;
				else
					isWinner = false;
			}
		}
		System.out.println("Is currentPlayer " + player + " a winner for row? " + isWinner );
		return isWinner;
	}
	
	private boolean checkColumnsForThisPlayer(char player)
	{
		boolean isWinner = false;
		int colCount = 0;
		while (colCount < boardSize)
		{
			for (int i = 0 ; i < boardSize ; i++)
			{
				if (board[i][colCount] == player)
					isWinner = true;
				else
					isWinner = false;
			}
			colCount++;
		}
		System.out.println("Is currentPlayer " + player + " a winner for column? " + isWinner );
		return isWinner;
	}

	private boolean checkDiagonalsForThisPlayer(char player)
	{
		boolean isWinner = false;
		// Top-left to right-bottom
		for (int i = 0; i < boardSize ; i++)
		{
			if (board[i][i] == player)
					isWinner = true;
				else
					isWinner = false;
		}
		// Bottom-left to right-top
		for (int i = 0, j = boardSize-1 ; i < boardSize && j >= 0 ; i++, j--)
		{
			if (board[i][j] == player)
					isWinner = true;
				else
					isWinner = false;
		}
		
		
		System.out.println("Is currentPlayer " + player + " a winner for diagonal? " + isWinner );
		return isWinner;
	}
	
	private boolean checkIfTie() {
	    for (int i = 0; i < boardSize ; i++)
	        for (int p=0; p < boardSize ; p++)
	            if(board [i][p]==' ')
	                return false;

	    return true;
	}

	private int generateRandomRow()
	{
		Random rand = new Random();
		return rand.nextInt(3);
	}
	
	private int generateRandomColumn()
	{
		Random rand = new Random();
		return rand.nextInt(3);
	}


}
