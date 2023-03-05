package edu.uoc.uocleaner.model;

public class Wall extends Sprite{

	public Wall(int row, int column) throws SpriteException{
		super(row, column, Symbol.WALL);
	}

}
