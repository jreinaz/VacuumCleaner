package edu.uoc.uocleaner.model;

public class Dumpster extends Sprite{
	private int load;
	
	public Dumpster(int row, int column) throws SpriteException{
		super(row, column, Symbol.DUMPSTER);
		this.load = 0;
	}
	
	public int getLoad() {
		return this.load;
	}
	
	private void setLoad​(int load)throws DumpsterException{
		if(load < 0) {
			throw new DumpsterException(DumpsterException.ERROR_LOAD_NEGATIVE_VALUE);
		}else {
			this.load = load;
		}
	}

	public void addLoad(int load) throws DumpsterException {
		if(load < 0) {
			throw new DumpsterException(DumpsterException.ERROR_LOAD_NEGATIVE_VALUE);
		}else {
			this.load += load;
		}
	}

	
}
