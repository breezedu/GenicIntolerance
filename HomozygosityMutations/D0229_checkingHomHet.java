package coursera_java_duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class D0229_checkingHomHet{
	
	
	public static void main(String[] args) throws FileNotFoundException{
		
		
		//1st readin text file from local dick.
		//D:\Dropbox\PhD Project BioStats\Homozygosity Mutations\als\LizDeidentified_151002\LizDeidentified_151002
		String routine = "D:/Dropbox/PhD Project BioStats/Homozygosity Mutations/als/LizDeidentified_151002/LizDeidentified_151002/";
		
		//initial names for each documents, there are 12 of them together.
		String[] doc = new String[12];
		
		//only two documents for test code:
		//doc[0] = "gene_samp_matrix_high_LOF_het";
		//doc[1] = "gene_samp_matrix_high_LOF_hom";
		
		/**/
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
		/*****/
		
		//2nd initiate an ArrayList of genes:
		ArrayList<gene> geneList = new ArrayList<gene>();
		
		//for each document, check how many genes, how many 1s, how man 2s, how many NAS patients, and how many Controls.
		for(int i=0; i<doc.length; i++){
			
			//append .txt to each document.
			doc[i] += ".txt";
			
			//use a Scanner to 'scan' the document.
			Scanner read_in = new Scanner( new File(routine + doc[i]));
			
			//call checkEachDoc() method, to analysis the document. According to the data readed in from each docs
			//update the ones and twos parameters of each gene in the geneList.
			geneList = checkEachDoc(read_in, doc[i], geneList);
			
			//close the read_in doc.
			read_in.close();
		
		}//end for loop;
		
		
		//3rd, statistic the n1 and n2, update Pi2[] for each gene.
		//check the # of genes in the geneList;
		System.out.println("\n There are " + geneList.size() + " gene objects in the arrayList.");
		for(int i=0; i<200; i++){
			System.out.println("Gene[" + i + "]: \t" + geneList.get(i).name + ", \t" + geneList.get(i).zeros + ", \t" + geneList.get(i).ones + ", \t" + geneList.get(i).twos);
		}
		
		
		
	}//end main()
	

	/**********************
	 * checkEachDoc() method, to do the analysis work.
	 * @param read_in
	 * @param doc
	 * @param geneList 
	 * @return 
	 */
	private static ArrayList<gene> checkEachDoc(Scanner read_in, String doc, ArrayList<gene> geneList) {
		// TODO Auto-generated method stub
		
		//the first line of the document shows how many genes are there
		String first_line = read_in.nextLine();
		
		//split the first line string, with '\t'
		String[] geneStr = first_line.split("\t");
		
		//initiate an arrayList to store gene names
		ArrayList<String> geneNames = new ArrayList<String>();
		
		//initiate an arrayList to store counts of each gene, a parallel arrayList to 
		ArrayList<Integer> countList = new ArrayList<Integer>();
		
		
		//add every gene name to geneList, add 0 to correlate gene index;
		//the first string in geneStr[] array is "sample", so we ignore this one later;
		for(int i=0; i<geneStr.length; i++){
			geneNames.add(geneStr[i]);
			countList.add(0);
			
			//System.out.println("There are " + geneList.size() + " genes in the geneList.");
			if(geneList.size()< geneStr.length){
				
				gene currGene = new gene();
				currGene.name = geneStr[i];
				currGene.zeros = 0;
				currGene.ones = 0;
				currGene.twos = 0;
			
				geneList.add(currGene);
			}//end if geneList is empty condition;
		
		}//end for i<geneStr.length loop;
		
		
		//print out informations
		System.out.println("\nFor document: " + doc);
		
		System.out.println("There are " + geneStr.length + " genes." + " " + geneStr[0] + " " + geneStr[1]);
		
		//initialize ones, twos, NASs, and Controls
		long zero = 0;
		long one = 0;
		long two = 0;
		long NAS = 0;
		long Control = 0;
		
		int MaxCount = 0;
		String maxGuy = "";
		
		//each line starts with NAS like "1899-NAS-1754", or Control like "CONTROL-10"
		while(read_in.hasNextLine()){
			
			int thisCount = 0;
			
			String currLine = read_in.nextLine();
			String[] lineSplit = currLine.split("\t");
			//System.out.print("  " + lineSplit[0]);
			
			//Check each word, see if it contains "control", "nas", or equal to 1 or 2.
			if(lineSplit[0].contains("CONTROL"))
				Control ++;
			else if(lineSplit[0].contains("NAS"))
				NAS ++;
			
			
			//check each data record in the matrix: if it is 0, gene-object.zero +1;
			// else if it is 1, gene-object.one +1;
			// else if it is 2, gene-object.two +1;
			for(int i=1; i<lineSplit.length; i++){
				
				if(lineSplit[i].equals("1")){
					countList.set(i, countList.get(i) + 1);
					thisCount ++;
					one ++;
					
					geneList.get(i).ones = geneList.get(i).ones + 1;
				
				} else if(lineSplit[i].equals("2")){
					countList.set(i, countList.get(i) + 2);
					thisCount += 2;
					two ++;
					
					geneList.get(i).twos = geneList.get(i).twos + 1;
					
				} else {
					
					zero ++;
				
					geneList.get(i).zeros = geneList.get(i).zeros + 1;
					
				}//end if-else conditions;
				
			}//end for i<lineSplit.length loop;
			
			if(thisCount > MaxCount ) {
				MaxCount = thisCount;
				maxGuy = lineSplit[0];
			}
			
			
		}// end while loop;
		
		
		//a secton to check the name of gene with most counts;
		int geneWithMaxCount = 0;
		String geneName = "";
		
		for(int i=0; i<countList.size(); i++){
			if(countList.get(i) > geneWithMaxCount){
				geneWithMaxCount = countList.get(i);
				geneName = geneNames.get(i);
			}
		}
		
		
		System.out.println("The one with most mutations is: " + maxGuy + " with: " + MaxCount + " mutation counts.");
		
		System.out.println("The gene with most mutations is: " + geneName +" with " + geneWithMaxCount +" counts.");
		
		//Print out the results:
		System.out.println("Controls: " + Control);
		System.out.println("NAS: " + NAS);
		System.out.println("Zeros: " + zero);
		System.out.println("Ones: " + one);
		System.out.println("Twos: " + two);

		
		System.out.println("\n ***************************\n");
		
		return geneList;
	}//end of checkEachDoc() method;
	
}//ee

/**********
 * create an object of gene
 * each gene has three parmaters: name, ones, and twos.
 * 
 * @author Jeff
 *
 *
class gene{
	
	String name = "";
	int zeros = 0;
	int ones = 0;
	int twos = 0;
	
}

*/