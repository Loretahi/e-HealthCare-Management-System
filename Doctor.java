import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Doctor extends Person {


    protected static ArrayList<Doctor> doctors = new ArrayList<>();
    protected static Map<Doctor, ArrayList<Appointment>> schedule = new HashMap<>();

    private String typeOfDoctor;

    public Doctor(String firstName, String lastName, String gender, String age, String city, String address,
                  String email, String userName, String password, String typeOfDoctor) {
        super(firstName, lastName, gender, age, city, address, email, userName, password);
        this.typeOfDoctor = typeOfDoctor;
    }

    protected void printDoctorSchedule(Doctor doctor) {
    }

    protected static Doctor checkForDoctor(String firstName, String lastName) {
        for (Doctor checkedDoctor : doctors) {
            if (checkedDoctor.getFirstName().toLowerCase().equals(firstName)) {
                if (checkedDoctor.getLastName().toLowerCase().equals(lastName)) {
                    return checkedDoctor;
                }
            }
        }
        System.out.println("There isn't doctor with such names in your region!");
        return null;
    }

    protected static Doctor checkForDoctor(String address) {
        for (Doctor checkedDoctor : doctors) {
            if (checkedDoctor.getAddress().toLowerCase().equals(address)) {
                return checkedDoctor;
            }
        }
        System.out.println("No doctor in the region!");
        return null;
    }

    @Override
    public String toString() {
        return "Doctor " + super.getFirstName() + " " + super.getLastName() +"\n" +
                "Specialist of " + typeOfDoctor + "\n";
    }
}
