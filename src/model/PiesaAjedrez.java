package model;

public abstract class PiesaAjedrez {
    private Tablero[][] board;
    private Tablero location;
    private Jugador player;
    private int moves;
    public abstract String getInitial();

    public abstract boolean isValidMove(Tablero dest);
        public boolean canMoveTo(Tablero dest) {
            if (location.equals(dest)) return false;
            if (dest.getPiesa() != null) {
                if (player.getColor() == dest.getPiesa().getPlayer().getColor()) {
                    return false;
                }
            }

            if (!isValidMove(dest))  return false;
            Tablero kingLoc = null;
		
            if (this instanceof Rey) {
	
                kingLoc = dest;
            }else {
		kingLoc = getPlayer().getRey().getLocation();
            }
            if (getPlayer().getRey().inCheck(kingLoc)) return false;
        	return true;
            }

    public boolean clearPathTo(Tablero dest) {
        if (location.getX() == dest.getX() && location.getY() != dest.getY()) {
            int curr = Math.min(location.getY(), dest.getY()) + 1;
            int end = Math.max(location.getY(), dest.getY()) - 1;

            while (curr <= end) {
		if (board[curr++][location.getX()].getPiesa() != null) return false;
            }
            return true;
        }else if (location.getX() != dest.getX() && location.getY() == dest.getY()) {
            int curr = Math.min(location.getX(), dest.getX()) + 1;
            int end = Math.max(location.getX(), dest.getX()) - 1;

            while (curr <= end) {
                if (board[location.getY()][curr++].getPiesa() != null) return false;
            }
            return true;
	}
	else if (Math.abs(location.getX() - dest.getX()) == Math.abs(location.getY() - dest.getY())) {
            int dx = (location.getX() < dest.getX()) ? 1 : -1;
            int dy = (location.getY() < dest.getY()) ? 1 : -1;
            int currX  = location.getX() + dx;
            int currY = location.getY() + dy;

            while ((dx == 1 && currX < dest.getX()) || (dx == -1 && currX > dest.getX())){
		if (board[currY][currX].getPiesa() != null){
                return false;
            }
                currX += dx;
		currY += dy;
            }
            return true;
	}
	return false;
    }

	public Tablero getLocation() { return location; }

	public void setLocation(Tablero location) { this.location = location; }

	public Jugador getPlayer() { return player; }

	public void setPlayer(Jugador player) { this.player = player; }

	public Tablero[][] getBoard() { return board; }

	public void incrementMoves() { moves++; }

	public int numberOfMoves() { return moves; }
	
	public void setNumberOfMoves(int moves) { this.moves = moves; }

	public void setBoard(Tablero[][] board) { this.board = board; }

}


