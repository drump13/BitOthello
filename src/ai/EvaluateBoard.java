package ai;
import debug.SearchingTest;
import debug.SearchingTestForSeminer2;
import main.*;

public class EvaluateBoard {
	
	public final static int WEIGHT_OF_KAKUTEISEKI = 100; //確定石の評価の重み
	public final static int WEIGHT_OF_PUTTABLE_PLACE = 30;//自分の置ける場所の重み
	public final static int WEIGHT_OF_OPEN = 1;//開放度理論に基づくボードの重み
	public final static int WEIGHT_OF_STONENUMBER = 17;//自分の石の数の重み
	
	public final static int CHANGE_TO_YOMIKIRI = 52;
	
	public static int getEvaluation(BitBoardNode bbn,boolean isAIBlack){
		
		//todo
		
	/*	if(CHANGE_TO_YOMIKIRI < OperateBitBoard.getNumberOfSumStones(b) - MainAI.AI_LEVEL){
			
			return 0;// OperateBitBoard.getPuttablePlaceList(b, MainAI.IS_AI_BLACK).size()*WEIGHT_OF_PUTTABLE_PLACE
			//	+ getKakuteiSeki(b, MainAI.IS_AI_BLACK)*WEIGHT_OF_KAKUTEISEKI;
		}else{*/
		SearchingTest.counter++;
		SearchingTestForSeminer2.EvaluateCounter++;
		//return OperateBitBoard.getNumberOfStones(bbn.b, bbn.nextIsBlack) - OperateBitBoard.getNumberOfStones(bbn.b, OperateBitBoard.reverse(bbn.nextIsBlack));
		
		return WEIGHT_OF_PUTTABLE_PLACE*(OperateBitBoard.getPuttablePlaceList(bbn.b, isAIBlack).size() - OperateBitBoard.getPuttablePlaceList(bbn.b, OperateBitBoard.reverse( isAIBlack)).size())
				+WEIGHT_OF_KAKUTEISEKI*(getKakuteiSeki(bbn.b, isAIBlack) - getKakuteiSeki(bbn.b, OperateBitBoard.reverse(isAIBlack)))
				+WEIGHT_OF_STONENUMBER*(OperateBitBoard.getNumberOfStones(bbn.b, isAIBlack) - OperateBitBoard.getNumberOfStones(bbn.b,OperateBitBoard.reverse(isAIBlack)));
	}
	
	
	//自分の色の確定石の数を返す
	private static int getKakuteiSeki(BitBoard b,boolean isBlack){
		long a = -9223372036854775808L;
		long current = 0L;
		int count=0;
		
		//左上の角から確定石を探索していく　
		if((a&(isBlack? b.black:b.white)) != 0){
			current = a;
			while(current != 0){
				current = OperateBitBoard.transferDown(current);
				if(((isBlack?b.black:b.white)&current) != 0L){
					count++;
				}

			}
			while(current != 0){
				current = OperateBitBoard.transferRight(current);
				if(((isBlack?b.black:b.white)&current) != 0L){
					count++;
				}
			}
		}
		
		
		//右上の角から確定石を探索していく
		a = 72057594037927936L;
		if((a&(isBlack? b.black:b.white)) != 0){
			current = a;
			while(current != 0){
				current = OperateBitBoard.transferDown(current);
				if(((isBlack?b.black:b.white)&current) != 0L){
					count++;
				}

			}
			while(true){
				current = OperateBitBoard.transferLeft(current);
				if(current ==0L){
					count-=8;
					break;
				}
				if(((isBlack?b.black:b.white)&current) != 0L){
					count++;
				}else{
					break;
				}
			}
			
		}
		
		//左下の角から確定石を探索していく
		a=128L;
		if((a&(isBlack? b.black:b.white)) != 0){
			current = a;
			while(current != 0){
				current = OperateBitBoard.transferRight(current);
				if(((isBlack?b.black:b.white)&current) != 0L){
					count++;
				}

			}
			while(true){
				current = OperateBitBoard.transferUp(current);
				if(current ==0L){
					count-=8;
					break;
				}
				if(((isBlack?b.black:b.white)&current) != 0L){
					count++;
				}else{
					break;
				}
			}
		}
		
		//右下の角から確定石を探索していく
		a=1L;
		if((a&(isBlack? b.black:b.white)) != 0){
			current = a;
			while(true){
				current = OperateBitBoard.transferUp(current);
				if(current ==0L){
					count-=8;
					break;
				}
				if(((isBlack?b.black:b.white)&current) != 0L){
					count++;
				}else{
					break;
				}
			}
			while(true){
				current = OperateBitBoard.transferLeft(current);
				if(current ==0L){
					count-=8;
					break;
				}
				if(((isBlack?b.black:b.white)&current) != 0L){
					count++;
				}else{
					break;
				}
			}
			
		}
		return count;
	}
	
	private static int getOpenDegree(BitBoard b){
		//todo
		return 0;
	}
	
	
}
