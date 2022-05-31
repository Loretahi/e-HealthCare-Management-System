import java.util.ArrayList;

public class Doctor extends Person{

    protected static ArrayList<Doctor> doctors = new ArrayList<>();

    public Doctor(String firstName, String lastName, String gender, String age, String address, String email, String userName, String password) {
        super(firstName, lastName, gender, age, address, email, userName, password);
    }

    public void addDoctor(Doctor doctor){
        doctors.add(doctor);
    }
}
