package aquohn.duke.command;

public enum Command {
    List("list") {
      public void execute(String inputStr) {

      }
    },
    Bye("bye") {

    },
    Done("done") {

    },
    ToDo("todo") {

    },
    Event("event") {

    },
    Deadline("deadline") {

    };

    private final String cmdStr;

    Command(final String _cmdStr) {
        cmdStr = _cmdStr;
    }

    @Override
    public String toString() {
        return cmdStr;
    }
}
