package debug;

import java.util.ArrayList;
import java.util.Random;
import main.BitBoard;
import main.OperateBitBoard;
import ai.BitBoardNode;
import ai.EvaluateBoard;
import ai.MakeBitBoardTree;
import ai.PermutationTable;


public class SearchingTestForSeminer2 {
	private static boolean IS_AI_BLACK = true;
	private final static int TEST_DEPTH = 6;
	private static final int NUMBER_OF_TEST = 10;
	private static final int NUMBER_OF_TESUU = 15;
	
	private static final int STEP_SIZE15 = 15;
	private static final int STEP_SIZE30 = 30;
	private static final int STEP_SIZE50 = 50;
	
	

	private static int F=0;

	public static int EvaluateCounter = 0;
	public static int SearchingCounter = 0;
	public static int MTCounter = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Random r = new Random();
		ArrayList<BitBoard> bArray = new ArrayList<BitBoard>();
		for(int i = 0;i < NUMBER_OF_TEST;i++){
			bArray.add(getRandom(NUMBER_OF_TESUU,  new BitBoard(34628173824L,68853694464L),  true, r));

		}

		long negascoutAverageTime = 0;
		long mtdfAverageTime = 0;
		
		long mtdfStepAverageS15Time = 0;
		long mtdfStepAverageS30Time = 0;
		long mtdfStepAverageS50Time = 0;

		long mtdfAlphaBetaAverageS15Time = 0;
		long mtdfAlphaBetaAverageS30Time = 0;
		long mtdfAlphaBetaAverageS50Time = 0;
		
		

		int negascoutAverageAmount = 0;
		int mtdfAverageAmount = 0;
		
		int mtdfStepAverageS15Amount = 0;
		int mtdfStepAverageS30Amount = 0;
		int mtdfStepAverageS50Amount = 0;

		int mtdfAlphaBetaAverageS15Amount = 0;
		int mtdfAlphaBetaAverageS30Amount = 0;
		int mtdfAlphaBetaAverageS50Amount = 0;

		
		int mtdfAverageMTCounter = 0;
		
		int mtdfStepAverageS15MTCounter = 0;
		int mtdfStepAverageS30MTCounter = 0;
		int mtdfStepAverageS50MTCounter = 0;

