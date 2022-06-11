import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Person {

    protected static String adminsPath = "src/Admin.csv";
    protected static ArrayList<Admin> adminsList = new ArrayList<>();

    protected static String input = "";
    private static int choice = 0;
    protected static Scanner scanner = new Scanner(System.in);


    public Admin(String firstName, String lastName, String gender, int age, String city,
                 String address, String email, String userName, String password) {
        super(firstName, lastName, gender, age, city, address, email, userName, password);
    }

    public static void addAdmin(String[] data){
        int age = Integer.parseInt(data[3]);
        Admin admin = new Admin(data[0], data[1], data[2], age, data[4], data[5],
                data[6], data[7], data[8]);
        adminsList.add(admin);
    }

    public static void adminSignIn() {
        System.out.println("\t\t\t\tAdmin Portal\n\t\t\t\t-----------");
        System.out.print("\tEnter user name: ");
        String userName = scanner.nextLine();
        System.out.print("\tEnter password: ");
        String password = scanner.nextLine();
        Admin currentAdmin = confirmAdmin(userName, password);

        if (currentAdmin != null) {
            System.out.println("***********************************************");
            System.out.println("\t\tWelcome to admin's menu!\n\t\tLogged in as Admin " + currentAdmin.getFirstName() + " " +
                    currentAdmin.getLastName());
            System.out.println("------------------------------------------------");
            printAdminMenu();
        } else {
            System.out.println("Wrong credentials!");
            System.out.print("Would you like to continue? [Y/N] ");
            input = scanner.nextLine().toLowerCase();
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
                        System.out.println("Please make a choice from Yes / No as available here!");
                        break;
                }

            }

        }
    }

    private static Admin confirmAdmin(String username, String password) {
        for (Admin user : adminsList) {
            if (user.getUserName().equals(username)) {
                if (confirmAdminPassword(user.getPassword()) != 0) {
                    break;
                } else {
                    return user;
                }
            }
        }
        return null;
    }

    private static int confirmAdminPassword(String password) {
        for (Admin user : adminsList) {
            if (user.getPassword().equals(password)) {
                return 0;
            }
        }
        return -1;
    }

    public static void printAdminMenu() {
        System.out.println("\t1. List hospitals");
        System.out.println("\t2. Search hospital");
        //name//address//doctorsList
        System.out.println("\t3. Add new hospital in list");
        System.out.println("\t4. Erase hospital from the list");
        System.out.println("\t5. Edit hospital information");
        System.out.println("\t6. Change password");
        System.out.println("\t0. log out\n");
        System.out.print("\tEnter your choice: ");
        choice = Integer.parseInt(scanner.nextLine());
        try {
            switch (choice) {
                case 1:
                    System.out.println("__________________________________");
                    ReadAndWrite.readFile(Hospital.hospitalsPath);
                    Hospital.hospitalsListingOptions();
                    break;
                case 2:
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
                    Menu.printAuthenticationMenu();
                    break;
                default:
                    System.out.println("Improper choice!");
                    System.out.println("Available options are: [0-6]");
                    printAdminMenu();
            }
        }catch (NumberFormatException e){
            System.out.println("Please use digits to navigate: [0-6]");
            printAdminMenu();
        }
    }

    /*System.out.println("More options [Y/N]: ");
        Admin.input = Admin.scanner.nextLine().toLowerCase();
        switch (Admin.input){
            case "y":

                break;
            case "n":
                System.out.println("\t\tReturning back to Admin menu.\n\t\t-----------------------------------");
                Admin.printAdminMenu();
                break;
        }*/


}
