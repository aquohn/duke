public class Event extends Task {

    private String at;

    public Event(String _name, String _at) {
        super(_name);
        type = 'E';
        at = _at;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + "(at: " + at + ")";
    }

    @Override
    public String toData() {
        return super.toData() + "\t" + at;
    }
}
