package ai;

import main.BitBoard;

public class MonteCarloLearning {

	public static int MONTE_LEARNING_AI_LEVEL = 4;
	
	
	/*
	 * モンテカルロ学習アルゴリズムに基づいたパターン評価を行いそれを返す
	 * 現在のボードの状況を引数にとり、次のボードの状態を返す
	 */
	public static BitBoard getNextBoard(BitBoard bitBoard,boolean isBlack,PatternRegistory pr){
		BitBoardNode bn = new BitBoardNode(bitBoard,isBlack);
		MakeBitBoardTree.makeChildren(bn,isBlack);
		
		for(int i = 0;i < bn.children.size();i++){
			recursivePattern(bn.children.get(i),isBlack,pr,MONTE_LEARNING_AI_LEVEL);
		}
		return bn.children.get(MakeBitBoardTree.getMaxChildrenIndex(bn,isBlack)).b;
	}
	
	/*
	 * ミニマックス法にαβカットを入れて高速化してある、評価関数はパターン別に評価するタイプのもの
	 * 
	 */
	public static void recursivePattern(BitBoardNode currentB,boolean isBlack,PatternRegistory pr,int depth){
		if(depth !=0){
			MakeBitBoardTree.makeChildren(currentB,isBlack);
			for(int i = 0;i < currentB.children.size();i++){
				if(MakeBitBoardTree.alphaCut(currentB,isBlack) || MakeBitBoardTree.betaCut(currentB,isBlack)){
					break;
				}
				recursivePattern(currentB.children.get(i),isBlack,pr,depth-1);
			}
			MakeBitBoardTree.doMiniMaxTransfer(currentB,isBlack);
			currentB.children = null;
		
		}else{
			currentB.score = PatternLearning.getScore(currentB.b, pr,isBlack);
			MakeBitBoardTree.doMiniMaxTransfer(currentB,isBlack);
		}
	}
	
	
	
	
	
}
