/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprosesing;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
//import weka.core.tokenizers.WordTokenizer;

/**
 *
 * @author Momon
 */
public class ReadTXT {
    
    public static void main(String[] args) {
	ReadTXT obj = new ReadTXT();
	System.out.println(obj.getFile("read/test.txt"));
     
      //  WordTokenizer wt =new WordTokenizer();
       // wt.tokenize(obj.getFile("test.txt"));
        
  }

  private String getFile(String fileName) {

	StringBuilder result = new StringBuilder("");

	//Get file from resources folder
	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource(fileName).getFile());

	try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			result.append(line).append("\n");
		}

		scanner.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

	return result.toString();

  }
}
