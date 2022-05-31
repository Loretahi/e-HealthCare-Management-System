import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    static String patientUserName = "";
    static String patientPassword = "";

    public static void printMenu(){
        System.out.println("1.Enter as Admin");
        System.out.println("2.Enter as patient");
        System.out.println("3.Quit");
        System.out.print("Enter your choice:");
    }

    public static void printPatientMenu(){
        System.out.println("Patient portal");
        System.out.print("Enter username: ");
        patientUserName = scanner.nextLine();
        System.out.print("Enter password: ");
        patientPassword = scanner.nextLine();
    }

    public static void printAdminMenu(){
        System.out.println("Admin portal");
        System.out.print("Enter username: ");
        String adminUserName = scanner.nextLine();
        System.out.print("Enter password: ");
        String adminPasswored = scanner.nextLine();
    }
}
