package ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import debug.SearchingTest;
import debug.SearchingTestForSeminer2;

import main.BitBoard;
import main.OperateBitBoard;

public class MakeBitBoardTree {

	/*
	 * 実際にはこの関数が呼ばれることによって,次の盤面を返しそのまま渡す。
	 *　この関数はMainAIでしか呼ばれないものとする
	 * 
	 */
	public static BitBoard getNextBoard(BitBoardNode top){
		makeChildren(top,MainAI.IS_AI_BLACK);

		for(int i = 0;i < top.children.size();i++){
			System.out.println(i);
			recursive(top.children.get(i),MainAI.IS_AI_BLACK,MainAI.AI_LEVEL);
		}
		return top.children.get(getMaxChildrenIndex(top,MainAI.IS_AI_BLACK)).b;
	}

	//引数の子のスコアの中で最も大きなスコアのインデックスを返す。
	public static int getMaxChildrenIndex(BitBoardNode b,boolean isBlack){
		int result=-1;
		int currentMax = isBlack?Integer.MIN_VALUE:Integer.MAX_VALUE;
		for(int i = 0; i < b.children.size();i++){
			if(isBlack?(currentMax <= b.children.get(i).score):(currentMax >= b.children.get(i).score)){
				currentMax = b.children.get(i).score;
				//		System.out.println("score is "+ b.children.get(i).score);
				result = i;
			}
		}
		return result;
	}

	/*
	 * BitBoardNodeに子供のリストをセットする。
	 * もし、次の手番に打つ場所が無かったらその次の手番の打つ場所を探索
	 * 両者打つ場所が無かったらchildrenには何も追加されないことになる。
	 */
	public static void makeChildren(BitBoardNode bbn,boolean isBlack){
		ArrayList<Long> next = OperateBitBoard.getPuttablePlaceList(bbn.b, bbn.nextIsBlack);
		if(next.size() >0){
			for(int i = 0;i<next.size();i++){
				bbn.children.add(new BitBoardNode(OperateBitBoard.pubBitAndReverse(bbn.b, next.get(i), bbn.nextIsBlack),bbn,OperateBitBoard.reverse(bbn.nextIsBlack)));
				bbn.children.get(i).score = (bbn.children.get(i).nextIsBlack == isBlack) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			}
		}else{
			next = OperateBitBoard.getPuttablePlaceList(bbn.b, OperateBitBoard.reverse(bbn.nextIsBlack));
			for(int i = 0;i<next.size();i++){
				bbn.children.add(new BitBoardNode(OperateBitBoard.pubBitAndReverse(bbn.b, next.get(i),OperateBitBoard.reverse(bbn.nextIsBlack)),bbn,bbn.nextIsBlack));
				bbn.children.get(i).score = (bbn.children.get(i).nextIsBlack == isBlack) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

			}
		}


	}


	/*
	 * とりあえず探索、ミニマックス法
	 * 
	 * 
	 */
	public static void recursive(BitBoardNode currentB,boolean isAIBlack,int depth){
		if(depth > 0/*||
				(OperateBitBoard.getNumberOfSumStones(currentB.b) - MainAI.AI_LEVEL > EvaluateBoard.CHANGE_TO_YOMIKIRI
				&& OperateBitBoard.getNumberOfSumStones(currentB.b) < 64 )*/){

			makeChildren(currentB,isAIBlack);
			for(int i = 0;i < currentB.children.size();i++){
				if(alphaCut(currentB,isAIBlack) || betaCut(currentB,isAIBlack)){
					break;
				}
				recursive(currentB.children.get(i),isAIBlack,depth-1);
			}
			doMiniMaxTransfer(currentB,isAIBlack);
			currentB.children = null;

		}else{
			currentB.score = EvaluateBoard.getEvaluation(currentB,isAIBlack);
			doMiniMaxTransfer(currentB,isAIBlack);
		}
	}


