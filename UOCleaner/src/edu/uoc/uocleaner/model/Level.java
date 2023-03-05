package edu.uoc.uocleaner.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 
 * Level/Room class. 
 * @author David García Solórzano
 * @version 1.0 
 */
public class Level{
	/**
	 * Number of rows of the board.
	 */
	private int numRows;
	/**
	 * Number of columns of the board.
	 */
	private int numColumns;
	/**
	 * Representation of the 2D board.
	 */
	private Sprite[][] board;
	/**
	 * Name of the background image for the GUI app.
	 */
	private String imageBackground;
	/**
	 * Number of turns which the player has in order to beat the level.
	 */
	private int turns;	
	/**
	 * Maximum time which the player has in order to beat the level.
	 */
	private int time;
		
	/**
	 * Constructor 
	 * @param fileName Name of the configuration file which has all the information of the level.
	 * @throws FileNotFoundException When it is impossible to open the configuration file.
	 * @throws LevelException When the number of rows or columns are zero or negative; 
	 * when the number of turns or time is negative; when there is not only one vacuum cleaner; when there are not dumpsters; 
	 * when there are not dirts or dustballs.
	 * @throws VacuumException When the value for the vacuum cleaner's capacity is zero or negative.
	 * @throws SpriteException When the index of either the row or the column is incorrect.
	 */
	public Level(String fileName) throws FileNotFoundException, LevelException, VacuumException, SpriteException {
		char[] line = null;
		boolean isDumpster = false, isDirt = false;
		int numVacuums = 0;		
		
		Scanner sc = new Scanner(new File(fileName));

		// find the number of rows and columns       
        setNumRows(Integer.parseInt(sc.nextLine()));
        setNumColumns(Integer.parseInt(sc.nextLine()));
        setImageBackground(sc.nextLine());
        setTurns(Integer.parseInt(sc.nextLine()));
        setTime(Integer.parseInt(sc.nextLine()));
        
        board = new Sprite[numRows][numColumns];           
        for (int row = 0; row < numRows; row++) {
        	line = sc.nextLine().toCharArray();        	
			for (int column = 0; column < numColumns; column++) {				
				board[row][column] = SpriteFactory.getSprite(row,column,Symbol.getName(line[column]));				
				if(board[row][column] instanceof Dirt) isDirt = true;
				if(board[row][column] instanceof Vacuum) numVacuums++;
				if(board[row][column] instanceof Dumpster) isDumpster = true;								
			}			
        }        
        
        sc.close();       
                
        if(numVacuums!=1) {
        	throw new LevelException(LevelException.ERROR_NUM_VACUUMS);
        }
        
        if(!isDumpster) {
        	throw new LevelException(LevelException.ERROR_NO_DUMPSTERS);
        }
        
        if(!isDirt) {
        	throw new LevelException(LevelException.ERROR_NO_DIRT);
        }        
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}

	public String getImageBackground() {
		return imageBackground;
	}

	public void setImageBackground(String imageBackground) {
		this.imageBackground = imageBackground;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}	
	
	public void decTurns() throws LevelException{
		if(this.getTurns() == 0) {
			throw new LevelException(LevelException.ERROR_NUM_TURNS_INCORRECT);
		}else {
			this.setTurns(this.getTurns()-1);
		}
	}
	
	public void decTime() throws LevelException{
		if(this.getTime() == 0) {
			throw new LevelException(LevelException.ERROR_NUM_TIME_INCORRECT);
		}else {
			this.setTime(this.getTime()-1);
		}
	}
	
	public List<Sprite> get1DBoard(){
		ArrayList<Sprite> answerBoard = new ArrayList<Sprite>();
		for(int row = 0; row < numRows;row++){
			for(int column = 0; column < numColumns;column++){
				if(board[row][column] != null) {
					answerBoard.add(board[row][column]);
				}
			}
		}
		return answerBoard;
	}
	
	public Sprite getCell(int row, int column) {
		return this.board[this.putRowInRange(row)][this.putColumnInRange(column)];
	}
	//supposing that the sprite existed previously
	//modify the cell sprite
	public void setCell(Sprite sprite) {
		this.board[sprite.getRow()][sprite.getColumn()] = sprite;
	}
	
	//supposing that the sprite existed previously
	//modify the cell sprite
	public void setCell(int row, int column,Sprite sprite) throws SpriteException{
		/*this.board[sprite.getRow()][sprite.getColumn()] = null;*/
		if(row != sprite.getRow() || column != sprite.getColumn()) {
			sprite.setRow(row);
			sprite.setColumn(column);
		}
		this.board[row][column] = sprite;
	}
	
	private int putRowInRange(int row) {
		if(row < 0) {
			row = 0;
		}else if(row >= this.numRows) {
			row = this.numRows-1;
		}
		return row;
	}
	
	private int putColumnInRange(int column) {
		if(column < 0) {
			column = 0;
		}else if(column >= this.numColumns) {
			column = this.numColumns-1;
		}
		return column;
	}
	
	@Override
	public String toString() {
		String ans = "";
		for(int row = 0; row < numRows;row++){
			for(int column = 0; column < numColumns;column++){
				if(board[row][column] != null) {
					ans = ans + board[row][column].getSymbol().toString();
				}
			}
			ans = ans + "\n";
		}
		ans = ans + "\n";
		return ans;
	}
	
}