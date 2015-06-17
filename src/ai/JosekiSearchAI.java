package ai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import joseki.SearchFile;

import main.BitBoard;

public class JosekiSearchAI {
	public static BitBoard getNextBoard(BitBoard b){
		ArrayList<BitBoard> next = null;
 		try {

 			next = SearchFile.getNextList(b);
		
 			
 			//リストが空ならnullを返すかどうかはまた再考する必要有り
 			if(next.isEmpty()){return new BitBoard(0, 0);}
 		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 		Random r = new Random();
 		
 		return next.get(r.nextInt(next.size()));
	}
}
