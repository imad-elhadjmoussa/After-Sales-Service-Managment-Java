package Classes;

import ValidationClasses.Date;
import ValidationClasses.validation;

import java.io.*;


public class Customer {
    private String number;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private  String email;

    //Length of Attributes
    private final static int numberLength=5;
    private final static int firstNameLength=20;
    private final static int lastNameLength=20;
    private final static int phoneNumbreLength=10;
    private static final int emailLength=20;
    private static final String externalFile="C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\External.txt";

    //Constructer of class Material
    public Customer(){
        //Reading number
        this.number= validation.validInputNumber(numberLength,"Customer","Number");

        //Reading first name
        this.firstName=validation.validInputeName(firstNameLength, "Customer","First Name");

        //Reading Last Name
        this.lastName=validation.validInputeName(lastNameLength, "Customer","Last Name");

        //Reading phone number
        this.phoneNumber=validation.validInputNumber(phoneNumbreLength, "Customer", "Phone Number");

        //Reading Email
        this.email=validation.validEmail();
    }

    public Customer(String data){
        int beginIndex=0;
        int endIndex=numberLength;
        this.number=data.substring(beginIndex,endIndex);
        beginIndex=endIndex;
        endIndex=beginIndex+lastNameLength;
        this.lastName=data.substring(beginIndex,endIndex);
        beginIndex=endIndex;
        endIndex=beginIndex+firstNameLength;
        this.firstName=data.substring(beginIndex,endIndex);
        beginIndex=endIndex;
        endIndex=beginIndex+phoneNumbreLength;
        this.phoneNumber=data.substring(beginIndex,endIndex);
        beginIndex=endIndex;
        endIndex=beginIndex+emailLength;
        this.email=data.substring(beginIndex,endIndex);
    }

    //Join function to join all data in one string
    public String join(){
        return this.number+this.lastName+this.firstName+this.phoneNumber+this.email;
    }

    //Function to add customer to file text
    public static boolean AddCustomer(String fileName){
        Customer customer=new Customer();
        if(searchNumber(fileName, customer.getNumber(),customer.getPhoneNumber()) == 0){
            while (searchNumber(fileName, customer.getNumber(),customer.getPhoneNumber()) == 0){
                System.out.println("This Number of  Customer Exist, Please Enter Another Customer Information !");
                customer = new Customer();
            }
        }
        if(searchNumber(fileName, customer.getNumber(),customer.getPhoneNumber()) == 1){
            System.out.println("This Customer Exist !");
            return false;
        } else {
            try {
                BufferedWriter writer=new BufferedWriter(new FileWriter(new File(fileName), true));
                writer.write(customer.join());
                writer.newLine();
                writer.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
            return true;
        }

    }

    //Function to get numre
    public String getNumber(){
        return this.number;
    }
    public String getPhoneNumber(){
        return this.phoneNumber ;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    //Function to search for number
    public static int searchNumber(String fileName, String number, String phoneNumber) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String data = reader.readLine();
            while (data != null) {
                Customer customer = new Customer(data);
                if (customer.getNumber().equals(number) && customer.getPhoneNumber().equals(phoneNumber)) {
                    reader.close();
                    return 1;
                }else if(customer.getNumber().equals(number)&& !customer.getPhoneNumber().equals(phoneNumber)){
                    reader.close();
                    return 0;
                }
                data = reader.readLine();
            }
            reader.close();
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean searchNumber(String fileName, String number) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String data = reader.readLine();
            while (data != null) {
                Customer customer = new Customer(data);
                if (customer.getNumber().equals(number)) {
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

    //Function to updat the file
    public static void updateFile(String fileName, String Number,String info, String type) {
        try {
            File oldInterventionFile = new File(fileName);
            File newInterventionFile = new File(externalFile);
            newInterventionFile.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(oldInterventionFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(newInterventionFile, true));
            String data = reader.readLine();
            while (data != null) {
                Customer customer = new Customer(data);
                if (customer.getNumber().equals(Number)) {
                    if(type.equals("Email")){
                        customer.setEmail(info);
                        writer.write(customer.join());
                        writer.newLine();
                    }else {
                        customer.setPhoneNumber(info);
                        writer.write(customer.join());
                        writer.newLine();
                    }
                }else {
                    writer.write(customer.join());
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

    //Function to change information
    public static boolean changeInformation(String fileName,String type) {
        String number = validation.validInput(numberLength, "Customer", "Number");
        String phoneNumber=validation.validInputNumber(phoneNumbreLength, "Customer", "Phone Number");
        if (searchNumber(fileName, number,phoneNumber) != 1) {
            System.out.println("This Customer not Available !");
            return false;
        } else {
            String info;
            if(type.equals("Email")){
                info = validation.validEmail();
            }else {
                info=validation.validInput(phoneNumbreLength,"Customer","Phone Number");
            }
            updateFile(fileName, number,info,type);
            return true;
        }
    }

}
