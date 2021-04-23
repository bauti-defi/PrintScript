package austral.ingsis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderPS {

  public static List<String> read(String path) {
    List<String> document = new ArrayList<>();
    try {
      File statements = new File(path);
      Scanner reader = new Scanner(statements);
      while (reader.hasNextLine()) {
        document.add(reader.nextLine());
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return document;
  }
}
