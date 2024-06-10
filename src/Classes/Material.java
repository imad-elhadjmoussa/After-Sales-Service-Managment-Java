package Classes;

import ValidationClasses.validation;
import com.sun.jdi.InconsistentDebugInfoException;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Material {
    private String seriesNumber;
    private String type;
    private String modle;
    private String capacity;
    private String number;

    //Length of Attributes
    private static final int seriesNumberLength = 6;
    private static final int typeLength = 1;
    public static final int modleLength = 9;
    private static final int capacityLength = 6;
    private static final int numberLength = 5;
    public static final String customerFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Customer.txt";


    //Constructor of class Material
    public Material() {
        //Reading series Number
        this.seriesNumber = validation.validInputNumber(seriesNumberLength, "Material", "series Number");

        //Reading type
        this.type = validation.validInput(typeLength, "Material", "Type");

        //Reading module
        this.modle = validation.validInput(modleLength, "Material", "Module");

        //Reading capacity
        this.capacity = validation.validInput(capacityLength, "Material", "Capacity");

        //Reading number of CLIENT
        String number=validation.validInputNumber(numberLength, "Customer", "Number");
        while (!Customer.searchNumber(customerFile,number)){
            System.out.println("The Number of Customer not Available, Pleas Enter A Valid Client Number !");
            number=validation.validInputNumber(numberLength, "Customer", "Number");
        }
        this.number = number;
    }

    public Material(String data) {
        int beginIndex = 0;
        int endIndex = seriesNumberLength;
        this.seriesNumber = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + numberLength;
        this.number = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + typeLength;
        this.type = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + modleLength;
        this.modle = data.substring(beginIndex, endIndex);
        beginIndex = endIndex;
        endIndex = beginIndex + capacityLength;
        this.capacity = data.substring(beginIndex, endIndex);
    }
    //Function to get number
    public String getNumber(){
        return this.number;
    }
    public String getSeriesNumber(){
        return this.seriesNumber;
    }
    public String getModle(){return  this.modle;}

    //Join function to join all data in one string
    public String join() {
        return this.seriesNumber + this.number + this.type + this.modle + this.capacity;
    }

    //Function to add material to file text
    public static void AddMatrial(String fileName) {
        Material material = new Material();
        if(searchSeriesNumber(fileName,material.seriesNumber)){
            System.out.println("This Material Exist !");
        } else {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName), true));
                writer.write(material.join());
                writer.newLine();
                writer.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    //Function to search for series number
    public static boolean searchSeriesNumber(String fileName,String seriesNumber){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String data = reader.readLine();
            while (data != null) {
                Material material = new Material(data);
                if (material.getSeriesNumber().equals(seriesNumber)) {
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

    //Function to calculate number of deposit of material
    public static int calcNbrDeposit(String materialFile,String interventionFile) {
        String numberClient = validation.validInputNumber(numberLength, "Customer", "Number");
        if(!Customer.searchNumber(customerFile,numberClient)){
            return -1;
        }
        int count = 0;
        Set<String> allseriesNumber = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(materialFile)));
            String data = reader.readLine();
            while (data != null) {
                Material material = new Material(data);
                if (numberClient.equals(material.getNumber())) {
                    allseriesNumber.add(material.getSeriesNumber());
                }
                data = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {

        }
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(interventionFile)));
            String data = reader.readLine();
            while (data != null) {
                Intervention intervention = new Intervention(data);
                if(allseriesNumber.contains(intervention.getSeriesNumber())){
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