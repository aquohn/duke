package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeFatalException;
import duke.exception.DukeResetException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

   private final File taskFile;

   /**
    * Constructs a new Storage object, with the task file at the specified path.
    * @param filePath Path at which to look for or create the data file.
    * @throws DukeFatalException If data file cannot be setup.
    */
   public Storage(String filePath) throws DukeFatalException {
      taskFile = new File(filePath);
      if (!taskFile.exists()) {
         try {
            if (!taskFile.createNewFile()) {
               throw new IOException();
            }
         } catch (IOException excp) {
            throw new DukeFatalException("Unable to setup data file, try checking your permissions?");
         }
      }
   }

   /**
    * Writes data to the task file.
    * @param taskFileStr String to write to task file.
    * @throws DukeFatalException If unable to write to the task file.
    */
   public void writeTaskFile(String taskFileStr) throws DukeFatalException {
      // TODO: figure out some way of editing that doesn't involve rewriting everything each time
      // Maybe some kind of diff file?

      try {
         FileWriter taskFileWr = new FileWriter(taskFile);
         taskFileWr.write(taskFileStr);
         taskFileWr.close();
      } catch (IOException excp) {
         throw new DukeFatalException("Unable to write data! Some data may have been lost,");
      }
   }

   /**
    * Parses a task file into an ArrayList of tasks.
    * @return An ArrayList of the tasks extracted from the task file.
    * @throws DukeResetException If any corruption or improperly formatted entries are encountered.
    * @throws DukeFatalException If file cannot be found.
    */
   public ArrayList<Task> parseTaskFile() throws DukeResetException, DukeFatalException {
      ArrayList<Task> taskArrList = new ArrayList<Task>();
      if (taskFile.length() == 0) { // file is empty
         return taskArrList;
      }

      //message for when data corruption is detected in the file
      String corrupt = "Data file has been corrupted!";

      try {
         Scanner taskScanner = new Scanner(taskFile);
         while (taskScanner.hasNextLine()) {
            String taskLine = taskScanner.nextLine();
            String[] taskArr = taskLine.split("\t");
            String taskType = taskArr[0];
            LocalDateTime datetime;
            boolean isDone;

            //check if task is done
            String doneStr = taskArr[1].strip();
            if (doneStr.equals("1")) {
               isDone = true;
            } else if (doneStr.equals("0")) {
               isDone = false;
            } else {
               throw new DukeResetException(corrupt);
            }

            //add tasks to taskArrList
            // TODO: use hashmap of enums, each with a taskFromStr function returning type Task
            switch (taskType) {
            case "T":
               ToDoTask currToDoTask = new ToDoTask(taskArr[2]);
               if (isDone) {
                  currToDoTask.markDone();
               }
               taskArrList.add(currToDoTask);
               break;
            case "E":
               datetime = LocalDateTime.parse(taskArr[3], TimedTask.getDataFormatter());
               EventTask currEventTask = new EventTask(taskArr[2], datetime);
               if (isDone) {
                  currEventTask.markDone();
               }
               taskArrList.add(currEventTask);
               break;
            case "D":
               datetime = LocalDateTime.parse(taskArr[3], TimedTask.getDataFormatter());
               DeadlineTask currDeadlineTask = new DeadlineTask(taskArr[2], datetime);
               if (isDone) {
                  currDeadlineTask.markDone();
               }
               taskArrList.add(currDeadlineTask);
               break;
            default:
               throw new DukeResetException(corrupt);
            }
         }
         taskScanner.close();
      } catch (DukeException | DateTimeParseException | IndexOutOfBoundsException excp) {
         throw new DukeResetException(corrupt);
      } catch (FileNotFoundException excp) {
         throw new DukeFatalException("Unable to find data file, try opening Duke again?");
      }

      return taskArrList;
   }
}
