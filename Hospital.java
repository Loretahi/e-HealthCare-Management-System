import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Hospital {

    private String name;
    private String city;
    private String address;

    protected static ArrayList<Hospital> hospitalArrayList = new ArrayList<>();
    protected static Map<Hospital, ArrayList<Doctor>> hospitalEmployees = new HashMap<>();
    protected static Map<Integer, Hospital> hospitalsMap = new HashMap<>();
    protected static Map<Integer, Map<Hospital, ArrayList<Doctor>>> fullHouse = new HashMap<>();


    public Hospital(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //Map<Integer, Map<Hospital, ArrayList<Doctor>>>
    public void addHospitalToArrayList(Hospital hospital) {
        if (hospitalArrayList.size() == 0) {
            hospitalArrayList.add(hospital);

            //System.out.println("hospital " + hospital.getName() + " has been added to hospitals list");
        } else {
            //for (Hospital ignored : hospitals) {
            if (checkHospitalInList(hospital.getName()) < 0) {
                hospitalArrayList.add(hospital);
                // System.out.println("hospital " + hospital.getName() + " has been added to hospitals list");
            } else {
                //System.out.println("hospital on file");
            }
        }
    }


    public int checkHospitalInList(String name) {
        for (Hospital checkedHospital : hospitalArrayList) {
            if (checkedHospital.getName().equals(name)) {
                return 1;
            }
        }
        return -1;
    }

    public void sortedHospitalsList() {
        for (Hospital currentHospital : Hospital.hospitalArrayList) {
            System.out.println(Hospital.hospitalArrayList.indexOf(currentHospital) + ". " +
                    currentHospital.getName() + " - " + currentHospital.getAddress());
        }
    }

    protected static void addDoctorsToHospital() {
        for (Hospital currentHospital : hospitalArrayList) {
            for (Doctor currentDoctor : Doctor.doctors) {
                if (currentHospital.getCity().equals(currentDoctor.getCity())) {
                    if (currentHospital.getAddress().equals(currentDoctor.getAddress())) {
                        hospitalEmployees.put(currentHospital, Doctor.doctors);
                    }
                }
            }
            //mapAll();
        }
    }

    protected static void mapAll() {
        for (Hospital currentHospital : hospitalArrayList) {
            fullHouse.put(hospitalArrayList.indexOf(currentHospital),
                    (Map<Hospital, ArrayList<Doctor>>) hospitalEmployees.get(currentHospital));
        }
    }

    //protected static Map<Hospital, ArrayList<Doctor>> hospitalEmployees = new HashMap<>();
    protected static void listDoctorsFromParticularHospital(int choise) {
        Hospital currentHospital = null;
        for (Hospital checkedHospital : hospitalArrayList) {
            if (Hospital.hospitalArrayList.indexOf(checkedHospital) == choise) {
                currentHospital = checkedHospital;
                System.out.println("\t- " + currentHospital.getName() + ":");
                break;
            }

        }
        //Set<Map.Entry<Hospital, ArrayList<Doctor>>> hospitalEntrySet = hospitalEmployees.entrySet();
        Set<Hospital> hospitalEntrySet = hospitalEmployees.keySet();

        for (Hospital key : hospitalEntrySet) {

        }
    }
}

