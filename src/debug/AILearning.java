package debug;

import java.util.ArrayList;
import java.util.Random;

import ai.MonteCarloLearning;
import ai.PatternLearning;
import ai.PatternRegistory;
import main.BitBoard;
import main.OperateBitBoard;

public class AILearning {

	/**
	 * @param args
	 */

	public static float LEARNING_RATE = 0.003f;

	public static void main(String[] args) {

		PatternRegistory pr = new PatternRegistory();
		pr.setPattern();
		Random r = new Random();
		int count = 0;
		while(count < 100){
			ArrayList<BitBoard> bbList = new ArrayList<BitBoard>();
			BitBoard b = new BitBoard(34628173824L,68853694464L);
			boolean isBlack = true;

			while(!OperateBitBoard.isGameEnd(b)){

				if(OperateBitBoard.isTherePuttablePlace(b, isBlack)){
					//最初の8手とそれ以外1％はランダムな手を打つ
					if(OperateBitBoard.getNumberOfSumStones(b) < 12||onePercentRandom(r)){
						ArrayList<Long> nextList = OperateBitBoard.getPuttablePlaceList(b, isBlack);
						b = OperateBitBoard.pubBitAndReverse(b, nextList.get(r.nextInt(nextList.size())), isBlack);
					}else{
						b = MonteCarloLearning.getNextBoard(b, isBlack, pr); 
						MainDebug.displayBoard(b.black, b.white);
						bbList.add(b);
					}
				}
				isBlack = OperateBitBoard.reverse(isBlack);

			}

			int reward = Long.bitCount(b.black) - Long.bitCount(b.white);

			while(OperateBitBoard.getNumberOfSumStones(bbList.get(bbList.size()-1)) > 52){
				bbList.remove(bbList.size()-1);
			}
			while(bbList.size() > 8){
				PatternLearning.registerParametersFromBoard(bbList.get(bbList.size()-1), pr, reward, LEARNING_RATE);
				bbList.remove(bbList.size()-1);
			}
			count++;
			System.out.println(count);
		}
		pr.printAllArrays();

	}

	public static boolean onePercentRandom(Random r){
		int i = r.nextInt(100);
		if(i == 1){
			return true;
		}
		return false;
	}

}
