package scaredyCat.logic;

import scaredyCat.constants.Config;
import scaredyCat.model.DynamicIntArrayModel;
import scaredyCat.model.DeckModel;

public class ScaredyCatManager {
	private DeckModel pile = null;
	private int[] scorePlayers;
	private int scarecrows;
	
	public ScaredyCatManager() throws Exception{
		initPlayers();
		
		initScarecrows();
		
		initCards();
	}
	
	private void initPlayers(){
		scorePlayers = new int[Config.N_PLAYERS];
		initPlayersScore();
	}
	
	private void initPlayersScore(){
		for(int i = 0; i< scorePlayers.length;i++){
			scorePlayers[i] = 0;
		}
	}
	
	private void initScarecrows(){
		scarecrows = 0;
	}
	
	private void initCards() throws Exception{
		int n_cards = Config.NUMBER_BIRDS + Config.NUMBER_CATS + Config.NUMBER_SCARECROWS;
		pile = new DeckModel(n_cards);
		setAllCards();
		pile.shuffleCards();
	}
	
	private void setAllCards() throws Exception{
		setAllCardsOfType(Config.CARD_BIRD,Config.NUMBER_BIRDS);
		setAllCardsOfType(Config.CARD_CAT,Config.NUMBER_CATS);
		setAllCardsOfType(Config.CARD_SCARECROW,Config.NUMBER_SCARECROWS);
	}
	
	private void setAllCardsOfType(int card, int nCards) throws Exception{
		for(int i=0;i<nCards;i++){
			pile.setCard(card);
		}
	}
	
	private void makeBirdTurn(int player){
		scorePlayers[player]++;
	}
	
	private void returnCards(int player, int card) throws Exception{
		for(int i = 0; i < scorePlayers[player];i++){
			pile.setCard(Config.CARD_BIRD);
		}
		pile.setCard(card);
	}
	
	private void makeCatTurn(int player, int card) throws Exception{
		returnCards(player, card);
		pile.shuffleCards();
		scorePlayers[player]=0;
	}
	
	private void makeScarecrowTurn(){
		scarecrows++;
	}
	
	private int getPlayerIndex(int player){
		return --player;
	}
	
	public int makePlayerTurn(int player) throws Exception{
		int playerIndex = getPlayerIndex(player);
		
		int card = pile.getLastCard();
		
		switch(card){
		case Config.CARD_BIRD:
			makeBirdTurn(playerIndex);
			break;
		case Config.CARD_CAT:
			makeCatTurn(playerIndex, card);
			break;
		case Config.CARD_SCARECROW:
			makeScarecrowTurn();
			break;
		}
		
		return card;
	}
	
	public boolean endOfGame(){
		return scarecrows == Config.NUMBER_SCARECROWS;
	}
	
	public DynamicIntArrayModel getWinners() throws Exception {
		DynamicIntArrayModel winnersArray = new DynamicIntArrayModel(scorePlayers.length);
		
		int max_points = -1;
		
		for(int i = 0; i < scorePlayers.length;i++){
			if(scorePlayers[i] > max_points){
				winnersArray.empty();
			}
			
			if(scorePlayers[i] >= max_points){
				max_points = scorePlayers[i];
				winnersArray.addItem(i+1, winnersArray.getSize());//i+1, we save player 1,2,... , variable i go from 0,1...
				
			}
		}
		
		return winnersArray;
	}
	
	public int[] getScore() {
		return scorePlayers;
	}
	
	public int getFindedScarecrows() {
		return scarecrows;
	}

}
