package utills;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

public class Utill {
	
	public static final String fileRoute = "C:/Users/John/eclipse-workspace/AIStudy/src/main/AI_Info.txt";

	public static void writeFileWithVector(Vector<String> v) throws IOException {
		
		FileWriter file = new FileWriter(fileRoute, false);
		
		for (int cnt = 0; cnt < v.size(); ++cnt) {
			file.write(v.get(cnt) + "\n");
		}
		
		file.close();
	}

	
	
	public static Vector<String> loadLastInfo() throws IOException {
		
        BufferedReader br = new BufferedReader(new FileReader(fileRoute));
        Vector<String> v = new Vector<>();
        
        while(true) {
        	
            String line = br.readLine();
            
            if (line == null) 
            	break;
            
            v.add(line);
        }
        
        br.close();
		
		return v;
	}
	
	public static void showAll(Vector<String> v) {
		Object[] arr = v.toArray();
		
		Arrays.sort(arr);
		
		for (int cnt = 0; cnt < arr.length; ++cnt) {
			System.out.println(arr[cnt]);
		}
	}
}
