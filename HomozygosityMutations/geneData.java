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
	private double sampleMean;
	private double controlMean;
	private double sampleVar;
	private double controlVar;
	
	//public geneData
	public geneData(String geneName){
		super();
		
		this.name = geneName;	
		this.controlList = new ArrayList<Integer>();
		this.sampleList = new ArrayList<Integer>();
		this.sampleMean = 0;
		this.controlMean = 0;
		this.sampleVar = 0;
		this.controlVar = 0;
		
		
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
	
	//set sampleMean and get sample mean;
	public void setSampleMean(){
		
		double sum = 0.0;
		for(int i=0; i<this.sampleList.size(); i++){
			sum += this.sampleList.get(i);
		}
		
		this.sampleMean = sum/this.sampleList.size();
	}
	
	public double getSampleMean(){
		return this.sampleMean;
	}
	
	//set control mean and get control mean
	public void setControlMean(){
		double sum = 0.0;
		
		for(int i=0; i<this.controlList.size(); i++){
			sum += this.controlList.get(i);
		}
		
		this.controlMean = sum/this.controlList.size();
	}
	
	public double getControlMean(){
		
		return this.controlMean;
	}
	
	//calculate and get control-variance;
	public void calControlVar(){
		double mean = getControlMean();
		double temp = 0;
		
		for(int i=0; i<this.controlList.size(); i++){
			temp += (mean - this.controlList.get(i) * (mean - this.controlList.get(i)));
		}
		
		this.controlVar = temp/this.controlList.size();
	}
	
	public double getControlVar(){
		return this.controlVar;
	}
	
	//calculate and get sample-variance;
	public void calSampleVar(){
		double mean = this.sampleMean;
		double temp = 0;
		
		for(int i=0; i<this.sampleList.size(); i++){
			temp += (mean - this.sampleList.get(i)) * (mean - this.sampleList.get(i));
		}
		
		this.sampleVar = temp/this.sampleList.size();
	}
	
	public double getSampleVar(){
		return this.sampleVar;
	}
	
}//end class geneData