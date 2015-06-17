package ai;

import java.util.Comparator;

public class CompareBitBodeNode implements Comparator<BitBoardNode>{

	@Override
	public int compare(BitBoardNode arg0, BitBoardNode arg1) {
		// TODO Auto-generated method stub
		return arg1.sortScore - arg0.sortScore;
	}
	
}
