package Classes;

import ValidationClasses.Date;
import ValidationClasses.validation;

import java.io.*;
import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Intervention {
    // Attributes of class Intervention
    private String seriesNumber;
    private String depositDate;
    private String repairDate;
    private String recoveryDate;
    private String affectTo;
    private String opinion;
    private String panne;

    //Length of Attributes
    private static final int seriesNumberLength = 6;
    private static final int affectToLength = 3;
    private static final int opinionLength = 20;
    private static final int panneLength = 20;
    public static final int dateLength = 10;
    public static final int modleLength = 9;
    private static final String externalFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\External.txt";
    public static final String technicianFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Technician.txt";
    public static final String materialFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Material.txt";


    //Constructer of class Intervention
    public Intervention() {
        //Reading series number
        String seriesNumber = validation.validInputNumber(seriesNumberLength, "Intervention", "Series Number");
        while (!Material.searchSeriesNumber(materialFile,seriesNumber)){
            System.out.println("This Series Number is Not Available, Pleas Enter A Valid Series Number ! ");
            seriesNumber = validation.validInputNumber(seriesNumberLength, "Intervention", "Series Number");
        }
        this.seriesNumber=seriesNumber;
        //Reading repaire day
        Date repairDate = new Date("Repair Date");
        this.repairDate = repairDate.dateToString(dateLength);

        //Reading deposit date
        Date depositDate = new Date("Deposit Date");
        this.depositDate = depositDate.dateToString(dateLength);

        //Reading recovey Date
        Date recoveyDate = new Date("Recovey Date");
        this.recoveryDate = recoveyDate.dateToString(dateLength);

        //Reading affect to
        String affectTo = validation.validInput(affectToLength, "Technician", "Matricule ");
        while (!Technician.searchMatricule(technicianFile, affectTo)) {
            System.out.println("This Matricule of  Technician Not Available , Pleas Enter A Valid Matricule !");
            affectTo = validation.validInput(affectToLength, "Technician", "Matricule ");
        }
        this.affectTo = affectTo;

        //Reading opinion
        this.opinion = validation.validInput(opinionLength, "Intervention", "Opinion");

        //Reading panne
        this.panne = validation.validInput(panneLength, "Intervention", "Panne");
    }

    public String getRepairDate() {
        return this.repairDate;
    }

    public String getAffectTo() {
        return this.affectTo;
    }

    public Intervention(String data) {
        int beginIndex = 0;
        int endIndex = seriesNumberLength;
        this.seriesNumber = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + 10;
        this.depositDate = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + 10;
        this.repairDate = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + 10;
        this.recoveryDate = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + affectToLength;
        this.affectTo = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + opinionLength;
        this.opinion = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + panneLength;
        this.panne = data.substring(beginIndex, endIndex);
    }

    //Join function to join all data into one string
    public String join() {
        return this.seriesNumber + this.depositDate + this.repairDate + this.recoveryDate + this.affectTo + this.opinion + this.panne;
    }

    //Function to add Intervention to file text
    public static void AddIntervention(String fileName) {
        Intervention intervention = new Intervention();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName), true));
            writer.write(intervention.join());
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    //Function to get seriesNumber
    public String getSeriesNumber() {
        return this.seriesNumber;
    }

    //Function to set recovery date
    public void setRecoveryDate(String recoveryDate) {
        this.recoveryDate = recoveryDate;
    }

    //Function to set Repair Date
    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    //Function to search for seriesNumber in file
    public static boolean searchSeriesNumber(String fileName, String seriesNumber) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String data = reader.readLine();
            while (data != null) {
                Intervention intervention = new Intervention(data);
                if (intervention.getSeriesNumber().equals(seriesNumber)) {
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

    //Function to update information file
    public static void updateFile(String fileName, String seriesNumber, Date date, String type) {
        try {
            File oldInterventionFile = new File(fileName);
            File newInterventionFile = new File(externalFile);
            newInterventionFile.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(oldInterventionFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(newInterventionFile, true));
            String data = reader.readLine();
            while (data != null) {
                Intervention intervention = new Intervention(data);
                if (intervention.getSeriesNumber().equals(seriesNumber)) {
                    if (type.equals("Recovery Date")) {
                        intervention.setRecoveryDate(date.dateToString(dateLength));
                        writer.write(intervention.join());
                        writer.newLine();
                    } else {
                        intervention.setRepairDate(date.dateToString(dateLength));
                        writer.write(intervention.join());
                        writer.newLine();
                    }
                } else {
                    writer.write(intervention.join());
                    writer.newLine();
                }
                data = reader.readLine();
            }
            reader.close();
            writer.close();
            oldInterventionFile.delete();
        } catch (Exception e) {

        }
    }

    //Function to change recovery Date
    public static boolean changeInformation(String fileName, String type) {
        String seriesNumber = validation.validInputNumber(seriesNumberLength, "Intervention", "series Number");
        if (!searchSeriesNumber(fileName, seriesNumber)) {
            System.out.println("This series Number not Available !");
            return false;
        } else {
            Date date;
            if (type.equals("Recovery Date")) {
                date = new Date("Recovery Date");
            } else {
                date = new Date("Repair Date");
            }
            updateFile(fileName, seriesNumber, date, type);
            return true;
        }
    }

    //Function to delete
    public static boolean delete(String fileName) {
        String seriesNumber = validation.validInputNumber(seriesNumberLength, "Intervention", "series Number");
        Date d = new Date("Repair Date");
        String repairDate=d.dateToString(dateLength);
        if (!searchSeriesNumber(fileName, seriesNumber)) {
            System.out.println("This series Number is not Available !");
            return false;
        } else {
            try {
                File oldInterventionFile = new File(fileName);
                File newInterventionFile = new File(externalFile);
                newInterventionFile.createNewFile();
                BufferedReader reader = new BufferedReader(new FileReader(oldInterventionFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(newInterventionFile, true));
                String data = reader.readLine();
                while (data != null) {
                    Intervention intervention = new Intervention(data);
                    if (intervention.getSeriesNumber().equals(seriesNumber) && !intervention.getRepairDate().equals(repairDate)) {
                        writer.write(intervention.join());
                        writer.newLine();
                    }
                    data = reader.readLine();
                }
                reader.close();
                writer.close();
                oldInterventionFile.delete();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    //Function to calculate the number of intervention by module
    public static int calcNbrByModule(String fileName) {
        String module = validation.validInput(modleLength, "Material", "Module");
        Set<String> allseriesNumber = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(materialFile));
            String data = reader.readLine();
            while (data != null) {
                Material material = new Material(data);
                if (module.equals(material.getModle())) {
                    allseriesNumber.add(material.getSeriesNumber());
                }
                data = reader.readLine();
            }
            reader.close();

        } catch (Exception e) {

        }
        int count = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String data = reader.readLine();
            while (data != null) {
                Intervention intervention = new Intervention(data);
                if (allseriesNumber.contains(intervention.getSeriesNumber())) {
                    count++;
                }
                data = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {

        }
        return count;
    }
}



