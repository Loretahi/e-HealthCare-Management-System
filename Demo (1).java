import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String peoplePath = "C:/Users/dkaza/Desktop/TalkToMeJava/people.csv";
        readFile(peoplePath);
        String hospitalPath = "C:/Users/dkaza/Desktop/TalkToMeJava/hospitals.csv";
        readFile(hospitalPath);

        /*System.out.println(Patient.patients.toString());
        System.out.println(Doctor.doctors.toString());
        System.out.println(Hospital.hospitals.toString());*/

        /*for (Patient patient : Patient.patients){
            System.out.println(patient.getFirstName() + " " + patient.getLastName());
        }*/

        boolean quit = false;
        while (!quit) {
            Menu.printMenu();
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input){
                case 1:
                    Menu.printAdminMenu();
                    break;
                case 2:
                    Menu.printPatientMenu();
                    Patient.accessAsPatient(Menu.patientUserName, Menu.patientPassword);
                    break;
                case 3:
                    quit = true;
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

    public static ArrayList<String> CSVtoArrayList(String input) {
        ArrayList<String> data = new ArrayList<>();
        if (input != null) {
            String[] splitData = input.split(",");
            if (splitData[0].toLowerCase().equals("patient")) {
                Patient patient = new Patient(splitData[1], splitData[2], splitData[3], splitData[4],
                        splitData[5], splitData[6], splitData[7], splitData[8]);
                patient.addPatient(patient);
            } else if (splitData[0].toLowerCase().equals("doctor")) {
                Doctor doctor = new Doctor(splitData[1], splitData[2], splitData[3], splitData[4],
                        splitData[5], splitData[6], splitData[7], splitData[8]);
                doctor.addDoctor(doctor);
            } else {
                Hospital hospital = new Hospital(splitData[0], splitData[1]);
                hospital.addHospital(hospital);
            }
        }
        return data;
    }
}

