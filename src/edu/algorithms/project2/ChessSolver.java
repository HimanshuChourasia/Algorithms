/*+----------------------------------------------------------------------
||
||  Class ChessSolver
||
||         Author:  Himanshu
||
||        Purpose:  This class has the main() method and it has methods relating all the chess pieces 
||
||  Inherits From:  None
||
||     Interfaces:  None
||
|+-----------------------------------------------------------------------
||
||      Constants:  int ROW = 8
||					int COLUMN = 8
|+-----------------------------------------------------------------------
||
||   Constructors:  ChessSolver(String PoString) 
||  				Initializes the chess board and initializes Maps 
||					Assigns a memory to a Map. 
||
||  Class Methods:  ChessSolver addPosition(String poString) returns ChessSolver object
||					printAllChessboardPositions() returns void
||					ChessSolver transferBoardStatus(ChessSolver chessSolver) returns ChessSolver object
||					convertPositionTo2DMatrix(String poString) returns String
||					checkPiecePosition(String poString,boolean initialPos)	returns boolean		
||                  checkPosition(String poString) returns boolean
||					
||
||  Inst. Methods:  int board[][] ;
||					String piece ;
||					Map <Integer,String> column  ; 
||					Map <String,Integer> rColumn ;
||
++-----------------------------------------------------------------------*/
package edu.algorithms.project2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Himanshu
 *
 */
public class ChessSolver {
	
	static int  COLUMN = 8;
	static int  ROW = 8;
	int board[][] ;
	String piece ;
	Map <Integer,String> column  ; 
	Map <String,Integer> rColumn ;
	/**
	 * @param board
	 */
	public ChessSolver() {
		board = new int[ROW][COLUMN];
		 for(int i = 0; i < board.length ; i++)
		 {	
			 for (int j = 0 ;j < board.length ; j++)
			 {
				 board[i][j] = 0 ;
			 }
			 
		 }
	
		 /* Initially  the chessboard pieces are occupied assuming there is only one player on the chess */
			
		 for (int i = 0; i <= 1  ; i++)
			{
			 for(int j = 0 ;j < board.length ; j++)
				board[i][j] = 1 ;
			}
		 column = new HashMap<>();
		 rColumn = new HashMap<>() ;
		 for (int i = 0 ;  i < 8 ;i++)
		 {
			 
			 char alphabet = (char) ( 'a' + i) ;
			 String salphabet = Character.toString(alphabet);
			 column.put(i,salphabet);
			 rColumn.put(salphabet, i) ;
		 }
		 
		 
	}
	/* This method adds the position to the chessBoard*/
	public ChessSolver addPosition(String poString) {
		String matrixPos = convertPositionTo2DMatrix(poString);
		int column = Integer.parseInt(matrixPos.split(",")[1])  ;
		int row = Integer.parseInt(matrixPos.split(",")[0])  ;
		this.board[row][column] = 1 ;
		return this ;
		
	}
	
	
	/* This method prints the initial chessboard positions*/
	public void printAllChessboardPositions() {
		String pos = "" ;
		String defaultString = "The positions are named as " ;
		for(int i = 0,j =1 ;i< board.length ; i++,j++)
		{
			if (i == 0 || i == 1 || i == 7) {
				
					for (Map.Entry<Integer, String> entry: column.entrySet()) {
						pos = pos + j + entry.getValue() + ",";
						
					}
					pos = pos.substring(0,pos.length()-1);
					System.out.print(defaultString + pos +"\n");
					pos = "" ;
				}
			else {
				if (i == 2)
				{
					System.out.println("==========================================================================================================");
				}
				
			}
			
		}
		
	}
	
	/* This method  transfers board state to the current invoking object*/
	
	public ChessSolver transferBoardStatus(ChessSolver chessSolver) {
		this.board = chessSolver.board ;
		return this ;
	}
	
	/* Converts the chessBoard data into 2DMatrix */
	
	public String convertPositionTo2DMatrix(String poString) {
		String matrixPos = "" ;
		int  row = Integer.parseInt(Character.toString(poString.charAt(0))) - 1 ;
		char cSecond = poString.charAt(1);
		int column = this.rColumn.get(Character.toString(cSecond));
		matrixPos = row + "" + ","+ column + "" ;
		return matrixPos ;
	}
	
