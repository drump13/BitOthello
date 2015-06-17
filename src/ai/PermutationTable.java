package ai;

import java.util.ArrayList;

import main.BitBoard;

public class PermutationTable {
	
	private ArrayList<PermutationTableItem> table;
	public PermutationTable(){
		table = new ArrayList<PermutationTableItem>();
	}
	
	public void addItem(BitBoard b,int lower,int upper){
		table.add(new PermutationTableItem(b.black, b.white, upper, lower));

		/*		PermutationTableItem ptitem = null;
		if((ptitem = indexOf(b.black, b.white)) == null){
			table.add(new PermutationTableItem(b.black, b.white, upper, lower));
		}else{
			ptitem.StoreLowerBound(lower);
			ptitem.StoreUpperBound(upper);
		}*/
	}
	
	public PermutationTableItem indexOf(long black,long white){
		for(int i = 0; i < table.size();i++){
			if(table.get(i).isConsistent(black, white)){
				return table.get(i);
			}
		}
		return null;
	}
	
	
	
}
