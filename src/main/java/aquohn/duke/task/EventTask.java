package aquohn.duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class EventTask extends TimedTask {

    EventTask(String _name, LocalDateTime _at) {
        super(_name, _at);
        type = 'E';
    }

    @Override
    public String toString() throws DateTimeException {
        return "[" + type + "]" + super.toString() + " (at: " + getTime() + ")";
    }
}
