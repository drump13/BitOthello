package debug;

import java.io.File;

public class FileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("hogehoge.txt");
		if(f.exists()){
			System.out.println("ok!!");
		}else{
			System.out.println("no");
		}
	}

}
