import java.util.ArrayList;

public abstract class Person {

    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String address;
    private String email;
    private String userName;
    private char[] password;


    public Person(String firstName, String lastName, String gender, String age,
                  String address, String email, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = Integer.parseInt(age);
        this.address = address;
        this.email = email;
        this.userName = userName;
        this.password = convertPassword(password);

    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getUserName() {
        return userName;
    }

    public char[] getPassword() {
        return password;
    }

    protected char[] convertPassword(String password) {
        char[] safePassword = new char[password.length()];
        for (int i = 0; i < password.length(); i++) {
            safePassword[i] = password.charAt(i);
        }
        return safePassword;
    }

    @Override
    public String toString() {
        return "firstName='" + firstName + '\n' +
                "lastName='" + lastName + '\n' +
                "gender='" + gender + '\n' +
                "age=" + age + '\n' +
                "address='" + address + '\n' +
                "email='" + email + '\n';
    }
}
