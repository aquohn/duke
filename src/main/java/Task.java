import java.lang.*;

public class Task {

    private String name;
    private Boolean isDone;

    public Task(String _name) {
        name = _name;
        isDone = false;
    }

    public void markDone() {
        isDone = true; 
    }

    public String getName() {
        return name;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}
