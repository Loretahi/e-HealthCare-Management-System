import java.io.*;
import java.util.*;

public class Demo {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String peoplePath = "C:/Users/dkaza/Desktop/TalkToMeJava/people.csv";
        readFile(peoplePath);
        String hospitalPath = "C:/Users/dkaza/Desktop/TalkToMeJava/Hospital.csv";
        readFile(hospitalPath);
        Hospital.addDoctorsToHospital();


        int date = 0;
        int month = 0;
        int year = 2022;

        boolean quit = false;
        Person user = null;
        String input = "";

        System.out.println("\nWellcome to e-HealthCare Management System!");

        while (!quit) {
            Menu.printMenu();
            switch (input) {
                case "1":
                    Menu.printAdminMenu();
                    break;
                case "2":
                    Menu.printPatientMenu();
                    boolean quitToPrevious = false;
                    while (!quitToPrevious) {
                        user = Patient.accessAsPatient(Menu.patientUserName, Menu.patientPassword);
                        if (user == null) {
                            System.out.println("Would you like to continue? [Y / N]");
                            String choise = scanner.nextLine().toLowerCase();
                            switch (choise) {
                                case "y":
                                    Menu.printPatientMenu();
                                    user = Patient.accessAsPatient(Menu.patientUserName, Menu.patientPassword);
                                    break;
                                case "n":
                                    System.out.println("Returning back to login start menu.");
                                    System.out.println("------------------------------------");
                                    quitToPrevious = true;
                                    break;
                                default:
                                    System.out.println("Please choose a proper option - [Yes / No]");
                                    break;
                            }
                        }
                        while (user instanceof Patient && !quitToPrevious) {
                            System.out.println("---------------------------------");
                            Menu.printPatientsOptions();
                            input = scanner.nextLine();
                            switch (input) {
                                case "1":
                                    System.out.println("-------------------------------------------");
                                    for (Hospital currentHospital : Hospital.hospitalArrayList) {
                                        System.out.println(Hospital.hospitalArrayList.indexOf(currentHospital) + ". " +
                                                currentHospital.getName() + " - " + currentHospital.getAddress());
                                    }
                                    System.out.println("--------------------------------------------");
                                    System.out.println("press enter key to continue....");
                                    scanner.nextLine();
                                    break;
                                case "2":
                                    //appointment

                                    Menu.appointmentMenu();
                                    input = scanner.nextLine();
                                    switch (input) {
                                        case "1":
                                            while (!quitToPrevious) {
                                                System.out.println("-------------------------------");
                                                System.out.println("Which doctor you would like to create appointment to?");
                                                System.out.print("Please enter first and last name: ");
                                                String[] names = names = scanner.nextLine().toLowerCase().split(" ");
                                                Doctor chosenDoctor = null;
                                                try {
                                                    chosenDoctor = Doctor.checkForDoctor(names[0], names[1]);
                                                }catch (ArrayIndexOutOfBoundsException e){
                                                    System.out.println("Improper input!");
                                                    System.out.println("Continue? [Y/N]");
                                                    input = scanner.nextLine().toLowerCase();
                                                    switch (input){
                                                        case "y":
                                                            System.out.println("Returning back to options menu");
                                                            break;
                                                        default:
                                                            quitToPrevious = true;
                                                            break;
                                                    }
                                                    break;
                                                }

                                                if (chosenDoctor == null){
                                                    System.out.println("Would you like to list regional doctors? [Y/N]");
                                                    input = scanner.nextLine().toLowerCase();
                                                    switch (input) {
                                                        case "y":
                                                            System.out.println(Doctor.checkForDoctor(user.getAddress().toLowerCase()));
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }
                                            }
                                            break;
                                        case "3":
                                            //edit appointment
                                            break;
                                        case "4":
                                            //check appointment
                                            break;
                                        case "5":
                                            //change password
                                            break;
                                        case "6":
                                            quitToPrevious = true;
                                            break;
                                        default:
                                            System.out.println("Enter a proper digit");
                                            break;
                                    }

                            }
                        }
                    }
                case "3":
                    if (quit) {
                        System.out.println("System turn off...");
                        break;
                    }

            }


        }
    }


    public static void readFile(String path) {
        Scanner reader = null;
        File peopleFile = new File(path);
        try {
            reader = new Scanner(peopleFile);
            while (reader.hasNextLine()) {
                String input = reader.nextLine();
                CSVtoArrayList(input);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Please provide an existing file!");
        } finally {
            reader.close();
        }
    }

    public static void CSVtoArrayList(String input) { //ArrayList<String>
        ArrayList<String> data = new ArrayList<>();
        if (input != null) {
            String[] splitData = input.split(",");
            if (splitData[0].toLowerCase().equals("patient")) {
                Patient patient = new Patient(splitData[1], splitData[2], splitData[3], splitData[4],
                        splitData[5], splitData[6], splitData[7], splitData[8], splitData[9]);
                patient.addPatient(patient);
            } else if (splitData[0].toLowerCase().equals("doctor")) {
                Doctor doctor = new Doctor(splitData[1], splitData[2], splitData[3], splitData[4],
                        splitData[5], splitData[6], splitData[7], splitData[8], splitData[9], splitData[10]);
                Doctor.doctors.add(doctor);
            } else {
                Hospital hospital = new Hospital(splitData[0], splitData[1], splitData[2]);
                hospital.addHospitalToArrayList(hospital);
            }
        }
        //return data;
    }
}

