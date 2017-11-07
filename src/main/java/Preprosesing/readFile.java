/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprosesing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.TextDirectoryLoader;
import weka.core.tokenizers.WordTokenizer;


/**
 *
 * @author Momon
 */
public class readFile {
    
    
public static void main(String[] args) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            File folder = new File(
                    "C:/Users/Momon/Documents/NetBeansProjects/Searching/src/main/resources/read");
            if (folder.isDirectory()) {
                for (File file : folder.listFiles()) {
                    fileReader = new FileReader(file);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    int lineCount = 0;
                    while (null != (line = bufferedReader.readLine())) {
                        lineCount++;
                        if (3 != lineCount) {
                            System.out.println(line);
                            
             
   
                            
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

//public void Convert(String Path, String Directory, String Filename) throws Exception{
  // String InputFilename = Path + Filename+".csv";
    // load CSV
    //CSVLoader loader = new CSVLoader();
   // loader.setSource(new File(InputFilename));
    //Instances data = loader.getDataSet();

    // save ARFF
    //ArffSaver saver = new ArffSaver();
    //saver.setInstances(data);
    //String FileT = Filename;
    //saver.setFile(new File(Path+Directory+"\\"+FileT));
    //saver.writeBatch();   
    
//}

}