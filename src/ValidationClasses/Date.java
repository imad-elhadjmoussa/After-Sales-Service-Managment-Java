package ValidationClasses;

import java.util.Scanner;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(String dateName){
        Scanner input=new Scanner(System.in);
        System.out.println("Enter date of "+dateName+" As following {DD/MM/YYYY}");
        int day;
        System.out.println("Enter the day : ");
        day=input.nextInt();
        while(day<1 || day>31){
            System.out.println("Pleas, Enter a valid day :");
            day=input.nextInt();
        }
        int month;
        System.out.println("Enter the month : ");
        month=input.nextInt();
        while(month<1 || month>12){
            System.out.println("Pleas, Enter a valid month : ");
            month=input.nextInt();
        }
        int year;
        System.out.println("Enter The year : ");
        year=input.nextInt();
        while(year<0){
            System.out.println("Pleas, Enter a valid year : ");
            year=input.nextInt();
        }
        this.day=day;
        this.month=month;
        this.year=year;
    }

    public String dateToString(int dataLength){
        String date=Integer.toString(this.day)+"/"+Integer.toString(this.month)+"/"+Integer.toString(this.year);
        int n = date.length();
        if (date.length() < dataLength) {
            for (int i = 1; i <= dataLength - n; i++) {
                date += ' ';
            }
        }
        return  date;
    }
}

