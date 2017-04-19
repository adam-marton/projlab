package tester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Adam
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> expectedOutputs = new ArrayList<String>();
        List<String> receivedOutputs = new ArrayList<String>();
        boolean mismatch = false;
        
        for(Scanner sc = new Scanner(new File(args[0])); sc.hasNext(); ) {
            expectedOutputs.add(sc.nextLine());
        }
        
        for(Scanner sc = new Scanner(new File(args[1])); sc.hasNext(); ) {
            receivedOutputs.add(sc.nextLine());
        }
		
        if(expectedOutputs.size() != receivedOutputs.size()) {
			System.out.println("The outputs are not equal! (size mismatch)");
			mismatch = true;
		} else {
			for(int i = 0; i < expectedOutputs.size(); i++) {
				if(!expectedOutputs.get(i).equals(receivedOutputs.get(i))) {
					System.out.println("Line mismatch at line " + (i+1) + ", expected: '" + expectedOutputs.get(i) + 
							"', received: '" + receivedOutputs.get(i) + "'.");
					mismatch = true;
				}
			}
		}
		
        if(!mismatch) {
            System.out.println("The outputs are equal!");
        }
    }
}
