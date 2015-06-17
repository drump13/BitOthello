package ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PatternRegistory {
	public final static int POW_3_4 = 81;
	public final static int POW_3_5 = 243;
	public final static int POW_3_6 = 729;
	public final static int POW_3_7 = 2187;
	public final static int POW_3_8 = 6561;
	
	private float[] diag4;
	private float[] diag5;
	private float[] diag6;
	private float[] diag7;
	private float[] hovert2;
	private float[] hovert3;
	private float[] hovert4;
	private float[] corner;
	private float[] edge;
	
	public PatternRegistory(){
		diag4 = new float[POW_3_4];
		diag5 = new float[POW_3_5];
		diag6 = new float[POW_3_6];
		diag7 = new float[POW_3_7];
		hovert2 = new float[POW_3_8];
		hovert3 = new float[POW_3_8];
		hovert4 = new float[POW_3_8];
		corner = new float[POW_3_8];
		edge = new float[POW_3_8];
	}
	
	
	/*
	 * 最初に実行される、パターンファイルから値を読み込み配列に格納
	 * 
	 */
	public void setPattern(){
		for(Pattern p: Pattern.values()){
			File file = new File(p.getFileName());
			FileReader f = null;
			try{
				f = new FileReader(file);
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
			BufferedReader br = new BufferedReader(f);
			try{
				setToArray(p, br);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * パターンとバッファを受け取って、そこで読まれてきたfloatを
	 * パターンに対応する配列に格納する
	 */
	public void setToArray(Pattern p,BufferedReader br) throws IOException{
		String s ="";
		int counter = 0;
		while((s = br.readLine()) != null){
			try{
				setArrayFromPattern(p, counter, Float.parseFloat(s));
				counter++;
			}catch(ArrayIndexOutOfBoundsException e){
				break;
			}
		}
	}
	
	/*
	 * 今のパターンレジストリのスコアの状態をファイルに保存する。
	 * 
	 */
	public void saveToFile(){
		for(Pattern p:Pattern.values()){
			File file = new File(p.getFileName());
			FileWriter fw = null; 
			try{
				fw = new FileWriter(file); 
			}catch(IOException e){
				e.printStackTrace();
			}
			float[] current = getArrayFromPattern(p);
			for(int i = 0;i<current.length;i++){
				try {
					fw.write(current[i]+"");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * そのパターンに対応した配列と渡されたインデックスを元に配列に値を入れていく
	 */
	public void setArrayFromPattern(Pattern p,int index,float value){
		switch(p){
		case DIAG4_LU:
			diag4[index] = value;
			break;
		case DIAG5_LU:
			diag5[index] = value;
			break;
		case DIAG6_LU:
			diag6[index] = value;
			break;
		case DIAG7_LU:
			diag7[index] = value;
			break;
		case HORVERT2_U:
			hovert2[index] = value;
			break;
		case HORVERT3_U:
			hovert3[index] = value;
			break;
		case HORVERT4_U:
			hovert4[index] = value;
			break;
		case CORNER_LU:
			corner[index] = value;
			break;
		case EDGE_LU:
			edge[index] = value;
			break;
		default :
			System.out.println("ありえないパターンが選択されました。");
		}

	}
	
	
	
	//そのパターンに対応した配列を返す
	public float[] getArrayFromPattern(Pattern p){
		switch(p){
		case DIAG4_LU:
			return diag4;
		case DIAG5_LU:
			return diag5;
		case DIAG6_LU:
			return diag6;
		case DIAG7_LU:
			return diag7;
		case HORVERT2_U:
			return hovert2;
		case HORVERT3_U:
			return hovert3;
		case HORVERT4_U:
			return hovert4;
		case CORNER_LU:
			return corner;
		case EDGE_LU:
			return edge;
		default :
			System.out.println("ありえないパターンが選択されました。");
			return null;
		}
	}
	
	
	/*
	 * 渡される学習率と報酬に合わせて配列を更新する
	 * (報酬-現在のパラメータ)*学習率分　　だけパラメータにプラスする
	 */
	public void updateArrays(Pattern p,int index,int reward,float learningRate){
		float[]  fArray = getArrayFromPattern(p);
		fArray[index] += (reward - fArray[index])*learningRate;
	}

	
	//全配列の中身を表示
	public void printAllArrays(){
		for(Pattern p : Pattern.values()){
			float[] now = getArrayFromPattern(p);
			System.out.print("[");
			for(int i = 0;i< now.length;i++){
				System.out.print(now[i]+",");
			}
			System.out.println("]"); 
		}
	}
	
	
}