		int mtdfAlphaBetaAverageS15MTCounter = 0;
		int mtdfAlphaBetaAverageS30MTCounter = 0;
		int mtdfAlphaBetaAverageS50MTCounter = 0;

		
		for(int c = 0;c < NUMBER_OF_TEST;c++){
			BitBoardNode bbnForNegaScout = new BitBoardNode(bArray.get(c), IS_AI_BLACK);
			BitBoardNode bbnForMTDf = new BitBoardNode(bArray.get(c),IS_AI_BLACK);
			BitBoardNode bbnForMTDfStep15 = new BitBoardNode(bArray.get(c),IS_AI_BLACK);
			BitBoardNode bbnForMTDfStep30 = new BitBoardNode(bArray.get(c),IS_AI_BLACK);
			BitBoardNode bbnForMTDfStep50 = new BitBoardNode(bArray.get(c),IS_AI_BLACK);
			BitBoardNode bbnForMTDfAlphaStep15 = new BitBoardNode(bArray.get(c),IS_AI_BLACK);
			BitBoardNode bbnForMTDfAlphaStep30 = new BitBoardNode(bArray.get(c),IS_AI_BLACK);
			BitBoardNode bbnForMTDfAlphaStep50 = new BitBoardNode(bArray.get(c),IS_AI_BLACK);

			
			
			
			long start = 0L;
			long stop = 0L;

			

			/*
			 * 
			 * ここからネガスカウト法のテスト
			 * 
			 */
			start = System.currentTimeMillis();
			MakeBitBoardTree.negaScoutSearch(bbnForNegaScout,Integer.MIN_VALUE,Integer.MAX_VALUE,TEST_DEPTH,IS_AI_BLACK);
			stop = System.currentTimeMillis();
			negascoutAverageTime += stop-start;
			negascoutAverageAmount += SearchingCounter;
			System.out.println("negascout time is "+(stop - start));
			System.out.println("negascout evaluate is "+EvaluateCounter);
			System.out.println("negascout amount of searching is " + SearchingCounter);
			System.out.println("");

			EvaluateCounter = 0;
			SearchingCounter = 0;

			
			
			
			/*
			 *ここからmtd-f法のテスト 			
			 */
			PermutationTable pt = new PermutationTable();
			start = System.currentTimeMillis();
			F = EvaluateBoard.getEvaluation(bbnForMTDf, IS_AI_BLACK);
			MakeBitBoardTree.mtdfSearch(bbnForMTDf,F,TEST_DEPTH,IS_AI_BLACK,pt);

			stop = System.currentTimeMillis();
			mtdfAverageTime += stop-start;
			mtdfAverageAmount += SearchingCounter;
			mtdfAverageMTCounter += MTCounter;
			System.out.println("mtdf time is "+(stop - start));
			System.out.println("mtdf evaluate is "+EvaluateCounter);
			System.out.println("mtdf amount of searching is " + SearchingCounter);
			System.out.println("mtdf mt counter is "+ MTCounter);
			System.out.println("");
			EvaluateCounter = 0;
			SearchingCounter = 0;
			MTCounter = 0;



			
			/*
			 *ここからmtd-f-step法のテスト 	
			 *ステップサイズ15		
			 */
			pt = new PermutationTable();
			start = System.currentTimeMillis();
			F = EvaluateBoard.getEvaluation(bbnForMTDfStep15, IS_AI_BLACK);
			MakeBitBoardTree.mtdfStepSearch(bbnForMTDfStep15,F,TEST_DEPTH,IS_AI_BLACK,pt,STEP_SIZE15);

			stop = System.currentTimeMillis();
			mtdfStepAverageS15Amount += SearchingCounter;
			mtdfStepAverageS15Time += (stop - start);
			mtdfStepAverageS15MTCounter += MTCounter;
			System.out.println("mtdf-step15 time is "+(stop - start));
			System.out.println("mtdf-step15 evaluate is "+EvaluateCounter);
			System.out.println("mtdf-step15 amount of searching is " + SearchingCounter);
			System.out.println("mtdf-step15 mt counter is "+ MTCounter);
			
			System.out.println("");
			EvaluateCounter = 0;
			SearchingCounter = 0;
			MTCounter = 0;


			
			/*
			 *ここからmtd-f-step法のテスト 	
			 *ステップサイズ30		
			 */
			pt = new PermutationTable();
			start = System.currentTimeMillis();
			F = EvaluateBoard.getEvaluation(bbnForMTDfStep30, IS_AI_BLACK);
			MakeBitBoardTree.mtdfStepSearch(bbnForMTDfStep30,F,TEST_DEPTH,IS_AI_BLACK,pt,STEP_SIZE30);

			stop = System.currentTimeMillis();
			mtdfStepAverageS30Time += (stop-start);
			mtdfStepAverageS30MTCounter += MTCounter;
			mtdfStepAverageS30Amount += SearchingCounter;
			System.out.println("mtdf-step30 time is "+(stop - start));
			System.out.println("mtdf-step30 evaluate is "+EvaluateCounter);
			System.out.println("mtdf-step30 amount of searching is " + SearchingCounter);
			System.out.println("mtdf-step30 mt counter is "+ MTCounter);

			System.out.println("");
			EvaluateCounter = 0;
			SearchingCounter = 0;
			MTCounter = 0;

			
			
			
			/*
			 *ここからmtd-f-step法のテスト 	
			 *ステップサイズ50		
			 */
			pt = new PermutationTable();
			start = System.currentTimeMillis();
			F = EvaluateBoard.getEvaluation(bbnForMTDfStep50, IS_AI_BLACK);
			MakeBitBoardTree.mtdfStepSearch(bbnForMTDfStep50,F,TEST_DEPTH,IS_AI_BLACK,pt,STEP_SIZE50);

			stop = System.currentTimeMillis();
			mtdfStepAverageS50Time += stop-start;
			mtdfStepAverageS50Amount += SearchingCounter;
			mtdfStepAverageS50MTCounter += MTCounter;
			System.out.println("mtdf-step50 time is "+(stop - start));
			System.out.println("mtdf-step50 evaluate is "+EvaluateCounter);
			System.out.println("mtdf-step50 amount of searching is " + SearchingCounter);
			System.out.println("mtdf-step50 mt counter is "+ MTCounter);

			System.out.println("");
			EvaluateCounter = 0;
			SearchingCounter = 0;
			MTCounter = 0;

			


			
			
			
			/*
			 *ここからmtd-f-αβ法のテスト 
			 *ステップサイズ15			
			 */
			pt = new PermutationTable();
			start = System.currentTimeMillis();
			F = EvaluateBoard.getEvaluation(bbnForMTDfAlphaStep15, IS_AI_BLACK);
			MakeBitBoardTree.mtdfAlphaBetaSearch(bbnForMTDfAlphaStep15,F,TEST_DEPTH,IS_AI_BLACK,pt,STEP_SIZE15);
			stop = System.currentTimeMillis();
			mtdfAlphaBetaAverageS15Time += stop-start;
			mtdfAlphaBetaAverageS15Amount += SearchingCounter;
			mtdfAlphaBetaAverageS15MTCounter += MTCounter;
			System.out.println("mtdf-αβ-s15 time is "+(stop - start));
			System.out.println("mtdf-αβ-s15 evaluate is "+EvaluateCounter);
			System.out.println("mtdf-αβ-s15 amount of searching is " + SearchingCounter);
			System.out.println("mtdf-αβ-s15 mt counter is "+ MTCounter);

			System.out.println("");
			EvaluateCounter = 0;
			SearchingCounter = 0;
			MTCounter = 0;

			
			
			/*
			 *ここからmtd-f-αβ法のテスト 
			 *ステップサイズ30			
			 */
			pt = new PermutationTable();
			start = System.currentTimeMillis();
			F = EvaluateBoard.getEvaluation(bbnForMTDfAlphaStep30, IS_AI_BLACK);
			MakeBitBoardTree.mtdfAlphaBetaSearch(bbnForMTDfAlphaStep30,F,TEST_DEPTH,IS_AI_BLACK,pt,STEP_SIZE30);
			stop = System.currentTimeMillis();
			mtdfAlphaBetaAverageS30Time +=(stop - start);
			mtdfAlphaBetaAverageS30Amount += SearchingCounter;
			mtdfAlphaBetaAverageS30MTCounter += MTCounter;
			System.out.println("mtdf-αβ-s30 time is "+(stop - start));
			System.out.println("mtdf-αβ-s30 evaluate is "+EvaluateCounter);
			System.out.println("mtdf-αβ-s30 amount of searching is " + SearchingCounter);
			System.out.println("mtdf-αβ-s30 mt counter is "+ MTCounter);

			System.out.println("");
			EvaluateCounter = 0;
			SearchingCounter = 0;
			MTCounter = 0;

			
			
			
			/*
			 *ここからmtd-f-αβ法のテスト 
			 *ステップサイズ50 			
			 */
			pt = new PermutationTable();
			start = System.currentTimeMillis();
			F = EvaluateBoard.getEvaluation(bbnForMTDfAlphaStep50, IS_AI_BLACK);
			MakeBitBoardTree.mtdfAlphaBetaSearch(bbnForMTDfAlphaStep50,F,TEST_DEPTH,IS_AI_BLACK,pt,STEP_SIZE50);
			stop = System.currentTimeMillis();
			mtdfAlphaBetaAverageS50Time +=(stop - start);
			mtdfAlphaBetaAverageS50Amount += SearchingCounter;
			mtdfAlphaBetaAverageS50MTCounter += MTCounter;
			System.out.println("mtdf-αβ-s50 time is "+(stop - start));
			System.out.println("mtdf-αβ-s50 evaluate is "+EvaluateCounter);
			System.out.println("mtdf-αβ-s50 amount of searching is " + SearchingCounter);
			System.out.println("mtdf-αβ-s50 mt counter is "+ MTCounter);

			System.out.println("");
			EvaluateCounter = 0;
			SearchingCounter = 0;
			MTCounter = 0;



			
			if(c % 10 == 9){
				System.out.println("now is "+ (c+1));
			}
		}
		
		
		
