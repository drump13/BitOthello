package joseki;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.BitBoard;

public class RegisterToFile {

	
	public static void registerToFile(BitBoard before,BitBoard after){
		try {
			FileWriter fw = new FileWriter(new File("joseki/josekiDatabase.txt"),true);
			registerPatterns(before, after, fw);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * このメソッドでは対称性を考えて登録する。
	 * registerToFileにしか呼ばれないと想定している
	 * 
	 */
	public static void registerPatterns(BitBoard before,BitBoard after,FileWriter fw){
		register(before,after,fw);
		
		BitBoard reverseBeforeB = new BitBoard(Long.reverse(before.black),Long.reverse(before.white));
		BitBoard reverseAfterB = new BitBoard(Long.reverse(after.black), Long.reverse(after.white));
		register(reverseBeforeB,reverseAfterB,fw);
		reverseBeforeB = new BitBoard(Long.reverseBytes(before.black),Long.reverseBytes(before.white));
		reverseBeforeB = new BitBoard(Long.reverseBytes(after.black),Long.reverseBytes(after.white));
		register(reverseBeforeB,reverseAfterB,fw);
		reverseBeforeB = new BitBoard(Long.reverseBytes(Long.reverse(before.black)),Long.reverseBytes(Long.reverse(before.white)));
		reverseBeforeB = new BitBoard(Long.reverseBytes(Long.reverse(after.black)),Long.reverseBytes(Long.reverse(after.white)));
		register(reverseBeforeB,reverseAfterB,fw);
		
		
		
		
	}
	
	
	/*その盤面情報を格納する
	 * 渡されるのは、filewriterオブジェクトと
	 * 打つ前のbitboard,打ったあとのbitboard
	 * 
	 */
	public static void register(BitBoard before,BitBoard after,FileWriter fw){
		String s = before.black +" "+before.white+" "+after.black+" "+after.white+"\n";
		try {
			fw.write(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
