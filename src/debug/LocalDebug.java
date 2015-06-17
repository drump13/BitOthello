package debug;

import java.util.ArrayList;
import java.util.Collections;

import main.*;

public class LocalDebug {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//バブルソート
		int[] scoreArray = {7,5,3,4,74,67,3,34,3};
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0;i < scoreArray.length;i++){
			//result.add(scoreArray[i]);
			result.add(i);
		}
		
		for(int i = scoreArray.length-1;0<i;i--){
			for(int j = 0;j<i;j++){
				if(scoreArray[j] < scoreArray[j+1]){
					int current = scoreArray[j];
					scoreArray[j] = scoreArray[j+1];
					scoreArray[j+1] = current;
					Collections.swap(result, j, j+1);
				}
			}
		}
		
		printList(result);
	
	}

	
	public static void printList(ArrayList<Integer> p){
		for(int i = 0; i < p.size();i++){
			System.out.print(p.get(i) +" ");
		}

	}
}
