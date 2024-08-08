// -------------------------------------------------------
// Assignment 4
// Written by:  Affan Thameem (40282375)
// For COMP 248 Section W  – Winter 2024
// -------------------------------------------------------
/**
* Driver class is for the Simple Mobile Dental Clinic Management System (SMDCMS).
* This class serves as the entry point for the application and arranges the functionality
* provided by the Clinic and Individual classes.
* The driver class implements menu based features to allow users (clinic personal assistants, dentists,
* and management) to interact with the SMDCMS and perform various tasks such as defining office/clinic,
* adding dentists and dental assistants, registering patients, managing charges, and displaying statistics.
* The Driver class is responsible for initializing the necessary components, handling user inputs,
* and invoking appropriate methods from the Clinic and Individual classes based on the selected menu options.
*/

import java.util.Scanner;

public class DentalClinicDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Clinic clinic = new Clinic();
        Individual Dentist;
        Individual[] DentistAsst;
        Individual[] Patients;
        String code;
        String userInput;
        String sb;

        // Welcome Banner
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+ Welcome to Simple Mobile Dental Clinic Management System (SDCMS) +");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

        System.out.println("Code -> Description");
        System.out.println("++++++++++++++++++++");
        System.out.println(" 1001 -> Define Office/Clinic");
        System.out.println(" 1002 -> Add Dentist to Office/Clinic");
        System.out.println(" 1003 -> Add Dental Assistants (Hygienists/Clerks) to Office/Clinic");
        System.out.println(" 1004 -> Register Patient(s) to Office/Clinic");
        System.out.println(" 1005 -> Deregister Dental Assistant(s) and/or Patient(s)");
        System.out.println(" 1006 -> Enter/Update Patient(s) Charges");
        System.out.println(" 1007 -> Display Patients' Statistics");
        System.out.println(" 1008 -> Display Office/Clinic Statistics");
        System.out.println(" 1009 -> Display Chargesheet");
        System.out.println(" 1010 -> Exit");

        // Prompt the user to enter a code corresponding to the task
        System.out.print("\nPlease enter a code, from the aforementioned, that corresponds to your task: ");

        do {
            code = sc.next();

            switch (code) {

                case "1001": // Define Office/Clinic
                    String clinicName, clinicCode, clinicTerm;

                    System.out.println("\n\n...Define Office/Clinic...");
                    System.out.println(".........................");

                    System.out.println("Enter Office/Clinic information (ClinicName ClinicCode Term/Patient) as single line entry:");
                    clinicName = sc.next();
                    clinicCode = sc.next();
                    clinicTerm = sc.next();

                    clinic.setClinicName(Individual.upperCase(clinicName));
                    clinic.setClinicCode(Individual.upperCase(clinicCode));
                    clinic.setClinicTerm(Individual.multiUpperCamelCase(clinicTerm));

                    break;

                case "1002": // Add Dentist for Office/Clinic
                    String firstName, lastName;
                    String employeeID;

                    System.out.println("\n\n...Add Dentist for Office/Clinic...");
                    System.out.println("......................................");

                    System.out.println("Enter dentist's information (EmployeeID FirstName LastName) as a single-line entry: ");
                    System.out.println();
                    employeeID = sc.next();
                    firstName = sc.next();
                    lastName = sc.next();

                    Dentist = new Individual("(" + employeeID + ")", firstName, Individual.upperCase(lastName));

                    clinic.setDenstist(Dentist);
                    break;

                case "1003": // Add Dental Assistants (Hygienists/Clerks) to Office/Clinic
                    System.out.println("\n\n...Add Dental Assistants (Hygienists/Clerks) to Office/Clinic...");
                    System.out.println(".........................................................");
                    System.out.println("Enter Dental Assistant(s) information (ID1,FirstName1,LastName1;ID2,FirstName2,LastName2): ");
                    userInput = sc.next();
                    DentistAsst = Individual.inStrToIndividualArr(userInput);
                    sb = clinic.appendToIndividualArr(DentistAsst, Integer.parseInt(code));
                    System.out.print(sb);

                    break;

                case "1004":
                    System.out.println("\n\n...Register Patients(s) at Office/Clinic...");
                    System.out.println(".........................................................");
                    System.out.println("Enter Patients information (ID1,FirstName1,LastName1;ID2,FirstName2,LastName2): ");
                    userInput = sc.next();
                    Patients = DentistAsst = Individual.inStrToIndividualArr(userInput);
                    sb = clinic.appendToIndividualArr(Patients, Integer.parseInt(code));
                    System.out.print(sb);
                    break;

                case "1005":
                    System.out.println("\n\n...Deregister Dental Assistant(s) and/or Patient(s)\"...");
                    System.out.println(".........................................................");
                    System.out.println("Enter '1003' to deregister Dental Assistant(s) and '1004' to deregister Patients(s): ");
                    userInput = sc.next();

                    if (userInput.equals("1003")) {
                        System.out.println("Enter information of entities (ID1;ID2); ");
                        String entitiesToDelete = sc.next();
                        sb = clinic.deleteFromIndividualArr(entitiesToDelete, Integer.parseInt(userInput));
                        System.out.print(sb);
                    } else {
                        System.out.println("Enter information of entities (ID1;ID2); ");
                        String entitiesToDelete = sc.next();
                        sb = clinic.deleteFromIndividualArr(entitiesToDelete, Integer.parseInt(userInput));
                        System.out.print(sb);
                    }
                    break;

                case "1006":
                    System.out.println("\n\n...Enter/Update Patient(s) Charges...");
                    System.out.println(".........................................................");
                    System.out.println("Enter Patients' Charges (ID1,Charge1:ID2;Charge2): ");
                    String chargeInput = sc.next();

                    sb = clinic.updateIndividualCharge(chargeInput, Integer.parseInt(code));
                    System.out.print(sb);

                    break;

                case "1007":
                    clinic.patientStats();
                    break;

                case "1008":
                    clinic.clinicStats();
                    break;

                case "1009":
                    clinic.chargeSheet();
                    break;

                case "1010":
                    System.out.println("Simple SMDCMS >>>> Exiting..\n");
                    break;
            }

            if (code.equals("1001") || code.equals("1002")) {
                System.out.print("Successful operation! Kindly continue by entering a Code, from the menu above, that corresponds to your task: ");
            } else if (!code.equals("1010")) {
                System.out.print("Kindly continue by entering a Code, from the menu above, that corresponds to your task: ");
            }
        } while (!code.equals("1010"));

        System.out.println("\nThank you for fostering our Simple Mobile Dental Clinic Management System (SMDCMS).");
        // Close the Scanner
        sc.close();
    }
}
