package objectClasses;

import java.io.IOException;

/**
 * Keeps a log of a patients temperature. Is a vital.
 * @author Taylor Stinson
 */
public class Temperature extends Vital {
	public Temperature(int firstRecord){
		super(firstRecord);
	}
	
	public Temperature(){
		super();
	}
	
	/**
	 * Returns the file path of where the vital is to be saved.
	 * Format : /HeartRate/(healthcardNumber).txt
	 * @param healthCardNumber
	 * @return
	 */
	public String getPath(int healthCardNumber){
		String path = "Temperature/";
		path += String.valueOf(healthCardNumber);
		path += ".txt";
		return path;
	}
	
	/**
	 * Saves this vital log to the file for this patient.
	 * @param healthCardNumber
	 * @throws IOException
	 */
	public void save(int healthCardNumber) throws IOException{
		int[] mapContents;
		String path;
		
		//Getting and writing map contents to auto generated file.
		path = this.getPath(healthCardNumber);
		mapContents = super.getMapContents();
		this.writeToFile(path, mapContents);
	}
}
