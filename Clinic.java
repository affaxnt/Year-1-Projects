// -------------------------------------------------------
// Assignment 4
// Written by:  Affan Thameem (40282375)
// For COMP 248 Section W  – Winter 2024
// -------------------------------------------------------
/**
 * Represents a clinic entity with attributes such as clinic name, clinic code, clinic term, dentist, dental assistants,
 * registered patients, and methods for managing clinic statistics and charge sheet.
 * Provides functionality to retrieve, update, and display clinic information.
 */
public class Clinic {
    private String clinicName;
    private String clinicCode;
    private String clinicTerm;
    private Individual dentist;
    private Individual[] dentalAsst;
    private Individual[] patient;

    // Constructor
    public Clinic() {
    	this.clinicName = null;
        this.clinicCode = null;
        this.clinicTerm = null;
        this.dentist = null;
        this.dentalAsst = null;
        this.patient = null;
    }

    public Clinic(String clinicName, String clinicCode, String clinicTerm, Individual dentist, Individual[] dentalAsst, Individual[] patient) {
        // Custom constructor initializes variables with arguments
        this.clinicName = clinicName;
        this.clinicCode = clinicCode;
        this.clinicTerm = clinicTerm;
        this.dentist = dentist;
        this.dentalAsst = dentalAsst;
        this.patient = patient;
    }

    // Accessor methods
    public String getClinicName() {
		
        return clinicName;
    }

    public String getClinicCode() {
        return clinicCode;
    }

    public String getClinicTerm() {
        return clinicTerm;
    }

    public Individual getDentist() {
        return dentist;
    }

    public Individual[] getDentalAsst() {
        return dentalAsst;
    }

    public Individual[] getPatient() {
        return patient;
    }

    // Mutator methods
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public void setClinicCode(String clinicCode) {
        this.clinicCode = clinicCode;
    }

    public void setClinicTerm(String clinicTerm) {
        this.clinicTerm = clinicTerm;
    }
    
    public void setDenstist(Individual dentist) {
    	 this.dentist = dentist;
    }

    public void setDentalAsst(Individual[] inClinicAsst) {
        this.dentalAsst = inClinicAsst;
    }

    public void setPatient(Individual[] inIndiviual) {
        this.patient = inIndiviual;
    }
    @Override
    public String toString() {
    	return "Clinic Name: " + clinicName + "\n" +
    	           "Clinic Code: " + clinicCode + "\n" +
    	           "Clinic Term: " + clinicTerm + "\n" +
    	           "Dentist: " + dentist + "\n" +
    	           "Number of Dental Assistants: " + dentalAsst.length + "\n" +
    	           "Number of Patients: " + patient.length;
    }
    
