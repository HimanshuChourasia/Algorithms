/*+----------------------------------------------------------------------
||
||  Class Bishop
||
||         Author:  Himanshu
||
||        Purpose:  This class has functions and operations pertaining to Bishop Chess piece.
||
||  Inherits From:  ChessSolver
||
||     Interfaces:  None
||
|+-----------------------------------------------------------------------
||
||      Constants:  None
|+-----------------------------------------------------------------------
||
||   Constructors:  Bishop(String PoString) 
||  				Initializes the Position of chess piece object
||					Assigns a memory to a Map. 
||
||  Class Methods:  checkBishopMove(int row,int column) returns boolean
||					checkUniquePosition(int row , int column) returns boolean
||					convertToChessPositions(int row, int column) returns boolean
||					checkCurrentPosition(int row, int column)  returns boolean	
||					checkPositionOccupied(int row , int column) returns boolean			
||                  Map<Integer,String> doMapping(int row, int column,int key) returns Map
||					Map<Integer,String> possibleMoves() returns Map
||					print(Map<Integer, String> moves) returns void
||					int getNoOfPossibleMoves() returns integer
||					boolean checkInitialPosition (String poString) returns boolean
||
||  Inst. Methods:  String poString;
||					int noOfPossibleMoves ;
||					Map<Integer, String> moves ;
||
++-----------------------------------------------------------------------*/



package edu.algorithms.project2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Himanshu
 *
 */
public class Bishop extends ChessSolver {
	
	String poString;
	int noOfPossibleMoves ;
	Map<Integer, String> moves ;

	/**
	 * @param poString
	 */
	public Bishop(String poString) {
		super();
		this.poString = poString;
		this.moves = new HashMap<Integer,String>() ;
	}
	/* Check if move made is out of chessboard or not */
	
	public boolean checkBishopMove(int row,int column) {
		if (row < 8 && row >= 0)
		{
			if (column < 8 && column >= 0)
			{
				return true ;
			}
			else {
				return false ;
			}
		}
		else {
			
			return false ;
		}
	}
	
	/* check if the position entered is unique or not  */
	
	public boolean checkUniquePosition(int row , int column) {
		String position = convertToChessPositions(row, column) ; 
		for(String values : this.moves.values())
		{
			if(position.equals(values))
			{
				return false ;
				
			}
			
		}
		return true ;
		
	}
	
	/* check if currentPosition is not in possible moves or not  */
	
	public boolean checkCurrentPosition(int row, int column) {
		String chessPos = convertToChessPositions(row, column);
		if (chessPos.equals(poString))
		{
			return false ;
		}
		else {
			return true ;
		}
	}
	
	/* convert the 2D Matrix position to chess positions */
	
	public String convertToChessPositions(int row, int column) {
		// FOR row increment by 1
		row++;
		String position = row+""+ this.column.get(column);
		return position ;
	}
	
	/* check if position is occupied or not */
	
	public boolean checkPositionOccupied(int row , int column) {
		if (this.board[row][column] == 1)
		{
			return false ;
		}
		else
		{
			return true ;
		}
	}
	
	/* Map chess position */
	
	public Map<Integer,String> doMapping(int row, int column,int key) {
		
			// remapping the move to chessblock move
			String position = convertToChessPositions(row, column) ; 
			this.moves.put(key, position) ;
			return this.moves ;
	}
	
	/* Calculate the possible moves */
	
	public Map<Integer,String> possibleMoves() {
		int key = 0 ;
		String pos = convertPositionTo2DMatrix(poString);
		int column = Integer.parseInt(pos.split(",")[1])  ;
		int row = Integer.parseInt(pos.split(",")[0])  ;
		int tempRow = row,tempColumn = column ;
			/* Calculating Possible move for a Bishop */
			// Upper left diagonal Move 
			while (checkBishopMove(tempRow, tempColumn)) {
				tempRow = tempRow + 1;
				tempColumn = tempColumn -1 ;
				// Add to moves if valid and not occupied
				if (checkBishopMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn) && checkUniquePosition(tempRow, tempColumn) && checkCurrentPosition(tempRow, tempColumn)) {
					this.moves = doMapping(tempRow, tempColumn, key);
					key++ ;
					}
			}
				
			
			tempColumn = column ;
			tempRow = row ;
			// lower Left DiagonalMove
			while (checkBishopMove(tempRow, tempColumn)) {
				tempRow = tempRow - 1;
				tempColumn = tempColumn + 1 ;
				// Add to moves if valid and not occupied
				if (checkBishopMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn) && checkUniquePosition(tempRow, tempColumn) && checkCurrentPosition(tempRow, tempColumn)) {
					this.moves = doMapping(tempRow, tempColumn, key);
					key++ ;
					}
			}
			tempColumn = column ;
			tempRow = row ;
			
			// Upper Right Diagonal
			
			while (checkBishopMove(tempRow, tempColumn)) {
				tempRow = tempRow + 1;
				tempColumn = tempColumn + 1 ;
				// Add to moves if valid and not occupied
				if (checkBishopMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn) && checkUniquePosition(tempRow, tempColumn) && checkCurrentPosition(tempRow, tempColumn)) {
					this.moves = doMapping(tempRow, tempColumn, key);
					key++ ;
					}
			}
			
			tempColumn = column ;
			tempRow = row ;
			
			// Lower Right Diagonal
			
			while (checkBishopMove(tempRow, tempColumn)) {
				tempColumn = tempColumn - 1 ;
				tempRow = tempRow - 1;
				// Add to moves if valid and not occupied
				if (checkBishopMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn) && checkUniquePosition(tempRow, tempColumn) && checkCurrentPosition(tempRow, tempColumn)) {
					this.moves = doMapping(tempRow, tempColumn, key);
					key++ ;
					}
			}
			
			
			return moves;
		
	}
	
	/* print all the possible moves */
	
	public void print(Map<Integer, String> moves) {
		int i = 0 ;
		
		for(String poString : moves.values())
		{
			System.out.println("Possible Position: "+i);
			System.out.println(poString);
			i++ ;
		}
		noOfPossibleMoves = i ;
	}
	
	/* get number of possible moves */
	
	public int getNoOfPossibleMoves() {
		return noOfPossibleMoves;
	}

	/* check for initial position */
	
	public boolean checkInitialPosition (String poString) {
		char cFirst = poString.charAt(0);
		char cSecond = poString.charAt(1);
		if (cFirst == '1') {
			if (cSecond == 'c' || cSecond == 'f') {
				
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			
			return false;
		}
	}

}
