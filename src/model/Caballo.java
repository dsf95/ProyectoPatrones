package model;

public class Caballo extends PiesaAjedrez {

    public String getInitial() { return "N"; }
	
    public boolean isValidMove(Tablero dest){
	int xPos = Math.abs( dest.getX() - getLocation().getX());
	int yPos= Math.abs( dest.getY() - getLocation().getY());
        
	if ((xPos == 2 && yPos == 1)) return true;
	if ((xPos == 1 && yPos == 2)) return true;
	return false;
    }

}
