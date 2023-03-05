package edu.uoc.uocleaner.model;

public enum Symbol {
	DUSTBALL('@',"dustball.png"),
	DUMPSTER('D',"dumpster.png"),
	VACUUM('V',"huocver.png"),
	DIRT('·',"dirt.png"),
	WALL('#',""),
	CORRIDOR(' ',"");
	private char ascii;
	private String image;
	
	private Symbol(char ascii, String image) {
		this.ascii = ascii;
		this.image = image;
	}

	public static Symbol getName(char ascii) {
		Symbol ans = null;
		switch(ascii) {
		case '@':
			ans = DUSTBALL;
		break;
		
		case 'D':
			ans = DUMPSTER;
		break;
		
		case 'V':
			ans = VACUUM;
		break;
		
		case '·':
			ans = DIRT;
		break;
		
		case '#':
			ans = WALL;
		break;
		
		default:
			ans = CORRIDOR;
		break;
		}		
	return ans;
	}
	
	public char getAscii() {
		return ascii;
	}
	
	public void setAscii(char ascii) {
		this.ascii = ascii;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.ascii);
	}
}
