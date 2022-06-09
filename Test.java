import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    private String description;
    private Date date;

    public Test(String description, int day, int month, int year) throws ParseException {
        this.description=description;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.setDate(simpleDateFormat.parse(String.format("%d/%d/%d", day, month, year)));

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Your Appointment is : %s. On(date): ",
                this.getDescription(), this.getDate().toString());
    }

    public boolean occursOn(int year, int month, int day) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = simpleDateFormat.parse(String.format("%d/%d/%d", day, month, year));

        return this.getDate().equals(date);
    }
}
