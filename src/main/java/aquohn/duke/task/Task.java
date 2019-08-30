package aquohn.duke.task;

public class Task {

    private String name;
    private Boolean isDone;
    protected char type;

    public Task(String _name) {
        name = _name;
        isDone = false;
        type = 'K';
    }

    public void markDone() {
        if (isDone) {
            throw new DukeException("You already did that task!");
        } else {
            isDone = true; 
        }
    }

    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + name; //ternary operator returns tick or X
    }

    public String toData() {
        return type + "\t" + (isDone ? "1" : "0") + "\t" + name;
    }
}
