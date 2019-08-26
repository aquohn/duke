import java.lang.reflect.Constructor;

public class ToDo extends Task {

    public ToDo(String _name) {
        super(_name);
        type = 'T';
    }

    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    @Override
    public Constructor defaultConstructor() {
        try {
            return ToDo.class.getConstructor(new Class[] {String.class});
        } catch (Exception excp) {
            throw new DukeException("Something has gone horribly wrong! Try reinstalling?");
        }
    }
}
