package scaredyCat.model;

public class PileCardModel{
	DynamicIntArrayModel array;
	public PileCardModel(int size){
		array = new DynamicIntArrayModel(size);
	}
	
	public void setCard(int card) throws Exception {
		array.setItem(card,array.getSize()); //we set the card on top of the pile
	}
	
	public int getLastCard() throws Exception{
		return array.getItem(array.getSize() - 1); //we get the card on top of the pile
	}
	
	public void shuffleCards() throws Exception {
		array.shuffle();
	}

}
