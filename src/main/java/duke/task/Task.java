package duke.task;

import duke.exception.DukeException;

abstract class Task {

    private String name;
    private Boolean isDone;
    char type;

    Task(String _name) {
        name = _name;
        isDone = false;
        type = 'K';
    }

    void markDone() throws DukeException {
        if (isDone) {
            throw new DukeException("You already did that task!");
        } else {
            isDone = true; 
        }
    }

    public void setName(String _name) {
        name = _name;
    }

    public char getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + name; //ternary operator returns tick or X
    }

    public String toData() {
        return type + "\t" + (isDone ? "1" : "0") + "\t" + name;
    }
}
