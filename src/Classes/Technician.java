package Classes;

import ValidationClasses.Date;
import ValidationClasses.validation;

import java.io.*;

import static Classes.Intervention.dateLength;

public class Technician {
    //Attributes of class Technician
    private String matricule;
    private String lastName;
    private String firstName;

    //Length of Attributes
    private static final int materialLength = 3;
    private static final int lastNameLength = 20;
    //Constructor of class Material
    private static final int firstNameLength = 20;
    private static final String technicianFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Technician.txt";

    public Technician() {
        //Reading matricule
        String matricule = validation.validInputNumber(materialLength, "Technician", "Matricule");
        while (searchMatricule(technicianFile, matricule)) {
            System.out.println("This Matricule Exist, Pleas Enter A Valid Matricule !");
            matricule = validation.validInputNumber(materialLength, "Technician", "Matricule");
        }
        this.matricule=matricule;
        //Reading last name
        this.lastName = validation.validInputeName(lastNameLength, "Technician", "Last Name");

        //Reading first name
        this.firstName = validation.validInputeName(firstNameLength, "Technician", "First Name");
    }

    public Technician(String data) {
        int beginIndex = 0;
        int endIndex = materialLength;
        this.matricule = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + lastNameLength;
        this.lastName = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + firstNameLength;
        this.firstName = data.substring(beginIndex, endIndex);
    }

    public String getMatricule() {
        return this.matricule;
    }

    //Join function to join all data in one string
    public String join() {
        return this.matricule + this.lastName + this.firstName;
    }

    //Function to add technician to file text
    public static void AddTechnician(String fileName) {
        Technician technician = new Technician();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName), true));
            writer.write(technician.join());
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    //Function to search for matricule
    public static boolean searchMatricule(String fileName, String matricule) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String data = reader.readLine();
            while (data != null) {
                Technician technician = new Technician(data);
                if (technician.getMatricule().equals(matricule)) {
                    reader.close();
                    return true;
                }
                data = reader.readLine();
            }
            reader.close();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    //Function to calculate nbr of Intervention of Technician by date
    public static  int calcNbrInterventionByDate(String fileName){
        String matricule = validation.validInputNumber(materialLength, "Technician", "Matricule");
        if(!searchMatricule(technicianFile,matricule)){
            System.out.println("This Matricule is not available !");
            return -1;
        }else {
            Date date=new Date("Repair Date");
            String dateAsString=date.dateToString(dateLength);
            int count=0;
            try{
                BufferedReader reader=new BufferedReader(new FileReader(new File(fileName)));
                String data=reader.readLine();
                while (data != null){
                    Intervention intervention=new Intervention(data);
                    if(intervention.getRepairDate().equals(dateAsString) && intervention.getAffectTo().equals(matricule)){
                        count++;
                    }
                    data=reader.readLine();
                }
                reader.close();
            }catch (Exception e){

            }
            return count;
        }

    }

}

