import duke.command.ArgCommand;
import duke.command.Command;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import duke.command.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private Parser uut = new Parser();

    // TODO: may need to create a toy taskList and Ui to be able to test the system

    @Test
    public void testParse_sanitise() {
        try {
            Command testCmd = uut.parse("todo hello\tworld");
        } catch (DukeException excp) {

        }
    }
}
