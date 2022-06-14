import java.util.*;

public class Admin extends Person {

    protected static Scanner scanner = new Scanner(System.in);
    protected static String adminsPath = "src/Admin.csv";
    protected static ArrayList<Admin> adminsList = new ArrayList<>();
    protected static Admin adminUser;

    protected static String input = "";
    private static int choice = 0;


    public Admin(String firstName, String lastName, String gender, int age, String city,
                 String address, String email, String userName, String password) {
        super(firstName, lastName, gender, age, city, address, email, userName, password);
    }

    public static void addAdmin(String[] data) {
        int age = Integer.parseInt(data[3]);
        Admin admin = new Admin(data[0], data[1], data[2], age, data[4], data[5],
                data[6], data[7], data[8]);
        adminsList.add(admin);
    }

    public static void adminSignIn() {

        System.out.println("\t\t-----------------------------");
        System.out.println("\t\t\t\tAdmin Portal\n\t\t\t\t-----------");
        System.out.print("\tEnter user name: ");
        String userName = scanner.nextLine();
        System.out.print("\tEnter password: ");
        String password = scanner.nextLine();
        adminUser = confirmAdmin(userName, password);

        if (adminUser != null) {
            System.out.println("***********************************************");
            System.out.println("\t\tWelcome to admin's menu!\n\t\tLogged in as Admin " + adminUser.getFirstName() + " " +
                    adminUser.getLastName());
            System.out.println("------------------------------------------------");
            ReadAndWrite.readFile(Hospital.hospitalsPath);
            Hospital.mappingHospitals();
            printAdminMenu();
        } else {
            System.out.println("Wrong credentials!");
            System.out.print("Would you like to continue? [Y/N] ");
            input = Menu.scanner.nextLine().toLowerCase();
            boolean verification = false;
            while (!verification) {
                switch (input) {
                    case "y":
                        verification = true;
                        adminSignIn();
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
                        //System.out.println("-----------------------------");
                        break;
                }

            }
        }
    }

    private static Admin confirmAdmin(String username, String password) {
        for (Admin checkedAdminUser : adminsList) {
            if (checkedAdminUser.getUserName().equals(username)) {
                if (checkedAdminUser.getPassword().equals(password)) {
                    adminUser = checkedAdminUser;
                    return adminUser;
                }
            }
        }
        return null;
    }

    /*private static int confirmAdminPassword(String password) {
        for (Admin checkedAdminUser : adminsList) {
            if (checkedAdminUser.getPassword().equals(password)) {
                return 0;
            }
        }
        return -1;
    }*/

    public static void printAdminMenu() {
        System.out.println("\t-----------------------------------------");
        System.out.println("\t1. List hospitals");
        System.out.println("\t2. Search hospital");
        System.out.println("\t3. Add new hospital in list");
        System.out.println("\t4. Change password");
        System.out.println("\t0. log out\n");
        System.out.print("Enter your choice: ");
        switch (Menu.choiceValidation()) {
            case 1:
                System.out.println("\t__________________________________");
                Hospital.hospitalsListingOptions();
                break;
            case 2:
                System.out.println("\t__________________________________");
                hospitalAdminsOptions();
                break;
            case 3:
                System.out.println("\t__________________________________");
                Hospital.addHospital();
                break;
            case 4:
                System.out.println("\t__________________________________");
                changePassword();
                break;
            case 0:
                Menu.printAuthenticationMenu();
                break;
            default:
                System.out.println("Improper choice!");
                System.out.println("Available options are: [0-6]");
                printAdminMenu();
        }
    }

    protected static void hospitalAdminsOptions() {
        System.out.println("\t\t1. Open hospital by ID");
        System.out.println("\t\t2. Open hospital by name");
        System.out.println("\t\t0. Return to previous menu");
        System.out.print("\nEnter your choice: ");
        switch (Menu.choiceValidation()) {
            case 1:
                System.out.println("\t__________________________________");
                System.out.print("\t\t\tEnter hospital ID: ");
                int hospitalId = Menu.choiceValidation() - 1;
                Hospital.hospitalInstance = adminSearchHospitalOnID(hospitalId);
                for (Map.Entry<Integer, Hospital> checkedEntry : Hospital.hospitalMap.entrySet()) {
                    if (checkedEntry.hashCode() == Hospital.hospitalInstance.hashCode()) {
                        if (checkedEntry.equals(Hospital.hospitalInstance)) {
                            System.out.println(checkedEntry.getKey() + ". " +
                                    checkedEntry.getValue().toString());
                        }
                    }
                }
                if (additionalOptionsConfirmation()) {
                    additionalOptions(Hospital.hospitalInstance);
                }
                break;
            case 2:
                System.out.println("__________________________________");
                System.out.print("Enter hospital name: ");
                input = scanner.nextLine();
                try {
                    Hospital.hospitalInstance = adminSearchHospitalOnName(input);
                } catch (NullPointerException e) {
                    System.out.println("Entry doesn't exist!");
                    hospitalAdminsOptions();
                }

                System.out.println(Hospital.hospitalInstance.toString());
                if (additionalOptionsConfirmation()) {
                    additionalOptions(Hospital.hospitalInstance);
                }
                break;
            case 0:
                System.out.println("Returning back to previous menu");
                Hospital.hospitalsListingOptions();
                break;
            default:
                System.out.println("Improper choice!");
                System.out.println("Available options are: [0-6]");
                hospitalAdminsOptions();
        }

    }

