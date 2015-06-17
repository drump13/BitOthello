package main;

import java.util.ArrayList;

public class OperateBitBoard {

	//石を新しく置く
	public static long putStone(long stone,long newStone){
		return stone|newStone;
	}

	//右に1bitシフトさせる
	//引数がtrueならblackの盤情報、falseなら白
	public static long transferRight(long stone){
		return (stone>>1)&9187201950435737471L;
	}

	//右に7bitシフトさせる
	//引数がtrueならblackの盤情報、falseなら白
	public static long transferLeftDown(long stone){
		return (stone>>7)&71775015237779198L;
	}
	//右に8bitシフトさせる
	//引数がtrueならblackの盤情報、falseなら白
	public static long transferDown(long stone){
		return (stone>>8)&72057594037927935L;
	}


	//右に9bitシフトさせる
	//引数がtrueならblackの盤情報、falseなら白
	public static long transferRightDown(long stone){
		return (stone>>9)&35887507618889599L;
	}

	//左に1bitシフトさせる
	//引数がtrueならblackの盤情報、falseなら白
	public static long transferLeft(long stone){
		return (stone<<1)&-72340172838076674L;
	}

	//左に7bitシフトさせる
	//引数がtrueならblackの盤情報、falseなら白
	public static long transferRightUp(long stone){
		return (stone<<7)&9187201950435737344L;
	}

	//左に8bitシフトさせる
	//引数がtrueならblackの盤情報、falseなら白
	public static long transferUp(long stone){
		return (stone<<8)&-256L;
	}

	//左に9bitシフトさせる
	//引数がtrueならblackの盤情報、falseなら白
	public static long transferLeftUp(long stone){
		return (stone<<9)&-72340172838076928L;
	}


	//実際に石を置く
	public static BitBoard putBit(BitBoard board,boolean isBlack,long rev,long newStone){
		if(isBlack){
			return new BitBoard((board.black^rev)|newStone,board.white^rev);
		}else{
			return new BitBoard(board.black^rev,(board.white^rev)|newStone); 
		}
	}

	
	//石を置いてからひっくり返すまでの動作(newStoneには必ず、置けると確定された石が入る)
	public static BitBoard pubBitAndReverse(BitBoard b,long newStone,boolean isBlack){
		long[] revPattern = getRevPattern(b, newStone, isBlack);
		long rev = makeRevFromRevPattern(revPattern);
		return putBit(b, isBlack, rev, newStone);
	}
	
	
	
	
	//そのボードに打てる場所があるかどうかを判定する。
	//ある場合はtrueを返し、無い場合はfalseを返す。
	public static boolean isTherePuttablePlace(BitBoard board,boolean isBlack){
		long blackOrWhite = board.black|board.white;
		String bow = complimentZero(Long.toBinaryString(blackOrWhite));
		
		for(int i = 0;i<64;i++){
			if(bow.charAt(i)=='1'){
				continue;
			}
			char[] ca= new char[64];
			for(int j = 0;j<64;j++){
				ca[j] = '0';
			}
			ca[i]='1';
			
			long cNewStone =0;
			try{
				cNewStone = Long.parseLong(String.valueOf(ca),2);
			}catch(NumberFormatException e){
				cNewStone = -9223372036854775808L;
			}
			long[] revPattern = getRevPattern(board, cNewStone, isBlack);
			if(isThisPuttable(makeRevFromRevPattern(revPattern))){
				return true;
			}
		}
		
		return false;
	}
	
	
	//その座標に打てるかどうかを返す
	//引数はrevでそれが０ならfalse
	public static boolean isThisPuttable(long rev){
		if(rev == 0L){
			return false;
		}else{
			return true;
		}
	}
	
	
	//ひっくり返す予定の石を生成し返す
	public static long makeRevFromRevPattern(long[] revPattern){
		long revResult = 0L;
		for(int i = 0; i < revPattern.length;i++){
			revResult |= revPattern[i];
		}
		return revResult;
	}
	


	//新しい石のボード（long）を返す
	public static long makeNewStone(int x,int y){
		char[] ca= new char[64];
		for(int i = 0;i<64;i++){
			ca[i] = '0';
		}
		ca[x+8*y]='1';
		String s = String.valueOf(ca);
		try{
			return Long.parseLong(s,2);
		}catch(NumberFormatException e){
			return -9223372036854775808L;
		}
	}