    /*
     * Function to append an entity into an Individual array 
     * based on the menu code
     * @param newArr 
     * @param code
     * @return String
     */
    public String appendToIndividualArr(Individual[] newArr, int code) {
        StringBuilder sb = new StringBuilder();
        switch (code) {
        case 1003: 
            /* Check if the dentalAsst array is empty, if not empty then append to 
             * to the dentalAsst array skipping individuals already exists in the
             * array else add all the individuals to the dentalAsst array.
            */
            if (dentalAsst != null && dentalAsst.length > 0) {
                String entitiesToAppend = "";
                for (Individual i : newArr) {
                    if (isEntityFound(i.getEntityID(), "DentalAssitant")) {
                        sb.append("Already Exists: " + i.getLastName()+", "+ i.getFirstName() +" ("+i.getEntityID()+").\n");    
                    } else {
                        entitiesToAppend += i.getEntityID() + ";";	    		            	
                    }
                }
                // Append to DentalAsst 
                if (!("".equals(entitiesToAppend))) {
                    String[] newEntities = entitiesToAppend.split(";");
                    Individual[] tempArray = new Individual[dentalAsst.length + newEntities.length];
                    
                    // Copy the existing dentalAsst array to tempArray
                    int tempArrayIndex = 0;
                    for (int i = 0; i < dentalAsst.length; i++) {
                        tempArray[tempArrayIndex] = dentalAsst[i];
                        tempArrayIndex++;
                    }  
                    // Append the new individuals to temp Array
                    for (int j = 0; j < newEntities.length; j++) {
                        for (int k = 0; k < newArr.length; k++) {
                            if (newEntities[j].equals(newArr[k].getEntityID())) { 							 
                                tempArray[tempArrayIndex] = newArr[k]; 
                                tempArrayIndex++;
                                sb.append("Successfully Added: "+ newArr[k].getLastName()+", "+ newArr[k].getFirstName() +" ("+newArr[k].getEntityID()+").\n");
                                break;
                            }    							 
                        }
                    }
                    setDentalAsst(null);
                    setDentalAsst(tempArray);
                }
            } else {
                Individual[] tempArray;
                for (int i = 0; i < newArr.length; i++) {
                    if (i==0) {
                        tempArray = new Individual[1];
                        tempArray[0] = newArr[i];
                        sb.append("Successfully Added: "+ newArr[i].getLastName()+", "+ newArr[i].getFirstName() +" ("+newArr[i].getEntityID()+").\n");
                        setDentalAsst(tempArray);
                    } else {
                        if (isEntityFound(newArr[i].getEntityID(), "DentalAssitant")) {
                            sb.append("Already Exists: " + newArr[i].getLastName()+", "+ newArr[i].getFirstName() +" ("+newArr[i].getEntityID()+").\n");
                            continue;
                        } else {
                            tempArray = new Individual[getDentalAsst().length + 1];
                            int index = 0;
                            
                            for (Individual inv : getDentalAsst()) {
                                tempArray[index] = inv;
                                index++;
                            }
                            
                            tempArray[index] = newArr[i];
                            setDentalAsst(tempArray);
                            sb.append("Successfully Added: "+ newArr[i].getLastName()+", "+ newArr[i].getFirstName() +" ("+newArr[i].getEntityID()+").\n");
                        }
                    }
                }		
            }
            break;

        case 1004:
        	/* Check if the patient array is empty, if not empty then append to 
             * to the dentalAsst array skipping individuals already exists in the
             * array else add all the individuals to the dentalAsst array.
            */
        	if (patient != null && patient.length > 0) {
                String entitiesToAppend = "";
                for (Individual i : newArr) {
                    if (isEntityFound(i.getEntityID(), "Patient")) {
                        sb.append("Already Exists: " + i.getLastName()+", "+ i.getFirstName() +" ("+i.getEntityID()+").\n");    
                    } else {
                        entitiesToAppend += i.getEntityID() + ";";	    		            	
                    }
                }
                // Append to DentalAsst 
                if (!("".equals(entitiesToAppend))) {
                    String[] newEntities = entitiesToAppend.split(";");
                    Individual[] tempArray = new Individual[patient.length + newEntities.length];
                    
                    // Copy the existing dentalAsst array to tempArray
                    int tempArrayIndex = 0;
                    for (int i = 0; i < patient.length; i++) {
                        tempArray[tempArrayIndex] = patient[i];
                        tempArrayIndex++;
                    }  
                    // Append the new individuals to temp Array
                    for (int j = 0; j < newEntities.length; j++) {
                        for (int k = 0; k < newArr.length; k++) {
                            if (newEntities[j].equals(newArr[k].getEntityID())) { 							 
                                tempArray[tempArrayIndex] = newArr[k]; 
                                tempArrayIndex++;
                                sb.append("Successfully Added: "+ newArr[k].getLastName()+", "+ newArr[k].getFirstName() +" ("+newArr[k].getEntityID()+").\n");
                                break;
                            }    							 
                        }
                    }
                    setPatient(null);
                    setPatient(tempArray);
                }
            } else {
                Individual[] tempArray;
                for (int i = 0; i < newArr.length; i++) {
                    if (i==0) {
                        tempArray = new Individual[1];
                        tempArray[0] = newArr[i];
                        sb.append("Successfully Added: "+ newArr[i].getLastName()+", "+ newArr[i].getFirstName() +" ("+newArr[i].getEntityID()+").\n");
                        setPatient(tempArray);
                    } else {
                        if (isEntityFound(newArr[i].getEntityID(), "Patient")) {
                            sb.append("Already Exists: " + newArr[i].getLastName()+", "+ newArr[i].getFirstName() +" ("+newArr[i].getEntityID()+").\n");
                            continue;
                        } else {
                            tempArray = new Individual[getPatient().length + 1];
                            int index = 0;
                            
                            for (Individual inv : getPatient()) {
                                tempArray[index] = inv;
                                index++;
                            }
                            
                            tempArray[index] = newArr[i];
                            setPatient(tempArray);
                            sb.append("Successfully Added: "+ newArr[i].getLastName()+", "+ newArr[i].getFirstName() +" ("+newArr[i].getEntityID()+").\n");
                        }
                    }
                }		
            }
            
        }
        return sb.toString();
    }

   
    /*
     * Function to find if the entity ID is found in Individual array 
     * based on the entity typpe such as; "DentalAssitant" or "Patient"
     * @param entityID 
     * @param entityType
     * @return boolean
     */
    private boolean isEntityFound(String entityID, String entityType) {
    	Individual [] Individual;
    	if (entityType.equals("DentalAssitant")) {
    		Individual = getDentalAsst();
    	}else {
    		Individual = getPatient();
    	}
    	for (Individual individual : Individual) {
			
    		if (individual.getEntityID().equals(entityID))
    		{
    			return true;
    		}
		}
    	return false;
    }