	/*
	 * 実際にミニマックス法によってスコアを親に伝播させていく。
	 * */
	public static void doMiniMaxTransfer(BitBoardNode currentB,boolean isBlack){
		if(isBlack==currentB.parent.nextIsBlack && currentB.parent.score < currentB.score){
			currentB.parent.score = currentB.score;
		}else if(isBlack != currentB.parent.nextIsBlack && currentB.parent.score > currentB.score){
			currentB.parent.score = currentB.score;
		}
	}


	//αカット判定
	public static boolean alphaCut(BitBoardNode b,boolean isBlack){
		if(b.nextIsBlack != isBlack && b.score < b.parent.score && b.nextIsBlack != b.parent.nextIsBlack){
			return true;
		}
		return false;
	}

	//βカット判定
	public static boolean betaCut(BitBoardNode b,boolean isBlack){
		if(b.nextIsBlack == isBlack && b.score > b.parent.score && b.nextIsBlack != b.parent.nextIsBlack){
			return true;
		}
		return false;
	}

	public static int negaMax(BitBoardNode currentB,int depth,boolean isAIBlack){
		if(depth == 0 || OperateBitBoard.isGameEnd(currentB.b)){
			return EvaluateBoard.getEvaluation(currentB, isAIBlack);
		}
		makeChildren(currentB, isAIBlack);
		int max = Integer.MIN_VALUE;
		for(int i = 0;i < currentB.children.size();i++){
			max = Math.max(max,-negaMax(currentB.children.get(i), depth-1, isAIBlack));
		}
		currentB.score = max;
		currentB.children = null;
		return max;
	}


	/*
	 * αβ法(wikipedia)版
	 * 
	 */
	public static int alphabeta(BitBoardNode currentB,int alpha,int beta,int depth,boolean isAIBlack){
		if(OperateBitBoard.isGameEnd(currentB.b) || depth == 0){
			return EvaluateBoard.getEvaluation(currentB,isAIBlack);
		}
		makeChildren(currentB, isAIBlack);
		if(currentB.nextIsBlack == isAIBlack){
			int newAlpha = alpha;
			for(int i = 0; i < currentB.children.size();i++){
				newAlpha = Math.max(newAlpha,alphabeta(currentB.children.get(i), newAlpha, beta, depth-1, isAIBlack));
				if(newAlpha >= beta){
					currentB.children = null;
					currentB.score = beta;
					return beta;
				}
			}
			currentB.children = null;
			currentB.score = newAlpha;
			return newAlpha;
		}else{
			int newBeta=beta;
			for(int i = 0;i<currentB.children.size();i++){
				newBeta= Math.min(newBeta,alphabeta(currentB.children.get(i), alpha, newBeta, depth-1, isAIBlack));
				if(alpha >= newBeta){
					currentB.children = null;
					currentB.score = alpha;
					return alpha;
				}
			}
			currentB.children = null;
			currentB.score = newBeta;
			return newBeta;
		}
	}

	/*
	 * ネガアルファ法
	 * 
	 */
	public static int negaAlphaSearch(BitBoardNode currentB,int alpha,int beta,int depth,boolean isAIBlack){
		if(depth == 0 || OperateBitBoard.isGameEnd(currentB.b)){
			return EvaluateBoard.getEvaluation(currentB,isAIBlack);
		}
		makeChildren(currentB, isAIBlack);
		int newAlpha = alpha;
		for(int i = 0;i<currentB.children.size();i++){
			if(currentB.nextIsBlack != currentB.children.get(i).nextIsBlack){
				newAlpha = Math.max(newAlpha, -negaAlphaSearch(currentB.children.get(i), -beta, -newAlpha, depth-1, isAIBlack));
			}else{
				newAlpha =Math.max(newAlpha, negaAlphaSearch(currentB.children.get(i), newAlpha , beta, depth-1, isAIBlack));
			}

			if(newAlpha >= beta){
				currentB.children = null;
				currentB.score=newAlpha;
				return newAlpha;
			}
		}
		currentB.children = null;
		currentB.score = newAlpha;
		return newAlpha;

	}



