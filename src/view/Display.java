package view;

import model.PiesaAjedrez;
import model.ColorJugador;
import model.Tablero;
import controller.Game;

public class Display {

	public static void showBoard(Game game) {

		Tablero[][] board = game.getBoard();
		int row, column;
                System.out.println(" A   B   C   D   E   F   G    H  ");
		for (row = 0; row < 8; row++) {
			for (column = 0; column < 8; column++) {
				
				PiesaAjedrez piece = board[row][column].getPiesa();

				if (piece == null) {
					
					
					if (row % 2 == 0) {
						
						
						if (column % 2 == 0) {
							
							System.out.print("[  ]");
							continue;
						}
						
						else {
							System.out.print("[  ]");
							continue;
						}
					}
					
					else {
						
						
						if (column % 2 == 0) {
							
							System.out.print("[  ]");
							continue;
						}
						
						else {
						
							System.out.print("[  ]");
							continue;
						}
					}
				}
				
				String initials = piece.getPlayer().getColor() == ColorJugador.BLACK ? "N" : "B";
				initials += piece.getInitial();
				
				System.out.print("["+initials + "]");
				
			}
			System.out.println(column - row);
		}
		
		System.out.println(" A   B   C   D   E   F   G    H  ");
		System.out.println();
				
	}

}
