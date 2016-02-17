package coursera_java_duke.coursera_java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class checkingHomHet{
	
	
	public static void main(String[] args) throws FileNotFoundException{
		
		//readin text file from local dick.
		//D:\Dropbox\PhD Project BioStats\Homozygosity Mutations\als\LizDeidentified_151002\LizDeidentified_151002
		String routine = "D:/Dropbox/PhD Project BioStats/Homozygosity Mutations/als/LizDeidentified_151002/LizDeidentified_151002/";
		
		//initial names for each documents, there are 12 of them together.
		String[] doc = new String[12];
		
		doc[0] = "gene_samp_matrix_high_LOF_het";
		doc[1] = "gene_samp_matrix_high_LOF_hom";
		doc[2] = "gene_samp_matrix_high_not_benign_het";
		doc[3] = "gene_samp_matrix_high_not_benign_hom";
		
		doc[4] = "gene_samp_matrix_high_not_syn_het";
		doc[5] = "gene_samp_matrix_high_not_syn_hom";
		doc[6] = "gene_samp_matrix_low_LOF_het";
		doc[7] = "gene_samp_matrix_low_LOF_hom";
		
		doc[8] = "gene_samp_matrix_low_not_benign_het";
		doc[9] = "gene_samp_matrix_low_not_benign_hom";
		doc[10] = "gene_samp_matrix_low_not_syn_het";
		doc[11] = "gene_samp_matrix_low_not_syn_hom";
		
		
		//for each document, check how many genes, how many 1s, how man 2s, how many NAS patients, and how many Controls.
		for(int i=0; i<doc.length; i++){
			
			//append .txt to each document.
			doc[i] += ".txt";
			
			//use a Scanner to 'scan' the document.
			Scanner read_in = new Scanner( new File(routine + doc[i]));
			
			//call checkEachDoc() method, to analysis the document.
			checkEachDoc(read_in, doc[i]);
			
			//close the read_in doc.
			read_in.close();
		
		}//end for loop;
		
		
	}//end main()
	

	/**********************
	 * checkEachDoc() method, to do the analysis work.
	 * @param read_in
	 * @param doc
	 */
	private static void checkEachDoc(Scanner read_in, String doc) {
		// TODO Auto-generated method stub
		
		//the first line of the document shows how many genes are there
		String first_line = read_in.nextLine();
		
		//split the first line string, with '\t'
		String[] first_line_spilt = first_line.split("\t");
		
		//print out informations
		System.out.println("\nFor document: " + doc);
		
		System.out.println("There are " + first_line_spilt.length + " genes.");
		
		//initialize ones, twos, NASs, and Controls
		long zero = 0;
		long one = 0;
		long two = 0;
		long NAS = 0;
		long Control = 0;
		//int lines = 0;
		
		
		//each line starts with NAS like "1899-NAS-1754", or Control like "CONTROL-10"
		while(read_in.hasNext()){
			
			String currLine = read_in.next();
			//String[] lineSplit = currLine.split("\t");
			//System.out.print("  " + lineSplit[0]);
			
			//Check each word, see if it contains "control", "nas", or equal to 1 or 2.
			if(currLine.contains("CONTROL"))
				Control ++;
			else if(currLine.contains("NAS"))
				NAS ++;
			else if (currLine.equals("1"))
				one ++;
			else if (currLine.equals("2"))
				two ++;
			else zero ++;
				
			
		}// end while loop;
		
		
		//Print out the results:
		System.out.println("Controls: " + Control);
		System.out.println("NAS: " + NAS);
		System.out.println("Zeros: " + zero);
		System.out.println("Ones: " + one);
		System.out.println("Twos: " + two);

		
		System.out.println("\n ***************************\n");
		
	}//end of checkEachDoc() method;
	
}//ee
