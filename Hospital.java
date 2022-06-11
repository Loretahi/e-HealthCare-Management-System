import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

public class Hospital {

    protected static String hospitalsPath = "src/hospitals.csv";
    protected static ArrayList<Hospital> hospitalsList = new ArrayList<>();
    protected static Map<Integer, Hospital> hospitalMap = new TreeMap<>();
    protected static Map<Hospital, ArrayList<Doctor>> hospitalEmployees = new HashMap<>();

    private String name;
    private String city;
    private String address;

    public Hospital(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public static void addHospital(String[] data){
        Hospital hospital = new Hospital(data[0], data[1], data[2]);
        hospitalsList.add(hospital);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static void hospitalsListingOptions(){
        System.out.println("\t- List hospitals");
        System.out.println("\t\t1. List hospitals by name.");
        System.out.println("\t\t2. List hospitals by city");
        System.out.println("\t\t0. Return back\n");

        try {
            System.out.print("Enter your choice: ");
            Menu.choice = Integer.parseInt(Menu.scanner.nextLine());
            switch (Menu.choice){
                case 1:
                    listAllHospitalsSortedByName();
                    System.out.println("Press enter to continue...");
                    Menu.scanner.nextLine();
                    hospitalsListingOptions();
                    break;
                case 2:
                    listAllHospitalsSortedByCity();
                    break;
                case 0:
                    Admin.printAdminMenu();
                default:
                    System.out.println();
                    break;
            }
        }catch (NumberFormatException e){
            System.out.println("--------------------------------------");
            System.out.println("Please use digits to navigate the menu");
            System.out.println("--------------------------------------");
            System.out.println();
            hospitalsListingOptions();

        }

    }

    public static void listAllHospitalsSortedByName(){
        Collections.sort(hospitalsList, Comparator.comparing(Hospital::getName));
        for (int i = 0; i < hospitalsList.size(); i++){
            System.out.println((i + 1) + ". " + hospitalsList.get(i).getName() + " - " + hospitalsList.get(i).getCity() +
                    " - " + hospitalsList.get(i).getAddress());
        }

    }

    public static void listAllHospitalsSortedByCity(){
        Collections.sort(hospitalsList, Comparator.comparing(Hospital::getCity));
        for (int i = 0; i < hospitalsList.size(); i++){
            System.out.println((i + 1) + ". " + hospitalsList.get(i).getName() + " - " + hospitalsList.get(i).getCity() +
                    " - " + hospitalsList.get(i).getAddress());
            mappingHospitals(i, hospitalsList.get(i).getName());
        }
        System.out.println("Press enter to continue back....");
        Menu.scanner.nextLine();
        hospitalsListingOptions();
    }

    private static void mappingHospitals(int index, String name){
        for (Hospital currentHospital : hospitalsList){
            if (currentHospital.getName().toLowerCase().equals(name.toLowerCase()))
                hospitalMap.put(index, currentHospital);
        }
    }

    public static void hospitalMenu(){
        System.out.println("1. Open hospital by ID");
        System.out.println("2. Add hospital");
        System.out.println("3. Edit hospital");
        System.out.println("4. Erase hospital");


    }

}
