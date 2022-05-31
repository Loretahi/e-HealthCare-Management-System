import java.util.ArrayList;


public class Patient extends Person {

    protected static ArrayList<Patient> patients = new ArrayList<>();

    public Patient(String firstName, String lastName, String gender, String age,
                   String address, String email, String userName, String password) {
        super(firstName, lastName, gender, age, address, email, userName, password);
    }


    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    private static int checkForExsitingPatient(Patient patient) {
        if (confirmPassword(patient, patient.getPassword())) {
            return 0;
        }
        return -1;
    }

    private static Person checkForExsitingPatient(String userName) {
        for (Person checkedPatient : patients) {
            if (checkedPatient.getUserName().equals(userName)) {
                if (checkForExsitingPatient((Patient) checkedPatient) == 0) {
                    return checkedPatient;
                }
            }
        }
        System.out.println("User not found");
        return null;
    }

    private static boolean confirmPassword(Person person, char[] password) {
        if (person.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public static Patient accessAsPatient(String username, String password) {
        Patient currentPatient = (Patient) checkForExsitingPatient(username);
        if (currentPatient != null){
            if(checkForExsitingPatient(currentPatient) == 0){
                return currentPatient;
            }
        }
        return null;
    }


}
