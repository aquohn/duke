package aquohn.duke.command;

public enum CMD {
    LIST("list"),
    BYE("bye"),
    DONE("done"),
    TODO("todo"),
    EVENT("event"),
    DLINE("deadline");

    private final String cmdStr;

    CMD (final String _cmdStr) {
        cmdStr = _cmdStr;
    }

    @Override
    public String toString() {
        return cmdStr;
    }
}
