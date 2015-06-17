package debug;

import graphic.DrawBoard;
import main.BitBoard;
import main.OperateBitBoard;
import ai.MainAI;
import ai.MonteCarloAI;

public class AIVsAIDebug{

	public static boolean IS_MON_BLACK = true;
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

			BitBoard newb = null;
			if(isBlack == IS_MON_BLACK){

				newb = MonteCarloAI.getNextBoard(b);
			}else{
				newb = MainAI.nextPut(b);
			//	newb = MonteCarloAI.getNextBoard(b);
			}
			b.black = newb.black;
			b.white = newb.white;
			isBlack = OperateBitBoard.reverse(isBlack);

		}
	}


}
