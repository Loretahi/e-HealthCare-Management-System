import java.util.Scanner;

public class Menu {
    protected static Scanner scanner = new Scanner(System.in);
    protected static int choice = 0;
    protected static String input = "";
    public static Person user = null;

    public static void printAuthenticationMenu() {
        System.out.println("\tLog in menu:");
        System.out.println("\t\t1. Admin sign in");
        System.out.println("\t\t2. Patient sign in");
        System.out.println("\t\t3. Sign up");
        System.out.println("\t\t0. Quit");
        System.out.print("\tEnter your choice: ");
        choiceValidation();
        System.out.println("\t--------------------------------------");
        //int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                ReadAndWrite.readFile(Admin.adminsPath);
                Admin.adminSignIn();
                break;
            case 2:
                ReadAndWrite.readFile(Patient.patientsPath);
                Patient.patientSignIn();
                break;
            case 3:
                ReadAndWrite.readFile(Patient.patientsPath);
                //patientSignUp

                break;
            case 0:
                System.out.println("System turn off...");
                //quit
                break;
            default:
                System.out.println("________________________________________________________");
                System.out.println("Please make your choice from the available options [0-3]");
                System.out.println("--------------------------------------------------------");
                printAuthenticationMenu();
                break;
        }
    }

    public static int choiceValidation() {
        boolean confirmation = false;
        while (!confirmation) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("######################################");
                System.out.println("Please use digits to navigate the menu");
                System.out.println("######################################");
                System.out.println();
                System.out.print("\tEnter your choice: ");
                choiceValidation();
            }
            confirmation = true;
        }
        return choice;
    }

    public static String input() {
        boolean verification = false;
        while (!verification) {
            input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "y":
                    verification = true;
                    break;
                case "n":
                    System.out.println("Returning back to previous menu...");
                    verification = true;
                    break;
                default:
                    System.out.println("-----------------------------");
                    System.out.println("Please make a choice from [Y/N] as available here!");
                    System.out.print("Enter option: ");
                    input = Menu.scanner.nextLine().toLowerCase();
                    // System.out.println("-----------------------------");
                    break;
            }
        }
        return input;
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
