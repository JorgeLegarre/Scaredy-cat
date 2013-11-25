package scaredyCat.model;

public class DynamicIntArrayModel {
	protected int array[];
	protected int lastPosition = -1;
	
	public DynamicIntArrayModel(int size){
		array = new int[size];
	}
	
	public int getSize(){
		return lastPosition + 1;
	}

	public void setItem(int item, int pos) throws Exception {
		if(pos >= 0 && pos <= lastPosition + 1){
			lastPosition++;
			for (int i = lastPosition; i > pos; i--) {
				array[i] = array[i-1];
			}
			array[pos] = item;
		}else{
			//TODO we can create a new array with size increased and then add the card, but we dont need this funcionallity now.
			throw new Exception("Illegal position.");
		}
	}
	
	//we query the item and then remove from the pile
	public int getItem(int position) throws Exception{
		int item;
		
		if(position >= 0 && position <= lastPosition){
			item = array[position];
			
			for(int i=position;i<lastPosition;i++){
				array[i]=array[i+1];
			}
			
			lastPosition--;
		}else{
			throw new Exception("Illegal position");
		}
		
		return item;
	}
	
	public void empty(){
		lastPosition = -1;
	}
	
}
