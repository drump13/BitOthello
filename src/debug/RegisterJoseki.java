package debug;

import joseki.RegisterToFile;
import graphic.DrawBoard;
import graphic.MouseClick;
import main.BitBoard;
import main.OperateBitBoard;

public class RegisterJoseki {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		BitBoard b = new BitBoard(34628173824L,68853694464L);
		
		
		DrawBoard d = new DrawBoard(b);
		Thread draw = new Thread(d);
		draw.start();
	
		
		
		boolean isBlack = true;
		
		//片方打てる場所が無かったらオンになり両方打てるところがなくなったらゲーム終了
		boolean isHalfEnd = false;
		while(!OperateBitBoard.isGameEnd(b)){
			
			if(!OperateBitBoard.isTherePuttablePlace(b, isBlack)){
				if(isHalfEnd){break;}
				isBlack =OperateBitBoard.reverse(isBlack);
				isHalfEnd = true;
				continue;
			}else{
				isHalfEnd = false;
			}
			
			int x = -1;
			int y = -1;
			while(x == -1 || y == -1){
				x = MouseClick.x;
				y = MouseClick.y;
				Thread.sleep(30);
			}
			
			System.out.println(8*x/600+" "+8*(y-20)/600);
			long newStone = OperateBitBoard.makeNewStone(8*x/600, 8*(y-20)/600);
			long[] revPattern = OperateBitBoard.getRevPattern(b, newStone, isBlack);
			long rev = OperateBitBoard.makeRevFromRevPattern(revPattern);
			System.out.println(rev);
			if(!OperateBitBoard.isThisPuttable(rev)){
				System.out.println("返せる石がありません");
				MouseClick.x=-1;
				MouseClick.y=-1;
				continue;
			}
			
			
			BitBoard newb = OperateBitBoard.putBit(b, isBlack, rev, newStone);
			RegisterToFile.registerToFile(b, newb);
			
			b.black = newb.black;
			b.white = newb.white;
			isBlack = OperateBitBoard.reverse(isBlack);
		}

	}
	
	

}
