// -------------------------------------------------------
// Assignment 4
// Written by:  Affan Thameem (40282375)
// For COMP 248 Section W  – Winter 2024
// -------------------------------------------------------

/**
 * Represents an individual entity with attributes such as entity ID, first name, last name, and charge percent.
 * Provides methods to manipulate and retrieve individual information.
 */
public class Individual {
    private String entityID;
    private String firstName;
    private String lastName;
    private double chargePercent;

    // Constructor
    public Individual() {
        // Default constructor initializes variables
        this.entityID = null;
        this.firstName = null;
        this.lastName = null;
        this.chargePercent = 0.0;
    }

    // Custom constructor with parameters
    public Individual(String entityID, String firstName, String lastName) {
        // Initialize variables with passed arguments
        this.entityID = entityID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Copy constructor
    public Individual(Individual individual) {
        // Create a deep copy of the individual object
        this.entityID = individual.getEntityID();
        this.firstName = individual.getFirstName();
        this.lastName = individual.getLastName();
        this.chargePercent = individual.getChargePercent();
    }

    // Accessor methods
    public String getEntityID() {
        return entityID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getChargePercent() {
        return chargePercent;
    }

    // Mutator methods
    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setChargePercent(double chargePercent) {
        this.chargePercent = chargePercent;
    }

    // Overloaded equals method
    public boolean equals(Individual otherIndividual) {
        if (otherIndividual == null) {
            return false;
        }
        return this.entityID.equals(otherIndividual.getEntityID());
    }

    // Override toString method
    @Override
    public String toString() {
        return lastName + ", " + firstName + ", " + entityID;
    }

    // Custom method: upperCamelCase
    public static String upperCamelCase(String inStr) {
        inStr = inStr.trim();
        char firstChar = inStr.charAt(0);
        char upperCase = Character.toUpperCase(firstChar); // Corrected typo
        String result = upperCase + inStr.substring(1);
        return result;
    }

    // Custom method: multiUpperCamelCase
    public static String multiUpperCamelCase(String inStr) {
        String[] words = inStr.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1)).append(" ");
        }
        String result = sb.toString().trim();
        return result;
    }

    // Custom method: upperCase
    public static String upperCase(String inStr) {
        inStr = inStr.toUpperCase().trim();
        return inStr;
    }

    // Custom method: inStrToIndividualArr
    public static Individual[] inStrToIndividualArr(String inStr) {
        String[] info = inStr.split(";");
        Individual[] individuals = new Individual[info.length];
        for (int i = 0; i < info.length; i++) {
            String[] data = info[i].split(",");
            String entityID = data[0];
            String firstName = data[1];
            String lastName = data[2];
            individuals[i] = new Individual(entityID, firstName, Individual.upperCase(lastName));
        }
        return individuals;
    }
}