	/*
	 * ネガスカウト法の実装
	 * 
	 * /
	 */
	public static int negaScoutSearch(BitBoardNode currentB,int alpha,int beta,int depth,boolean isAIBlack){
		SearchingTestForSeminer2.SearchingCounter++;

		if(depth == 0|| OperateBitBoard.isGameEnd(currentB.b)){
			return EvaluateBoard.getEvaluation(currentB,isAIBlack);
		}
		makeChildren(currentB, isAIBlack);
		if(depth > 5){
			//	currentB.children = sortChildren(currentB.children,isAIBlack); //評価値でソート
			//	printList(currentB.children);

		}

		int v = 0;
		int max = v = -negaScoutSearch(currentB.children.get(0), -beta, -alpha, depth-1, isAIBlack);
		int newAlpha = alpha;
		if(beta <= v){
			currentB.score=v;
			return v;
		}
		if(newAlpha < v){
			newAlpha = v; 
		}

		for(int i = 1;i < currentB.children.size();i++){
			v = -negaScoutSearch(currentB.children.get(i), -newAlpha-1, -newAlpha, depth-1, isAIBlack);//Null Window Search
			if(beta <= v){
				currentB.score =v;
				return v;
			}

			if(newAlpha < v){
				newAlpha = v;
				v = -negaScoutSearch(currentB.children.get(i), -beta, -newAlpha, depth-1, isAIBlack);
				if(beta <= v){
					currentB.score = v;
					return v;
				}

				if(newAlpha < v){
					newAlpha = v;
				}

			}
			if(max < v){
				max = v;
			}

		}	
		currentB.score = max;
		return max;

	}


	/*
	 * 手の並び替え、ソート
	 * その盤面の評価値が大きい順番に並び替え、ArrayListの0から格納していく
	 * 
	 */
	private static ArrayList<BitBoardNode> sortChildren(ArrayList<BitBoardNode> children,boolean isAIBlack){
		for(int i = 0;i < children.size();i++){
			children.get(i).sortScore = EvaluateBoard.getEvaluation(children.get(i),isAIBlack
					);
		}

		ArrayList<BitBoardNode> result = new ArrayList<BitBoardNode>(children);

		Collections.sort(result,new CompareBitBodeNode());
		return result;
	}

	private static void printList(ArrayList<BitBoardNode> l){
		for(int i = 0 ; i < l.size();i++){
			System.out.print(l.get(i).sortScore + " ");

		}
		System.out.println("");
	}




	/*
	 * mtd-f法
	 * 
	 */
	public static int mtdfSearch(BitBoardNode currentB,int f ,int depth,boolean isAIBlack,PermutationTable table){
		int g = f;
		int upperBound = Integer.MAX_VALUE;
		int lowerBound = Integer.MIN_VALUE;
		int beta = 0;
		while(lowerBound < upperBound){
			SearchingTestForSeminer2.MTCounter++;
			if(g == lowerBound){
				beta = g+1;
			}else{
				beta = g;
			}
			g = alphaBetaWithMemory(currentB, beta-1, beta, depth, isAIBlack,table);
			if(g < beta){
				upperBound = g;
			}else{
				lowerBound = g;
			}
		}
		return g;

	}




	/*
	 * mtd-f-step法
	 * 
	 */
	public static int mtdfStepSearch(BitBoardNode currentB,int f ,int depth,boolean isAIBlack,PermutationTable table,int stepSize){
		int g = f;
		int upperBound = Integer.MAX_VALUE;
		int lowerBound = Integer.MIN_VALUE;
		int beta = 0;
		int step= stepSize;
		int beforeG = 0;

		if(step < 10){
			return f;
		}

		while(lowerBound < upperBound){
			SearchingTestForSeminer2.MTCounter++;
			beforeG = g;
			if(g == lowerBound){
				beta = beforeG+1;
			}else{
				beta = beforeG;
			}
			g = alphaBetaWithMemory(currentB, beta-1, beta, depth, isAIBlack,table);
			if(g < beta){
				upperBound = beforeG - stepSize;
				g = upperBound;
			}else{
				lowerBound = beforeG + stepSize;
				g = lowerBound;
			}
		}		
		g = mtdfStepSearch(currentB, g, depth, isAIBlack, table, stepSize/2);


		return mtdfSearch(currentB, g, depth, isAIBlack, table);

	}



