import java.util.Scanner;

public class Menu {
    protected static Scanner scanner = new Scanner(System.in);
    public static int choice = 0;

    public static void printAuthenticationMenu() {
        System.out.println("\t\t\t\t\tLog in menu:");
        System.out.println("\t\t\t\t\t\t1. Admin sign in");
        System.out.println("\t\t\t\t\t\t2. Patient sign in");
        System.out.println("\t\t\t\t\t\t3. Sign up");
        System.out.println("\t\t\t\t\t\t0. Quit");

        try {
            System.out.print("Please enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            /*if (choice < 0 || choice > 3){
                System.out.println("________________________________________________________");
                System.out.println("Please make your choice from the available options [0-3]");
                System.out.println("--------------------------------------------------------");
                printAuthenticationMenu();
            }*/
        }catch (NumberFormatException e){
            System.out.println("--------------------------------------");
            System.out.println("Please use digits to navigate the menu");
            System.out.println("--------------------------------------");
            System.out.println();
            printAuthenticationMenu();
        }
        System.out.println("--------------------------------------");

        switch (choice){
            case 1:
                ReadAndWrite.readFile(Admin.adminsPath);
                Admin.adminSignIn();
                break;
            case 2:
                ReadAndWrite.readFile(Patient.patientsPath);
                Patient.patientSignIn();
                break;
            case 3:
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
}
