package objectClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.io.PrintWriter;
import java.io.BufferedReader;

public class ER {
	//The directory of where the patient files are held
	private String nursePath; 
	private final static String DIRECTORY = "/Patients/";
	private Map<String, String> nurseMap; 
	
	/**
	 * must be private so that Nurse can only access it through specified methods
	 * has a Comparator method to specify what how the priority should be handled  
	 */
	private static PriorityQueue<Patient> triageUrgency = new PriorityQueue<Patient>(10, new Comparator<Patient>(){
		@Override
		public int compare(Patient o1, Patient o2) {
			if(o1.getUrgency() > o2.getUrgency()){
				return 1;
			}
			else if(o1.getUrgency() == o2.getUrgency()){
				return 0;
			}else{
				return -1;
			}
		}
	});
	
	private static PriorityQueue<Patient> triageArrivalTime = new PriorityQueue<Patient>(10, new Comparator<Patient>(){

		@Override
		public int compare(Patient o1, Patient o2) {
			if(o1.getArrivalTime().compareTo(o2.getArrivalTime()) == -1){
				return -1;
			}else if(o1.getArrivalTime().compareTo(o2.getArrivalTime()) == 0){
				return 0;
			}else{
				return 1;
			}
		}
	});
	
	/***
	 * Returns the Queue with all the patients organized by arrival time 
	 * @return triageArrivalTime
	 */
	public PriorityQueue<Patient> getPatientsByArrival(){
		return triageArrivalTime;
	}
	
	/***
	 * Returns the PriorityQueue with all the patients organized by Urgency
	 * @return triageUrgency
	 */
	public PriorityQueue<Patient> getPatientsByUrgency(){
		return triageUrgency;
	}
	
	/***
	 * Returns the next patient (i.e. the first one in the queue) in the 
	 * triage organized by urgency
	 * @return next Patient in line 
	 */
	public Patient viewNextPatientByUrgency(){
		return triageUrgency.peek();
	}
	
	/***
	 * returns and removes the next patient in line to be attended by urgency 
	 * and the finds and removes that patient from triageArrivalTime as well.
	 * @return next Patient in line 
	 */
	public Patient callOnNextPatient(){
		Patient next = triageUrgency.poll();
		triageArrivalTime.remove(next);
		return next;
	}
	
	/***
	 * Adds a patient to both the triageUrgency and triageArrivalTime given 
	 * by Patient's urgency and arrival time 
	 * variables respectively.
	 * @param p is a given Patient
	 */
	public void addPatientToTriage(Patient p){
		triageUrgency.add(p);
		triageArrivalTime.add(p);
		
	}   
	
	/***
	 * Saves Patient's information onto a file given the Patients' profile
	 * @param p the Patients' profile 
	 * @throws FileNotFoundException 
	 */
	public void savePatient(Patient p){
		 try {
			 FileWriter writer = new FileWriter(p.getFile(), true); 
			 
			 // if file doesn't exists, then create it
			 if (!p.getFile().exists()) {
				 p.getFile().createNewFile();
			 }
             // write person info as dictated by toString method.
             writer.write(p.toString());
             writer.flush();
             writer.close();
             
     } catch (IOException e) {
         e.printStackTrace();
     }
	}
	
	/*** -------------------dellllllllleeeeeeeeeete ------------------
	 * Saves Patient's information onto a file given the Patients health card 
	 * @param healthCard the health card of the Patient
	 */
	public void savePatient(int healthCard){
		for(Patient p: triageUrgency){
			if(p.getHealthCardNum() == healthCard){
				savePatient(p);
			}
		}
	}
	
	/***
	 * saves every patient in the triage then deletes the two priority queues
	 * triageUrgency and triageArrivalTime
	 * Alternate solution: save the index of where each patient is in both lists and 
	 * rebuild them in linear time
	 */
	public void saveState(){
		for(Patient p:triageUrgency){
			savePatient(p);
		}
		triageUrgency = null;
		triageArrivalTime = null;
	}
	
	/***
	 * Finds the directory where the patient files are held. 
	 * iterates through each of them gives it to loadPatient
	 * then adds them to the two triage PriorityQueues.
	 */
	public static void loadState(){
		File[] dir = getDirectory();
		for(File f: dir){
			Patient p = new Patient();
			try {
				p.loadPatient(f);
			} catch (IOException e) {
				//Do something I haven't figured it out yet
			}
			triageUrgency.add(p);
			triageArrivalTime.add(p);
		}
	}

	/***
	 * returns an array of all the files in the directory
	 * @return an array of files
	 */
	private static File[] getDirectory() {
		return new File(DIRECTORY).listFiles();
	}

	public static CharSequence printTriage() {
		String allPatients = null;
		for(Patient p: triageUrgency){
			allPatients += p.printPatientInfo();
		}
		return allPatients;
	}    
     
     public static void addRegisteredNurse(String username, String password){
		nurseMap1.put(username, password);
        String nurseUserName = username;
        String nursePassWord = password;
        FileWriter nurseInfo = new FileWriter(nursePath, True);
        PrintWriter writeToFile = new PrintWriter(nurseInfo);
        writeToFile.printf("%s" + "%n", nurseUserName);
        writeToFile.printf("%s" + "%n", nursePassWord);
        writeToFile.close();
        
     }  
     
     public void loadNurseInfo(){
    	 FileReader loadNurse = new FileReader(nursePath);
    	 BufferedReader nurseInfoLoad = new BufferedReader(loadNurse);
    	 String userNameNurse;
    	 String passWordNurse;
    	 for ((userNameNurse = nurseInfoLoad.readline()) != null ){
    		 passWordNurse = nurseInfoLoad.readline(); 
    		 nurseMap1.put(userNameNurse, passWordNurse);
    	 }
     }
}     
        
	public static CharSequence printTriage() {
		String allPatients = null;
		for(Patient p: triageUrgency){
			allPatients += p.printPatientInfo();
		}
		return allPatients;
	}    
   
     
}
        