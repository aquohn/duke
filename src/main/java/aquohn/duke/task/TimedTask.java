package aquohn.duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimedTask extends Task {

    private static final DateTimeFormatter PAT_DATETIME = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter PAT_DATETIME_DISPLAY = DateTimeFormatter.ofPattern("eee, d MMM yyyy hh:mm a");

    protected LocalDateTime time;

    TimedTask(String _name, LocalDateTime _time) {
        super(_name);
        time = _time;
    }

   @Override
    public String toData() {
        return super.toData() + "\t" + time.format(PAT_DATETIME);
    }

    protected String getTime() throws DateTimeException {
        return time.format(PAT_DATETIME_DISPLAY);
    }

    public String setTime(LocalDateTime _time) {
        time = _time;
    }

    public static DateTimeFormatter getDataFormatter() {
        return PAT_DATETIME;
    }

    public static DateTimeFormatter getDisplayFormatter() {
        return PAT_DATETIME_DISPLAY;
    }
}
