import java.lang.reflect.Constructor;

public class Event extends Task {

    private String at;

    public Event(String _name, String _at) {
        super(_name);
        type = 'E';
        at = _at;
    }

    public String toString() {
        return "[" + type + "]" + super.toString() + "(at: " + at + ")";
    }

    @Override
    public Constructor defaultConstructor() {
        try {
            return Event.class.getConstructor(new Class[] {String.class, String.class});
        } catch (Exception excp) {
            throw new DukeException("Something has gone horribly wrong! Try reinstalling?");
        }
    }
}
