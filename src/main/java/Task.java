import java.lang.reflect.Constructor;

public class Task {

    private String name;
    private Boolean isDone;
    protected char type;

    public Task(String _name) {
        name = _name;
        isDone = false;
    }

    public void markDone() {
        if (isDone) {
            throw new DukeException("Oops! :( You already did that task!");
        } else {
            isDone = true; 
        }
    }

    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + name; //ternary operator returns tick or X
    }
}
