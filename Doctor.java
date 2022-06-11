import java.util.ArrayList;

public class Doctor extends Person{

    protected static String doctorsPath = "src/Doctors.csv";
    protected static ArrayList<Doctor> doctorsList = new ArrayList<>();


    private String typeOfDoctor;

    public Doctor(String firstName, String lastName, String gender, int age, String city,
                  String address, String email, String userName, String password, String typeOfDoctor) {
        super(firstName, lastName, gender, age, city, address, email, userName, password);
        this.typeOfDoctor = typeOfDoctor;
    }

    public static void addDoctor(String[] data){
        int age = Integer.parseInt(data[3]);
        Doctor doctor = new Doctor(data[0], data[1], data[2], age, data[4], data[5],
                data[6], data[7], data[8], data[9]);
        doctorsList.add(doctor);
    }

    public static void mapDoctorsToHospital(){

    }
}
