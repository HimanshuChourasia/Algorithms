/*+----------------------------------------------------------------------
||
||  Class Pawn
||
||         Author:  Himanshu
||
||        Purpose:  This class has functions and operations pertaining to Pawn Chess piece.
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
||   Constructors:  Pawn(String PoString) 
||  				Initializes the Position of chess piece object
||					Assigns a memory to a Map. 
||
||  Class Methods:  checkPawnMove(int row,int column) returns boolean
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
public class Pawn extends ChessSolver{
	String poString;
	int noOfPossibleMoves ;
	Map<Integer, String> moves ;

	/**
	 * @param poString
	 */
	public Pawn(String poString) {
		super();
		this.poString = poString;
		this.moves = new HashMap<Integer,String>() ;
	}
	
	/* Check if move made is out of chessboard or not */
	public boolean checkPawnMove(int row,int column) {
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
		if (checkInitialPosition(poString)) {
			/* Calculating Possible move for a pawn */
			// Straight move + 2
			tempRow = row + 2 ;
			// Add to moves if valid and not occupied
			if (checkPawnMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
				this.moves = doMapping(tempRow, tempColumn, key);
				key++ ;
			
				}
		}
			// Straight Move + 1
		tempRow = row + 1 ;
		// Add to moves if valid and not occupied
		if (checkPawnMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
			this.moves = doMapping(tempRow, tempColumn, key);
			key++ ;
			}
		// Right Diagonal Attack
		tempRow = row + 1;
		tempColumn = column + 1 ;
		// Add to moves if valid and not occupied
		if (checkPawnMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
			this.moves = doMapping(tempRow, tempColumn, key);
			key++ ;
			}
		// Left Diagonal Attack
		
		tempRow = row + 1 ;
		tempColumn = column - 1 ;
		// Add to moves if valid and not occupied
		if (checkPawnMove(tempRow, tempColumn) && checkPositionOccupied(tempRow, tempColumn)) {
			this.moves = doMapping(tempRow, tempColumn, key);
			key++ ;
		}
		return moves;
		
	}
	/* print the possible moves */
	
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
	
	/* get possible moves */
	
	public int getNoOfPossibleMoves() {
		return noOfPossibleMoves;
	}

	/* check initial position  */
	
	public boolean checkInitialPosition (String poString) {
		char cFirst = poString.charAt(0);
		char cSecond = poString.charAt(1);
		if (cFirst == '2') {
			if (cSecond == 'a' || cSecond == 'b' || cSecond == 'c' || cSecond == 'd' || cSecond == 'e' || cSecond == 'f' || cSecond == 'g' || cSecond == 'h') {
				
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
