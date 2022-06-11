import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAndWrite {

    public static void addToList(String[] data) {
        int age = Integer.parseInt(data[3]);

    }

    public static void readFile(String path) {
        ArrayList dataList = new ArrayList();
        Scanner reader = null;
        File filePath = new File(path);
        try {
            reader = new Scanner(filePath);
            while (reader.hasNextLine()) {
                String input = reader.nextLine();
                if (path.substring(4).equals("Admin.csv")) {
                    Admin.addAdmin(CSVtoArrayList(input));
                } else if (path.substring(4).equals("Doctors.csv")) {
                    Doctor.addDoctor(CSVtoArrayList(input));
                } else if (path.substring(4).equals("Patients.csv")) {
                    Patient.addPatient(CSVtoArrayList(input));
                } else {
                    Hospital.addHospital(CSVtoArrayList(input));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Please provide an existing file!");
        } finally {
            reader.close();
        }
        //return dataList;
    }

    public static String[] CSVtoArrayList(String input) {
        //ArrayList<String> data = new ArrayList<>();
        if (input != null) {
            String[] splitData = input.split(",");
            return splitData;
        }
        return null;
    }

    public static void writeOn(String name, String city, String adress, String filepath) {

        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(name + "," + city + "," + adress);
            pw.flush();
            pw.close();

            JOptionPane.showMessageDialog(null, "Record saved");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }

    }
}