	/*
	 * mtd-f-αβ法
	 * 
	 */
	public static int mtdfAlphaBetaSearch(BitBoardNode currentB,int f ,int depth,boolean isAIBlack,PermutationTable table,int stepSize){
		int g = f;
		int upperBound = Integer.MAX_VALUE;
		int lowerBound = Integer.MIN_VALUE;
		int beta = 0;
		int beforeG;
	
		while(lowerBound < upperBound){
			beforeG = g;
			if(g == lowerBound){
				beta = beforeG+1;
			}else{
				beta = beforeG;
			}
			g = alphaBetaWithMemory(currentB, beta-1, beta, depth, isAIBlack,table);
			if(g < beta){
				upperBound = beforeG - stepSize;
				g = upperBound;
			}else{
				lowerBound = beforeG + stepSize;
				g = lowerBound;
			}
			System.out.println("lower and upper is "+lowerBound+"   "+upperBound);
			SearchingTestForSeminer2.MTCounter++;
		}
		return alphabeta(currentB, lowerBound, upperBound, depth, isAIBlack);

	}





	public static int alphaBetaWithMemory(BitBoardNode bbn,int alphaK ,int betaK ,int depth,boolean isAIBlack,PermutationTable tableK){
		SearchingTestForSeminer2.SearchingCounter++;
		int alpha = alphaK;
		int beta = betaK;
		PermutationTable table = tableK;
		PermutationTableItem item = null;
		int g = 0;

		if(depth == 0||OperateBitBoard.isGameEnd(bbn.b)){
			g=EvaluateBoard.getEvaluation(bbn, isAIBlack);
		}
		if(depth > 1){
			if((item = table.indexOf(bbn.b.black, bbn.b.white))!= null){
				int lowerBound = item.getLowerBound();
				if(beta <= lowerBound){//failed-high
					bbn.score = lowerBound;
					return lowerBound;
				}
				int upperBound = item.getUpperBound();
				if(upperBound <= alpha ){//failed-low
					bbn.score = upperBound;
					return upperBound;
				}
				//探索窓を狭められるかも
				alpha = Math.max(alpha, lowerBound);
				beta = Math.min(beta,upperBound);
			}

		}


		if(depth == 0||OperateBitBoard.isGameEnd(bbn.b)){
			g=EvaluateBoard.getEvaluation(bbn, isAIBlack);
		}else{
			makeChildren(bbn, isAIBlack);
			if(bbn.nextIsBlack == isAIBlack){
				g = Integer.MIN_VALUE;
				int a = alpha;
				for(int i = 0; i < bbn.children.size();i++){
					g = Math.max(g, alphaBetaWithMemory(bbn.children.get(i), a, beta, depth-1,isAIBlack,table));
					if(beta <= g){//failed-high
						if(depth >1){
							table.addItem(bbn.b, g, Integer.MAX_VALUE);
						}
						bbn.score =g;
						return g;
					}
					a = Math.max(a, g);
				}
			}else{
				g = Integer.MAX_VALUE;
				int b = beta;
				for(int i = 0;i < bbn.children.size();i++){
					g = Math.min(g, alphaBetaWithMemory(bbn.children.get(i), alpha, b, depth-1,isAIBlack,table));
					if(g <= alpha){//failed-low
						if(depth >1){
							table.addItem(bbn.b, Integer.MIN_VALUE, g);
						}
						bbn.score =g;
						return g;
					}
					b = Math.min(b,g);
				}
			}

		}

		table.addItem(bbn.b, g, g);
		bbn.score =g;
		return g;
	}

}