	/* Check piece position is it occupied or not */
	
	public boolean checkPiecePosition(String poString,boolean initialPos) {
		if (!initialPos)
		{
		String matrixPos = convertPositionTo2DMatrix(poString);
		int column = Integer.parseInt(matrixPos.split(",")[1]) ;
		int row = Integer.parseInt(matrixPos.split(",")[0]) ;
			if (this.board[row][column] == 1)
			{
				return false;
			}
			else {
				return true ;
			}
		}
		else {
			return true ;
		}
	}
	
	/* Check the entered position */
	
	public boolean checkPosition(String poString) {
		int len = poString.length();
		if (len > 2)
		{
			return false;
		}
		else {
			char cFirst = poString.charAt(0);
			char cSecond = poString.charAt(1);
			if (cFirst == '1' || cFirst == '2' || cFirst == '3' || cFirst == '4' || cFirst == '5' || cFirst == '6' || cFirst == '7' || cFirst == '8') {
				if (cSecond == 'a' || cSecond == 'b' || cSecond == 'c' || cSecond == 'd' || cSecond == 'e' || cSecond == 'f' || cSecond == 'g' || cSecond == 'h') {
					return true;
					
				}
				else {
					return false;
				}
			}
			else {
				return false ;
			}
			
			
		}
		
	}

