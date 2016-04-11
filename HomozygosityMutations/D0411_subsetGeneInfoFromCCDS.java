package coursera_java_duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*****
 * 
 * @author Jeff
 *
 * The consensus coding sequence (CCDS) project: Identifying a common protein-coding gene set for the human and mouse genomes
 * Get the gene "frame" from the CCDS data file;
 * 
 * for each gene, get the chromosome ID, the gene name, start position, end position, and exon Counts;
 * Save the gene frame to 
 */
public class D0411_subsetGeneInfoFromCCDS {
	
	public static void main(String[] args) throws IOException{
		
		//1st readin gene_name text file from local dick.
		//D:\PhD\ 
		String routine = "D:/PhD/";
		String file_name = "CCDS.current.txt";
		
		Scanner read_in = new Scanner(new File(routine + file_name));
		
		
		//2nd, inite a buffer writter:
		//save the gene names to a txt document
		File output = new File(routine + "/CCDS_gene_frame_exonCounts.txt");
		BufferedWriter out_Writer = new BufferedWriter(new FileWriter(output));
		
		
		//write the first line, the title line to the CCDS_gene_frame_exons.txt file
		out_Writer.write("Chr" + "\t" + "gene_name" + "\t" + "geneStart" + "\t" + "geneEnd" + "\t" + "exon_count" + "\n");
		

		
		
		/*******
		 * initial an ArrayList, to store all buffer writers for each chromosome;
		 * while checking each gene's location: 
		 * 		if the chr location is already in the chrList, 
		 * 		then get the index of that "chr" string in the chrList;
		 * 		the same index in chr_writter would be the corresponding writter to write-in data to.
		 * 		
		 * 		if the chr location is not in the chrList, like chr = 12:
		 * 		create a new chr_writter, a new txt file: CCDS.chr12.gene_frame_exon.txt;
		 * 		put string "chr_12" to the chrList; 
		 * 		put chr_writter_12 to the chr_writers arrayList;
		 * 		write-in current line data to CCDS.chr12.gene_frame_exon.txt document;
		 */

		
		//get the headline from CCDS.current.txt document
		String headline = read_in.nextLine();
		System.out.println("The first line: \n" + headline + "\n");

		
		
		
		while(read_in.hasNextLine()){
			String[] currLine = read_in.nextLine().split("\t");
		
			for(int j=0; j<currLine.length; j++){
			
				System.out.print("\t" + currLine[j]);
			}
				
			System.out.println();
		
			//write chr gene-name start and end position info to document
			String chr = currLine[0];
			String geneName = currLine[2];
			String start = currLine[7];
			String end = currLine[8];
			String exonList = currLine[9];
			
			
			/*********
			 * Some genes lake the information about their exons,
			 * so here we ignore these genes;
			 */
			if(exonList.length() > 2){
				int exon_count = 1; 
				
				for(int i=0; i<exonList.length(); i++){
					if(exonList.charAt(i) == ',') exon_count += 1;
				}
				
				//this out_Writer will write gene-frame for each gene, no matter which chromosome it locates.
				out_Writer.write(chr + "\t" + geneName + "\t" + start + "\t" + end + "\t" + exon_count + "\n");

				
			}
			
			
		
		}//end while(read_in.hasNextLine()) loop;
		
		
		
		//close read_in and out_writer
		read_in.close();
		out_Writer.close();		
		
	
		
		
		
	}//end main()

	
	
}//end of everything in D0324_getGeneInfoFromCCDS class
