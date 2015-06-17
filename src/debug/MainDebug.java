package debug;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.*;

public class MainDebug {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BitBoard b = new BitBoard(34628173824L,68853694464L);
		displayBoard(b.black, b.white);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;

		boolean isBlack = true;
		while(!OperateBitBoard.isGameEnd(b)){
			try{
				if(isBlack){
					System.out.print("黒番です。");
				}else{
					System.out.print("白番です。");
				}
				System.out.println("どの場所に石を打ちますか？");
				input = br.readLine();
			}catch(IOException e){
				e.printStackTrace();
			}
			String[] inputArray = input.split(" ");
			long newStone = OperateBitBoard.makeNewStone(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]));
			long[] revPattern = OperateBitBoard.getRevPattern(b, newStone, isBlack);
			long rev = OperateBitBoard.makeRevFromRevPattern(revPattern);
			if(!OperateBitBoard.isThisPuttable(rev)){
				System.out.println("返せる石がありません");
				continue;
			}
			b = OperateBitBoard.putBit(b, isBlack, rev, newStone);
			isBlack = OperateBitBoard.reverse(isBlack);
			displayBoard(b.black, b.white);
		}
	}


	public static void displayBoard(long black,long white){
		String b = OperateBitBoard.complimentZero(Long.toBinaryString(black));
		String w = OperateBitBoard.complimentZero(Long.toBinaryString(white));
		System.out.println(b);
		System.out.println(w);

		for(int i = 0;i<64;i++){
			if(b.charAt(i)=='1'){
				System.out.print("●");
			}else if(w.charAt(i)=='1'){
				System.out.print("○");
			}else{
				System.out.print(" ");
			}
			if(i%8==7){System.out.println("");}
		}
	}

}