    protected static boolean additionalOptionsConfirmation() {
        System.out.print("Would you like to check additional options? [Y/N]\nEnetr your choice: ");
        switch (Menu.input()) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Improper choice!");
                additionalOptionsConfirmation();
        }
        return false;
    }

    protected static Hospital adminSearchHospitalOnID(int id) {
        System.out.println("_______________________________");
        try {
            Hospital.hospitalInstance = Hospital.hospitalMap.get(id);
            //System.out.println("-------------------------------");
        } catch (NullPointerException e) {
            System.out.println("\tEntry doesn't exist.");
            if (hospitalNullEntry()) {
                System.out.print("\t\t\tEnter hospital ID: ");
                Hospital.hospitalInstance = Hospital.hospitalMap.get(id);
                adminSearchHospitalOnID(id);
            } else {
                hospitalAdminsOptions();
            }
        }
        // Hospital.hospitalMap.get(id).
        System.out.println("ID." + (id + 1) + ". " + Hospital.hospitalMap.get(id).getName() + "\n" +
                Hospital.hospitalMap.get(id).getCity() + " " +
                Hospital.hospitalMap.get(id).getAddress());
        System.out.println("-------------------------------");
        return Hospital.hospitalInstance;
    }

    protected static boolean hospitalNullEntry() {
        System.out.print("Would you like to continue? [Y/N] -> ");
        switch (Menu.input()) {
            case "y":
                break;
            case "n":
                System.out.print("Returning to previous menu...");
                return false;
        }
        return true;
    }

    protected static Hospital adminSearchHospitalOnName(String name) {
        System.out.println("\t---------------------------");
        Hospital.hospitalInstance = null;
        for (Hospital checkedHospital : Hospital.hospitalsList) {
            if (checkedHospital.getName().toLowerCase().equals(name.toLowerCase())) {
                Hospital.hospitalInstance = checkedHospital;
                // return Hospital.hospitalInstance;
                if (hospitalComparation(checkedHospital, Hospital.hospitalInstance)) {
                    return Hospital.hospitalInstance;
                }
            }
        }
        return null;
    }

    protected static boolean hospitalComparation(Hospital searchedHospital, Hospital currentHospital) {
        if (searchedHospital.equals(currentHospital)) {
            if (searchedHospital.hashCode() == currentHospital.hashCode()) {
                return true;
            }
        }
        return false;
    }

    private static void editHospitalCredentials(Hospital hospital) {
        System.out.println("\t------------------------------");
        System.out.print("\t\tEnter new name: ");
        String name = scanner.nextLine();
        System.out.print("\t\tEnter city: ");
        String city = scanner.nextLine();
        System.out.print("\t\tEnter address: ");
        String address = scanner.nextLine();

        hospital.setName(name);
        hospital.setCity(city);
        hospital.setAddress(address);

        System.out.println("Record updated!");
        System.out.println("New information over " + hospital.getName() + " saved!");
        System.out.println("\t--------------------------------");
        hospitalAdminsOptions();

    }

    private static void eraseHospital(Hospital hospital) {
       /* Hospital.hospitalsList.remove(hospital);
        System.out.println("Hospital deleted.");
        hospitalAdminsOptions();*/
        //System.out.println(Hospital.hospitalsList.indexOf(hospital));
        Hospital.hospitalsList.remove(hospital);
        Hospital.hospitalMap.remove(Hospital.hospitalsList.indexOf(hospital), hospital);
        System.out.println("Hospital deleted.");
        hospitalAdminsOptions();

    }

    private static void additionalOptions(Hospital hospital) {
        System.out.println("\t\t----------------------------");
        System.out.println("\t\t\t1. Add hospital");
        System.out.println("\t\t\t2. Edit hospital");
        System.out.println("\t\t\t3. Erase hospital");
        System.out.print("\tEnter your choice: ");
        switch (Menu.choiceValidation()) {
            case 1:
                Hospital.addHospital();
                break;
            case 2:
                editHospitalCredentials(hospital);
                break;
            case 3:
                eraseHospital(hospital);
                break;
        }
    }

    private static void changePassword() {

        System.out.print("\t\tConform password: ");
        String oldPass = scanner.nextLine();
        if (Menu.user.getPassword().equals(oldPass)) {
            System.out.print("\t\tEnter new password: ");
            String newPass = scanner.nextLine();
            Menu.user.setPassword(newPass);
            System.out.println("\t\tPassword updated!");
        }
        System.out.println("\t----------------------------");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
