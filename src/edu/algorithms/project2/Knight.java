/*+----------------------------------------------------------------------
||
||  Class Pawn
||
||         Author:  Himanshu
||
||        Purpose:  This class has functions and operations pertaining to Knight Chess piece.
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
||   Constructors:  Knight(String PoString) 
||  				Initializes the Position of chess piece object
||					Assigns a memory to a Map. 
||
||  Class Methods:  checkKnightMove(int row,int column) returns boolean
||					convertToChessPositions(int row, int column) returns boolean
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
public class Knight extends ChessSolver {
	String poString;
	int noOfPossibleMoves ;
	Map<Integer, String> moves ;

	/**
	 * @param poString
	 */
	public Knight(String poString) {
		super();
		this.poString = poString;
		this.moves = new HashMap<Integer,String>() ;
	}
	
	public boolean checkKnightMove(int row,int column) {
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

	/* method to convert to chess positions */
	
	
	public String convertToChessPositions(int row, int column) {
		// FOR row increment by 1
		row++;
		String position = row+""+ this.column.get(column);
		return position ;
	}
	
	/* check if the position provided is occupied or not */
	
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
	
	/* Map possible moves */
	
	public Map<Integer,String> doMapping(int row, int column,int key) {
		
			// remapping the move to chessblock move
			String position = convertToChessPositions(row, column) ; 
			this.moves.put(key,position);
			return this.moves ;
	}
	
	/* Calculate possible moves */
	
	public Map<Integer,String> possibleMoves() {
		int key = 0 ;
		String pos = convertPositionTo2DMatrix(poString);
		int column = Integer.parseInt(pos.split(",")[1])  ;
		int row = Integer.parseInt(pos.split(",")[0])  ;
		int tempRow = row,tempColumn = column ;
		
			/* Calculating Possible move for a Knight */
			// Left Move along straight Row
			tempRow = row + 2 ;
			tempColumn = column - 1 ;
			// Add to moves if valid and not occupied
			if (checkKnightMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
				this.moves = doMapping(tempRow, tempColumn, key);
				key++ ;
			
				}
		
			// Right Move along straight Row
			tempRow = row + 2 ;
			tempColumn = column + 1 ;
		// Add to moves if valid and not occupied
		if (checkKnightMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
			this.moves = doMapping(tempRow, tempColumn, key);
			key++ ;
			}
		// Left Move along Backward Row
		tempRow = row - 2 ;
		tempColumn = column - 1 ;
		// Add to moves if valid and not occupied
		if (checkKnightMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
			this.moves = doMapping(tempRow, tempColumn, key);
			key++ ;
			}
		// Right Move along Backward Row
		
		tempRow = row - 2 ;
		tempColumn = column + 1 ;
		// Add to moves if valid and not occupied
		if (checkKnightMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
			this.moves = doMapping(tempRow, tempColumn, key);
			key++ ;
		}
		
		// Forward Move along Backward Column
		
				tempRow = row + 1 ;
				tempColumn = column - 2 ;
				// Add to moves if valid and not occupied
				if (checkKnightMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
					this.moves = doMapping(tempRow, tempColumn, key);
					key++ ;
				}
			
				// Backward Move along Backward Column
				
				tempRow = row - 1 ;
				tempColumn = column - 2 ;
				// Add to moves if valid and not occupied
				if (checkKnightMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
					this.moves = doMapping(tempRow, tempColumn, key);
					key++ ;
				}	
				
				// Forward Move along Forward Column
				
				tempRow = row + 1 ;
				tempColumn = column + 2 ;
				// Add to moves if valid and not occupied
				if (checkKnightMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
					this.moves = doMapping(tempRow, tempColumn, key);
					key++ ;
				}
				
				// Backward Move along Forward Column
				
				tempRow = row - 1 ;
				tempColumn = column + 2 ;
				// Add to moves if valid and not occupied
				if (checkKnightMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
					this.moves = doMapping(tempRow, tempColumn, key);
					key++ ;
				}
				
				
				
		return moves;
		
	}
	
	/* Print all the possible moves */
	
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
	
	/* get the number of possible moves */
	
	public int getNoOfPossibleMoves() {
		return noOfPossibleMoves;
	}

	/* check for initial position */
	
	public boolean checkInitialPosition (String poString) {
		char cFirst = poString.charAt(0);
		char cSecond = poString.charAt(1);
		if (cFirst == '1') {
			if (cSecond == 'b' || cSecond == 'g' ) {
				
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
