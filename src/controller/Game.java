package controller;

import model.Alfil;
import model.PiesaAjedrez;
import model.ColorJugador;
import model.Rey;
import model.Caballo;
import model.Peon;
import model.Jugador;
import model.Reina;
import model.Torre;
import model.Tablero;

public class Game {

    private Tablero[][] board;
    private PiesaAjedrez[] capturedWhite;
    private PiesaAjedrez[] capturedBlack;
    private int capturedWhiteCount;
    private int capturedBlackCount;
    private Jugador black;
    private Jugador white;
    private Jugador turn;
    private boolean blackInCheck;
    private boolean whiteInCheck;

    public Game() {

        board = new Tablero[8][8];
	black = new Jugador(ColorJugador.BLACK);
	white = new Jugador(ColorJugador.WHITE);
	black.setOponente(white);
	white.setOponente(black);
	blackInCheck = false;
	whiteInCheck = false;
	capturedWhite = new PiesaAjedrez[16];
	capturedBlack = new PiesaAjedrez[16];
	capturedWhiteCount = 0;
	capturedBlackCount = 0;
	turn = white;

	for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
		board[i][j] = new Tablero(i, j);
            }
	}

	colocarPiesas(black);
	colocarPiesas(white);

}

private void colocarPiesas(Jugador jugador) {

    int row = jugador.getColor() == ColorJugador.WHITE ? 7: 0;

    PiesaAjedrez[] piesas = new PiesaAjedrez[]{ 
        new Torre(), new Caballo(), new Alfil(), new Reina(), new Rey(), new Alfil(), new Caballo(), new Torre()};
        
        jugador.setRey((Rey)piesas[4]);

	for (int i = 0; i < 8; i++) {
            piesas[i].setLocation(board[row][i]);
            piesas[i].setBoard(board);
            board[row][i].setPiesa(piesas[i]);
            board[row][i].getPiesa().setPlayer(jugador);
            jugador.addPiece(piesas[i]);
	}

	row = jugador.getColor() == ColorJugador.WHITE ? 6 : 1;

	for (int i = 0; i < 8; i++) {
            PiesaAjedrez peon = new Peon();
            peon.setLocation(board[row][i]);
            peon.setBoard(board);
            board[row][i].setPiesa(peon);
            board[row][i].getPiesa().setPlayer(jugador);
            jugador.addPiece(peon);
	}
    }

	public Tablero[][] getBoard() { return board; }

	public Jugador getCurrentPlayer() { return turn; } 

	public boolean move(String s, String d, String p) {

            blackInCheck = false;
            whiteInCheck = false;
            int c = fileToIndex(s.charAt(0));
            int r = 8 - Character.getNumericValue(s.charAt(1));
    
            Tablero source = board[r][c];

            c = fileToIndex(d.charAt(0));
            r = 8 - Character.getNumericValue(d.charAt(1));
            Tablero dest = board[r][c];
            PiesaAjedrez sourcePiece = source.getPiesa();
            PiesaAjedrez destPiece = dest.getPiesa();

            if (sourcePiece == null) return false;
            if (sourcePiece.getPlayer().getColor() != getCurrentPlayer().getColor()) return false;
            if (!sourcePiece.canMoveTo(dest)) return false;
            if (destPiece != null) {
            destPiece.setLocation(null);
			if (turn == white) {

				capturedBlack[capturedBlackCount++] = destPiece;
			} 
			else {

				capturedWhite[capturedWhiteCount++] = destPiece;
			}
		}

		enPassant(sourcePiece, dest);


		source.setPiesa(null);
		sourcePiece.setLocation(dest);
		dest.setPiesa(sourcePiece);
		sourcePiece.incrementMoves();

		if (((turn == white && dest.getY() == 0) || (turn == black && dest.getY() == 7)) && sourcePiece instanceof Peon) {

			PiesaAjedrez promotedPiece = promotion(p);
			promotedPiece.setLocation(dest);
			promotedPiece.setNumberOfMoves(sourcePiece.numberOfMoves());
			promotedPiece.setPlayer(turn);
			promotedPiece.setBoard(board);
			sourcePiece = promotedPiece;
			dest.setPiesa(sourcePiece);
		}

		Tablero oppKing = turn.getOponente().getRey().getLocation();
		
		if (turn.getOponente().getRey().inCheck(oppKing)) {

			if (turn.getColor() == ColorJugador.WHITE) {
				blackInCheck = true;
				if (black.getRey().checkmate(black.getRey().getLocation())) {
					System.out.println("Blanco Gana.");
					System.exit(0);
				}
			} else {
				whiteInCheck = true;
				if (white.getRey().checkmate(white.getRey().getLocation())) {
					System.out.println("Negro Gana.");
					System.exit(0);
				}
			}
		}


		turn = (turn == white) ? black : white;

		return true;
	}

	private void enPassant(PiesaAjedrez sourcePiece, Tablero dest) {

		if (sourcePiece instanceof Peon) {

			int yPos = sourcePiece.getPlayer().getColor() == ColorJugador.WHITE ? 1 : -1;


			if (Math.abs(sourcePiece.getLocation().getX() - dest.getX()) == 1 && sourcePiece.getLocation().getY() == dest.getY() + yPos) {

				PiesaAjedrez capturedPawn = board[sourcePiece.getLocation().getY()][dest.getX()].getPiesa();

				if (turn == white) {
					capturedBlack[capturedBlackCount++] = capturedPawn;
				}
				else {
					capturedWhite[capturedWhiteCount++] = capturedPawn;
				}

				board[sourcePiece.getLocation().getY()][dest.getX()].setPiesa(null);
			}
		}
	}

	private int fileToIndex(char file) {

		switch (file) {

		case 'A':
			return 0;
		case 'B':
			return 1;
		case 'C':
			return 2;
		case 'D':
			return 3;
		case 'E':
			return 4;
		case 'F':
			return 5;
		case 'G':
			return 6;
		case 'H':
			return 7;
		default:
			return -1;
		}

	}

    public PiesaAjedrez promotion(String p) {
	if (p == null) p = "Q";

	switch(p.charAt(0)) {
                case 'Q':
        	return new Reina();
	case 'B':
            return new Alfil();
        case 'N':
            return new Caballo();
		
        case 'R':
            return new Torre();
        default:
		return new Reina();
	}
    }
    public int blackCaptureCount() { return capturedBlackCount; }

    public int whiteCaptureCount() { return capturedWhiteCount; }

    public boolean blackInCheck() { return blackInCheck; }

    public boolean whiteInCheck() { return whiteInCheck; }
}