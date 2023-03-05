package edu.uoc.uocleaner.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

public class DustBall extends Dirt implements Movable{

	public DustBall(int row, int column) throws SpriteException {
		super(row, column,Symbol.DUSTBALL,0);
		this.setScore​(5);
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
	
	private boolean validMove​(Level level,int row,int column) {
		boolean ans = false;
		if(level.getCell(row, column) instanceof Corridor || level.getCell(row, column) instanceof Dirt) {
			ans = true;
		}
		return ans;
	}
	
	public Collection<int[]> moveRandomly(Level level) {
		ArrayList<int[]> availablePlaces = new ArrayList<int[]>(); 
		int [] arriba = new int[2];
		arriba[0] = this.getRow() - DustBall.SPEED;
		arriba[1] = this.getColumn();
		int [] derecha = new int[2];
		derecha[0] = this.getRow();
		derecha[1] = this.getColumn()+DustBall.SPEED;
		int [] abajo = new int[2];
		abajo[0] = this.getRow() + DustBall.SPEED;
		abajo[1] = this.getColumn();
		int [] izquierda = new int[2];
		izquierda[0] = this.getRow();
		izquierda[1] = this.getColumn()-DustBall.SPEED;
		if(this.getColumn() == 0) {
			if(this.getRow() == 0) {
				//peor caso solo tiene dos posibilidades esquina superior izq 
				//posiciones posibles derecha y abajo.
				if(this.validMove​(level, derecha[0], derecha[1])) {
					availablePlaces.add(derecha);
				}
				if(this.validMove​(level, abajo[0], abajo[1])) {
					availablePlaces.add(abajo);
				}
			}else if(this.getRow() == level.getNumRows()-1) {
				//peor caso solo tiene dos posibilidades esquina inferior izq
				//posiciones posibles derecha y arriba
				if(this.validMove​(level, derecha[0], derecha[1])) {
					availablePlaces.add(derecha);
				}
				if(this.validMove​(level, arriba[0], arriba[1])) {
					availablePlaces.add(arriba);
				}
			}else {
				//tiene tres posibilidades punto medio borde izq
				//posiciones posibles derecha arriba y abajo
				if(this.validMove​(level, derecha[0], derecha[1])) {
					availablePlaces.add(derecha);
				}
				if(this.validMove​(level, abajo[0], abajo[1])) {
					availablePlaces.add(abajo);
				}
				if(this.validMove​(level, arriba[0], arriba[1])) {
					availablePlaces.add(arriba);
				}
			}
		}else if(this.getColumn() == level.getNumColumns()-1) {
			if(this.getRow() == 0) {
				//peor caso solo tiene dos posibilidades esquina superior der
				//posiciones posibles izquierda y abajo
				if(this.validMove​(level, izquierda[0], izquierda[1])) {
					availablePlaces.add(izquierda);
				}
				if(this.validMove​(level, abajo[0], abajo[1])) {
					availablePlaces.add(abajo);
				}
			}else if(this.getRow() == level.getNumRows()-1) {
				//peor caso solo tiene dos posibilidades esquina inferior der
				//posiciones posibles izquierda y arrriba
				if(this.validMove​(level, izquierda[0], izquierda[1])) {
					availablePlaces.add(izquierda);
				}
				if(this.validMove​(level, arriba[0], arriba[1])) {
					availablePlaces.add(arriba);
				}
			}else {
				//tiene tres posibilidades punto medio borde der
				//posiciones posibles izquierda arriba y abajo
				if(this.validMove​(level, izquierda[0], izquierda[1])) {
					availablePlaces.add(izquierda);
				}
				if(this.validMove​(level, abajo[0], abajo[1])) {
					availablePlaces.add(abajo);
				}
				if(this.validMove​(level, arriba[0], arriba[1])) {
					availablePlaces.add(arriba);
				}
			}
		}else {
			if(this.getRow() == 0) {
				//peor caso solo tiene tres posibilidades
				//posiciones posibles izquierda derecha abajo
				if(this.validMove​(level, derecha[0], derecha[1])) {
					availablePlaces.add(derecha);
				}
				if(this.validMove​(level, abajo[0], abajo[1])) {
					availablePlaces.add(abajo);
				}
				if(this.validMove​(level, izquierda[0], izquierda[1])) {
					availablePlaces.add(izquierda);
				}
			}else if(this.getRow() == level.getNumRows()-1) {
				//peor caso solo tiene tres posibilidades
				//posiciones posibles izquierda derecha arriba
				if(this.validMove​(level, derecha[0], derecha[1])) {
					availablePlaces.add(derecha);
				}
				if(this.validMove​(level, arriba[0], arriba[1])) {
					availablePlaces.add(arriba);
				}
				if(this.validMove​(level, izquierda[0], izquierda[1])) {
					availablePlaces.add(izquierda);
				}
			}else {
				//tiene cuatro posibilidades
				if(this.validMove​(level, derecha[0], derecha[1])) {
					availablePlaces.add(derecha);
				}
				if(this.validMove​(level, abajo[0], abajo[1])) {
					availablePlaces.add(abajo);
				}
				if(this.validMove​(level, izquierda[0], izquierda[1])) {
					availablePlaces.add(izquierda);
				}
				if(this.validMove​(level, arriba[0], arriba[1])) {
					availablePlaces.add(arriba);
				}
			}
		}
		double rand1 = ThreadLocalRandom.current().nextDouble(0, 1);
		if(rand1 >= 0.75) {
			if(availablePlaces.size() > 0) {
				int rand2 = ThreadLocalRandom.current().nextInt(0,availablePlaces.size());
				try{
					level.setCell(this.getRow(), this.getColumn(), new Dirt(this.getRow(),this.getColumn()));
					this.moveTo(availablePlaces.get(rand2)[0], availablePlaces.get(rand2)[1]);
					level.setCell(this);
				}catch(SpriteException se) {
					System.out.println(se.getMessage());
				}
			}else {
				System.out.println("ARRAYLIST VACIO");
			}
		}
		return availablePlaces; 
	}	
}