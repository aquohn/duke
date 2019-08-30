package aquohn.duke.task;

import java.time.LocalDateTime;
import java.time.DateTimeException;

public class EventTask extends TimedTask {

    public EventTask(String _name, LocalDateTime _at) {
        super(_name, _at);
        type = 'E';
    }

    @Override
    public String toString() throws DateTimeException {
        return "[" + type + "]" + super.toString() + " (at: " + getTime() + ")";
    }
}
