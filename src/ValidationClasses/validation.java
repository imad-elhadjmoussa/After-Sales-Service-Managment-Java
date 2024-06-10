package ValidationClasses;

import java.io.File;
import java.util.Scanner;

public class validation {
    public static String validInput(int dataLength, String type, String dataName) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter " + dataName + " of " + type + ", contains at most " + dataLength + " characters :");
        String data;
        data = input.nextLine();
        while (data.length() > dataLength) {
            System.out.println("Pleas, Enter a valid " + dataName + " !");
            data = input.nextLine();
        }

        int n = data.length();
        if (data.length() < dataLength) {
            for (int i = 1; i <= dataLength - n; i++) {
                data += ' ';
            }
        }
        return data;
    }

    public static String validInputeName(int dataLength, String type, String dataName) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter " + dataName + " of " + type + ", contains at most " + dataLength + " Letters:");
        String data = input.nextLine();
        while (!validation.validName(data) || data.length() > dataLength) {
            System.out.println("Pleas, Enter a valid " + dataName + " !");
            data = input.nextLine();
        }
        int n = data.length();
        if (data.length() < dataLength) {
            for (int i = 1; i <= dataLength - n; i++) {
                data += ' ';
            }
        }
        return data;
    }

    private static boolean validNumber(String data) {
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) < '0' || data.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    public static String validInputNumber(int dataLength, String type, String dataName) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter " + dataName + " of " + type + ", contains " + dataLength + " Numbers:");
        String data = input.nextLine();
        while (!validNumber(data) || data.length() != dataLength) {
            System.out.println("Pleas, Enter A Valid " + dataName + " :");
            data = input.next();
        }
        return data;
    }

    private static boolean validName(String data) {
        for (int i = 0; i < data.length(); i++) {
            if ((data.charAt(i) < 'A' || data.charAt(i) > 'z') && data.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static String validEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Email As following EEEEEEEEEE@gmail.com");
        System.out.println("Enter EEEEEEEEEE :");
        String data = input.next();
        while (data.length() > 10) {
            System.out.println("Pleas, Enter a valid Email :");
            data = input.next();
        }
        return data + "@gmail.com";
    }

    //Function to Rename file
    public static void renameFile(String oldFileName, String NewFileName) {
        File newFile = new File(NewFileName);
        File oldFile = new File(oldFileName);
        oldFile.renameTo(newFile);
    }

}
