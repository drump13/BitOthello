package ai;

import main.BitBoard;
import main.OperateBitBoard;

public class PatternLearning {

	/**
	 * @param args
	 */
	public final static int POW_3_8 = 6561;
	
	
	/*
	 * 現在のボードの状況とパターン毎の評価値を格納したパターンレジストリを受け取って、そのボードのスコアを返す
	 * 
	 */
	public static int getScore(BitBoard board,PatternRegistory pr,boolean isBlack){
		//todo
		float f = 0;
		
		for(Pattern p:Pattern.values()){
			int index = getTernaryNumeral(board, p.getBoard());
			f += pr.getArrayFromPattern(p)[index];
		}
		return (int) (isBlack ? (f*1000):(-f*1000));
	}

	/*
	 * 現在のボードの状況からそれぞれのスコアを割り出し、
	 * パターンレジストリを更新する
	 * パターンの対称性も考慮してそれぞれのインデックスについて値を更新するようにする。
	 */
	public static void registerParametersFromBoard(BitBoard board,PatternRegistory pr,int reward,float learningRate){
		for(Pattern p:Pattern.values()){
			int index = getTernaryNumeral(board, p.getBoard());
			pr.updateArrays(p, index, reward, learningRate);
			index = getTernaryNumeral(board, Long.reverse(p.getBoard()));
			pr.updateArrays(p, index, reward, learningRate);
			index = getTernaryNumeral(board, Long.reverseBytes(p.getBoard()));
			pr.updateArrays(p, index, reward, learningRate);
			index = getTernaryNumeral(board, Long.reverse(Long.reverseBytes(p.getBoard())));
			pr.updateArrays(p, index, reward, learningRate);
		}
	}
	
	
	
	/*
	 * ボード情報から3進数を計算して返す,ハッシュ関数
	 */
	public static int getTernaryNumeral(BitBoard b,long patternBoard){
		int result = 0;
		int count = 0;
		String blackStirng = OperateBitBoard.complimentZero(Long.toBinaryString(b.black & patternBoard));
		String whiteString = OperateBitBoard.complimentZero(Long.toBinaryString(b.white & patternBoard));
		String pattern = OperateBitBoard.complimentZero(Long.toBinaryString(patternBoard));
		for(int i = 0; i < 64;i++){
			if(pattern.charAt(i) == '1'){
				if(blackStirng.charAt(i) == '1'){
					result += 1 + Math.pow(3, count);
				}else if(whiteString.charAt(i) == '1'){
					result += 2 + Math.pow(3, count);
				}
				count++;
			}
		}	
		
		return result;
	}
	
}
