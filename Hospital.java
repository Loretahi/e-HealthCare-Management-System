import java.util.*;

public class Hospital {

    protected static Hospital hospitalInstance = null;

    protected static String hospitalsPath = "src/hospitals.csv";
    protected static ArrayList<Hospital> hospitalsList = new ArrayList<>();
    protected static Map<Integer, Hospital> hospitalMap = new HashMap<>();
    protected static Map<Hospital, ArrayList<Doctor>> hospitalEmployees = new HashMap<>();
    protected static Map<String, String> cityMap = new TreeMap<>();

    private String name;
    private String city;
    private String address;

    public Hospital(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    protected static void addHospital(String[] data) {
        Hospital hospital = new Hospital(data[0], data[1], data[2]);
        if (validateNewHospital(hospital)) {
            hospitalsList.add(hospital);
        }
    }

    protected static void addHospital() {
        Menu.input = "";
        try {
            System.out.println("\t---------------------------");
            System.out.println();
            String[] data = new String[3];
            System.out.print("\tEnter hospital name: ");
            data[0] = Menu.scanner.nextLine();
            Menu.input = data[0];
            System.out.print("\tEnter city: ");
            data[1] = Menu.scanner.nextLine();
            System.out.print("\tEnter address:");
            data[2] = Menu.scanner.nextLine();
            addHospital(data);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMandatory information related to hospital creation is:\n\t\t" +
                    "- Hospital name\n\t\t- City where hospital is located\n\t\t- Address of the hospital\n");
            System.out.println("\tPress enter to continue...");
            Menu.scanner.nextLine();
            addHospital();
        }
        searchHospitalOnName(Menu.input);
        Admin.printAdminMenu();

    }

    private static boolean validateNewHospital(Hospital hospital) {
        for (Hospital checkedHospital : hospitalsList) {
            if (checkedHospital.getName().toLowerCase().equals(hospital.getName().toLowerCase())) {
                if (checkedHospital.getCity().toLowerCase().equals(hospital.getCity().toLowerCase())) {
                    if (checkedHospital.getAddress().toLowerCase().equals(hospital.getAddress().toLowerCase())) {
                        System.out.println("\tHospital " + hospital.getName() + " based in " + hospital.getCity() + " on address " +
                                hospital.getAddress() + "\n\t is already on file!");
                        System.out.println("\n\tWould you like to add new hospital? [Y/N] ->");
                        switch (Menu.input()) {
                            case "y":
                                addHospital();
                                break;
                            case "n":
                                System.out.println("\tReturning back to hospital listing options.");
                                hospitalsListingOptions();
                                break;
                        }
                    }
                }
            }
        }
        return true;
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

    protected static void hospitalsListingOptions() {
        System.out.println("\t\t\t1. List hospitals by name.");
        System.out.println("\t\t\t2. List hospitals by city");
        System.out.println("\t\t\t3. Search hospital");
        System.out.println("\t\t\t0. Return back\n");
        System.out.print("\tEnter your choice: ");
        switch (Menu.choiceValidation()) {
            case 1:
                listAllHospitalsSortedByName();
                if (Menu.user instanceof Admin) {
                    Admin.additionalOptionsConfirmation();
                    System.out.println("\t--------------------------------------");
                }
                System.out.print("\tPress enter to continue...");
                Menu.scanner.nextLine();
                hospitalsListingOptions();
                break;
            case 2:
                listAllHospitalsSortedByCity();
                if (Menu.user instanceof Admin) {
                    Admin.additionalOptionsConfirmation();
                    System.out.println("\t--------------------------------------");
                }
                System.out.println("\tPress enter to continue...");
                Menu.scanner.nextLine();
                hospitalsListingOptions();
                break;
            case 3:
                //System.out.print("Enter city:");
                searchForHospital();
            case 0:
                if (Menu.user instanceof Admin) {
                    Admin.printAdminMenu();
                } else {
                    Patient.printPatientMenu();
                }
            default:
                System.out.println();
                break;
        }
    }

    public static void listAllHospitalsSortedByName() {
        Collections.sort(hospitalsList, Comparator.comparing(Hospital::getName));
        System.out.println("\t_________________________________________________________________");
        for (int i = 0; i < hospitalsList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + hospitalsList.get(i).getName() + "\n\t\t\t" + hospitalsList.get(i).getCity() +
                    ", " + hospitalsList.get(i).getAddress());
            System.out.println("\t-------------------------------------------------------------");
        }
    }

