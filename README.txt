This is an Android mobile application used by nurses and doctors to priortize between patients order in a traige given their symptoms as well as to track recurring patients. The application allows two kinds of users Nurses and Doctors of which each have separate functionality such as update medication.
 
 - Login & Register:

	Once the user launchs the app, it shows the Login screen that contains two blank text fields. These fields take in the user (in this implementation the nurse's) username and password. If the user doesn’t have username, just click on the register button. Then it will pop up a window that you can choose to register as nurse or physician. If you choose nurse, after login you will be ushered to Triage view. If you choose physician, you will be ushered to Search patient view. 

-Log out
Your can click on the log out button(door button) at the left-up corner to log out. You will go back to login view. 

- Check the list of patient and patient profile:

	Triage view displays the entire list of patients. Clicking on one of these patients will take the user to the patient's file.  

-Check the history of patient
In patient’s file, you can see a checkbox beside testview Attended To, you can check it to mean this patient attented to hospital. And there are four buttons beside patient’s symptoms. You can click on it to see the history of patient symptoms.

- Add a new patient and save it to the file:

	From Triage view a new patient can be added by clicking on the add patient button(plus button). From there just fill in the text fields and click the add patient button and the new patient is now in the system. If you want to exit this page, just click on the button(left arrow) at the left-up corner so that you can go back to Triage view.

-Search a patient:
    You can search patient by clicking on the search patient button(zoom button). You will be lead to a new screen. Just fill the healthcard number in the text field and click the Search button and you will be ushered to that patient’s profile.

- Update patient symptoms:

	Select desired patient from triage view. Clicking on this brings you to an update view for that patient. Add new values and click update button beside them and the program will update symptoms for that patient. If you want to leave this page without updating patient’s symptoms, just click on the left arrow button to go back to last page.

-Update medication:
If you login as physician, you will directly get to search patient view. Then search the patient by healthcard number then get to the patient’s profile. Then you will find a button named UpdateMedication at the bottom of screen. You can click on it and fill in patient’s name and instructions for this patient and click on Add button so that medication for this patient is added or click on left arrow button to leave this page without adding medication.

The project lives in PIII/TriageApplication
