package debug;

import java.util.ArrayList;
import java.util.Random;

import main.BitBoard;
import main.OperateBitBoard;
import ai.BitBoardNode;
import ai.EvaluateBoard;
import ai.MakeBitBoardTree;
import ai.PermutationTable;

public class CalculateAverageAndVariance {

	/**
	 * @param args
	 * 最初の近似値f　と数手先の評価値との誤差の平均と分散を計算する
	 */
	
	private final static int NUMBER_OF_TEST = 1000;
	private final static int NUMBER_OF_TESUU = 15;
	private final static int TEST_DEPTH = 6;
	private final static boolean IS_AI_BLACK = true;
	private final static int STEP_SIZE = 50;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int datas[] = new int[NUMBER_OF_TEST];
		Random r = new Random();
		ArrayList<BitBoard> bArray = new ArrayList<BitBoard>();
		for(int i = 0;i < NUMBER_OF_TEST;i++){
			bArray.add(getRandom(NUMBER_OF_TESUU,  new BitBoard(34628173824L,68853694464L),  true, r));
		}
		
		
		for(int i = 0; i < NUMBER_OF_TEST ;i++){
			BitBoardNode bbnTest = new BitBoardNode(bArray.get(i), IS_AI_BLACK);
			int f = EvaluateBoard.getEvaluation(bbnTest, IS_AI_BLACK);
			PermutationTable pt = new PermutationTable();
			datas[i] = f - MakeBitBoardTree.mtdfAlphaBetaSearch(bbnTest, f, TEST_DEPTH, IS_AI_BLACK, pt , STEP_SIZE);
		
			if(i % 10 == 9){
				System.out.println(i+1);
			}
		}
		
		calculateAverageAndDeviation(datas);

	}
	
	
	private static BitBoard getRandom(int tesuu,BitBoard bb, boolean isBlack,Random r){
		if(tesuu == 0||OperateBitBoard.isGameEnd(bb)){
			return bb;
		}

		ArrayList<Long> next = OperateBitBoard.getPuttablePlaceList(bb, isBlack);
		if(next.size()==0){
			return getRandom(tesuu,bb,OperateBitBoard.reverse(isBlack),r);
		}else{
			BitBoard newb = OperateBitBoard.pubBitAndReverse(bb, next.get(r.nextInt(next.size())), isBlack);
			return getRandom(tesuu-1, newb, OperateBitBoard.reverse(isBlack), r);

		}
	}
	
	public static void calculateAverageAndDeviation(int[] data){
		double average = 0;
		double deviation = 0;
		for(int i = 0 ; i < data.length;i++){
			average += data[i];
		}
		average /= data.length;
		System.out.println("Error average is "+average);
		
		for(int i = 0;i < data.length;i++){
			deviation +=Math.pow(data[i] - average,2);
		}
		deviation = Math.sqrt(deviation/data.length);
		System.out.println("Error standard deviation is "+deviation);
		
		
		
	}

}
