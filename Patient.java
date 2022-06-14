import java.util.ArrayList;
import java.util.Scanner;

public class Patient extends Person {

    private static Scanner scanner = new Scanner(System.in);
    private static String input = "";
    protected static String patientsPath = "src/Patients.csv";
    private static ArrayList<Patient> patientsList = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static Patient user = null;
    private static Appointment currentAppointment = null;


    public Patient(String firstName, String lastName, String gender, int age, String city,
                   String address, String email, String userName, String password) {
        super(firstName, lastName, gender, age, city, address, email, userName, password);
    }

    public static void addPatient(String[] data) {
        int age = Integer.parseInt(data[3]);
        Patient patient = new Patient(data[0], data[1], data[2], age, data[4], data[5],
                data[6], data[7], data[8]);
        patientsList.add(patient);
    }

    public static void patientSignIn() {
        System.out.println("\t\t-----------------------------");
        System.out.println("\t\t\tWelcome to Patients Portal!");
        System.out.print("\tEnter user name: ");
        String userName = scanner.nextLine();
        System.out.print("\tEnter password: ");
        String password = scanner.nextLine();
        user = confirmPatient(userName, password);

        if (user != null) {
            System.out.println("***********************************************");
            System.out.println("\t\t\t\tWelcome to Patients menu!\n\t\tLogged in as Patient " + user.getFirstName() + " " +
                    user.getLastName());
            System.out.println("------------------------------------------------");
            ReadAndWrite.readFile(Hospital.hospitalsPath);
            Hospital.mappingHospitals();
            printPatientMenu();
        } else {
            System.out.println("Wrong credentials!");
            System.out.print("Would you like to continue? [Y/N] ");
            input = Menu.scanner.nextLine().toLowerCase();
            boolean verification = false;
            while (!verification) {
                switch (input) {
                    case "y":
                        verification = true;
                        patientSignIn();
                        break;
                    case "n":
                        verification = true;
                        Menu.printAuthenticationMenu();
                        break;
                    default:
                        System.out.println("-----------------------------");
                        System.out.println("Please make a choice from [Y/N] as available here!");
                        System.out.print("Enter option: ");
                        input = Menu.scanner.nextLine().toLowerCase();
                        System.out.println("-----------------------------");
                        break;
                }

            }

        }
    }

    private static Patient confirmPatient(String username, String password) {
        for (Patient checkedUser : patientsList) {
            if (checkedUser.getUserName().equals(username)) {
                if (checkedUser.getPassword().equals(password)) {
                    user = checkedUser;
                    return user;
                }
                //Menu.user = checkedUser;
                /*if (confirmPatientPassword(checkedUser.getPassword()) == 0) {
                    break;
                } else {
                    return checkedUser;
                }*/
            }
        }
        return null;
    }

    /*private static int confirmPatientPassword(String password) {
        for (Patient checkedUser : patientsList) {
            if (checkedUser.getPassword().equals(user.getPassword())) {
                return 0;
            }
        }
        return -1;
    }*/

    protected static void printPatientMenu() {
        System.out.println("\t1. List hospitals");
        System.out.println("\t2. Book appointment");
        //name//address//doctorsList
        System.out.println("\t3. Edit appointment");
        System.out.println("\t4. Erase appointment");
        System.out.println("\t5. Show appointments");
        System.out.println("\t6. Change password");
        System.out.println("\t0. log out\n");
        System.out.print("\tEnter your choice: ");
        switch (Menu.choiceValidation()) {
            case 1:
                System.out.println("\t__________________________________");

                Hospital.hospitalsListingOptions();
                break;
            case 2:
                System.out.println("__________________________________");
                if (!bookAppointment()){
                    System.out.println("Appointment not successful!");
                    System.out.println("Returning back to previous menu...");
                    System.out.println("-----------------------------------");

                }
                printPatientMenu();

                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 0:
                System.out.println("---------------------\nLogged out.\n-------------------------------------");
                Menu.printAuthenticationMenu();
                break;
            default:
                System.out.println("Improper choice!");
                System.out.println("Available options are: [0-6]");
                printPatientMenu();
                break;
        }
    }

    private static boolean bookAppointment() {
        System.out.print("Enter date: ");
        int date = Menu.choiceValidation();
        System.out.print("Enter month: ");
        int month = Menu.choiceValidation();
        System.out.print("Enter year: ");
        int year = Menu.choiceValidation();
        while (!Appointment.yearValidation(year)) {
            System.out.print("Enter year: ");
            Menu.choiceValidation();
        }
        while (!Appointment.dateValidation(date, month, year)) {
            System.out.print("Enter date: ");
            date = Menu.choiceValidation();
            System.out.print("Enter month: ");
            month = Menu.choiceValidation();
        }

        System.out.print("Enter hour: ");
        int hour = Menu.choiceValidation();
        System.out.print("Enter minutes: ");
        int minutes = Menu.choiceValidation();
        while (Appointment.timeValidation(hour, minutes)) {
            System.out.print("Enter hour: ");
            hour = Menu.choiceValidation();
            System.out.print("Enter minutes: ");
            minutes = Menu.choiceValidation();
            Appointment.timeValidation(hour, minutes);
        }

        String[] doctor = null;
        String docFirstName = "";
        String docLastName = "";
        System.out.print("Enter doctor names: ");
        try {
            doctor = scanner.nextLine().split(" ");
            docFirstName = doctor[0];
            docLastName = doctor[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Improper input!\n" +
                    "Returning back on previous menu...");
            printPatientMenu();
        }

        if (Doctor.searchDoctor(docFirstName, docLastName) == null){
            return false;
        }else {
            System.out.println("Appointment approved!");
            Appointment appointment = new Appointment(date, month, year, hour, minutes, Doctor.doctor, user);
            ReadAndWrite.writeAppointmentOn(year, month, date, Doctor.doctor, hour, minutes, user, "src/Appointments.csv");
            System.out.println("-------------------------------------------");
        }
        return true;
    }

    protected static void patientSignUp(){
        //String firstName, String lastName, String gender, int age, String city,
        //                   String address, String email, String userName, String password
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter m@ilbox: ");
        String email = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        boolean usernameAvailability = false;
        while (!usernameAvailability) {
            usernameAvailability = true;
            for (Patient newUser : patientsList) {
                if (newUser.getUserName().equals(username)) {
                    System.out.println("Username not available!");
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    usernameAvailability = false;
                }
            }
        }
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        Patient newUser = new Patient(firstName, lastName, gender, age, city, address, email, username, password);
        ReadAndWrite.writePatientOn(firstName, lastName, gender, age, city, address, email, username, password, patientsPath);
        System.out.println("User has been created!");
        patientSignIn();

    }
}
