import Classes.Customer;
import Classes.Intervention;
import Classes.Material;
import Classes.Technician;
import ValidationClasses.DisplayChoices;
import ValidationClasses.validation;
import ValidationClasses.Date;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static final String externalFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\External.txt";
    public static final String materialFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Material.txt";
    public static final String customerFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Customer.txt";
    public static final String technicianFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Technician.txt";
    public static final String interventionFile = "C:\\Users\\eternel\\Desktop\\TP SI\\Tp Si\\src\\TextFilres\\Intervention.txt";

    public static void main(String[] args) throws IOException {
        DisplayChoice();
    }
    public static void DisplayChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("================== The Menu ==================");
            System.out.println("1-Insertions.");
            System.out.println("2-Editing.");
            System.out.println("3-Deleting.");
            System.out.println("4-Statistics.");
            System.out.println("5-Exit The Menu.");
            System.out.println("================== End Menu ==================");
            System.out.println("Enter your Choice !");
            choice = scanner.nextInt();
            if (choice < 1 || choice > 5) {
                System.out.println("Pleas, Enter A Valid Choice !");
            } else {
                switch (choice) {
                    case 1:
                        DisplayChoices.displayInsertionsSection();
                        break;
                    case 2:
                        DisplayChoices.displayEditingSection();
                        break;
                    case 3:
                        DisplayChoices.displayDeletingSection();
                        break;
                    case 4:
                        DisplayChoices.displayStatisticsSection();
                        break;
                }
            }
        } while (choice != 5);
    }


}