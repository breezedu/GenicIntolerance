package coursera_java_duke;

import java.util.ArrayList;

/*************
 * For a given number, get all possible combinations:
 * i.e. take 3 for example:
 * 
 * 0 0 0
 * 0 0 1
 * 0 0 2
 * 0 1 0
 * 0 1 1
 * 0 2 0
 * 0 2 1
 * 0 2 2
 * 1 0 0 
 * 1 0 1
 * 1 0 2
 * 1 1 0
 * 1 1 1
 * 1 2 0
 * 1 2 1
 * 1 2 2
 * 2 0 0
 * 2 0 1
 * 2 0 2 
 * 2 1 0
 * 2 1 1
 * 2 2 0
 * 2 2 1
 * 2 2 2
 * 
 * @author Jeff
 *
 */
public class GetAllCombinations_3base {
	
	
	// the main function;
	public static void main(String[] args){
		
		//create a new object of GetAllCombinations_3base()
		GetAllCombinations_3base comb = new GetAllCombinations_3base();
		
		System.out.println("We are going to get all combinations for #4! \n");
		
		
		//call GetAllCombinations_3base.run() method to generate all combinations of 4 positions:
		ArrayList<ArrayList<Integer>> com_of_n = comb.run(5);
		
		//print out all combinations;
		Print_ArrayListOfCombinations(com_of_n);
		
		
	}

	
	/***********
	 * Printout arrayList of arrayList
	 * Each inner arrayList will be printed in one line;
	 * 
	 * @param aList
	 */
	private static void Print_ArrayListOfCombinations(ArrayList<ArrayList<Integer>> aList) {
		// TODO Auto-generated method stub
		int size = aList.size();				//the # of arrayLists in the AL
		int inner_size = aList.get(0).size();	//the # of numbers in each inner ArrayList;
		
		for(int i=0; i<size; i++){
			
			for(int j=0; j<inner_size; j++){
				
				System.out.print(aList.get(i).get(j) + "\t");
			}
			
			System.out.println();
		}
		
		System.out.println();
		
	}//end print_arrayListOfcombinations() method;



	/*********
	 * Pass by a number
	 * generate all possible combinations
	 * store those combinations into ArrayLists
	 * sotre all these ArrayLists into one big ArrayList; 
	 * 
	 * @param num
	 * @return
	 */
	private ArrayList<ArrayList<Integer>> run(int num) {
		// TODO Auto-generated method stub
		
		// create one empty ArrayList to store ArrayList of combinations
		ArrayList<ArrayList<Integer>> retAL = new ArrayList<ArrayList<Integer>>();
		
		// return an empty arrayList if num < 1;
		if(num < 1) return retAL;
		
		
		// generate initial 3 ArrayList with on 0, 1, and 2 in each inner ArrayList;
		// then add all these 3 inner arrayLists to the retAL;
		for(int i=0; i<3; i++){
			
			ArrayList<Integer> currAL = new ArrayList<Integer>();
			currAL.add(i);
			
			retAL.add(currAL);
		}
		
		if(num == 1) return retAL;
		

		//call extendArrayList() method to exten current retAL arrayList, add 0, 1, and 2 seperately to each arrayList in it.
		for(int i=1; i<num; i++){
			
			retAL = extendArrayList(retAL);
		}
		
		return retAL;
		
	}// end run() method;


	/***********
	 * passby an exist ArrayList with arrayLists in it;
	 * 
	 * create a new ArrayList of ArrayList, get every arrayList from retAL, extend it with 0, 1, and 2, separately
	 * then we get 3 newly updated arrayLists, add them to the new ArrayList of ArrayList;
	 * 
	 * @param aList
	 * @return
	 */
	private ArrayList<ArrayList<Integer>> extendArrayList(ArrayList<ArrayList<Integer>> aList) {
		// TODO Auto-generated method stub

		ArrayList<ArrayList<Integer>> returnAL = new ArrayList<ArrayList<Integer>>(); 
		
		int size = aList.size();
		
		for(int i=0; i<size; i++){
			
			ArrayList<Integer> currList = aList.get(i);
			
			ArrayList<Integer> addZero = new ArrayList<Integer>(currList);
			ArrayList<Integer> addOne  = new ArrayList<Integer>(currList);			
			ArrayList<Integer> addTwo  = new ArrayList<Integer>(currList);
			
			addZero.add(0);
			addOne.add(1);
			addTwo.add(2);

			//add extended arrayLists to the returnAL;
			returnAL.add(addZero);
			returnAL.add(addOne);
			returnAL.add(addTwo);

		}
		
		return returnAL;
	}//end extendArrayList() method; 

	
	
}//end of everything in GetAllcombinations_3base class
