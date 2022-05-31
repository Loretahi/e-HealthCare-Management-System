import java.util.ArrayList;

public class Hospital {

    private String name;
    private String address;
    protected static ArrayList<Hospital> hospitals = new ArrayList<>();

    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
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

    public void addHospital(Hospital hospital) {
        if (hospitals.size() == 0) {
            hospitals.add(hospital);
            //System.out.println("hospital " + hospital.getName() + " has been added to hospitals list");
        } else {
            //for (Hospital ignored : hospitals) {
            if (checkHospitalInList(hospital.getName()) < 0) {
                hospitals.add(hospital);
               // System.out.println("hospital " + hospital.getName() + " has been added to hospitals list");
            } else {
                //System.out.println("hospital on file");
            }
        }
    }


    public int checkHospitalInList(String name) {
        for (Hospital checkedHospital : hospitals) {
            if (checkedHospital.getName().equals(name)) {
                return 1;
            }
        }
        return -1;
    }


}
