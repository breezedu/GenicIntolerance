package coursera_java_duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class D0322_saveGeneNames_in_ALSdata {

	public static void main(String[] args) throws IOException{
		
		//1st readin text file from local dick.
				//D:\PhD\LizDeidentified_151002\LizDeidentified_151002
				String routine = "D:/PhD/LizDeidentified_151002/LizDeidentified_151002/";
				
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
				
				
				
				//2nd, innate an arrayList to store all the names of genes in ALS datafile.
				ArrayList<String> geneNames = new ArrayList<String>();
				System.out.println("Step two:");
				

				//for each document, check how many genes, save them to geneNames arrayList, also check mismatches between these gene names.
				for(int i=0; i<doc.length; i++){
					
					//append .txt to each document.
					doc[i] += ".txt";
					
					//use a Scanner to 'scan' the document.
					Scanner read_in = new Scanner( new File(routine + doc[i]));
					

					//check each headline of each doc, to extract the gene names.
					geneNames = getNamesFromEachDoc(read_in, doc[i], geneNames);
					
					//close the read_in doc.
					read_in.close();
				
				}//end for loop;
				
				
				
				//get gencode.v24.basic.annotation.gftf document from: ftp://ftp.sanger.ac.uk/pub/gencode/Gencode_human/release_24/
				
				
				
				
				//save the gene names to a txt document
				File output = new File(routine + "/gene_names.txt");
				BufferedWriter out_Writer = new BufferedWriter(new FileWriter(output));
				
				//write every string in the geneNames arrayList to the output file
				for(int i=0; i<geneNames.size(); i++){
					
					out_Writer.write(geneNames.get(i) + "\t");
				}
				
				out_Writer.write("\n");
		
				
				out_Writer.close();
				
	}//end main();

	
	/******************
	 * 
	 * @param read_in
	 * @param docName
	 * @param geneNames
	 * @return
	 */
	private static ArrayList<String> getNamesFromEachDoc(Scanner read_in, String docName, ArrayList<String> geneNames) {
		// TODO Auto-generated method stub
		int mismatch = 0; //the mistched names in the old header and new header;
		
		
		String firstLine = read_in.nextLine();
		String[] names = firstLine.split("\t");
		
		if(geneNames == null || geneNames.size()==0){

			
			for(int i=1; i<names.length; i++){
				
				geneNames.add(names[i]);
			} //end for loop;
			
			
		} else {
			
			for(int i=1; i<names.length; i++){
				
				if(!geneNames.get(i-1).equals(names[i])) mismatch ++;
			}
			
		}//end if-else conditions;
		
		System.out.println("There are " + mismatch + " mismatches in this doc header.");
		
		return geneNames;
		
	}//end getNamesFromEachDoc() method.



}//end of everything in saveGeneNames_inALSdata.java class;
