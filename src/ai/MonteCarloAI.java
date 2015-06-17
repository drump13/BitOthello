package ai;

import java.util.ArrayList;
import java.util.Random;

import debug.AIVsAIDebug;

import main.BitBoard;
import main.OperateBitBoard;

public class MonteCarloAI {

	//public static boolean IS_MONTECARLO_BLACK = OperateBitBoard.reverse(VsAIDebug.IS_PLAYER_BLACK);
	public static boolean  IS_MONTECARLO_BLACK = AIVsAIDebug.IS_MON_BLACK;
	public static int MonteCarloAILevel = 500;

	public static BitBoard getNextBoard(BitBoard bitBoard){
		Random r = new Random();
		BitBoardNode b = new BitBoardNode(bitBoard,IS_MONTECARLO_BLACK);
		MakeBitBoardTree.makeChildren(b,IS_MONTECARLO_BLACK);
		int[] nextPut = new int[b.children.size()];

		for(int next = 0; next < b.children.size();next++){		
			for(int i = 0;i< MonteCarloAILevel;i++){
				if(randomPut(b.children.get(next).b,r,IS_MONTECARLO_BLACK)){
					nextPut[next]++;
				}
			}
		}
		return b.children.get(getMaxArrayIndex(nextPut)).b;

	}


	//int型配列を受け取ってそのなかの要素が最も大きいインデックスを返す。
	public static int getMaxArrayIndex(int[] scores){
		int resultIndex = -1;
		int score= -1;
		for(int i = 0;i<scores.length;i++){
			if(score < scores[i]){
				score = scores[i];
				resultIndex = i;
			}
		}
		printArray(scores, resultIndex);
		return resultIndex;
	}

	//終局まで打って、勝ったらtrueを返す
	public static boolean randomPut(BitBoard b,Random r,boolean isBlack){
		ArrayList<Long> nextList = OperateBitBoard.getPuttablePlaceList(b, isBlack);
		if(OperateBitBoard.isGameEnd(b)){
			if(OperateBitBoard.getNumberOfStones(b, IS_MONTECARLO_BLACK) 
					>OperateBitBoard.getNumberOfStones(b, OperateBitBoard.reverse(IS_MONTECARLO_BLACK))){
				return true;
			}else{
				return false;
			}
		}else if(nextList.size()==0){
			return randomPut(b, r, OperateBitBoard.reverse(isBlack));
		}else{
			BitBoard newb = OperateBitBoard.pubBitAndReverse(b, nextList.get(r.nextInt(nextList.size())), isBlack);
			return randomPut(newb,r,OperateBitBoard.reverse(isBlack));

		}
	}


	public static void printArray(int[] scores,int maxIndex){
		System.out.print("[");
		for(int i = 0;i < scores.length;i++){
			System.out.print(scores[i]+",");
		}
		System.out.println("]");
		System.out.println(maxIndex);
	}



}
