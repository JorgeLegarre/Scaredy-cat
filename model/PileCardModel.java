package scaredyCat.model;

public class PileCardModel extends DynamicIntArrayModel{
	public PileCardModel(int size){
		super(size);
	}
	
	public void setCard(int card) throws Exception {
		super.setItem(card,super.getSize()); //we set the card on top of the pile
	}
	
	public int getLastCard() throws Exception{
		return super.getItem(super.getSize() - 1); //we get the card on top of the pile
	}
	
	public void shuffleCards() throws Exception {
		int[] newarray = new int[array.length];
		int newLastPosition = lastPosition;
		
		for(int i=0;i<=newLastPosition;i++){
			int randomPos = (int)(Math.random() * (lastPosition + 1)); //(new Random(System.nanoTime())).nextInt(lastPosition+1);//(new Random()).nextInt(lastPosition+1); // 0 and lastPosition inclusive
			newarray[i] = super.getItem(randomPos);
		}
		
		array = newarray;
		lastPosition = newLastPosition;
	}

}
