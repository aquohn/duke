public class ToDo extends Task {

    public ToDo(String _name) {
        super(_name);
        type = 'T';
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
