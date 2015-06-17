package ai;
import debug.AIVsAIDebug;
import main.*;

public class MainAI {
	public final static boolean IS_AI_BLACK = OperateBitBoard.reverse(AIVsAIDebug.IS_MON_BLACK); 
	
	//public final static boolean IS_AI_BLACK = OperateBitBoard.reverse(VsAIDebug.IS_PLAYER_BLACK); 
	public final static int AI_LEVEL = 8;
	
	/*とりあえずミニマックス法
	 * そのうちいろいろなタイプのAIを実装する。
	 * 
	 */
	public static BitBoard nextPut(BitBoard b){
		//todo
		BitBoardNode currentBoard = new BitBoardNode(b,IS_AI_BLACK);
		return MakeBitBoardTree.getNextBoard(currentBoard);
	}
	

	
	
}
