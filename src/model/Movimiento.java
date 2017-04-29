package model;

import java.io.Serializable;

public class Movimiento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	
	public PiesaAjedrez chessPiece;
	public PiesaAjedrez capture;
	public Tablero source;
	public Tablero destination;
	
	public Movimiento(PiesaAjedrez chessPiece, PiesaAjedrez capture, Tablero source, Tablero destination) {
		this.chessPiece = chessPiece;
		this.capture = capture;
		this.source = source;
		this.destination = destination;
	}

	

}