		System.out.println("negascout average time is "+negascoutAverageTime+" ,amout is " +negascoutAverageAmount);
		System.out.println("mtdf average time is "+mtdfAverageTime+" ,amout is " +mtdfAverageAmount+" ,mt counter is "+ mtdfAverageMTCounter);

		System.out.println("mtdf-step average step15 time is "+ mtdfStepAverageS15Time +" ,amout is " +mtdfStepAverageS15Amount+" ,mt counter is "+mtdfStepAverageS15MTCounter);
		System.out.println("mtdf-step average step30 time is "+ mtdfStepAverageS30Time+" ,amout is " +mtdfStepAverageS30Amount+" ,mt counter is "+mtdfStepAverageS30MTCounter);
		System.out.println("mtdf-step average step50 time is "+ mtdfStepAverageS50Time+" ,amout is " +mtdfStepAverageS50Amount+" ,mt counter is "+mtdfStepAverageS50MTCounter);

		
		System.out.println("mtdf-αβ average step15 time is "+mtdfAlphaBetaAverageS15Time+" ,amout is " +mtdfAlphaBetaAverageS15Amount+" ,mt counter is "+mtdfAlphaBetaAverageS15MTCounter);
		System.out.println("mtdf-αβ average step30 time is "+mtdfAlphaBetaAverageS30Time+" ,amout is " +mtdfAlphaBetaAverageS30Amount+" ,mt counter is "+mtdfAlphaBetaAverageS30MTCounter);
		System.out.println("mtdf-αβ average step50 time is "+mtdfAlphaBetaAverageS50Time+" ,amout is " +mtdfAlphaBetaAverageS50Amount+" ,mt counter is "+mtdfAlphaBetaAverageS50MTCounter);
		
		
		
		
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
