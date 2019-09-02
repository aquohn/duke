package duke.command;

public enum CMD {
    LIST("list") {
        public Command getCommand() {
            return new ListCommand();
        }
    },
    BYE("bye") {
        public Command getCommand() {
            return new ByeCommand();
        }
    },
    DONE("done") {
        public Command getCommand() {
            return new DoneCommand();
        }
    },
    TODO("todo") {
        public Command getCommand() {
            return new NewToDoCommand();
        }
    },
    EVENT("event") {
        public Command getCommand() {
            return new NewEventCommand();
        }
    },
    DLINE("deadline") {
        public Command getCommand() {
            return new NewDeadlineCommand();
        }
    };

    private final String cmdStr;

    CMD (final String _cmdStr) {
        cmdStr = _cmdStr;
    }

    @Override
    public String toString() {
        return cmdStr;
    }

    public abstract Command getCommand();

}
