package objectClasses;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to keep records of patients vitals.
 * @author Taylor Stinson
 *
 */
public abstract class Vital {
	private Map<Integer, Integer> record = new HashMap<Integer, Integer>();
	protected int entryNumber;
	protected String path;
	
	/**
	 * Adds the very first record to the patients record of vitals.
	 * @param firstRecord - first record to be added to vital log
	 */
	public Vital(int firstRecord){
		this.record.put(1, firstRecord);
		this.entryNumber = 2;
	}
	
	public Vital (){
		this.entryNumber = 1;
	}
	
	/**
	 * Adds a new record to the patients record of vitals.
	 * @param newRecord - new record to be added to vital log
	 */
	public void Update(int newRecord){
		this.record.put(entryNumber, newRecord);
		entryNumber += 1;
	}
	
	/**
	 * Gets the latest vital entry. 
	 * @return - Returns object as Blood pressure cannot return int
	 * like Temperature and Heart rate - use of this function
	 * must be cast as int().
	 */
	public int getCurrentValue(){
		return this.record.get(entryNumber);
	}
	
	/**
	 * Gets the indicated previous entry in the vital record. 
	 * @param previousEntryNumber
	 * @return - Returns object as Blood pressure cannot return int
	 * like Temperature and Heart rate - use of this function
	 * must be cast as int().
	 */
	public int getPreviousValue(int previousEntryNumber){
		return this.record.get(previousEntryNumber);
	}
	
	/**
	 * Returns all the records added into this vital log for this 
	 * patient
	 * @return
	 */
	public int[] getMapContents(){
		int[] mapContents = new int[this.entryNumber];
		
		for (int i = 0; i < this.entryNumber; i++){
			mapContents[i] = this.record.get(i);
		}
		return mapContents;
	}
	
	/**
	 * Writes a series of integers to a given file
	 * @param path
	 * @param mapContents
	 * @throws IOException
	 */
	public void writeToFile(String path, int[] mapContents) throws IOException{
		FileWriter fw = new FileWriter(path, false);
		PrintWriter printLine = new PrintWriter(fw);
		
		for (int i = 0; i < this.entryNumber; i++){
			printLine.printf(String.valueOf(mapContents[i]),"%n");
		}
		
	}
	
	public String[] readInFile(){
		String[] fileContent = null;
		//fileContent = new String[this.fileLength()];
		
		return fileContent;
	}
	
	public void load(long healthCardNum){
		//this.path = healthCardNum;
		//Read in file information to String[]
		
		//parse and assigne it
	}
}
