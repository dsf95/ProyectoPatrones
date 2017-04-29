package model;

public class Alfil extends PiesaAjedrez {
	
	public String getInitial() { return "B"; }

	public boolean isValidMove(Tablero dest) {
		int xPos = Math.abs( dest.getX() - getLocation().getX());
		int yPos= Math.abs( dest.getY() - getLocation().getY());
		if(xPos != yPos) 
		{
			return false;
		}
		return this.clearPathTo(dest);	
	}

}
