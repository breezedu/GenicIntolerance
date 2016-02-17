package coursera_java_duke.coursera_java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class checkingHomHet{
	
	
	public static void main(String[] args) throws FileNotFoundException{
		
		//readin text file from local dick.
		//D:\Dropbox\PhD Project BioStats\Homozygosity Mutations\als\LizDeidentified_151002\LizDeidentified_151002
		String routine = "D:/Dropbox/PhD Project BioStats/Homozygosity Mutations/als/LizDeidentified_151002/LizDeidentified_151002/";
		
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
		
		for(int i=0; i<doc.length; i++){
			
			doc[i] += ".txt";
		
		
		Scanner read_in = new Scanner( new File(routine + doc[i]));
		
		
		checkEachDoc(read_in, doc[i]);
		
		
		
		
		read_in.close();
		
		}
		
	}//end main()

	private static void checkEachDoc(Scanner read_in, String doc) {
		// TODO Auto-generated method stub
		
		String first_line = read_in.nextLine();
		
		
		System.out.println("\nFor document: " + doc);
		System.out.println("There are " + first_line.length() + " genes.");
		
		long one = 0;
		long two = 0;
		long NAS = 0;
		long Control = 0;
		//int lines = 0;
		
		while(read_in.hasNext()){
			
			String currLine = read_in.next();
			//String[] lineSplit = currLine.split("\t");
			//System.out.print("  " + lineSplit[0]);
			
			if(currLine.contains("CONTROL"))
				Control ++;
			else if(currLine.contains("NAS"))
				NAS ++;
			else if (currLine.equals("1"))
				one ++;
			else if (currLine.equals("2"))
				two ++;
				
			
		}// end while loop;
		

		
		System.out.println("Ones: " + one);
		System.out.println("Twos: " + two);
		System.out.println("Controls: " + Control);
		System.out.println("NAS: " + NAS);
		
		System.out.println("\n ***************************\n");
	}//end 
	
}//ee
