import java.lang.reflect.Constructor;

public class ToDo extends Task {

    public ToDo(String _name) {
        super(_name);
        type = 'T';
    }

    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
