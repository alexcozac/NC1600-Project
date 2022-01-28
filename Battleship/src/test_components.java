import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


import java.util.ArrayList;
import java.util.Random;

public class test_components {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		int vAxis = 5;
		int hAxis = 1;
		int vUpperAnc = 2;
		int vLowerAnc = 5;
		int state3_hAxis;


		ArrayList<String> test = new ArrayList<String>();		
		Random rand = new Random();
		
		BufferedWriter write = new BufferedWriter(new FileWriter("LearnAndAdapt.txt", true));
		
	      
		
		vLowerAnc = vAxis;
		state3_hAxis = hAxis;
		int p = vLowerAnc - vUpperAnc;
		for (int i = 1; i < p ; i++) {
			// already hit line
			vAxis = vLowerAnc - i;
			// after assign log it
			for (int j = -1; j < 3;) {
				hAxis = state3_hAxis + j;
				test.add(Integer.toString(vAxis) + Integer.toString(hAxis));
				//write.append(Integer.toString(vAxis) + Integer.toString(hAxis));
				//write.newLine();
				j += 2;

			}
		}
		//write.close();
		
		for (int i = 0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		int random = rand.nextInt(test.size());
		vAxis = Character.getNumericValue(test.get(random).charAt(0));
		hAxis = Character.getNumericValue(test.get(random).charAt(1));
		System.out.println("random nr is " + random);
		System.out.println(test.get(random).charAt(0));
		System.out.println(test.get(random).charAt(1));
		System.out.println("_____________");
		System.out.println(vAxis);
		System.out.println(hAxis);
		

		
		long finish = System.currentTimeMillis();
		
		long elapsed = finish - start;
		
		System.out.println(elapsed);
		
		
	
	}

}
