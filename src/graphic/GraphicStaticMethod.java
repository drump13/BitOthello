package graphic;

public class GraphicStaticMethod {

	public static long convertToLong(int x,int y){
		String s = "";
		for(int i = 0;i<x+8*y;i++){
			s += "0";
		}
		s+=1;
		return Long.parseLong(s,2);
	}
}
