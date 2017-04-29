package model;

public class Reina extends PiesaAjedrez {

    public String getInitial() { return "Q"; }

    public boolean isValidMove(Tablero dest) {
        if ((getLocation().getX() == dest.getX()) ||getLocation().getY() == dest.getY() ||
            Math.abs(getLocation().getX() - dest.getX()) == Math.abs(getLocation().getY() - dest.getY())){
        
            return this.clearPathTo(dest);
	}
	return false;
    }

}
