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
 * Subset the genome information according to the chromosome position, divide the original dataset into 23 sub-datasets; 
 * 
 * for each gene, get the chromosome ID, the gene name, start position, end position, and exon list;
 * Save the gene frame to 
 */
public class D0403_subsetGeneInfoFromCCDS {
	
	public static void main(String[] args) throws IOException{
		
		//1st readin gene_name text file from local dick.
		//D:\PhD\ 
		String routine = "D:/PhD/";
		String file_name = "CCDS.current.txt";
		
		Scanner read_in = new Scanner(new File(routine + file_name));
		
		
		//2nd, inite a buffer writter:
		//save the gene names to a txt document
		File output = new File(routine + "/CCDS_gene_frame_exons.txt");
		BufferedWriter out_Writer = new BufferedWriter(new FileWriter(output));
		
		
		//write the first line, the title line to the CCDS_gene_frame_exons.txt file
		out_Writer.write("Chr" + "\t" + "gene_name" + "\t" + "start" + "\t" + "end" + "exon_list" + "\n");
		
		
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
		String exons = currLine[9];
		
		out_Writer.write(chr + "\t" + geneName + "\t" + start + "\t" + end + "\t" +exons + "\n");
		
		}//end for j<5 loop;
		
		
		
		//close read_in and out_writer
		read_in.close();
		out_Writer.close();
		
		
	}//end main()

	
	
}//end of everything in D0324_getGeneInfoFromCCDS class
