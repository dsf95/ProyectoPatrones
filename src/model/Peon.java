package model;

public class Peon extends PiesaAjedrez {

    public String getInitial() { return "P"; }

    public boolean isValidMove(Tablero dest) {

        int yPos = getPlayer().getColor() == ColorJugador.WHITE ? 1 : -1;
        if (getLocation().getX() == dest.getX()){
            return (dest.getPiesa() == null 
            && getLocation().getY() == dest.getY() + yPos)||(numberOfMoves() == 0 
            && dest.getPiesa() == null 
            && getLocation().getY() == dest.getY() + (2*yPos));
        }

        if (Math.abs(getLocation().getX() - dest.getX()) == 1 && getLocation().getY() == dest.getY() + yPos) {	
            if (dest.getPiesa() != null) return true;
            PiesaAjedrez piece = getBoard()[getLocation().getY()][dest.getX()].getPiesa();
            if (piece != null && piece instanceof Peon && piece.numberOfMoves() == 1) {
                if (piece.getPlayer().getColor() == getPlayer().getColor()) return false;
                return ((getPlayer().getColor() == ColorJugador.WHITE && dest.getY() == 2 || getPlayer().getColor() == ColorJugador.BLACK && dest.getY() == 5));
            }
        }
        return false;
    }

}
