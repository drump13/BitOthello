package debug;

import java.util.ArrayList;
import java.util.Random;

import main.BitBoard;
import main.OperateBitBoard;
import ai.BitBoardNode;
import ai.MakeBitBoardTree;
import ai.PermutationTable;

public class SearchingTest {

	private static boolean IS_AI_BLACK = true;
	private final static int TEST_DEPTH = 7;
	private final static int NUMBER_OF_TEST = 10;
	private final static int NUMBER_OF_TESUU = 15;
	private final static int F = 0;

	public static int counter = 0;







	public static void main(String[] arg){

		Random r = new Random();
		ArrayList<BitBoard> bArray = new ArrayList<BitBoard>();
		for(int i = 0;i < NUMBER_OF_TEST;i++){
			bArray.add(getRandom(NUMBER_OF_TESUU,  new BitBoard(34628173824L,68853694464L),  true, r));

		}

		int beforeCounter = 0;
		int negamaxAverage = 0;
		int negaalphaAverage = 0;
		int negascoutAverage = 0;
		int negamaxCounterAverage = 0;
		int negaalphaCounterAverage = 0;
		int negascoutCounterAverage = 0;



		for(int c = 0;c < NUMBER_OF_TEST;c++){
			BitBoardNode bbn1 = new BitBoardNode(bArray.get(c), IS_AI_BLACK);
			BitBoardNode bbn2 = new BitBoardNode(new BitBoard(2014838784L,48636276768768L), IS_AI_BLACK);
			BitBoardNode bbn3 = new BitBoardNode(bArray.get(c), IS_AI_BLACK);
			BitBoardNode bbn4 = new BitBoardNode(bArray.get(c), IS_AI_BLACK);
			BitBoardNode bbn5 = new BitBoardNode(bArray.get(c), IS_AI_BLACK);

			//		BitBoardNode bbn3 = new BitBoardNode(new BitBoard(1142375401422848L,-6890472243357548544L), IS_AI_BLACK);
			//		BitBoardNode bbn4 = new BitBoardNode(new BitBoard(1142375401422848L,-6890472243357548544L), IS_AI_BLACK);



			long start = System.currentTimeMillis();
			//MakeBitBoardTree.recursive(bbn, IS_AI_BLACK,TEST_DEPTH);
			MakeBitBoardTree.makeChildren(bbn1,IS_AI_BLACK);
			for(int i = 0;i < bbn1.children.size();i++){
				//				MakeBitBoardTree.negaMax(bbn1.children.get(i),TEST_DEPTH,IS_AI_BLACK);
				//		System.out.println("negamax Score"+i+" is "+bbn1.children.get(i).score);
			}
			System.out.println("negamax "+(counter - beforeCounter));
			negamaxCounterAverage += counter-beforeCounter;
			beforeCounter = counter;
			long stop = System.currentTimeMillis();
			negamaxAverage += stop-start;
			System.out.println("time is "+(stop - start));


/*
 * ここからαβのテスト
 * /
 */
			start = System.currentTimeMillis();
			MakeBitBoardTree.makeChildren(bbn2,IS_AI_BLACK);
			for(int i = 0;i < bbn2.children.size();i++){
		//		MakeBitBoardTree.alphabeta(bbn2.children.get(i),Integer.MIN_VALUE,Integer.MAX_VALUE,TEST_DEPTH,IS_AI_BLACK);
			//	System.out.println("alphabeta Score"+i+" is "+bbn2.children.get(i).score);
			//	bbn2.children.get(i).children=null;
			}
			System.out.println("negamax "+(counter - beforeCounter));
			beforeCounter = counter;
			stop = System.currentTimeMillis();
			System.out.println("time is "+(stop - start));

			/*/
			 * 
			 * ここからネガアルファ法のテスト
			 * 
			 */

			start = System.currentTimeMillis();
			MakeBitBoardTree.makeChildren(bbn3,IS_AI_BLACK);
			for(int i = 0;i < bbn3.children.size();i++){
				//		MakeBitBoardTree.negaAlphaSearch(bbn3.children.get(i),Integer.MIN_VALUE,Integer.MAX_VALUE,TEST_DEPTH,IS_AI_BLACK);
				//		System.out.println("negaalpha Score"+i+" is "+bbn3.children.get(i).score);
				bbn3.children.get(i).children=null;
			}
			System.out.println("negaalpha "+(counter - beforeCounter));
			negaalphaCounterAverage += counter-beforeCounter;
			beforeCounter = counter;
			stop = System.currentTimeMillis();
			negaalphaAverage += stop-start;
			System.out.println("time is "+(stop - start));


			/*
			 * 
			 * ここからネガスカウト法のテスト
			 * /
			 */

			start = System.currentTimeMillis();
			MakeBitBoardTree.makeChildren(bbn4,IS_AI_BLACK);
			for(int i = 0;i < bbn4.children.size();i++){
				MakeBitBoardTree.negaScoutSearch(bbn4.children.get(i),Integer.MIN_VALUE,Integer.MAX_VALUE,TEST_DEPTH,IS_AI_BLACK);
				System.out.println("negascout Score"+i+" is "+bbn4.children.get(i).score);
				bbn4.children.get(i).children=null;
			}
			System.out.println("negascout "+(counter - beforeCounter));
			negascoutCounterAverage += counter-beforeCounter;
			beforeCounter = counter;
			stop = System.currentTimeMillis();
			negascoutAverage += stop-start;
			System.out.println("time is "+(stop - start));



			/*
			 *ここからmtd-f法のテスト 			
			 */
			PermutationTable pt = new PermutationTable();
			start = System.currentTimeMillis();
			MakeBitBoardTree.makeChildren(bbn5,IS_AI_BLACK);
			for(int i = 0;i < bbn5.children.size();i++){
				MakeBitBoardTree.mtdfSearch(bbn5.children.get(i),F,TEST_DEPTH,IS_AI_BLACK,pt);
				System.out.println("btd-f Score"+i+" is "+bbn5.children.get(i).score);
				bbn5.children.get(i).children=null;
				pt = new PermutationTable();
			}
			System.out.println("btd-f "+(counter - beforeCounter));
			negascoutCounterAverage += counter-beforeCounter;
			beforeCounter = counter;
			stop = System.currentTimeMillis();
			//	negascoutAverage += stop-start;
			System.out.println("time is "+(stop - start));



		}
		System.out.println(negamaxAverage);
		System.out.println(negaalphaAverage);
		System.out.println(negascoutAverage);
		System.out.println();
		System.out.println(negamaxCounterAverage);
		System.out.println(negaalphaCounterAverage);
		System.out.println(negascoutCounterAverage);



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

}
