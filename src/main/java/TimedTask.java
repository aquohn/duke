import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

public class TimedTask extends Task {

    // TODO: convert to enum and place in separate file with other enums
    
    public static final DateTimeFormatter PAT_DATETIME = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public static final DateTimeFormatter PAT_DATETIME_VIEW = DateTimeFormatter.ofPattern("eee, d MMM yyyy hh:mm a");

    protected LocalDateTime time;

    public TimedTask(String _name, LocalDateTime _time) {
        super(_name);
        time = _time;
    }

   @Override
    public String toData() {
        return super.toData() + "\t" + time.format(PAT_DATETIME);
    }

    protected String getTime() throws DateTimeException {
        return time.format(PAT_DATETIME_VIEW);
    }
}
