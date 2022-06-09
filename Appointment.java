import org.threeten.extra.Interval;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Appointment {

    private LocalDateTime start;
    private Duration duration;

    public Appointment (LocalDateTime start , Duration duration ) {
        this.start = start;
        this.duration = duration;
    }
    public Interval toInterval (ZoneId zoneId ) {
        Objects.requireNonNull( zoneId , "Must pass a time zone to get the start/stop interval of an appointment. Message # bbf021e6-baa7-468d-83ad-cf73acb6702e." );
        ZonedDateTime zdtStart = this.start.atZone( zoneId );
        Interval interval = Interval.of( zdtStart.toInstant() , this.duration );
        return interval;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Duration getDuration() {
        return duration;
    }

    // Get start moment for a particular time zone.
    public ZonedDateTime toStartMoment ( ZoneId zoneId ) {
        ZonedDateTime zdt = this.toInterval( zoneId ).getStart().atZone( zoneId );
        return zdt;
    }

    // Get stop moment for a particular time zone.
    public ZonedDateTime toStopMoment ( ZoneId zoneId ) {
        ZonedDateTime zdt = this.toInterval( zoneId ).getEnd().atZone( zoneId );
        return zdt;
    }

    // Dynamically determine if this appointment will be in the future for some specific time zone.
    public Boolean isFuture ( ZoneId zoneId ) {
        Objects.requireNonNull( zoneId , "Must pass a time zone to determine if an appointment is in the future. Message # e1c64bc1-9a44-4d15-b20d-e68414fb5ab5." );
        ZonedDateTime zdtStart = this.start.atZone( zoneId );
        ZonedDateTime zdtNow = ZonedDateTime.now( zoneId );
        boolean isInTheFuture = zdtNow.isBefore( zdtStart );
        return isInTheFuture;
    }

    // -----------|  Object overrides  |---------------------------
    @Override
    public String toString ( ) {
        return "Appointment{ " +
                "start=" + start +
                " | duration=" + duration +
                " }";
    }

}
