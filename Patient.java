import java.util.ArrayList;

public class Patient extends Person{

    private static String input = "";
    protected static String patientsPath = "src/Patients.csv";
    private static ArrayList<Patient> patientsList = new ArrayList<>();


    public Patient(String firstName, String lastName, String gender, int age, String city,
                   String address, String email, String userName, String password) {
        super(firstName, lastName, gender, age, city, address, email, userName, password);
    }

    public static void addPatient(String[] data){
        int age = Integer.parseInt(data[3]);
        Patient patient = new Patient(data[0], data[1], data[2], age, data[4], data[5],
                data[6], data[7], data[8]);
        patientsList.add(patient);
    }

    public static void patientSignIn() {
        System.out.println("Welcome to Patients Portal!");
        System.out.print("\tEnter user name: ");
        String userName = Admin.scanner.nextLine();
        System.out.print("\tEnter password: ");
        String password = Admin.scanner.nextLine();
        Patient currentPatient = confirmPatient(userName, password);

        if (currentPatient != null) {
            System.out.println("***********************************************");
            System.out.println("\t\t\t\tWelcome to Patients menu!\n\t\tLogged in as Patient " + currentPatient.getFirstName() + " " +
                    currentPatient.getLastName());
            System.out.println("------------------------------------------------");
            printPatientMenu();
        } else {
            System.out.println("Wrong credentials!");
            System.out.print("Would you like to continue? [Y/N] ");
            input = Admin.scanner.nextLine().toLowerCase();
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
                        System.out.println("Please make a choice from Yes / No as available here!");
                        break;
                }

            }

        }
    }

    private static Patient confirmPatient(String username, String password) {
        for (Patient user : patientsList) {
            if (user.getUserName().equals(username)) {
                if (confirmPatientPassword(user.getPassword()) != 0) {
                    break;
                } else {
                    return user;
                }
            }
        }
        return null;
    }

    private static int confirmPatientPassword(String password) {
        for (Patient user : patientsList) {
            if (user.getPassword().equals(password)) {
                return 0;
            }
        }
        return -1;
    }

    private static void printPatientMenu() {
        System.out.println("1. List all hospitals");
        System.out.println("2. Book appointment");
        //name//address//doctorsList
        System.out.println("3. Edit appointment");
        System.out.println("4. Erase appointment");
        System.out.println("5. Show appointments");
        System.out.println("6. Change password");
        System.out.println("0. log out\n");
        System.out.print("Enter your choice: ");
        Menu.choice = Integer.parseInt(Menu.scanner.nextLine());
        try {
            switch (Menu.choice) {
                case 1:
                    System.out.println("__________________________________");
                    ReadAndWrite.readFile(Hospital.hospitalsPath);
                    Hospital.hospitalsListingOptions();
                    break;
                case 2:
                    //bookAppointment
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
            }
        }catch (NumberFormatException e){
            System.out.println("Please use digits to navigate: [0-6]");
            printPatientMenu();
        }
    }
}
