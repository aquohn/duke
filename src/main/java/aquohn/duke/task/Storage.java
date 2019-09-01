package aquohn.duke.task;

import java.io.File;

public class Storage {

   private File taskFile;

   public Storage(String filePath) {
      taskFile = new File(filePath);

   }
}
