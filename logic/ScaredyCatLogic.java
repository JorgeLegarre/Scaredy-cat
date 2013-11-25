package scaredyCat.logic;

import scaredyCat.constants.Config;
import scaredyCat.model.DynamicIntArrayModel;
import scaredyCat.model.PileCardModel;
import scaredyCat.ui.ScaredyCatInterface;

public class ScaredyCatLogic {
	private PileCardModel pile = null;
	private int[] scorePlayers;
	private int scarecrows;
	
	public ScaredyCatLogic() throws Exception{
		init();
	}
	
	private void setAllCardsOfType(int card, int nCards) throws Exception{
		for(int i=0;i<nCards;i++){
			pile.setCard(card);
		}
	}
	
	private void setAllCards() throws Exception{
		setAllCardsOfType(Config.CARD_BIRD,Config.NUMBER_BIRDS);
		setAllCardsOfType(Config.CARD_CAT,Config.NUMBER_CATS);
		setAllCardsOfType(Config.CARD_SCARECROW,Config.NUMBER_SCARECROWS);
	}
	
	private void initPlayersScore(){
		for(int i = 0; i< scorePlayers.length;i++){
			scorePlayers[i] = 0;
		}
	}
	
	public void init() throws Exception{
		//init players and scores
		scorePlayers = new int[Config.N_PLAYERS];
		initPlayersScore();
		
		//init scarecrows viewed
		scarecrows = 0;
		
		//init cards
		int n_cards = Config.NUMBER_BIRDS + Config.NUMBER_CATS + Config.NUMBER_SCARECROWS;
		pile = new PileCardModel(n_cards);
		
		setAllCards();
		
		pile.shuffleCards();	
		
	}
	
	private void makeBirdTurn(int player){
		scorePlayers[player]++;
	}
	
	private void makeCatTurn(int player, int card) throws Exception{
		scorePlayers[player]=0;
		pile.setCard(card);
		pile.shuffleCards();
	}
	
	private void makeScarecrowTurn(){
		scarecrows++;
	}
	
	private int turnPlayer(int player) throws Exception{
		int card = pile.getLastCard();
		switch(card){
		case Config.CARD_BIRD:
			makeBirdTurn(player);
			break;
		case Config.CARD_CAT:
			makeCatTurn(player, card);
			break;
		case Config.CARD_SCARECROW:
			makeScarecrowTurn();
			break;
		}
		
		return card;
	}
	
	private boolean endOfGame(){
		return scarecrows == Config.NUMBER_SCARECROWS;
	}
	
	private int changePlayer(int player){
		player++;
		if(player == Config.N_PLAYERS)
			player = 0;
		return player;
	}
	
	private static DynamicIntArrayModel calculateWinnersArray(int[] scorePlayers) throws Exception {
		DynamicIntArrayModel winnersArray = new DynamicIntArrayModel(scorePlayers.length);
		
		int max_points = -1;
		
		for(int i = 0; i < scorePlayers.length;i++){
			if(scorePlayers[i] > max_points){
				winnersArray.empty();
			}
			
			if(scorePlayers[i] >= max_points){
				max_points = scorePlayers[i];
				winnersArray.setItem(i+1, winnersArray.getSize());//i+1, we save player 1,2,... , variable i go from 0,1...
				
			}
		}
		
		return winnersArray;
	}
	
	public void play() throws Exception{
		 
		int player = 0;//turn first player, players go between 0 and n_player - 1
		do{
			int card = turnPlayer(player); 
			
			ScaredyCatInterface.showTurn(player + 1, card,scorePlayers);
			
			if(!endOfGame()){
				player=changePlayer(player);
			}
		}while(!endOfGame());
		
		
		DynamicIntArrayModel winners = calculateWinnersArray(scorePlayers);
		ScaredyCatInterface.showWinner(scorePlayers, winners);
		
	}

}
