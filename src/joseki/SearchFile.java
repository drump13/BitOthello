package joseki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import main.BitBoard;

public class SearchFile {
	
	
	/*
	 *現在のビットボードを渡してやり、次の手の候補リストを返す 
	 * 
	 */
	public static ArrayList<BitBoard> getNextList(BitBoard b) throws IOException{
		File f = new File("joseki/josekiDatabase.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String s = "";
		ArrayList<BitBoard> result = new ArrayList<BitBoard>();
		while((s = br.readLine()) != null){
			String[] array = s.split(" ");
			if(b.black == Long.parseLong(array[0]) && b.white == Long.parseLong(array[1])){
				result.add(new BitBoard(Long.parseLong(array[2]), Long.parseLong(array[3])));
			}
 		}
		
		return result;
	}
	
	
}
