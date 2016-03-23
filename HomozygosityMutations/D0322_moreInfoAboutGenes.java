package coursera_java_duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class D0322_moreInfoAboutGenes {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		//1st readin gene_name text file from local dick.
		//D:\PhD\LizDeidentified_151002\LizDeidentified_151002
		String routine = "D:/PhD/LizDeidentified_151002/LizDeidentified_151002/";
		String file_name = "gene_names.txt";
		
		Scanner readNames = new Scanner(new File(routine + file_name));
		
		String allnames = readNames.nextLine();
		
		//close the readNames()
		readNames.close();
		
		
		//2nd split the name string, and store all names in an ArrayList;
		String[] names = allnames.split("\t");
		
		ArrayList<String> geneNames = new ArrayList<String>();
		
		for(int i=0; i<names.length; i++){
			
			geneNames.add(names[i]);
			
		} //end for loop;
		
		
		
		//3rd, read gene information from gencode.v24.basic.annotation.gtf
		//the gtf data file was downloaded from ftp://ftp.sanger.ac.uk/pub/gencode/Gencode_human/release_24/
		String geneGTF = "gencode.v24.basic.annotation.gtf";
		
		Scanner readGeneInfo = new Scanner(new File(routine + geneGTF));
		
		String currLine = "";
		while(readGeneInfo.hasNextLine()){
			currLine = readGeneInfo.nextLine();
			
			//elute the headlines start with #
			while(currLine.charAt(0) == '#'){
				currLine = readGeneInfo.nextLine();
			}
			
			
			
			
		}
		
		
		//Have to split each line with RegEx method.
		String[] lastLineTemp = currLine.split("[\\s,;]+");
		
		System.out.println("Print out last line splitted words.");
		for(int i=0; i<lastLineTemp.length; i++){
			
			System.out.print("\t** " + lastLineTemp[i]);
		}
		
	}//end main()
	
	
	
	

}//end of everything;



class geneInfo{
	
	String name = "";
	String chro = "";
	int start = 0;
	int end = 0;
	
} //end draft geneInfo class;