    /*
     * Function to delete an entity in Individual array 
     * based on the entity code
     * @param inStr 
     * @param code
     * @return String
     */
    public String deleteFromIndividualArr(String inStr, int code) {
        StringBuilder sb = new StringBuilder();
        switch (code) {
            case 1003:
            	String[] listOfID = inStr.split(";");
                Individual[] dentalAsst = getDentalAsst();
                if (dentalAsst == null) {
                	for (int i = 0; i < listOfID.length; i++)
                    sb.append("You cannot delet any entity from an EMPTY array.\n");
                	return sb.toString();
                }
                String indexToRemove = "";
                int newDentalIndex = 0;
                boolean foundEntity = false; // Flag to check if any entity is found
                for (String str : listOfID) {
                    if (!isEntityFound(str, "DentalAssitant")) {
                        sb.append("Entity NOT found: " + str +"\n");
                        foundEntity = true; // Set flag if any entity is not found
                    }
                }
                for (String str : listOfID) {
                    for (int index = 0; index < dentalAsst.length; index++) {
                        if (str.equals(dentalAsst[index].getEntityID())) // Assuming getEntityID() is the method to get ID
                            indexToRemove += index + ";";
                    }
                }
                indexToRemove = indexToRemove.substring(0, indexToRemove.length() - 1);
                Individual[] newDentalAsst = new Individual[dentalAsst.length - indexToRemove.split(";").length];
                for (int index = 0; index < dentalAsst.length; index++) {
                    if (!(indexToRemove.contains(Integer.toString(index)))) {
                        newDentalAsst[newDentalIndex] = dentalAsst[index];
                        newDentalIndex++;
                    } else
                        sb.append("Successfully Deleted: " + dentalAsst[index] + "\n");
                }
                setDentalAsst(newDentalAsst);
                break;
        
	    case 1004:
	    	String[] patientIdList = inStr.split(";");
	        Individual[] patient = getPatient();
	        if (patient == null) {
	        	for (int i = 0; i < patientIdList.length; i++)
	            sb.append("You cannot delet any entity from an EMPTY array.\n");
	        	return sb.toString();
	        }
	        String removePatientIndex = "";
	        int newPatientIndex = 0;
	        boolean patientEntity = false; // Flag to check if any entity is found
	        for (String str : patientIdList) {
	            if (!isEntityFound(str, "Patients")) {
	                sb.append("Entity NOT found: " + str + "\n");
	                patientEntity = true; // Set flag if any entity is not found
	            }
	        }
	        for (String str : patientIdList) {
	            for (int index = 0; index < patient.length; index++) {
	                if (str.equals(patient[index].getEntityID())) // Assuming getEntityID() is the method to get ID
	                	removePatientIndex += index + ";";
	            }
	        }
	        removePatientIndex = removePatientIndex.substring(0, removePatientIndex.length() - 1);
	        Individual[] newPatientArray = new Individual[patient.length - removePatientIndex.split(";").length];
	        for (int index = 0; index < patient.length; index++) {
	            if (!(removePatientIndex.contains(Integer.toString(index)))) {
	            	newPatientArray[newPatientIndex] = patient[index];
	            	newPatientIndex++;
	            } else
	                sb.append("Successfully Deleted: " + patient[index] + "\n");
	        }
	        setPatient(newPatientArray);
	        break;
	}
        return sb.toString(); 
    }
    
