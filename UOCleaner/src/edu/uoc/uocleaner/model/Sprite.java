package edu.uoc.uocleaner.model;

public class Sprite {
	private int row;
	private int column;
	private Symbol symbol;
	
	protected Sprite(int row, int column, Symbol symbol) throws SpriteException{
		setRow(row);
		setColumn(column);
		this.symbol = symbol;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) throws SpriteException{
		if(row < 0) {
			throw new SpriteException(SpriteException.ERROR_INDEX_ROW_INCORRECT);
		}else {
			this.row = row;
		}
	}

	public int getColumn() {
		return this.column;
	}

	public void setColumn(int column) throws SpriteException{
		if(column < 0) {
			throw new SpriteException(SpriteException.ERROR_INDEX_COLUMN_INCORRECT);
		}else {
			this.column = column;
		}
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public boolean equals(Object object) {
		boolean ans = false;
		if(object.getClass()==this.getClass() && ((Sprite) object).getRow() == this.getRow() && ((Sprite) object).getColumn() == this.getColumn()) {
			ans = true;
		}
		return ans;
	}
	
	@Override
	public String toString() {
		return this.getSymbol().toString();
	}
}