	/*
	 * ひっくり返すパターンのlong型の配列を返す
	 * 順番は右１、７、８、９左１、７、８、９
	 * 
	 */
	public static long[] getRevPattern(BitBoard b,long newStone,boolean isBlack){
		long[] revResult = {0L,0L,0L,0L,0L,0L,0L,0L};
		if(((b.black|b.white)&newStone) != 0L){
			return revResult;
		}

		for(int i= 0;i < 8;i++){
			long mask = 0L;
			long rev = 0L;
			switch(i){
			case 0:
				rev = 0L;
				mask = newStone;
				while(true){
					mask = transferRight(mask);
					if(mask == 0L || (isBlack ? mask&b.white : mask&b.black) == 0L){
						break;
					}
					rev |= mask;
				}
				if((mask&(isBlack?b.black:b.white))!=0){
					revResult[i] = rev;
				}
				break;
			case 1:
				 rev = 0L;
				 mask = newStone;
				while(true){
					mask = transferLeftDown(mask);
					if(mask == 0L || (isBlack ? mask&b.white : mask&b.black) == 0L){
						break;
					}
					rev |= mask;
				}
				if((mask&(isBlack?b.black:b.white))!=0){
					revResult[i] = rev;
				}
				
				break;
			case 2:
				 rev = 0L;
				 mask = newStone;
				while(true){
					mask = transferDown(mask);
					if(mask == 0L || (isBlack ? mask&b.white : mask&b.black) == 0L){
						break;
					}
					rev |= mask;
				}
				if((mask&(isBlack?b.black:b.white))!=0){
					revResult[i] = rev;
				}
				
				break;
			case 3:
				 rev = 0L;
				 mask = newStone;
				while(true){
					mask = transferRightDown(mask);
					if(mask == 0L || (isBlack ? mask&b.white : mask&b.black) == 0L){
						break;
					}
					rev |= mask;
				}
				if((mask&(isBlack?b.black:b.white))!=0){
					revResult[i] = rev;
				}
				
				break;
			case 4:
				 rev = 0L;
				 mask = newStone;
				while(true){
					mask = transferLeft(mask);
					if(mask == 0L || (isBlack ? mask&b.white : mask&b.black) == 0L){
						break;
					}
					rev |= mask;
				}
				if((mask&(isBlack?b.black:b.white))!=0){
					revResult[i] = rev;
				}
				
				break;
			case 5:
				 rev = 0L;
				 mask = newStone;
				while(true){
					mask = transferRightUp(mask);
					if(mask == 0L || (isBlack ? mask&b.white : mask&b.black) == 0L){
						break;
					}
					rev |= mask;
				}
				if((mask&(isBlack?b.black:b.white))!=0){
					revResult[i] = rev;
				}
				
				break;
			case 6:
				 rev = 0L;
				 mask = newStone;
				while(true){
					mask = transferUp(mask);
					if(mask == 0L || (isBlack ? mask&b.white : mask&b.black) == 0L){
						break;
					}
					rev |= mask;
				}
				if((mask&(isBlack?b.black:b.white))!=0){
					revResult[i] = rev;
				}
				
				break;
			case 7:
				 rev = 0L;
				 mask = newStone;
				while(true){
					mask = transferLeftUp(mask);
					if(mask == 0L || (isBlack ? mask&b.white : mask&b.black) == 0L){
						break;
					}
					rev |= mask;
				}
				if((mask&(isBlack?b.black:b.white))!=0){
					revResult[i] = rev;
				}
				
				break;
			default:
				System.out.println("デフォルトルール、エラー");
				break;

			}
		}
		return revResult;
	}

	//ゲーム終了ならtrueを返す
	public static boolean isGameEnd(BitBoard board){
		if((board.black|board.white) == -1||board.black==0||board.white==0||
				(getPuttablePlaceList(board, true).size()==0 && getPuttablePlaceList(board,false).size() == 0)){
			return true;
		}
		return false;
	}
	
	//石の反転、実際にはboolean を受け取ってその逆を返す
	public static boolean reverse(boolean isBlack){
		if(isBlack){
			return false;
		}
		return true;
	}
	
	
	//そのボードに打てる石のリストを返す（long型のリスト）
	public static ArrayList<Long> getPuttablePlaceList(BitBoard board,boolean isBlack){
		long blackOrWhite = board.black|board.white;
		String bow = Long.toBinaryString(blackOrWhite);
		bow = complimentZero(bow);
		ArrayList<Long> result = new ArrayList<Long>();
		for(int i = 0;i<64;i++){
			if(bow.charAt(i)=='1'){
				continue;
			}
			char[] ca= new char[64];
			for(int j = 0;j<64;j++){
				ca[j] = '0';
			}
			ca[i]='1';
			long cNewStone = 0L;
			try{
				cNewStone = Long.parseLong(String.valueOf(ca),2);
			}catch(NumberFormatException e){
				cNewStone = -9223372036854775808L;
			}
			long[] revPattern = getRevPattern(board, cNewStone, isBlack);
			if(isThisPuttable(makeRevFromRevPattern(revPattern))){
				result.add(cNewStone);
			}
		}
		
		return result;
	}
	
	//BitStringで６４桁に足りない分を０で補完する
	public static String complimentZero(String s){
		String c = "";
		if(s.length() < 64){
			for(int i = 0;i < 64-s.length();i++){
				c += "0";
			}
		}
		s = c + s;
		return s;
	}


	
	//現在の白石黒石の総和を返す
	public static int getNumberOfSumStones(BitBoard b){
		return Long.bitCount(b.black|b.white);
	}

	
	/*石の数を返す
	* 第一引数はボード
	*　第二引数は黒白の情報
	*/
	public static int getNumberOfStones(BitBoard b,boolean isBlack){
		return Long.bitCount(isBlack ? b.black:b.white);
	}
	
}