    public static void listAllHospitalsSortedByCity() {
        Collections.sort(hospitalsList, Comparator.comparing(Hospital::getCity));
        System.out.println("______________________________________________________________________________________");
        for (int i = 0; i < hospitalsList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + hospitalsList.get(i).getName() + "\n\t\t\t" + hospitalsList.get(i).getCity() + ", " +
                    hospitalsList.get(i).getAddress());
            System.out.println("--------------------------------------------------------------------------------------");
        }
        System.out.println("Press enter to continue...");
        Menu.scanner.nextLine();
        hospitalsListingOptions();
    }

    protected static void mappingHospitals() {
        Collections.sort(hospitalsList, Comparator.comparing(Hospital::getName));
        for (int i = 0; i < hospitalsList.size(); i++) {
            hospitalMap.put(i, hospitalsList.get(i));
        }
    }

    protected static void searchForHospital() {
        System.out.println("\t\t\t1. Search hospital by ID");
        System.out.println("\t\t\t2. Search hospital by name");
        System.out.println("\t\t\t3. Search hospitals by city");
        System.out.println("\t\t\t0. Return back\n");
        System.out.print("\tEnter your choice: ");
        switch (Menu.choiceValidation()) {
            case 1:
                searchHospitalOnID();
                break;
            case 2:
                System.out.print("\t\t\t\tEnter hospital name: ");
                Menu.input = Menu.scanner.nextLine();
                searchHospitalOnName(Menu.input);
                break;
            case 3:

                break;

            case 0:
                if (Menu.user instanceof Admin){
                    Admin.printAdminMenu();
                    break;
                }
                hospitalsListingOptions();
                break;
        }
    }

    protected static void searchHospitalOnID() {
        System.out.print("\t\t\tEnter hospital ID: ");
        int hospitalId = Menu.choiceValidation() - 1;
        System.out.println("_______________________________");
        try {
            // -----------------------------------------------   //върни обект от тук!!!!!
            //return hospitalMap.get(hospitalId).toString();
            System.out.println("ID." + (hospitalId + 1) + ". " + hospitalMap.get(hospitalId).getName() + "\n" +
                    hospitalMap.get(hospitalId).getCity() + " " + hospitalMap.get(hospitalId).getAddress());
            System.out.println("-------------------------------");
        } catch (NullPointerException e) {
            System.out.println("\tEntry doesn't exist.");
        }
        System.out.print("\tWould you like to continue searching? [Y/N] -> ");
        switch (Menu.input()) {
            case "y":
                searchHospitalOnID();
                break;
            case "n":
                searchForHospital();
                break;
        }
    }

    protected static void searchHospitalOnName(String name) {
        System.out.println("\t---------------------------");
        Hospital currentHospital = null;
        for (Hospital checkedHospital : hospitalsList) {
            if (checkedHospital.getName().toLowerCase().equals(name.toLowerCase())) {
                currentHospital = checkedHospital;
                break;
            }
        }
        if (currentHospital != null) {
            System.out.println("_______________________________");
            System.out.println("Hospital " + currentHospital.getName() + ", located in " + currentHospital.getCity() + "\n" +
                    "on address " + currentHospital.getAddress() + " is on file.");
            System.out.println("-------------------------------\n Press enter to continue...");
            Menu.scanner.nextLine();

        } else {
            if (Menu.user instanceof Admin) {
                System.out.print("\tHospital " + name + " not on file!\n\tWould you like to add it? [Y/N] -> ");
                switch (Menu.input()) {
                    case "y":
                        addHospital();
                        break;
                    case "n":
                        System.out.println("\tReturning back to hospital listing options.");
                        hospitalsListingOptions();
                        break;
                }
            } else {
                System.out.print("\tHospital " + name + " not on file!\n\tWould you like to continue searching? [Y/N] -> ");
                switch (Menu.input()) {
                    case "y":
                        searchForHospital();
                        break;
                    case "n":
                        System.out.println("\tReturning back to hospital listing options.");
                        hospitalsListingOptions();
                        break;
                }
            }

        }
    }

    @Override
    public String toString() {
        return "\n\tHospital" +
                name + "\n\t\t" + city + ", " + address;
    }


}
