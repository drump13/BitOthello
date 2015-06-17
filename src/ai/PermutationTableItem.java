package ai;

public class PermutationTableItem {
	private long black;
	private long white;
	private int upperBound;
	private int lowerBound;
	
	
	PermutationTableItem(long black,long white,int upper,int lower){
		this.black = black;
		this.white = white;
		this.upperBound = upper;
		this.lowerBound = lower;
	}
	
	//盤面が一致しているかどうかを返す
	public boolean isConsistent(long black,long white){
		if(black == this.black && white == this.white){
			return true;
		}
		return false;
	}
	
	public void StoreLowerBound(int lowerB){
		lowerBound = lowerB;
	}
	
	public void StoreUpperBound(int upperB){
		upperBound = upperB;
	}
	
	public int getLowerBound(){
		return lowerBound;
	}
	
	public int getUpperBound(){
		return upperBound;
	}

}
