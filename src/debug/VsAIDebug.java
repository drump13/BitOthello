package debug;

import ai.JosekiSearchAI;
import ai.MainAI;
import ai.MonteCarloAI;
import graphic.DrawBoard;
import graphic.MouseClick;
import main.BitBoard;
import main.OperateBitBoard;

public class VsAIDebug {

	
	public static boolean IS_PLAYER_BLACK = false;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		BitBoard b = new BitBoard(34628173824L,68853694464L);
		DrawBoard d = new DrawBoard(b);
		Thread draw = new Thread(d);
		draw.start();

		boolean isJoseki = true;
		
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

			BitBoard newb = null;
			if(isBlack == IS_PLAYER_BLACK){

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


				newb = OperateBitBoard.putBit(b, isBlack, rev, newStone);
			}else{
			//	newb = MainAI.nextPut(b);
				if(isJoseki){
					newb = JosekiSearchAI.getNextBoard(b);
					if(newb.black == 0L && newb.white == 0L){
						newb = MonteCarloAI.getNextBoard(b);
						isJoseki = false;
					}
				}else{
					newb = MonteCarloAI.getNextBoard(b);
				}
			}
			b.black = newb.black;
			b.white = newb.white;
			isBlack = OperateBitBoard.reverse(isBlack);

		}
	}

}
