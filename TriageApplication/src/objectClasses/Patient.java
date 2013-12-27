package objectClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

public class Patient extends Person{
	private static final long serialVersionUID = 5132464465254696339L;
	private long healthCardNum;
	private boolean attendedTo;
	private Date arrivalTime;
	private String filePath;
	private int urgency;
	private HeartRate heartRate = new HeartRate();
	private BloodPressure BP = new BloodPressure();
	private Temperature temp = new Temperature();
	private Date DOB;
	private File patientFile;

	/**
	 * Constructs a blank patient. Meant to be used for when patients are
	 * being read in from file.
	 */
	public Patient(){
		this.heartRate = new HeartRate();
		this.BP = new BloodPressure();
		this.temp = new Temperature();		
	}
	
	/**
	 * Constructs a Patient. Meant to be used for when a patient first
	 * comes to the hospital
	 * @param first 		
	 * @param last 			
	 * @param healthCardNum 
	 * @param attendedTo    
	 * @param arrivalTime
	 */
	public Patient(String first, String last, int healthCardNum, boolean attendedTo, 
			Date arrivalTime, Date dateOfBirth){
		this.first = first;
		this.last = last;
		this.healthCardNum = healthCardNum;
		this.attendedTo = attendedTo;
		this.arrivalTime = arrivalTime;
		this.filePath = getFilePath();
		this.DOB = dateOfBirth;
		this.heartRate = new HeartRate();
		this.BP = new BloodPressure();
		this.temp = new Temperature();
 	}
	
	/**
	 * Auto-generates a path for this particular patient.
	 * @return
	 */
	public String getFilePath() {
		String filePath;
		filePath = "/Patients/";
		filePath += Long.toString(healthCardNum);
		filePath += ".txt";
		return filePath;
	}
	
	/**
	 * Parses through data read in from patient file assigning each
	 * line to the appropriate patient variable
	 */
	public void assignData(String[] readData){
		this.first = readData[0];
		this.last = readData[1];
		this.healthCardNum = Long.valueOf(readData[2]);
		this.attendedTo = Boolean.valueOf(readData[3]);
		this.arrivalTime = Date.valueOf(readData[4]);
	}
	
	/**
	 * Gets the number of items in patient text file.
	 * @return - Returns number of items in the file. 
	 */
	public int getFileLength() throws IOException{
		int numberOfLines = 0;
		String buffer;
		
		//Opens file for reading
		FileReader fr = new FileReader(this.patientFile);
		BufferedReader textReader = new BufferedReader(fr);
		
		//While there is still data unread in the file
		while ((buffer = textReader.readLine()) != null){
			numberOfLines += 1;
		}
		//Cleanup
		textReader.close();
		return numberOfLines;
	}
	
	/**
	 * Reads in the full contents of a file and returns 
	 * it as a string array. 
	 * @return - returns the contents of the text file.
	 * @throws FileNotFoundException 
	 */
	public String[] readInFile() throws IOException{
		int numberOfLines;
		String[] fileContents;
		
		//Opens file for reading
		FileReader fr = new FileReader(this.patientFile);
		BufferedReader textReader = new BufferedReader(fr);
		
		//Creates an array of Strings just large enough to contain 
		//all of the items in the file. 
		numberOfLines = this.getFileLength();
		fileContents = new String[numberOfLines];
		
		//Iterates through the file and reads the element to the array
		for (int i = 0; i < numberOfLines; i++){
			fileContents[i] = textReader.readLine();
		}
		
		textReader.close();//Closes file
		return fileContents;
	}
	
	/**
	 * Fills the patient class with the information saved 
	 * previously in this particular patients text file.
	 * @return - Returns the filled patient class.
	 * @throws IOException 
	 */
	public void loadPatient(File patientFile) throws IOException{
		String[] fileContents;
		
		this.patientFile = patientFile;
		
		//Read in File Contents and assign parsed contents to this patient
		fileContents = this.readInFile();
		this.assignData(fileContents);
		
		//Read in and fills vitals
		this.BP.load(this.healthCardNum);
		this.temp.load(this.healthCardNum);
		this.heartRate.load(this.healthCardNum);
	}
	
	/**
	 * Returns a file object pointing at this patients file.
	 * @return
	 */
	public File getFile(){
		File file = new File(this.filePath);
		return file;
	}
	
	/**
	 * Gets the time the patient was attended to
	 * @return
	 */
	public boolean getAttendedTo(){
		return attendedTo;
	}
	
	/**
	 * Gets patients healthcare number
	 * @return
	 */
	public long getHealthCardNum() {
		return healthCardNum;
	}
	
	/**
	 * Generates the patients urgency.
	 * @return
	 */
	public int getUrgency() {
		return urgency;
	}
	
	/**
	 * Gets the time the patient entered the triage list
	 * @return
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}
	
	/*
	 * added a simple toString we need to discuss the order in which we should print this so it 
	 * can be easily read when we are trying to reLoad the saved data.
	 */
	public String toString() {
		return String.format(first+"%n"+last+"%n"+DOB+"%n"+healthCardNum+
				"%n"+attendedTo+"%n"+arrivalTime+"%n");
	}
	
	/***
	 * prints the short hand form of patient that will be used in ViewTriageActivity
	 * @return a String with select attributes printed
	 */
	public String printPatientInfo(){
		return first+" "+last+" "+healthCardNum+ " "+arrivalTime;
	}
	
	/***
	 * generates urgency for the patient right after the patient being created
	 */
	public void generateUrgency() {
		if((DOB.getYear() - 2013) < 2) {
			urgency += 1;
		}
		if(temp.getCurrentValue() >= 39) {
			urgency += 1;
		}
		if((BP.getCurrentSystallic() >= 140) || 
				(BP.getCurrentDiastolic() >= 90)) {
			urgency += 1;
		}
		if((heartRate.getCurrentValue() >= 100) ||
				(heartRate.getCurrentValue() <= 50)) {
			urgency += 1;
		}
	}

	public void setDOB(Date DOB) {
		this.DOB = DOB;
	}
	
	public Date getDOB(){
		return DOB;
	}

	public int getHeartrate() {
		return heartRate.getCurrentValue();
	}
	
	public int getPreviousHeartRate(int n) {
		return heartRate.getPreviousValue(n);
	}

	public void updateHeartrate(int hr) {
		heartRate.Update(hr);
	}

	public int getTemp() {
		return temp.getCurrentValue();
	}
	
	public int getPreviousTemp(int n) {
		return temp.getPreviousValue(n);
	}

	public void setTemp(int tp) {
		temp.Update(tp);
	}

	public int getPreviousBPSy(int entryNumber) {
		return this.BP.getPreviousSystallic(entryNumber);
	}
	
	public int getBPDi() {
		return this.BP.getCurrentDiastolic();
	}
	
	public int getPreviousBPDi(int n) {
		return this.BP.getPreviousDiastolic(n);
	}

	public void setBP(int di, int sy) {
		((BloodPressure) this.BP).UpdateSystallic(sy);
		((BloodPressure) this.BP).UpdateDiastolic(di);
		
	}
}