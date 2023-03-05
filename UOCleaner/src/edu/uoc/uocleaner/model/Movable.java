package edu.uoc.uocleaner.model;

public interface Movable {
	public static int SPEED = 1;
	public void moveTo(int row, int column)throws SpriteException;
}
