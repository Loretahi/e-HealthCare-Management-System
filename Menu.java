import javax.xml.bind.SchemaOutputResolver;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    protected static Map<Integer, Options> authenticationMenu = new HashMap<>();
    protected static Map<Integer, Options> patientMenu = new HashMap<>();
    protected static Map<Integer, String> adminMenu = new HashMap<>();
    protected static Map<Integer, Options> appointmentMenu = new HashMap<>();


    static Patient user;
    static String patientUserName = "";
    static String patientPassword = "";
    static int choice = 0;
    static String[] input = null;
    static int month = 0;
    static int date = 0;
    static int year = 2022;

    public static void printMenu() {
        authenticationMenu.put(1, new Options(1, "Enter as admin"));
        authenticationMenu.put(2, new Options(2, "Enter as patient"));
        authenticationMenu.put(3, new Options(3, "Quit"));
        for (Map.Entry<Integer, Options> authMenu : authenticationMenu.entrySet()) {
            System.out.println("\t" + authMenu.getKey() + " " + authMenu.getValue().description);
        }

        while (!choiseValidation()) {
        }
        switch (choice) {
            case 1:
                printAdminMenu();
                break;
            case 2:
                printPatientMenu();
                System.out.println("--------------------------");
                printPatientsOptions();
                break;
            case 3:

        }

    }

    public static void printPatientMenu() {
        System.out.println("________________________");
        System.out.println("Patient portal");
        System.out.print("\tEnter username: ");
        patientUserName = scanner.nextLine();
        System.out.print("\tEnter password: ");
        patientPassword = scanner.nextLine();
        System.out.println("-------------------------");
        user = Patient.accessAsPatient(Menu.patientUserName, Menu.patientPassword);
        if (user == null) {
            System.out.println("Would you like to continue? [Y / N]");
            String choise = scanner.nextLine().toLowerCase();
            switch (choise) {
                default:
                    while (!choise.equals("y") && !choise.equals("n")) {
                        System.out.println("Please choose a proper option - [Yes / No]");
                        choise = scanner.nextLine();
                    }
                    break;
                case "y":
                    Menu.printPatientMenu();
                    break;
                case "n":
                    System.out.println("Returning back to login start menu.");
                    System.out.println("------------------------------------");
                    Menu.printMenu();
                    //break;

            }
        }
    }


    public static void printPatientsOptions() {
        System.out.println("List of available options:");
        patientMenu.put(1, new Options(1, "List all hospitals"));
        patientMenu.put(2, new Options(2, "Make appointment"));
        patientMenu.put(3, new Options(3, "Edit appointment"));
        patientMenu.put(4, new Options(4, "Check appointments"));
        patientMenu.put(5, new Options(5, "Change password"));
        patientMenu.put(6, new Options(6, "Log out"));
        for (Map.Entry<Integer, Options> patMenu : patientMenu.entrySet()) {
            System.out.println("\t" + patMenu.getKey() + " " + patMenu.getValue().description);
        }
        System.out.println("_________________________");
        System.out.print("Enter your choice: ");
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (InputMismatchException e) {
            System.out.println("Use digit to chose from the options: 1-6");
            System.out.println("************************************");
            printPatientsOptions();
        }

        switch (choice) {
            case 1:
                System.out.println("-------------------------------------------");
                for (Hospital currentHospital : Hospital.hospitalArrayList) {
                    System.out.println(Hospital.hospitalArrayList.indexOf(currentHospital) + ". " +
                            currentHospital.getName() + " - " + currentHospital.getAddress());
                }
                System.out.println("--------------------------------------------");
                System.out.println("Would you like to list all doctors from a particular hospital?");
                System.out.print("Please enter your choice: [Y/N] -> ");
                String choise = scanner.nextLine().toLowerCase();
                while (!choise.equals("y") && !choise.equals("n")) {
                    System.out.print("Please choose a proper option - [Yes / No] -> ");
                    choise = scanner.nextLine();
                }
                switch (choise) {
                    case "y":
                        //list doctors from hospital
                        System.out.print("Enter ID key of Hospital: ");
                        int code = scanner.nextInt();
                        scanner.nextLine();
                        Hospital.listDoctorsFromParticularHospital(code);
                        scanner.nextLine();
                        break;
                    case "n":
                        System.out.println("Returning back to login start menu.");
                        System.out.println("------------------------------------");
                        Menu.printMenu();
                        break;
                    default:

                        break;
                }

                System.out.println("press enter key to continue....");
                scanner.nextLine();
                Menu.printPatientsOptions();
                break;
            case 2:
                System.out.println("------------------");
                appointmentMenu();
        }

    }


    public static void appointmentMenu() {
        System.out.println("Please choose an option for appointment:");
        appointmentMenu.put(1, new Options(1, "Search for particular doctor."));
        appointmentMenu.put(2, new Options(2, "Check list of doctors related to particular hospital."));
        appointmentMenu.put(3, new Options(3, "Return back"));
        for (Map.Entry<Integer, Options> appMenu : appointmentMenu.entrySet()) {
            System.out.println("\t" + appMenu.getKey() + ". " + appMenu.getValue().description);
        }
        System.out.println("_________________________");
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter first and last name: ");
                try {
                    input = scanner.nextLine().split(" ");
                } catch (ArrayIndexOutOfBoundsException e) {

                }
                appointmentMenuRegion();
                scanner.nextLine();
                break;


            case 2:
                break;
            case 3:
                printPatientsOptions();
                break;
        }

    }

    protected static void appointmentParticulardoctor(String fisrtName, String lastName) {
        for (Doctor chekedDoctor : Doctor.doctors) {
            if (chekedDoctor.getFirstName().toLowerCase().equals(fisrtName.toLowerCase())) {
                if (chekedDoctor.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
                    Doctor doctor = chekedDoctor;
                    chekedDoctor.toString();
                }
            }
        }
        System.out.println("Would you like to book appointment? [Y/N]");
        String choise = scanner.nextLine().toLowerCase();
        switch (choise) {
            default:
                while (!choise.equals("y") && !choise.equals("n")) {
                    System.out.println("Please choose a proper option - [Yes / No]");
                    choise = scanner.nextLine();
                }
                break;
            case "y":
                Menu.printPatientMenu();
                break;
            case "n":
                System.out.println("Returning back to login start menu.");
                System.out.println("------------------------------------");
                Menu.printMenu();
                break;

        }
    }

    public static void checkSchedule(String firstName, String lastName) {
        Doctor currentDoctor = Doctor.checkForDoctor(firstName, lastName);
        for (Map.Entry<Doctor, ArrayList<Appointment>> schedule : Doctor.schedule.entrySet()) {
            if (schedule.getKey() == currentDoctor) {
                System.out.println("Doctor " + schedule.getKey().getFirstName() + " " + schedule.getKey().getLastName());
                System.out.println("\t " + schedule.getValue().toString());
            } else if (schedule == null) {
                makeAppointment();
            }

        }
    }

    public static void makeAppointment() {
        //Appointment a = new Appointment( LocalDateTime.of( 2018 , 12 , 25 , 0 , 0 , 0 , 0 ) , Duration.ofHours( 2 ) );

        dateValidation();
        System.out.println("Please enter hour: ");
        int hour = hoursValidation();
        System.out.println("Please enter minutes: ");
        int minutes = minutesValidation();

        Appointment appointment = new Appointment(LocalDateTime.of(year, month, date,
                hour, minutes, 0, 0), Duration.ofMinutes(30));
    }


    public static void appointmentMenuRegion() {
        listRegionalDoctors();
    }

    protected static void listRegionalDoctors() {
        for (Doctor currentDoctor : Doctor.doctors) {
            if (currentDoctor.getCity().toLowerCase().equals(user.getCity().toLowerCase())) {
                System.out.print(Doctor.doctors.indexOf(currentDoctor) + ". " +
                        currentDoctor.getFirstName() + " " + currentDoctor.getLastName());
                for (Hospital checkedHospital : Hospital.hospitalArrayList) {
                    if (checkedHospital.getCity().toLowerCase().equals(currentDoctor.getCity().toLowerCase())) {
                        if (checkedHospital.getAddress().toLowerCase().equals(currentDoctor.getAddress().toLowerCase())) {
                            System.out.print(" - hospital " + checkedHospital.getName() + "\n");

                        }
                    }
                }
            }
        }
    }


    public static class Options {

        public final int optionId;
        protected final String description;

        public Options(int optionId, String description) {
            this.optionId = optionId;
            this.description = description;
        }
    }

    public static boolean choiseValidation() {
        try {
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            //scanner.nextLine();
            return true;
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Enter a digit to mark your choice!");
        }
        return false;
    }

    private static int hoursValidation() {
        try {
            int hour = scanner.nextInt();
            scanner.nextLine();
            boolean acceptableHour = false;
            if (hour >= 7 && hour < 17) {
                acceptableHour = true;
                return hour;
            }
            while (!acceptableHour) {
                System.out.println("Out of working time! Please book hour in interval 7-17 h!");
                hoursValidation();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please use digits to choose hour.");
            hoursValidation();
        }
        return -1;
    }

    private static int minutesValidation() {
        try {
            int minutes = scanner.nextInt();
            scanner.nextLine();
            boolean acceptableMinutes = false;
            if (minutes >= 0 && minutes < 60) {
                acceptableMinutes = true;
                return minutes;
            }
            while (!acceptableMinutes) {
                System.out.println("Minutes can be in the interval 0-59");
                minutesValidation();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please use digits to choose minutes");
            minutesValidation();
        }
        return -1;
    }

    protected static boolean dateValidation() {

        System.out.print("Please enter month: ");
        month = Integer.parseInt(scanner.nextLine());
        System.out.print("Please enter date: ");
        date = Integer.parseInt(scanner.nextLine());
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

    private static int monthValidation(int month) {
        if (month >= 1 && month <= 12) {
            return month;
        }
        System.out.println("Improper month!");
        return -1;
    }

    public static void printAdminMenu() {
        System.out.println("Admin portal");
        System.out.print("Enter username: ");
        String adminUserName = scanner.nextLine();
        System.out.print("Enter password: ");
        String adminPasswored = scanner.nextLine();
    }
}
