package model;

public class Rey extends PiesaAjedrez {


	public String getInitial() { return "K"; }

	public boolean isValidMove(Tablero dest)
	{
		int xPos = Math.abs( dest.getX() - getLocation().getX());
		int yPos= Math.abs( dest.getY() - getLocation().getY());

		if (((xPos) <=1 && (yPos) <=1)) return true;

		if (xPos != 2 || numberOfMoves() != 0 || dest.getY() != getLocation().getY())
		{

			return false;
		}

		//Check for the location of a rook
		int xPosRook;
		if(dest.getX() == 6)
			xPosRook =7;
		else
			xPosRook = 0;

		PiesaAjedrez castlingRook = getBoard()[xPosRook][getLocation().getY()].getPiesa();

		if (!(castlingRook instanceof Torre)|| castlingRook.numberOfMoves() != 0 ||castlingRook == null)
		{

			return false;
		} 

		if (xPosRook == 7)
		{

			return getBoard()[5][getLocation().getY()].getPiesa() == null &&  getBoard()[6][getLocation().getY()].getPiesa() == null; 
		} 
		else 
		{
			return getBoard()[1][getLocation().getY()].getPiesa() == null &&  getBoard()[2][getLocation().getY()].getPiesa() == null &&  getBoard()[3][getLocation().getY()].getPiesa() == null;
		}


	}

	public boolean inCheck(Tablero kingLoc) {

		for (PiesaAjedrez chessP: getPlayer().getOponente().getPieces())
		{
			if (chessP.getLocation() == null) {
				return false;

			}
			if (chessP.isValidMove(kingLoc) && chessP.getLocation() != null)
			{
				return true;
			}

		}

		return false;
	}	

	public boolean checkmate(Tablero kingLoc) {

		Tablero forward = new Tablero(kingLoc.getY() + 1, kingLoc.getX());
		Tablero backward = new Tablero(kingLoc.getY() - 1, kingLoc.getX());
		Tablero rightUp = new Tablero(kingLoc.getY() + 1, kingLoc.getX() + 1);
		Tablero leftUp = new Tablero(kingLoc.getY() + 1, kingLoc.getX() - 1);
		Tablero rightDown = new Tablero(kingLoc.getY() - 1, kingLoc.getX() + 1);
		Tablero leftDown = new Tablero(kingLoc.getY() - 1, kingLoc.getX() - 1);

		if (forward.getX() >= 0 && forward.getX() <= 7
				&& forward.getY() >= 0 && forward.getY() <= 7) {

			if (!inCheck(forward) && getBoard()[forward.getY()][forward.getX()].getPiesa() == null) return false;

		}
		if (backward.getX() >= 0 && backward.getY() <= 7
				&& backward.getY() >= 0 && backward.getY() <= 7) {

			if (!inCheck(backward) && getBoard()[backward.getY()][backward.getX()].getPiesa() == null) return false;
		}
		if (rightUp.getX() >= 0 && rightUp.getY() <= 7
				&& rightUp.getY() >= 0 && rightUp.getY() <= 7) {

			if (!inCheck(rightUp) && getBoard()[rightUp.getY()][rightUp.getX()].getPiesa() == null) return false;
		}
		if (leftUp.getX() >= 0 && leftUp.getY() <= 7
				&& leftUp.getY() >= 0 && leftUp.getY() <= 7) {

			if (!inCheck(leftUp) && getBoard()[leftUp.getY()][leftUp.getX()].getPiesa() == null) return false;
		}
		if (rightDown.getX() >= 0 && rightDown.getY() <= 7
				&& rightDown.getY() >= 0 && rightDown.getY() <= 7) {

			if (!inCheck(rightDown) && getBoard()[rightDown.getY()][rightDown.getX()].getPiesa() == null) return false;
		}
		if (leftDown.getX() >= 0 && leftDown.getY() <= 7
				&& leftDown.getY() >= 0 && leftDown.getY() <= 7) {

			if (!inCheck(leftDown) && getBoard()[leftDown.getY()][leftDown.getX()].getPiesa() == null) return false;
		}

		return true;

	}
}
