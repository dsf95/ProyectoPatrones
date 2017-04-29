package model;

import java.io.Serializable;


public class Tablero implements Serializable{	
    private int row;
    private int column;
    private static final long serialVersionUID = 1L;
    private PiesaAjedrez piece; //posición actual  de la pieza
	
    public int getX() { return row; }
	
    public int getY() { return column; }
	
    public Tablero(int r, int c) {
        this.column = r;
	this.row = c;
    }
	
    public PiesaAjedrez getPiesa() { return piece; }
	
    public void setPiesa(PiesaAjedrez piece) { this.piece = piece; }
	
    @Override
    public boolean equals(Object o) {		
        if (!(o instanceof Tablero)) return false;
	if (this.getX() == ((Tablero) o).getX() && this.getY() == ((Tablero) o).getY()) return true;	
	return false;
	}
}
