package coursera_java_duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
		out_Writer.write("Chr" + "\t" + "gene_name" + "\t" + "start" + "\t" + "end" + "\t" + "exon_list" + "\n");
		
		
		
		/**************
		 * initial an ArrayList, to store all chromosome names from the CCDS.current.txt document;
		 * the first verb of each line other than title line would be the chromosome name; 
		 */
		ArrayList<String> chrList = new ArrayList<String>();
		
		
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
		ArrayList<BufferedWriter> chr_writers = new ArrayList<BufferedWriter>();
		
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
			
			
			//this out_Writer will write gene-frame for each gene, no matter which chromosome it locates.
			out_Writer.write(chr + "\t" + geneName + "\t" + start + "\t" + end + "\t" +exons + "\n");
			
			
			/***********
			 * check the chromosome location of current gene; 
			 * 
			 */
			if(!chrList.contains(chr)){
			
				//1. put this chr into chrList
				chrList.add(chr);
			
				//2. create a new Buffer_Writer, put it into chr_writers arrayList
				String txt_name = "/CCDS_" + "chr_" + chr + "_gene_frame_exons.txt";
				File output_temp = new File(routine + txt_name);
				
				BufferedWriter temp_Writer = new BufferedWriter(new FileWriter(output_temp));
				
				
				//write the first line, the title line to the CCDS_gene_frame_exons.txt file
				temp_Writer.write("Chr" + "\t" + "gene_name" + "\t" + "start" + "\t" + "end" +"\t" +  "exon_list" + "\n");
				
				//write the gene information line:
				temp_Writer.write(chr + "\t" + geneName + "\t" + start + "\t" + end + "\t" +exons + "\n");
				

				chr_writers.add(temp_Writer);
				
			
			} else {
			
				//1. get the index of chr in the chrList
				int index = chrList.indexOf(chr);
			
				//2. get the corresponding buffer-writer
				BufferedWriter curr_writer = chr_writers.get(index);
				curr_writer.write(chr + "\t" + geneName + "\t" + start + "\t" + end + "\t" +exons + "\n");
			
			
			}
		
		}//end while(read_in.hasNextLine()) loop;
		
		
		
		//close read_in and out_writer
		read_in.close();
		out_Writer.close();
		
		
		//close all chr_writers in the ArrayList
		System.out.println("There are " + chr_writers.size() + " out-writers. ");
		for(int i=0; i<chr_writers.size(); i++){
			
			chr_writers.get(i).close();
			
		}//end for in chr_writers loop;
		
		
		
	}//end main()

	
	
}//end of everything in D0324_getGeneInfoFromCCDS class
