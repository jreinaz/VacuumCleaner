package edu.uoc.uocleaner.model;

public class Vacuum extends Sprite implements Movable{
	private int MAX_CAPACITY;
	private int capacity;
	private Sprite under;
	
	public Vacuum(int row, int column, int maxCapacity) throws SpriteException, VacuumException {
		super(row, column, Symbol.VACUUM);
		if(maxCapacity <= 0) {
			throw new VacuumException(VacuumException.ERROR_MAX_CAPACITY_VALUE);
		}else {
			this.MAX_CAPACITY = maxCapacity;
		}
		this.setCapacity(0);
		this.setUnder(new Corridor(row,column));
	}
	public int getCapacity() {
		return capacity;
	}
	public void incCapacity(int increment) throws VacuumException {
		if(increment < 0) {
			throw new VacuumException(VacuumException.ERROR_INC_CAPACITY_NEGATIVE_VALUE);
		}else if(capacity + increment > this.MAX_CAPACITY){
			throw new VacuumException(VacuumException.ERROR_OVERFLOW_MAX_CAPACITY);
		}else {
			this.capacity = capacity + increment;
		}
	}
	public void setCapacity(int capacity) throws VacuumException {
		if(capacity < 0) {
			throw new VacuumException(VacuumException.ERROR_CAPACITY_NEGATIVE_VALUE);
		}else if(capacity > this.MAX_CAPACITY){
			throw new VacuumException(VacuumException.ERROR_OVERFLOW_MAX_CAPACITY);
		}else {
			this.capacity = capacity;
		}
	}
	public void empty() throws VacuumException {
		if(capacity < 0) {
			throw new VacuumException(VacuumException.ERROR_CAPACITY_NEGATIVE_VALUE);
		}else if(capacity > this.MAX_CAPACITY){
			throw new VacuumException(VacuumException.ERROR_OVERFLOW_MAX_CAPACITY);
		}else {
			this.capacity = 0;
		}
	}
	public int getMaxCapacity() {
		return MAX_CAPACITY;
	}
	public Sprite getUnder() {
		return under;
	}
	public void setUnder(Sprite under) {
		this.under = under;
	}
	
	@Override
	public void moveTo(int row, int column) throws SpriteException {
		if(row < 0 && column >= 0) {
			throw new SpriteException(SpriteException.ERROR_INDEX_ROW_INCORRECT);
		}else if(row >= 0 && column < 0) {
			throw new SpriteException(SpriteException.ERROR_INDEX_COLUMN_INCORRECT);
		}else if(row < 0 && column < 0) {
			throw new SpriteException();
		}else {
			this.setRow(row);
			this.setColumn(column);
		}
		
	}
	
}