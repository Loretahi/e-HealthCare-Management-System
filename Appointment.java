public class Appointment {
    private int date;
    private int month;
    private int year;
    private int hour;
    private int minutes;
    private Doctor doctor;
    private Patient user;

    public Appointment(int date, int month, int year, int hour,
                       int minutes, Doctor doctor, Patient user) {
        this.date = date;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
        this.doctor = doctor;
        this.user = user;
    }

    private static boolean isLeap(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    protected static boolean dateValidation(int date, int month, int year) {
        boolean confirmed = false;
        if (month < 1) {
            return false;
        } else {
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


    private static int monthValidation(int month) {
        if (month >= 1 && month <= 12) {
            return month;
        }
        System.out.println("Improper month!");
        return -1;
    }

    protected static boolean yearValidation(int year) {
        if (year >= 2022) {
            return true;
        }
        System.out.println("Can't book appointment for previous period!");
        return false;
    }

    protected static boolean timeValidation(int hour, int minutes){
        if (hoursValidation(hour) < 0){
            return false;
        }
        if (minutesValidation(minutes) <= 0){
            return false;
        }
        System.out.println("Time accepted!");
        System.out.println(hour + ":" + minutes);
        return true;
    }

    private static int hoursValidation (int hours){
        if (hours >= 7 && hours < 17){
            return hours;
        }
        System.out.println("Out of working hours");
        return -1;
    }

    private static int minutesValidation(int minutes){
        if (minutes >= 0 && minutes <= 59){
            return minutes;
        }
        System.out.println("Improper choice as hour has 60 minutes");
        return -1;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getUser() {
        return user;
    }

    public void setUser(Patient user) {
        this.user = user;
    }
}