	/* main() method */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	String chessPiece = "" ;
	String exitChoice = "yes" ;
	String piecelist = "1. Rook 2. Knight 3. Bishop 4. Queen 5. King 6. Pawn" ;
	String position = "" ;
	Map<Integer, String> moves ;
	Scanner scanner  = new Scanner(System.in);
	ChessSolver cSolver = new ChessSolver() ;
	System.out.println("All the 8 rows are named using numbers [1-8]");
	System.out.println("All the 8 columns are named using alphabets [a-h]");
	cSolver.printAllChessboardPositions();
	System.out.println("******************************************Let's start the game now****************************************");
	do {
		System.out.println("You can Select one chess piece from the following : "+piecelist);
		System.out.println("Enter piece name ");
		chessPiece = scanner.nextLine();
		if (chessPiece.equalsIgnoreCase("Pawn")) {
			System.out.println("Enter the position of the  " + chessPiece.toLowerCase());
			position = scanner.nextLine();
			if (cSolver.checkPosition(position)) {
				Pawn pawn = new Pawn(position);
				boolean initialPos = pawn.checkInitialPosition(position);
					if (cSolver.checkPiecePosition(position,initialPos)) {
						pawn.transferBoardStatus(cSolver);
						moves = pawn.possibleMoves();
						pawn.print(moves);
						int possibleMoves = pawn.getNoOfPossibleMoves() - 1;
						if (possibleMoves >= 0)
						{
							System.out.println("Select the position number( 0 - "+possibleMoves+""+" )"+ " you want the "+chessPiece.toLowerCase()+" to move to");
							try {
								int positionNum = Integer.parseInt(scanner.nextLine());
						if (positionNum >=0 && positionNum <= possibleMoves) {
							String chessPosition = moves.get(positionNum);
							System.out.println("finally selected details row: "+chessPosition.charAt(0)+"  column: "+ chessPosition.charAt(1));
							cSolver = cSolver.addPosition(chessPosition);
							System.out.println("Do you want to continue [yes/no]");
							exitChoice = scanner.nextLine() ;
						}
						else {
							System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
						}
					} catch (NumberFormatException e) {
						// TODO: handle exception
						System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
					}					
					}
					else {
						System.out.println("No Possible moves for the "+chessPiece.toLowerCase()+" :");
					}
				}
					
				else {
					System.out.println("This Position is filled. Please select a different position");
				}
				
			}
			else {
				System.out.println("Invalid Position .Enter positions such as :");
				cSolver.printAllChessboardPositions();
			}
			
		}
		else if (chessPiece.equalsIgnoreCase("Rook")) {
			System.out.println("Enter the position of the  " + chessPiece.toLowerCase());
			position = scanner.nextLine();
			if (cSolver.checkPosition(position)) {
				Rook rook = new Rook(position) ;
				boolean initialPos = rook.checkInitialPosition(position) ;
				if (cSolver.checkPiecePosition(position,initialPos)) {
					rook.transferBoardStatus(cSolver);
					moves = rook.possibleMoves();
					rook.print(moves);
					int possibleMoves = rook.getNoOfPossibleMoves() - 1;
					if (possibleMoves >= 0)
					{
						System.out.println("Select the position number( 0 - "+possibleMoves+""+" )"+ " you want the "+chessPiece.toLowerCase()+" to move to");
						try {
							int positionNum = Integer.parseInt(scanner.nextLine());
					if (positionNum >=0 && positionNum <= possibleMoves) {
						String chessPosition = moves.get(positionNum);
						System.out.println("finally selected details row: "+chessPosition.charAt(0)+"  column: "+ chessPosition.charAt(1));
						cSolver = cSolver.addPosition(chessPosition);
						System.out.println("Do you want to continue [yes/no]");
						exitChoice = scanner.nextLine() ;
					}
					else {
						System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
				}					
				}
				else {
					System.out.println("No Possible moves for the "+chessPiece.toLowerCase()+" :");
				}
				}
				else {
					System.out.println("This Position is filled. Please select a different position");
				}
				
			}
			else {
				System.out.println("Invalid Position .Enter positions such as :");
				cSolver.printAllChessboardPositions();
			}
			
		}
		else if (chessPiece.equalsIgnoreCase("Knight")) {
			System.out.println("Enter the position of the  " + chessPiece.toLowerCase());
			position = scanner.nextLine();
			if (cSolver.checkPosition(position)) {
				Knight knight = new Knight(position);
				boolean initialPos = knight.checkInitialPosition(position) ;
				if (cSolver.checkPiecePosition(position,initialPos)) {
				
					knight.transferBoardStatus(cSolver);
					moves = knight.possibleMoves();
					knight.print(moves);
					int possibleMoves = knight.getNoOfPossibleMoves() - 1;
					if (possibleMoves >= 0)
					{
						System.out.println("Select the position number( 0 - "+possibleMoves+""+" )"+ " you want the "+chessPiece.toLowerCase()+" to move to");
						try {
							int positionNum = Integer.parseInt(scanner.nextLine());
					if (positionNum >=0 && positionNum <= possibleMoves) {
						String chessPosition = moves.get(positionNum);
						System.out.println("finally selected details row: "+chessPosition.charAt(0)+"  column: "+ chessPosition.charAt(1));
						cSolver = cSolver.addPosition(chessPosition);
						System.out.println("Do you want to continue [yes/no]");
						exitChoice = scanner.nextLine() ;
					}
					else {
						System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
				}					
				}
				else {
					System.out.println("No Possible moves for the "+chessPiece.toLowerCase()+" :");
				}
				}
				else {
					System.out.println("This Position is filled. Please select a different position");
				}
				
			}
			else {
				System.out.println("Invalid Position .Enter positions such as :");
				cSolver.printAllChessboardPositions();
			}
		}
		else if (chessPiece.equalsIgnoreCase("Bishop")) {
			System.out.println("Enter the position of the  " + chessPiece.toLowerCase());
			position = scanner.nextLine();
			if (cSolver.checkPosition(position)) {
				Bishop bishop = new Bishop(position);
				boolean initialPos = bishop.checkInitialPosition(position) ;
				if (cSolver.checkPiecePosition(position,initialPos)) {
					bishop.transferBoardStatus(cSolver);
					moves = bishop.possibleMoves();
					bishop.print(moves);
					int possibleMoves = bishop.getNoOfPossibleMoves() - 1;
					if (possibleMoves >= 0)
					{
						System.out.println("Select the position number( 0 - "+possibleMoves+""+" )"+ " you want the "+chessPiece.toLowerCase()+" to move to");
						try {
							int positionNum = Integer.parseInt(scanner.nextLine());
					if (positionNum >=0 && positionNum <= possibleMoves) {
						String chessPosition = moves.get(positionNum);
						System.out.println("finally selected details row: "+chessPosition.charAt(0)+"  column: "+ chessPosition.charAt(1));
						cSolver = cSolver.addPosition(chessPosition);
						System.out.println("Do you want to continue [yes/no]");
						exitChoice = scanner.nextLine() ;
					}
					else {
						System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
				}					
				}
				else {
					System.out.println("No Possible moves for the "+chessPiece.toLowerCase()+" :");
				}
				}
				else {
					System.out.println("This Position is filled. Please select a different position");
				}
				
			}
			else {
				System.out.println("Invalid Position .Enter positions such as :");
				cSolver.printAllChessboardPositions();
			}
		}
		else if (chessPiece.equalsIgnoreCase("Queen")) {
			System.out.println("Enter the position of the  " + chessPiece.toLowerCase());
			position = scanner.nextLine();
			if (cSolver.checkPosition(position)) {
				Queen queen = new Queen(position);
				boolean initialPos = queen.checkInitialPosition(position) ;
				if (cSolver.checkPiecePosition(position,initialPos)) {
					queen.transferBoardStatus(cSolver);
					moves = queen.possibleMoves();
					queen.print(moves);
					int possibleMoves = queen.getNoOfPossibleMoves() - 1;
					if (possibleMoves >= 0)
					{
						System.out.println("Select the position number( 0 - "+possibleMoves+""+" )"+ " you want the "+chessPiece.toLowerCase()+" to move to");
						try {
							int positionNum = Integer.parseInt(scanner.nextLine());
					if (positionNum >=0 && positionNum <= possibleMoves) {
						String chessPosition = moves.get(positionNum);
						System.out.println("finally selected details row: "+chessPosition.charAt(0)+"  column: "+ chessPosition.charAt(1));
						cSolver = cSolver.addPosition(chessPosition);
						System.out.println("Do you want to continue [yes/no]");
						exitChoice = scanner.nextLine() ;
					}
					else {
						System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
				}					
				}
				else {
					System.out.println("No Possible moves for the "+chessPiece.toLowerCase()+" :");
				}
				}
				else {
					System.out.println("This Position is filled. Please select a different position");
				}
			}
			else {
				System.out.println("Invalid Position .Enter positions such as :");
				cSolver.printAllChessboardPositions();
			}
		}
		else if (chessPiece.equalsIgnoreCase("King")) {
			System.out.println("Enter the position of the  " + chessPiece.toLowerCase());
			position = scanner.nextLine();
			if (cSolver.checkPosition(position)) {
				King king = new King(position);
				boolean initialPos = king.checkInitialPosition(position) ;
				if (cSolver.checkPiecePosition(position,initialPos)) {
					king.transferBoardStatus(cSolver);
					moves = king.possibleMoves();
					king.print(moves);
					int possibleMoves = king.getNoOfPossibleMoves() - 1;
					if (possibleMoves >= 0)
					{
						System.out.println("Select the position number( 0 - "+possibleMoves+""+" )"+ " you want the "+chessPiece.toLowerCase()+" to move to");
						try {
							int positionNum = Integer.parseInt(scanner.nextLine());
					if (positionNum >=0 && positionNum <= possibleMoves) {
						String chessPosition = moves.get(positionNum);
						System.out.println("finally selected details row: "+chessPosition.charAt(0)+"  column: "+ chessPosition.charAt(1));
						cSolver = cSolver.addPosition(chessPosition);
						System.out.println("Do you want to continue [yes/no]");
						exitChoice = scanner.nextLine() ;
					}
					else {
						System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("Please enter the position number between "+"( 0 - "+possibleMoves+""+" )");
				}					
				}
				else {
					System.out.println("No Possible moves for the "+chessPiece.toLowerCase()+" :");
				}
				}
				else {
					System.out.println("This Position is filled. Please select a different position");
				}
				
			}
			else {
				System.out.println("Invalid Position .Enter positions such as :");
				cSolver.printAllChessboardPositions();
			}
		}
		else {
			System.out.println("Invalid Chess piece .. Enter one of the following: "+piecelist);
		}
	} while (exitChoice.equalsIgnoreCase("yes"));
		System.out.println("Hope you enjoyed playing the game\n");
		System.out.println("Thank you");
		scanner.close();
	}
	
}

 

