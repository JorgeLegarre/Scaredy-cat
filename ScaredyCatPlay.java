package scaredyCat;

import scaredyCat.constants.Config;
import scaredyCat.logic.ScaredyCatLogic;
import scaredyCat.ui.ScaredyCatInterface;

public class ScaredyCatPlay {
	private static int changePlayer(int player){
		player++;
		if(player == Config.N_PLAYERS + 1)
			player = 1;
		return player;
	}
	
	public static void main(String[] args) throws Exception {
		ScaredyCatLogic scaredyCat = new ScaredyCatLogic();
		
		int player = 1;
		do{
			int card = scaredyCat.makePlayerTurn(player); 
			
			ScaredyCatInterface.showTurn(player, card, scaredyCat.getScorePlayers());
			
			player=changePlayer(player);
			
		}while(!scaredyCat.endOfGame());
		
		
		ScaredyCatInterface.showWinner(scaredyCat.getScorePlayers(), scaredyCat.calculateWinnersArray());
	}
}
