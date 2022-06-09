import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Date {
    private static int date;
    private static int month;
    private static int year;
    protected static Map<Date, ArrayList<Time>> calendar = new TreeMap<>();

    public Date(int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public static int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public static int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public static int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private static boolean isLeap(int year){
        if (year % 4 == 0){
            if (year % 100 == 0){
                if (year % 400 == 0){
                    return true;
                }
            }
        }
        return false;
    }

    protected static boolean dateValidation(int date, int month, int year){
        boolean confirmed = false;
        if (month < 1){
            return false;
        }else {
            switch (monthValidation(month)) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (date >= 1 && date <= 31) {
                        return true;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (date >= 1 && date <= 30) {
                        return true;
                    }
                    break;
                case 2:
                    if (isLeap(year)) {
                        if (date >= 1 && date <= 29) {
                            return true;
                        }
                    } else {
                        if (date >= 1 && date <= 29) {
                            return true;
                        }
                    }
                    break;
                default:

            }
        }
        System.out.println("Improper date! Please input proper date / month");
        return false;
    }

    private static int monthValidation(int month){
        if (month >= 1 && month <= 12){
            return month;
        }
        System.out.println("Improper month!");
        return -1;
    }
}