    /*
     * Function to update an Individual charge amount 
     * based on the entity code
     * @param inStr 
     * @param mode
     * @return String
     */
    public String updateIndividualCharge(String inStr, int mode) {
        StringBuilder sb = new StringBuilder();
        String[] patientCharge = inStr.split(";");
        Individual[] patients = getPatient();
        
        if (patients == null) {
            sb.append("You cannot update charges for an EMPTY array.\n");
            return sb.toString();
        }
        
        for (String charge : patientCharge) {
            String[] chargeInfo = charge.split(",");
            String[] patientIDCharge = chargeInfo[0].split(";");
            String patientID = patientIDCharge[0].trim();
            double chargeAmount = Double.parseDouble(chargeInfo[1].trim());

            boolean entityFound = false;
            for (Individual patient : patients) {
                if (patient.getEntityID().equals(patientID)) {
                    entityFound = true;
                    patient.setChargePercent(chargeAmount);
                    sb.append("Successfully Added/Updated: Charge for " + patient.getLastName()+", "+
                    		patient.getFirstName() + " (" + patientID + ").\n");
                    break;
                }
            }
            if (!entityFound) {
                sb.append("Entity NOT found: " + patientID + ".\n");
            }
        }
        return sb.toString();
    }
    
    /*
     * Function to display patient Statistics
     */
    public void patientStats() {
    	System.out.println("\n\n...Display Patients' Statistics...");
    	System.out.println("....................................");
    		System.out.println(getClinicName() +" ("+ getClinicCode() + "): "+ getClinicTerm());
    		System.out.println("dentist: Dr. " + getDentist());
    
    	    int numDentalAssistants = getDentalAsst().length;
    	    System.out.println("Dental Assistants: " + numDentalAssistants + " Dental Assistant(s)");
    	    
    	    int numPatients = getPatient().length;
    	    System.out.println("The number of patients registered with the clinic: " + numPatients + " patients");
    	    
    	    // Assuming you have a method to get the list of registered patients
    	    Individual[] patients = getPatient();
    	    System.out.println("....................................");
    	    for (int i = 0; i < patients.length; i++) {
    	    	 System.out.println((i + 1) + ". " + patients[i].getLastName() + ", " + 
    	    			 patients[i].getFirstName() + " (" + patients[i].getEntityID() + ")");
    	    } System.out.println("....................................");
    }   
    
    /*
     * Function to display Clinic Statistics
     */
    public void clinicStats() {
    	System.out.println("\n\n...................Office/Clinic Statistics..................");
    	System.out.println("............................................................");
    	System.out.println("• Clinic Name: " + getClinicName());
    	System.out.println("• Clinic Code: " + getClinicCode());
    	System.out.println("• Term/Permit: " + getClinicTerm());
    	System.out.println("• Dentist Name: " + getDentist());
    	Individual[] dentalAssistants = getDentalAsst();
    	for (int i = 0; i < dentalAssistants.length; i++) {
    	    System.out.println("• Dental Assistant " + (i + 1) + ": " + dentalAssistants[i].getLastName() + ", " + 
    	    		dentalAssistants[i].getFirstName() + " (" + dentalAssistants[i].getEntityID() + ")");
    	}
    	System.out.println("• Registered patients: " + getPatient().length);
    	System.out.println("............................................................");
    }
    
    /*
     * Function to display Charge Sheet of a all the patients
     */
    public void chargeSheet() {
    	    System.out.println("\n\n...............Office/Clinic Chargesheet.........................");
    	    System.out.println(".................................................................");
    	    // Display chargesheet header
    	    System.out.printf("%-5s%-12s%-12s%-12s%-12s\n", "P/N.", "Patient ID", "SURNAME", "First Name", "Charge");
    	    System.out.println(".................................................................");
    	    // Display each patient's information and charge
    	    Individual[] patients = getPatient(); // Assuming this method returns an array of patients
    	    for (int i = 0; i < patients.length; i++) {
    	        Individual patient = patients[i];
    	        double charge = patient.getChargePercent(); // Use getChargePercent() to retrieve the charge
    	                
    	        System.out.printf("•%-4d%-12s%-12s%-12s%-12.2f\n", (i + 1), patient.getEntityID(), 
    	        			patient.getLastName(), patient.getFirstName(), charge);
    	    }
    	    
    	    System.out.println(".................................................................");
    }

}

 


    

    