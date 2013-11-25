package scaredyCat.ui;

import java.io.IOException;

import scaredyCat.constants.Config;
import scaredyCat.model.DynamicIntArrayModel;

public class ScaredyCatInterface {
	private static void printScores(int[] scorePlayers){
		for (int i = 0; i < scorePlayers.length; i++) {
			System.out.println("Player " + (i + 1) + ": " + scorePlayers[i] + " pts. "); 
		}
	}
	
	private static String getTextCard(int card){
		switch(card){
		case Config.CARD_BIRD:
			return "bird";
		case Config.CARD_CAT:
			return "cat";
		case Config.CARD_SCARECROW:
			return "scarecrow";
		}
		return "";
	}
	
	private static void requireEnter() throws IOException {
		char c;
		System.out.println("Press enter to obtain the nex card.");
		do{
			c = (char)System.in.read();
		}while (c != '\n');
		
	}
	
	private static void printWinners( DynamicIntArrayModel winnersModel) throws Exception {
		String winners = "Player";
		if(winnersModel.getSize() > 1)
			winners += "s";
		
		winners += " ";
		
		int nWinners = winnersModel.getSize();
		for (int i = 0; i < nWinners; i++) {
			winners += winnersModel.getItem(0);
			if(winnersModel.getSize() > 0)
				winners += " and ";
		}
		
		winners += " win!!!!!!!";
		
		System.out.println(winners);
		
	}
	
	public static void showTurn(int player, int card, int[] scorePlayers) throws IOException {
		System.out.println();
		System.out.println("Player " + player + " obtained a " + getTextCard(card) + ".");
		System.out.println("Scores: ");
		printScores(scorePlayers);
		
		if(!Config.AUTOPLAY){
			requireEnter();
		}
	}

	public static void showWinner(int[] scorePlayers, DynamicIntArrayModel winners) throws Exception {
			System.out.println();
			if(winners.getSize() == scorePlayers.length){
				System.out.println("It's a draw!!!!.");
			}else{
				printWinners(winners);
			}

			printScores(scorePlayers);
		
	}

}
