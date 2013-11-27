package scaredyCat.model;

public class DeckModel{
	DynamicIntArrayModel array;
	public DeckModel(int size){
		array = new DynamicIntArrayModel(size);
	}
	
	public void setCard(int card) throws Exception {
		array.addItem(card,array.getSize()); //we set the card on top of the pile
	}
	
	public int getLastCard() throws Exception{
		return array.retrieveItem(array.getSize() - 1); //we get the card on top of the pile
	}
	
	public void shuffleCards() throws Exception {
		array.shuffle();
	}

}
