package debug;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ai.Pattern;

public class InitializePatternLearning {

	/**
	 * @param args
	 */
	public static int POW_3_8 = 6561;
	
	public static void main(String[] args) {
		
		for(Pattern p:Pattern.values()){
			File f = new File(p.getFileName());
			FileWriter fr = null;
			try {
				if(!f.createNewFile()){
					f.delete();
					f.createNewFile();
				}
				fr = new FileWriter(f);
				fr.write(initString());
				fr.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	private static String initString(){
		String s = "";
		for(int i = 0; i < POW_3_8;i++){
			s += "0\n";
		}
		return s;
	}
	

}
