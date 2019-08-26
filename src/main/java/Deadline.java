import java.lang.reflect.Constructor;

public class Deadline extends Task {

    private String by;

    public Deadline(String _name, String _by) {
        super(_name);
        type = 'D';
        by = _by;
    }

    public String toString() {
        return "[" + type + "]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public Constructor defaultConstructor() {
        try {
            return Deadline.class.getConstructor(new Class[] {String.class, String.class});
        } catch (Exception excp) {
            throw new DukeException("Something has gone horribly wrong! Try reinstalling?");
        }
    }
}
