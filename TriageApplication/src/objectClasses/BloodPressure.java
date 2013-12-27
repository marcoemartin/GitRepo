package objectClasses;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Keeps a log of patients blood pressure. Is a vital.
 * @author Taylor Stinson
 */
public class BloodPressure extends Vital {
	private Map<Integer, Integer> Systallic = new HashMap<Integer, Integer>();
	
	/**
	 * Adds the first two blood pressure readings into a log to keep track of
	 * a patients blood pressure. 
	 * @param firstDiastolic
	 * @param firstSystallic
	 */
	public BloodPressure(int firstDiastolic, int firstSystallic){
		super(firstDiastolic);
		this.Systallic.put(super.entryNumber, firstSystallic);
	}
	
	public BloodPressure(){
		
	}
	
	/**
	 * Updates patients bloodpressure log with two new readings
	 * @param newDiastolic
	 * @param newSystallic
	 */
	public void UpdateDiastolic(int newDiastolic){
		super.Update(newDiastolic);
	}
	
	public void UpdateSystallic(int newSystallic){
		this.Systallic.put(super.entryNumber, newSystallic);
	}
	
	/**
	 * Gets the most recent Diastolic reading
	 * @return
	 */
	public int getCurrentDiastolic(){
		return super.getCurrentValue();
	}
	
	/**
	 * Gets the most recent Systallic reading
	 * @return
	 */
	public int getCurrentSystallic(){
		return this.Systallic.get(super.entryNumber);
	}
	
	/**
	 * Gets a previous Diastolic reading
	 * @param previousEntryNumber
	 * @return
	 */
	public int getPreviousDiastolic(int previousEntryNumber){
		return super.getPreviousValue(previousEntryNumber);
	}
	
	/**
	 * Gets a previous Sysatllic reading
	 * @return
	 */
	public int getPreviousSystallic(int entryNumber){
		return this.Systallic.get(entryNumber);
	}
	
	public String[] getFilePath(int healthCardNumber){
		String[] path = new String[2];
		
		path[0] = "Diastollic/";
		path[1] = "Systallic/";
 		path[0] += String.valueOf(healthCardNumber);
 		path[1] += String.valueOf(healthCardNumber);
 		path[0] += ".txt";
 		path[1] += ".txt";
 		
		return path;
	}
	
	public int[] getSystallicContents(){
		int[] systallicContents = new int[super.entryNumber];
		
		for (int i = 0; i < this.entryNumber; i++){
			systallicContents[i] = this.Systallic.get(i);
		}
		return systallicContents;
	}
	
	/**
	 * Saves this vital log to the file for this patient.
	 * @param healthCardNumber
	 * @throws IOException
	 */
	public void save(int healthCardNumber) throws IOException{
		int[] diastollicContents;
		int[] systallicContents;
		String[] path;
		
		//Getting and writing map contents to auto generated file.
		path = this.getFilePath(healthCardNumber);
		diastollicContents = getMapContents();
		systallicContents = getSystallicContents();
		//Write to File
		super.writeToFile(path[0], diastollicContents);
		super.writeToFile(path[1], systallicContents);
	}
}
