package coursera_java_duke;

import java.util.ArrayList;

/**********
 * create an object of geneData
 * each gene has two arrayLists
 * arrayList controlList
 * and arrayList sampleList
 * 
 * @author Jeff
 *
 */
class geneData{
	
	private String name;
	private ArrayList<Integer> controlList;
	private ArrayList<Integer> sampleList;
	
	//public geneData
	public geneData(String geneName, String sample, int num){
		super();
		
		this.name = geneName;
		
		if(sample.equals("Control")){
			
			setControlList(num);
			
		} else {
			
			setSampleList(num);
		}
		
	}//end public
	
	//get name and set name;
	public String getName(){
		return name;
	}
	
	public void setName(String geneName){
		this.name = geneName;
	}
	
	//get controlList and set controlList
	public ArrayList<Integer> getControlList(){
		return controlList;
	}
	
	public void setControlList(int num){
		this.controlList.add(num);
	}
	
	
	//get sampleList and set sampleList
	public ArrayList<Integer> getSampleList(){
		return sampleList;
	}
	
	public void setSampleList(int num){
		this.sampleList.add(num);
	}
}