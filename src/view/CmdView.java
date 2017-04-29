package view;

import java.util.Scanner;

import model.ColorJugador;
import controller.Game;

public class CmdView {

	private Game game;
	public boolean drawEntered;

	public CmdView(Game game) { this.game = game; }

	public void startGame() {

		Display.showBoard(game);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		while (true) 
		{
			String player = game.getCurrentPlayer().getColor() == ColorJugador.WHITE ? "Blanco" : "Negro";
			System.out.print("Turno del jugador "+player+": ");
			String input = sc.nextLine().trim();
			parseInput(input);
			Display.showBoard(game);
			if (game.blackInCheck() || game.whiteInCheck()) System.out.println("Check.");

		}
	}


	public void parseInput(String args){

		String[] tokens = args.split("\\s+");


		if (tokens.length == 0) return;

		if(args.contains("draw?") && args.length()==11)
		{
			this.drawEntered = true;

		}
		if (args.compareTo("draw") == 0 && this.drawEntered) { 
			System.exit(0);
		}
		if (tokens.length > 3 || (tokens.length == 1 && tokens[0].compareTo("resign") != 0)) {
			System.out.println("Invalid input");
			return;
		}
		if(tokens[0].compareTo("resign") == 0)
		{
			String winner = (game.getCurrentPlayer().getColor() == ColorJugador.WHITE) ? "Negro" : "Blanco"; 
			String loser = (winner.compareTo("Blanco") == 0) ? "Negro" : "Blanco";

			System.out.println(loser + " resigned. " + winner + " Gano!");
			System.exit(0);
		}

		String source = tokens[0];
		String dest = tokens[1];
		String promotion = null;

		if (tokens.length == 3) {

			promotion = tokens[2].trim();
			if (promotion.compareTo("Q") != 0
					&& promotion.compareTo("B") != 0
					&& promotion.compareTo("N") != 0
					&& promotion.compareTo("R") != 0)
			{
				System.out.println("Invalid input");
				return;
			}
		}

		if (source.length() != 2 || dest.length() != 2) {

			System.out.println("Invalid input.");
			return;
		}

		int asciiRank1 = (int) tokens[0].toLowerCase().charAt(0);
		int file1 = Character.getNumericValue(tokens[0].charAt(1));
		int asciiRank2 = (int) tokens[1].toLowerCase().charAt(0);
		int file2 = Character.getNumericValue(tokens[1].charAt(1));


		if ((asciiRank1 < (int) 'a' || asciiRank1 > (int) 'h') 
				|| (asciiRank2 < (int)'a' || asciiRank2 > (int)'h')
				|| (file1 < 1 || file1 > 8)
				|| (file2 < 1 || file2 > 8))
		{
			System.out.println("Invalid Input");
			return;
		}


		if (!game.move(source, dest, promotion)) {

			System.out.println("Moviento invalido, intente otro.");
			return;
		}


	}

}


