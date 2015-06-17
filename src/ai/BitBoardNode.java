package ai;
import java.util.ArrayList;
import java.util.Comparator;

import main.*;

public class BitBoardNode{
	public BitBoard b;
	public BitBoardNode parent;
	public ArrayList<BitBoardNode> children;
	public int score=0;
	public int sortScore = 0;
	public boolean nextIsBlack;//次は黒番か白番か
	
	
	public BitBoardNode(BitBoard b,boolean next){
		this.b = b;
		this.parent = null;
		children = new ArrayList<BitBoardNode>();
		nextIsBlack = next;

	}
	
	
	BitBoardNode(BitBoard b,BitBoardNode parent,boolean next) {
		this.b = b;
		this.parent = parent;
		children = new ArrayList<BitBoardNode>();
		nextIsBlack = next;
	}
	
	
	
}
