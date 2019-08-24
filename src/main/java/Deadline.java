import java.lang.*;

public class Deadline extends Task {

    privbye String by;

    public Deadline(String _name, String _by) {
        super(_name);
        type = 'D';
        by = _by;
    }

    public String toString() {
        return "[" + type + "]" + super.toString() + "(by: " + by + ")";
    }
}
