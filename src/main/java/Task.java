import java.lang.*;

public class Task {

    private String name;
    private Boolean isDone;
    protected char type;

    public Task(String _name) {
        name = _name;
        isDone = false;
    }

    public void markDone() {
        isDone = true; 
    }

    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + name; //ternary operator returns tick or X
    }
}
