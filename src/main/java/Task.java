import java.lang.*;

public class Task {

    private String name;
    private Boolean isDone;

    public Task(String _name) {
        name = _name;
        isDone = false;
    }

    public void markDone() {
        isDone = false; 
    }
}
