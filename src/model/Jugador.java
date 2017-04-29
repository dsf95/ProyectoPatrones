package model;

import java.util.ArrayList;

public class Jugador {

    private ArrayList<PiesaAjedrez> pieces;
    private Jugador opponent;
    private Rey king;
    private ColorJugador color;

    public Jugador(ColorJugador color) {
        this.color = color;	
	pieces = new ArrayList<PiesaAjedrez>(16);
    }

    public ColorJugador getColor() { return color; }

    public Jugador getOponente() { return opponent; }
    
    public void addPiece(PiesaAjedrez piece) { pieces.add(piece); } 

    public ArrayList<PiesaAjedrez> getPieces() { return pieces; }

    public void setOponente(Jugador opponent) { this.opponent = opponent; }

    public void setRey(Rey king) { this.king = king; }
    
    public Rey getRey() { return king; }

}
