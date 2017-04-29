package model;

public class Torre extends PiesaAjedrez {

    public String getInitial() { return "R"; }

    public boolean isValidMove(Tablero dest) {
        if (getLocation().getY() != dest.getY() && getLocation().getX() != dest.getX()) return false;	
        return this.clearPathTo(dest);
    }
	
}
