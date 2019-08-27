import java.time.LocalDateTime;
import java.time.DateTimeException;

public class Deadline extends TimedTask {

    public Deadline(String _name, LocalDateTime _by) {
        super(_name, _by);
        type = 'D';
    }

    @Override
    public String toString() throws DateTimeException {
        return "[" + type + "]" + super.toString() + " (by: " + getTime() + ")";
    }

}
