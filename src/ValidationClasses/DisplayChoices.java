package ValidationClasses;

import Classes.Customer;
import Classes.Intervention;
import Classes.Material;
import Classes.Technician;

import java.util.Scanner;

public class DisplayChoices {
    public static final String externalFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\External.txt";
    public static final String materialFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Material.txt";
    public static final String customerFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Customer.txt";
    public static final String technicianFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Technician.txt";
    public static final String interventionFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Intervention.txt";

    public static void displayInsertionsSection() {
        Scanner scanner = new Scanner(System.in);
        int insertionschoice;
        do {
            System.out.println("=============== Insertions Menu ===============");
            System.out.println("1-Insert A Client And A Material And Intervention.");
            System.out.println("2-Insert A Technician.");
            System.out.println("3-Exit Insertions Menu. ");
            System.out.println("============= End Insertions Menu =============");
            System.out.println("Enter your Choice !");
            insertionschoice = scanner.nextInt();
            if (insertionschoice < 1 || insertionschoice > 3) {
                System.out.println("Pleas, Enter A Valid Choice!");
            } else {
                switch (insertionschoice) {
                    case 1:
                        System.out.println("== Insert A Client ==");
                        Customer.AddCustomer(customerFile);
                        System.out.println("== Insert A Matrial ==");
                        Material.AddMatrial(materialFile);
                        System.out.println("== Insert A Intervention ==");
                        Intervention.AddIntervention(interventionFile);
                        break;
                    case 2:
                        Technician.AddTechnician(technicianFile);
                        break;
                }
            }
        } while (insertionschoice != 3);
    }

    public static void displayEditingSection() {
        Scanner scanner = new Scanner(System.in);
        int editChoice;
        do {
            System.out.println("=============== Editing Menu ===============");
            System.out.println("1-Edit A Client.");
            System.out.println("2-Edit A Intervention.");
            System.out.println("3-Exit Editing Menu.");
            System.out.println("=============== End Editing Menu ===============");
            editChoice = scanner.nextInt();
            if (editChoice < 1 || editChoice > 3) {
                System.out.println("Pleas, Enter A Valid Choice!");
            } else {
                switch (editChoice) {
                    case 1:
                        int clientEditChoice;
                        do {
                            System.out.println("=============== Editing Client Menu ===============");
                            System.out.println("1-Edit Phone Number.");
                            System.out.println("2-Edit Email.");
                            System.out.println("3-Exist Editing Client Menu.");
                            System.out.println("============= End Editing Client Menu =============");
                            System.out.println("Enter Your Choice");
                            clientEditChoice = scanner.nextInt();
                            if (clientEditChoice < 1 || clientEditChoice > 3) {
                                System.out.println("Pleas, Enter A Valid Choice !");
                            } else {
                                switch (clientEditChoice) {
                                    case 1:
                                        Customer.changeInformation(customerFile,"Phone");
                                        validation.renameFile(externalFile,customerFile);
                                        break;
                                    case 2:
                                        Customer.changeInformation(customerFile,"Email");
                                        validation.renameFile(externalFile,customerFile);
                                        break;
                                }
                            }
                        } while (clientEditChoice != 3);
                        break;
                    case 2:
                        int interventionEditChoice;
                        do {
                            System.out.println("=============== Editing Intervention Menu ===============");
                            System.out.println("1-Edit Repair Date .");
                            System.out.println("2-Edit Recovery Date.");
                            System.out.println("3-Exist Editing Client Menu.");
                            System.out.println("============= End Editing Intervention Menu =============");
                            System.out.println("Enter Your Choice");
                            interventionEditChoice = scanner.nextInt();
                            if (interventionEditChoice < 1 || interventionEditChoice > 3) {
                                System.out.println("Pleas, Enter A Valid Choice !");
                            } else {
                                switch (interventionEditChoice) {
                                    case 1:
                                        Intervention.changeInformation(interventionFile,"Repair Date");
                                        validation.renameFile(externalFile,interventionFile);
                                        break;
                                    case 2:
                                        Intervention.changeInformation(interventionFile,"Recovery Date");
                                        validation.renameFile(externalFile,interventionFile);
                                        break;
                                }
                            }
                        } while (interventionEditChoice != 3);
                        break;
                }
            }
        } while (editChoice != 3);
    }

    public static void displayDeletingSection() {
        Scanner scanner = new Scanner(System.in);
        int deleteChoice;
        do {
            System.out.println("=============== Deleting Menu ===============");
            System.out.println("1-Delete A Intervention.");
            System.out.println("2-Exit Deleting Menu.");
            System.out.println("=============== End Deleting Menu ===============");
            deleteChoice = scanner.nextInt();
            if (deleteChoice < 1 || deleteChoice > 2) {
                System.out.println("Pleas, Enter A Valid Choice.");
            } else {
                if (deleteChoice == 1) {
                    Intervention.delete(interventionFile);
                    validation.renameFile(externalFile,interventionFile);
                }
            }
        } while (deleteChoice != 2);
    }
    public static void displayStatisticsSection() {
        Scanner scanner=new Scanner(System.in);
        int statisticChoice;
        do {
            System.out.println("============ Statistics Menu =================");
            System.out.println("1-Display The Number Of Deposit Of A Client. ");
            System.out.println("2-Display The Number Ot Intervention Affect By Module.");
            System.out.println("3-Display The Number Ot Intervention Of A Technician By Date.");
            System.out.println("4-Exit Statistics Menu.");
            System.out.println("============ End Statistics Menu =================");
            System.out.println("Enter Your Choice :");
            statisticChoice= scanner.nextInt();
            if(statisticChoice <1 || statisticChoice>4){
                System.out.println("Pleas, Enter A Valid Choice.");
            }else {
                switch (statisticChoice){
                    case 1:
                        int nbr1=Material.calcNbrDeposit(materialFile,interventionFile);
                        if(nbr1 != -1){
                            System.out.println("This Client Deposit "+nbr1+" Material.");
                        }else {
                            System.out.println("This Client Not Available !");
                        }
                        break;
                    case 2:
                        System.out.println("This Module Has "+Intervention.calcNbrByModule(interventionFile)+" Intervention.");
                        break;
                    case 3:
                        int nbr3=Technician.calcNbrInterventionByDate(interventionFile);
                        if(nbr3 != -1){
                            System.out.println("This Technician Has "+nbr3+" Intervention.");
                        }
                        break;
                }
            }
        }while ( statisticChoice != 4);
    }

}
